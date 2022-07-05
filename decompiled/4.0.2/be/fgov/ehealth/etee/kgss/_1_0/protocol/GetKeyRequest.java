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
   propOrder = {"sealedKeyRequest"}
)
@XmlRootElement(
   name = "GetKeyRequest"
)
public class GetKeyRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedKeyRequest",
      required = true
   )
   protected SealedContentType sealedKeyRequest;

   public GetKeyRequest() {
   }

   public SealedContentType getSealedKeyRequest() {
      return this.sealedKeyRequest;
   }

   public void setSealedKeyRequest(SealedContentType value) {
      this.sealedKeyRequest = value;
   }
}
