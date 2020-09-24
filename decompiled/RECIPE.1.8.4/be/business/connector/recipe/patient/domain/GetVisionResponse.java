package be.business.connector.recipe.patient.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getVisionResponse",
   propOrder = {"getVisionResultSealed"}
)
@XmlRootElement(
   name = "getVisionResponse",
   namespace = "http:/services.recipe.be/patient"
)
public class GetVisionResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetVisionResultSealed"
   )
   protected byte[] getVisionResultSealed;

   public GetVisionResponse() {
   }

   public GetVisionResponse(byte[] getVisionResultSealed) {
      this.getVisionResultSealed = getVisionResultSealed;
   }

   public byte[] getGetVisionResultSealed() {
      return this.getVisionResultSealed;
   }

   public void setGetVisionResultSealed(byte[] value) {
      this.getVisionResultSealed = value;
   }
}
