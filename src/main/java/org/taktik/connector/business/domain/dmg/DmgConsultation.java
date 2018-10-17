package org.taktik.connector.business.domain.dmg;

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 15/06/14
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class DmgConsultation extends DmgMessage implements Serializable {
    protected String inss;
    protected String firstName;
    protected String lastName;
    protected Instant birthday;
    protected Instant deceased;
    protected String sex;
    protected String regNrWithMut;
    protected String mutuality;

    private HcpartyType hcParty;
    private Instant from;
    private Instant to;

    private Boolean payment;

    private String requestXML;
    private String consultRequestXML;
    private String responseXML;

    public DmgConsultation(boolean complete) {
        super(complete);
    }

    public HcpartyType getHcParty() {
        return hcParty;
    }

    public void setHcParty(HcpartyType hcParty) {
        this.hcParty = hcParty;
    }

    public Instant getFrom() {
        return from;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getTo() {
        return to;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public String getInss() {
        return inss;
    }

    public void setInss(String inss) {
        this.inss = inss;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public Instant getDeceased() {
        return deceased;
    }

    public void setDeceased(Instant deceased) {
        this.deceased = deceased;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegNrWithMut() {
        return regNrWithMut;
    }

    public void setRegNrWithMut(String regNrWithMut) {
        this.regNrWithMut = regNrWithMut;
    }

    public String getMutuality() {
        return mutuality;
    }

    public void setMutuality(String mutuality) {
        this.mutuality = mutuality;
    }

    public String getRequestXML() {
        return requestXML;
    }

    public void setRequestXML(String requestXML) {
        this.requestXML = requestXML;
    }

    public String getConsultRequestXML() {
        return consultRequestXML;
    }

    public void setConsultRequestXML(String consultRequestXML) {
        this.consultRequestXML = consultRequestXML;
    }

    public String getResponseXML() {
        return responseXML;
    }

    public void setResponseXML(String responseXML) {
        this.responseXML = responseXML;
    }
}
