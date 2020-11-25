package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeceaseRequestType",
   propOrder = {"deceaseDate", "deceasePlace"}
)
public class DeceaseRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeceaseDate",
      required = true
   )
   protected String deceaseDate;
   @XmlElement(
      name = "DeceasePlace"
   )
   protected WhereRequestType deceasePlace;

   public String getDeceaseDate() {
      return this.deceaseDate;
   }

   public void setDeceaseDate(String value) {
      this.deceaseDate = value;
   }

   public WhereRequestType getDeceasePlace() {
      return this.deceasePlace;
   }

   public void setDeceasePlace(WhereRequestType value) {
      this.deceasePlace = value;
   }
}
