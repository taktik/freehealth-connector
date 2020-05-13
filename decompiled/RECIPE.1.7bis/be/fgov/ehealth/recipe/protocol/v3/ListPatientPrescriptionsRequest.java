package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPatientPrescriptionsRequestType",
   propOrder = {"securedListPatientPrescriptionsRequest"}
)
@XmlRootElement(
   name = "ListPatientPrescriptionsRequest"
)
public class ListPatientPrescriptionsRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredListPatientPrescriptionsRequest",
      required = true
   )
   protected SecuredContentType securedListPatientPrescriptionsRequest;

   public SecuredContentType getSecuredListPatientPrescriptionsRequest() {
      return this.securedListPatientPrescriptionsRequest;
   }

   public void setSecuredListPatientPrescriptionsRequest(SecuredContentType value) {
      this.securedListPatientPrescriptionsRequest = value;
   }
}
