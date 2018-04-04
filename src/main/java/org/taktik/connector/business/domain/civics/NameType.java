package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class NameType {

	String nameTypeCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String nameType;
	Long nameTypeSeq;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTypeCv() {
        return nameTypeCv;
    }

    public void setNameTypeCv(String nameTypeCv) {
        this.nameTypeCv = nameTypeCv;
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

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Long getNameTypeSeq() {
        return nameTypeSeq;
    }

    public void setNameTypeSeq(Long nameTypeSeq) {
        this.nameTypeSeq = nameTypeSeq;
    }
}

