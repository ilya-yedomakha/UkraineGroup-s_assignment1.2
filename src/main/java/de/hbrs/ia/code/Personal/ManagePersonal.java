package de.hbrs.ia.code.Personal;
import de.hbrs.ia.model.SalesMan;

import java.util.List;

/**
 * Code lines are commented for suppressing compile errors.
 * Are there any CRUD-operations missing?
 */
public interface ManagePersonal {
    boolean createSalesMan(SalesMan record);

    SalesMan readSalesMan(int sid);

    List<SalesMan> readAllSalesMen();

    boolean addSocialPerformanceRecord(Integer recordId , Integer salesManId);

    boolean removeSocialPerformanceRecord(Integer recordId , Integer salesManId);

    boolean deleteSalesMan(int sid);
}
