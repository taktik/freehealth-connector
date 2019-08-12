package be.fgov.ehealth.addressbook.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public EHealthBoxType createEHealthBoxType() {
      return new EHealthBoxType();
   }

   public IndividualContactInformationType createIndividualContactInformationType() {
      return new IndividualContactInformationType();
   }

   public OrganizationContactInformationType createOrganizationContactInformationType() {
      return new OrganizationContactInformationType();
   }

   public ProfessionalInformationType createProfessionalInformationType() {
      return new ProfessionalInformationType();
   }

   public ProfessionalInformation createProfessionalInformation() {
      return new ProfessionalInformation();
   }
}
