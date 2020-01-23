package com.sanjana;

import com.sanjana.file.File;
import com.sanjana.file.FileManager;
import com.sanjana.utils.Utils;
import com.sanjana.utils.CommandLineProcessor;

/**
 * This is the driver of the file reader project
 * It takes one to four arguments of form <ATTRIBUTE>=<VALUE>
 * Sample usage - java com.sanjana.ReadFile file=example.txt charset=utf-8 compression=gzip encryption=AES128
 */

public class FileProcessor {
    public static void main(String[] args) {
        File file = CommandLineProcessor.process(args);

        if(Utils.isObjectNull(file)) {
            System.out.println("Please provide a valid file and options to process the file");
            return;
        }

        System.out.println(file.getPath() + " " + file.getEncoding() + " " + file.getCompression() + " " + file.getEncryption());
        System.out.println(FileManager.process(file));
    }
}
