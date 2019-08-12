package be.ehealth.businessconnector.dmg.session.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.dmg.exception.DmgBusinessConnectorException;
import be.ehealth.businessconnector.dmg.session.DmgService;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse;

public class DmgServiceImpl implements DmgService {
   private be.ehealth.businessconnector.dmg.service.DmgService service;

   public DmgServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new be.ehealth.businessconnector.dmg.service.impl.DmgServiceImpl(sessionValidator, replyValidator);
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public ConsultGlobalMedicalFileResponse consultGlobalMedicalFile(ConsultGlobalMedicalFileRequest request) throws DmgBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.consultGlobalMedicalFile(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public NotifyGlobalMedicalFileResponse notifyGlobalMedicalFile(NotifyGlobalMedicalFileRequest request) throws DmgBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.notifyGlobalMedicalFile(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public ConfirmResponse confirmRequest(Confirm request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      return this.service.confirmRequest(Session.getInstance().getSession().getSAMLToken(), request, header);
   }

   public GetResponse getRequest(Get request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      return this.service.getRequest(Session.getInstance().getSession().getSAMLToken(), request, header);
   }

   public PostResponse postRequest(Post request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException {
      return this.service.postRequest(Session.getInstance().getSession().getSAMLToken(), request, header);
   }
}
