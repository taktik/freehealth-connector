package be.apb.standards.gfddpp.patient.signature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"signature", "publickey"}
)
@XmlRootElement(
   name = "patientSignature"
)
public class PatientSignature {
   @XmlElement(
      required = true
   )
   protected byte[] signature;
   @XmlElement(
      required = true
   )
   protected byte[] publickey;

   public byte[] getSignature() {
      return this.signature;
   }

   public void setSignature(byte[] value) {
      this.signature = value;
   }

   public byte[] getPublickey() {
      return this.publickey;
   }

   public void setPublickey(byte[] value) {
      this.publickey = value;
   }
}
