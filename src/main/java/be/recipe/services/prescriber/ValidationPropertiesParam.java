package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationPropertiesParam",
   propOrder = {"symmKey", "clientPropertiesVersion"}
)
@XmlRootElement(
   name = "validationPropertiesParam"
)
public class ValidationPropertiesParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String clientPropertiesVersion;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public String getClientPropertiesVersion() {
      return this.clientPropertiesVersion;
   }

   public void setClientPropertiesVersion(String value) {
      this.clientPropertiesVersion = value;
   }

}
