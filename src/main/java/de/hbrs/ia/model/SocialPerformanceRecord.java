package de.hbrs.ia.model;

import org.bson.Document;

import java.time.Year;
import java.util.Objects;

public class SocialPerformanceRecord {
    private Integer goalId;
    private String goalDescription;
    private Integer targetValue;
    private Integer actualValue;
    private Year year = Year.now();

    public SocialPerformanceRecord() {
    }

    public SocialPerformanceRecord(Integer goalId, String goalDescription, Integer targetValue, Integer actualValue, Integer year) {
        this.goalId = goalId;
        this.goalDescription = goalDescription;
        this.targetValue = targetValue;
        this.actualValue = actualValue;
        this.year = Year.of(year);
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public Integer getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Integer targetValue) {
        this.targetValue = targetValue;
    }

    public Integer getActualValue() {
        return actualValue;
    }

    public void setActualValue(Integer actualValue) {
        this.actualValue = actualValue;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = Year.of(year);
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("goalId" , this.goalId);
        document.append("goalDescription" , this.goalDescription);
        document.append("actualValue",this.actualValue);
        document.append("targetValue",this.targetValue);
        document.append("year",this.year.getValue());
        return document;
    }

    public static SocialPerformanceRecord toSocialPerformanceRecord(Document document) {

        if(document == null){
            return null;
        }
        SocialPerformanceRecord socialPerformanceRecord = new SocialPerformanceRecord();
        socialPerformanceRecord.setGoalId((Integer) document.get("goalId"));
        socialPerformanceRecord.setGoalDescription((String) document.get("goalDescription"));
        socialPerformanceRecord.setActualValue((Integer) document.get("actualValue"));
        socialPerformanceRecord.setTargetValue((Integer) document.get("targetValue"));
        socialPerformanceRecord.setYear((Integer) document.get("year"));
        return socialPerformanceRecord;
    }


    @Override
    public boolean equals(Object obj) {
        SocialPerformanceRecord record = (SocialPerformanceRecord) obj;
        return Objects.equals(this.goalId, record.goalId)
                && Objects.equals(this.goalDescription, record.goalDescription)
                && Objects.equals(this.actualValue,record.actualValue)
                && Objects.equals(this.targetValue, record.targetValue)
                && Objects.equals(this.year.getValue(), record.year.getValue());
    }

}
