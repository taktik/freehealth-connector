package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class PharmaceuticalForm {

	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String noInnInd;
	String dividableInd;
	String entericCoatedInd;
	String retardedInd;
	String solidToLiquid;
	String aerosolType;
	String tool;
	String vehicInd;
	String crushableInd;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNoInnInd() {
        return noInnInd;
    }

    public void setNoInnInd(String noInnInd) {
        this.noInnInd = noInnInd;
    }

    public String getDividableInd() {
        return dividableInd;
    }

    public void setDividableInd(String dividableInd) {
        this.dividableInd = dividableInd;
    }

    public String getEntericCoatedInd() {
        return entericCoatedInd;
    }

    public void setEntericCoatedInd(String entericCoatedInd) {
        this.entericCoatedInd = entericCoatedInd;
    }

    public String getRetardedInd() {
        return retardedInd;
    }

    public void setRetardedInd(String retardedInd) {
        this.retardedInd = retardedInd;
    }

    public String getSolidToLiquid() {
        return solidToLiquid;
    }

    public void setSolidToLiquid(String solidToLiquid) {
        this.solidToLiquid = solidToLiquid;
    }

    public String getAerosolType() {
        return aerosolType;
    }

    public void setAerosolType(String aerosolType) {
        this.aerosolType = aerosolType;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getVehicInd() {
        return vehicInd;
    }

    public void setVehicInd(String vehicInd) {
        this.vehicInd = vehicInd;
    }

    public String getCrushableInd() {
        return crushableInd;
    }

    public void setCrushableInd(String crushableInd) {
        this.crushableInd = crushableInd;
    }
}

