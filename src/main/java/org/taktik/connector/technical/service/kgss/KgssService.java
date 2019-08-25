package org.taktik.connector.technical.service.kgss;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.w3c.dom.Element;

public interface KgssService {

   KeyResult getNewKey(GetNewKeyRequestContent request, UUID keystoreId, KeyStore keystore, String passPhrase, byte[] kgssETK) throws TechnicalConnectorException;

   GetNewKeyResponseContent getNewKey(GetNewKeyRequestContent request, Crypto crypto, Credential encryption, Map<String, PrivateKey> decryptionKeys, byte[] etkKGSS) throws TechnicalConnectorException;

   GetKeyResponseContent getKey(KeyStoreCredential credential, GetKeyRequestContent request, SAMLToken samlToken, byte[] etkKGSS) throws TechnicalConnectorException;

   KeyResult retrieveKeyFromKgss(KeyStoreCredential credential, SAMLToken samlToken, byte[] keyId, byte[] myEtk, byte[] kgssEtk) throws TechnicalConnectorException;

   KeyResult retrieveNewKey(KeyStoreCredential credential, byte[] etkKgss, List<String> credentialTypes, String prescriberId, String executorId, String patientId, byte[] myEtk) throws TechnicalConnectorException;
}
