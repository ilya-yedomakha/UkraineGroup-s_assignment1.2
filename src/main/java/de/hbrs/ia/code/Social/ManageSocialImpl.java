package de.hbrs.ia.code.Social;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import de.hbrs.ia.code.Data.DbConnection;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageSocialImpl implements ManageSocial{

    private static final String COLLECTION_SP_NAME = "social_performance_records";

    private MongoCollection<Document> socialPerformRecordCollection;

    @Override
    public boolean createSocialPerformanceRecord(SocialPerformanceRecord record) {
       socialPerformRecordCollection =  DbConnection.getCollection(COLLECTION_SP_NAME);
       InsertOneResult result = socialPerformRecordCollection.insertOne(record.toDocument());
       DbConnection.closeSession();
       return result.wasAcknowledged();
    }


    @Override
    public SocialPerformanceRecord readByIdSocialPerformanceRecord(Integer goalId) {
        socialPerformRecordCollection = DbConnection.getCollection(COLLECTION_SP_NAME);
        Document document = socialPerformRecordCollection.find(eq("goalId", goalId)).first();
        DbConnection.closeSession();
        return SocialPerformanceRecord.toSocialPerformanceRecord(document);
    }

    @Override
    public List<SocialPerformanceRecord> readAllSocialPerformanceRecords() {
        ArrayList<SocialPerformanceRecord> list = new ArrayList<>();
        socialPerformRecordCollection = DbConnection.getCollection(COLLECTION_SP_NAME);
        FindIterable<Document> documentFindIterable = socialPerformRecordCollection.find();
        try (MongoCursor<Document> cursor = documentFindIterable.iterator()) {
            while (cursor.hasNext()) {
                list.add(SocialPerformanceRecord.toSocialPerformanceRecord(cursor.next()));
            }
        }
        DbConnection.closeSession();
        return list;
    }

    @Override
    public boolean updateSocialPerformanceRecord(SocialPerformanceRecord socialPerformanceRecord) {
        socialPerformRecordCollection = DbConnection.getCollection(COLLECTION_SP_NAME);
        Bson bsonUpdate = Updates.combine(Updates.set("goalId",socialPerformanceRecord.getGoalId()),Updates.set("goalDescription",socialPerformanceRecord.getGoalDescription()),
                Updates.set("actualValue",socialPerformanceRecord.getActualValue()), Updates.set("targetValue",socialPerformanceRecord.getTargetValue()),
                Updates.set("year",socialPerformanceRecord.getYear().getValue()));
        UpdateResult result = socialPerformRecordCollection.updateOne(eq("goalId",socialPerformanceRecord.getGoalId()),bsonUpdate);
        return result.wasAcknowledged();
    }


    @Override
    public boolean deleteSocialPerformanceRecord(Integer goalId) {
        socialPerformRecordCollection =  DbConnection.getCollection(COLLECTION_SP_NAME);
        DeleteResult result = socialPerformRecordCollection.deleteOne(eq("goalId", goalId));
        DbConnection.closeSession();
        return result.wasAcknowledged();
    }
}
