package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"unsigned", "signed"}
)
@XmlRootElement(
   name = "single-message"
)
public class SingleMessage {
   protected SmoaMessageType unsigned;
   protected DigitalSignedSmoaMessageType signed;

   public SmoaMessageType getUnsigned() {
      return this.unsigned;
   }

   public void setUnsigned(SmoaMessageType value) {
      this.unsigned = value;
   }

   public DigitalSignedSmoaMessageType getSigned() {
      return this.signed;
   }

   public void setSigned(DigitalSignedSmoaMessageType value) {
      this.signed = value;
   }
}
