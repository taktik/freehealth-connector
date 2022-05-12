package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticGender",
   propOrder = {"genderCode"}
)
public class PhoneticGender implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenderCode",
      required = true
   )
   protected String genderCode;

   public PhoneticGender() {
   }

   public String getGenderCode() {
      return this.genderCode;
   }

   public void setGenderCode(String value) {
      this.genderCode = value;
   }
}
