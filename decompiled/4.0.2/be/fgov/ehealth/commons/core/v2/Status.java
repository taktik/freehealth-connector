package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusType",
   propOrder = {"statusCode", "statusMessage", "statusDetail"}
)
@XmlRootElement(
   name = "Status"
)
public class Status implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "StatusCode",
      required = true
   )
   protected StatusCode statusCode;
   @XmlElement(
      name = "StatusMessage"
   )
   protected String statusMessage;
   @XmlElement(
      name = "StatusDetail"
   )
   protected StatusDetail statusDetail;

   public Status() {
   }

   public StatusCode getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(StatusCode value) {
      this.statusCode = value;
   }

   public String getStatusMessage() {
      return this.statusMessage;
   }

   public void setStatusMessage(String value) {
      this.statusMessage = value;
   }

   public StatusDetail getStatusDetail() {
      return this.statusDetail;
   }

   public void setStatusDetail(StatusDetail value) {
      this.statusDetail = value;
   }
}
