package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationPropertiesResponse",
   propOrder = {"validationPropertiesResultSealed"}
)
@XmlRootElement(
   name = "validationPropertiesResponse"
)
public class ValidationPropertiesResponse {
   @XmlElement(
      name = "ValidationPropertiesResultSealed"
   )
   protected byte[] validationPropertiesResultSealed;

   public byte[] getValidationPropertiesResultSealed() {
      return this.validationPropertiesResultSealed;
   }

   public void setValidationPropertiesResultSealed(byte[] value) {
      this.validationPropertiesResultSealed = value;
   }

}
