package org.taktik.connector.business.memberdata.service.impl;

import org.taktik.connector.business.memberdata.service.MemberDataService;
import org.taktik.connector.business.memberdata.service.ServiceFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
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
         GenericRequest service = ServiceFactory.INSTANCE.getMemberDataSyncPort(token);
         service.setPayload(request);
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         return xmlResponse.asObject(MemberDataConsultationResponse.class);
      } catch (SOAPException ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.getMessage());
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(MemberDataConsultationRequest.class);
      JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse.class);
   }
}
