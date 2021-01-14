package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPersonPhoneticallyCriteriaType",
   propOrder = {"name", "birth", "gender", "address", "maximumResultCount"}
)
public class SearchPersonPhoneticallyCriteriaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected PhoneticName name;
   @XmlElement(
      name = "Birth",
      required = true
   )
   protected PhoneticBirth birth;
   @XmlElement(
      name = "Gender"
   )
   protected PhoneticGender gender;
   @XmlElement(
      name = "Address"
   )
   protected PhoneticAddress address;
   protected Integer maximumResultCount;

   public PhoneticName getName() {
      return this.name;
   }

   public void setName(PhoneticName value) {
      this.name = value;
   }

   public PhoneticBirth getBirth() {
      return this.birth;
   }

   public void setBirth(PhoneticBirth value) {
      this.birth = value;
   }

   public PhoneticGender getGender() {
      return this.gender;
   }

   public void setGender(PhoneticGender value) {
      this.gender = value;
   }

   public PhoneticAddress getAddress() {
      return this.address;
   }

   public void setAddress(PhoneticAddress value) {
      this.address = value;
   }

   public Integer getMaximumResultCount() {
      return this.maximumResultCount;
   }

   public void setMaximumResultCount(Integer value) {
      this.maximumResultCount = value;
   }
}
