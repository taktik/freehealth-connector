package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "markAsArchived",
   propOrder = {"markAsArchivedParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "markAsArchived"
)
public class MarkAsArchived implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MarkAsArchivedParamSealed"
   )
   protected byte[] markAsArchivedParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getMarkAsArchivedParamSealed() {
      return this.markAsArchivedParamSealed;
   }

   public void setMarkAsArchivedParamSealed(byte[] value) {
      this.markAsArchivedParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
