package com.sanjana.services.decoding;

import com.sanjana.file.File;
import com.sanjana.file.FileManager;
import com.sanjana.services.ServiceFactory;
import com.sanjana.utils.Utils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * This is the factory that provides an instance of a DecodingService
 */
public class DecodingFactory extends ServiceFactory {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    /**
     * @param file The input file
     * @param filePath The file's original location or intermediate file location
     * @return DecodingService that will actually decode the intermediate file
     * @throws Exception
     */
    public DecodingService getService(File file, String filePath) throws Exception {
        if (Utils.isStringBlank(file.getEncoding()) || Utils.isStringBlank(filePath)) {
            throw new Exception("Insufficient data to decode the file");
        }

        DecodingService service = new DecodingService();
        try {
            switch (Objects.requireNonNull(CHARSETS.fromString(file.getEncoding().toLowerCase().trim()))) {
                case utf_8:
                    service.setCharset(StandardCharsets.UTF_8);
                    break;
                case iso_8859_1:
                    service.setCharset(StandardCharsets.ISO_8859_1);
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid encryption method provided!");
            logger.log(Level.ERROR, e);
            return null;
        }

        service.setFilePath(filePath);
        return service;
    }

    /**
     * This is purely syntactic sugar to handle hyphenated Strings and map them to underscored enum values
     */
    public enum CHARSETS {
        utf_8("utf-8"),
        iso_8859_1("iso-8859-1");

        private CHARSETS(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static CHARSETS fromString(String text) {
            for (CHARSETS b : CHARSETS.values()) {
                if (b.name.equalsIgnoreCase(text.trim())) {
                    return b;
                }
            }
            return null;
        }

        private final String name;
    }
}
