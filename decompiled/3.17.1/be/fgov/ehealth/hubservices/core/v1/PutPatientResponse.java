package be.fgov.ehealth.hubservices.core.v1;

import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutPatientResponseType",
   propOrder = {"response", "acknowledge", "patient"}
)
@XmlRootElement(
   name = "PutPatientResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class PutPatientResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected PersonType patient;

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public PersonType getPatient() {
      return this.patient;
   }

   public void setPatient(PersonType value) {
      this.patient = value;
   }
}
