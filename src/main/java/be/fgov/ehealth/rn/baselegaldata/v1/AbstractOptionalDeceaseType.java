package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalDeceaseType",
   propOrder = {"deceaseDate", "deceasePlace"}
)
@XmlSeeAlso({DeceaseInfoBaseType.class, DeceaseInfoWithUpdateStatusType.class, DeceaseInfoWithStatusAndSourceType.class})
public abstract class AbstractOptionalDeceaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeceaseDate"
   )
   protected String deceaseDate;
   @XmlElement(
      name = "DeceasePlace"
   )
   protected LocationType deceasePlace;

   public String getDeceaseDate() {
      return this.deceaseDate;
   }

   public void setDeceaseDate(String value) {
      this.deceaseDate = value;
   }

   public LocationType getDeceasePlace() {
      return this.deceasePlace;
   }

   public void setDeceasePlace(LocationType value) {
      this.deceasePlace = value;
   }
}
