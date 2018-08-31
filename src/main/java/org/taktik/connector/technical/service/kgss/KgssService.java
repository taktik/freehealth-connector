package org.taktik.connector.technical.service.kgss;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.session.SessionItem;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;

public interface KgssService {
   KeyResult getNewKey(GetNewKeyRequestContent var1, byte[] var2) throws TechnicalConnectorException;

   GetNewKeyResponseContent getNewKey(GetNewKeyRequestContent var1, Credential var2, Map<String, PrivateKey> var3, byte[] var4) throws TechnicalConnectorException;

   GetKeyResponseContent getKey(GetKeyRequestContent var1, Credential var2, Credential var3, Element var4, Map<String, PrivateKey> var5, byte[] var6) throws TechnicalConnectorException;

   KeyResult retrieveKeyFromKgss(KeyStore keystore, SAMLToken samlToken, String passPhrase, byte[] keyId, byte[] myEtk, byte[] kgssEtk) throws TechnicalConnectorException;

   KeyResult retrieveNewKey(KeyStore keystore, SAMLToken samlToken, String passPhrase, byte[] etkKgss, List<String> credentialTypes, String prescriberId, String executorId, String patientId, byte[] myEtk) throws TechnicalConnectorException;
}
