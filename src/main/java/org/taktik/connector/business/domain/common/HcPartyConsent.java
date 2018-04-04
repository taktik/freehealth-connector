package org.taktik.connector.business.domain.common;

import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 10/12/12
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class HcPartyConsent implements Serializable {
    protected be.fgov.ehealth.hubservices.core.v1.HCPartyIdType hcparty;
    protected Instant signdate;
    protected Instant revokedate;
    protected be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType author;
    protected String hubId;

    public HCPartyIdType getHcparty() {
        return hcparty;
    }

    public void setHcparty(HCPartyIdType hcparty) {
        this.hcparty = hcparty;
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

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public String getNihii() {
        IDHCPARTYschemes scheme = hcparty != null && hcparty.getIds().size() > 0 ? hcparty.getIds().get(0).getS() : null;
        return scheme!=null && scheme.equals(IDHCPARTYschemes.ID_HCPARTY)?hcparty.getIds().get(0).getValue():null;
    }

    public String getSsin() {
        IDHCPARTYschemes scheme = hcparty != null && hcparty.getIds().size() > 0 ? hcparty.getIds().get(0).getS() : null;
        return scheme!=null && scheme.equals(IDHCPARTYschemes.INSS)?hcparty.getIds().get(0).getValue():null;
    }
}
