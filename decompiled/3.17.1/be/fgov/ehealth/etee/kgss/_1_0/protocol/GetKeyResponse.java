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
   propOrder = {"sealedKeyResponse"}
)
@XmlRootElement(
   name = "GetKeyResponse"
)
public class GetKeyResponse extends EteeResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedKeyResponse"
   )
   protected SealedContentType sealedKeyResponse;

   public SealedContentType getSealedKeyResponse() {
      return this.sealedKeyResponse;
   }

   public void setSealedKeyResponse(SealedContentType value) {
      this.sealedKeyResponse = value;
   }
}
