package org.taktik.connector.business.dmg.session.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException;
import org.taktik.connector.business.dmg.session.DmgService;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse;

public class DmgServiceImpl implements DmgService {
   private org.taktik.connector.business.dmg.service.DmgService service;

   public DmgServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new org.taktik.connector.business.dmg.service.impl.DmgServiceImpl(sessionValidator, replyValidator);
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
