
package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The "root"-type of the common output element.
 * 
 * <p>Classe Java pour CommonOutputType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommonOutputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InputReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NIPReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OutputReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CommonOutputType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "inputReference",
    "nipReference",
    "outputReference",
    "reference",
    "attribute"
})
public class CommonOutputType {

    @XmlElement(name = "InputReference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String inputReference;
    @XmlElement(name = "NIPReference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String nipReference;
    @XmlElement(name = "OutputReference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected String outputReference;
    @XmlElement(name = "Reference", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected List<ReferenceType> reference;
    @XmlElement(name = "Attribute", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected List<AttributeType> attribute;

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
     * Obtient la valeur de la propriété outputReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputReference() {
        return outputReference;
    }

    /**
     * Définit la valeur de la propriété outputReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputReference(String value) {
        this.outputReference = value;
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
