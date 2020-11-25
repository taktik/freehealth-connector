package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "revokePrescription",
   propOrder = {"revokePrescriptionParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "revokePrescription"
)
public class RevokePrescription implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RevokePrescriptionParamSealed"
   )
   protected byte[] revokePrescriptionParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getRevokePrescriptionParamSealed() {
      return this.revokePrescriptionParamSealed;
   }

   public void setRevokePrescriptionParamSealed(byte[] value) {
      this.revokePrescriptionParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
