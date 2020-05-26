package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "markAsUnDelivered",
   propOrder = {"markAsUndeliveredParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "markAsUnDelivered"
)
public class MarkAsUnDelivered implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MarkAsUndeliveredParamSealed"
   )
   protected byte[] markAsUndeliveredParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getMarkAsUndeliveredParamSealed() {
      return this.markAsUndeliveredParamSealed;
   }

   public void setMarkAsUndeliveredParamSealed(byte[] value) {
      this.markAsUndeliveredParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
