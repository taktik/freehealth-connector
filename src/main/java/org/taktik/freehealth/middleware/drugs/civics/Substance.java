package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class Substance {

	Long casNr;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String excInd;
	String activeBaseInd;
	Long baseFormCasId;
	String derivFormType;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCasNr() {
        return casNr;
    }

    public void setCasNr(Long casNr) {
        this.casNr = casNr;
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

    public String getExcInd() {
        return excInd;
    }

    public void setExcInd(String excInd) {
        this.excInd = excInd;
    }

    public String getActiveBaseInd() {
        return activeBaseInd;
    }

    public void setActiveBaseInd(String activeBaseInd) {
        this.activeBaseInd = activeBaseInd;
    }

    public Long getBaseFormCasId() {
        return baseFormCasId;
    }

    public void setBaseFormCasId(Long baseFormCasId) {
        this.baseFormCasId = baseFormCasId;
    }

    public String getDerivFormType() {
        return derivFormType;
    }

    public void setDerivFormType(String derivFormType) {
        this.derivFormType = derivFormType;
    }
}

