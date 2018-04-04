package org.taktik.connector.business.dmg.service.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException;
import org.taktik.connector.business.dmg.service.DmgService;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.service.GenAsyncService;
import org.taktik.connector.business.genericasync.service.ServiceFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.soap.SOAPException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DmgServiceImpl implements DmgService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(DmgServiceImpl.class);
   private static final String SERVICE_NAME = "dmg";
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;
   private GenAsyncService genAsyncService;

   public DmgServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
      this.genAsyncService = ServiceFactory.getGenAsyncService("dmg", sessionValidator);
   }

   public DmgServiceImpl() {
      LOG.debug("creating DmgServiceImpl for bootstrapping purposes");
   }

   public final ConsultGlobalMedicalFileResponse consultGlobalMedicalFile(SAMLToken token, ConsultGlobalMedicalFileRequest request) throws DmgBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = org.taktik.connector.business.dmg.service.ServiceFactory.getConsultationService(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1:ConsultGlobalMedicalFile");
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         ConsultGlobalMedicalFileResponse response = (ConsultGlobalMedicalFileResponse)xmlResponse.asObject(ConsultGlobalMedicalFileResponse.class);
         this.replyValidator.validateReplyStatus((ResponseType)response);
         return response;
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   public final NotifyGlobalMedicalFileResponse notifyGlobalMedicalFile(SAMLToken token, NotifyGlobalMedicalFileRequest request) throws TechnicalConnectorException, DmgBusinessConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = org.taktik.connector.business.dmg.service.ServiceFactory.getNotificationService(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1:NotifyGlobalMedicalFile");
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         NotifyGlobalMedicalFileResponse response = (NotifyGlobalMedicalFileResponse)xmlResponse.asObject(NotifyGlobalMedicalFileResponse.class);
         this.replyValidator.validateReplyStatus((ResponseType)response);
         return response;
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   public ConfirmResponse confirmRequest(SAMLToken token, Confirm request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      try {
         if (StringUtils.isEmpty(header.getAction().toString())) {
            header.setAction(new URI("urn:be:cin:nip:async:generic:confirm:hash"));
         }
      } catch (URISyntaxException var5) {
         LOG.error(var5.getClass().getSimpleName() + ": " + var5.getMessage());
      }

      return this.genAsyncService.confirmRequest(token, request, header);
   }

   public GetResponse getRequest(SAMLToken token, Get request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      try {
         if (StringUtils.isEmpty(header.getAction().toString())) {
            header.setAction(new URI("urn:be:cin:nip:async:generic:get:query"));
         }
      } catch (URISyntaxException var5) {
         LOG.error(var5.getClass().getSimpleName() + ": " + var5.getMessage());
      }

      return this.genAsyncService.getRequest(token, request, header);
   }

   public PostResponse postRequest(SAMLToken token, Post request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException {
      try {
         if (StringUtils.isEmpty(header.getAction().toString())) {
            header.setAction(new URI("urn:be:cin:nip:async:generic:post:msg"));
         }
      } catch (URISyntaxException var5) {
         LOG.error(var5.getClass().getSimpleName() + ": " + var5.getMessage());
      }

      return this.genAsyncService.postRequest(token, request, header);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(ConsultGlobalMedicalFileRequest.class);
      JaxbContextFactory.initJaxbContext(ConsultGlobalMedicalFileResponse.class);
      JaxbContextFactory.initJaxbContext(NotifyGlobalMedicalFileRequest.class);
      JaxbContextFactory.initJaxbContext(NotifyGlobalMedicalFileResponse.class);
   }
}
