package de.hbrs.ia.code.Social;

import de.hbrs.ia.code.Data.DbConnection;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ManageSocialTest {

    private ManageSocial manageSocial;

    @BeforeEach
    void setUp() {
        manageSocial = new ManageSocialImpl();
    }

    @AfterEach
    void tearDown() {
        DbConnection.getCollection("social_performance_records").drop();
        DbConnection.closeSession();
    }

    @Test
    void createSocialPerformanceRecord() {
        // ARRANGE
        SocialPerformanceRecord record = new SocialPerformanceRecord(1,"Communication Skills",5,4,2024);

        // ACT
        boolean result = manageSocial.createSocialPerformanceRecord(record);

        // ASSERT
        assertTrue(result);
    }



    @Test
    void readByIdSocialPerformanceRecord() {
        // ARRANGE
        SocialPerformanceRecord record = new SocialPerformanceRecord(2,"Communication Skills",5,4,2024);
        manageSocial.createSocialPerformanceRecord(record);

        // ACT
        SocialPerformanceRecord result = manageSocial.readByIdSocialPerformanceRecord(2);

        // ASSERT
        assertEquals(record, result);
    }

    @Test
    void readAllSocialPerformanceRecords() {
        // ARRANGE
        SocialPerformanceRecord record = new SocialPerformanceRecord(2,"Communication Skills",
                5,4,2024);
        SocialPerformanceRecord record1 = new SocialPerformanceRecord(2,"Communication Skills",
                5,4,2024);
        SocialPerformanceRecord record2 = new SocialPerformanceRecord(2,"Communication Skills",
                5,4,2024);
        List<SocialPerformanceRecord> recordArray = List.of(record,record1,record2);
        manageSocial.createSocialPerformanceRecord(record);
        manageSocial.createSocialPerformanceRecord(record1);
        manageSocial.createSocialPerformanceRecord(record2);

        // ACT
        List<SocialPerformanceRecord> resultArray = manageSocial.readAllSocialPerformanceRecords();

        // ASSERT
        assertEquals(3, resultArray.size());
    }

    @Test
    void deleteSocialPerformanceRecord() {
        // ARRANGE
        SocialPerformanceRecord record = new SocialPerformanceRecord(2,"Communication Skills",5,4,2024);
        manageSocial.createSocialPerformanceRecord(record);

        // ACT
        boolean result = manageSocial.deleteSocialPerformanceRecord(2);

        // ASSERT
        assertTrue(result);
    }

    @Test
    void updateSocialPerformanceRecord() {
        // ARRANGE
        SocialPerformanceRecord record = new SocialPerformanceRecord(1,"Communication Skills",5,4,2024);
        manageSocial.createSocialPerformanceRecord(record);
        SocialPerformanceRecord updateRecord = new SocialPerformanceRecord(1,"Communication Skills(UPD)",6,3,2023);

        // ACT
        boolean result = manageSocial.updateSocialPerformanceRecord(updateRecord);


        // ASSERT
        assertEquals("Communication Skills(UPD)",manageSocial.readByIdSocialPerformanceRecord(1).getGoalDescription());
        assertEquals(6,manageSocial.readByIdSocialPerformanceRecord(1).getTargetValue());
        assertEquals(2023,manageSocial.readByIdSocialPerformanceRecord(1).getYear().getValue());
        assertEquals(3,manageSocial.readByIdSocialPerformanceRecord(1).getActualValue());
        assertEquals(3,manageSocial.readByIdSocialPerformanceRecord(1).getActualValue());
        assertTrue(result);
    }


}