package com.sanjana.decompression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * This class decompresses Gzip archives
 */
public class GzipDecompression extends DecompressionService {

    /**
     * This relies on the base implementation of unzipping an archive file by simply
     * specifying to the abstract class the specific InputStream to be used to read Gzip files
     * @param filePath The file's original location, since decompression happens first
     */
    @Override
    public void setInputStream(String filePath) {
        try {
            inputStream = new GZIPInputStream(new FileInputStream(filePath));
        } catch (IOException e) {
            System.out.println("There was an error in opening the file at - " + filePath);
        }
    }
}
