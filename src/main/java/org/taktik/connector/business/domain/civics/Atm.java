package org.taktik.connector.business.domain.civics;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.taktik.connector.business.domain.civics.*;

public class Atm {

	Vtm vtm;
	Company company;

    Set<org.taktik.connector.business.domain.civics.Amp> amps = new HashSet<org.taktik.connector.business.domain.civics.Amp>();

	Date startDate;
	Date createdTms;
	String createdUserId;
	Date endDate;
	Long nameId;
	String specialtyOrigin;
	Date initDate;
	Date closeDate;
	String modificationStatus;
    private Long id;
    private Set<Therapy> therapies = new HashSet<Therapy>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vtm getVtm() {
        return vtm;
    }

    public void setVtm(Vtm vtm) {
        this.vtm = vtm;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getSpecialtyOrigin() {
        return specialtyOrigin;
    }

    public void setSpecialtyOrigin(String specialtyOrigin) {
        this.specialtyOrigin = specialtyOrigin;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getModificationStatus() {
        return modificationStatus;
    }

    public void setModificationStatus(String modificationStatus) {
        this.modificationStatus = modificationStatus;
    }

    public Set<Therapy> getTherapies() {
        return therapies;
    }

    public void setTherapies(Set<Therapy> therapies) {
        this.therapies = therapies;
    }

    public Set<org.taktik.connector.business.domain.civics.Amp> getAmps() {
        return amps;
    }

    public void setAmps(Set<org.taktik.connector.business.domain.civics.Amp> amps) {
        this.amps = amps;
    }
}

