package be.cin.io.unsealed.medicaladvisoragreement.ask.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"timestampReply", "kmehrResponse"}
)
@XmlRootElement(
   name = "Response"
)
public class Response implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TimestampReply",
      required = true
   )
   protected byte[] timestampReply;
   @XmlElement(
      name = "KmehrResponse",
      required = true
   )
   protected byte[] kmehrResponse;

   public Response() {
   }

   public byte[] getTimestampReply() {
      return this.timestampReply;
   }

   public void setTimestampReply(byte[] value) {
      this.timestampReply = value;
   }

   public byte[] getKmehrResponse() {
      return this.kmehrResponse;
   }

   public void setKmehrResponse(byte[] value) {
      this.kmehrResponse = value;
   }
}
