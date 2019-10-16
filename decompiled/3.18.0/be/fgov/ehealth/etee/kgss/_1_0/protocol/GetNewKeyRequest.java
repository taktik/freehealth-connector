package be.fgov.ehealth.etee.kgss._1_0.protocol;

import be.fgov.ehealth.commons._1_0.protocol.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"sealedNewKeyRequest"}
)
@XmlRootElement(
   name = "GetNewKeyRequest"
)
public class GetNewKeyRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedNewKeyRequest",
      required = true
   )
   protected SealedContentType sealedNewKeyRequest;

   public SealedContentType getSealedNewKeyRequest() {
      return this.sealedNewKeyRequest;
   }

   public void setSealedNewKeyRequest(SealedContentType value) {
      this.sealedNewKeyRequest = value;
   }
}
