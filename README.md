# FileReader

### What does this do?
This is an exercise in software design, which reads a file and applies decompression, decryption and decoding.  


### How to run this project
In a Java IDE of choice, import this project, navigate to `FileProcessor.java` in `java` > `com.sanjana`. The main method in the class needs to be executed.
Command line arguments can be provided via the run configuration dialog box or via CLI.

### Usage
| Operation     | Supported values      |       
| ------------- |---------------------- |
| Compression   | `gzip`, `bzip`        |
| Encryption    | `aes128`              |
| Encoding      | `utf-8`, `iso-8859-1` |

Examples - 
1. `java FileProcessor file=TestData/GzipEncrypted.txt.gz compression=gzip encryption=aes128 secret=tellmeyoursecret charset=utf-8`  
This attempts to read a file at `TestData/GzipEncrypted.txt.gz`, decompresses it from `gzip`, decrypts the file using the AES128 algorithm and the private key `tellmeyoursecret`, and finally decodes the text file in `UTF-8`
2. `java FileProcessor file=TestData/EncryptedText.txt encryption=aes128 secret=tellmeyoursecret charset=utf-8`  
This attempts to read a file at `TestData/EncryptedText.txt`, decrypts the file using the AES128 algorithm and the private key `tellmeyoursecret`, and finally decodes the text file in `UTF-8`
3. `java FileProcessor file=TestData/PlainText.txt`  
This opens the file at `TestData/PlainText.txt` and returns is as no operations take place
4. `java FileProcessor file=TestData/BzipUnencrypted.tar.bz2 compression=bzip`  
This opens the file at `TestData/BzipUnencrypted.tar.bz2` and decompresses it from `bzip` and writes it to a new location  
  
Processed files will be written to the *root directory of the project* with the name starting with the operation last performed on the file.  
For example, if the file is being decompressed and decoded, the final output will reside in a file named `Decoding_2019-01-02.txt`  
Example files can be found in the `TestData` folder
