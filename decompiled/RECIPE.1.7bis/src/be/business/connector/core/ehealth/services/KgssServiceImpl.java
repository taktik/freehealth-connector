package org.taktik.connector.business.recipeprojects.core.ehealth.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import org.w3c.dom.Element;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.ETKHelper;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.service.ServiceFactory;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.service.sts.STSService;
import be.ehealth.technicalconnector.service.sts.STSServiceFactory;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttribute;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttributeDesignator;
import be.ehealth.technicalconnector.service.sts.impl.AbstractSTSService;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;

import com.sun.xml.ws.client.ClientTransportException;

/**
 * The Class EncryptionKeyServiceImpl.
 */
public class KgssServiceImpl implements KgssService {

    /**
     * The Constant LOG.
     */
    private final static Logger LOG = Logger.getLogger(KgssServiceImpl.class);

    /**
     * The encryption key service.
     */
    private static KgssService kgssService;

    private KeyGenerator keyGen;

    private KgssServiceImpl() {
    }

    /**
     * Gets the singleton instance of KeyDepotServiceImpl.
     *
     * @return singleton instance of KeyDepotServiceImpl
     */
    public static KgssService getInstance() {
        if (kgssService == null) {
            kgssService = new KgssServiceImpl();
        }
        return kgssService;
    }

    /* (non-Javadoc)
	 * @see be.technicalconnector.services.KgssService#retrieveKeyFromKgss(byte[], byte[], byte[])
     */
    @Override
    public KeyResult retrieveKeyFromKgss(byte[] keyId, byte[] myEtk, byte[] kgssEtk) throws IntegrationModuleException {
        LOG.debug("KeyIdentifier : " + Arrays.toString(keyId));
        GetKeyRequestContent getKeyRequestContent = new GetKeyRequestContent();
        Key key = null;

        if (myEtk != null) {
            // Mode1 : using ETK
            getKeyRequestContent.setETK(myEtk);
        } else {
            // Using sym Key
            try {
                keyGen = KeyGenerator.getInstance("AES");
                synchronized (keyGen) {
                    key = keyGen.generateKey();
                }
            } catch (Exception e) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"), e);
            }
            getKeyRequestContent.setKeyEncryptionKey(key.getEncoded());
        }

        getKeyRequestContent.setKeyIdentifier(Base64.decodeBase64(keyId));

        KeyResult keyResultToReturn = null;

        try {
            be.ehealth.technicalconnector.service.kgss.KgssService kgss = be.ehealth.technicalconnector.service.ServiceFactory.getKgssService();

            SessionItem sessionItem = Session.getInstance().getSession();
            GetKeyResponseContent getKeyResponseContent = kgss.getKey(getKeyRequestContent, sessionItem.getHolderOfKeyCredential(), sessionItem.getEncryptionCredential(), sessionItem.getSAMLToken().getAssertion(), sessionItem.getEncryptionPrivateKeys(), kgssEtk);
            keyResultToReturn = new KeyResult(new SecretKeySpec(getKeyResponseContent.getKey(), "AES"), new String(keyId));

        } catch (SOAPFaultException se) {
            if (se.getFault() != null && se.getFault().getFaultCode() != null && se.getFault().getFaultCode().contains("InvalidSecurity")) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.kgss.getKey"), se);
            } else {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.kgss.getKey.other"), se);
            }
        } catch (ClientTransportException cte) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.kgss"), cte);
        } catch (TechnicalConnectorException e) {
            LOG.error("Error retrieving key", e);
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.key"), e);
        }

        return keyResultToReturn;
    }

    /* (non-Javadoc)
	 * @see be.technicalconnector.services.KgssService#retrieveNewKey(byte[], java.util.List, java.lang.String, java.lang.String, java.lang.String, byte[])
     */
    @Override
    public KeyResult retrieveNewKey(byte[] etkKgss, List<String> credentialTypes, String prescriberId, String executorId, String patientId, byte[] myEtk) throws IntegrationModuleException {
        GetNewKeyRequestContent req = new GetNewKeyRequestContent();
        req.setETK(myEtk);

        // --- Building the Access Control List
        List<CredentialType> allowedReaders = req.getAllowedReaders();

        for (String credentialTypeStr : credentialTypes) {
            String[] atrs = credentialTypeStr.split(",");
            if (atrs.length != 3 && atrs.length != 2) {
                throw new IntegrationModuleException("Invalid property : kgss.createPrescription.ACL.XXX = " + credentialTypeStr);
            }

            String value = "";
            if (atrs.length == 3) {
                value = atrs[2];
                value = value.replaceAll("%PRESCRIBER_ID%", prescriberId);
                value = value.replaceAll("%EXECUTOR_ID%", executorId);
                value = value.replaceAll("%PATIENT_ID%", patientId);
            }

            CredentialType ct = new CredentialType();
            ct.setNamespace(atrs[0]);
            ct.setName(atrs[1]);
            ct.getValues().add(value);

            allowedReaders.add(ct);
        }

        KeyResult keyResultToReturn = null;

        try {
            be.ehealth.technicalconnector.service.kgss.KgssService kgss = ServiceFactory.getKgssService();
            SessionItem sessionItem = Session.getInstance().getSession();

            GetNewKeyResponseContent getNewKeyResponseContent = kgss.getNewKey(req, sessionItem.getEncryptionCredential(), sessionItem.getEncryptionPrivateKeys(), etkKgss);
            byte[] keyResponse = getNewKeyResponseContent.getNewKey();
            byte[] keyId = getNewKeyResponseContent.getNewKeyIdentifier();

            keyResultToReturn = new KeyResult(new SecretKeySpec(keyResponse, "AES"), new String(Base64.encodeBase64(keyId)));
        } catch (TechnicalConnectorException e) {
            LOG.error("Error retrieving new key", e);

            if (e instanceof UnsealConnectorException) {
                if (((UnsealConnectorException) e).getUnsealResult() != null) {
                    List<NotificationError> decryptionFailure = ((UnsealConnectorException) e).getUnsealResult().getErrors();
                    for (NotificationError error : decryptionFailure) {
                        LOG.error("NotificationError: " + error.toString());
                    }
                    List<NotificationWarning> warnings = ((UnsealConnectorException) e).getUnsealResult().getWarnings();
                    for (NotificationWarning warning : warnings) {
                        LOG.error("NotificationWarning: " + warning.toString());
                    }
                }
            }
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.new.key"), e);
        }
        return keyResultToReturn;
    }

    /**
     * Helper method for GetKey Retrieves an SAML Assertion / Secured token from
     * STS.
     *
     * @param authentication the authentication
     * @param service the service
     * @return the token
     * @throws TechnicalConnectorException the technical connector exception
     */
    private Element getToken(Credential authentication, Credential service) throws TechnicalConnectorException {
        // create the list of SAML Attributes Designators
        List<SAMLAttributeDesignator> designators = new ArrayList<SAMLAttributeDesignator>();
        designators.add(new SAMLAttributeDesignator("urn:be:fgov:ehealth:1.0:certificateholder:person:ssin", "urn:be:fgov:identification-namespace"));
        // designators.add(new SAMLAttributeDesignator("urn:be:fgov:ehealth:1.0:hospital:nihii-number", "urn:be:fgov:identification-namespace"));
        // designators.add(new SAMLAttributeDesignator("urn:be:fgov:ehealth:1.0:hospital:nihii-number:recognisedhospital:boolean", "urn:be:fgov:certified-namespace:ehealth"));
        designators.add(new SAMLAttributeDesignator("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace"));

        // create the list of SAML Attributes
        List<SAMLAttribute> attributes = new ArrayList<SAMLAttribute>();
        attributes.add(new SAMLAttribute("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace", ConfigFactory.getConfigValidator(new ArrayList<String>()).getProperty("user.inss")));
        attributes.add(new SAMLAttribute("urn:be:fgov:ehealth:1.0:certificateholder:person:ssin", "urn:be:fgov:identification-namespace", ConfigFactory.getConfigValidator(new ArrayList<String>()).getProperty("user.inss")));
        // attributes.add(new SAMLAttribute("urn:be:fgov:ehealth:1.0:hospital:nihii-number", "urn:be:fgov:identification-namespace", TestPropertiesLoader.getProperty("test.nihii")));

        int validity = 24;

        // get the token
        STSService sts = STSServiceFactory.getInstance();
        return sts.getToken(authentication, service, attributes, designators, AbstractSTSService.HOK_METHOD, validity);
    }

}
