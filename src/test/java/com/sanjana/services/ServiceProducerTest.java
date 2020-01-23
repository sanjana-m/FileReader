package com.sanjana.services;

import com.sanjana.producers.ServiceProducer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ServiceProducerTest {
    @Test
    public void enumValueCheck() {
        assertEquals("DECOMPRESSION", ServiceProducer.FACTORIES.DECOMPRESSION.name());
        assertEquals("DECODING", ServiceProducer.FACTORIES.DECODING.name());
        assertEquals("DECRYPTION", ServiceProducer.FACTORIES.DECRYPTION.name());
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnThrowExceptionIfInputIsNull() {
        assertNull(ServiceProducer.getFactory(null));
    }

    @Test
    public void shouldReturnValidFactoryIfInputIsInEnum() {
        assertEquals("DecompressionFactory", ServiceProducer
                                                        .getFactory(ServiceProducer.FACTORIES.DECOMPRESSION)
                                                        .getClass()
                                                        .getSimpleName()
        );
    }
}
