package com.sanjana.services;

import com.sanjana.services.decoding.DecodingFactory;
import com.sanjana.services.decompression.DecompressionFactory;
import com.sanjana.services.decryption.DecryptionFactory;

/**
 * Class that decides which factory to return based on the operation needed
 */
public class ServiceProducer {
    public enum FACTORIES {DECOMPRESSION, DECRYPTION, DECODING}

    /**
     * @param factories One of the possible enumerable operations
     * @return ServiceFactory responsible for generating the appropriate factory
     * which in turn creates the Service object needed to process the file
     */
    public static ServiceFactory getFactory(FACTORIES factories) {
        switch (factories) {
            case DECODING:
                return new DecodingFactory();
            case DECOMPRESSION:
                return new DecompressionFactory();
            case DECRYPTION:
                return new DecryptionFactory();
        }
        return null;
    }
}
