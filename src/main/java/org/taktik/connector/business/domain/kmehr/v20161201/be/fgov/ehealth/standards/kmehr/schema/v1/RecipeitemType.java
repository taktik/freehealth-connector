//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;


/**
 * the item is used to describe atomic medical information.
 *
 * <p>Classe Java pour recipeitemType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="recipeitemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipebasicID-KMEHR"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeCD-ITEM"/>
 *         &lt;element name="content" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipecontentType"/>
 *         &lt;element name="beginmoment" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipemomentType"/>
 *         &lt;element name="endmoment" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipemomentType" minOccurs="0"/>
 *         &lt;element name="lifecycle" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipelifecycleType"/>
 *         &lt;element name="temporality" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipetemporalityType" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipequantityType" minOccurs="0"/>
 *         &lt;element name="frequency" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipefrequencyType" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipedurationType" minOccurs="0"/>
 *         &lt;element name="posology">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="regimen" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;choice minOccurs="0">
 *                     &lt;element name="daynumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *                     &lt;element name="date" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}date"/>
 *                     &lt;element name="weekday">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;extension base="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeweekdayType">
 *                             &lt;sequence>
 *                               &lt;element name="weeknumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *                             &lt;/sequence>
 *                           &lt;/extension>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/choice>
 *                   &lt;element name="daytime">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="time" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}time"/>
 *                             &lt;element name="dayperiod" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipedayperiodType"/>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="quantity" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeadministrationquantityType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="deliverydate" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}date" minOccurs="0"/>
 *         &lt;element name="renewal" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}reciperenewalType" minOccurs="0"/>
 *         &lt;element name="route" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}reciperouteType" minOccurs="0"/>
 *         &lt;element name="instructionforpatient" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" minOccurs="0"/>
 *         &lt;element name="instructionforreimbursement" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" minOccurs="0"/>
 *         &lt;element name="issubstitutionallowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipeitemType", propOrder = {
    "id",
    "cd",
    "content",
    "beginmoment",
    "endmoment",
    "lifecycle",
    "temporality",
    "quantity",
    "frequency",
    "duration",
    "posology",
    "regimen",
    "deliverydate",
    "renewal",
    "route",
    "instructionforpatient",
    "instructionforreimbursement",
    "issubstitutionallowed"
})
public class RecipeitemType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(required = true)
    protected RecipebasicIDKMEHR id;
    @XmlElement(required = true)
    protected RecipeCDITEM cd;
    @XmlElement(required = true)
    protected RecipecontentType content;
    @XmlElement(required = true)
    protected RecipemomentType beginmoment;
    protected RecipemomentType endmoment;
    @XmlElement(required = true)
    protected RecipelifecycleType lifecycle;
    protected RecipetemporalityType temporality;
    protected RecipequantityType quantity;
    protected RecipefrequencyType frequency;
    protected RecipedurationType duration;
    @XmlElement(required = true)
    protected RecipePosology posology;
    protected RecipeRegimen regimen;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deliverydate;
    protected ReciperenewalType renewal;
    protected ReciperouteType route;
    protected TextType instructionforpatient;
    protected TextType instructionforreimbursement;
    @XmlElement(defaultValue = "true")
    protected Boolean issubstitutionallowed;

    /**
     * Obtient la valeur de la propriété id.
     *
     * @return
     *     possible object is
     *     {@link RecipebasicIDKMEHR }
     *
     */
    public RecipebasicIDKMEHR getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     *
     * @param value
     *     allowed object is
     *     {@link RecipebasicIDKMEHR }
     *
     */
    public void setId(RecipebasicIDKMEHR value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété cd.
     *
     * @return
     *     possible object is
     *     {@link RecipeCDITEM }
     *
     */
    public RecipeCDITEM getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     *
     * @param value
     *     allowed object is
     *     {@link RecipeCDITEM }
     *
     */
    public void setCd(RecipeCDITEM value) {
        this.cd = value;
    }

    /**
     * Obtient la valeur de la propriété content.
     *
     * @return
     *     possible object is
     *     {@link RecipecontentType }
     *
     */
    public RecipecontentType getContent() {
        return content;
    }

    /**
     * Définit la valeur de la propriété content.
     *
     * @param value
     *     allowed object is
     *     {@link RecipecontentType }
     *
     */
    public void setContent(RecipecontentType value) {
        this.content = value;
    }

    /**
     * Obtient la valeur de la propriété beginmoment.
     *
     * @return
     *     possible object is
     *     {@link RecipemomentType }
     *
     */
    public RecipemomentType getBeginmoment() {
        return beginmoment;
    }

    /**
     * Définit la valeur de la propriété beginmoment.
     *
     * @param value
     *     allowed object is
     *     {@link RecipemomentType }
     *
     */
    public void setBeginmoment(RecipemomentType value) {
        this.beginmoment = value;
    }

    /**
     * Obtient la valeur de la propriété endmoment.
     *
     * @return
     *     possible object is
     *     {@link RecipemomentType }
     *
     */
    public RecipemomentType getEndmoment() {
        return endmoment;
    }

    /**
     * Définit la valeur de la propriété endmoment.
     *
     * @param value
     *     allowed object is
     *     {@link RecipemomentType }
     *
     */
    public void setEndmoment(RecipemomentType value) {
        this.endmoment = value;
    }

    /**
     * Obtient la valeur de la propriété lifecycle.
     *
     * @return
     *     possible object is
     *     {@link RecipelifecycleType }
     *
     */
    public RecipelifecycleType getLifecycle() {
        return lifecycle;
    }

    /**
     * Définit la valeur de la propriété lifecycle.
     *
     * @param value
     *     allowed object is
     *     {@link RecipelifecycleType }
     *
     */
    public void setLifecycle(RecipelifecycleType value) {
        this.lifecycle = value;
    }

    /**
     * Obtient la valeur de la propriété temporality.
     *
     * @return
     *     possible object is
     *     {@link RecipetemporalityType }
     *
     */
    public RecipetemporalityType getTemporality() {
        return temporality;
    }

    /**
     * Définit la valeur de la propriété temporality.
     *
     * @param value
     *     allowed object is
     *     {@link RecipetemporalityType }
     *
     */
    public void setTemporality(RecipetemporalityType value) {
        this.temporality = value;
    }

    /**
     * Obtient la valeur de la propriété quantity.
     *
     * @return
     *     possible object is
     *     {@link RecipequantityType }
     *
     */
    public RecipequantityType getQuantity() {
        return quantity;
    }

    /**
     * Définit la valeur de la propriété quantity.
     *
     * @param value
     *     allowed object is
     *     {@link RecipequantityType }
     *
     */
    public void setQuantity(RecipequantityType value) {
        this.quantity = value;
    }

    /**
     * Obtient la valeur de la propriété frequency.
     *
     * @return
     *     possible object is
     *     {@link RecipefrequencyType }
     *
     */
    public RecipefrequencyType getFrequency() {
        return frequency;
    }

    /**
     * Définit la valeur de la propriété frequency.
     *
     * @param value
     *     allowed object is
     *     {@link RecipefrequencyType }
     *
     */
    public void setFrequency(RecipefrequencyType value) {
        this.frequency = value;
    }

    /**
     * Obtient la valeur de la propriété duration.
     *
     * @return
     *     possible object is
     *     {@link RecipedurationType }
     *
     */
    public RecipedurationType getDuration() {
        return duration;
    }

    /**
     * Définit la valeur de la propriété duration.
     *
     * @param value
     *     allowed object is
     *     {@link RecipedurationType }
     *
     */
    public void setDuration(RecipedurationType value) {
        this.duration = value;
    }

    /**
     * Obtient la valeur de la propriété posology.
     *
     * @return
     *     possible object is
     *     {@link RecipePosology }
     *
     */
    public RecipePosology getPosology() {
        return posology;
    }

    /**
     * Définit la valeur de la propriété posology.
     *
     * @param value
     *     allowed object is
     *     {@link RecipePosology }
     *
     */
    public void setPosology(RecipePosology value) {
        this.posology = value;
    }

    /**
     * Obtient la valeur de la propriété regimen.
     *
     * @return
     *     possible object is
     *     {@link RecipeRegimen }
     *
     */
    public RecipeRegimen getRegimen() {
        return regimen;
    }

    /**
     * Définit la valeur de la propriété regimen.
     *
     * @param value
     *     allowed object is
     *     {@link RecipeRegimen }
     *
     */
    public void setRegimen(RecipeRegimen value) {
        this.regimen = value;
    }

    /**
     * Obtient la valeur de la propriété deliverydate.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDeliverydate() {
        return deliverydate;
    }

    /**
     * Définit la valeur de la propriété deliverydate.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDeliverydate(XMLGregorianCalendar value) {
        this.deliverydate = value;
    }

    /**
     * Obtient la valeur de la propriété renewal.
     *
     * @return
     *     possible object is
     *     {@link ReciperenewalType }
     *
     */
    public ReciperenewalType getRenewal() {
        return renewal;
    }

    /**
     * Définit la valeur de la propriété renewal.
     *
     * @param value
     *     allowed object is
     *     {@link ReciperenewalType }
     *
     */
    public void setRenewal(ReciperenewalType value) {
        this.renewal = value;
    }

    /**
     * Obtient la valeur de la propriété route.
     *
     * @return
     *     possible object is
     *     {@link ReciperouteType }
     *
     */
    public ReciperouteType getRoute() {
        return route;
    }

    /**
     * Définit la valeur de la propriété route.
     *
     * @param value
     *     allowed object is
     *     {@link ReciperouteType }
     *
     */
    public void setRoute(ReciperouteType value) {
        this.route = value;
    }

    /**
     * Obtient la valeur de la propriété instructionforpatient.
     *
     * @return
     *     possible object is
     *     {@link TextType }
     *
     */
    public TextType getInstructionforpatient() {
        return instructionforpatient;
    }

    /**
     * Définit la valeur de la propriété instructionforpatient.
     *
     * @param value
     *     allowed object is
     *     {@link TextType }
     *
     */
    public void setInstructionforpatient(TextType value) {
        this.instructionforpatient = value;
    }

    /**
     * Obtient la valeur de la propriété instructionforreimbursement.
     *
     * @return
     *     possible object is
     *     {@link TextType }
     *
     */
    public TextType getInstructionforreimbursement() {
        return instructionforreimbursement;
    }

    /**
     * Définit la valeur de la propriété instructionforreimbursement.
     *
     * @param value
     *     allowed object is
     *     {@link TextType }
     *
     */
    public void setInstructionforreimbursement(TextType value) {
        this.instructionforreimbursement = value;
    }

    /**
     * Obtient la valeur de la propriété issubstitutionallowed.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIssubstitutionallowed() {
        return issubstitutionallowed;
    }

    /**
     * Définit la valeur de la propriété issubstitutionallowed.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIssubstitutionallowed(Boolean value) {
        this.issubstitutionallowed = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "text"
    })
    public static class RecipePosology
        implements Serializable
    {

        private final static long serialVersionUID = 20161201L;
        protected TextType text;

        /**
         * Obtient la valeur de la propriété text.
         *
         * @return
         *     possible object is
         *     {@link TextType }
         *
         */
        public TextType getText() {
            return text;
        }

        /**
         * Définit la valeur de la propriété text.
         *
         * @param value
         *     allowed object is
         *     {@link TextType }
         *
         */
        public void setText(TextType value) {
            this.text = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;choice minOccurs="0">
     *           &lt;element name="daynumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
     *           &lt;element name="date" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}date"/>
     *           &lt;element name="weekday">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;extension base="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeweekdayType">
     *                   &lt;sequence>
     *                     &lt;element name="weeknumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
     *                   &lt;/sequence>
     *                 &lt;/extension>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *         &lt;/choice>
     *         &lt;element name="daytime">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="time" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}time"/>
     *                   &lt;element name="dayperiod" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipedayperiodType"/>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="quantity" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeadministrationquantityType"/>
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
        "daynumbersAndQuantitiesAndDaytimes"
    })
    public static class RecipeRegimen
        implements Serializable
    {

        private final static long serialVersionUID = 20161201L;
        @XmlElements({
            @XmlElement(name = "daynumber", required = true, type = BigInteger.class),
            @XmlElement(name = "quantity", required = true, type = RecipeadministrationquantityType.class),
            @XmlElement(name = "daytime", required = true, type = RecipeRegimen.Daytime.class),
            @XmlElement(name = "date", required = true, type = XMLGregorianCalendar.class),
            @XmlElement(name = "weekday", required = true, type = RecipeRegimen.Weekday.class)
        })
        protected List<Object> daynumbersAndQuantitiesAndDaytimes;

        /**
         * Gets the value of the daynumbersAndQuantitiesAndDaytimes property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the daynumbersAndQuantitiesAndDaytimes property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDaynumbersAndQuantitiesAndDaytimes().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BigInteger }
         * {@link RecipeadministrationquantityType }
         * {@link RecipeRegimen.Daytime }
         * {@link XMLGregorianCalendar }
         * {@link RecipeRegimen.Weekday }
         *
         *
         */
        public List<Object> getDaynumbersAndQuantitiesAndDaytimes() {
            if (daynumbersAndQuantitiesAndDaytimes == null) {
                daynumbersAndQuantitiesAndDaytimes = new ArrayList<Object>();
            }
            return this.daynumbersAndQuantitiesAndDaytimes;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         *
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;choice>
         *         &lt;element name="time" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}time"/>
         *         &lt;element name="dayperiod" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipedayperiodType"/>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "dayperiod",
            "time"
        })
        public static class Daytime implements Serializable
        {

            private final static long serialVersionUID = 20161201L;
            protected RecipedayperiodType dayperiod;
            @XmlSchemaType(name = "time")
            protected XMLGregorianCalendar time;

            /**
             * Obtient la valeur de la propriété dayperiod.
             *
             * @return
             *     possible object is
             *     {@link RecipedayperiodType }
             *
             */
            public RecipedayperiodType getDayperiod() {
                return dayperiod;
            }

            /**
             * Définit la valeur de la propriété dayperiod.
             *
             * @param value
             *     allowed object is
             *     {@link RecipedayperiodType }
             *
             */
            public void setDayperiod(RecipedayperiodType value) {
                this.dayperiod = value;
            }

            /**
             * Obtient la valeur de la propriété time.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getTime() {
                return time;
            }

            /**
             * Définit la valeur de la propriété time.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setTime(XMLGregorianCalendar value) {
                this.time = value;
            }

        }


        /**
         * <p>Classe Java pour anonymous complex type.
         *
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeweekdayType">
         *       &lt;sequence>
         *         &lt;element name="weeknumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "weeknumber"
        })
        public static class Weekday
            extends RecipeweekdayType
            implements Serializable
        {

            private final static long serialVersionUID = 20161201L;
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger weeknumber;

            /**
             * Obtient la valeur de la propriété weeknumber.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getWeeknumber() {
                return weeknumber;
            }

            /**
             * Définit la valeur de la propriété weeknumber.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setWeeknumber(BigInteger value) {
                this.weeknumber = value;
            }

        }

    }

}
