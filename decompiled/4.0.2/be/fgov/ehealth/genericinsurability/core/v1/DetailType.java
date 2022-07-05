package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailType",
   propOrder = {"detailCode", "location", "message"}
)
public class DetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DetailCode",
      required = true
   )
   protected String detailCode;
   @XmlElement(
      name = "Location"
   )
   protected String location;
   @XmlElement(
      name = "Message"
   )
   protected MessageType message;

   public DetailType() {
   }

   public String getDetailCode() {
      return this.detailCode;
   }

   public void setDetailCode(String value) {
      this.detailCode = value;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(String value) {
      this.location = value;
   }

   public MessageType getMessage() {
      return this.message;
   }

   public void setMessage(MessageType value) {
      this.message = value;
   }
}
