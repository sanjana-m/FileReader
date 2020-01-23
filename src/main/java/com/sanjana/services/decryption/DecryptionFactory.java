package com.sanjana.services.decryption;

import com.sanjana.file.File;
import com.sanjana.file.FileManager;
import com.sanjana.services.ServiceFactory;
import com.sanjana.utils.Utils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the factory that provides an instance of a DecryptionService
 */
public class DecryptionFactory extends ServiceFactory {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    enum ENCRYPTION_METHODS  { aes128 }

    /**
     * @param file The input file
     * @param filePath The file's original location or intermediate file location
     * @return DecryptionService that will decrypt the intermediate file
     * @throws Exception
     */
    public DecryptionService getService(File file, String filePath) throws Exception {
        if (Utils.isStringBlank(file.getEncryption()) || Utils.isStringBlank(filePath) || Utils.isStringBlank(file.getPrivateKey())) {
            throw new Exception("Insufficient data to decrypt the file");
        }

        DecryptionService service = null;
        try {
            switch (ENCRYPTION_METHODS.valueOf(file.getEncryption().toLowerCase().trim())) {
                case aes128:
                    service = new AesDecryption();
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid encryption method provided");
            logger.log(Level.ERROR, e);
            return null;
        }

        service.setFilePath(filePath);
        service.setPrivateKey(file.getPrivateKey());

        return service;
    }
}
