package be.fgov.ehealth.etkra.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EhealthDistinguishedNameType",
   propOrder = {"commonName", "organisationUnit", "organisation", "serialNumber", "emailAddress"}
)
public class EhealthDistinguishedNameType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonName",
      required = true
   )
   protected String commonName;
   @XmlElement(
      name = "OrganisationUnit",
      required = true
   )
   protected String organisationUnit;
   @XmlElement(
      name = "Organisation",
      required = true
   )
   protected String organisation;
   @XmlElement(
      name = "SerialNumber",
      required = true
   )
   protected String serialNumber;
   @XmlElement(
      name = "EmailAddress",
      required = true
   )
   protected String emailAddress;

   public String getCommonName() {
      return this.commonName;
   }

   public void setCommonName(String value) {
      this.commonName = value;
   }

   public String getOrganisationUnit() {
      return this.organisationUnit;
   }

   public void setOrganisationUnit(String value) {
      this.organisationUnit = value;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String value) {
      this.organisation = value;
   }

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String value) {
      this.serialNumber = value;
   }

   public String getEmailAddress() {
      return this.emailAddress;
   }

   public void setEmailAddress(String value) {
      this.emailAddress = value;
   }
}
