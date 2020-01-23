package com.sanjana.services;

import com.sanjana.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract holder for all services that provide decompression, decryption and decoding
 */
public abstract class Service {
    public String getServiceName() {
        return "Service";
    }

    /**
     * @param filePath
     * @return The location where the contents after the service's operation is performed will be stored
     */
    protected String getOutputFile(String filePath) {
        if(Utils.isStringBlank(filePath))
            return null;

        String extension = filePath.lastIndexOf('.') == -1
                ? "txt"
                : (filePath.substring(filePath.lastIndexOf('.') + 1));
        return getServiceName() + "_" + (new SimpleDateFormat("Y-M-d_HH:mm:ss")).format(new Date())  + "." + extension;
    }
}
