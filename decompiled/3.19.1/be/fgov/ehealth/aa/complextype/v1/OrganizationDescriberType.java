package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Attribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OrganizationDescriberType",
   propOrder = {"organizationTypeCode", "organizationTypeFriendlyNames", "organizationType", "attribute", "checkDigit", "regex", "allowedBaseServices"}
)
@XmlSeeAlso({SupportedOrganization.class})
public class OrganizationDescriberType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OrganizationTypeCode",
      required = true
   )
   protected String organizationTypeCode;
   @XmlElement(
      name = "OrganizationTypeFriendlyName",
      required = true
   )
   protected List<NameType> organizationTypeFriendlyNames;
   @XmlElement(
      name = "OrganizationType",
      required = true
   )
   protected OrganizationIdentifierType organizationType;
   @XmlElement(
      name = "Attribute",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected Attribute attribute;
   @XmlElement(
      name = "CheckDigit"
   )
   protected String checkDigit;
   @XmlElement(
      name = "Regex"
   )
   protected String regex;
   @XmlElement(
      name = "AllowedBaseServices",
      required = true
   )
   protected BaseServiceListType allowedBaseServices;

   public String getOrganizationTypeCode() {
      return this.organizationTypeCode;
   }

   public void setOrganizationTypeCode(String value) {
      this.organizationTypeCode = value;
   }

   public List<NameType> getOrganizationTypeFriendlyNames() {
      if (this.organizationTypeFriendlyNames == null) {
         this.organizationTypeFriendlyNames = new ArrayList();
      }

      return this.organizationTypeFriendlyNames;
   }

   public OrganizationIdentifierType getOrganizationType() {
      return this.organizationType;
   }

   public void setOrganizationType(OrganizationIdentifierType value) {
      this.organizationType = value;
   }

   public Attribute getAttribute() {
      return this.attribute;
   }

   public void setAttribute(Attribute value) {
      this.attribute = value;
   }

   public String getCheckDigit() {
      return this.checkDigit;
   }

   public void setCheckDigit(String value) {
      this.checkDigit = value;
   }

   public String getRegex() {
      return this.regex;
   }

   public void setRegex(String value) {
      this.regex = value;
   }

   public BaseServiceListType getAllowedBaseServices() {
      return this.allowedBaseServices;
   }

   public void setAllowedBaseServices(BaseServiceListType value) {
      this.allowedBaseServices = value;
   }
}
