
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Information used to determine the health insurance organization. NIP-PIN uses it if the HIO is not defined explicitely in the WS-Addressing "To" element.
 * 
 * <p>Classe Java pour RoutingType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RoutingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="CareReceiver" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}CareReceiverIdType"/>
 *           &lt;element name="Subject" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}SubjectType"/>
 *         &lt;/choice>
 *         &lt;element name="ReferenceDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Period" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}PeriodType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoutingType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "careReceiver",
    "subject",
    "referenceDate",
    "period"
})
public class RoutingType {

    @XmlElement(name = "CareReceiver", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected CareReceiverIdType careReceiver;
    @XmlElement(name = "Subject", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected SubjectType subject;
    @XmlElement(name = "ReferenceDate", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar referenceDate;
    @XmlElement(name = "Period", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected PeriodType period;

    /**
     * Obtient la valeur de la propriété careReceiver.
     * 
     * @return
     *     possible object is
     *     {@link CareReceiverIdType }
     *     
     */
    public CareReceiverIdType getCareReceiver() {
        return careReceiver;
    }

    /**
     * Définit la valeur de la propriété careReceiver.
     * 
     * @param value
     *     allowed object is
     *     {@link CareReceiverIdType }
     *     
     */
    public void setCareReceiver(CareReceiverIdType value) {
        this.careReceiver = value;
    }

    /**
     * Obtient la valeur de la propriété subject.
     * 
     * @return
     *     possible object is
     *     {@link SubjectType }
     *     
     */
    public SubjectType getSubject() {
        return subject;
    }

    /**
     * Définit la valeur de la propriété subject.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectType }
     *     
     */
    public void setSubject(SubjectType value) {
        this.subject = value;
    }

    /**
     * Obtient la valeur de la propriété referenceDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReferenceDate() {
        return referenceDate;
    }

    /**
     * Définit la valeur de la propriété referenceDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReferenceDate(XMLGregorianCalendar value) {
        this.referenceDate = value;
    }

    /**
     * Obtient la valeur de la propriété period.
     * 
     * @return
     *     possible object is
     *     {@link PeriodType }
     *     
     */
    public PeriodType getPeriod() {
        return period;
    }

    /**
     * Définit la valeur de la propriété period.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *     
     */
    public void setPeriod(PeriodType value) {
        this.period = value;
    }

}
