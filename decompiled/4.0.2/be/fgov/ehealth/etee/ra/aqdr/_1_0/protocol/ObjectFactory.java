package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public EHActorQualitiesDataRequest createEHActorQualitiesDataRequest() {
      return new EHActorQualitiesDataRequest();
   }

   public EHActorQualitiesDataResponse createEHActorQualitiesDataResponse() {
      return new EHActorQualitiesDataResponse();
   }

   public LegalPerson createLegalPerson() {
      return new LegalPerson();
   }

   public NaturalPerson createNaturalPerson() {
      return new NaturalPerson();
   }

   public Quality createQuality() {
      return new Quality();
   }

   public Organization createOrganization() {
      return new Organization();
   }

   public Identifier createIdentifier() {
      return new Identifier();
   }
}
