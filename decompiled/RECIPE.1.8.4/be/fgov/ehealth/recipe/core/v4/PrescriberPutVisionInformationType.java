package be.fgov.ehealth.recipe.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PrescriberPutVisionInformationType",
   propOrder = {"prescriptionVersion", "referenceSourceVersion"}
)
public class PrescriberPutVisionInformationType {
   @XmlElement(
      name = "PrescriptionVersion",
      required = true
   )
   protected String prescriptionVersion;
   @XmlElement(
      name = "ReferenceSourceVersion",
      required = true
   )
   protected String referenceSourceVersion;

   public String getPrescriptionVersion() {
      return this.prescriptionVersion;
   }

   public void setPrescriptionVersion(String value) {
      this.prescriptionVersion = value;
   }

   public String getReferenceSourceVersion() {
      return this.referenceSourceVersion;
   }

   public void setReferenceSourceVersion(String value) {
      this.referenceSourceVersion = value;
   }
}
