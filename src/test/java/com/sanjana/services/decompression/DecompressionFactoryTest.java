package com.sanjana.services.decompression;

import com.sanjana.file.File;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecompressionFactoryTest {
    private DecompressionFactory factory;

    @Before
    public void init() {
        factory = new DecompressionFactory();
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfFileCompressionIsEmpty() throws Exception {
        File file = new File();
        factory.getService(file, "path");
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfFilePathIsEmpty() throws Exception {
        File file = new File();
        file.setCompression("gzip");
        factory.getService(file, null);
        factory.getService(file, "");
    }

    @Test
    public void shouldReturnNullIfInvalidCompressionProvided() throws Exception {
        File file = new File();
        file.setCompression("blah");
        factory.getService(file, "filePath");
    }

    @Test
    public void shouldReturnValidServiceForSupportedEncoding() throws Exception {
        File file = new File();
        file.setCompression("gzip");
        file.setPath("TestData/GzipEncrypted.txt.gz");

        DecompressionService service = factory.getService(file, "TestData/GzipEncrypted.txt.gz");

        assertEquals("GzipDecompression", service.getClass().getSimpleName());
        assertEquals("GZIPInputStream", service.inputStream.getClass().getSimpleName());
    }
}
