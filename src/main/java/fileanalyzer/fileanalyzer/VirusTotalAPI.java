package fileanalyzer.fileanalyzer;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class VirusTotalAPI {

    private OkHttpClient client;
    private String apiKey;

    public VirusTotalAPI() {
        client = new OkHttpClient();
        setupApiKey();
    }

    public Response startFileAnalysis(File file) throws IOException{
        Request request = setupScanFileRequest(file);
        Response response = client.newCall(request).execute();
        return response;
    }

    private void setupApiKey() {
        apiKey = "";
    }

    private MediaType getFileMediatype(File file) {
        MediaType mediaType = MediaType.parse("");
        try {
            String rawMediaType = Files.probeContentType(file.toPath());
            mediaType = MediaType.parse(rawMediaType);

        } catch (Exception e) {
            // It should never throw this exception because file is validated previously
            System.out.println("ERROR: Get file media type failed (it doesn't exist?)");
            System.out.println(e);
            System.out.println(file);
        }
        return mediaType;
    }

    private Request setupScanFileRequest(File file) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("apikey", apiKey)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(getFileMediatype(file), file))
                .build();
        Request request = new Request.Builder()
                .url("https://www.virustotal.com/vtapi/v2/file/scan")
                .post(requestBody)
                .build();
        return request;
    }
}
