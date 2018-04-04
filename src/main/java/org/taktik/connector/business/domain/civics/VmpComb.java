package org.taktik.connector.business.domain.civics;

import java.util.Date;

import org.taktik.connector.business.domain.civics.*;

public class VmpComb {

	org.taktik.connector.business.domain.civics.Vmp vmp;
	org.taktik.connector.business.domain.civics.Vmp vmpCmb;

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

    public org.taktik.connector.business.domain.civics.Vmp getVmp() {
        return vmp;
    }

    public void setVmp(org.taktik.connector.business.domain.civics.Vmp vmp) {
        this.vmp = vmp;
    }

    public org.taktik.connector.business.domain.civics.Vmp getVmpCmb() {
        return vmpCmb;
    }

    public void setVmpCmb(org.taktik.connector.business.domain.civics.Vmp vmpCmb) {
        this.vmpCmb = vmpCmb;
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

