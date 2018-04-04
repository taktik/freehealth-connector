package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.businessconnector.hub.builders.BuilderFactory;
import be.ehealth.businessconnector.hub.builders.RequestBuilderComplete;
import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubAccessRightService;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.hubservices.core.v1.AccessRightListType;
import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.TransactionAccessListType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;

public class IntraHubAccessRightServiceImpl extends IntraHubAbstract implements IntraHubAccessRightService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private RequestBuilderComplete builder;

   public IntraHubAccessRightServiceImpl(IntraHubService hubService, HubReplyValidator validator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      super(hubService, validator);
      this.builder = BuilderFactory.getInstance().getRequestBuilderComplete();
   }

   public IntraHubAccessRightServiceImpl() {
   }

   public void putAccessRight(AccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutAccessRightRequest request = this.builder.buildPutAccessRightRequest(accessRight);
      PutAccessRightResponse response = this.getService().putAccessRight(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public AccessRightListType getAccessRight(TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetAccessRightRequest request = this.builder.buildGetAccessRight(transaction);
      GetAccessRightResponse response = this.getService().getAccessRight(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getAccessrightlist();
   }

   public void revokeAccessRight(SelectRevokeAccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeAccessRightRequest request = this.builder.buildRevokeAccessRight(accessRight);
      RevokeAccessRightResponse response = this.getService().revokeAccessRight(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public TransactionAccessListType getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetPatientAuditTrailRequest request = this.builder.buildGetPatientAudiTrail(patientAuditTrail);
      GetPatientAuditTrailResponse response = this.getService().getPatientAuditTrail(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getTransactionaccesslist();
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(AccessRightListType.class);
      JaxbContextFactory.initJaxbContext(AccessRightType.class);
      JaxbContextFactory.initJaxbContext(GetAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(GetAccessRightResponse.class);
      JaxbContextFactory.initJaxbContext(GetPatientAuditTrailRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientAuditTrailResponse.class);
      JaxbContextFactory.initJaxbContext(PutAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(PutAccessRightResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeAccessRightResponse.class);
      JaxbContextFactory.initJaxbContext(SelectGetPatientAuditTrailType.class);
      JaxbContextFactory.initJaxbContext(SelectRevokeAccessRightType.class);
      JaxbContextFactory.initJaxbContext(TransactionAccessListType.class);
      JaxbContextFactory.initJaxbContext(TransactionIdType.class);
   }
}
