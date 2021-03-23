
package org.taktik.connector.business.vaccinnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Service needed to remove a vaccination from vaccinnet.
 *
 * <p>Java class for RemoveVaccinationResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RemoveVaccinationResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}StatusType"/>
 *         &lt;sequence>
 *           &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="VaccinationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoveVaccinationResponseType", propOrder = {
    "status",
    "patientId",
    "vaccinationId"
})
public class RemoveVaccinationResponseType {

    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    @XmlElement(name = "PatientId")
    protected String patientId;
    @XmlElement(name = "VaccinationId")
    protected String vaccinationId;

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link StatusType }
     *
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the patientId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the value of the patientId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPatientId(String value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the vaccinationId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVaccinationId() {
        return vaccinationId;
    }

    /**
     * Sets the value of the vaccinationId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVaccinationId(String value) {
        this.vaccinationId = value;
    }

}
