package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DigitalSignedSmoaMessageType",
   propOrder = {"message", "signature"}
)
public class DigitalSignedSmoaMessageType {
   @XmlElement(
      required = true
   )
   protected SmoaMessageType message;
   @XmlElement(
      required = true
   )
   protected DigitalSignatureType signature;

   public SmoaMessageType getMessage() {
      return this.message;
   }

   public void setMessage(SmoaMessageType value) {
      this.message = value;
   }

   public DigitalSignatureType getSignature() {
      return this.signature;
   }

   public void setSignature(DigitalSignatureType value) {
      this.signature = value;
   }
}
