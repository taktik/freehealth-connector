package be.fgov.ehealth.errors.core.v1;

import be.fgov.ehealth.errors.soa.v1.SOAErrorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ErrorType",
   propOrder = {"origin", "code", "messages", "retry", "contact"}
)
@XmlSeeAlso({SOAErrorType.class})
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
   protected List<LocalisedStringType> messages;
   @XmlElement(
      name = "Retry"
   )
   protected Boolean retry;
   @XmlElement(
      name = "Contact"
   )
   protected String contact;
   @XmlAttribute(
      name = "Id"
   )
   protected String id;

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

   public List<LocalisedStringType> getMessages() {
      if (this.messages == null) {
         this.messages = new ArrayList();
      }

      return this.messages;
   }

   public Boolean isRetry() {
      return this.retry;
   }

   public void setRetry(Boolean value) {
      this.retry = value;
   }

   public String getContact() {
      return this.contact;
   }

   public void setContact(String value) {
      this.contact = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
