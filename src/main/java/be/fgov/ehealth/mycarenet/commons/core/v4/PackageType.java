
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about the software package that is connectedto MyCareNet.
 * 
 * <p>Classe Java pour PackageType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PackageType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}AbstractIdType">
 *       &lt;sequence>
 *         &lt;element name="License" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}LicenseType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "license"
})
public class PackageType
    extends AbstractIdType
{

    @XmlElement(name = "License", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", required = true)
    protected LicenseType license;

    /**
     * Obtient la valeur de la propriété license.
     * 
     * @return
     *     possible object is
     *     {@link LicenseType }
     *     
     */
    public LicenseType getLicense() {
        return license;
    }

    /**
     * Définit la valeur de la propriété license.
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseType }
     *     
     */
    public void setLicense(LicenseType value) {
        this.license = value;
    }

}
