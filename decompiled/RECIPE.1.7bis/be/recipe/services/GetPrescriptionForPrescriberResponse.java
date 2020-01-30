package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForPrescriberResponse",
   propOrder = {"getPrescriptionForPrescriberResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionForPrescriberResponse"
)
public class GetPrescriptionForPrescriberResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionForPrescriberResultSealed"
   )
   protected byte[] getPrescriptionForPrescriberResultSealed;

   public byte[] getGetPrescriptionForPrescriberResultSealed() {
      return this.getPrescriptionForPrescriberResultSealed;
   }

   public void setGetPrescriptionForPrescriberResultSealed(byte[] value) {
      this.getPrescriptionForPrescriberResultSealed = (byte[])value;
   }
}
