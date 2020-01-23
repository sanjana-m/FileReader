package com.sanjana.services.decryption;

import com.sanjana.services.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This service provides decrypting abilities to decrypt a file
 */
public abstract class DecryptionService extends Service {
    private String filePath;
    private String privateKey;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String decrypt() {
        throw new UnsupportedOperationException("Decryption has not been implemented!");
    }

    @Override
    public String getServiceName() {
        return "Decryption";
    }
}
