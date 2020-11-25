package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UploadFileRequestType",
   propOrder = {"securedUploadFileRequest"}
)
public class UploadFileRequestType extends RequestType {
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
