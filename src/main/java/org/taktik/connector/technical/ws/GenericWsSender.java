package org.taktik.connector.technical.ws;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface GenericWsSender {
   GenericResponse send(GenericRequest var1) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   String sendUnsecured(String var1, String var2) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Node sendUnsecured(String var1, Document var2) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   String sendSamlSecured(String var1, String var2, Element var3, Credential var4) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Node sendSamlSecured(String var1, Document var2, Element var3, Credential var4) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   String sendCertificateSecured(String var1, String var2, X509Certificate var3, PrivateKey var4) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Node sendCertificateSecured(String var1, Document var2, X509Certificate var3, PrivateKey var4) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   String sendUnsecured(String var1, String var2, String var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Node sendUnsecured(String var1, Document var2, String var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   String sendSamlSecured(String var1, String var2, Element var3, Credential var4, String var5) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Node sendSamlSecured(String var1, Document var2, Element var3, Credential var4, String var5) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   String sendCertificateSecured(String var1, String var2, X509Certificate var3, PrivateKey var4, String var5) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Node sendCertificateSecured(String var1, Document var2, X509Certificate var3, PrivateKey var4, String var5) throws TechnicalConnectorException;
}
