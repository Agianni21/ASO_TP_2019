package fileanalyzer;
import java.io.File;

/*
Controls application flow
 */
public class TerminalController {

    private String[] args;

    public TerminalController(String[] args) {
        this.args = args;
    }

    public void run() {
        if( ! this.isArgsValid() ) {
            System.out.println("Invalid arguments (none or too many)");
            return;
        }

        File file = new File(args[0]);
        FileValidator fv = new FileValidator(file);
        if ( ! fv.isValidFile() ) {
            System.out.println("File not valid (Not PE or doesn't exist)");
        }

    }

    public boolean isArgsValid() {
        boolean hasEnoughArguments = args.length == 1;
        return hasEnoughArguments;
    }
}
