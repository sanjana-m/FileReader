package com.sanjana.file;

import com.sanjana.producers.ServiceProducer;
import com.sanjana.utils.Utils;

import static com.sanjana.producers.ServiceProducer.FACTORIES.DECOMPRESSION;
import static com.sanjana.producers.ServiceProducer.FACTORIES.DECODING;
import static com.sanjana.producers.ServiceProducer.FACTORIES.DECRYPTION;

/**
 * This is where the file actually gets read and processed, which happens in the static method process()
 * The file is checked for compression, encryption and encoding in this specific order
 * The method returns the file path of the now decompressed, decrypted and decoded file, which can be found at the root of the project
 */

public class FileManager {
    public static String process(File file) {
        if(file == null || Utils.isStringBlank(file.getPath())) {
            System.out.println("Please enter a valid file");
            return null;
        }

        String outputFile = file.getPath();
        ServiceProducer producer = new ServiceProducer();

        if (file.hasCompression()) {
            try {
                outputFile = producer.perform(DECOMPRESSION, outputFile, file, outputFile);
            } catch(Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        if (file.hasEncryption()) {
            try {
                outputFile = producer.perform(DECRYPTION, outputFile, file, outputFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        if(file.hasEncoding()) {
            try {
                outputFile = outputFile = producer.perform(DECODING, outputFile, file, outputFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        return outputFile;
    }
}
