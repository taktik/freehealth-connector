
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
 * <p>Java class for AddVaccinationsResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AddVaccinationsResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}StatusType"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="Results" maxOccurs="5">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="KmehrId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;sequence>
 *                       &lt;element name="Status" type="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}StatusType" minOccurs="0"/>
 *                       &lt;element name="VaccinationId" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;simpleContent>
 *                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                               &lt;attribute name="Source" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                             &lt;/extension>
 *                           &lt;/simpleContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="Stocked" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                     &lt;/sequence>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddVaccinationsResponseType", propOrder = {
    "status",
    "patientId",
    "results"
})
public class AddVaccinationsResponseType {

    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    @XmlElement(name = "PatientId")
    protected String patientId;
    @XmlElement(name = "Results")
    protected List<AddVaccinationsResponseType.Results> results;

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
     * Gets the value of the results property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the results property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResults().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddVaccinationsResponseType.Results }
     *
     *
     */
    public List<AddVaccinationsResponseType.Results> getResults() {
        if (results == null) {
            results = new ArrayList<AddVaccinationsResponseType.Results>();
        }
        return this.results;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="KmehrId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;sequence>
     *           &lt;element name="Status" type="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}StatusType" minOccurs="0"/>
     *           &lt;element name="VaccinationId" minOccurs="0">
     *             &lt;complexType>
     *               &lt;simpleContent>
     *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                   &lt;attribute name="Source" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;/extension>
     *               &lt;/simpleContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *           &lt;element name="Stocked" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    @XmlType(name = "", propOrder = {
        "kmehrId",
        "status",
        "vaccinationId",
        "stocked"
    })
    public static class Results {

        @XmlElement(name = "KmehrId", required = true)
        protected String kmehrId;
        @XmlElement(name = "Status")
        protected StatusType status;
        @XmlElement(name = "VaccinationId")
        protected AddVaccinationsResponseType.Results.VaccinationId vaccinationId;
        @XmlElement(name = "Stocked")
        protected Integer stocked;

        /**
         * Gets the value of the kmehrId property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getKmehrId() {
            return kmehrId;
        }

        /**
         * Sets the value of the kmehrId property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setKmehrId(String value) {
            this.kmehrId = value;
        }

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
         * Gets the value of the vaccinationId property.
         *
         * @return
         *     possible object is
         *     {@link AddVaccinationsResponseType.Results.VaccinationId }
         *
         */
        public AddVaccinationsResponseType.Results.VaccinationId getVaccinationId() {
            return vaccinationId;
        }

        /**
         * Sets the value of the vaccinationId property.
         *
         * @param value
         *     allowed object is
         *     {@link AddVaccinationsResponseType.Results.VaccinationId }
         *
         */
        public void setVaccinationId(AddVaccinationsResponseType.Results.VaccinationId value) {
            this.vaccinationId = value;
        }

        /**
         * Gets the value of the stocked property.
         *
         * @return
         *     possible object is
         *     {@link Integer }
         *
         */
        public Integer getStocked() {
            return stocked;
        }

        /**
         * Sets the value of the stocked property.
         *
         * @param value
         *     allowed object is
         *     {@link Integer }
         *
         */
        public void setStocked(Integer value) {
            this.stocked = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="Source" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        public static class VaccinationId {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "Source", required = true)
            protected String source;

            /**
             * Gets the value of the value property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the source property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSource() {
                return source;
            }

            /**
             * Sets the value of the source property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSource(String value) {
                this.source = value;
            }

        }

    }

}
