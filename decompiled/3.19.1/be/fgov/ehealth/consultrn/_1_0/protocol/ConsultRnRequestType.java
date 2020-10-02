package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.commons._1_0.core.IdentifierType;
import be.fgov.ehealth.commons._1_0.protocol.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRnRequestType",
   propOrder = {"organisation", "applicationID"}
)
@XmlSeeAlso({SearchPhoneticRequest.class, PersonHistoryRequest.class, MonitoringRequest.class, ModifyPersonRequest.class, CreatePersonRequest.class, SearchBySSINRequest.class, FamilyCompositionRequest.class, DeleteInscriptionRequest.class, InsertInscriptionRequest.class})
public class ConsultRnRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Organisation"
   )
   protected IdentifierType organisation;
   @XmlElement(
      name = "ApplicationID",
      required = true
   )
   protected String applicationID;

   public IdentifierType getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(IdentifierType value) {
      this.organisation = value;
   }

   public String getApplicationID() {
      return this.applicationID;
   }

   public void setApplicationID(String value) {
      this.applicationID = value;
   }
}
