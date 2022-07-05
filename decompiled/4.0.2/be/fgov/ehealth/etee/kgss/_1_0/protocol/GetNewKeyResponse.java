package be.fgov.ehealth.etee.kgss._1_0.protocol;

import be.fgov.ehealth.etee.commons._1_0.etee.EteeResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"sealedNewKeyResponse"}
)
@XmlRootElement(
   name = "GetNewKeyResponse"
)
public class GetNewKeyResponse extends EteeResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedNewKeyResponse"
   )
   protected SealedContentType sealedNewKeyResponse;

   public GetNewKeyResponse() {
   }

   public SealedContentType getSealedNewKeyResponse() {
      return this.sealedNewKeyResponse;
   }

   public void setSealedNewKeyResponse(SealedContentType value) {
      this.sealedNewKeyResponse = value;
   }
}
