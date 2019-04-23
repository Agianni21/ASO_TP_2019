package fileanalyzer.fileanalyzer;

import okhttp3.Response;

import java.io.File;
import java.io.IOException;

public class FileAnalyzer {

    private final VirusTotalAPI api;
    private final File file;

    public FileAnalyzer(File file) {
        this.file = file;
        this.api = new VirusTotalAPI();
    }

    public FileReport analyze() {
        FileReport report = new FileReport("", false);
        try {
            Response response = api.startFileAnalysis(file);
        } catch (IOException e) {
            System.out.println("An error ocurred during HTTP request execution");
        }
    }

}
