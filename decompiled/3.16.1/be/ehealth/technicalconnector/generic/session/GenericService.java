package be.ehealth.technicalconnector.generic.session;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface GenericService {
   GenericResponse send(GenericRequest var1) throws TechnicalConnectorException;

   String sendXML(String var1, URL var2) throws TechnicalConnectorException, SessionManagementException;

   String sendXML(String var1, String var2) throws TechnicalConnectorException, SessionManagementException;

   Node sendDocument(Document var1, String var2) throws TechnicalConnectorException, SessionManagementException;

   Node sendDocument(Document var1, URL var2) throws TechnicalConnectorException, SessionManagementException;

   String sendXML(String var1, URL var2, String var3) throws TechnicalConnectorException, SessionManagementException;

   String sendXML(String var1, String var2, String var3) throws TechnicalConnectorException, SessionManagementException;

   Node sendDocument(Document var1, String var2, String var3) throws TechnicalConnectorException, SessionManagementException;

   Node sendDocument(Document var1, URL var2, String var3) throws TechnicalConnectorException, SessionManagementException;
}
