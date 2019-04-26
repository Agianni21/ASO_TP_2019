package fileanalyzer.fileanalyzer;
/*
Esta clase deberia recibir el json del reporte de virus total
y dejarlo en formatito mas pasable para mostrarlo por consola
*/
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
