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
   name = "PutPatientRequestType",
   propOrder = {"request", "patient"}
)
@XmlRootElement(
   name = "PutPatientRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class PutPatientRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected PersonType patient;

   public PutPatientRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public PersonType getPatient() {
      return this.patient;
   }

   public void setPatient(PersonType value) {
      this.patient = value;
   }
}
