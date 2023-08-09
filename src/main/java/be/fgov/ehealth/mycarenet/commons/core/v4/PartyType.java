
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about the party. A party is rather general. some examples are: 1) A physical person by itself. 2) A physical person working for an enterprise. 3) A enterprise by itself
 * 
 * <p>Classe Java pour PartyType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PartyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "PartyType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "physicalPerson",
    "organization"
})
public class PartyType {

    @XmlElement(name = "PhysicalPerson", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected IdType physicalPerson;
    @XmlElement(name = "Organization", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected IdType organization;

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
