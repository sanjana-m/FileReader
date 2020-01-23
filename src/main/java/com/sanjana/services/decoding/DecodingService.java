package com.sanjana.services.decoding;

import com.sanjana.file.FileManager;
import com.sanjana.services.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * This service provides decoding abilities while reading a file
 */
public class DecodingService extends Service {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    private Charset charset;
    private String filePath;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the file in the charset specified and writes to a new file with the same charset
     * @return The location of the newly encoded file's contents
     */
    public String decode() {
        if(filePath == null || filePath.isEmpty())
            return null;

        if(charset == null)
            charset = StandardCharsets.UTF_8;

        String outputFile = null;

        try {
            File fileDir = new File(filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), charset));

            outputFile = getOutputFile(filePath);
            BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

            char[] buffer = new char[1024];
            int len;

            while ((len = in.read(buffer)) > 0) {
                String str1 = new String(String.valueOf(buffer, 0, len).getBytes(charset));
                logger.log(Level.DEBUG,str1);
                bufWriter.write(str1);
            }

            in.close();
            bufWriter.close();
        }
        catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return outputFile;
    }

    @Override
    public String getServiceName() {
        return "Decoding";
    }
}
