package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class HPrice {

	Long amppId;
	String deliveryEnvironment;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Double priceAmnt;
	Double reimbBasePrice;
	Double referenceBasePrice;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPriceAmnt() {
        return priceAmnt;
    }

    public void setPriceAmnt(Double priceAmnt) {
        this.priceAmnt = priceAmnt;
    }

    public Double getReimbBasePrice() {
        return reimbBasePrice;
    }

    public void setReimbBasePrice(Double reimbBasePrice) {
        this.reimbBasePrice = reimbBasePrice;
    }

    public Double getReferenceBasePrice() {
        return referenceBasePrice;
    }

    public void setReferenceBasePrice(Double referenceBasePrice) {
        this.referenceBasePrice = referenceBasePrice;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

