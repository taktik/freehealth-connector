package org.taktik.connector.business.domain.civics;

import java.util.Date;

import org.taktik.connector.business.domain.civics.*;
import org.taktik.connector.business.domain.civics.Substance;
import org.taktik.connector.business.domain.civics.Vtm;

public class VirtualIngredientStrength {

	org.taktik.connector.business.domain.civics.Vmp vmp;
	org.taktik.connector.business.domain.civics.Vtm vtm;
	org.taktik.connector.business.domain.civics.Substance cas;

	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Double strengthQuantity;
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

    public org.taktik.connector.business.domain.civics.Vmp getVmp() {
        return vmp;
    }

    public void setVmp(org.taktik.connector.business.domain.civics.Vmp vmp) {
        this.vmp = vmp;
    }

    public org.taktik.connector.business.domain.civics.Vtm getVtm() {
        return vtm;
    }

    public void setVtm(Vtm vtm) {
        this.vtm = vtm;
    }

    public org.taktik.connector.business.domain.civics.Substance getCas() {
        return cas;
    }

    public void setCas(Substance cas) {
        this.cas = cas;
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

    public Double getStrengthQuantity() {
        return strengthQuantity;
    }

    public void setStrengthQuantity(Double strengthQuantity) {
        this.strengthQuantity = strengthQuantity;
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

