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
   name = "OrganizationDescriberTypeV2",
   propOrder = {"organizationTypeCodes", "organizationTypeFriendlyNames", "attributes", "checkDigit", "regex", "allowedBaseServices"}
)
@XmlSeeAlso({SupportedOrganizationV2.class})
public class OrganizationDescriberTypeV2 implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OrganizationTypeCode",
      required = true
   )
   protected List<TypeCodeType> organizationTypeCodes;
   @XmlElement(
      name = "OrganizationTypeFriendlyName",
      required = true
   )
   protected List<NameType> organizationTypeFriendlyNames;
   @XmlElement(
      name = "Attribute",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected List<Attribute> attributes;
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

   public OrganizationDescriberTypeV2() {
   }

   public List<TypeCodeType> getOrganizationTypeCodes() {
      if (this.organizationTypeCodes == null) {
         this.organizationTypeCodes = new ArrayList();
      }

      return this.organizationTypeCodes;
   }

   public List<NameType> getOrganizationTypeFriendlyNames() {
      if (this.organizationTypeFriendlyNames == null) {
         this.organizationTypeFriendlyNames = new ArrayList();
      }

      return this.organizationTypeFriendlyNames;
   }

   public List<Attribute> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
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
