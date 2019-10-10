//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.03.08 à 06:26:06 PM CET
//


package org.taktik.connector.business.domain.kmehr.v20161201.org.w3._2001._04.xmlenc_;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour EncryptedKeyType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="EncryptedKeyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/04/xmlenc#}EncryptedType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}ReferenceList" minOccurs="0"/>
 *         &lt;element name="CarriedKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Recipient" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedKeyType", propOrder = {
    "referenceList",
    "carriedKeyName"
})
@XmlRootElement(name = "EncryptedKey")
public class EncryptedKey
    extends EncryptedType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(name = "ReferenceList")
    protected ReferenceList referenceList;
    @XmlElement(name = "CarriedKeyName")
    protected String carriedKeyName;
    @XmlAttribute(name = "Recipient")
    protected String recipient;

    /**
     * Obtient la valeur de la propriété referenceList.
     *
     * @return
     *     possible object is
     *     {@link ReferenceList }
     *
     */
    public ReferenceList getReferenceList() {
        return referenceList;
    }

    /**
     * Définit la valeur de la propriété referenceList.
     *
     * @param value
     *     allowed object is
     *     {@link ReferenceList }
     *
     */
    public void setReferenceList(ReferenceList value) {
        this.referenceList = value;
    }

    /**
     * Obtient la valeur de la propriété carriedKeyName.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCarriedKeyName() {
        return carriedKeyName;
    }

    /**
     * Définit la valeur de la propriété carriedKeyName.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCarriedKeyName(String value) {
        this.carriedKeyName = value;
    }

    /**
     * Obtient la valeur de la propriété recipient.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Définit la valeur de la propriété recipient.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecipient(String value) {
        this.recipient = value;
    }

}
