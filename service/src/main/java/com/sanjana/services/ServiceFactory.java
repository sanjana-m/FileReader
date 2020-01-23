package com.sanjana.services;

import com.sanjana.file.File;

/**
 * Abstract class from where all service generating factories inherit from
 */
public abstract class ServiceFactory {
    public String getFactoryName() {
        return getClass().getName();
    }

    /**
     *
     * @param file The input file
     * @param filePath The file's original location or intermediate file location
     * @return Service The appropriate service class which will be invoked to process the file
     * @throws Exception
     */
    public abstract Service getService(File file, String filePath) throws Exception;
}
