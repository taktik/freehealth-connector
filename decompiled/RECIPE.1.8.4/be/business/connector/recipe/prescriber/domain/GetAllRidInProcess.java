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
   name = "GetAllRidInProcess"
)
public class GetAllRidInProcess {
   @XmlElement(
      name = "GetAllRidInProcessParam"
   )
   protected byte[] getAllRidInProcessParam;

   public byte[] getGetAllRidInProcessParam() {
      return this.getAllRidInProcessParam;
   }

   public void setGetAllRidInProcessParam(byte[] getAllRidInProcessParam) {
      this.getAllRidInProcessParam = getAllRidInProcessParam;
   }
}
