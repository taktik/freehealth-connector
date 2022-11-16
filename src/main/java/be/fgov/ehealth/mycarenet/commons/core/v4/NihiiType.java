
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The NIHII number with its meta-data.
 * 
 * <p>Classe Java pour NihiiType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NihiiType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}SelfRefType">
 *       &lt;sequence>
 *         &lt;element name="Quality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Value" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ValueRefString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NihiiType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "quality",
    "value"
})
public class NihiiType
    extends SelfRefType
{

    @XmlElement(name = "Quality", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String quality;
    @XmlElement(name = "Value", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected ValueRefString value;

    /**
     * Obtient la valeur de la propriété quality.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuality() {
        return quality;
    }

    /**
     * Définit la valeur de la propriété quality.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuality(String value) {
        this.quality = value;
    }

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link ValueRefString }
     *     
     */
    public ValueRefString getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueRefString }
     *     
     */
    public void setValue(ValueRefString value) {
        this.value = value;
    }

}
