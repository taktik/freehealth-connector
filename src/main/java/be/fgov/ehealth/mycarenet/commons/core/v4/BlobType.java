
package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Used to transfer (large) binary objects. 
 * 
 * A Binary Large Object with technical metadata. Can contain large abound of binary data, which can be transported via MTOM/XOP when used in web services. It contains attributes/fields that provide technical data. The content/value contains the binary data, first compressed via deflate and then base 64 encoded.
 * 
 * <p>Classe Java pour BlobType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BlobType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *       &lt;attribute name="ContentType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ContentEncoding" type="{http://www.w3.org/2001/XMLSchema}string" default="deflate" />
 *       &lt;attribute name="ContentEncryption" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Etk" type="{http://www.w3.org/2001/XMLSchema}base64Binary" />
 *       &lt;attribute name="HashValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary" />
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Issuer" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="MessageVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BlobType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "value"
})
public class BlobType {

    @XmlValue
    protected byte[] value;
    @XmlAttribute(name = "ContentType", required = true)
    protected String contentType;
    @XmlAttribute(name = "ContentEncoding")
    protected String contentEncoding;
    @XmlAttribute(name = "ContentEncryption")
    protected String contentEncryption;
    @XmlAttribute(name = "Etk")
    protected byte[] etk;
    @XmlAttribute(name = "HashValue")
    protected byte[] hashValue;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String id;
    @XmlAttribute(name = "Reference")
    protected String reference;
    @XmlAttribute(name = "Issuer")
    @XmlSchemaType(name = "anyURI")
    protected String issuer;
    @XmlAttribute(name = "MessageVersion")
    protected String messageVersion;
    @XmlAttribute(name = "MessageName")
    protected String messageName;

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setValue(byte[] value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété contentType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Définit la valeur de la propriété contentType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Obtient la valeur de la propriété contentEncoding.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentEncoding() {
        if (contentEncoding == null) {
            return "deflate";
        } else {
            return contentEncoding;
        }
    }

    /**
     * Définit la valeur de la propriété contentEncoding.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentEncoding(String value) {
        this.contentEncoding = value;
    }

    /**
     * Obtient la valeur de la propriété contentEncryption.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentEncryption() {
        return contentEncryption;
    }

    /**
     * Définit la valeur de la propriété contentEncryption.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentEncryption(String value) {
        this.contentEncryption = value;
    }

    /**
     * Obtient la valeur de la propriété etk.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEtk() {
        return etk;
    }

    /**
     * Définit la valeur de la propriété etk.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEtk(byte[] value) {
        this.etk = value;
    }

    /**
     * Obtient la valeur de la propriété hashValue.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHashValue() {
        return hashValue;
    }

    /**
     * Définit la valeur de la propriété hashValue.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setHashValue(byte[] value) {
        this.hashValue = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété reference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Définit la valeur de la propriété reference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Obtient la valeur de la propriété issuer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Définit la valeur de la propriété issuer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Obtient la valeur de la propriété messageVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageVersion() {
        return messageVersion;
    }

    /**
     * Définit la valeur de la propriété messageVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageVersion(String value) {
        this.messageVersion = value;
    }

    public void setMessageName(String value) {
        this.messageName = value;
    }

}
