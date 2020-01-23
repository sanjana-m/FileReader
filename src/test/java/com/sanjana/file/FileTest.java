package com.sanjana.file;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileTest {
    @Test
    public void shouldSetEncryptionInFile() {
        File file = new File();
        file.setEncryption("aes128");
        assertEquals("aes128", file.getEncryption());
    }

    @Test
    public void shouldSetEncodingInFile() {
        File file = new File();
        file.setEncoding("utf-8");
        assertEquals("utf-8", file.getEncoding());
    }

    @Test
    public void shouldSetCompressionInFile() {
        File file = new File();
        file.setCompression("gzip");
        assertEquals("gzip", file.getCompression());
    }

    @Test
    public void shouldSetPathInFile() {
        File file = new File();
        file.setPath("hello.txt");
        assertEquals("hello.txt", file.getPath());
    }
}
