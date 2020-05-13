package be.ehealth.technicalconnector.ws.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.CertificateCallback;
import be.ehealth.technicalconnector.handler.SAMLHolderOfKeyHandler;
import be.ehealth.technicalconnector.handler.SoapActionHandler;
import be.ehealth.technicalconnector.service.sts.SAMLTokenFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.GenericWsSender;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.xml.soap.SOAPException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GenericWsSenderImpl extends AbstractWsSender implements GenericWsSender {
   public String sendUnsecured(String url, String payload) throws TechnicalConnectorException {
      return this.sendUnsecured(url, (String)payload, (String)null);
   }

   public String sendUnsecured(String url, String payload, String soapAction) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(payload);
      if (soapAction != null && soapAction.isEmpty()) {
         request.setSoapAction(soapAction);
      }

      request.setEndpoint(url);
      request.addHandlerChain((new HandlerChain()).register(HandlerPosition.SECURITY, new SoapActionHandler()));

      try {
         return this.send(request).asString();
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   public Node sendUnsecured(String url, Document payload) throws TechnicalConnectorException {
      return this.sendUnsecured(url, (Document)payload, (String)null);
   }

   public Node sendUnsecured(String url, Document payload, String soapAction) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(payload);
      if (soapAction != null && soapAction.isEmpty()) {
         request.setSoapAction(soapAction);
      }

      request.setEndpoint(url);
      request.addHandlerChain((new HandlerChain()).register(HandlerPosition.SECURITY, new SoapActionHandler()));

      try {
         return this.send(request).asNode();
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   public String sendSamlSecured(String url, String payload, Element assertion, Credential credential) throws TechnicalConnectorException {
      return this.sendSamlSecured(url, (String)payload, assertion, credential, (String)null);
   }

   public String sendSamlSecured(String url, String payload, Element assertion, Credential credential, String soapAction) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(payload);
      request.setEndpoint(url);
      if (soapAction != null && soapAction.isEmpty()) {
         request.setSoapAction(soapAction);
      }

      request.setEndpoint(url);
      request.addHandlerChain((new HandlerChain()).register(HandlerPosition.SECURITY, new SoapActionHandler()));
      request.setSamlSecured(assertion, credential);

      try {
         return this.send(request).asString();
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }

   public Node sendSamlSecured(String url, Document payload, Element assertion, Credential credential) throws TechnicalConnectorException {
      return this.sendSamlSecured(url, (Document)payload, assertion, credential, (String)null);
   }

   public Node sendSamlSecured(String url, Document payload, Element assertion, Credential credential, String soapAction) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(payload);
      request.setEndpoint(url);
      if (soapAction != null && soapAction.isEmpty()) {
         request.setSoapAction(soapAction);
      }

      SAMLToken token = SAMLTokenFactory.getInstance().createSamlToken(assertion, credential);
      request.addHandlerChain((new HandlerChain()).register(HandlerPosition.SECURITY, new SAMLHolderOfKeyHandler(token)).register(HandlerPosition.SECURITY, new SoapActionHandler()));

      try {
         return this.send(request).asNode();
      } catch (SOAPException var9) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var9, new Object[]{var9.getMessage()});
      }
   }

   public String sendCertificateSecured(String url, String payload, X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      return this.sendCertificateSecured(url, (String)payload, certificate, privateKey, (String)null);
   }

   public String sendCertificateSecured(String url, String payload, X509Certificate certificate, PrivateKey privateKey, String soapAction) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(payload);
      request.setEndpoint(url);
      if (soapAction != null && soapAction.isEmpty()) {
         request.setSoapAction(soapAction);
      }

      request.addHandlerChain((new HandlerChain()).register(HandlerPosition.SECURITY, new CertificateCallback(certificate, privateKey)).register(HandlerPosition.SECURITY, new SoapActionHandler()));

      try {
         return this.send(request).asString();
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }

   public Node sendCertificateSecured(String url, Document payload, X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      return this.sendCertificateSecured(url, (Document)payload, certificate, privateKey, (String)null);
   }

   public Node sendCertificateSecured(String url, Document payload, X509Certificate certificate, PrivateKey privateKey, String soapAction) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(payload);
      request.setEndpoint(url);
      if (soapAction != null && soapAction.isEmpty()) {
         request.setSoapAction(soapAction);
      }

      request.setCertificateSecured(certificate, privateKey);
      request.addHandlerChain((new HandlerChain()).register(HandlerPosition.SECURITY, new CertificateCallback(certificate, privateKey)).register(HandlerPosition.SECURITY, new SoapActionHandler()));

      try {
         return this.send(request).asNode();
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }
}
