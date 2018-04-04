package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class TreatmentDurationCategory {

	String treatmentDurationCatCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	Long treatmentDurationValue;
	String treatmentDurationUnit;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreatmentDurationCatCv() {
        return treatmentDurationCatCv;
    }

    public void setTreatmentDurationCatCv(String treatmentDurationCatCv) {
        this.treatmentDurationCatCv = treatmentDurationCatCv;
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

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long nameId) {
        this.nameId = nameId;
    }

    public Long getTreatmentDurationValue() {
        return treatmentDurationValue;
    }

    public void setTreatmentDurationValue(Long treatmentDurationValue) {
        this.treatmentDurationValue = treatmentDurationValue;
    }

    public String getTreatmentDurationUnit() {
        return treatmentDurationUnit;
    }

    public void setTreatmentDurationUnit(String treatmentDurationUnit) {
        this.treatmentDurationUnit = treatmentDurationUnit;
    }
}

