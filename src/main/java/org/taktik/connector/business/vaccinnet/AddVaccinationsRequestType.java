
package org.taktik.connector.business.vaccinnet;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Service needed to add vaccinations to vaccinnet.
 *
 * <p>Java class for AddVaccinationsRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AddVaccinationsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}RequestType">
 *       &lt;sequence>
 *         &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Base64EncodedKmehrmessage" maxOccurs="5">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *                 &lt;attribute name="DisableStockChange" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "AddVaccinationsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddVaccinationsRequestType", propOrder = {
    "patientId",
    "base64EncodedKmehrmessage"
})
public class AddVaccinationsRequestType
    extends RequestType
{

    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlElement(name = "Base64EncodedKmehrmessage", required = true)
    protected List<AddVaccinationsRequestType.Base64EncodedKmehrmessage> base64EncodedKmehrmessage;

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
     * Gets the value of the base64EncodedKmehrmessage property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the base64EncodedKmehrmessage property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBase64EncodedKmehrmessage().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddVaccinationsRequestType.Base64EncodedKmehrmessage }
     *
     *
     */
    public List<AddVaccinationsRequestType.Base64EncodedKmehrmessage> getBase64EncodedKmehrmessage() {
        if (base64EncodedKmehrmessage == null) {
            base64EncodedKmehrmessage = new ArrayList<AddVaccinationsRequestType.Base64EncodedKmehrmessage>();
        }
        return this.base64EncodedKmehrmessage;
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
     *       &lt;attribute name="DisableStockChange" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    public static class Base64EncodedKmehrmessage {

        @XmlValue
        protected byte[] value;
        @XmlAttribute(name = "DisableStockChange")
        protected Boolean disableStockChange;

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

        /**
         * Gets the value of the disableStockChange property.
         *
         * @return
         *     possible object is
         *     {@link Boolean }
         *
         */
        public Boolean isDisableStockChange() {
            return disableStockChange;
        }

        /**
         * Sets the value of the disableStockChange property.
         *
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *
         */
        public void setDisableStockChange(Boolean value) {
            this.disableStockChange = value;
        }

    }

}
