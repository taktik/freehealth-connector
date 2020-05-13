package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenPrescriptionResponse",
   propOrder = {"getListOpenPrescriptionResultSealed"}
)
@XmlRootElement(
   name = "listOpenPrescriptionResponse"
)
public class ListOpenPrescriptionResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetListOpenPrescriptionResultSealed"
   )
   protected byte[] getListOpenPrescriptionResultSealed;

   public byte[] getGetListOpenPrescriptionResultSealed() {
      return this.getListOpenPrescriptionResultSealed;
   }

   public void setGetListOpenPrescriptionResultSealed(byte[] value) {
      this.getListOpenPrescriptionResultSealed = (byte[])value;
   }
}
