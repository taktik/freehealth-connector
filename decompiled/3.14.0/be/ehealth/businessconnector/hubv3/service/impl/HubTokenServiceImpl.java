package be.ehealth.businessconnector.hubv3.service.impl;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.business.intrahubcommons.helper.ServiceHelper;
import be.ehealth.business.intrahubcommons.security.IntrahubEncryptionUtil;
import be.ehealth.businessconnector.hubv3.service.HubTokenService;
import be.ehealth.businessconnector.hubv3.service.ServiceFactory;
import be.ehealth.businessconnector.hubv3.util.RequestTypeBuilder;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateRequest;
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetResponse;
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v3.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse;
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v3.RequestType;
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionResponse;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HubTokenServiceImpl implements HubTokenService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAPACTION_DECLARE_TRANSACTION = "urn:be:fgov:ehealth:intrahub:protocol:v3:DeclareTransaction";
   private static final String SOAPACTION_PUT_TRANSACTION = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransaction";
   private static final String SOAPACTION_PUT_TRANSACTIONSET = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransactionSet";
   private static final String SOAPACTION_REVOKE_TRANSACTION = "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTransaction";
   private static final String SOAPACTION_GET_TRANSACTIONLIST = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionList";
   private static final String SOAPACTION_GET_TRANSACTION = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransaction";
   private static final String SOAPACTION_GET_TRANSACTIONSET = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionSet";
   private static final String SOAPACTION_REQUEST_PUBLICATION = "urn:be:fgov:ehealth:intrahub:protocol:v3:RequestPublication";
   private static final String SOAPACTION_PUT_HCPARTY = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCParty";
   private static final String SOAPACTION_GET_HCPARTY = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCParty";
   private static final String SOAPACTION_PUT_PATIENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatient";
   private static final String SOAPACTION_GET_PATIENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatient";
   private static final String SOAPACTION_PUT_HCPARTYCONSENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCPartyConsent";
   private static final String SOAPACTION_GET_HCPARTYCONSENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCPartyConsent";
   private static final String SOAPACTION_REVOKE_HCPARTYCONSENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeHCPartyConsent";
   private static final String SOAPACTION_PUT_PATIENTCONSENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatientConsent";
   private static final String SOAPACTION_GET_PATIENTCONSENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientConsent";
   private static final String SOAPACTION_REVOKE_PATIENTCONSENT = "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokePatientConsent";
   private static final String SOAPACTION_PUT_THERAPEUTICLINK = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTherapeuticLink";
   private static final String SOAPACTION_GET_THERAPEUTICLINK = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTherapeuticLink";
   private static final String SOAPACTION_REVOKE_THERAPEUTICLINK = "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTherapeuticLink";
   private static final String SOAPACTION_PUT_ACCESSRIGHT = "urn:be:fgov:ehealth:intrahub:protocol:v3:PutAccessRight";
   private static final String SOAPACTION_GET_ACCESSRIGHT = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetAccessRight";
   private static final String SOAPACTION_REVOKE_ACCESSRIGHT = "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeAccessRight";
   private static final String SOAPACTION_GET_PATIENTAUDIT = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientAuditTrail";
   private static final String SOAPACTION_GET_LASTUPDATE = "urn:be:fgov:ehealth:intrahub:protocol:v3:GetLatestUpdate";
   private static final Logger LOG = LoggerFactory.getLogger(HubTokenServiceImpl.class);

   public DeclareTransactionResponse declareTransaction(SAMLToken token, DeclareTransactionRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (DeclareTransactionResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:DeclareTransaction", DeclareTransactionResponse.class);
   }

   public PutTransactionResponse putTransaction(SAMLToken token, PutTransactionRequest request) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.setEncryptedRequestType());
      MarshallerHelper<PutTransactionRequest, PutTransactionRequest> helper = new MarshallerHelper(PutTransactionRequest.class, PutTransactionRequest.class);
      LOG.debug("PutTransactionRequest unsigned request :" + helper.toString(request));
      return (PutTransactionResponse)this.executeOperation(IntrahubEncryptionUtil.encryptFolder(request, "hubv3.id", "hubv3.application"), "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransaction", PutTransactionResponse.class);
   }

   private RequestType setEncryptedRequestType() throws TechnicalConnectorException {
      return RequestTypeBuilder.init().addAuthorWithEncryptionInformation().build();
   }

   public RevokeTransactionResponse revokeTransaction(SAMLToken token, RevokeTransactionRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (RevokeTransactionResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTransaction", RevokeTransactionResponse.class);
   }

   public GetTransactionListResponse getTransactionList(SAMLToken token, GetTransactionListRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetTransactionListResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionList", GetTransactionListResponse.class);
   }

   public GetTransactionResponse getTransaction(SAMLToken token, GetTransactionRequest request) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.setEncryptedRequestType());
      GetTransactionRequest encryptedRequest = (GetTransactionRequest)IntrahubEncryptionUtil.encryptFolder(request, "hubv3.id", "hubv3.application");
      return (GetTransactionResponse)this.executeOperation(encryptedRequest, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransaction", GetTransactionResponse.class);
   }

   public RequestPublicationResponse requestPublication(SAMLToken token, RequestPublicationRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (RequestPublicationResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RequestPublication", RequestPublicationResponse.class);
   }

   public PutHCPartyResponse putHCParty(SAMLToken token, PutHCPartyRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (PutHCPartyResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCParty", PutHCPartyResponse.class);
   }

   public GetHCPartyResponse getHCParty(SAMLToken token, GetHCPartyRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetHCPartyResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCParty", GetHCPartyResponse.class);
   }

   public PutPatientResponse putPatient(SAMLToken token, PutPatientRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (PutPatientResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatient", PutPatientResponse.class);
   }

   public GetPatientResponse getPatient(SAMLToken token, GetPatientRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetPatientResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatient", GetPatientResponse.class);
   }

   public PutHCPartyConsentResponse putHCPartyConsent(SAMLToken token, PutHCPartyConsentRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (PutHCPartyConsentResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCPartyConsent", PutHCPartyConsentResponse.class);
   }

   public GetHCPartyConsentResponse getHCPartyConsent(SAMLToken token, GetHCPartyConsentRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetHCPartyConsentResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCPartyConsent", GetHCPartyConsentResponse.class);
   }

   public RevokeHCPartyConsentResponse revokeHCPartyConsent(SAMLToken token, RevokeHCPartyConsentRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (RevokeHCPartyConsentResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeHCPartyConsent", RevokeHCPartyConsentResponse.class);
   }

   public PutPatientConsentResponse putPatientConsent(SAMLToken token, PutPatientConsentRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (PutPatientConsentResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatientConsent", PutPatientConsentResponse.class);
   }

   public GetPatientConsentResponse getPatientConsent(SAMLToken token, GetPatientConsentRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetPatientConsentResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientConsent", GetPatientConsentResponse.class);
   }

   public RevokePatientConsentResponse revokePatientConsent(SAMLToken token, RevokePatientConsentRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (RevokePatientConsentResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokePatientConsent", RevokePatientConsentResponse.class);
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken token, PutTherapeuticLinkRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (PutTherapeuticLinkResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTherapeuticLink", PutTherapeuticLinkResponse.class);
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken token, GetTherapeuticLinkRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetTherapeuticLinkResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTherapeuticLink", GetTherapeuticLinkResponse.class);
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken token, RevokeTherapeuticLinkRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (RevokeTherapeuticLinkResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTherapeuticLink", RevokeTherapeuticLinkResponse.class);
   }

   public PutAccessRightResponse putAccessRight(SAMLToken token, PutAccessRightRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (PutAccessRightResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutAccessRight", PutAccessRightResponse.class);
   }

   public GetAccessRightResponse getAccessRight(SAMLToken token, GetAccessRightRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetAccessRightResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetAccessRight", GetAccessRightResponse.class);
   }

   public RevokeAccessRightResponse revokeAccessRight(SAMLToken token, RevokeAccessRightRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (RevokeAccessRightResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeAccessRight", RevokeAccessRightResponse.class);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SAMLToken token, GetPatientAuditTrailRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetPatientAuditTrailResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientAuditTrail", GetPatientAuditTrailResponse.class);
   }

   private RequestType buildKmehrRequest() throws TechnicalConnectorException {
      return RequestTypeBuilder.init().addGenericAuthor().build();
   }

   public PutTransactionSetResponse putTransactionSet(SAMLToken token, PutTransactionSetRequest request) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.setEncryptedRequestType());
      return (PutTransactionSetResponse)this.executeOperation(IntrahubEncryptionUtil.encryptFolder(request, "hubv3.id", "hubv3.application"), "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransactionSet", PutTransactionSetResponse.class);
   }

   public GetTransactionSetResponse getTransactionSet(SAMLToken token, GetTransactionSetRequest request) throws TechnicalConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.setEncryptedRequestType());
      return (GetTransactionSetResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionSet", GetTransactionSetResponse.class);
   }

   public GetLatestUpdateResponse getLatestUpdate(SAMLToken token, GetLatestUpdateRequest request) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest());
      return (GetLatestUpdateResponse)this.executeOperation(request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetLatestUpdate", GetLatestUpdateResponse.class);
   }

   private <T> T executeOperation(Object request, String operation, Class<T> clazz) throws TechnicalConnectorException {
      try {
         SAMLToken token = Session.getInstance().getSession().getSAMLToken();
         GenericRequest service = ServiceFactory.getIntraHubPort(token, operation).setPayload(request);
         return be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      } catch (WebServiceException var7) {
         throw ServiceHelper.handleWebServiceException(var7);
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
      JaxbContextFactory.initJaxbContext(GetLatestUpdateRequest.class);
      JaxbContextFactory.initJaxbContext(GetLatestUpdateResponse.class);
   }

   private void checkSessionCertificateExists() throws TechnicalConnectorException {
      if (SessionUtil.getEncryptionCrypto() == null) {
         LOG.error("No Personal Crypto defined... Have you created a session that also loads your Personal eHealth Certificate?");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE, new Object[0]);
      }
   }
}
