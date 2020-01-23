package com.sanjana.decompression;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class provides a way to decompress BZip2 archives
 */
public class BzipDecompression extends DecompressionService {
    @Override
    public void setInputStream(String filePath) {
        try {
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(new FileInputStream(filePath));
            inputStream = new TarArchiveInputStream(bzIn);
        } catch (IOException e) {
            System.out.println("There was an error in opening the file at - " + filePath);
        }
    }

    /**
     * This iterates over the archived files in the archive and writes to a new location
     * We ASSUME the archive has only one file
     * @return Location containing decompressed file contents
     */
    @Override
    public String decompress() {
        if(!shouldDecompress())
            return null;

        String outputFile = null;
        ArchiveEntry entry = null;
        try {
            while (null != (entry = ((TarArchiveInputStream) inputStream).getNextEntry())){
                if (entry.getSize() < 1)
                    continue;

                if(!entry.isDirectory()) {
                    int len;
                    byte[] buffer = new byte[1024];

                    outputFile = getOutputFile(filePath);

                    FileOutputStream out = new FileOutputStream(outputFile, false);
                    try (BufferedOutputStream dest = new BufferedOutputStream(out)) {
                        while ((len = inputStream.read(buffer)) > 0) {
                            dest.write(buffer, 0, len);
                        }
                    }
                    out.close();
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputFile;
    }
}
