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
   propOrder = {"ehActorQualitiesDataResponse"}
)
@XmlRootElement(
   name = "GetEHActorQualitiesResponse"
)
public class GetEHActorQualitiesResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHActorQualitiesDataResponse"
   )
   protected byte[] ehActorQualitiesDataResponse;

   public byte[] getEHActorQualitiesDataResponse() {
      return this.ehActorQualitiesDataResponse;
   }

   public void setEHActorQualitiesDataResponse(byte[] value) {
      this.ehActorQualitiesDataResponse = value;
   }
}
