package be.fgov.ehealth.etee.commons._1_0.etee;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
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
   propOrder = {"code", "messages"}
)
public class ErrorType implements Serializable {
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
   protected List<LocalisedString> messages;

   public ErrorType() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public List<LocalisedString> getMessages() {
      if (this.messages == null) {
         this.messages = new ArrayList();
      }

      return this.messages;
   }
}
