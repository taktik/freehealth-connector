
package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The "root"-type of the common input element.
 * 
 * <p>Classe Java pour CommonInputType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommonInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Request" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}RequestType"/>
 *         &lt;element name="Origin" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}OriginType"/>
 *         &lt;element name="InputReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NIPReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}ReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Attribute" type="{urn:be:fgov:ehealth:mycarenet:commons:core:v4}AttributeType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonInputType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "request",
    "origin",
    "inputReference",
    "nipReference",
    "reference",
    "attribute"
})
public class CommonInputType {

    @XmlElement(name = "Request", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", required = true)
    protected RequestType request;
    @XmlElement(name = "Origin", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", required = true)
    protected OriginType origin;
    @XmlElement(name = "InputReference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String inputReference;
    @XmlElement(name = "NIPReference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String nipReference;
    @XmlElement(name = "Reference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected List<ReferenceType> reference;
    @XmlElement(name = "Attribute", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected List<AttributeType> attribute;

    /**
     * Obtient la valeur de la propriété request.
     * 
     * @return
     *     possible object is
     *     {@link RequestType }
     *     
     */
    public RequestType getRequest() {
        return request;
    }

    /**
     * Définit la valeur de la propriété request.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestType }
     *     
     */
    public void setRequest(RequestType value) {
        this.request = value;
    }

    /**
     * Obtient la valeur de la propriété origin.
     * 
     * @return
     *     possible object is
     *     {@link OriginType }
     *     
     */
    public OriginType getOrigin() {
        return origin;
    }

    /**
     * Définit la valeur de la propriété origin.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginType }
     *     
     */
    public void setOrigin(OriginType value) {
        this.origin = value;
    }

    /**
     * Obtient la valeur de la propriété inputReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputReference() {
        return inputReference;
    }

    /**
     * Définit la valeur de la propriété inputReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputReference(String value) {
        this.inputReference = value;
    }

    /**
     * Obtient la valeur de la propriété nipReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNIPReference() {
        return nipReference;
    }

    /**
     * Définit la valeur de la propriété nipReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNIPReference(String value) {
        this.nipReference = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public List<ReferenceType> getReference() {
        if (reference == null) {
            reference = new ArrayList<ReferenceType>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeType }
     * 
     * 
     */
    public List<AttributeType> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<AttributeType>();
        }
        return this.attribute;
    }

}
