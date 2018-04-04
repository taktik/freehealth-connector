package org.taktik.connector.business.genericasync.session.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.service.ServiceFactory;
import org.taktik.connector.business.genericasync.session.GenAsyncService;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.validator.SessionValidator;

public class GenAsyncServiceImpl implements GenAsyncService {
   private org.taktik.connector.business.genericasync.service.GenAsyncService service;

   public GenAsyncServiceImpl(SessionValidator sessionValidator, String serviceName) throws TechnicalConnectorException {
      this.service = ServiceFactory.getGenAsyncService(serviceName, sessionValidator);
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public PostResponse postRequest(Post request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.postRequest(Session.getInstance().getSession().getSAMLToken(), request, header);
   }

   public GetResponse getRequest(Get request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.getRequest(Session.getInstance().getSession().getSAMLToken(), request, header);
   }

   public ConfirmResponse confirmRequest(Confirm request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.confirmRequest(Session.getInstance().getSession().getSAMLToken(), request, header);
   }
}
