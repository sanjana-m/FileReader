package com.sanjana.services.decompression;

import com.sanjana.file.FileManager;
import com.sanjana.services.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This service provides decompressing abilities to unzip an archive input file
 */
public abstract class DecompressionService extends Service {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    InputStream inputStream;
    String filePath;

    public abstract void setInputStream(String filePath);

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The ASSUMPTION here is that the input file is an archived .txt file
     * @param filePath
     * @return The location to write a .txt file
     */
    @Override
    protected String getOutputFile(String filePath) {
        if(filePath == null || filePath.isEmpty())
            return null;

        String extension = filePath.lastIndexOf('.') == -1
                ? "txt"
                : (filePath.substring(filePath.lastIndexOf('.') + 1));

        // If the extension contains zipped extensions, try to find out if the file has an extension before the zipped extension
        // For example, if the file is named Test.txt.gz, extension will contain .gz, but the unzipped file will be .txt
        // The below code handles this case
        if(extension.equalsIgnoreCase("gz") || extension.contains("bz")) {
            String[] extensions = filePath.split("\\.");
            if(extensions.length >= 2)
                extension = extensions[extensions.length-2];
        }

        return getServiceName() + "_" + (new SimpleDateFormat("Y-M-d_HH:mm:ss")).format(new Date())  + "." + extension;
    }

    /**
     * Do not attempt to compress a file whose location has not been specified or whose InputStream hasn't been set
     * @return true if input is sufficient to decompress, false otherwise
     */
    boolean shouldDecompress() {
        if(filePath == null || filePath.isEmpty()) {
            System.out.println("The file to decompress is empty");
            return false;
        }

        if(inputStream == null) {
            System.out.println("You have provided an invalid input stream to perform decompression");
            return false;
        }
        return true;
    }

    /**
     * Base decompression implementation
     * @return File path where uncompressed contents are written
     */
    public String decompress() {
        if(!shouldDecompress())
            return null;

        String outputFile = null;
        byte[] buffer = new byte[1024];

        try{
            outputFile = getOutputFile(filePath);

            FileOutputStream out = new FileOutputStream(outputFile);

            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                logger.log(Level.DEBUG, "DECOMPRESSED DATA - " + new String(buffer));
                out.write(buffer, 0, len);
            }

            inputStream.close();
            out.close();
        } catch (IOException e){
            logger.log(Level.ERROR, e);
        } catch (Exception e) {
            System.out.println("Error while decompressing: " + e.getMessage());
            logger.log(Level.ERROR, e);
        }
        return outputFile;
    }

    @Override
    public String getServiceName() {
        return "Decompression";
    }
}
