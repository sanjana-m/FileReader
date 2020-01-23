package com.sanjana.file;

import com.sanjana.services.ServiceProducer;
import com.sanjana.services.decoding.DecodingService;
import com.sanjana.services.decompression.DecompressionService;
import com.sanjana.services.decryption.DecryptionService;
import com.sanjana.utils.Utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.sanjana.services.ServiceProducer.FACTORIES.DECOMPRESSION;
import static com.sanjana.services.ServiceProducer.FACTORIES.DECODING;
import static com.sanjana.services.ServiceProducer.FACTORIES.DECRYPTION;

/**
 * This is where the file actually gets read and processed, which happens in the static method process()
 * The file is checked for compression, encryption and encoding in this specific order
 * The method returns the file path of the now decompressed, decrypted and decoded file, which can be found at the root of the project
 */

public class FileManager {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    public static String process(File file) {
        if(file == null || Utils.isStringBlank(file.getPath())) {
            System.out.println("Please enter a valid file");
            return null;
        }

        String outputFile = file.getPath();

        if (file.hasCompression()) {
            try {
                DecompressionService service = (DecompressionService) ServiceProducer.getFactory(DECOMPRESSION).getService(file, outputFile);
                outputFile = service.decompress();
            } catch (Exception e) {
                System.out.println("There has been an exception in decompression - " + e.getMessage());
                logger.log(Level.ERROR, e);
                return null;
            }
        }

        if (file.hasEncryption()) {
            try {
                DecryptionService service = (DecryptionService) ServiceProducer.getFactory(DECRYPTION).getService(file, outputFile);
                outputFile = service.decrypt();
            } catch (Exception e) {
                System.out.println("There has been an exception in decryption - " + e.getMessage());
                logger.log(Level.ERROR, e);
                return null;
            }
        }

        if(file.hasEncoding()) {
            try {
                DecodingService service = (DecodingService) ServiceProducer.getFactory(DECODING).getService(file, outputFile);
                outputFile = service.decode();
            } catch (Exception e) {
                System.out.println("There has been an exception in decoding - " + e.getMessage());
                logger.log(Level.ERROR, e);
                return null;
            }
        }

        return outputFile;
    }
}
