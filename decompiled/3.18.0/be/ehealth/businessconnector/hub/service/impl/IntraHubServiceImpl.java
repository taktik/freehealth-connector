package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.business.intrahubcommons.helper.ServiceHelper;
import be.ehealth.business.intrahubcommons.security.IntrahubEncryptionUtil;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.service.ServiceFactory;
import be.ehealth.businessconnector.hub.util.RequestTypeBuilder;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionResponse;
import java.net.MalformedURLException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntraHubServiceImpl implements IntraHubService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAPACTION_DECLARE_TRANSACTION = "urn:be:fgov:ehealth:interhub:protocol:v1:DeclareTransaction";
   private static final String SOAPACTION_PUT_TRANSACTION = "urn:be:fgov:ehealth:interhub:protocol:v1:PutTransaction";
   private static final String SOAPACTION_REVOKE_TRANSACTION = "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeTransaction";
   private static final String SOAPACTION_GET_TRANSACTIONLIST = "urn:be:fgov:ehealth:interhub:protocol:v1:GetTransactionList";
   private static final String SOAPACTION_GET_TRANSACTION = "urn:be:fgov:ehealth:interhub:protocol:v1:GetTransaction";
   private static final String SOAPACTION_REQUEST_PUBLICATION = "urn:be:fgov:ehealth:interhub:protocol:v1:RequestPublication";
   private static final String SOAPACTION_PUT_HCPARTY = "urn:be:fgov:ehealth:interhub:protocol:v1:PutHCParty";
   private static final String SOAPACTION_GET_HCPARTY = "urn:be:fgov:ehealth:interhub:protocol:v1:GetHCParty";
   private static final String SOAPACTION_PUT_PATIENT = "urn:be:fgov:ehealth:interhub:protocol:v1:PutPatient";
   private static final String SOAPACTION_GET_PATIENT = "urn:be:fgov:ehealth:interhub:protocol:v1:GetPatient";
   private static final String SOAPACTION_PUT_HCPARTYCONSENT = "urn:be:fgov:ehealth:interhub:protocol:v1:PutHCPartyConsent";
   private static final String SOAPACTION_GET_HCPARTYCONSENT = "urn:be:fgov:ehealth:interhub:protocol:v1:GetHCPartyConsent";
   private static final String SOAPACTION_REVOKE_HCPARTYCONSENT = "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeHCPartyConsent";
   private static final String SOAPACTION_PUT_PATIENTCONSENT = "urn:be:fgov:ehealth:interhub:protocol:v1:PutPatientConsent";
   private static final String SOAPACTION_GET_PATIENTCONSENT = "urn:be:fgov:ehealth:interhub:protocol:v1:GetPatientConsent";
   private static final String SOAPACTION_REVOKE_PATIENTCONSENT = "urn:be:fgov:ehealth:interhub:protocol:v1:RevokePatientConsent";
   private static final String SOAPACTION_PUT_THERAPEUTICLINK = "urn:be:fgov:ehealth:interhub:protocol:v1:PutTherapeuticLink";
   private static final String SOAPACTION_GET_THERAPEUTICLINK = "urn:be:fgov:ehealth:interhub:protocol:v1:GetTherapeuticLink";
   private static final String SOAPACTION_REVOKE_THERAPEUTICLINK = "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeTherapeuticLink";
   private static final String SOAPACTION_PUT_ACCESSRIGHT = "urn:be:fgov:ehealth:interhub:protocol:v1:PutAccessRight";
   private static final String SOAPACTION_GET_ACCESSRIGHT = "urn:be:fgov:ehealth:interhub:protocol:v1:GetAccessRight";
   private static final String SOAPACTION_REVOKE_ACCESSRIGHT = "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeAccessRight";
   private static final String SOAPACTION_GET_PATIENTAUDIT = "urn:be:fgov:ehealth:interhub:protocol:v1:GetPatientAuditTrail";
   private static final String PROP_HUBID = "hub.id";
   private static final String PROP_HUBAPPID = "hub.application";
   private static final Logger LOG = LoggerFactory.getLogger(IntraHubServiceImpl.class);

   public DeclareTransactionResponse declareTransaction(SAMLToken token, DeclareTransactionRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      try {
         request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:DeclareTransaction");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (DeclareTransactionResponse)genResp.asObject(DeclareTransactionResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutTransactionResponse putTransaction(SAMLToken token, PutTransactionRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      if (SessionUtil.getEncryptionCrypto() == null) {
         LOG.error("No Personal Crypto defined... Have you created a session that also loads your Personal eHealth Certificate?");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE, new Object[]{"Encryption for PutTransaction"});
      } else {
         request.setRequest(RequestTypeBuilder.init().addAuthorWithEncryptionInformation().build());
         PutTransactionRequest encryptedRequest = (PutTransactionRequest)IntrahubEncryptionUtil.encryptFolder(request, "hub.id", "hub.application");

         try {
            GenericRequest genReq = ServiceFactory.getIntraHubPortWithFolderEncryption(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutTransaction");
            genReq.setPayload((Object)encryptedRequest);
            GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
            return (PutTransactionResponse)genResp.asObject(PutTransactionResponse.class);
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
         } catch (WebServiceException var7) {
            throw ServiceHelper.handleWebServiceException(var7);
         } catch (MalformedURLException var8) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
         }
      }
   }

   public RevokeTransactionResponse revokeTransaction(SAMLToken token, RevokeTransactionRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeTransaction");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (RevokeTransactionResponse)genResp.asObject(RevokeTransactionResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetTransactionListResponse getTransactionList(SAMLToken token, GetTransactionListRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetTransactionList");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetTransactionListResponse)genResp.asObject(GetTransactionListResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetTransactionResponse getTransaction(SAMLToken token, GetTransactionRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      if (SessionUtil.getEncryptionCrypto() == null) {
         LOG.error("No Personal Crypto defined... Have you created a session that also loads your Personal eHealth Certificate?");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE, new Object[]{"Decryption for GetTransaction"});
      } else {
         LOG.debug("adding request with encryption id codes");
         request.setRequest(RequestTypeBuilder.init().addAuthorWithEncryptionInformation().build());
         GetTransactionRequest encryptedRequest = (GetTransactionRequest)IntrahubEncryptionUtil.encryptFolder(request, "hub.id", "hub.application");
         LOG.debug("Sending message with KMEHR headers to hub");

         try {
            GenericRequest genReq = ServiceFactory.getIntraHubPortWithFolderEncryption(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetTransaction");
            genReq.setPayload((Object)encryptedRequest);
            GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
            return (GetTransactionResponse)genResp.asObject(GetTransactionResponse.class);
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
         } catch (WebServiceException var7) {
            throw ServiceHelper.handleWebServiceException(var7);
         } catch (MalformedURLException var8) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
         }
      }
   }

   public RequestPublicationResponse requestPublication(SAMLToken token, RequestPublicationRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:RequestPublication");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (RequestPublicationResponse)genResp.asObject(RequestPublicationResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutHCPartyResponse putHCParty(SAMLToken token, PutHCPartyRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutHCParty");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (PutHCPartyResponse)genResp.asObject(PutHCPartyResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetHCPartyResponse getHCParty(SAMLToken token, GetHCPartyRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetHCParty");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetHCPartyResponse)genResp.asObject(GetHCPartyResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutPatientResponse putPatient(SAMLToken token, PutPatientRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutPatient");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (PutPatientResponse)genResp.asObject(PutPatientResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetPatientResponse getPatient(SAMLToken token, GetPatientRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetPatient");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetPatientResponse)genResp.asObject(GetPatientResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutHCPartyConsentResponse putHCPartyConsent(SAMLToken token, PutHCPartyConsentRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutHCPartyConsent");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (PutHCPartyConsentResponse)genResp.asObject(PutHCPartyConsentResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetHCPartyConsentResponse getHCPartyConsent(SAMLToken token, GetHCPartyConsentRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetHCPartyConsent");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetHCPartyConsentResponse)genResp.asObject(GetHCPartyConsentResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public RevokeHCPartyConsentResponse revokeHCPartyConsent(SAMLToken token, RevokeHCPartyConsentRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeHCPartyConsent");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (RevokeHCPartyConsentResponse)genResp.asObject(RevokeHCPartyConsentResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutPatientConsentResponse putPatientConsent(SAMLToken token, PutPatientConsentRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutPatientConsent");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (PutPatientConsentResponse)genResp.asObject(PutPatientConsentResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetPatientConsentResponse getPatientConsent(SAMLToken token, GetPatientConsentRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetPatientConsent");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetPatientConsentResponse)genResp.asObject(GetPatientConsentResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public RevokePatientConsentResponse revokePatientConsent(SAMLToken token, RevokePatientConsentRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:RevokePatientConsent");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (RevokePatientConsentResponse)genResp.asObject(RevokePatientConsentResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken token, PutTherapeuticLinkRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutTherapeuticLink");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (PutTherapeuticLinkResponse)genResp.asObject(PutTherapeuticLinkResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken token, GetTherapeuticLinkRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetTherapeuticLink");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetTherapeuticLinkResponse)genResp.asObject(GetTherapeuticLinkResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken token, RevokeTherapeuticLinkRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeTherapeuticLink");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (RevokeTherapeuticLinkResponse)genResp.asObject(RevokeTherapeuticLinkResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public PutAccessRightResponse putAccessRight(SAMLToken token, PutAccessRightRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:PutAccessRight");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (PutAccessRightResponse)genResp.asObject(PutAccessRightResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetAccessRightResponse getAccessRight(SAMLToken token, GetAccessRightRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetAccessRight");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetAccessRightResponse)genResp.asObject(GetAccessRightResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public RevokeAccessRightResponse revokeAccessRight(SAMLToken token, RevokeAccessRightRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:RevokeAccessRight");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (RevokeAccessRightResponse)genResp.asObject(RevokeAccessRightResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SAMLToken token, GetPatientAuditTrailRequest request) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      request.setRequest(RequestTypeBuilder.init().addGenericAuthor().build());

      try {
         GenericRequest genReq = ServiceFactory.getIntraHubPort(token, "urn:be:fgov:ehealth:interhub:protocol:v1:GetPatientAuditTrail");
         genReq.setPayload((Object)request);
         GenericResponse genResp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genReq);
         return (GetPatientAuditTrailResponse)genResp.asObject(GetPatientAuditTrailResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      } catch (WebServiceException var6) {
         throw ServiceHelper.handleWebServiceException(var6);
      } catch (MalformedURLException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(DeclareTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(DeclareTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(GetAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(GetAccessRightResponse.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyConsentResponse.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyRequest.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyResponse.class);
      JaxbContextFactory.initJaxbContext(GetPatientAuditTrailRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientAuditTrailResponse.class);
      JaxbContextFactory.initJaxbContext(GetPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(GetPatientRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientResponse.class);
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(GetTransactionListRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionListResponse.class);
      JaxbContextFactory.initJaxbContext(GetTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(PutAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(PutAccessRightResponse.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyConsentResponse.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyRequest.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyResponse.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(PutPatientRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientResponse.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(PutTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(PutTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(RequestPublicationRequest.class);
      JaxbContextFactory.initJaxbContext(RequestPublicationResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeAccessRightResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentResponse.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTransactionResponse.class);
   }
}
