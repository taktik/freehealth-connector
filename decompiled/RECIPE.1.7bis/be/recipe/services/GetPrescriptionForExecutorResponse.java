package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForExecutorResponse",
   propOrder = {"getPrescriptionForExecutorResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionForExecutorResponse"
)
public class GetPrescriptionForExecutorResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionForExecutorResultSealed"
   )
   protected byte[] getPrescriptionForExecutorResultSealed;

   public byte[] getGetPrescriptionForExecutorResultSealed() {
      return this.getPrescriptionForExecutorResultSealed;
   }

   public void setGetPrescriptionForExecutorResultSealed(byte[] value) {
      this.getPrescriptionForExecutorResultSealed = (byte[])value;
   }
}
