package org.taktik.connector.business.recipeprojects.core.technical.connector.utils;

import java.util.List;

import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.RecipeException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;

public class Crypto {

    private final static Logger LOG = Logger.getLogger(Crypto.class);
    private static final String SYMM_KEY_ALGORITHM = "DESede";

    public Crypto() {

    }

    public static byte[] seal(EncryptionToken etk, String data) throws IntegrationModuleException {
        return seal(etk, data.getBytes());
    }

    public static byte[] seal(EncryptionToken etk, byte[] data) throws IntegrationModuleException {
        try {
            DataSealer dataSealer = EncryptionUtils.getInstance().initSealing();
            return dataSealer.seal(etk, data);
        } catch (KeyStoreException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (UnrecoverableKeyException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (CertificateException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (IOException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (DataSealerException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        }
    }

    public static byte[] seal(byte[] data, SecretKey secretKey, String keyId) throws IntegrationModuleException {
        try {
            DataSealer dataSealer = EncryptionUtils.getInstance().initSealing();
            return dataSealer.seal(data, secretKey, keyId);
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException | CertificateException | IOException | DataSealerException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        }
    }

    public static byte[] seal(List<EncryptionToken> etks, byte[] data) throws IntegrationModuleException {
        return seal(etks.get(0), data);
    }

    public static byte[] unseal(byte[] data) throws IntegrationModuleException {
        try {
            EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
            DataUnsealer dataUnsealer = encryptionUtils.initUnsealing();
            return encryptionUtils.unsealingData(dataUnsealer.unseal(data));
        } catch (KeyStoreException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (UnrecoverableKeyException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (CertificateException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        } catch (IOException ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        }
    }

    public static byte[] unseal(SecretKey secretKey, byte[] data) throws IntegrationModuleException {
        try {
            EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
            DataUnsealer dataUnsealer = encryptionUtils.initUnsealing();
            return encryptionUtils.unsealingData(dataUnsealer.unseal(data, secretKey));
        } catch (Exception ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        }
    }

    /**
     * Unseal with symm key.
     *
     * @param symmKey the symm key
     * @param objectToUnseal the object to unseal
     * @return the byte[]
     * @throws RecipeException the recipe exception
     */
    public byte[] unsealWithSymmKey(Key symmKey, byte[] objectToUnseal) throws IntegrationModuleException {
        try {
            Cipher cipher = Cipher.getInstance(SYMM_KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, symmKey);
            return cipher.doFinal(objectToUnseal);
        } catch (Exception ex) {
            throw new IntegrationModuleException("technical.connector.error.data.seal", ex);
        }
    }

    public static byte[] unsealForUnknown(KeyResult keyResult, byte[] data) throws IntegrationModuleException {
        return unseal(keyResult.getSecretKey(), data);
    }
}
