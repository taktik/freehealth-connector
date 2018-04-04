package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class ActualIngredientStrength {

	Substance cas;
	Amp amp;

	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String excInd;
	Double strengthQuantity;
	Long virtualIngredientCasId;
	Double strengthQuantity_2;
	String strengthUnit;
	Double strengthDenomQuantity;
	String strengthDenomUnit;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Substance getCas() {
        return cas;
    }

    public void setCas(Substance cas) {
        this.cas = cas;
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

    public Double getStrengthQuantity() {
        return strengthQuantity;
    }

    public void setStrengthQuantity(Double strengthQuantity) {
        this.strengthQuantity = strengthQuantity;
    }

    public Long getVirtualIngredientCasId() {
        return virtualIngredientCasId;
    }

    public void setVirtualIngredientCasId(Long virtualIngredientCasId) {
        this.virtualIngredientCasId = virtualIngredientCasId;
    }

    public Double getStrengthQuantity_2() {
        return strengthQuantity_2;
    }

    public void setStrengthQuantity_2(Double strengthQuantity_2) {
        this.strengthQuantity_2 = strengthQuantity_2;
    }

    public String getStrengthUnit() {
        return strengthUnit;
    }

    public void setStrengthUnit(String strengthUnit) {
        this.strengthUnit = strengthUnit;
    }

    public Double getStrengthDenomQuantity() {
        return strengthDenomQuantity;
    }

    public void setStrengthDenomQuantity(Double strengthDenomQuantity) {
        this.strengthDenomQuantity = strengthDenomQuantity;
    }

    public String getStrengthDenomUnit() {
        return strengthDenomUnit;
    }

    public void setStrengthDenomUnit(String strengthDenomUnit) {
        this.strengthDenomUnit = strengthDenomUnit;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

