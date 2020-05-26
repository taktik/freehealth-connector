package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenRidsResponse",
   propOrder = {"listOpenRidsResultSealed"}
)
@XmlRootElement(
   name = "listOpenRidsResponse"
)
public class ListOpenRidsResponse {
   @XmlElement(
      name = "ListOpenRidsResultSealed"
   )
   protected byte[] listOpenRidsResultSealed;

   public byte[] getListOpenRidsResultSealed() {
      return this.listOpenRidsResultSealed;
   }

   public void setListOpenRidsResultSealed(byte[] value) {
      this.listOpenRidsResultSealed = value;
   }
}
