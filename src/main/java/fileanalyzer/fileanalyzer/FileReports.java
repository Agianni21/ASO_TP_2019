package fileanalyzer.fileanalyzer;

import fileanalyzer.FileHash;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.*;
import org.bson.Document;

public class FileReports {

    private MongoCollection<Document> collection;
    private String lastFileHash;
    private FileReport lastFileReport;

    public FileReports() {
        setMongoCollection();
    }

    public FileReport getFileReport(String fileHash) {
        return lastFileReport;
    }

    public void addFileReport(FileReport fileReport) {
        Document document = new Document("hash", fileReport.getFileHash())
                .append("report", fileReport.getRawJson());
        collection.insertOne(document);
    }

    public boolean fileReportExists(String fileHash) {
        Document fileReport = collection.find(eq("hash", fileHash)).first();
        if (fileReport != null) {
            lastFileHash = fileHash;
            lastFileReport = new FileReport(fileReport.get("report").toString() ,true ,lastFileHash);
            return true;
        }
        return false;
    }

    private void setMongoCollection() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("fileanalyzer");
        collection = database.getCollection("fileReports");
    }
}
