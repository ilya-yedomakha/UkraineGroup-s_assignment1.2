package de.hbrs.ia.code.Social;

import de.hbrs.ia.model.SocialPerformanceRecord;

import java.util.List;

public interface ManageSocial {

    boolean createSocialPerformanceRecord(SocialPerformanceRecord record);

    SocialPerformanceRecord readByIdSocialPerformanceRecord( Integer goalId );

    List<SocialPerformanceRecord> readAllSocialPerformanceRecords();

    boolean deleteSocialPerformanceRecord(Integer goalId);

    boolean updateSocialPerformanceRecord(SocialPerformanceRecord socialPerformanceRecord);

}
