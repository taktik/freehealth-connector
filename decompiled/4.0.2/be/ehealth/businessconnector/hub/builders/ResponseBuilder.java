package be.ehealth.businessconnector.hub.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.fgov.ehealth.hubservices.core.v1.AccessRightListType;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderGetTransactionList;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v1.TransactionAccessListType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public final class ResponseBuilder {
   public ResponseBuilder() {
   }

   public <T> String buildResponse(T response, Class<T> clazz) throws TechnicalConnectorException {
      MarshallerHelper<T, T> marshaller = new MarshallerHelper(clazz, clazz);
      return marshaller.toString(response);
   }

   public String buildIDKMEHRResponse(IDKMEHR response) throws TechnicalConnectorException {
      return this.buildResponse(response, IDKMEHR.class);
   }

   public String buildKmehrHeaderGetTransactionListResponse(KmehrHeaderGetTransactionList response) throws TechnicalConnectorException {
      return this.buildResponse(response, KmehrHeaderGetTransactionList.class);
   }

   public String buildKmehrmessageResponse(Kmehrmessage response) throws TechnicalConnectorException {
      return this.buildResponse(response, Kmehrmessage.class);
   }

   public String buildHCPartyAdaptedTypeResponse(HCPartyAdaptedType response) throws TechnicalConnectorException {
      return this.buildResponse(response, HCPartyAdaptedType.class);
   }

   public String buildPersonTypeResponse(PersonType response) throws TechnicalConnectorException {
      return this.buildResponse(response, PersonType.class);
   }

   public String buildConsentHCPartyTypeResponse(ConsentHCPartyType response) throws TechnicalConnectorException {
      return this.buildResponse(response, ConsentHCPartyType.class);
   }

   public String buildConsentTypeResponse(ConsentType response) throws TechnicalConnectorException {
      return this.buildResponse(response, ConsentType.class);
   }

   public String buildTherapeuticLinkTypeResponse(TherapeuticLinkType response) throws TechnicalConnectorException {
      return this.buildResponse(response, TherapeuticLinkType.class);
   }

   public String buildAccessRightListTypeResponse(AccessRightListType response) throws TechnicalConnectorException {
      return this.buildResponse(response, AccessRightListType.class);
   }

   public String buildTransactionAccessListTypeResponse(TransactionAccessListType response) throws TechnicalConnectorException {
      return this.buildResponse(response, TransactionAccessListType.class);
   }
}
