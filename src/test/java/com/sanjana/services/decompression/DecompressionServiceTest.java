package com.sanjana.services.decompression;

import com.sanjana.file.File;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DecompressionServiceTest {
    private DecompressionService service;
    private DecompressionFactory factory;

    @Before
    public void init() {
        factory = new DecompressionFactory();
    }

    @Test
    public void shouldNotDecompressBlankFilePath() throws Exception {
        String inputFile = "TestData/BzipUnencrypted.tar.bz2";

        File file = new File();
        file.setPath(inputFile);
        file.setCompression("bzip");

        service = factory.getService(file, inputFile);
        assertTrue(service.shouldDecompress());
    }

    @Test
    public void shouldDecompressValidFile() throws Exception {
        String inputFile = "TestData/BzipUnencrypted.tar.bz2";

        File file = new File();
        file.setPath(inputFile);
        file.setCompression("bzip");

        service = factory.getService(file, inputFile);
        assertTrue(service.shouldDecompress());
    }

    @Test
    public void shouldSetValidInputStreamForCompressedFile() throws Exception {
        String inputFile = "TestData/BzipUnencrypted.tar.bz2";

        File file = new File();
        file.setPath(inputFile);
        file.setCompression("bzip");

        service = factory.getService(file, inputFile);
        assertTrue(service.inputStream instanceof InputStream);
    }

    @Test
    public void shouldReturnNewFilePathWithDecompressedData() throws Exception {
        String inputFile = "TestData/BzipUnencrypted.tar.bz2";

        File file = new File();
        file.setPath(inputFile);
        file.setCompression("bzip");

        service = factory.getService(file, inputFile);
        String output = service.decompress();

        assertEquals(service.getOutputFile(inputFile), output);
    }

    @Test
    public void shouldCreateNewFileWithDecompressedData() throws Exception {
        String inputFile = "TestData/BzipUnencrypted.tar.bz2";

        File file = new File();
        file.setPath(inputFile);
        file.setCompression("bzip");

        service = factory.getService(file, inputFile);
        String output = service.decompress();

        assertTrue((new java.io.File(output)).exists());
    }
}
