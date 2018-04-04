package be.fgov.ehealth.certra.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"ehActorQualitiesDataRequest"}
)
@XmlRootElement(
   name = "GetEHActorQualitiesRequest"
)
public class GetEHActorQualitiesRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHActorQualitiesDataRequest",
      required = true
   )
   protected byte[] ehActorQualitiesDataRequest;

   public byte[] getEHActorQualitiesDataRequest() {
      return this.ehActorQualitiesDataRequest;
   }

   public void setEHActorQualitiesDataRequest(byte[] value) {
      this.ehActorQualitiesDataRequest = value;
   }
}
