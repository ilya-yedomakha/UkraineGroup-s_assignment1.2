package de.hbrs.ia.code.Data;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DbConnection {
    private static final String CONNECTION_URL ="localhost";
    private static final Integer CONNECTION_PORT = 27017;
    private static final String DATABASE_NAME = "SmartHooverHR";

    private static MongoClient client;


    private static void createMongoClient(){
        if(client == null){
                client = new MongoClient(CONNECTION_URL,CONNECTION_PORT);
        }
    }

    private static MongoDatabase getDatabase(){
        if(client ==null){
            createMongoClient();
        }
        return client.getDatabase(DATABASE_NAME);
    }

    public static MongoCollection<Document> getCollection(String colName){
        return getDatabase().getCollection(colName);
    }

    public static void closeSession() {
        if (client != null) {
            client.close();
            client = null;
        }
    }
}
