package be.fgov.ehealth.rn.personservice.core.v1;

import be.fgov.ehealth.rn.baselegaldata.v1.GenderCodeType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
   @XmlSchemaType(
      name = "string"
   )
   protected GenderCodeType genderCode;

   public GenderCodeType getGenderCode() {
      return this.genderCode;
   }

   public void setGenderCode(GenderCodeType value) {
      this.genderCode = value;
   }
}
