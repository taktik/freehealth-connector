package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalBirthType",
   propOrder = {"birthDate", "birthPlace", "actType"}
)
@XmlSeeAlso({BirthInfoBaseType.class, BirthInfoWithUpdateStatusType.class, BirthInfoWithStatusAndSourceType.class})
public abstract class AbstractOptionalBirthType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BirthDate"
   )
   protected String birthDate;
   @XmlElement(
      name = "BirthPlace"
   )
   protected LocationType birthPlace;
   @XmlElement(
      name = "ActType"
   )
   protected ActType actType;

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public LocationType getBirthPlace() {
      return this.birthPlace;
   }

   public void setBirthPlace(LocationType value) {
      this.birthPlace = value;
   }

   public ActType getActType() {
      return this.actType;
   }

   public void setActType(ActType value) {
      this.actType = value;
   }
}
