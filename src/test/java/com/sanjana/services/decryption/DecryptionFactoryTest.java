package com.sanjana.services.decryption;

import com.sanjana.file.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DecryptionFactoryTest {
    private DecryptionFactory factory;

    @Before
    public void init() {
        factory = new DecryptionFactory();
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfFileEncryptionIsEmpty() throws Exception {
        File file = new File();
        file.setPrivateKey("hellohellohello1");
        factory.getService(file, "path");
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfPrivateKeyIsEmpty() throws Exception {
        File file = new File();
        file.setEncryption("aes128");
        factory.getService(file, "path");
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfFilePathIsEmpty() throws Exception {
        File file = new File();
        file.setEncryption("aes128");
        file.setPrivateKey("hellohellohello1");
        factory.getService(file, null);
        factory.getService(file, "");
    }

    @Test
    public void shouldReturnNullIfInvalidDecodingProvided() throws Exception {
        File file = new File();
        file.setEncryption("blah");
        file.setPrivateKey("hellohellohello1");
        assertNull(factory.getService(file, "filePath"));
    }

    @Test
    public void shouldReturnValidServiceForSupportedEncoding() throws Exception {
        File file = new File();
        file.setEncryption("aes128");
        file.setPath("TestData/EncryptedText.txt");
        file.setPrivateKey("tellmeyoursecret");

        DecryptionService service = factory.getService(file, "TestData/EncryptedText.txt");

        Assert.assertEquals("AesDecryption", service.getClass().getSimpleName());
    }
}
