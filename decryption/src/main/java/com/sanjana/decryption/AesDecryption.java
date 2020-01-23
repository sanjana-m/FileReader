package com.sanjana.decryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * This class decrypts files encrypted with the AES128 algorithm
 */
public class AesDecryption extends DecryptionService {
    /**
     * This performs the asymmetric decryption by retrieving the private key used to encrypt the file
     * @return A location where decrypted file contents are written
     */
    @Override
    public String decrypt() {
        if(getFilePath() == null || getFilePath().isEmpty())
            return null;

        String outputFile = null;

        try {
            SecretKeySpec secretKey = new SecretKeySpec(getPrivateKey().trim().getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            outputFile = getOutputFile(getFilePath());

            FileInputStream fis = new FileInputStream(getFilePath());
            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] in = new byte[64];
            while (fis.read(in) != -1) {
                byte[] decoded = Base64.getDecoder().decode((new String(in).trim()).getBytes());
                byte[] output = cipher.update(decoded, 0, decoded.length);
                if (output != null)
                    fos.write(output);
            }

            byte[] output = cipher.doFinal();
            if (output != null)
                fos.write(output);

            fis.close();
            fos.flush();
            fos.close();
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.getMessage());
        }
        return outputFile;
    }
}
