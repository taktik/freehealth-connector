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
   name = "SearchProfessionalsRequestType",
   propOrder = {"ssin", "nihii", "firstName", "lastName", "profession", "zipCode", "city", "eMail"}
)
@XmlRootElement(
   name = "SearchProfessionalsRequest"
)
public class SearchProfessionalsRequest extends PaginationRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "NIHII"
   )
   protected String nihii;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "LastName"
   )
   protected String lastName;
   @XmlElement(
      name = "Profession"
   )
   protected String profession;
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

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public String getNIHII() {
      return this.nihii;
   }

   public void setNIHII(String value) {
      this.nihii = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public String getProfession() {
      return this.profession;
   }

   public void setProfession(String value) {
      this.profession = value;
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
