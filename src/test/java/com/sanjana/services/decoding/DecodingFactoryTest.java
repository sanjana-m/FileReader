package com.sanjana.services.decoding;

import com.sanjana.file.File;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class DecodingFactoryTest {
    private DecodingFactory factory;

    @Before
    public void init() {
        factory = new DecodingFactory();
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfFileEncodingIsEmpty() throws Exception {
        File file = new File();
        factory.getService(file, "path");
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfFilePathIsEmpty() throws Exception {
        File file = new File();
        file.setEncoding("utf-8");
        factory.getService(file, null);
        factory.getService(file, "");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEIfInvalidDecodingProvided() throws Exception {
        File file = new File();
        file.setEncoding("blah");
        factory.getService(file, "filePath");
    }

    @Test
    public void shouldReturnValidServiceForSupportedEncoding() throws Exception {
        File file = new File();
        file.setEncoding("utf-8");
        DecodingService service = factory.getService(file, "TestData/PlainText.txt");
        assertEquals("DecodingService", service.getClass().getSimpleName());
        assertEquals("TestData/PlainText.txt", service.getFilePath());
        assertEquals(StandardCharsets.UTF_8, service.getCharset());
    }
}
