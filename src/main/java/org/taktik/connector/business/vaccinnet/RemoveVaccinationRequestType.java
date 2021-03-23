
package org.taktik.connector.business.vaccinnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Service needed to remove a vaccination from vaccinnet.
 *
 * <p>Java class for RemoveVaccinationRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RemoveVaccinationRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}RequestType">
 *       &lt;sequence>
 *         &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VaccinationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoveVaccinationRequestType", propOrder = {
    "patientId",
    "vaccinationId"
})
public class RemoveVaccinationRequestType
    extends RequestType
{

    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlElement(name = "VaccinationId", required = true)
    protected String vaccinationId;

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
