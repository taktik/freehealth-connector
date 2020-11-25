package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createFeedback",
   propOrder = {"createFeedbackParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "createFeedback"
)
public class CreateFeedback implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CreateFeedbackParamSealed"
   )
   protected byte[] createFeedbackParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getCreateFeedbackParamSealed() {
      return this.createFeedbackParamSealed;
   }

   public void setCreateFeedbackParamSealed(byte[] value) {
      this.createFeedbackParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
