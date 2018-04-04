package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class Atc {

	String atcCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String atcCvParent;
	String finalLevelInd;
	String flatRateInd;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Atc getParentAtc() {;
         return findByAtcCvParent(atcCvParent);
     };*/

    public String getAtcCv() {
        return atcCv;
    }

    public void setAtcCv(String atcCv) {
        this.atcCv = atcCv;
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

    public String getAtcCvParent() {
        return atcCvParent;
    }

    public void setAtcCvParent(String atcCvParent) {
        this.atcCvParent = atcCvParent;
    }

    public String getFinalLevelInd() {
        return finalLevelInd;
    }

    public void setFinalLevelInd(String finalLevelInd) {
        this.finalLevelInd = finalLevelInd;
    }

    public String getFlatRateInd() {
        return flatRateInd;
    }

    public void setFlatRateInd(String flatRateInd) {
        this.flatRateInd = flatRateInd;
    }
}

