package be.fgov.ehealth.certra.core.v2;

import be.fgov.ehealth.commons.core.v2.ActorType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CertificateIdentifierType",
   propOrder = {"actor", "applicationId"}
)
public class CertificateIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Actor",
      required = true
   )
   protected ActorType actor;
   @XmlElement(
      name = "ApplicationId"
   )
   protected String applicationId;

   public ActorType getActor() {
      return this.actor;
   }

   public void setActor(ActorType value) {
      this.actor = value;
   }

   public String getApplicationId() {
      return this.applicationId;
   }

   public void setApplicationId(String value) {
      this.applicationId = value;
   }
}
