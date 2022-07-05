package be.fgov.ehealth.etee.commons._1_0.etee;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"code", "message"}
)
public class Status implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Message",
      required = true
   )
   protected String message;

   public Status() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String value) {
      this.message = value;
   }
}
