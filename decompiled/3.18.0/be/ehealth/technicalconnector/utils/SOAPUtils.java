package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.soap.SOAPFaultException;

public class SOAPUtils {
   private static final MessageFactory MF;

   protected SOAPUtils() {
   }

   public static SOAPFaultException newSOAPFaultException(String reasonText, Throwable cause) {
      try {
         SOAPFactory factory = SOAPFactory.newInstance();
         SOAPFault soapFault = factory.createFault();
         soapFault.setFaultString(reasonText);
         SOAPFaultException except = new SOAPFaultException(soapFault);
         except.initCause(cause);
         return except;
      } catch (SOAPException var5) {
         throw new IllegalArgumentException(var5);
      }
   }

   public static SOAPMessage newSOAPMessage(String payload) throws TechnicalConnectorException {
      try {
         SOAPMessage response = MF.createMessage();
         SOAPPart soapPart = response.getSOAPPart();
         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
         SOAPBody soapBody = soapEnvelope.getBody();
         soapBody.addDocument(ConnectorXmlUtils.toDocument(payload));
         return response;
      } catch (Exception var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var5, new Object[0]);
      }
   }

   static {
      try {
         MF = MessageFactory.newInstance();
      } catch (Exception var1) {
         throw new IllegalArgumentException(var1);
      }
   }
}
