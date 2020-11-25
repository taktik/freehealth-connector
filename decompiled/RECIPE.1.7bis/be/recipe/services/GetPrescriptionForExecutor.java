package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForExecutor",
   propOrder = {"getPrescriptionForExecutorParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "getPrescriptionForExecutor"
)
public class GetPrescriptionForExecutor implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionForExecutorParamSealed"
   )
   protected byte[] getPrescriptionForExecutorParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getGetPrescriptionForExecutorParamSealed() {
      return this.getPrescriptionForExecutorParamSealed;
   }

   public void setGetPrescriptionForExecutorParamSealed(byte[] value) {
      this.getPrescriptionForExecutorParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
