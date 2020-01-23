package com.sanjana.file;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileManagerTest {
    @Test
    public void shouldReturnNullIfFileIsNull() {
        assertNull(FileManager.process(null));
    }

    @Test
    public void shouldReturnNullIfFilePathIsNull() {
        File file = new File();
        assertNull(FileManager.process(null));
    }

    @Test
    public void shouldReturnNullIfFilePathIsEmpty() {
        File file = new File();
        file.setPath("");
        assertNull(FileManager.process(null));
    }

    @Test
    public void shouldReturnFilePathIfNoOptionsGiven() {
        File file = new File();
        file.setPath("blah.txt");

        String outputFile = FileManager.process(file);
        assertEquals(outputFile, file.getPath());
    }

    // COMPRESSION
    @Test
    public void shouldReturnNullIfInvalidCompressionGiven() {
        File file = new File();
        file.setPath("blah.txt");
        file.setCompression("lala");

        String outputFile = FileManager.process(file);
        assertNull(outputFile);
    }

    @Test
    public void shouldReturnCreatedDecompressedFileIfSpecified() {
        File file = new File();
        file.setPath("blah_txt.tar.bz2");
        file.setCompression("bzip");

        String outputFile = FileManager.process(file);
        assertNotNull(outputFile);

        java.io.File output = new java.io.File(outputFile);
        assertTrue(output.exists());
    }

    // ENCRYPTION
    @Test
    public void shouldReturnNullIfInvalidEncryptionGiven() {
        File file = new File();
        file.setPath("blah_encrypted.txt");
        file.setEncryption("lala");
        file.setPrivateKey("tellmeyoursecret");

        String outputFile = FileManager.process(file);
        assertNull(outputFile);
    }

    @Test
    public void shouldReturnNullIfPrivateKeyIsNotGiven() {
        File file = new File();
        file.setPath("blah_encrypted.txt");
        file.setEncryption("aes128");

        String outputFile = FileManager.process(file);
        assertNull(outputFile);
    }

    @Test
    public void shouldReturnCreatedDecryptedFileIfSpecified() {
        File file = new File();
        file.setPath("blah_encrypted.txt");
        file.setEncryption("aes128");
        file.setPrivateKey("tellmeyoursecret");

        String outputFile = FileManager.process(file);
        assertNotNull(outputFile);

        java.io.File output = new java.io.File(outputFile);
        assertTrue(output.exists());
    }

    // ENCODING
    @Test
    public void shouldReturnNullIfInvalidEncodingGiven() {
        File file = new File();
        file.setPath("blah.txt");
        file.setEncoding("lala");

        String outputFile = FileManager.process(file);
        assertNull(outputFile);
    }

    @Test
    public void shouldReturnCreatedDecodedFileIfSpecified() {
        File file = new File();
        file.setPath("blah.txt");
        file.setEncoding("utf-8");

        String outputFile = FileManager.process(file);
        assertNotNull(outputFile);

        java.io.File output = new java.io.File(outputFile);
        assertTrue(output.exists());
    }
}
