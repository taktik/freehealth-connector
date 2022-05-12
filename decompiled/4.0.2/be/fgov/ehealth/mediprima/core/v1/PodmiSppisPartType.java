package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PodmiSppisPartType",
   propOrder = {"hospitalizationPart", "ambulatoryCarePart", "otherPart"}
)
public class PodmiSppisPartType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "HospitalizationPart",
      required = true
   )
   protected ZivAmiPatientPartType hospitalizationPart;
   @XmlElement(
      name = "AmbulatoryCarePart",
      required = true
   )
   protected ZivAmiPatientPartType ambulatoryCarePart;
   @XmlElement(
      name = "OtherPart",
      required = true
   )
   protected ZivAmiPatientPartType otherPart;

   public PodmiSppisPartType() {
   }

   public ZivAmiPatientPartType getHospitalizationPart() {
      return this.hospitalizationPart;
   }

   public void setHospitalizationPart(ZivAmiPatientPartType value) {
      this.hospitalizationPart = value;
   }

   public ZivAmiPatientPartType getAmbulatoryCarePart() {
      return this.ambulatoryCarePart;
   }

   public void setAmbulatoryCarePart(ZivAmiPatientPartType value) {
      this.ambulatoryCarePart = value;
   }

   public ZivAmiPatientPartType getOtherPart() {
      return this.otherPart;
   }

   public void setOtherPart(ZivAmiPatientPartType value) {
      this.otherPart = value;
   }
}
