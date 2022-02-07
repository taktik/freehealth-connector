
package oasis.names.tc.saml._2_0.protocol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Subject;


/**
 * <p>Java class for SubjectQueryAbstractType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SubjectQueryAbstractType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}RequestAbstractType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Subject"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectQueryAbstractType", propOrder = {
    "subject"
})
@XmlSeeAlso({
    AuthnQuery.class,
    AuthzDecisionQuery.class,
    AttributeQuery.class
})
public abstract class SubjectQueryAbstractType
    extends RequestAbstractType
{

    @XmlElement(name = "Subject", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", required = true)
    protected Subject subject;

    /**
     * Gets the value of the subject property.
     *
     * @return
     *     possible object is
     *     {@link Subject }
     *
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     *
     * @param value
     *     allowed object is
     *     {@link Subject }
     *
     */
    public void setSubject(Subject value) {
        this.subject = value;
    }

}
