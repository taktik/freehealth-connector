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
}
