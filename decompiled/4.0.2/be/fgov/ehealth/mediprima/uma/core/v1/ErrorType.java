package be.fgov.ehealth.mediprima.uma.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ErrorType",
   propOrder = {"origin", "code", "messages"}
)
public class ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Origin",
      required = true
   )
   protected String origin;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Message",
      required = true
   )
   protected List<NameType> messages;

   public ErrorType() {
   }

   public String getOrigin() {
      return this.origin;
   }

   public void setOrigin(String value) {
      this.origin = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public List<NameType> getMessages() {
      if (this.messages == null) {
         this.messages = new ArrayList();
      }

      return this.messages;
   }
}
