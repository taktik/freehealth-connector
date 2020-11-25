package be.ehealth.apb.gfddpp.services.tipsystem;

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
   namespace = "urn:be:fgov:ehealth:errors:core:v1",
   propOrder = {"origin", "code", "message", "retry", "contact"}
)
@XmlSeeAlso({SOAErrorType.class})
public class ErrorType {
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
   protected List<LocalisedStringType> message;
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

   public void setOrigin(String var1) {
      this.origin = var1;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String var1) {
      this.code = var1;
   }

   public List<LocalisedStringType> getMessage() {
      if (this.message == null) {
         this.message = new ArrayList();
      }

      return this.message;
   }

   public Boolean isRetry() {
      return this.retry;
   }

   public void setRetry(Boolean var1) {
      this.retry = var1;
   }

   public String getContact() {
      return this.contact;
   }

   public void setContact(String var1) {
      this.contact = var1;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String var1) {
      this.id = var1;
   }
}
