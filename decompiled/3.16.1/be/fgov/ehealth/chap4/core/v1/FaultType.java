package be.fgov.ehealth.chap4.core.v1;

import be.fgov.ehealth.commons.core.v1.LocalisedString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   protected LocalisedString message;
   @XmlElement(
      name = "Detail"
   )
   protected List<DetailType> details;

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

   public LocalisedString getMessage() {
      return this.message;
   }

   public void setMessage(LocalisedString value) {
      this.message = value;
   }

   public List<DetailType> getDetails() {
      if (this.details == null) {
         this.details = new ArrayList();
      }

      return this.details;
   }
}
