package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"status"}
)
@XmlRootElement(
   name = "RevokeDataResponse"
)
public class RevokeDataResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected StatusType status;

   public RevokeDataResponse() {
   }

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType value) {
      this.status = value;
   }
}
