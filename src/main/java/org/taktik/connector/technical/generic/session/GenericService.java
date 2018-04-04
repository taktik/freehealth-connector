package org.taktik.connector.technical.generic.session;

import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
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
