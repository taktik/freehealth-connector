package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HealthCareOrganizationTypeV2",
   propOrder = {"id", "organizationTypeCodes", "organizationTypeFriendlyNames", "names"}
)
@XmlSeeAlso({OrganizationAddressbookType.class, be.fgov.ehealth.addressbook.protocol.v1.HealthCareOrganization.class})
public class HealthCareOrganizationTypeV2 implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   protected TypeCodeType id;
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
      name = "Name"
   )
   protected List<NameType> names;

   public TypeCodeType getId() {
      return this.id;
   }

   public void setId(TypeCodeType value) {
      this.id = value;
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

   public List<NameType> getNames() {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      return this.names;
   }
}
