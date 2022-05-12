package oasis.names.tc.saml._2_0.protocol;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.saml._2_0.assertion.NameIDType;
import org.joda.time.DateTime;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestAbstractType",
   propOrder = {"issuer", "signature", "extensions"}
)
@XmlSeeAlso({NameIDMappingRequest.class, LogoutRequest.class, ManageNameIDRequest.class, ArtifactResolve.class, AuthnRequest.class, AssertionIDRequest.class, SubjectQueryAbstractType.class})
public abstract class RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Issuer",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected NameIDType issuer;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;
   @XmlElement(
      name = "Extensions"
   )
   protected ExtensionsType extensions;
   @XmlAttribute(
      name = "ID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "Version",
      required = true
   )
   protected String version;
   @XmlAttribute(
      name = "IssueInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueInstant;
   @XmlAttribute(
      name = "Destination"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String destination;
   @XmlAttribute(
      name = "Consent"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String consent;

   public RequestAbstractType() {
   }

   public NameIDType getIssuer() {
      return this.issuer;
   }

   public void setIssuer(NameIDType value) {
      this.issuer = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }

   public ExtensionsType getExtensions() {
      return this.extensions;
   }

   public void setExtensions(ExtensionsType value) {
      this.extensions = value;
   }

   public String getID() {
      return this.id;
   }

   public void setID(String value) {
      this.id = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public DateTime getIssueInstant() {
      return this.issueInstant;
   }

   public void setIssueInstant(DateTime value) {
      this.issueInstant = value;
   }

   public String getDestination() {
      return this.destination;
   }

   public void setDestination(String value) {
      this.destination = value;
   }

   public String getConsent() {
      return this.consent;
   }

   public void setConsent(String value) {
      this.consent = value;
   }
}
