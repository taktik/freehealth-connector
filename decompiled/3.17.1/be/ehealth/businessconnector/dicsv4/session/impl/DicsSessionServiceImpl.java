package be.ehealth.businessconnector.dicsv4.session.impl;

import be.ehealth.business.common.helper.EhealthServiceHelper;
import be.ehealth.businessconnector.dicsv4.exception.DicsDataNotFoundException;
import be.ehealth.businessconnector.dicsv4.exception.DicsException;
import be.ehealth.businessconnector.dicsv4.service.ServiceFactory;
import be.ehealth.businessconnector.dicsv4.session.DicsSessionService;
import be.ehealth.businessconnector.dicsv4.validator.DicsValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.dics.protocol.v4.FindAmpRequest;
import be.fgov.ehealth.dics.protocol.v4.FindAmpResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCommentedClassificationRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCommentedClassificationResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCompanyRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCompanyResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingFormulaRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingFormulaResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingIngredientRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingIngredientResponse;
import be.fgov.ehealth.dics.protocol.v4.FindLegislationTextRequest;
import be.fgov.ehealth.dics.protocol.v4.FindLegislationTextResponse;
import be.fgov.ehealth.dics.protocol.v4.FindReferencesRequest;
import be.fgov.ehealth.dics.protocol.v4.FindReferencesResponse;
import be.fgov.ehealth.dics.protocol.v4.FindReimbursementRequest;
import be.fgov.ehealth.dics.protocol.v4.FindReimbursementResponse;
import be.fgov.ehealth.dics.protocol.v4.FindVmpGroupRequest;
import be.fgov.ehealth.dics.protocol.v4.FindVmpGroupResponse;
import be.fgov.ehealth.dics.protocol.v4.FindVmpRequest;
import be.fgov.ehealth.dics.protocol.v4.FindVmpResponse;
import be.fgov.ehealth.dics.protocol.v4.FindVtmRequest;
import be.fgov.ehealth.dics.protocol.v4.FindVtmResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductPackagesRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductPackagesResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductsRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductsResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVirtualMedicinalProductsRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVirtualMedicinalProductsResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVmpGroupsRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVmpGroupsResponse;
import be.fgov.ehealth.dics.protocol.v4.ListConsultationRequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DicsSessionServiceImpl implements DicsSessionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;
   private DicsValidator dicsValidator;
   private static final Logger LOG = LoggerFactory.getLogger(DicsSessionServiceImpl.class);

   public DicsSessionServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator, DicsValidator dicsValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
      this.dicsValidator = dicsValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public DicsSessionServiceImpl() {
      LOG.debug("creating DicsSessionServiceImpl for bootstrapping purposes");
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
      JaxbContextFactory.initJaxbContext(ListConsultationRequestType.class);
      JaxbContextFactory.initJaxbContext(FindCompanyResponse.class);
      JaxbContextFactory.initJaxbContext(FindCompanyRequest.class);
      JaxbContextFactory.initJaxbContext(FindCompanyResponse.class);
   }

   public FindAmpResponse findAmp(FindAmpRequest findAmpRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindAmpResponse)this.executeOperation(findAmpRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findAmp", FindAmpResponse.class);
   }

   public FindCompanyResponse findCompany(FindCompanyRequest findCompanyRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindCompanyResponse)this.executeOperation(findCompanyRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCompany", FindCompanyResponse.class);
   }

   public FindLegislationTextResponse findLegislationText(FindLegislationTextRequest findLegislationTextRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindLegislationTextResponse)this.executeOperation(findLegislationTextRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findLegislationText", FindLegislationTextResponse.class);
   }

   public FindReferencesResponse findReferences(FindReferencesRequest findReferencesRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindReferencesResponse)this.executeOperation(findReferencesRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findReferences", FindReferencesResponse.class);
   }

   public FindReimbursementResponse findReimbursement(FindReimbursementRequest findReimbursementRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindReimbursementResponse)this.executeOperation(findReimbursementRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findReimbursement", FindReimbursementResponse.class);
   }

   public FindVmpResponse findVmp(FindVmpRequest findVmpRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindVmpResponse)this.executeOperation(findVmpRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findVmpRequest", FindVmpResponse.class);
   }

   public FindCommentedClassificationResponse findCommentedClassification(FindCommentedClassificationRequest findCommentedClassificationRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindCommentedClassificationResponse)this.executeOperation(findCommentedClassificationRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCommentedClassificationRequest", FindCommentedClassificationResponse.class);
   }

   public FindCompoundingFormulaResponse findFormula(FindCompoundingFormulaRequest findCompoundingFormulaRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindCompoundingFormulaResponse)this.executeOperation(findCompoundingFormulaRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCommentedClassificationRequest", FindCompoundingFormulaResponse.class);
   }

   public FindCompoundingIngredientResponse findIngredient(FindCompoundingIngredientRequest findCompoundingIngredientRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindCompoundingIngredientResponse)this.executeOperation(findCompoundingIngredientRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCommentedClassificationRequest", FindCompoundingIngredientResponse.class);
   }

   public FindVmpGroupResponse findVmpGroup(FindVmpGroupRequest findVmpGroupRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindVmpGroupResponse)this.executeOperation(findVmpGroupRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCommentedClassificationRequest", FindVmpGroupResponse.class);
   }

   public FindVtmResponse findVtm(FindVtmRequest findVtmRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (FindVtmResponse)this.executeOperation(findVtmRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCommentedClassificationRequest", FindVtmResponse.class);
   }

   public GetListOfVirtualMedicinalProductsResponse getListOfVirtualMedicinalProducts(GetListOfVirtualMedicinalProductsRequest getListOfVirtualMedicinalProductsRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (GetListOfVirtualMedicinalProductsResponse)this.executeOperation(getListOfVirtualMedicinalProductsRequest, "urn:be:fgov:ehealth:dics:protocol:v4:getListOfVirtualMedicinalProducts", GetListOfVirtualMedicinalProductsResponse.class);
   }

   public GetListOfVmpGroupsResponse getListOfVmpGroups(GetListOfVmpGroupsRequest getListOfVmpGroupsRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (GetListOfVmpGroupsResponse)this.executeOperation(getListOfVmpGroupsRequest, "urn:be:fgov:ehealth:dics:protocol:v4:getListOfVmpGroups", GetListOfVmpGroupsResponse.class);
   }

   public GetListOfActualMedicinalProductPackagesResponse getListOfActualMedicinalProductPackages(GetListOfActualMedicinalProductPackagesRequest getListOfActualMedicinalProductPackagesRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (GetListOfActualMedicinalProductPackagesResponse)this.executeOperation(getListOfActualMedicinalProductPackagesRequest, "urn:be:fgov:ehealth:dics:protocol:v4:getListOfActualMedicinalProductPackages", GetListOfActualMedicinalProductPackagesResponse.class);
   }

   public GetListOfActualMedicinalProductsResponse getListOfActualMedicinalProducts(GetListOfActualMedicinalProductsRequest getListOfActualMedicinalProductsRequest) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException {
      return (GetListOfActualMedicinalProductsResponse)this.executeOperation(getListOfActualMedicinalProductsRequest, "urn:be:fgov:ehealth:dics:protocol:v4:findCommentedClassificationRequest", GetListOfActualMedicinalProductsResponse.class);
   }

   private <T extends StatusResponseType> T executeOperation(Object request, String operation, Class<T> clazz) throws DicsException, TechnicalConnectorException, DicsDataNotFoundException {
      try {
         SAMLToken samlToken = Session.getInstance().getSession().getSAMLToken();
         T response = EhealthServiceHelper.callEhealthServiceV2(samlToken, ServiceFactory.getDicsService(samlToken, operation), request, clazz, this.sessionValidator, this.replyValidator);
         this.dicsValidator.validateResponse(response);
         return response;
      } catch (SoaErrorException var6) {
         throw new DicsException((StatusResponseType)var6.getResponseTypeV2());
      }
   }
}
