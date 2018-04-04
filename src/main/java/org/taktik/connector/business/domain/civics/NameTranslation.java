package org.taktik.connector.business.domain.civics;

import java.util.Date;

import org.taktik.connector.business.domain.civics.*;

public class NameTranslation {

	org.taktik.connector.business.domain.civics.NameExplanation name;

	String nameTypeCv;
	String languageCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	String shortText;
	String longText;
	byte[] longBinaryText;
	String addressUrl;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*NameType getNameType() {;
         NameType.findByNameTypeCv(nameTypeCv);
     };*/

    public org.taktik.connector.business.domain.civics.NameExplanation getName() {
        return name;
    }

    public void setName(org.taktik.connector.business.domain.civics.NameExplanation name) {
        this.name = name;
    }

    public String getNameTypeCv() {
        return nameTypeCv;
    }

    public void setNameTypeCv(String nameTypeCv) {
        this.nameTypeCv = nameTypeCv;
    }

    public String getLanguageCv() {
        return languageCv;
    }

    public void setLanguageCv(String languageCv) {
        this.languageCv = languageCv;
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

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public byte[] getLongBinaryText() {
        return longBinaryText;
    }

    public void setLongBinaryText(byte[] longBinaryText) {
        this.longBinaryText = longBinaryText;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

