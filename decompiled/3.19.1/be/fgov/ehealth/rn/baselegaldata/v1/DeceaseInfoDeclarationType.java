package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeceaseInfoDeclarationType",
   propOrder = {"deceaseDate", "deceasePlace"}
)
public class DeceaseInfoDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeceaseDate",
      required = true
   )
   protected String deceaseDate;
   @XmlElement(
      name = "DeceasePlace"
   )
   protected LocationDeclarationType deceasePlace;

   public String getDeceaseDate() {
      return this.deceaseDate;
   }

   public void setDeceaseDate(String value) {
      this.deceaseDate = value;
   }

   public LocationDeclarationType getDeceasePlace() {
      return this.deceasePlace;
   }

   public void setDeceasePlace(LocationDeclarationType value) {
      this.deceasePlace = value;
   }
}
