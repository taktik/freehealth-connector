package org.taktik.connector.business.recipeprojects.core.utils;

import org.bouncycastle.util.encoders.Base64;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * The Class EncryptionUtils.
 */
public class EncryptionUtils {
    /**
     * The Constant SYMM_KEY_PROPERTY.
     */
    private static final String SYMM_KEY_PROPERTY = "symmKey";

    /**
     * The properties.
     */
    private PropertyHandler propertyHandler;

    /**
     * The instance.
     */
    private static EncryptionUtils instance = null;

    /**
     * Instantiates a new encryption utils.
     *
     * @param propertyHandler the property handler
     */
    private EncryptionUtils(PropertyHandler propertyHandler) {
        this.propertyHandler = propertyHandler;
    }

    /**
     * Gets the single instance of EncryptionUtils.
     *
     * @return single instance of EncryptionUtils
     */
    public static synchronized EncryptionUtils getInstance(PropertyHandler propertyHandler) {
        if (instance==null) {
            instance = new EncryptionUtils(propertyHandler);
        }
        return instance;
    }

    /**
     * Generate secret key.
     *
     * @return the key
     * @throws IntegrationModuleException the integration module exception
     */
    public Key generateSecretKey() throws IntegrationModuleException {
        try {
            if (propertyHandler.hasProperty(SYMM_KEY_PROPERTY)) {
                String base64key = propertyHandler.getProperty(SYMM_KEY_PROPERTY);
                DESedeKeySpec keyspec = new DESedeKeySpec(Base64.decode(base64key));
                SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
                return keyfactory.generateSecret(keyspec);
            }
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            return keyGen.generateKey();

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e) {
            throw new IntegrationModuleException(e);
        }
    }
}
