package fileanalyzer.fileanalyzer;

public class FileReport {
    private final String rawJson;
    private final boolean success;

    public FileReport(String json, boolean success) {
        this.rawJson = json;
        this.success = success;
    }

    public String getRawJson() {
        return rawJson;
    }

    public boolean wasAnalysisSuccesful() {
        return success;
    }
}
