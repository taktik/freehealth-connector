package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class HCopayment {

	String chapterName;
	String paragraphName;
	Long atmId;
	Long amppId;
	String deliveryEnvironment;
	Long regimeType;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Double copayAmnt;
	Double solidFlatRateAmnt;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getParagraphName() {
        return paragraphName;
    }

    public void setParagraphName(String paragraphName) {
        this.paragraphName = paragraphName;
    }

    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
    }

    public Long getAmppId() {
        return amppId;
    }

    public void setAmppId(Long amppId) {
        this.amppId = amppId;
    }

    public String getDeliveryEnvironment() {
        return deliveryEnvironment;
    }

    public void setDeliveryEnvironment(String deliveryEnvironment) {
        this.deliveryEnvironment = deliveryEnvironment;
    }

    public Long getRegimeType() {
        return regimeType;
    }

    public void setRegimeType(Long regimeType) {
        this.regimeType = regimeType;
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

    public Double getCopayAmnt() {
        return copayAmnt;
    }

    public void setCopayAmnt(Double copayAmnt) {
        this.copayAmnt = copayAmnt;
    }

    public Double getSolidFlatRateAmnt() {
        return solidFlatRateAmnt;
    }

    public void setSolidFlatRateAmnt(Double solidFlatRateAmnt) {
        this.solidFlatRateAmnt = solidFlatRateAmnt;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

