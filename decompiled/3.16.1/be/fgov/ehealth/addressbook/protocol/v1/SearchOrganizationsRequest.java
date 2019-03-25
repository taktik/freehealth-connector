package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.PaginationRequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchOrganizationsRequestType",
   propOrder = {"ehp", "cbe", "nihii", "institutionName", "institutionType", "zipCode", "city", "eMail"}
)
@XmlRootElement(
   name = "SearchOrganizationsRequest"
)
public class SearchOrganizationsRequest extends PaginationRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHP"
   )
   protected String ehp;
   @XmlElement(
      name = "CBE"
   )
   protected String cbe;
   @XmlElement(
      name = "NIHII"
   )
   protected String nihii;
   @XmlElement(
      name = "InstitutionName"
   )
   protected String institutionName;
   @XmlElement(
      name = "InstitutionType"
   )
   protected String institutionType;
   @XmlElement(
      name = "ZipCode"
   )
   protected String zipCode;
   @XmlElement(
      name = "City"
   )
   protected String city;
   @XmlElement(
      name = "EMail"
   )
   protected String eMail;

   public String getEHP() {
      return this.ehp;
   }

   public void setEHP(String value) {
      this.ehp = value;
   }

   public String getCBE() {
      return this.cbe;
   }

   public void setCBE(String value) {
      this.cbe = value;
   }

   public String getNIHII() {
      return this.nihii;
   }

   public void setNIHII(String value) {
      this.nihii = value;
   }

   public String getInstitutionName() {
      return this.institutionName;
   }

   public void setInstitutionName(String value) {
      this.institutionName = value;
   }

   public String getInstitutionType() {
      return this.institutionType;
   }

   public void setInstitutionType(String value) {
      this.institutionType = value;
   }

   public String getZipCode() {
      return this.zipCode;
   }

   public void setZipCode(String value) {
      this.zipCode = value;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String value) {
      this.city = value;
   }

   public String getEMail() {
      return this.eMail;
   }

   public void setEMail(String value) {
      this.eMail = value;
   }
}
