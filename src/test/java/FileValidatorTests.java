import static org.junit.jupiter.api.Assertions.assertEquals;

import fileanalyzer.FileValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileValidatorTests {

    @Test
    void isValidFileWithValidFile() throws IOException {
        new File("testFile.exe").createNewFile();
        File validFile = new File("testFile.exe");
        FileValidator fv = new FileValidator(validFile);
        assertEquals(true, fv.isValidFile());
        validFile.delete();
    }

}
