
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about a care provider. This is a person or institution that provides health care service to persons.
 * 
 * <p>Classe Java pour CareProviderType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CareProviderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Nihii" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}NihiiType" minOccurs="0"/>
 *         &lt;element name="PhysicalPerson" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}IdType" minOccurs="0"/>
 *         &lt;element name="Organization" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}IdType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CareProviderType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "nihii",
    "physicalPerson",
    "organization"
})
public class CareProviderType {

    @XmlElement(name = "Nihii", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected NihiiType nihii;
    @XmlElement(name = "PhysicalPerson", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected IdType physicalPerson;
    @XmlElement(name = "Organization", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected IdType organization;

    /**
     * Obtient la valeur de la propriété nihii.
     * 
     * @return
     *     possible object is
     *     {@link NihiiType }
     *     
     */
    public NihiiType getNihii() {
        return nihii;
    }

    /**
     * Définit la valeur de la propriété nihii.
     * 
     * @param value
     *     allowed object is
     *     {@link NihiiType }
     *     
     */
    public void setNihii(NihiiType value) {
        this.nihii = value;
    }

    /**
     * Obtient la valeur de la propriété physicalPerson.
     * 
     * @return
     *     possible object is
     *     {@link IdType }
     *     
     */
    public IdType getPhysicalPerson() {
        return physicalPerson;
    }

    /**
     * Définit la valeur de la propriété physicalPerson.
     * 
     * @param value
     *     allowed object is
     *     {@link IdType }
     *     
     */
    public void setPhysicalPerson(IdType value) {
        this.physicalPerson = value;
    }

    /**
     * Obtient la valeur de la propriété organization.
     * 
     * @return
     *     possible object is
     *     {@link IdType }
     *     
     */
    public IdType getOrganization() {
        return organization;
    }

    /**
     * Définit la valeur de la propriété organization.
     * 
     * @param value
     *     allowed object is
     *     {@link IdType }
     *     
     */
    public void setOrganization(IdType value) {
        this.organization = value;
    }

}
