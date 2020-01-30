package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listNotificationsResponse",
   propOrder = {"listNotificationsResultSealed"}
)
@XmlRootElement(
   name = "listNotificationsResponse"
)
public class ListNotificationsResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ListNotificationsResultSealed"
   )
   protected byte[] listNotificationsResultSealed;

   public byte[] getListNotificationsResultSealed() {
      return this.listNotificationsResultSealed;
   }

   public void setListNotificationsResultSealed(byte[] value) {
      this.listNotificationsResultSealed = (byte[])value;
   }
}
