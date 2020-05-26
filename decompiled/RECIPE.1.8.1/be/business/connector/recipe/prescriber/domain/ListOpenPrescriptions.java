package be.business.connector.recipe.prescriber.domain;

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
   name = "ListOpenPrescriptions"
)
public class ListOpenPrescriptions {
   @XmlElement(
      name = "ListOpenPrescriptionsParamSealed"
   )
   protected byte[] listOpenPrescriptionsParamSealed;

   public byte[] getListOpenPrescriptionsParamSealed() {
      return this.listOpenPrescriptionsParamSealed;
   }

   public void setListOpenPrescriptionsParamSealed(byte[] listOpenPrescriptionsParamSealed) {
      this.listOpenPrescriptionsParamSealed = listOpenPrescriptionsParamSealed;
   }
}
