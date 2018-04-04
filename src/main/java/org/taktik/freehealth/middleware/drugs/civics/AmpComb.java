package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class AmpComb {

	Amp ampCmb;
	Amp amp;

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

    public Amp getAmpCmb() {
        return ampCmb;
    }

    public void setAmpCmb(Amp ampCmb) {
        this.ampCmb = ampCmb;
    }

    public Amp getAmp() {
        return amp;
    }

    public void setAmp(Amp amp) {
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

