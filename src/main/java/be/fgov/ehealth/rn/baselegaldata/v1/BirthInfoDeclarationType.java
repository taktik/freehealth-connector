package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BirthInfoDeclarationType",
   propOrder = {"birthDate", "birthPlace"}
)
public class BirthInfoDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BirthDate"
   )
   protected String birthDate;
   @XmlElement(
      name = "BirthPlace"
   )
   protected LocationDeclarationType birthPlace;

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public LocationDeclarationType getBirthPlace() {
      return this.birthPlace;
   }

   public void setBirthPlace(LocationDeclarationType value) {
      this.birthPlace = value;
   }
}
