package com.sanjana.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CommandLineProcessorTest {
    @Test
    public void shouldReturnNullIfArgsIsNull() {
        assertNull(CommandLineProcessor.process(null));
    }

    @Test
    public void shouldReturnEmptyFileIfArgsIsEmpty() {
        String[] args = new String[0];
        assertNull(CommandLineProcessor.process(args).getPath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfArgsIsInvalid() {
        String[] args = {"invalid=arg"};
        assertNull(CommandLineProcessor.process(args).getPath());
    }

    @Test
    public void shouldCreateFileIfArgsIsValid() {
        String[] args = {"charset=utf-8"};
        assertEquals("utf-8", CommandLineProcessor.process(args).getEncoding());
    }
}
