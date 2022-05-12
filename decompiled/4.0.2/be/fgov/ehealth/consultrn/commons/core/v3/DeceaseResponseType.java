package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeceaseResponseType",
   propOrder = {"deceaseDate", "deceasePlace"}
)
public class DeceaseResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeceaseDate"
   )
   protected String deceaseDate;
   @XmlElement(
      name = "DeceasePlace"
   )
   protected WhereResponseType deceasePlace;

   public DeceaseResponseType() {
   }

   public String getDeceaseDate() {
      return this.deceaseDate;
   }

   public void setDeceaseDate(String value) {
      this.deceaseDate = value;
   }

   public WhereResponseType getDeceasePlace() {
      return this.deceasePlace;
   }

   public void setDeceasePlace(WhereResponseType value) {
      this.deceasePlace = value;
   }
}
