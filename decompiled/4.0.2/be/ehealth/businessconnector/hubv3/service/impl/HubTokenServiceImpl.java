package be.ehealth.businessconnector.hubv3.service.impl;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.business.intrahubcommons.helper.ServiceHelper;
import be.ehealth.business.intrahubcommons.security.IntrahubEncryptionUtil;
import be.ehealth.businessconnector.hubv3.service.HubTokenService;
import be.ehealth.businessconnector.hubv3.service.ServiceFactory;
import be.ehealth.businessconnector.hubv3.util.RequestListTypeBuilder;
import be.ehealth.businessconnector.hubv3.util.RequestTypeBuilder;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
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
import be.fgov.ehealth.hubservices.core.v3.Paginationrequestinfo;
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

   public HubTokenServiceImpl() {
   }

   public DeclareTransactionResponse declareTransaction(SAMLToken token, DeclareTransactionRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (DeclareTransactionResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:DeclareTransaction", DeclareTransactionResponse.class);
   }

   public PutTransactionResponse putTransaction(SAMLToken token, PutTransactionRequest request, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.buildKmehrRequestWithAuthorEncryptionInfo(breakTheGlass));
      MarshallerHelper<PutTransactionRequest, PutTransactionRequest> helper = new MarshallerHelper(PutTransactionRequest.class, PutTransactionRequest.class);
      LOG.debug("PutTransactionRequest unsigned request :" + helper.toString(request));
      return (PutTransactionResponse)this.executeOperation(token, IntrahubEncryptionUtil.encryptFolder(request, "hubv3.id", "hubv3.application"), "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransaction", PutTransactionResponse.class);
   }

   public RevokeTransactionResponse revokeTransaction(SAMLToken token, RevokeTransactionRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (RevokeTransactionResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTransaction", RevokeTransactionResponse.class);
   }

   public GetTransactionListResponse getTransactionList(SAMLToken token, GetTransactionListRequest request, String breakTheGlass) throws TechnicalConnectorException {
      return this.getTransactionList(token, request, (Paginationrequestinfo)null, breakTheGlass);
   }

   public GetTransactionListResponse getTransactionList(SAMLToken token, GetTransactionListRequest request, Paginationrequestinfo pagReqInfo, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(RequestListTypeBuilder.init().addGenericAuthor().addPaginationInfo(pagReqInfo).addBreakTheGlass(breakTheGlass).build());
      return (GetTransactionListResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionList", GetTransactionListResponse.class);
   }

   public GetTransactionResponse getTransaction(SAMLToken token, GetTransactionRequest request, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.buildKmehrRequestWithAuthorEncryptionInfo(breakTheGlass));
      GetTransactionRequest encryptedRequest = (GetTransactionRequest)IntrahubEncryptionUtil.encryptFolder(request, "hubv3.id", "hubv3.application");
      return (GetTransactionResponse)this.executeOperation(token, encryptedRequest, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransaction", GetTransactionResponse.class);
   }

   public RequestPublicationResponse requestPublication(SAMLToken token, RequestPublicationRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (RequestPublicationResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RequestPublication", RequestPublicationResponse.class);
   }

   public PutHCPartyResponse putHCParty(SAMLToken token, PutHCPartyRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (PutHCPartyResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCParty", PutHCPartyResponse.class);
   }

   public GetHCPartyResponse getHCParty(SAMLToken token, GetHCPartyRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetHCPartyResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCParty", GetHCPartyResponse.class);
   }

   public PutPatientResponse putPatient(SAMLToken token, PutPatientRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (PutPatientResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatient", PutPatientResponse.class);
   }

   public GetPatientResponse getPatient(SAMLToken token, GetPatientRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetPatientResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatient", GetPatientResponse.class);
   }

   public PutHCPartyConsentResponse putHCPartyConsent(SAMLToken token, PutHCPartyConsentRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (PutHCPartyConsentResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCPartyConsent", PutHCPartyConsentResponse.class);
   }

   public GetHCPartyConsentResponse getHCPartyConsent(SAMLToken token, GetHCPartyConsentRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetHCPartyConsentResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCPartyConsent", GetHCPartyConsentResponse.class);
   }

   public RevokeHCPartyConsentResponse revokeHCPartyConsent(SAMLToken token, RevokeHCPartyConsentRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (RevokeHCPartyConsentResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeHCPartyConsent", RevokeHCPartyConsentResponse.class);
   }

   public PutPatientConsentResponse putPatientConsent(SAMLToken token, PutPatientConsentRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (PutPatientConsentResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatientConsent", PutPatientConsentResponse.class);
   }

   public GetPatientConsentResponse getPatientConsent(SAMLToken token, GetPatientConsentRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetPatientConsentResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientConsent", GetPatientConsentResponse.class);
   }

   public RevokePatientConsentResponse revokePatientConsent(SAMLToken token, RevokePatientConsentRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (RevokePatientConsentResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokePatientConsent", RevokePatientConsentResponse.class);
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken token, PutTherapeuticLinkRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (PutTherapeuticLinkResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTherapeuticLink", PutTherapeuticLinkResponse.class);
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken token, GetTherapeuticLinkRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetTherapeuticLinkResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTherapeuticLink", GetTherapeuticLinkResponse.class);
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken token, RevokeTherapeuticLinkRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (RevokeTherapeuticLinkResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTherapeuticLink", RevokeTherapeuticLinkResponse.class);
   }

   public PutAccessRightResponse putAccessRight(SAMLToken token, PutAccessRightRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (PutAccessRightResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:PutAccessRight", PutAccessRightResponse.class);
   }

   public GetAccessRightResponse getAccessRight(SAMLToken token, GetAccessRightRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetAccessRightResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetAccessRight", GetAccessRightResponse.class);
   }

   public RevokeAccessRightResponse revokeAccessRight(SAMLToken token, RevokeAccessRightRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (RevokeAccessRightResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeAccessRight", RevokeAccessRightResponse.class);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SAMLToken token, GetPatientAuditTrailRequest request, String breakTheGlass) throws TechnicalConnectorException {
      return this.getPatientAuditTrail(token, request, (Paginationrequestinfo)null, breakTheGlass);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SAMLToken token, GetPatientAuditTrailRequest request, Paginationrequestinfo paginationrequestinfo, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(RequestListTypeBuilder.init().addGenericAuthor().addPaginationInfo(paginationrequestinfo).addBreakTheGlass(breakTheGlass).build());
      return (GetPatientAuditTrailResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientAuditTrail", GetPatientAuditTrailResponse.class);
   }

   public PutTransactionSetResponse putTransactionSet(SAMLToken token, PutTransactionSetRequest request, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.buildKmehrRequestWithAuthorEncryptionInfo(breakTheGlass));
      return (PutTransactionSetResponse)this.executeOperation(token, IntrahubEncryptionUtil.encryptFolder(request, "hubv3.id", "hubv3.application"), "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransactionSet", PutTransactionSetResponse.class);
   }

   public GetTransactionSetResponse getTransactionSet(SAMLToken token, GetTransactionSetRequest request, String breakTheGlass) throws TechnicalConnectorException {
      this.checkSessionCertificateExists();
      request.setRequest(this.buildKmehrRequestWithAuthorEncryptionInfo(breakTheGlass));
      return (GetTransactionSetResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionSet", GetTransactionSetResponse.class);
   }

   public GetLatestUpdateResponse getLatestUpdate(SAMLToken token, GetLatestUpdateRequest request, String breakTheGlass) throws TechnicalConnectorException {
      request.setRequest(this.buildKmehrRequest(breakTheGlass));
      return (GetLatestUpdateResponse)this.executeOperation(token, request, "urn:be:fgov:ehealth:intrahub:protocol:v3:GetLatestUpdate", GetLatestUpdateResponse.class);
   }

   private RequestType buildKmehrRequestWithAuthorEncryptionInfo(String breakTheGlass) throws TechnicalConnectorException {
      return RequestTypeBuilder.init().addAuthorWithEncryptionInformation().addBreakTheGlass(breakTheGlass).build();
   }

   private RequestType buildKmehrRequest(String breakTheGlass) throws TechnicalConnectorException {
      return RequestTypeBuilder.init().addGenericAuthor().addBreakTheGlass(breakTheGlass).build();
   }

   private <T> T executeOperation(SAMLToken token, Object request, String operation, Class<T> clazz) throws TechnicalConnectorException {
      try {
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
      JaxbContextFactory.initJaxbContext(GetTransactionSetRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionSetResponse.class);
   }

   private void checkSessionCertificateExists() throws TechnicalConnectorException {
      if (SessionUtil.getEncryptionCrypto() == null) {
         LOG.error("No Personal Crypto defined... Have you created a session that also loads your Personal eHealth Certificate?");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE, new Object[0]);
      }
   }
}
