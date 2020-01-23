package com.sanjana.services;

import com.sanjana.file.File;
import com.sanjana.services.decoding.DecodingFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceFactoryTest {
    @Test
    public void shouldReturnClassNameOfFactory() {
        Assert.assertEquals("com.sanjana.services.decoding.DecodingFactory", (new DecodingFactory()).getFactoryName());
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfInsufficientDataToCreateService() throws Exception {
        assertEquals("DecodingService", (new DecodingFactory()).getService(new File(), "filepath").getClass().getSimpleName());
    }

    @Test
    public void shouldReturnServiceIfDataIsValid() {
        File file = new File();
        file.setEncoding("utf-8");
        try {
            assertEquals("DecodingService", (new DecodingFactory()).getService(file, "filepath").getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
