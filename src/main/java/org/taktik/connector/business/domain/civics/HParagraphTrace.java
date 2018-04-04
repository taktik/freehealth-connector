package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class HParagraphTrace {

	String chapterName;
	String paragraphName;
	String parentChapterName;
	String parentParagraphName;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
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

    public String getParentChapterName() {
        return parentChapterName;
    }

    public void setParentChapterName(String parentChapterName) {
        this.parentChapterName = parentChapterName;
    }

    public String getParentParagraphName() {
        return parentParagraphName;
    }

    public void setParentParagraphName(String parentParagraphName) {
        this.parentParagraphName = parentParagraphName;
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

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

