
package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about the originator of the request.
 * 
 * <p>Classe Java pour OriginType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OriginType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;sequence>
 *             &lt;element name="Package" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}PackageType"/>
 *             &lt;element name="SiteID" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ValueRefString" minOccurs="0"/>
 *             &lt;element name="CareProvider" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}CareProviderType"/>
 *             &lt;element name="Sender" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}PartyType" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="Actor" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ActorType" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OriginType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "_package",
    "siteID",
    "careProvider",
    "sender",
    "actor"
})
public class OriginType {

    @XmlElement(name = "Package", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected PackageType _package;
    @XmlElement(name = "SiteID", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected ValueRefString siteID;
    @XmlElement(name = "CareProvider", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected CareProviderType careProvider;
    @XmlElement(name = "Sender", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected PartyType sender;
    @XmlElement(name = "Actor", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected List<ActorType> actor;

    /**
     * Obtient la valeur de la propriété package.
     * 
     * @return
     *     possible object is
     *     {@link PackageType }
     *     
     */
    public PackageType getPackage() {
        return _package;
    }

    /**
     * Définit la valeur de la propriété package.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageType }
     *     
     */
    public void setPackage(PackageType value) {
        this._package = value;
    }

    /**
     * Obtient la valeur de la propriété siteID.
     * 
     * @return
     *     possible object is
     *     {@link ValueRefString }
     *     
     */
    public ValueRefString getSiteID() {
        return siteID;
    }

    /**
     * Définit la valeur de la propriété siteID.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueRefString }
     *     
     */
    public void setSiteID(ValueRefString value) {
        this.siteID = value;
    }

    /**
     * Obtient la valeur de la propriété careProvider.
     * 
     * @return
     *     possible object is
     *     {@link CareProviderType }
     *     
     */
    public CareProviderType getCareProvider() {
        return careProvider;
    }

    /**
     * Définit la valeur de la propriété careProvider.
     * 
     * @param value
     *     allowed object is
     *     {@link CareProviderType }
     *     
     */
    public void setCareProvider(CareProviderType value) {
        this.careProvider = value;
    }

    /**
     * Obtient la valeur de la propriété sender.
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getSender() {
        return sender;
    }

    /**
     * Définit la valeur de la propriété sender.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setSender(PartyType value) {
        this.sender = value;
    }

    /**
     * Gets the value of the actor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActorType }
     * 
     * 
     */
    public List<ActorType> getActor() {
        if (actor == null) {
            actor = new ArrayList<ActorType>();
        }
        return this.actor;
    }

}
