
package oasis.names.tc.saml._2_0.assertion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2001._04.xmlenc.EncryptedData;
import org.w3._2001._04.xmlenc.EncryptedKey;


/**
 * <p>Java class for EncryptedElementType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EncryptedElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptedData"/>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptedKey" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedElementType", propOrder = {
    "encryptedData",
    "encryptedKey"
})
public class EncryptedElementType {

    @XmlElement(name = "EncryptedData", namespace = "http://www.w3.org/2001/04/xmlenc#", required = true)
    protected EncryptedData encryptedData;
    @XmlElement(name = "EncryptedKey", namespace = "http://www.w3.org/2001/04/xmlenc#")
    protected List<EncryptedKey> encryptedKey;

    /**
     * Gets the value of the encryptedData property.
     *
     * @return
     *     possible object is
     *     {@link EncryptedData }
     *
     */
    public EncryptedData getEncryptedData() {
        return encryptedData;
    }

    /**
     * Sets the value of the encryptedData property.
     *
     * @param value
     *     allowed object is
     *     {@link EncryptedData }
     *
     */
    public void setEncryptedData(EncryptedData value) {
        this.encryptedData = value;
    }

    /**
     * Gets the value of the encryptedKey property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encryptedKey property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncryptedKey().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EncryptedKey }
     *
     *
     */
    public List<EncryptedKey> getEncryptedKey() {
        if (encryptedKey == null) {
            encryptedKey = new ArrayList<EncryptedKey>();
        }
        return this.encryptedKey;
    }

}
