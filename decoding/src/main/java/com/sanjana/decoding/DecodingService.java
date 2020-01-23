package com.sanjana.decoding;

import com.sanjana.services.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This service provides decoding abilities while reading a file
 */
public class DecodingService extends Service {
    private Charset charset;
    private String filePath;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the file in the charset specified and writes to a new file with the same charset
     * @return The location of the newly encoded file's contents
     */
    public String decode() {
        if(filePath == null || filePath.isEmpty())
            return null;

        if(charset == null)
            charset = StandardCharsets.UTF_8;

        String outputFile = null;

        try {
            File fileDir = new File(filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), charset));

            outputFile = getOutputFile(filePath);
            BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

            String str;

            while ((str = in.readLine()) != null) {
                String str1 = new String(str.getBytes(charset));
                System.out.println(str1);
                bufWriter.write(str1);
            }

            in.close();
            bufWriter.close();
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return outputFile;
    }

    @Override
    public String getServiceName() {
        return "Decoding";
    }
}
