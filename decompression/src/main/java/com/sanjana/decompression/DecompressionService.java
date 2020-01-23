package com.sanjana.decompression;

import com.sanjana.services.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This service provides decompressing abilities to unzip an archive input file
 */
public abstract class DecompressionService extends Service {
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

        return getServiceName() + "_" + (new SimpleDateFormat("Y-M-d_HH:mm:ss")).format(new Date())  + "." + "txt";
    }

    /**
     * Do not attempt to compress a file whose location has not been specified or whose InputStream hasn't been set
     * @return true if input is sufficient to decompress, false otherwise
     */
    boolean shouldDecompress() {
        if(filePath == null || filePath.isEmpty()) {
            System.out.println("FILE TO DECOMPRESS IS EMPTY");
            return false;
        }

        if(inputStream == null) {
            System.out.println("INVALID INPUT STREAM");
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
                System.out.println("DECOMPRESSED DATA - " + new String(buffer));
                out.write(buffer, 0, len);
            }

            inputStream.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error while decompressing: " + e.getMessage());
        }
        return outputFile;
    }

    @Override
    public String getServiceName() {
        return "Decompression";
    }
}
