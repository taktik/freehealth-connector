package be.ehealth.businessconnector.hub.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v1.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public final class RequestBuilder {
   public RequestBuilder() {
   }

   public <T> T buildRequest(String request, Class<T> clazz) throws TechnicalConnectorException {
      MarshallerHelper<T, T> marshaller = new MarshallerHelper(clazz, clazz);
      return marshaller.toObject(request);
   }

   public KmehrHeaderDeclareTransaction buildKmehrHeaderDeclareTransaction(String xml) throws TechnicalConnectorException {
      return (KmehrHeaderDeclareTransaction)this.buildRequest(xml, KmehrHeaderDeclareTransaction.class);
   }

   public Kmehrmessage buildKmehrmessage(String xml) throws TechnicalConnectorException {
      return (Kmehrmessage)this.buildRequest(xml, Kmehrmessage.class);
   }

   public PatientIdType buildPatientIdType(String xml) throws TechnicalConnectorException {
      return (PatientIdType)this.buildRequest(xml, PatientIdType.class);
   }

   public TransactionIdType buildTransactionIdType(String xml) throws TechnicalConnectorException {
      return (TransactionIdType)this.buildRequest(xml, TransactionIdType.class);
   }

   public LocalSearchType buildLocalSearchType(String name) throws TechnicalConnectorException {
      try {
         return LocalSearchType.fromValue(name);
      } catch (IllegalArgumentException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var3, new Object[0]);
      }
   }

   public TransactionWithPeriodType buildTransactionWithPeriodType(String xml) throws TechnicalConnectorException {
      return (TransactionWithPeriodType)this.buildRequest(xml, TransactionWithPeriodType.class);
   }

   public TransactionBaseType buildTransactionBaseType(String xml) throws TechnicalConnectorException {
      return (TransactionBaseType)this.buildRequest(xml, TransactionBaseType.class);
   }

   public HCPartyAdaptedType buildHCPartyAdaptedType(String xml) throws TechnicalConnectorException {
      return (HCPartyAdaptedType)this.buildRequest(xml, HCPartyAdaptedType.class);
   }

   public HCPartyIdType buildHCPartyIdType(String xml) throws TechnicalConnectorException {
      return (HCPartyIdType)this.buildRequest(xml, HCPartyIdType.class);
   }

   public PersonType buildPersonType(String xml) throws TechnicalConnectorException {
      return (PersonType)this.buildRequest(xml, PersonType.class);
   }

   public ConsentHCPartyType buildConsentHCPartyType(String xml) throws TechnicalConnectorException {
      return (ConsentHCPartyType)this.buildRequest(xml, ConsentHCPartyType.class);
   }

   public ConsentType buildConsentType(String xml) throws TechnicalConnectorException {
      return (ConsentType)this.buildRequest(xml, ConsentType.class);
   }

   public SelectGetPatientConsentType buildSelectGetPatientConsentType(String xml) throws TechnicalConnectorException {
      return (SelectGetPatientConsentType)this.buildRequest(xml, SelectGetPatientConsentType.class);
   }

   public TherapeuticLinkType buildTherapeuticLinkType(String xml) throws TechnicalConnectorException {
      return (TherapeuticLinkType)this.buildRequest(xml, TherapeuticLinkType.class);
   }

   public SelectGetHCPartyPatientConsentType buildSelectGetHCPartyPatientConsentType(String xml) throws TechnicalConnectorException {
      return (SelectGetHCPartyPatientConsentType)this.buildRequest(xml, SelectGetHCPartyPatientConsentType.class);
   }

   public AccessRightType buildAccessRightType(String xml) throws TechnicalConnectorException {
      return (AccessRightType)this.buildRequest(xml, AccessRightType.class);
   }

   public SelectRevokeAccessRightType buildSelectRevokeAccessRightType(String xml) throws TechnicalConnectorException {
      return (SelectRevokeAccessRightType)this.buildRequest(xml, SelectRevokeAccessRightType.class);
   }

   public SelectGetPatientAuditTrailType buildSelectGetPatientAuditTrailType(String xml) throws TechnicalConnectorException {
      return (SelectGetPatientAuditTrailType)this.buildRequest(xml, SelectGetPatientAuditTrailType.class);
   }
}
