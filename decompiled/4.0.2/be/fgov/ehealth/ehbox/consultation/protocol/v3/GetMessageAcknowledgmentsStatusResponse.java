package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetMessageAcknowledgmentsStatusResponseType",
   propOrder = {"acknowledgmentsStatus"}
)
@XmlRootElement(
   name = "GetMessageAcknowledgmentsStatusResponse"
)
public class GetMessageAcknowledgmentsStatusResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AcknowledgmentsStatus"
   )
   protected AcknowledgmentsStatus acknowledgmentsStatus;

   public GetMessageAcknowledgmentsStatusResponse() {
   }

   public AcknowledgmentsStatus getAcknowledgmentsStatus() {
      return this.acknowledgmentsStatus;
   }

   public void setAcknowledgmentsStatus(AcknowledgmentsStatus value) {
      this.acknowledgmentsStatus = value;
   }
}
