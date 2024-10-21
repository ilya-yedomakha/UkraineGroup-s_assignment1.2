package de.hbrs.ia.code.Personal;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import de.hbrs.ia.code.Data.DbConnection;
import de.hbrs.ia.model.SalesMan;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

public class ManagePersonalImpl implements ManagePersonal {
    private static final String COLLECTION_SALESMEN_NAME = "salesmen";

    private MongoCollection<Document> salesmenCollection;

    @Override
    public boolean createSalesMan(SalesMan record) {
        salesmenCollection = DbConnection.getCollection(COLLECTION_SALESMEN_NAME);
        InsertOneResult result = salesmenCollection.insertOne(record.toDocument());
        DbConnection.closeSession();
        return  result.wasAcknowledged();
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        salesmenCollection = DbConnection.getCollection(COLLECTION_SALESMEN_NAME);
        Document document = salesmenCollection.find(eq("sid", sid)).first();
        DbConnection.closeSession();
        if (document == null)
            return null;
        else
            return SalesMan.toSalesMan(document);
    }

    @Override
    public List<SalesMan> readAllSalesMen() {
        ArrayList<SalesMan> list = new ArrayList<>();
        salesmenCollection = DbConnection.getCollection(COLLECTION_SALESMEN_NAME);
        FindIterable<Document> documentFindIterable = salesmenCollection.find();
        try (MongoCursor<Document> cursor = documentFindIterable.iterator()) {
            while (cursor.hasNext()) {
                list.add(SalesMan.toSalesMan(cursor.next()));
            }
        }
        DbConnection.closeSession();
        return list;
    }

    @Override
    public boolean addSocialPerformanceRecord(Integer recordId, Integer salesManId) {
        salesmenCollection = DbConnection.getCollection(COLLECTION_SALESMEN_NAME);
        UpdateResult res = salesmenCollection.updateOne(eq("sid", salesManId), Updates.push("performanceRecords", recordId));
        res.wasAcknowledged();
        DbConnection.closeSession();
        return res.wasAcknowledged();
    }

    @Override
    public boolean removeSocialPerformanceRecord(Integer recordId, Integer salesManId) {
        salesmenCollection = DbConnection.getCollection(COLLECTION_SALESMEN_NAME);
        UpdateResult result = salesmenCollection.updateOne(eq("sid", salesManId), new Document("$pull", in("performanceRecords", recordId)));
        DbConnection.closeSession();
        return result.wasAcknowledged();
    }


    @Override
    public boolean deleteSalesMan(int sid) {
        salesmenCollection = DbConnection.getCollection(COLLECTION_SALESMEN_NAME);
        DeleteResult result = salesmenCollection.deleteOne(eq("sid", sid));
        DbConnection.closeSession();
        return result.wasAcknowledged();
    }
}
