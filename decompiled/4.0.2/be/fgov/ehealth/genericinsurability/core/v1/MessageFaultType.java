package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MessageFaultType",
   propOrder = {"faultCode", "faultSource", "multiIO", "message", "details"}
)
public class MessageFaultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FaultCode",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected FaultCodeType faultCode;
   @XmlElement(
      name = "FaultSource",
      required = true
   )
   protected String faultSource;
   @XmlElement(
      name = "MultiIO"
   )
   protected MultiIOType multiIO;
   @XmlElement(
      name = "Message",
      required = true
   )
   protected MessageType message;
   @XmlElement(
      name = "Details",
      required = true
   )
   protected DetailsType details;

   public MessageFaultType() {
   }

   public FaultCodeType getFaultCode() {
      return this.faultCode;
   }

   public void setFaultCode(FaultCodeType value) {
      this.faultCode = value;
   }

   public String getFaultSource() {
      return this.faultSource;
   }

   public void setFaultSource(String value) {
      this.faultSource = value;
   }

   public MultiIOType getMultiIO() {
      return this.multiIO;
   }

   public void setMultiIO(MultiIOType value) {
      this.multiIO = value;
   }

   public MessageType getMessage() {
      return this.message;
   }

   public void setMessage(MessageType value) {
      this.message = value;
   }

   public DetailsType getDetails() {
      return this.details;
   }

   public void setDetails(DetailsType value) {
      this.details = value;
   }
}
