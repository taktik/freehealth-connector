package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "markAsDelivered",
   propOrder = {"markAsDeliveredParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "markAsDelivered"
)
public class MarkAsDelivered implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MarkAsDeliveredParamSealed"
   )
   protected byte[] markAsDeliveredParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getMarkAsDeliveredParamSealed() {
      return this.markAsDeliveredParamSealed;
   }

   public void setMarkAsDeliveredParamSealed(byte[] value) {
      this.markAsDeliveredParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
