package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenPrescription",
   propOrder = {"getListOpenPrescriptionParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "listOpenPrescription"
)
public class ListOpenPrescription implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetListOpenPrescriptionParamSealed"
   )
   protected byte[] getListOpenPrescriptionParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getGetListOpenPrescriptionParamSealed() {
      return this.getListOpenPrescriptionParamSealed;
   }

   public void setGetListOpenPrescriptionParamSealed(byte[] value) {
      this.getListOpenPrescriptionParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
