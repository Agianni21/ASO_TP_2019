package fileanalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class FileHash {

    private File file;
    private String hexStringHash;
    private byte[] hash;

    private FileHash(File file) {
        this.file = file;
    }

    /**
     * Creates and returns FileHash object (SHA-256)
     * @param file
     * @return (returns null if something goes wrong)
     */
    static public FileHash createFileHash(File file) {
        FileHash fileHash = new FileHash(file);
        fileHash.digestFile();
        if (fileHash.hash == null) {
            return null;
        }
        return fileHash;
    }

    public String getHashString() {
        digestFile();
        return hexStringHash;
    }


    private void digestFile() {
        // returns hash as HEX string
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            InputStream fileStream = new FileInputStream(file);
            DigestInputStream digestStream = new DigestInputStream(fileStream, md);
            byte[] buffer = new byte[4096];
            while(digestStream.read() > 0) {
            }

            BigInteger bg = new BigInteger(1, digestStream.getMessageDigest().digest());

            hash = bg.toByteArray();

            hexStringHash = bg.toString(16);

        } catch (Exception e) {
            System.out.println("Something went wrong digesting File: " + file.getName());
        }
    }
}
