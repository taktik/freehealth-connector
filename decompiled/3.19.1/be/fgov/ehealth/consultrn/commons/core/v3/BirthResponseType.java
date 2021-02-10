package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BirthResponseType",
   propOrder = {"birthDate", "birthPlace"}
)
public class BirthResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BirthDate"
   )
   protected String birthDate;
   @XmlElement(
      name = "BirthPlace"
   )
   protected WhereResponseType birthPlace;

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public WhereResponseType getBirthPlace() {
      return this.birthPlace;
   }

   public void setBirthPlace(WhereResponseType value) {
      this.birthPlace = value;
   }
}
