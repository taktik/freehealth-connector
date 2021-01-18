package be.fgov.ehealth.commons.protocol.v2;

import be.fgov.ehealth.commons.core.v2.Status;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusResponseType",
   propOrder = {"status"}
)
@XmlSeeAlso({PaginationStatusResponseType.class})
public class StatusResponseType extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      namespace = "urn:be:fgov:ehealth:commons:core:v2",
      required = true
   )
   protected Status status;

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status value) {
      this.status = value;
   }
}
