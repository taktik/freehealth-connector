package org.taktik.connector.business.domain.dmg;

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class DmgClosure extends DmgMessageWithPatient implements Serializable {
    private HcpartyType previousHcParty;
    private HcpartyType newHcParty;
    private Date endOfPreviousDmg;
    private Date beginOfNewDmg;
    private String newHcPartyId;

    public HcpartyType getPreviousHcParty() {
        return previousHcParty;
    }

    public void setPreviousHcParty(HcpartyType previousHcParty) {
        this.previousHcParty = previousHcParty;
    }

    public HcpartyType getNewHcParty() {
        return newHcParty;
    }

    public void setNewHcParty(HcpartyType newHcParty) {
        this.newHcParty = newHcParty;
    }

    public Date getEndOfPreviousDmg() {
        return endOfPreviousDmg;
    }

    public void setEndOfPreviousDmg(Date endOfPreviousDmg) {
        this.endOfPreviousDmg = endOfPreviousDmg;
    }

    public Date getBeginOfNewDmg() {
        return beginOfNewDmg;
    }

    public void setBeginOfNewDmg(Date beginOfNewDmg) {
        this.beginOfNewDmg = beginOfNewDmg;
    }

    public void setNewHcPartyId(String newHcPartyId) {
        this.newHcPartyId = newHcPartyId;
    }

    public String getNewHcPartyId() {
        return newHcPartyId;
    }
}
