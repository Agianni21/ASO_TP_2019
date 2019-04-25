package fileanalyzer.fileanalyzer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        /*
        Tenga miedo quien quiera arreglar esta monstruosidad
         */
        FileReport report = new FileReport("", false);
        try {
            Response response = api.scanFileRequest(file);
            String fileId = "";
            if (response.isSuccessful()) {
                // Este if puede ser una funcion
                String rawResponseBody = response.body().string();
                JsonObject json = new JsonParser().parse(rawResponseBody).getAsJsonObject();
                fileId = json.get("scan_id").getAsString();
            } else {
                System.out.println("Fallo request");
                System.out.println(response.code());
                System.out.println(response.body());
            }

            JsonObject reportResponseJson = new JsonObject();
            do {
                try {
                    Thread.sleep(15000);
                }
                catch (InterruptedException e) {
                    System.out.println("Sleep was interrupted");
                }
                Response reportResponse = api.fileReportRequest(fileId);
                if (reportResponse.isSuccessful()) {
                    String rawResponseBody = reportResponse.body().string(); // Codigo duplicado *cara de pikachu :o*
                    System.out.println(rawResponseBody);
                    reportResponseJson = new JsonParser().parse(rawResponseBody).getAsJsonObject();
                }
                else {
                    System.out.println("Fallo request");
                }
            }
           while (!isFileAnalysisFinished(reportResponseJson));

            report = new FileReport(reportResponseJson.toString(), true);


        } catch (IOException e) {
            System.out.println("An error ocurred during HTTP request execution");
        }

        return report;
    }

    private boolean isFileAnalysisFinished(JsonObject reportResponse) {
        // response_code == 1 cuando reporte esta finalizado
        // == -2 cuando todavia no finalizo
        return reportResponse.get("response_code").getAsInt() == 1;
    }

}
