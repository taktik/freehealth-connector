package be.cin.types.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FaultType",
   propOrder = {"faultCode", "faultSource", "message", "details"}
)
public class FaultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FaultCode",
      required = true
   )
   protected String faultCode;
   @XmlElement(
      name = "FaultSource",
      required = true
   )
   protected String faultSource;
   @XmlElement(
      name = "Message",
      required = true
   )
   protected StringLangType message;
   @XmlElement(
      name = "Details",
      required = true
   )
   protected DetailsType details;

   public String getFaultCode() {
      return this.faultCode;
   }

   public void setFaultCode(String value) {
      this.faultCode = value;
   }

   public String getFaultSource() {
      return this.faultSource;
   }

   public void setFaultSource(String value) {
      this.faultSource = value;
   }

   public StringLangType getMessage() {
      return this.message;
   }

   public void setMessage(StringLangType value) {
      this.message = value;
   }

   public DetailsType getDetails() {
      return this.details;
   }

   public void setDetails(DetailsType value) {
      this.details = value;
   }
}
