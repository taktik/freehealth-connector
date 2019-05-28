package be.ehealth.technicalconnector.generic.session.impl;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.generic.session.GenericService;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.GenericWsSender;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.net.URL;
import javax.xml.soap.SOAPException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GenericServiceImpl implements GenericService {
   private SessionValidator sessionValidator;

   public GenericServiceImpl(SessionValidator sessionValidator) {
      this.sessionValidator = sessionValidator;
   }

   public String sendXML(String payload, URL endpoint) throws TechnicalConnectorException, SessionManagementException {
      return this.sendXML(payload, (String)endpoint.toExternalForm(), (String)null);
   }

   public String sendXML(String payload, URL endpoint, String soapAction) throws TechnicalConnectorException, SessionManagementException {
      return this.sendXML(payload, endpoint.toExternalForm(), soapAction);
   }

   public String sendXML(String payload, String endpoint) throws TechnicalConnectorException, SessionManagementException {
      return this.sendXML(payload, (String)endpoint, (String)null);
   }

   public String sendXML(String payload, String endpoint, String soapAction) throws TechnicalConnectorException, SessionManagementException {
      GenericWsSender sender = ServiceFactory.getGenericWsSender();
      if (this.sessionValidator.validateSession()) {
         GenericRequest request = new GenericRequest();
         request.setEndpoint(endpoint);
         request.setSoapAction(soapAction);
         request.setCredentialFromSession(TokenType.SAML);
         request.setPayload(payload);

         try {
            return sender.send(request).asString();
         } catch (SOAPException var7) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
         }
      } else {
         return null;
      }
   }

   public Node sendDocument(Document payload, URL endpoint) throws TechnicalConnectorException, SessionManagementException {
      return this.sendDocument(payload, (String)endpoint.toExternalForm(), (String)null);
   }

   public Node sendDocument(Document payload, URL endpoint, String soapAction) throws TechnicalConnectorException, SessionManagementException {
      return this.sendDocument(payload, endpoint.toExternalForm(), soapAction);
   }

   public Node sendDocument(Document payload, String endpoint) throws TechnicalConnectorException, SessionManagementException {
      return this.sendDocument(payload, (String)endpoint, (String)null);
   }

   public Node sendDocument(Document payload, String endpoint, String soapAction) throws TechnicalConnectorException, SessionManagementException {
      GenericWsSender sender = ServiceFactory.getGenericWsSender();
      if (this.sessionValidator.validateSession()) {
         GenericRequest request = new GenericRequest();
         request.setEndpoint(endpoint);
         request.setSoapAction(soapAction);
         request.setCredentialFromSession(TokenType.SAML);
         request.setPayload(payload);

         try {
            return sender.send(request).asNode();
         } catch (SOAPException var7) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
         }
      } else {
         return null;
      }
   }

   public GenericResponse send(GenericRequest request) throws TechnicalConnectorException {
      GenericWsSender sender = ServiceFactory.getGenericWsSender();
      return sender.send(request);
   }
}
