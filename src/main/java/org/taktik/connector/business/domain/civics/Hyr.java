package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class Hyr {

	String hyrCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String finalLevelInd;
	Long hyrIdParent;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHyrCv() {
        return hyrCv;
    }

    public void setHyrCv(String hyrCv) {
        this.hyrCv = hyrCv;
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

    public String getFinalLevelInd() {
        return finalLevelInd;
    }

    public void setFinalLevelInd(String finalLevelInd) {
        this.finalLevelInd = finalLevelInd;
    }

    public Long getHyrIdParent() {
        return hyrIdParent;
    }

    public void setHyrIdParent(Long hyrIdParent) {
        this.hyrIdParent = hyrIdParent;
    }
}

