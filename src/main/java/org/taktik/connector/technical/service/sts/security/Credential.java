package org.taktik.connector.technical.service.sts.security;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public interface Credential {
   String getIssuer() throws TechnicalConnectorException;

   String getIssuerQualifier() throws TechnicalConnectorException;

   PublicKey getPublicKey() throws TechnicalConnectorException;

   PrivateKey getPrivateKey() throws TechnicalConnectorException;

   X509Certificate getCertificate() throws TechnicalConnectorException;

   String getProviderName() throws TechnicalConnectorException;

   Certificate[] getCertificateChain() throws TechnicalConnectorException;

   KeyStore getKeyStore() throws TechnicalConnectorException;
}
