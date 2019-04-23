import static org.junit.jupiter.api.Assertions.assertEquals;

import fileanalyzer.TerminalController;
import org.junit.jupiter.api.Test;

public class TerminalControllerTests {

    @Test
    void isArgsValidWithCorrectArgs() {
        String[] args = {"oneArgument"};
        TerminalController tc = new TerminalController(args);
        assertEquals(true, tc.isArgsValid());
    }
}
