package org.taktik.connector.business.domain.dmg;

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 07:55
 * To change this template use File | Settings | File Templates.
 */
public class DmgNotification extends DmgMessage implements Serializable {
    private HcpartyType hcParty;
    private Boolean payment;
    private Instant from;

    private String requestXML;
    private String gmdRequestXML;
    private String responseXML;

    public DmgNotification() {
    }

    public DmgNotification(boolean complete) {
        super(complete);
    }

    public HcpartyType getHcParty() {
        return hcParty;
    }

    public void setHcParty(HcpartyType hcParty) {
        this.hcParty = hcParty;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getFrom() {
        return from;
    }

    public String getRequestXML() {
        return requestXML;
    }

    public void setRequestXML(String requestXML) {
        this.requestXML = requestXML;
    }

    public String getGmdRequestXML() {
        return gmdRequestXML;
    }

    public void setGmdRequestXML(String gmdRequestXML) {
        this.gmdRequestXML = gmdRequestXML;
    }

    public String getResponseXML() {
        return responseXML;
    }

    public void setResponseXML(String responseXML) {
        this.responseXML = responseXML;
    }
}
