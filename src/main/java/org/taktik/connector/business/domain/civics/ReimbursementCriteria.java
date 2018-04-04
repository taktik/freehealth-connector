package org.taktik.connector.business.domain.civics;

import java.util.Date;

public class ReimbursementCriteria {

	String reimbCriteriaCv;
	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String criteriaName;
	String reimbCategoryCv;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /*public ReimbursementCategory getReimbCategory() {;
         return ReimbursementCategory.findByReimbCategoryCv(reimbCategoryCv);
     };*/

    public String getReimbCriteriaCv() {
        return reimbCriteriaCv;
    }

    public void setReimbCriteriaCv(String reimbCriteriaCv) {
        this.reimbCriteriaCv = reimbCriteriaCv;
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

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getReimbCategoryCv() {
        return reimbCategoryCv;
    }

    public void setReimbCategoryCv(String reimbCategoryCv) {
        this.reimbCategoryCv = reimbCategoryCv;
    }
}

