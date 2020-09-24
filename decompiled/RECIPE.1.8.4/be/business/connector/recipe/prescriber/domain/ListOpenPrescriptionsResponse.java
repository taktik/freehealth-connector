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
   name = "ListOpenPrescriptionsResponse"
)
public class ListOpenPrescriptionsResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ListOpenPrescriptionsResultSealed"
   )
   protected byte[] listOpenPrescriptionsResultSealed;

   public ListOpenPrescriptionsResponse() {
   }

   public ListOpenPrescriptionsResponse(byte[] listOpenPrescriptionsResultSealed) {
      this.listOpenPrescriptionsResultSealed = listOpenPrescriptionsResultSealed;
   }

   public byte[] getListOpenPrescriptionsResultSealed() {
      return this.listOpenPrescriptionsResultSealed;
   }

   public void setListOpenPrescriptionsResultSealed(byte[] listOpenPrescriptionsResultSealed) {
      this.listOpenPrescriptionsResultSealed = listOpenPrescriptionsResultSealed;
   }
}
