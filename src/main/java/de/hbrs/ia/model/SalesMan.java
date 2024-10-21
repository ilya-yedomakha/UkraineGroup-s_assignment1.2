package de.hbrs.ia.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalesMan{
    private String firstname;
    private String lastname;
    private Integer sid;
    private List<Integer> performanceRecordIds = new ArrayList<>();

    public SalesMan() {
    }

    public SalesMan(String firstname, String lastname, Integer sid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public List<Integer> getPerformanceRecords() {
        return performanceRecordIds;
    }

    public void setPerformanceRecords(List<Integer> performanceRecords) {
        this.performanceRecordIds = performanceRecords;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("firstname" , this.firstname );
        document.append("lastname" , this.lastname );
        document.append("sid" , this.sid);
        document.append("performanceRecords", this.performanceRecordIds);
        return document;
    }

    public static SalesMan toSalesMan(Document document) {
        SalesMan salesMan = new SalesMan();
        salesMan.setFirstname((String) document.get("firstname"));
        salesMan.setLastname((String) document.get("lastname"));
        salesMan.setSid((Integer) document.get("sid"));
        salesMan.setPerformanceRecords(document.getList("performanceRecords", Integer.class));
        return salesMan;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == SalesMan.class) {
            SalesMan salesMan = (SalesMan) obj;
            return Objects.equals(salesMan.firstname, this.firstname)
                    && Objects.equals(salesMan.lastname, this.lastname)
                    && Objects.equals(salesMan.sid, this.sid);
        }
        return false;
    }
}
