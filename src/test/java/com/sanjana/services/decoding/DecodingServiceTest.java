package com.sanjana.services.decoding;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class DecodingServiceTest {
    private DecodingService service;

    @Before
    public void init() {
        service = new DecodingService();
    }

    @Test
    public void shouldReturnNullIfFilePathIsEmpty() {
        service.setFilePath(null);
        assertNull(service.decode());

        service.setFilePath("");
        assertNull(service.decode());
    }

    @Test
    public void shouldSetDefaultCharsetToUtf8() {
        service.setFilePath("TestData/PlainText.txt");
        service.decode();
        assertEquals(StandardCharsets.UTF_8, service.getCharset());
    }

    @Test
    public void shouldCreateNewFileWithDecodedData() {
        service.setFilePath("TestData/PlainText.txt");
        String outputFile = service.decode();
        assertTrue((new File(outputFile)).exists());
    }
}
