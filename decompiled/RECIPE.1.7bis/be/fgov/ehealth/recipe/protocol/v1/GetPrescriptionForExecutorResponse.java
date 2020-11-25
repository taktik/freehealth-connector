package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForExecutorResponseType",
   propOrder = {"securedGetPrescriptionForExecutorResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionForExecutorResponse"
)
public class GetPrescriptionForExecutorResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredGetPrescriptionForExecutorResponse"
   )
   protected SecuredContentType securedGetPrescriptionForExecutorResponse;

   public SecuredContentType getSecuredGetPrescriptionForExecutorResponse() {
      return this.securedGetPrescriptionForExecutorResponse;
   }

   public void setSecuredGetPrescriptionForExecutorResponse(SecuredContentType value) {
      this.securedGetPrescriptionForExecutorResponse = value;
   }
}
