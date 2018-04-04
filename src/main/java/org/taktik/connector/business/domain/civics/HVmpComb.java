package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class HVmpComb {

	Long vmpIdCmb;
	Long vmpId;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	String vmpCmbSeq;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVmpIdCmb() {
        return vmpIdCmb;
    }

    public void setVmpIdCmb(Long vmpIdCmb) {
        this.vmpIdCmb = vmpIdCmb;
    }

    public Long getVmpId() {
        return vmpId;
    }

    public void setVmpId(Long vmpId) {
        this.vmpId = vmpId;
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

    public String getVmpCmbSeq() {
        return vmpCmbSeq;
    }

    public void setVmpCmbSeq(String vmpCmbSeq) {
        this.vmpCmbSeq = vmpCmbSeq;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

