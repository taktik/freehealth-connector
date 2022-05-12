package be.fgov.ehealth.dics.protocol.v5;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValidateSamIdRequestType",
   propOrder = {"samId"}
)
@XmlRootElement(
   name = "ValidateSamIdRequest"
)
public class ValidateSamIdRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SamId",
      required = true
   )
   protected String samId;

   public ValidateSamIdRequest() {
   }

   public String getSamId() {
      return this.samId;
   }

   public void setSamId(String value) {
      this.samId = value;
   }
}
