package be.ehealth.businessconnector.dicsv5.session.impl;

import be.ehealth.businessconnector.dicsv5.exception.DicsException;
import be.ehealth.businessconnector.dicsv5.service.ServiceFactory;
import be.ehealth.businessconnector.dicsv5.session.DicsSessionService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.dics.protocol.v5.FindAmpRequest;
import be.fgov.ehealth.dics.protocol.v5.FindAmpResponse;
import be.fgov.ehealth.dics.protocol.v5.FindAmppRequest;
import be.fgov.ehealth.dics.protocol.v5.FindAmppResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCommentedClassificationRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCommentedClassificationResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCompanyRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCompanyResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingFormulaRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingFormulaResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingIngredientRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingIngredientResponse;
import be.fgov.ehealth.dics.protocol.v5.FindLegislationTextRequest;
import be.fgov.ehealth.dics.protocol.v5.FindLegislationTextResponse;
import be.fgov.ehealth.dics.protocol.v5.FindListOfAmpRequest;
import be.fgov.ehealth.dics.protocol.v5.FindListOfAmpResponse;
import be.fgov.ehealth.dics.protocol.v5.FindNonMedicinalProductRequest;
import be.fgov.ehealth.dics.protocol.v5.FindNonMedicinalProductResponse;
import be.fgov.ehealth.dics.protocol.v5.FindReferencesRequest;
import be.fgov.ehealth.dics.protocol.v5.FindReferencesResponse;
import be.fgov.ehealth.dics.protocol.v5.FindReimbursementRequest;
import be.fgov.ehealth.dics.protocol.v5.FindReimbursementResponse;
import be.fgov.ehealth.dics.protocol.v5.FindVmpGroupRequest;
import be.fgov.ehealth.dics.protocol.v5.FindVmpGroupResponse;
import be.fgov.ehealth.dics.protocol.v5.FindVmpRequest;
import be.fgov.ehealth.dics.protocol.v5.FindVmpResponse;
import be.fgov.ehealth.dics.protocol.v5.FindVtmRequest;
import be.fgov.ehealth.dics.protocol.v5.FindVtmResponse;
import be.fgov.ehealth.dics.protocol.v5.ValidateProductIdRequest;
import be.fgov.ehealth.dics.protocol.v5.ValidateProductIdResponse;
import be.fgov.ehealth.dics.protocol.v5.ValidateSamIdRequest;
import be.fgov.ehealth.dics.protocol.v5.ValidateSamIdResponse;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class DicsSessionServiceImpl implements DicsSessionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private SessionValidator sessionValidator;

   public DicsSessionServiceImpl(SessionValidator sessionValidator) {
      this.sessionValidator = sessionValidator;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(FindAmpRequest.class);
      JaxbContextFactory.initJaxbContext(FindAmpResponse.class);
      JaxbContextFactory.initJaxbContext(FindCompanyRequest.class);
      JaxbContextFactory.initJaxbContext(FindCompanyResponse.class);
      JaxbContextFactory.initJaxbContext(FindVmpRequest.class);
      JaxbContextFactory.initJaxbContext(FindVmpResponse.class);
      JaxbContextFactory.initJaxbContext(FindLegislationTextRequest.class);
      JaxbContextFactory.initJaxbContext(FindLegislationTextResponse.class);
      JaxbContextFactory.initJaxbContext(FindReimbursementRequest.class);
      JaxbContextFactory.initJaxbContext(FindReimbursementResponse.class);
      JaxbContextFactory.initJaxbContext(FindReferencesRequest.class);
      JaxbContextFactory.initJaxbContext(FindReferencesResponse.class);
      JaxbContextFactory.initJaxbContext(FindVmpGroupRequest.class);
      JaxbContextFactory.initJaxbContext(FindVmpGroupResponse.class);
      JaxbContextFactory.initJaxbContext(FindVtmRequest.class);
      JaxbContextFactory.initJaxbContext(FindVtmResponse.class);
      JaxbContextFactory.initJaxbContext(FindCommentedClassificationRequest.class);
      JaxbContextFactory.initJaxbContext(FindCommentedClassificationResponse.class);
      JaxbContextFactory.initJaxbContext(FindCompoundingIngredientRequest.class);
      JaxbContextFactory.initJaxbContext(FindCompoundingIngredientResponse.class);
      JaxbContextFactory.initJaxbContext(FindCompoundingFormulaRequest.class);
      JaxbContextFactory.initJaxbContext(FindCompoundingFormulaResponse.class);
      JaxbContextFactory.initJaxbContext(FindAmppRequest.class);
      JaxbContextFactory.initJaxbContext(FindAmppResponse.class);
      JaxbContextFactory.initJaxbContext(FindNonMedicinalProductRequest.class);
      JaxbContextFactory.initJaxbContext(FindNonMedicinalProductResponse.class);
      JaxbContextFactory.initJaxbContext(FindListOfAmpRequest.class);
      JaxbContextFactory.initJaxbContext(FindListOfAmpResponse.class);
      JaxbContextFactory.initJaxbContext(ValidateSamIdRequest.class);
      JaxbContextFactory.initJaxbContext(ValidateSamIdResponse.class);
      JaxbContextFactory.initJaxbContext(ValidateProductIdRequest.class);
      JaxbContextFactory.initJaxbContext(ValidateProductIdResponse.class);
   }

   public FindAmpResponse findAmp(FindAmpRequest request) throws TechnicalConnectorException, DicsException {
      return (FindAmpResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findAmp", FindAmpResponse.class);
   }

   public FindCompanyResponse findCompany(FindCompanyRequest request) throws TechnicalConnectorException, DicsException {
      return (FindCompanyResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findCompany", FindCompanyResponse.class);
   }

   public FindVmpResponse findVmp(FindVmpRequest request) throws TechnicalConnectorException, DicsException {
      return (FindVmpResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findVmp", FindVmpResponse.class);
   }

   public FindLegislationTextResponse findLegislationText(FindLegislationTextRequest request) throws TechnicalConnectorException, DicsException {
      return (FindLegislationTextResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findLegislationText", FindLegislationTextResponse.class);
   }

   public FindReimbursementResponse findReimbursement(FindReimbursementRequest request) throws TechnicalConnectorException, DicsException {
      return (FindReimbursementResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findReimbursement", FindReimbursementResponse.class);
   }

   public FindReferencesResponse findReferences(FindReferencesRequest request) throws TechnicalConnectorException, DicsException {
      return (FindReferencesResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findReferences", FindReferencesResponse.class);
   }

   public FindVmpGroupResponse findVmpGroup(FindVmpGroupRequest request) throws TechnicalConnectorException, DicsException {
      return (FindVmpGroupResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findVmpGroup", FindVmpGroupResponse.class);
   }

   public FindVtmResponse findVtm(FindVtmRequest request) throws TechnicalConnectorException, DicsException {
      return (FindVtmResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findVtm", FindVtmResponse.class);
   }

   public FindCommentedClassificationResponse findCommentedClassification(FindCommentedClassificationRequest request) throws TechnicalConnectorException, DicsException {
      return (FindCommentedClassificationResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findCommentedClassification", FindCommentedClassificationResponse.class);
   }

   public FindCompoundingIngredientResponse findIngredient(FindCompoundingIngredientRequest request) throws TechnicalConnectorException, DicsException {
      return (FindCompoundingIngredientResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findIngredient", FindCompoundingIngredientResponse.class);
   }

   public FindCompoundingFormulaResponse findFormula(FindCompoundingFormulaRequest request) throws TechnicalConnectorException, DicsException {
      return (FindCompoundingFormulaResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findFormula", FindCompoundingFormulaResponse.class);
   }

   public FindAmppResponse findAmpp(FindAmppRequest request) throws TechnicalConnectorException, DicsException {
      return (FindAmppResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findAmpp", FindAmppResponse.class);
   }

   public FindNonMedicinalProductResponse findNonMedicinalProduct(FindNonMedicinalProductRequest request) throws TechnicalConnectorException, DicsException {
      return (FindNonMedicinalProductResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findNonMedicinalProduct", FindNonMedicinalProductResponse.class);
   }

   public FindListOfAmpResponse findListOfAmp(FindListOfAmpRequest request) throws TechnicalConnectorException, DicsException {
      return (FindListOfAmpResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:findListOfAmp", FindListOfAmpResponse.class);
   }

   public ValidateSamIdResponse validateSamId(ValidateSamIdRequest request) throws TechnicalConnectorException, DicsException {
      return (ValidateSamIdResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:validateSamId", ValidateSamIdResponse.class);
   }

   public ValidateProductIdResponse validateProductId(ValidateProductIdRequest request) throws TechnicalConnectorException, DicsException {
      return (ValidateProductIdResponse)this.executeOperation(request, "urn:be:fgov:ehealth:dics:protocol:v5:validateProductId", ValidateProductIdResponse.class);
   }

   private <T extends ResponseType> T executeOperation(Object request, String soapAction, Class<T> clazz) throws DicsException, TechnicalConnectorException {
      try {
         SAMLToken samlToken = Session.getInstance().getSession().getSAMLToken();
         this.sessionValidator.validateToken(samlToken);
         GenericRequest service = ServiceFactory.getDicsService(samlToken, soapAction);
         service.setPayload(request);
         GenericResponse wsResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         return (ResponseType)wsResponse.asObject(clazz);
      } catch (SOAPFaultException var7) {
         throw new DicsException(var7.getFault());
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }
}
