package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticBirth",
   propOrder = {"birthDate", "variation"}
)
public class PhoneticBirth implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BirthDate",
      required = true
   )
   protected String birthDate;
   @XmlElement(
      name = "Variation"
   )
   protected Integer variation;

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public Integer getVariation() {
      return this.variation;
   }

   public void setVariation(Integer value) {
      this.variation = value;
   }
}
