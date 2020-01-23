package com.sanjana.producers;

import com.sanjana.decoding.DecodingService;
import com.sanjana.decompression.DecompressionService;
import com.sanjana.decryption.DecryptionService;
import com.sanjana.services.ServiceFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class that decides which factory to return based on the operation needed
 */
public class ServiceProducer {
    public enum FACTORIES {DECOMPRESSION, DECRYPTION, DECODING}

    private Object invokeMethod(String jarName, Object object, String methodStr, Object... args) {
        Object result = null;
        try {
            Method method = null;
            for(Method method1: object.getClass().getMethods()) {
                if(method1.getName().equals(methodStr)) {
                    method = method1;
                    break;
                }
            }

            result = method.invoke(object, args);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Object getJarObject(String jarName, String className) {
        Object instance = null;
        try {
            URLClassLoader child = new URLClassLoader(
                    new URL[] {new File("out/artifacts/" + jarName).toURI().toURL()},
                    this.getClass().getClassLoader()
            );

            Class classToLoad = Class.forName(className, true, child);
            instance = classToLoad.newInstance();
        } catch (MalformedURLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public String perform(FACTORIES factories, String filePath, Object ...args) {
        switch (factories) {
            case DECODING: {
                String jarPath = "Decoding_jar/decoding.jar";
                ServiceFactory factory = (ServiceFactory) getJarObject(jarPath, "com.sanjana.decoding.DecodingFactory");
                DecodingService service = (DecodingService) invokeMethod(jarPath, factory, "getService", args);
                return (String) invokeMethod(jarPath, service, "decode");
            }
            case DECOMPRESSION: {
                String jarPath = "Decompression_jar/decompression.jar";
                ServiceFactory factory = (ServiceFactory) getJarObject(jarPath, "com.sanjana.decompression.DecompressionFactory");
                DecompressionService service = (DecompressionService) invokeMethod(jarPath, factory, "getService", args);
                return (String) invokeMethod(jarPath, service, "decompress");
            }
            case DECRYPTION: {
                String jarPath = "Decryption_jar/decryption.jar";
                ServiceFactory factory = (ServiceFactory) getJarObject(jarPath, "com.sanjana.decryption.DecryptionFactory");
                DecryptionService service = (DecryptionService) invokeMethod(jarPath, factory, "getService", args);
                return (String) invokeMethod(jarPath, service, "decrypt");
            }
        }
        return null;
    }
}
