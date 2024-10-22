package de.hbrs.ia.code.Personal;

import de.hbrs.ia.code.Data.DbConnection;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagePersonalTest {
    private static final String COLLECTION_SALESMEN_NAME = "salesmen";

    private ManagePersonal managePersonal;

    @BeforeEach
    void setUp() {
        managePersonal = new ManagePersonalImpl();
    }

    @AfterEach
    void tearDown() {
        DbConnection.getCollection(COLLECTION_SALESMEN_NAME).drop();
        DbConnection.closeSession();
    }

    @Test
    void createSalesMan() {
        // ARRANGE
        SalesMan salesMan = new SalesMan("Sascha","Alda",90133);
        List<SalesMan> salesman = managePersonal.readAllSalesMen();
        int salesmanCount = salesman.size();

        // ACT
        boolean result = managePersonal.createSalesMan(salesMan);

        // ASSERT
        assertTrue(result);
        salesman = managePersonal.readAllSalesMen();
        assertEquals( salesmanCount + 1, salesman.size());
    }

    @Test
    void readSalesMan() {
        // ARRANGE
        SalesMan salesMan = new SalesMan("Sascha","Alda",90133);
        managePersonal.createSalesMan(salesMan);

        // ACT
        SalesMan foundSalesMan = managePersonal.readSalesMan(90133);

        // ASSERT
        assertTrue(salesMan.equals(foundSalesMan));
    }

    @Test
    void readAllSalesMen() {
        // ARRANGE
        List<SalesMan> salesman = managePersonal.readAllSalesMen();
        int salesmanCount = salesman.size();
        managePersonal.createSalesMan(new SalesMan("Sascha","Alda",90131));
        managePersonal.createSalesMan(new SalesMan("Sascha","Alda",90132));
        managePersonal.createSalesMan(new SalesMan("Sascha","Alda",90133));

        // ACT
        salesman = managePersonal.readAllSalesMen();

        // ASSERT
        assertEquals(salesmanCount + 3, salesman.size());
    }

    @Test
    void addSocialPerformanceRecord() {
        // ARRANGE
        SalesMan salesMan = new SalesMan("Sascha","Alda",90133);
        SocialPerformanceRecord record = new SocialPerformanceRecord(1234, "test", 20, 30, 2018);
        managePersonal.createSalesMan(salesMan);

        // ACT
        managePersonal.addSocialPerformanceRecord(record.getGoalId(), salesMan.getSid());

        // ASSERT
        salesMan = managePersonal.readSalesMan(90133);
        assertEquals(1, salesMan.getPerformanceRecords().size());
    }

    @Test
    void removeSocialPerformanceRecord() {
        // ARRANGE
        SalesMan salesMan = new SalesMan("Sascha","Alda",90133);
        SocialPerformanceRecord record = new SocialPerformanceRecord(1234, "test", 20, 30, 2018);
        managePersonal.createSalesMan(salesMan);
        managePersonal.addSocialPerformanceRecord(record.getGoalId(), salesMan.getSid());

        // ACT
        managePersonal.removeSocialPerformanceRecord(record.getGoalId(), salesMan.getSid());

        // ASSERT
        salesMan = managePersonal.readSalesMan(90133);
        assertEquals(0, salesMan.getPerformanceRecords().size());
    }
    // test

    @Test
    void deleteSalesMan() {
        // ARRANGE
        SalesMan salesMan = new SalesMan("Sascha","Alda",90133);
        managePersonal.createSalesMan(salesMan);
        List<SalesMan> salesmanList = managePersonal.readAllSalesMen();
        int salesmanCount = salesmanList.size();

        // ACT
        boolean result = managePersonal.deleteSalesMan(90133);
        SalesMan foundSalesMan = managePersonal.readSalesMan(90133);
        salesmanList = managePersonal.readAllSalesMen();

        // ASSERT
        assertEquals( salesmanCount-1, salesmanList.size());
        assertNull(foundSalesMan);
    }
}