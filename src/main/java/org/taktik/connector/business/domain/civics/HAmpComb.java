package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class HAmpComb {

	Long ampId;
	Long ampIdCmb;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	String ampCmbSeq;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmpId() {
        return ampId;
    }

    public void setAmpId(Long ampId) {
        this.ampId = ampId;
    }

    public Long getAmpIdCmb() {
        return ampIdCmb;
    }

    public void setAmpIdCmb(Long ampIdCmb) {
        this.ampIdCmb = ampIdCmb;
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

    public String getAmpCmbSeq() {
        return ampCmbSeq;
    }

    public void setAmpCmbSeq(String ampCmbSeq) {
        this.ampCmbSeq = ampCmbSeq;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

