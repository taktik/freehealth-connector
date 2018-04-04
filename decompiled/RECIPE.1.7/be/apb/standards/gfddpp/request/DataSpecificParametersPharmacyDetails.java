package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dataSpecificParametersPharmacyDetails",
   propOrder = {"dGuid", "motivation"}
)
public class DataSpecificParametersPharmacyDetails {
   @XmlElement(
      required = true
   )
   protected String dGuid;
   @XmlElement(
      required = true
   )
   protected Motivation motivation;

   public String getDGuid() {
      return this.dGuid;
   }

   public void setDGuid(String value) {
      this.dGuid = value;
   }

   public Motivation getMotivation() {
      return this.motivation;
   }

   public void setMotivation(Motivation value) {
      this.motivation = value;
   }
}
