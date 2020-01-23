package com.sanjana.file;

import com.sanjana.utils.Utils;

/**
 * This class holds all the options passed via command line
 */

public class File {
    // Path of the file which is being processed
    private String path;

    // Charset
    private String encoding;

    // Algorithm used to compress the file
    private String compression;

    // Encryption used in file
    private String encryption;

    // Secret key used to encrypt the file
    private String privateKey;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public boolean hasCompression() {
        return !Utils.isStringBlank(getCompression());
    }

    public boolean hasEncryption() {
        return !Utils.isStringBlank(getEncryption());
    }

    public boolean hasEncoding() {
        return !Utils.isStringBlank(getEncoding());
    }
}
