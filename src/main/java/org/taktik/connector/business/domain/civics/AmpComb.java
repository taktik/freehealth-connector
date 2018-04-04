package org.taktik.connector.business.domain.civics;

import java.util.Date;

import org.taktik.connector.business.domain.civics.*;

public class AmpComb {

	org.taktik.connector.business.domain.civics.Amp ampCmb;
	org.taktik.connector.business.domain.civics.Amp amp;

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

    public org.taktik.connector.business.domain.civics.Amp getAmpCmb() {
        return ampCmb;
    }

    public void setAmpCmb(org.taktik.connector.business.domain.civics.Amp ampCmb) {
        this.ampCmb = ampCmb;
    }

    public org.taktik.connector.business.domain.civics.Amp getAmp() {
        return amp;
    }

    public void setAmp(org.taktik.connector.business.domain.civics.Amp amp) {
        this.amp = amp;
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

