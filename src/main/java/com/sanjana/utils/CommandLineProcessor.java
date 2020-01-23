package com.sanjana.utils;

import com.sanjana.file.File;

/**
 * This processes the command line args and returns the File object after setting options provided
 */
public class CommandLineProcessor {
    enum  OPTIONS {file, charset, encryption, compression, secret}

    /**
     * @param data The array of command line args
     * @param file The file object whose attributes will be set on reading every argument
     */
    private static void setFileAttribute(String[] data, File file) {
        if(!Utils.isObjectNull(file) && !Utils.isObjectBlank(data) && data.length >= 2) {
            switch(OPTIONS.valueOf(data[0].toLowerCase())) {
                case file: file.setPath(data[1]); break;
                case charset: file.setEncoding(data[1]); break;
                case encryption: file.setEncryption(data[1]); break;
                case compression: file.setCompression(data[1]); break;
                case secret: file.setPrivateKey(data[1]); break;
            }
        }
    }

    /**
     * @param commandLineArgs The list of command line args
     * @return File object after setting attributes
     */
    public static File process(String[] commandLineArgs) {
        if(Utils.isObjectBlank(commandLineArgs)) {
            System.out.println("Insufficient arguments to process the file");
            return null;
        }

        File file = new File();
        for(String arg : commandLineArgs) {
            setFileAttribute(arg.split("="), file);
        }

        return file;
    }
}
