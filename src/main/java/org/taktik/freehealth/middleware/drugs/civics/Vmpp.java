package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class Vmpp {

	Vmp vmp;

	String treatmentDurationCatCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /*TreatmentDurationCategory getTreatmentDurationCategory() {;
         return TreatmentDurationCategory.findByTreatmentDurationCatCv(treatmentDurationCatCv);
     };*/

    public Vmp getVmp() {
        return vmp;
    }

    public void setVmp(Vmp vmp) {
        this.vmp = vmp;
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

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

