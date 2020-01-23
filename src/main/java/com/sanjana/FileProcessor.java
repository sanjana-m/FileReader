package com.sanjana;

import com.sanjana.file.File;
import com.sanjana.file.FileManager;
import com.sanjana.utils.CommandLineProcessor;
import com.sanjana.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the driver of the file reader project
 * It takes one to four arguments of form <ATTRIBUTE>=<VALUE>
 * Sample usage - java com.sanjana.ReadFile file=example.txt charset=utf-8 compression=gzip encryption=AES128
 */

public class FileProcessor {
    private static Logger logger = LogManager.getLogger(FileProcessor.class);

    public static void main(String[] args) {
        File file = CommandLineProcessor.process(args);

        if(Utils.isObjectNull(file)) {
            System.out.println("Please provide a valid file and options to process the file");
            return;
        }

        System.out.println(
                "Input file - " + file.getPath()
                + " with encoding - " + file.getEncoding()
                + " with compression - " + file.getCompression()
                + " with encryption - " + file.getEncryption()
        );

        String filename = FileManager.process(file);
        if(!Utils.isStringBlank(filename))
            System.out.println("The result is written to - "+ filename);
    }
}
