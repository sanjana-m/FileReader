package com.sanjana.utils;

import com.sanjana.file.File;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {
    @Test
    public void shouldReturnTrueIfObjectIsNull() {
        File file = null;
        assertTrue(Utils.isObjectNull(file));
    }

    @Test
    public void shouldReturnFalseIfObjectIsNotNull() {
        File file = new File();
        assertFalse(Utils.isObjectNull(file));
    }

    @Test
    public void shouldReturnTrueIfStringIsNull() {
        String str = null;
        assertTrue(Utils.isStringBlank(str));
    }

    @Test
    public void shouldReturnTrueIfStringIsEmpty() {
        String str = "";
        assertTrue(Utils.isStringBlank(str));
    }

    @Test
    public void shouldReturnFalseIfStringIsNotEmpty() {
        String str = "abc";
        assertFalse(Utils.isStringBlank(str));
    }

    @Test
    public void shouldReturnTrueIfArrayIsNull() {
        ArrayList<Integer> list = null;
        assertTrue(Utils.isObjectBlank(list));
    }

    @Test
    public void shouldReturnTrueIfArrayIsEmpty() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        assertTrue(Utils.isObjectBlank(list));
    }

    @Test
    public void shouldReturnFalseIfArrayIsNotEmpty() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        assertFalse(Utils.isObjectBlank(list));
    }
}
