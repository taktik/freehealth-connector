package org.taktik.connector.business.domain.civics;

import java.util.Date;

import org.taktik.connector.business.domain.civics.*;

public class AmpIntermediatePackage {

	org.taktik.connector.business.domain.civics.Amp amp;

	Double contentQuantity;
	String contentUnit;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long addedMultiplier;
	Double addedQuantity;
	String addedUnit;
	String addedType;
	String innerPackageCv;
	String packageTxt;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public org.taktik.connector.business.domain.civics.Amp getAmp() {
        return amp;
    }

    public void setAmp(org.taktik.connector.business.domain.civics.Amp amp) {
        this.amp = amp;
    }

    public Double getContentQuantity() {
        return contentQuantity;
    }

    public void setContentQuantity(Double contentQuantity) {
        this.contentQuantity = contentQuantity;
    }

    public String getContentUnit() {
        return contentUnit;
    }

    public void setContentUnit(String contentUnit) {
        this.contentUnit = contentUnit;
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

    public Long getAddedMultiplier() {
        return addedMultiplier;
    }

    public void setAddedMultiplier(Long addedMultiplier) {
        this.addedMultiplier = addedMultiplier;
    }

    public Double getAddedQuantity() {
        return addedQuantity;
    }

    public void setAddedQuantity(Double addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public String getAddedUnit() {
        return addedUnit;
    }

    public void setAddedUnit(String addedUnit) {
        this.addedUnit = addedUnit;
    }

    public String getAddedType() {
        return addedType;
    }

    public void setAddedType(String addedType) {
        this.addedType = addedType;
    }

    public String getInnerPackageCv() {
        return innerPackageCv;
    }

    public void setInnerPackageCv(String innerPackageCv) {
        this.innerPackageCv = innerPackageCv;
    }

    public String getPackageTxt() {
        return packageTxt;
    }

    public void setPackageTxt(String packageTxt) {
        this.packageTxt = packageTxt;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

