package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listRidsHistoryResponse",
   propOrder = {"listRidsHistoryResultSealed"}
)
@XmlRootElement(
   name = "listRidsHistoryResponse"
)
public class ListRidsHistoryResponse {
   @XmlElement(
      name = "ListRidsHistoryResultSealed"
   )
   protected byte[] listRidsHistoryResultSealed;

   public byte[] getListRidsHistoryResultSealed() {
      return this.listRidsHistoryResultSealed;
   }

   public void setListRidsHistoryResultSealed(byte[] value) {
      this.listRidsHistoryResultSealed = value;
   }
}
