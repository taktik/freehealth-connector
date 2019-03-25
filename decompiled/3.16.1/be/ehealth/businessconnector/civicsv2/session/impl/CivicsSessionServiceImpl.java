package be.ehealth.businessconnector.civicsv2.session.impl;

import be.ehealth.businessconnector.civicsv2.service.ServiceFactory;
import be.ehealth.businessconnector.civicsv2.session.CivicsSessionService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindCNKRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindCNKResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphTextRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphTextResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindPublicCNKRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindPublicCNKResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindReimbursementConditionsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindReimbursementConditionsResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetAddedDocumentsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetAddedDocumentsResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphExclusionsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphExclusionsResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphIncludedSpecialtiesRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphIncludedSpecialtiesResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetProfessionalAuthorizationsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetProfessionalAuthorizationsResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CivicsSessionServiceImpl implements CivicsSessionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private SessionValidator sessionValidator;
   private static final Logger LOG = LoggerFactory.getLogger(CivicsSessionServiceImpl.class);

   public CivicsSessionServiceImpl(SessionValidator sessionValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public CivicsSessionServiceImpl() {
      LOG.debug("creating CivicsSessionServiceImpl for bootstrapping purposes");
   }

   public FindParagraphTextResponse findParagraphText(FindParagraphTextRequest request) throws TechnicalConnectorException {
      return (FindParagraphTextResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:findParagraphText", FindParagraphTextResponse.class);
   }

   public GetParagraphExclusionsResponse getParagraphExclusions(GetParagraphExclusionsRequest request) throws TechnicalConnectorException {
      return (GetParagraphExclusionsResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:getParagraphExclusions", GetParagraphExclusionsResponse.class);
   }

   public GetParagraphIncludedSpecialtiesResponse getParagraphIncludedSpecialities(GetParagraphIncludedSpecialtiesRequest request) throws TechnicalConnectorException {
      return (GetParagraphIncludedSpecialtiesResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:getParagraphIncludedSpecialities", GetParagraphIncludedSpecialtiesResponse.class);
   }

   public GetProfessionalAuthorizationsResponse getProfessionalAuthorizations(GetProfessionalAuthorizationsRequest request) throws TechnicalConnectorException {
      return (GetProfessionalAuthorizationsResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:getProfessionalAuthorizations", GetProfessionalAuthorizationsResponse.class);
   }

   public GetAddedDocumentsResponse getAddedDocuments(GetAddedDocumentsRequest request) throws TechnicalConnectorException {
      return (GetAddedDocumentsResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:getAddedDocuments", GetAddedDocumentsResponse.class);
   }

   public FindReimbursementConditionsResponse findReimbursementConditions(FindReimbursementConditionsRequest request) throws TechnicalConnectorException {
      return (FindReimbursementConditionsResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:findReimbursementConditions", FindReimbursementConditionsResponse.class);
   }

   public FindParagraphResponse findParagraph(FindParagraphRequest request) throws TechnicalConnectorException {
      return (FindParagraphResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:findParagraph", FindParagraphResponse.class);
   }

   public FindCNKResponse findCNK(FindCNKRequest request) throws TechnicalConnectorException {
      return (FindCNKResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:findCNK", FindCNKResponse.class);
   }

   public FindPublicCNKResponse findPublicCNK(FindPublicCNKRequest request) throws TechnicalConnectorException {
      return (FindPublicCNKResponse)this.executeOperation(request, "urn:be:fgov:ehealth:civics:protocol:v2:findPublicCNK", FindPublicCNKResponse.class);
   }

   private <T> T executeOperation(Object request, String operation, Class<T> clazz) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getCivicsService(Session.getInstance().getSession().getSAMLToken(), operation);
         service.setPayload(request);
         return be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(FindCNKRequest.class);
      JaxbContextFactory.initJaxbContext(FindCNKResponse.class);
      JaxbContextFactory.initJaxbContext(FindParagraphRequest.class);
      JaxbContextFactory.initJaxbContext(FindParagraphResponse.class);
      JaxbContextFactory.initJaxbContext(FindParagraphTextRequest.class);
      JaxbContextFactory.initJaxbContext(FindParagraphTextResponse.class);
      JaxbContextFactory.initJaxbContext(FindReimbursementConditionsRequest.class);
      JaxbContextFactory.initJaxbContext(FindReimbursementConditionsResponse.class);
      JaxbContextFactory.initJaxbContext(GetAddedDocumentsRequest.class);
      JaxbContextFactory.initJaxbContext(GetAddedDocumentsResponse.class);
      JaxbContextFactory.initJaxbContext(GetParagraphExclusionsRequest.class);
      JaxbContextFactory.initJaxbContext(GetParagraphExclusionsResponse.class);
      JaxbContextFactory.initJaxbContext(GetParagraphIncludedSpecialtiesRequest.class);
      JaxbContextFactory.initJaxbContext(GetParagraphIncludedSpecialtiesResponse.class);
      JaxbContextFactory.initJaxbContext(GetProfessionalAuthorizationsRequest.class);
      JaxbContextFactory.initJaxbContext(GetProfessionalAuthorizationsResponse.class);
   }
}
