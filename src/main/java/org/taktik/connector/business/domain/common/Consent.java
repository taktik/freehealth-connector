package org.taktik.connector.business.domain.common;

import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

/**
 * Created by aduchate on 9/11/13, 15:06
 */
public class Consent implements Serializable {
    protected be.fgov.ehealth.hubservices.core.v1.PatientIdType patient;
    protected List<be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT> cds;
    protected Instant signdate;
    protected Instant revokedate;
    protected be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType author;

    public PatientIdType getPatient() {
        return patient;
    }

    public void setPatient(PatientIdType patient) {
        this.patient = patient;
    }

    public List<CDCONSENT> getCds() {
        return cds;
    }

    public void setCds(List<CDCONSENT> cds) {
        this.cds = cds;
    }

    public Instant getSigndate() {
        return signdate;
    }

    public void setSigndate(Instant signdate) {
        this.signdate = signdate;
    }

    public Instant getRevokedate() {
        return revokedate;
    }

    public void setRevokedate(Instant revokedate) {
        this.revokedate = revokedate;
    }

    public AuthorType getAuthor() {
        return author;
    }

    public void setAuthor(AuthorType author) {
        this.author = author;
    }
}
