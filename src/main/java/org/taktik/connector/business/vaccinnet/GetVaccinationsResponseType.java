
package org.taktik.connector.business.vaccinnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Service needed to get vaccinations from vaccinnet.
 * 
 * <p>Java class for GetVaccinationsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetVaccinationsResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}StatusType"/>
 *         &lt;sequence>
 *           &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="Base64EncodedKmehr" minOccurs="0">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetVaccinationsResponseType", propOrder = {
    "status",
    "patientId",
    "base64EncodedKmehr"
})
public class GetVaccinationsResponseType {

    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    @XmlElement(name = "PatientId")
    protected String patientId;
    @XmlElement(name = "Base64EncodedKmehr")
    protected GetVaccinationsResponseType.Base64EncodedKmehr base64EncodedKmehr;

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
     * Gets the value of the base64EncodedKmehr property.
     * 
     * @return
     *     possible object is
     *     {@link GetVaccinationsResponseType.Base64EncodedKmehr }
     *     
     */
    public GetVaccinationsResponseType.Base64EncodedKmehr getBase64EncodedKmehr() {
        return base64EncodedKmehr;
    }

    /**
     * Sets the value of the base64EncodedKmehr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetVaccinationsResponseType.Base64EncodedKmehr }
     *     
     */
    public void setBase64EncodedKmehr(GetVaccinationsResponseType.Base64EncodedKmehr value) {
        this.base64EncodedKmehr = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Base64EncodedKmehr {

        @XmlValue
        protected byte[] value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setValue(byte[] value) {
            this.value = value;
        }

    }

}
