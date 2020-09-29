package be.business.connector.recipe.prescriber.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutVisionResponse"
)
public class PutVisionResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PutVisionResultSealed"
   )
   protected byte[] putVisionResultSealed;

   public byte[] getPutVisionResultSealed() {
      return this.putVisionResultSealed;
   }

   public void setPutVisionResultSealed(byte[] putVisionResultSealed) {
      this.putVisionResultSealed = putVisionResultSealed;
   }
}
