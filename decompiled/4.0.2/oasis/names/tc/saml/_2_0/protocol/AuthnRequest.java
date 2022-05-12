package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Conditions;
import oasis.names.tc.saml._2_0.assertion.Subject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthnRequestType",
   propOrder = {"subject", "nameIDPolicy", "conditions", "requestedAuthnContext", "scoping"}
)
@XmlRootElement(
   name = "AuthnRequest"
)
public class AuthnRequest extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Subject",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected Subject subject;
   @XmlElement(
      name = "NameIDPolicy"
   )
   protected NameIDPolicy nameIDPolicy;
   @XmlElement(
      name = "Conditions",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected Conditions conditions;
   @XmlElement(
      name = "RequestedAuthnContext"
   )
   protected RequestedAuthnContext requestedAuthnContext;
   @XmlElement(
      name = "Scoping"
   )
   protected Scoping scoping;
   @XmlAttribute(
      name = "ForceAuthn"
   )
   protected Boolean forceAuthn;
   @XmlAttribute(
      name = "IsPassive"
   )
   protected Boolean isPassive;
   @XmlAttribute(
      name = "ProtocolBinding"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String protocolBinding;
   @XmlAttribute(
      name = "AssertionConsumerServiceIndex"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer assertionConsumerServiceIndex;
   @XmlAttribute(
      name = "AssertionConsumerServiceURL"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String assertionConsumerServiceURL;
   @XmlAttribute(
      name = "AttributeConsumingServiceIndex"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer attributeConsumingServiceIndex;
   @XmlAttribute(
      name = "ProviderName"
   )
   protected String providerName;

   public AuthnRequest() {
   }

   public Subject getSubject() {
      return this.subject;
   }

   public void setSubject(Subject value) {
      this.subject = value;
   }

   public NameIDPolicy getNameIDPolicy() {
      return this.nameIDPolicy;
   }

   public void setNameIDPolicy(NameIDPolicy value) {
      this.nameIDPolicy = value;
   }

   public Conditions getConditions() {
      return this.conditions;
   }

   public void setConditions(Conditions value) {
      this.conditions = value;
   }

   public RequestedAuthnContext getRequestedAuthnContext() {
      return this.requestedAuthnContext;
   }

   public void setRequestedAuthnContext(RequestedAuthnContext value) {
      this.requestedAuthnContext = value;
   }

   public Scoping getScoping() {
      return this.scoping;
   }

   public void setScoping(Scoping value) {
      this.scoping = value;
   }

   public Boolean isForceAuthn() {
      return this.forceAuthn;
   }

   public void setForceAuthn(Boolean value) {
      this.forceAuthn = value;
   }

   public Boolean isIsPassive() {
      return this.isPassive;
   }

   public void setIsPassive(Boolean value) {
      this.isPassive = value;
   }

   public String getProtocolBinding() {
      return this.protocolBinding;
   }

   public void setProtocolBinding(String value) {
      this.protocolBinding = value;
   }

   public Integer getAssertionConsumerServiceIndex() {
      return this.assertionConsumerServiceIndex;
   }

   public void setAssertionConsumerServiceIndex(Integer value) {
      this.assertionConsumerServiceIndex = value;
   }

   public String getAssertionConsumerServiceURL() {
      return this.assertionConsumerServiceURL;
   }

   public void setAssertionConsumerServiceURL(String value) {
      this.assertionConsumerServiceURL = value;
   }

   public Integer getAttributeConsumingServiceIndex() {
      return this.attributeConsumingServiceIndex;
   }

   public void setAttributeConsumingServiceIndex(Integer value) {
      this.attributeConsumingServiceIndex = value;
   }

   public String getProviderName() {
      return this.providerName;
   }

   public void setProviderName(String value) {
      this.providerName = value;
   }
}
