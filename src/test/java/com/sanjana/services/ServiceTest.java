package com.sanjana.services;

import com.sanjana.services.decoding.DecodingService;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ServiceTest {
    private Service service;

    @Before
    public void init() {
        service = new DecodingService();
    }

    @Test
    public void shouldReturnServiceName() {
        assertEquals("Decoding", (new DecodingService()).getServiceName());
    }

    @Test
    public void shouldReturnNullIfInputFileIsEmpty() {
        assertNull(service.getOutputFile(null));
        assertNull(service.getOutputFile(""));
    }

    @Test
    public void shouldReturnOutputFileWithTxtExtensionIfInputFileHasNoExtension() {
        assertTrue(service.getOutputFile("blah").endsWith(".txt"));
    }

    @Test
    public void shouldReturnOutputFileWithSameExtensionAsInput() {
        assertTrue(service.getOutputFile("blah.pdf").endsWith(".pdf"));
    }

    @Test
    public void shouldReturnOutputFileWithCurrentDate() {
        String date = (new SimpleDateFormat("Y-M-d_HH:mm:ss")).format(new Date());
        assertTrue(service.getOutputFile("blah.pdf").contains(date));
    }
}
