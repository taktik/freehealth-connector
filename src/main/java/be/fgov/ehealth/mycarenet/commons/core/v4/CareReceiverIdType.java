
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Universal way of identifying a care receiver. A care receiver can be identified in one of the following ways: 1) SSIN only 2) Mutuality code (e.g. 312) and registration number with the mutuality 3) SSIN, Mutuality code (e.g. 312) and registration number with the mutuality. The most common case it the SSIN only.
 * 
 * <p>Classe Java pour CareReceiverIdType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CareReceiverIdType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ssin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegNrWithMut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mutuality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CareReceiverIdType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "ssin",
    "regNrWithMut",
    "mutuality"
})
public class CareReceiverIdType {

    @XmlElement(name = "Ssin", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String ssin;
    @XmlElement(name = "RegNrWithMut", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String regNrWithMut;
    @XmlElement(name = "Mutuality", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String mutuality;

    /**
     * Obtient la valeur de la propriété ssin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsin() {
        return ssin;
    }

    /**
     * Définit la valeur de la propriété ssin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsin(String value) {
        this.ssin = value;
    }

    /**
     * Obtient la valeur de la propriété regNrWithMut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegNrWithMut() {
        return regNrWithMut;
    }

    /**
     * Définit la valeur de la propriété regNrWithMut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegNrWithMut(String value) {
        this.regNrWithMut = value;
    }

    /**
     * Obtient la valeur de la propriété mutuality.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMutuality() {
        return mutuality;
    }

    /**
     * Définit la valeur de la propriété mutuality.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMutuality(String value) {
        this.mutuality = value;
    }

}
