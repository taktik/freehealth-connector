package org.taktik.freehealth.middleware.drugs.civics;

import java.util.Date;

public class Therapy {

	Atm atm;

	String chapterName;
	String paragraphName;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	String maskedInd;
	String reimbCriteriaCv;
	String reimbCategoryCv;
	String modificationStatus;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public ReimbursementCategory getReimbCategory() {;
         return ReimbursementCategory.findByReimbCategoryCv(reimbCategoryCv);
     };

     public ReimbursementCriteria getReimbCriteria() {;
         return ReimbursementCriteria.findByReimbCriteriaCv(reimbCriteriaCv);
     };*/

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
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

    public String getMaskedInd() {
        return maskedInd;
    }

    public void setMaskedInd(String maskedInd) {
        this.maskedInd = maskedInd;
    }

    public String getReimbCriteriaCv() {
        return reimbCriteriaCv;
    }

    public void setReimbCriteriaCv(String reimbCriteriaCv) {
        this.reimbCriteriaCv = reimbCriteriaCv;
    }

    public String getReimbCategoryCv() {
        return reimbCategoryCv;
    }

    public void setReimbCategoryCv(String reimbCategoryCv) {
        this.reimbCategoryCv = reimbCategoryCv;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }
}

