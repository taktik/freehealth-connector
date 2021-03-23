
package org.taktik.connector.business.vaccinnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Service needed to get the vaccination from vaccinnet.
 *
 * <p>Java class for GetVaccinationsRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetVaccinationsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}RequestType">
 *       &lt;sequence>
 *         &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vaccinationDateSince" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
@XmlType(name = "GetVaccinationsRequestType", propOrder = {
    "patientId",
    "vaccinationDateSince"
})
public class GetVaccinationsRequestType
    extends RequestType
{

    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vaccinationDateSince;

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
     * Gets the value of the vaccinationDateSince property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getVaccinationDateSince() {
        return vaccinationDateSince;
    }

    /**
     * Sets the value of the vaccinationDateSince property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setVaccinationDateSince(XMLGregorianCalendar value) {
        this.vaccinationDateSince = value;
    }

}
