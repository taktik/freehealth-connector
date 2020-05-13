package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UploadFileRequestType",
   propOrder = {"securedUploadFileRequest"}
)
public class UploadFileRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredUploadFileRequest",
      required = true
   )
   protected SecuredContentType securedUploadFileRequest;

   public SecuredContentType getSecuredUploadFileRequest() {
      return this.securedUploadFileRequest;
   }

   public void setSecuredUploadFileRequest(SecuredContentType value) {
      this.securedUploadFileRequest = value;
   }
}
