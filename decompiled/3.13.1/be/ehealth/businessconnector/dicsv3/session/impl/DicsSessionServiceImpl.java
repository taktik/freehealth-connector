package be.ehealth.businessconnector.dicsv3.session.impl;

import be.ehealth.businessconnector.dicsv3.service.ServiceFactory;
import be.ehealth.businessconnector.dicsv3.session.DicsSessionService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.dics.protocol.v3.FindAmpRequest;
import be.fgov.ehealth.dics.protocol.v3.FindAmpResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCommentedClassificationRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCommentedClassificationResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCompanyRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCompanyResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingFormulaRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingFormulaResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingIngredientRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingIngredientResponse;
import be.fgov.ehealth.dics.protocol.v3.FindLegislationTextRequest;
import be.fgov.ehealth.dics.protocol.v3.FindLegislationTextResponse;
import be.fgov.ehealth.dics.protocol.v3.FindReferencesRequest;
import be.fgov.ehealth.dics.protocol.v3.FindReferencesResponse;
import be.fgov.ehealth.dics.protocol.v3.FindReimbursementRequest;
import be.fgov.ehealth.dics.protocol.v3.FindReimbursementResponse;
import be.fgov.ehealth.dics.protocol.v3.FindVmpGroupRequest;
import be.fgov.ehealth.dics.protocol.v3.FindVmpGroupResponse;
import be.fgov.ehealth.dics.protocol.v3.FindVmpRequest;
import be.fgov.ehealth.dics.protocol.v3.FindVmpResponse;
import be.fgov.ehealth.dics.protocol.v3.FindVtmRequest;
import be.fgov.ehealth.dics.protocol.v3.FindVtmResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DicsSessionServiceImpl implements DicsSessionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private SessionValidator sessionValidator;
   private static final Logger LOG = LoggerFactory.getLogger(DicsSessionServiceImpl.class);

   public DicsSessionServiceImpl(SessionValidator sessionValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public DicsSessionServiceImpl() {
      LOG.debug("creating DicsSessionServiceImpl for bootstrapping purposes");
   }

   public FindAmpResponse findAmp(FindAmpRequest findAmpRequest) throws TechnicalConnectorException {
      return (FindAmpResponse)this.executeOperation(findAmpRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findAmp", FindAmpResponse.class);
   }

   public FindCompanyResponse findCompany(FindCompanyRequest findCompanyRequest) throws TechnicalConnectorException {
      return (FindCompanyResponse)this.executeOperation(findCompanyRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findCompany", FindCompanyResponse.class);
   }

   public FindLegislationTextResponse findLegislationText(FindLegislationTextRequest findLegislationTextRequest) throws TechnicalConnectorException {
      return (FindLegislationTextResponse)this.executeOperation(findLegislationTextRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findLegislationText", FindLegislationTextResponse.class);
   }

   public FindReferencesResponse findReferences(FindReferencesRequest findReferencesRequest) throws TechnicalConnectorException {
      return (FindReferencesResponse)this.executeOperation(findReferencesRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findReferences", FindReferencesResponse.class);
   }

   public FindReimbursementResponse findReimbursement(FindReimbursementRequest findReimbursementRequest) throws TechnicalConnectorException {
      return (FindReimbursementResponse)this.executeOperation(findReimbursementRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findReimbursement", FindReimbursementResponse.class);
   }

   public FindVmpResponse findVmp(FindVmpRequest findVmpRequest) throws TechnicalConnectorException {
      return (FindVmpResponse)this.executeOperation(findVmpRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findVmpRequest", FindVmpResponse.class);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(FindAmpRequest.class);
      JaxbContextFactory.initJaxbContext(FindAmpResponse.class);
      JaxbContextFactory.initJaxbContext(FindReferencesRequest.class);
      JaxbContextFactory.initJaxbContext(FindReferencesResponse.class);
      JaxbContextFactory.initJaxbContext(FindVmpRequest.class);
      JaxbContextFactory.initJaxbContext(FindVmpResponse.class);
      JaxbContextFactory.initJaxbContext(FindReimbursementRequest.class);
      JaxbContextFactory.initJaxbContext(FindReimbursementResponse.class);
      JaxbContextFactory.initJaxbContext(FindLegislationTextRequest.class);
      JaxbContextFactory.initJaxbContext(FindLegislationTextResponse.class);
      JaxbContextFactory.initJaxbContext(FindCompanyRequest.class);
      JaxbContextFactory.initJaxbContext(FindCompanyResponse.class);
   }

   private <T> T executeOperation(Object request, String operation, Class<T> clazz) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getDicsService(Session.getInstance().getSession().getSAMLToken(), operation);
         service.setPayload(request);
         return be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public FindCommentedClassificationResponse findCommentedClassification(FindCommentedClassificationRequest findCommentedClassificationRequest) throws TechnicalConnectorException {
      return (FindCommentedClassificationResponse)this.executeOperation(findCommentedClassificationRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findCommentedClassificationRequest", FindCommentedClassificationResponse.class);
   }

   public FindCompoundingFormulaResponse findFormula(FindCompoundingFormulaRequest findCompoundingFormulaRequest) throws TechnicalConnectorException {
      return (FindCompoundingFormulaResponse)this.executeOperation(findCompoundingFormulaRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findCommentedClassificationRequest", FindCompoundingFormulaResponse.class);
   }

   public FindCompoundingIngredientResponse findIngredient(FindCompoundingIngredientRequest findCompoundingIngredientRequest) throws TechnicalConnectorException {
      return (FindCompoundingIngredientResponse)this.executeOperation(findCompoundingIngredientRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findCommentedClassificationRequest", FindCompoundingIngredientResponse.class);
   }

   public FindVmpGroupResponse findVmpGroup(FindVmpGroupRequest findVmpGroupRequest) throws TechnicalConnectorException {
      return (FindVmpGroupResponse)this.executeOperation(findVmpGroupRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findCommentedClassificationRequest", FindVmpGroupResponse.class);
   }

   public FindVtmResponse findVtm(FindVtmRequest findVtmRequest) throws TechnicalConnectorException {
      return (FindVtmResponse)this.executeOperation(findVtmRequest, "urn:be:fgov:ehealth:dics:protocol:v3:findCommentedClassificationRequest", FindVtmResponse.class);
   }
}
