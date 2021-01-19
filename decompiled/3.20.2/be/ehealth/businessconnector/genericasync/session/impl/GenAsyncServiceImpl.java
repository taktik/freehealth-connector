package be.ehealth.businessconnector.genericasync.session.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.service.ServiceFactory;
import be.ehealth.businessconnector.genericasync.session.GenAsyncService;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class GenAsyncServiceImpl implements GenAsyncService {
   private be.ehealth.businessconnector.genericasync.service.GenAsyncService service;

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
