package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacks",
   propOrder = {"listFeedbacksParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "listFeedbacks"
)
public class ListFeedbacks implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ListFeedbacksParamSealed"
   )
   protected byte[] listFeedbacksParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getListFeedbacksParamSealed() {
      return this.listFeedbacksParamSealed;
   }

   public void setListFeedbacksParamSealed(byte[] value) {
      this.listFeedbacksParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
