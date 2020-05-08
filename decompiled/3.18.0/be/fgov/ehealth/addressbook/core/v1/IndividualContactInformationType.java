package be.fgov.ehealth.addressbook.core.v1;

import be.fgov.ehealth.aa.complextype.v1.Address;
import be.fgov.ehealth.aa.complextype.v1.HealthCareAdditionalInformation;
import be.fgov.ehealth.aa.complextype.v1.IndividualType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IndividualContactInformationType",
   propOrder = {"addresses", "healthCareAdditionalInformations", "professionalInformations"}
)
public class IndividualContactInformationType extends IndividualType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Address",
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1"
   )
   protected List<Address> addresses;
   @XmlElement(
      name = "HealthCareAdditionalInformation",
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1"
   )
   protected List<HealthCareAdditionalInformation> healthCareAdditionalInformations;
   @XmlElement(
      name = "ProfessionalInformation",
      required = true
   )
   protected List<ProfessionalInformation> professionalInformations;

   public List<Address> getAddresses() {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      return this.addresses;
   }

   public List<HealthCareAdditionalInformation> getHealthCareAdditionalInformations() {
      if (this.healthCareAdditionalInformations == null) {
         this.healthCareAdditionalInformations = new ArrayList();
      }

      return this.healthCareAdditionalInformations;
   }

   public List<ProfessionalInformation> getProfessionalInformations() {
      if (this.professionalInformations == null) {
         this.professionalInformations = new ArrayList();
      }

      return this.professionalInformations;
   }
}
