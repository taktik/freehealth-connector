package be.fgov.ehealth.idsupport.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.idsupport.core.v2.ValidationResult;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VerifyIdResponseType",
   propOrder = {"validationResult"}
)
@XmlRootElement(
   name = "VerifyIdResponse"
)
public class VerifyIdResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValidationResult",
      namespace = "urn:be:fgov:ehealth:idsupport:core:v2"
   )
   protected ValidationResult validationResult;

   public ValidationResult getValidationResult() {
      return this.validationResult;
   }

   public void setValidationResult(ValidationResult value) {
      this.validationResult = value;
   }
}
