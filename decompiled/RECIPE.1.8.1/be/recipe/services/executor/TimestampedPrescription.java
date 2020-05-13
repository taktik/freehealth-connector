package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig_.SignatureType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"prescriptionWithSecurityToken", "signature"}
)
@XmlRootElement(
   name = "timestampedPrescription"
)
public class TimestampedPrescription {
   @XmlElement(
      namespace = "http:/services.recipe.be/executor"
   )
   protected PrescriptionWithSecurityToken prescriptionWithSecurityToken;
   @XmlElement(
      name = "Signature"
   )
   protected SignatureType signature;

   public PrescriptionWithSecurityToken getPrescriptionWithSecurityToken() {
      return this.prescriptionWithSecurityToken;
   }

   public void setPrescriptionWithSecurityToken(PrescriptionWithSecurityToken value) {
      this.prescriptionWithSecurityToken = value;
   }

   public SignatureType getSignature() {
      return this.signature;
   }

   public void setSignature(SignatureType value) {
      this.signature = value;
   }
}
