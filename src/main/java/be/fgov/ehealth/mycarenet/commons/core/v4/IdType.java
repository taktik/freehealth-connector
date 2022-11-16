
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Enities that have a unique identification have this type. This identification is always issued by an authority. Only one of the child elements is required, the others are prohibited. In other words, this is actualy a xs:-choise, but this does not always translate so well in code. Therefore we decided to make it an sequence with all optional elements.
 * 
 * <p>Classe Java pour IdType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="IdType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}AbstractIdType">
 *       &lt;sequence>
 *         &lt;element name="Nihii" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}NihiiType" minOccurs="0"/>
 *         &lt;element name="Ssin" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ValueRefString" minOccurs="0"/>
 *         &lt;element name="Cbe" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ValueRefString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "nihii",
    "ssin",
    "cbe"
})
public class IdType
    extends AbstractIdType
{

    @XmlElement(name = "Nihii", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected NihiiType nihii;
    @XmlElement(name = "Ssin", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected ValueRefString ssin;
    @XmlElement(name = "Cbe", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected ValueRefString cbe;

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
     * Obtient la valeur de la propriété ssin.
     * 
     * @return
     *     possible object is
     *     {@link ValueRefString }
     *     
     */
    public ValueRefString getSsin() {
        return ssin;
    }

    /**
     * Définit la valeur de la propriété ssin.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueRefString }
     *     
     */
    public void setSsin(ValueRefString value) {
        this.ssin = value;
    }

    /**
     * Obtient la valeur de la propriété cbe.
     * 
     * @return
     *     possible object is
     *     {@link ValueRefString }
     *     
     */
    public ValueRefString getCbe() {
        return cbe;
    }

    /**
     * Définit la valeur de la propriété cbe.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueRefString }
     *     
     */
    public void setCbe(ValueRefString value) {
        this.cbe = value;
    }

}
