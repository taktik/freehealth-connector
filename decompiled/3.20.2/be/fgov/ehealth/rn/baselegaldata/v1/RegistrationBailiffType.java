package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegistrationBailiffType",
   propOrder = {"bailiffName", "location"}
)
public class RegistrationBailiffType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BailiffName"
   )
   protected String bailiffName;
   @XmlElement(
      name = "Location"
   )
   protected LocationType location;

   public String getBailiffName() {
      return this.bailiffName;
   }

   public void setBailiffName(String value) {
      this.bailiffName = value;
   }

   public LocationType getLocation() {
      return this.location;
   }

   public void setLocation(LocationType value) {
      this.location = value;
   }
}
