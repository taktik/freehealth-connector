package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class ProfessionalAuthorisation {

	String qualificationList;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	String purchasingAdvisorName;
	String professionalCv;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(String qualificationList) {
        this.qualificationList = qualificationList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreatedTms() {
        return createdTms;
    }

    public void setCreatedTms(Date createdTms) {
        this.createdTms = createdTms;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPurchasingAdvisorName() {
        return purchasingAdvisorName;
    }

    public void setPurchasingAdvisorName(String purchasingAdvisorName) {
        this.purchasingAdvisorName = purchasingAdvisorName;
    }

    public String getProfessionalCv() {
        return professionalCv;
    }

    public void setProfessionalCv(String professionalCv) {
        this.professionalCv = professionalCv;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

