package be.ehealth.businessconnector.mycarenet.memberdatav2.service.impl;

import be.ehealth.businessconnector.mycarenet.memberdatav2.service.MemberDataService;
import be.ehealth.businessconnector.mycarenet.memberdatav2.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberDataServiceImpl implements MemberDataService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(MemberDataServiceImpl.class);
   private SessionValidator sessionValidator;

   public MemberDataServiceImpl(SessionValidator sessionValidator) {
      this.sessionValidator = sessionValidator;
   }

   public MemberDataServiceImpl() {
      LOG.debug("creating MemberDataServiceImpl for bootstrapping purposes");
   }

   public final MemberDataConsultationResponse consultMemberData(SAMLToken token, MemberDataConsultationRequest request) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = ServiceFactory.getMemberDataSyncPort(token);
         service.setPayload((Object)request);
         GenericResponse wsResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         return (MemberDataConsultationResponse)wsResponse.asObject(MemberDataConsultationResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(MemberDataConsultationRequest.class);
      JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse.class);
   }
}
