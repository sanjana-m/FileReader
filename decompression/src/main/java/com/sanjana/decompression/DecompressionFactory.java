package com.sanjana.decompression;

import com.sanjana.file.File;
import com.sanjana.services.ServiceFactory;
import com.sanjana.utils.Utils;

/**
 * This is the factory that provides an instance of a DecompressionService
 */
public class DecompressionFactory extends ServiceFactory {
    enum COMPRESSION_METHODS  {gzip, bzip}

    /**
     * @param file The input file
     * @param filePath The file's original location or intermediate file location
     * @return DecompressionService that will decompress the intermediate file
     * @throws Exception
     */
    public DecompressionService getService(File file, String filePath) throws Exception {
        if (Utils.isStringBlank(file.getCompression()) || Utils.isStringBlank(filePath))
            throw new Exception("Insufficient data to decompress the file");

        DecompressionService service = null;
        try {
            switch (COMPRESSION_METHODS.valueOf(file.getCompression().toLowerCase().trim())) {
                case gzip:
                    service = new GzipDecompression();
                    break;
                case bzip:
                    service = new BzipDecompression();
                    break;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Invalid compression method provided!");
            return null;
        }

        service.setFilePath(file.getPath());
        service.setInputStream(file.getPath());

        return service;
    }
}
