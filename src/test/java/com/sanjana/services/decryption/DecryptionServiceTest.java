package com.sanjana.services.decryption;

import com.sanjana.file.File;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecryptionServiceTest {
    private DecryptionService service;
    private DecryptionFactory factory;

    @Before
    public void init() {
        factory = new DecryptionFactory();
    }

    @Test
    public void shouldReturnNullIfFilePathIsBlank() throws Exception {
        String input = "TestData/EncryptedText.txt";

        File file = new File();
        file.setPath(input);
        file.setEncryption("aes128");
        file.setPrivateKey("tellmeyoursecret");

        service = factory.getService(file, input);
        service.setFilePath("");
        assertNull(service.decrypt());
    }

    @Test
    public void shouldCreateNewDecryptedFile() throws Exception {
        String input = "TestData/EncryptedText.txt";

        File file = new File();
        file.setPath(input);
        file.setEncryption("aes128");
        file.setPrivateKey("tellmeyoursecret");

        service = factory.getService(file, input);
        String output = service.decrypt();

        assertTrue((new java.io.File(output)).exists());
    }
}
