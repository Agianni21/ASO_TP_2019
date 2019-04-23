package fileanalyzer;

import java.io.File;

public class FileValidator {
    private File file;

    public FileValidator(File file) {
        this.file = file;
    }

    public boolean isValidFile() {
        boolean fileExists = file.exists();
        boolean isPE = isPortableExecutableFile();
        return fileExists && isPE;
    }

    private boolean isPortableExecutableFile() {
        String fileName = file.getName();
        boolean fileIsPE = fileName.matches(".*exe$");
        return fileIsPE;
    }
}
