package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.businessconnector.hub.builders.BuilderFactory;
import be.ehealth.businessconnector.hub.builders.RequestBuilderComplete;
import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.service.IntraHubTransactionService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderGetTransactionList;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.util.List;

public class IntraHubTransactionServiceImpl extends IntraHubAbstract implements IntraHubTransactionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private RequestBuilderComplete builder;

   public IntraHubTransactionServiceImpl(IntraHubService hubService, HubReplyValidator validator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      super(hubService, validator);
      this.builder = BuilderFactory.getInstance().getRequestBuilderComplete();
   }

   public IntraHubTransactionServiceImpl() {
   }

   public List<IDKMEHR> declareTransaction(KmehrHeaderDeclareTransaction input) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      DeclareTransactionRequest request = this.builder.buildDeclareTransactionRequest(input);
      DeclareTransactionResponse response = this.getService().declareTransaction(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getTransaction().getIds();
   }

   public void putTransaction(Kmehrmessage input) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutTransactionRequest request = this.builder.buildPutTransactionRequest(input);
      PutTransactionResponse response = this.getService().putTransaction(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public void revokeTransaction(PatientIdType patient, TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeTransactionRequest request = this.builder.buildRevokeTransactionRequest(patient, transaction);
      RevokeTransactionResponse response = this.getService().revokeTransaction(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public KmehrHeaderGetTransactionList getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetTransactionListRequest request = this.builder.buildGetTransactionListRequest(patient, searchType, transaction);
      GetTransactionListResponse response = this.getService().getTransactionList(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getKmehrheader();
   }

   public Kmehrmessage getTransaction(PatientIdType patient, TransactionBaseType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetTransactionRequest request = this.builder.buildGetTransactionRequest(patient, transaction);
      GetTransactionResponse response = this.getService().getTransaction(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getKmehrmessage();
   }

   public void requestPublication(PatientIdType patient, TransactionWithPeriodType transaction, String comment) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RequestPublicationRequest request = this.builder.buildRequestPublicationRequest(patient, transaction, comment);
      RequestPublicationResponse response = this.getService().requestPublication(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(DeclareTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(DeclareTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(GetTransactionListRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionListResponse.class);
      JaxbContextFactory.initJaxbContext(GetTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(KmehrHeaderDeclareTransaction.class);
      JaxbContextFactory.initJaxbContext(KmehrHeaderGetTransactionList.class);
      JaxbContextFactory.initJaxbContext(PutTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(PutTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(RequestPublicationRequest.class);
      JaxbContextFactory.initJaxbContext(RequestPublicationResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(TransactionBaseType.class);
      JaxbContextFactory.initJaxbContext(TransactionIdType.class);
      JaxbContextFactory.initJaxbContext(TransactionWithPeriodType.class);
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
   }
}
