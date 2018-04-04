package org.taktik.connector.technical.utils;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

public final class SOAPFaultFactory {
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
}
