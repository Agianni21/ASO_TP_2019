package fileanalyzer.fileanalyzer;
/*
Esta clase deberia recibir el json del reporte de virus total
y dejarlo en formatito mas pasable para mostrarlo por consola
*/
public class FileReport {
    private final String rawJson;
    private final boolean success;
    private final String fileHash;

    public FileReport(String json, boolean success, String fileHash) {
        this.rawJson = json;
        this.success = success;
        this.fileHash = fileHash;
    }

    public String getFileHash() { return fileHash; }

    public String getRawJson() {
        return rawJson;
    }

    public boolean wasAnalysisSuccesful() {
        return success;
    }
}
