package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ErrorType",
   propOrder = {"code", "messages", "failures", "destinations"}
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
   protected List<String> messages;
   @XmlElement(
      name = "Failure"
   )
   protected List<String> failures;
   @XmlElement(
      name = "Destination"
   )
   protected List<EhboxIdentifierType> destinations;
   @XmlAttribute(
      name = "PublicationId"
   )
   protected String publicationId;

   public ErrorType() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public List<String> getMessages() {
      if (this.messages == null) {
         this.messages = new ArrayList();
      }

      return this.messages;
   }

   public List<String> getFailures() {
      if (this.failures == null) {
         this.failures = new ArrayList();
      }

      return this.failures;
   }

   public List<EhboxIdentifierType> getDestinations() {
      if (this.destinations == null) {
         this.destinations = new ArrayList();
      }

      return this.destinations;
   }

   public String getPublicationId() {
      return this.publicationId;
   }

   public void setPublicationId(String value) {
      this.publicationId = value;
   }
}
