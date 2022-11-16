
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Everything that is an entity derives from this type. Enties are object that are distinguishable and have a name. This name isn't nesesary unique.
 * 
 * <p>Classe Java pour AbstractIdType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractIdType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}SelfRefType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ValueRefString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractIdType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "name"
})
@XmlSeeAlso({
    IdType.class,
    PackageType.class
})
public abstract class AbstractIdType
    extends SelfRefType
{

    @XmlElement(name = "Name", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected ValueRefString name;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link ValueRefString }
     *     
     */
    public ValueRefString getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueRefString }
     *     
     */
    public void setName(ValueRefString value) {
        this.name = value;
    }

}
