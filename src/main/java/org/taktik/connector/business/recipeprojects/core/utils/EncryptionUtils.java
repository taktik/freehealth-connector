/**
 * Copyright (C) 2010 Recip-e
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.taktik.connector.business.recipeprojects.core.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealerBuilder;
import be.fgov.ehealth.etee.crypto.decrypt.UnsealedData;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerBuilder;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.policies.*;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * The Class EncryptionUtils.
 */
public class EncryptionUtils {

    private static final String KEYSTORE_DIR = "KEYSTORE_DIR";

    private static final String TSA_KEYSTORE_TYPE = "timestamp.signature.keystore.type";
    private static final String TSA_KEYSTORE_FILE = "timestamp.signature.keystore.path";
    private static final String TSA_KEYSTORE_PASSWORD = "timestamp.signature.keystore.pwd";

    /**
     * The Constant DEFAULT_PASSWORD.
     */
    private static final char[] DEFAULT_PASSWORD = "hello!".toCharArray();

    /**
     * The Constant AUTHENTICATION_ALIAS.
     */
    public static final String AUTHENTICATION_ALIAS = "authentication";

    /**
     * The Constant AUTHENTICATION_FALLBACK_ALIAS.
     */
    private static final String AUTHENTICATION_FALLBACK_ALIAS = "authentication_fallback";

    /**
     * The Constant PROP_KEYSTORE_FILE.
     */
    public static final String PROP_KEYSTORE_FILE = "sessionmanager.holderofkey.keystore";

    private static final String PROP_OLD_KEYSTORE_FILE = "OLD_KEYSTORE_FILE";

    /**
     * The Constant PROP_KEYSTORE_TYPE.
     */
    private static final String PKCS12 = "PKCS12";

    /**
     * The Constant PROP_KEYSTORE_PASSWORD.
     */
    public static final String PROP_KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD";

    private static final String PROP_OLD_KEYSTORE_PASSWORD = "OLD_KEYSTORE_PASSWORD";

    /**
     * The Constant PROP_KEYSTORE_P12_FOLDER.
     */
    public static final String PROP_KEYSTORE_P12_FOLDER = "KEYSTORE_DIR";

    /**
     * The Constant PROP_KEYSTORE_P12_FOLDER.
     */
    private static final String PROP_KEYSTORE_OLD_P12_FOLDER = "KEYSTORE_AUTH_OLD_P12_FOLDER";

    private static final String PERSONAL_KEYSTORE = "PERSONAL_KEYSTORE";

    /**
     * The Constant PROP_KEYSTORE_FILE.
     */
    private static final String PROP_KEYSTORE_MANDATE_FILE = "KEYSTORE_FILE_MANDATE_ORGANISATION";

    /**
     * The Constant PROP_KEYSTORE_PASSWORD.
     */
    private static final String PROP_KEYSTORE_MANDATE_PASSWORD = "KEYSTORE_PASSWORD_MANDATE_ORGANISATION";

    private static final String PROP_KEYSTORE_RIZIV_KBO = "PROP_KEYSTORE_RIZIV_KBO";

    private static final String PROP_OLD_KEYSTORE_RIZIV_KBO = "PROP_OLD_KEYSTORE_RIZIV_KBO";

    /**
     * The Constant LOCAL_CA_CERTIFICATES_STORE_FILE.
     */
    private static final String LOCAL_CA_CERTIFICATES_STORE_FILE = "CAKEYSTORE_LOCATION";

    /**
     * The Constant LOCAL_CA_CERTIFICATES_STORE_TYPE.
     */
    private static final String LOCAL_CA_CERTIFICATES_STORE_TYPE = "LOCAL_CA_CERTIFICATES_STORE_TYPE";

    /**
     * The Constant LOCAL_CA_CERTIFICATES_STORE_PASSWORD.
     */
    private static final String LOCAL_CA_CERTIFICATES_STORE_PASSWORD = "CAKEYSTORE_PASSWORD";

    /**
     * The Constant SYMM_KEY_PROPERTY.
     */
    private static final String SYMM_KEY_PROPERTY = "symmKey";

    /**
     * The client key store.
     */
    private KeyStore keyStore;

    private KeyStore tsaKeyStore;

    private KeyStore mandateKeyStore;

    private KeyStore oldKeyStore;

    private List<String> tsaStoreAliases;

    /**
     * The properties.
     */
    private PropertyHandler propertyHandler = null;

    /**
     * The Constant LOG.
     */
    private final static Logger LOG = Logger.getLogger(EncryptionUtils.class);

    /**
     * The instance.
     */
    private static EncryptionUtils instance = null;

    /**
     * The system keystore path.
     */
    private String systemKeystorePath;
    /**
     * The old system keystore path.
     */
    private String oldSystemKeystorePath;

    private String caCertificateKeystoreLocation;

    private final String encryptionSignature = "ENCRYPTI0NS1GNATURE";

    /**
     * Instantiates a new encryption utils.
     *
     * @param propertyHandler the property handler
     */
    public EncryptionUtils(PropertyHandler propertyHandler) {
        this.propertyHandler = propertyHandler;
    }

    /**
     * Gets the single instance of EncryptionUtils.
     *
     * @return single instance of EncryptionUtils
     */
    public static EncryptionUtils getInstance(PropertyHandler propertyHandler) {
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
                SecretKey key = keyfactory.generateSecret(keyspec);
                return key;
            }
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            return keyGen.generateKey();

        } catch (NoSuchAlgorithmException e) {
            throw new IntegrationModuleException(e);
        } catch (InvalidKeyException e) {
            throw new IntegrationModuleException(e);
        } catch (InvalidKeySpecException e) {
            throw new IntegrationModuleException(e);
        }
    }

    /**
     * Unseal with symm key.
     *
     * @param symmKey        the symm key
     * @param objectToUnseal the object to unseal
     * @return the byte[]
     */
    public static byte[] unsealWithSymmKey(Key symmKey, byte[] objectToUnseal) {

        try {
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, symmKey);
            return cipher.doFinal(objectToUnseal);
        } catch (Exception e) {
            LOG.error("unsealWithSymmKey Error", e);
        }
        return null;
    }

    /**
     * Gets the client key store.
     *
     * @return the client key store
     * @throws IntegrationModuleException
     */
    public KeyStore getKeyStore() throws IntegrationModuleException {

        if (keyStore == null) {
            try {

                InputStream stream = getSystemKeystoreStream();
                String pwd = getSystemKeystorePassword();

                // Loading system keystore
                try {
                    keyStore = KeyManager.getKeyStore(stream, PKCS12, pwd.toCharArray());
                } catch (IOException ex) {
                    if (ex.getCause() instanceof javax.crypto.BadPaddingException) {
                        throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.password"), ex);
                    } else {
                        throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"), ex);
                    }
                }

                LOG.debug("Iterating over keyStore.aliases()");
                // Resetting all password in memory
                Enumeration<String> aliases = keyStore.aliases();
                List<String> keyEntries = new ArrayList<>();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    LOG.debug("Checking key : " + alias);
                    if (keyStore.isKeyEntry(alias)) {
                        keyEntries.add(alias);
                    }
                }

                LOG.debug("Deleting entries of keyStore.aliases()");
                for (String alias : keyEntries) {
                    Key key = keyStore.getKey(alias, pwd.toCharArray());
                    Certificate[] chain = keyStore.getCertificateChain(alias);
                    keyStore.deleteEntry(alias);
                    keyStore.setKeyEntry(alias, key, DEFAULT_PASSWORD, chain);
                }

                dumpKeyStore(keyStore);

            } catch (Throwable t) {

                LOG.error("Invalid keystore configuration", t);
                Exceptionutils.errorHandler(t, "error.keystore");
            }
        }
        return keyStore;
    }

    /**
     * Gets the client key store.
     *
     * @return the client key store
     * @throws IntegrationModuleException
     */
    public KeyStore getTSAKeyStore() throws IntegrationModuleException {

        if (tsaKeyStore == null) {
            try {

                InputStream stream = null;
                char[] pwd = null;

                stream = getTSAKeystoreStream();
                pwd = propertyHandler.getProperty(TSA_KEYSTORE_PASSWORD).toCharArray();

                // Loading system keystore
                try {
                    tsaKeyStore = KeyManager.getKeyStore(stream, propertyHandler.getProperty(TSA_KEYSTORE_TYPE), pwd);
                } catch (IOException ex) {
                    if (ex.getCause() instanceof javax.crypto.BadPaddingException) {
                        throw new IntegrationModuleException(I18nHelper.getLabel("error.tsaStore.password"), ex);
                    } else {
                        throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"), ex);
                    }
                }

                LOG.debug("Iterating over tsaKeyStore.aliases()");
                // Resetting all password in memory
                Enumeration<String> aliases = tsaKeyStore.aliases();
                tsaStoreAliases = new ArrayList<>();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    LOG.debug("Checking key : " + alias);
                    tsaStoreAliases.add(alias);
                }
            } catch (Throwable t) {

                LOG.error("Invalid keystore configuration", t);
                Exceptionutils.errorHandler(t, "error.tsaStore");
            }
        }
        return tsaKeyStore;
    }

    public List<String> getTsaStoreAliases() {
        return tsaStoreAliases;
    }


    private InputStream getSystemKeystoreStream() throws IOException, IntegrationModuleException {
        return IOUtils.getResourceAsStream(getSystemKeystorePath());
    }

    public String getSystemKeystorePath() throws IntegrationModuleException {
        if (!StringUtils.isBlank(systemKeystorePath)) {
            return systemKeystorePath;
        }

        if (propertyHandler.hasProperty(PROP_KEYSTORE_P12_FOLDER) && propertyHandler.hasProperty(PROP_KEYSTORE_RIZIV_KBO)) {
            String directory = propertyHandler.getProperty(PROP_KEYSTORE_P12_FOLDER);
            String riziv = propertyHandler.getProperty(PROP_KEYSTORE_RIZIV_KBO);
            List<String> files = IOUtils.getP12FileList(directory, riziv);
            String file = files.get(0);
            return file;
        }

        if (propertyHandler.hasProperty(PROP_KEYSTORE_FILE) && propertyHandler.hasProperty(KEYSTORE_DIR)) {
            String keyStoreDir = propertyHandler.getProperty(KEYSTORE_DIR);
            String keyStoreFile = propertyHandler.getProperty(PROP_KEYSTORE_FILE);

            String keyStoreFilePath = "";
            if (StringUtils.endsWith(keyStoreDir, "/")) {
                keyStoreFilePath = keyStoreDir + keyStoreFile;
            } else {
                keyStoreFilePath = keyStoreDir + "/" + keyStoreFile;
            }
            return keyStoreFilePath;
        }
        throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.file"));
    }

    private InputStream getTSAKeystoreStream() throws IOException, IntegrationModuleException {
        InputStream stream = null;
        String tSAKeyStoreDir = propertyHandler.getProperty(KEYSTORE_DIR);
        String tSAKeyStoreFile = propertyHandler.getProperty(TSA_KEYSTORE_FILE);

        String keyStoreFilePath = "";
        if (StringUtils.endsWith(tSAKeyStoreDir, "/")) {
            keyStoreFilePath = tSAKeyStoreDir + tSAKeyStoreFile;
        } else {
            keyStoreFilePath = tSAKeyStoreDir + "/" + tSAKeyStoreFile;
        }
        stream = IOUtils.getResourceAsStream(keyStoreFilePath);
        return stream;
    }

    public KeyStore getOldKeyStore() throws IntegrationModuleException {

        if (oldKeyStore == null) {
            try {

                InputStream stream = getOldSystemKeystoreStream();
                String pwd = getOldSystemKeystorePassword();

                if (stream != null) {
                    // Loading system keystore
                    try {
                        oldKeyStore = KeyManager.getKeyStore(stream, PKCS12, pwd.toCharArray());
                    } catch (IOException ex) {
                        throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.password"), ex);
                    }
                    // Resetting all password in memory
                    Enumeration<String> aliases = oldKeyStore.aliases();
                    while (aliases.hasMoreElements()) {
                        String alias = aliases.nextElement();
                        LOG.debug("Checking key : " + alias);
                        if (oldKeyStore.isKeyEntry(alias)) {
                            Key key = oldKeyStore.getKey(alias, pwd.toCharArray());
                            Certificate[] chain = oldKeyStore.getCertificateChain(alias);
                            oldKeyStore.deleteEntry(alias);
                            oldKeyStore.setKeyEntry(alias, key, DEFAULT_PASSWORD, chain);
                        }
                    }

                    dumpKeyStore(oldKeyStore);
                }
            } catch (Exception e) {

                LOG.error("Invalid old keystore configuration", e);

                throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore"), e);

            }
        }
        return oldKeyStore;
    }


    public String getOldSystemKeystorePath() {
        if (!StringUtils.isEmpty(oldSystemKeystorePath)) {
            return oldSystemKeystorePath;
        } else if (propertyHandler.hasProperty(PROP_KEYSTORE_OLD_P12_FOLDER) && propertyHandler.hasProperty(PROP_OLD_KEYSTORE_RIZIV_KBO)) {
            String directory = propertyHandler.getProperty(PROP_KEYSTORE_OLD_P12_FOLDER);
            String riziv = propertyHandler.getProperty(PROP_OLD_KEYSTORE_RIZIV_KBO);

            List<String> files = IOUtils.getP12FileList(directory, riziv);
            return files.get(0);
        } else if (propertyHandler.hasProperty(PROP_OLD_KEYSTORE_FILE)) {
            return propertyHandler.getProperty(PROP_OLD_KEYSTORE_FILE);
        }
        return null;
    }

    private InputStream getOldSystemKeystoreStream() throws IOException {
        final String path = getOldSystemKeystorePath();
        return StringUtils.isBlank(path) ? null : IOUtils.getResourceAsStream(path);
    }


    public KeyStore getMandateOrganisationKeyStore() {

        if (mandateKeyStore == null) {
            try {

                InputStream stream = null;
                char[] pwd = null;
                if (propertyHandler.hasProperty(PROP_KEYSTORE_MANDATE_FILE)) {
                    stream = IOUtils.getResourceAsStream(propertyHandler.getProperty(PROP_KEYSTORE_MANDATE_FILE));
                } else {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.file"));
                }
                if (propertyHandler.hasProperty(PROP_KEYSTORE_MANDATE_PASSWORD)) {
                    pwd = propertyHandler.getProperty(PROP_KEYSTORE_MANDATE_PASSWORD).toCharArray();
                } else {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.password"));
                }
                // Loading system keystore
                mandateKeyStore = KeyManager.getKeyStore(stream, PKCS12, pwd);

                // Resetting all password in memory
                Enumeration<String> aliases = mandateKeyStore.aliases();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    LOG.debug("Checking key : " + alias);
                    if (mandateKeyStore.isKeyEntry(alias)) {
                        Key key = mandateKeyStore.getKey(alias, pwd);
                        Certificate[] chain = mandateKeyStore.getCertificateChain(alias);
                        mandateKeyStore.deleteEntry(alias);
                        mandateKeyStore.setKeyEntry(alias, key, DEFAULT_PASSWORD, chain);
                    }
                }

                dumpKeyStore(mandateKeyStore);
            } catch (Exception e) {
                LOG.error("Invalid keystore configuration", e);
                throw new IllegalArgumentException(I18nHelper.getLabel("error.keystore"), e);
            }
        }
        return mandateKeyStore;
    }

    /**
     * Clear keystore.
     */
    public void clearKeystore() {

        keyStore = null;
    }

    /**
     * Clear keystore.
     */
    public void clearOldKeystore() {

        oldKeyStore = null;
    }

    /**
     * Clear keystore.
     */
    public void clearMandateKeystore() {

        mandateKeyStore = null;
    }

    /**
     * Dump key store.
     *
     * @param keyStore the key store
     */
    private void dumpKeyStore(KeyStore keyStore) {

        try {
            LOG.debug("Current Keystore content : ================");
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                StringBuffer sb = new StringBuffer();
                sb.append("alias=" + alias + " : ");
                sb.append("isCertificateEntry=" + keyStore.isCertificateEntry(alias) + ",");
                sb.append("isKey=" + keyStore.isKeyEntry(alias) + ",");
                LOG.debug(sb);
            }
            LOG.debug("========================================");
        } catch (KeyStoreException e) {
            LOG.error("KeyStoreException", e);
        }
    }

    public static String getThumbPrint(X509Certificate cert)
            throws NoSuchAlgorithmException, CertificateEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] der = cert.getEncoded();
        md.update(der);
        byte[] digest = md.digest();
        return hexify(digest);
    }

    public static String hexify(byte bytes[]) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            buf.append(hexDigits[(bytes[i] & 0xf0) >> 4]);
            buf.append(hexDigits[bytes[i] & 0x0f]);
        }
        return buf.toString();
    }

    /**
     * Inits the sealing.
     *
     * @return the data sealer
     * @throws KeyStoreException          the key store exception
     * @throws UnrecoverableKeyException  the unrecoverable key exception
     * @throws NoSuchAlgorithmException   the no such algorithm exception
     * @throws CertificateException       the certificate exception
     * @throws IOException                Signals that an I/O exception has occurred.
     * @throws IntegrationModuleException
     * @throws IntegrationModuleException
     */
    public DataSealer initSealing() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException, IntegrationModuleException {

        // 0. BouncyCastle must be added as a security provider
        // because the ehealth.etee.crypto library depends on it.
        Security.addProvider(new BouncyCastleProvider());

        // 1.0. Get the DataSealerFactory
//        DataSealerFactory dataSealerFactory = DataSealerFactory.getInstance();
        // 1.1. Get the sender's private authentication key for signature
        // creation
        PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        PrivateKey clientAuthenticationKey = keyAndCerts.getPrivateKey();

        // 1.2. Get the sender's authentication certificate that matches the
        // authentication key
        X509Certificate clientAuthCertificate = getCertificate();
        LOG.debug("Encryption initialized for SubjectDN: " + clientAuthCertificate.getSubjectDN());
        LOG.debug("Encryption initialized for SerialNumber: " + clientAuthCertificate.getSerialNumber());
        LOG.debug("Encryption initialized for ThumbPrint: " + getThumbPrint(clientAuthCertificate));

        // 1.3 Get the DataSealer for client
        final SigningCredential signingCredential = SigningCredential.create(clientAuthenticationKey, clientAuthCertificate);
        DataSealer dataSealer = DataSealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(SigningPolicy.EHEALTH_CERT, signingCredential).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT)
                .addSecretKeyPolicy(EncryptionPolicy.UNKNOWN_RECIPIENT).build();

        return dataSealer;
    }

    public DataSealer initOldSealing() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IntegrationModuleException {

        // 0. BouncyCastle must be added as a security provider
        // because the ehealth.etee.crypto library depends on it.
        Security.addProvider(new BouncyCastleProvider());

        // 1.0. Get the DataSealerFactory
//        DataSealerFactory dataSealerFactory = DataSealerFactory.getInstance();
        // 1.1. Get the sender's private authentication key for signature
        // creation
        PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getOldKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        PrivateKey clientAuthenticationKey = keyAndCerts.getPrivateKey();

        // 1.2. Get the sender's authentication certificate that matches the
        // authentication key
        X509Certificate clientAuthCertificate = getOldCertificate();
        LOG.debug("Encryption initialized for :" + clientAuthCertificate.getSubjectDN());

        // 1.3 Get the DataSealer for client
        final SigningCredential signingCredential = SigningCredential.create(clientAuthenticationKey, clientAuthCertificate);
        DataSealer dataSealer = DataSealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(SigningPolicy.EHEALTH_CERT, signingCredential).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT)
                .addSecretKeyPolicy(EncryptionPolicy.UNKNOWN_RECIPIENT).build();
        return dataSealer;
    }

    /**
     * Inits the unsealing.
     *
     * @return the data unsealer
     * @throws CertificateException       the certificate exception
     * @throws IOException                Signals that an I/O exception has occurred.
     * @throws KeyStoreException          the key store exception
     * @throws NoSuchAlgorithmException   the no such algorithm exception
     * @throws UnrecoverableKeyException  the unrecoverable key exception
     * @throws IntegrationModuleException
     * @throws IntegrationModuleException
     */
    public DataUnsealer initUnsealing() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, IntegrationModuleException {

        // 1. Create recipe DataUnsealer
        // 1.0. BouncyCastle must be added as a security provider because
        // the ehealth.etee.crypto library depends on it.
        Security.addProvider(new BouncyCastleProvider());

        // 1.1 Get a DataUnsealerFactory
//        DataUnsealerFactory dataUnsealerFactory = DataUnsealerFactory.getInstance();
        KeyStore caCertificatesKeystore = KeyManager.getKeyStore(getCaCertificateKeystoreIs(), propertyHandler.getProperty(LOCAL_CA_CERTIFICATES_STORE_TYPE), propertyHandler.getProperty(LOCAL_CA_CERTIFICATES_STORE_PASSWORD).toCharArray());

        Map<String, PrivateKey> clientDecryptionKeys = KeyManager.getDecryptionKeys(getKeyStore(), DEFAULT_PASSWORD);

        for (String key : clientDecryptionKeys.keySet()) {
            LOG.debug("Key Available for decryption : " + key);
        }

//        // 1.4 Choose your RevocationStatusChecker
//        RevocationStatusChecker revocationStatusChecker = new MockRevocationStatusChecker(); // should
//                                                                                             // be
//                                                                                             // the
//                                                                                             // OCSP
//                                                                                             // checker
        // 1.5 Now you have all what is necessary to create recipe's
        // DataUnsealer
        DataUnsealer dataUnsealer = DataUnsealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(caCertificatesKeystore, SigningPolicy.EHEALTH_CERT, SigningPolicy.EID)
                .addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT, EncryptionCredentials.from(clientDecryptionKeys)).build();

        return dataUnsealer;
    }

    public DataUnsealer initOldUnSealing() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, IntegrationModuleException {

        // 1.0. BouncyCastle must be added as a security provider because
        // the ehealth.etee.crypto library depends on it.
        Security.addProvider(new BouncyCastleProvider());

        KeyStore caCertificatesKeystore = KeyManager.getKeyStore(getCaCertificateKeystoreIs(), propertyHandler.getProperty(LOCAL_CA_CERTIFICATES_STORE_TYPE), propertyHandler.getProperty(LOCAL_CA_CERTIFICATES_STORE_PASSWORD).toCharArray());

        Map<String, PrivateKey> clientDecryptionKeys = KeyManager.getDecryptionKeys(getOldKeyStore(), DEFAULT_PASSWORD);

        for (String key : clientDecryptionKeys.keySet()) {
            LOG.debug("Key Available for decryption : " + key);
        }

//        // 1.4 Choose your RevocationStatusChecker
//        RevocationStatusChecker revocationStatusChecker = new MockRevocationStatusChecker(); // should
//                                                                                             // the
//                                                                                             // OCSP
//                                                                                             // checker
        // 1.5 Now you have all what is necessary to create recipe's DataUnsealer
        DataUnsealer dataUnsealer = DataUnsealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(caCertificatesKeystore, SigningPolicy.EHEALTH_CERT, SigningPolicy.EID)
                .addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT, EncryptionCredentials.from(clientDecryptionKeys)).build();
        return dataUnsealer;
    }

    /**
     * Unlock personal key.
     *
     * @param niss the niss
     * @param pwd  the pwd
     * @throws IntegrationModuleException the integration module exception
     */
    public void unlockPersonalKey(String niss, String pwd) throws IntegrationModuleException {

        String file = null;

        if (StringUtils.isEmpty(pwd)) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.personal.keystore.password.null"));
        }

        if (propertyHandler.hasProperty(PERSONAL_KEYSTORE)) {
            file = propertyHandler.getProperty(PERSONAL_KEYSTORE);
        } else {
            if (!propertyHandler.hasProperty(PROP_KEYSTORE_P12_FOLDER)) {
                LOG.error("Undefined property " + PROP_KEYSTORE_P12_FOLDER);
                throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.personal.keystore"));
            }

            List<String> files = IOUtils.getP12FileList(propertyHandler.getProperty(PROP_KEYSTORE_P12_FOLDER), niss);
            if (files.size() == 0) {
                LOG.error("No P12 file found marching pattern '*" + niss + "*.p12' in folder " + propertyHandler.getProperty(PROP_KEYSTORE_P12_FOLDER));
                throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.personal.keystore.pattern", new Object[]{niss}));
            }
            if (files.size() > 1) {
                LOG.error("Too many files matching pattern '*" + niss + "*.p12', please clean folder : " + propertyHandler.getProperty(PROP_KEYSTORE_P12_FOLDER));
                throw new IntegrationModuleException(I18nHelper.getLabel("error.property.found.to.much.keystores"));
            }

            file = files.get(0);
        }

        try {

            InputStream stream = IOUtils.getResourceAsStream(file);

            // Loading personal keystore
            KeyStore personalKeyStore = KeyManager.getKeyStore(stream, PKCS12, pwd.toCharArray());

            X509Certificate certificate = (X509Certificate) personalKeyStore.getCertificate(AUTHENTICATION_ALIAS);
            if (certificate != null) {
                try {
                    certificate.checkValidity();
                } catch (CertificateExpiredException e) {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.expired.personal.certificate"), e);
                } catch (CertificateNotYetValidException e) {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.personal.certificate"), e);
                }
            } else {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.notfound.personal.certificate"));
            }

            Enumeration<String> aliases = personalKeyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                LOG.debug("Importing : " + alias);
                Certificate[] chain = personalKeyStore.getCertificateChain(alias);
                Key key = personalKeyStore.getKey(alias, pwd.toCharArray());
                if (key != null) {
                    String tAlias = AUTHENTICATION_ALIAS.equals(alias) ? AUTHENTICATION_FALLBACK_ALIAS : alias;
                    getKeyStore().setKeyEntry(tAlias, key, DEFAULT_PASSWORD, chain);
                }
            }

        } catch (IOException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.password"), e);
        } catch (KeyStoreException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), e);
        } catch (NoSuchAlgorithmException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), e);
        } catch (CertificateException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), e);
        } catch (UnrecoverableKeyException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), e);
        }
    }

    /**
     * Unsealing data.
     *
     * @param rslt the rslt
     * @return the byte[]
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public byte[] unsealingData(CryptoResult<UnsealedData> rslt) throws IOException {

        byte[] unsealedData = null;
        // 3. Process the result of the unseal operation
        if (rslt.hasData()) { // 3.A. The decryption operation succeeded
            if (!rslt.hasErrors()) { // 3.A.A. There are no errors or failures
                // 3.A.A.1. Get the author
                LOG.debug("from author: " + rslt.getData().getAuthenticationCert().getSubjectDN());
                // 3.A.A.2. Get the unsealed data
                InputStream unsealedDataStream = rslt.getData().getContent();
                unsealedData = IOUtils.getBytes(unsealedDataStream);
                LOG.debug("unsealed data: " + new String(unsealedData));

            } else { // 3.A.B. The data authenticity is not OK
                // 3.A.B.1. Get the DataAuthenticationErrors or
                // DataAuthenticationFailures
                // and do your specific security failure or error processing
                // BEFORE reading the unsealed data (otherwise you will have an
                // RuntimeException
                for (NotificationError error : rslt.getErrors()) {
                    // e.g.
                    LOG.error("error: " + error);
                }
                for (NotificationWarning warning : rslt.getWarnings()) {
                    // e.g.
                    LOG.warn("failure: " + warning);
                }
                if (rslt.getFatal() != null) {
                    // e.g.
                    LOG.fatal("Fatal: " + rslt.getFatal().getErrorMessage());
                }
                // 3.A.B.2. After checking the errors and failures you can get
                // the unsealed data
                InputStream unsealedDataStream = rslt.getData().getContent();
                unsealedData = IOUtils.getBytes(unsealedDataStream);
                LOG.debug("unsealed data: " + new String(unsealedData));

                // 3.A.B.3. and in most cases also the author
                if (!rslt.getErrors().contains(NotificationError.INNER_CERTIFICATE_EXPECTED_BUT_NOT_PRESENT)) {
                    LOG.debug("author certificate: " + rslt.getData().getAuthenticationCert());
                }
            }
        } else { // 3.B the decryption failed, there is no decrypted data
            LOG.debug("the msg could not be unsealed, because:" + rslt.getFatal());
        }
        return unsealedData;
    }

    /**
     * Gets the ca certificate keystore is.
     *
     * @return the ca certificate keystore is
     * @throws IOException                Signals that an I/O exception has occurred.
     * @throws IntegrationModuleException
     */
    private InputStream getCaCertificateKeystoreIs() throws IntegrationModuleException {

        try {
            return IOUtils.getResourceAsStream(getCaCertificateKeystoreLocation());
        } catch (IOException e) {
            throw new IntegrationModuleException("error.loading.cakeystore", e);
        }
    }

    private String getCaCertificateKeystoreLocation() {

        if (caCertificateKeystoreLocation == null) {
            caCertificateKeystoreLocation = propertyHandler.getProperty(KEYSTORE_DIR) + propertyHandler.getProperty(LOCAL_CA_CERTIFICATES_STORE_FILE);
        }
        return caCertificateKeystoreLocation;
    }

    private String getCaCertificateKeystorePwd() throws IntegrationModuleException {

        if (propertyHandler.hasProperty(LOCAL_CA_CERTIFICATES_STORE_PASSWORD)) {
            return propertyHandler.getProperty(LOCAL_CA_CERTIFICATES_STORE_PASSWORD);
        }

        throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.password"));
    }

    public KeyStore loadCaKeystore() throws IntegrationModuleException {

        return loadKeyStore(getCaCertificateKeystoreIs(), getCaCertificateKeystorePwd().toCharArray());
    }

    public KeyStore loadKeyStore(InputStream stream, char[] pwd) throws IntegrationModuleException {

        KeyStore keyStore = null;
        try {
            keyStore = KeyManager.getKeyStore(stream, "JKS", pwd);
        } catch (IOException ex) {
            if (ex.getCause() instanceof javax.crypto.BadPaddingException) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.password"), ex);
            } else {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"), ex);
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return keyStore;
    }

    /**
     * Gets the certificate.
     *
     * @return the certificate
     * @throws IntegrationModuleException
     * @throws IntegrationModuleException
     */
    public X509Certificate getCertificate() throws IntegrationModuleException {

        try {
            return (X509Certificate) getKeyStore().getCertificate(AUTHENTICATION_ALIAS);
        } catch (KeyStoreException e) {
            LOG.error("KeyStoreException", e);
            return null;
        }
    }

    public X509Certificate getOldCertificate() throws IntegrationModuleException {

        try {
            return (X509Certificate) getOldKeyStore().getCertificate(AUTHENTICATION_ALIAS);
        } catch (KeyStoreException e) {
            LOG.error("KeyStoreException", e);
            return null;
        }
    }

    /**
     * Gets the private keyStore for authentication
     *
     * @param keyStore
     * @return private keyStore
     */
    private PrivateKey getPrivateKey(KeyStore keyStore, String privateKeyAlias, char[] privateKeyPassword) {

        try {
            PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(keyStore, privateKeyAlias, privateKeyPassword);
            return keyAndCerts.getPrivateKey();
        } catch (UnrecoverableKeyException e) {
            LOG.error("UnrecoverableKeyException", e);
            return null;
        }
    }

    /**
     * Gets the public keyStore for authentication.
     *
     * @param keyStore
     * @return the public keyStore
     */
    private PublicKey getPublicKey(KeyStore keyStore, String privateKeyAlias, char[] privateKeyPassword) {

        try {
            PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(keyStore, privateKeyAlias, privateKeyPassword);
            return keyAndCerts.getCertificate().getPublicKey();
        } catch (UnrecoverableKeyException e) {
            LOG.error("UnrecoverableKeyException", e);
            return null;
        }
    }

    /**
     * Gets the private key for authentication.
     *
     * @return the private key
     * @throws IntegrationModuleException
     */
    public PrivateKey getPrivateKey() throws IntegrationModuleException {

        return getPrivateKey(getKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getKeyStore(),
        // AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // return keyAndCerts.getPrivateKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    public PrivateKey getOldPrivateKey() throws IntegrationModuleException {

        return getPrivateKey(getOldKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getOldKeyStore(),
        // AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // return keyAndCerts.getPrivateKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    /**
     * Gets the public key for authentication.
     *
     * @return the public key
     * @throws IntegrationModuleException
     * @throws IntegrationModuleException
     */
    public PublicKey getPublicKey() throws IntegrationModuleException {

        return getPublicKey(getKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getKeyStore(),
        // AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // return keyAndCerts.getCertificate().getPublicKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    public PublicKey getOldPublicKey() throws IntegrationModuleException {

        return getPublicKey(getOldKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getOldKeyStore(),
        // AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // return keyAndCerts.getCertificate().getPublicKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    /**
     * Gets the private key for authentication.
     *
     * @return the private key
     */
    public PrivateKey getMandatePrivateKey() {

        return getPrivateKey(getMandateOrganisationKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts =
        // KeyManager.getKeyAndCertificates(getMandateOrganisationKeyStore(), AUTHENTICATION_ALIAS,
        // DEFAULT_PASSWORD);
        // return keyAndCerts.getPrivateKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    /**
     * Gets the public key for authentication.
     *
     * @return the public key
     */
    public PublicKey getMandatePublicKey() {

        return getPublicKey(getMandateOrganisationKeyStore(), AUTHENTICATION_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts =
        // KeyManager.getKeyAndCertificates(getMandateOrganisationKeyStore(), AUTHENTICATION_ALIAS,
        // DEFAULT_PASSWORD);
        // return keyAndCerts.getCertificate().getPublicKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    /**
     * Gets the fallback private key.
     *
     * @return the fallback private key
     * @throws IntegrationModuleException
     */
    public PrivateKey getFallbackPrivateKey() throws IntegrationModuleException {

        return getPrivateKey(getKeyStore(), AUTHENTICATION_FALLBACK_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getKeyStore(),
        // AUTHENTICATION_FALLBACK_ALIAS, DEFAULT_PASSWORD);
        // return keyAndCerts.getPrivateKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    /**
     * Gets the fallback public key.
     *
     * @return the fallback public key
     * @throws IntegrationModuleException
     */
    public PublicKey getFallbackPublicKey() throws IntegrationModuleException {

        return getPublicKey(getKeyStore(), AUTHENTICATION_FALLBACK_ALIAS, DEFAULT_PASSWORD);
        // try {
        // PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getKeyStore(),
        // AUTHENTICATION_FALLBACK_ALIAS, DEFAULT_PASSWORD);
        // return keyAndCerts.getCertificate().getPublicKey();
        // } catch (UnrecoverableKeyException e) {
        // LOG.error("UnrecoverableKeyException", e);
        // return null;
        // } catch (KeyStoreException e) {
        // LOG.error("KeyStoreException", e);
        // return null;
        // } catch (NoSuchAlgorithmException e) {
        // LOG.error("NoSuchAlgorithmException", e);
        // return null;
        // }
    }

    /**
     * Gets the fallback certificate.
     *
     * @return the fallback certificate
     * @throws IntegrationModuleException
     */
    public X509Certificate getFallbackCertificate() throws IntegrationModuleException {

        try {
            return (X509Certificate) getKeyStore().getCertificate(AUTHENTICATION_FALLBACK_ALIAS);
        } catch (KeyStoreException e) {
            LOG.error("KeyStoreException", e);
            return null;
        }
    }

    public X509Certificate getMandateCertificate() {

        try {
            return (X509Certificate) getMandateOrganisationKeyStore().getCertificate(AUTHENTICATION_ALIAS);
        } catch (KeyStoreException e) {
            LOG.error("KeyStoreException", e);
            return null;
        }
    }

    /**
     * Verify decryption.
     *
     * @param myETK the my etk
     * @throws IntegrationModuleException the integration module exception
     */
    public void verifyDecryption(EncryptionToken myETK) throws IntegrationModuleException {

        boolean found = false;
        try {
            Enumeration<String> aliases = getKeyStore().aliases();
            while (aliases.hasMoreElements()) {
                try {
                    String alias = aliases.nextElement();
                    LOG.debug("verifyDecryption : " + alias);
                    PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(getKeyStore(), alias, DEFAULT_PASSWORD);
                    myETK.getCertificate().verify(keyAndCerts.getCertificate().getPublicKey());
                    found = true;
                } catch (UnrecoverableKeyException e) {
                } catch (NoSuchAlgorithmException e) {
                } catch (InvalidKeyException e) {
                } catch (CertificateException e) {
                } catch (NoSuchProviderException e) {
                } catch (SignatureException e) {
                } finally {
                }
            }
        } catch (KeyStoreException e) {
        } finally {
        }

        if (!found) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.decryption.key"));
        }
    }

    /**
     * @param systemKeystorePassword the systemKeystorePassword to set
     */
    public void setSystemKeystorePassword(String systemKeystorePassword) {
        if (StringUtils.isNotBlank(systemKeystorePassword)) {
            propertyHandler.getProperties().put(PROP_KEYSTORE_PASSWORD, systemKeystorePassword);
        }
    }

    public String getSystemKeystorePassword() throws IntegrationModuleException {

        if (propertyHandler.hasProperty(PROP_KEYSTORE_PASSWORD)) {
            return propertyHandler.getProperty(PROP_KEYSTORE_PASSWORD);
        }
        throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.password"));
    }


    /**
     * @param systemKeystorePath the systemKeystorePath to set
     */
    public void setSystemKeystorePath(String systemKeystorePath) {
        if (StringUtils.isNotBlank(systemKeystorePath)) {
            this.systemKeystorePath = systemKeystorePath;
        }
    }


    /**
     * @param systemKeystoreDirectory the systemKeystoreDirectory to set
     */
    public void setSystemKeystoreDirectory(String systemKeystoreDirectory) {
        if (StringUtils.isNotBlank(systemKeystoreDirectory)) {
            propertyHandler.getProperties().put(PROP_KEYSTORE_P12_FOLDER, systemKeystoreDirectory);
        }
    }

    /**
     * @param systemKeystoreRiziv the systemKeystoreRiziv to set
     */
    public void setSystemKeystoreRiziv(String systemKeystoreRiziv) {
        if (StringUtils.isNotBlank(systemKeystoreRiziv)) {
            propertyHandler.getProperties().put(PROP_KEYSTORE_RIZIV_KBO, systemKeystoreRiziv);
        }
    }

    /**
     * @param oldSystemKeystorePath the oldSystemKeystorePath to set
     */
    public void setOldSystemKeystorePath(String oldSystemKeystorePath) {
        if(StringUtils.isNotBlank(oldSystemKeystorePath)) {
            this.oldSystemKeystorePath = oldSystemKeystorePath;
        }
    }


    /**
     * @param oldSystemKeystorePassword the oldSystemKeystorePassword to set
     */
    public void setOldSystemKeystorePassword(String oldSystemKeystorePassword) {
        if (StringUtils.isNotBlank(oldSystemKeystorePassword)) {
            propertyHandler.setProperty(PROP_OLD_KEYSTORE_PASSWORD, oldSystemKeystorePassword);
        }
    }

    public String getOldSystemKeystorePassword() {
        return propertyHandler.getProperty(PROP_OLD_KEYSTORE_PASSWORD);
    }

    /**
     * @param oldSystemKeystoreDirectory the oldSystemKeystoreDirectory to set
     */
    public void setOldSystemKeystoreDirectory(String oldSystemKeystoreDirectory) {
        if (StringUtils.isNotBlank(oldSystemKeystoreDirectory)) {
            propertyHandler.getProperties().put(PROP_KEYSTORE_OLD_P12_FOLDER, oldSystemKeystoreDirectory);
        }
    }

    /**
     * @return the oldSystemKeystoreDirectory
     */
    public String getOldSystemKeystoreDirectory() {
        return propertyHandler.getProperty(PROP_KEYSTORE_OLD_P12_FOLDER);
    }

    /**
     * @param oldSystemKeystoreRiziv the oldSystemKeystoreRiziv to set
     */
    public void setOldSystemKeystoreRiziv(String oldSystemKeystoreRiziv) {
        if (StringUtils.isNotBlank(oldSystemKeystoreRiziv)) {
            propertyHandler.getProperties().put(PROP_OLD_KEYSTORE_RIZIV_KBO, oldSystemKeystoreRiziv);
        }
    }

    /**
     * @return the oldSystemKeystoreRiziv
     */
    public String getOldSystemKeystoreRiziv() {
        return propertyHandler.getProperty(PROP_OLD_KEYSTORE_RIZIV_KBO);
    }

    /**
     * queueEncrypt / queueDecryt works only for marshaled object.
     *
     * @param data
     * @param publicKey
     * @return
     * @throws IntegrationModuleException
     */
    public byte[] queueEncrypt(byte[] data, PublicKey publicKey) throws IntegrationModuleException {

        String s = new String(data);
        s += encryptionSignature;
        data = s.getBytes();
        try {
            String key = publicKey.toString().substring(0, 16);
            final SecretKeySpec symKey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");

            int rest = 0;
            int length = 0;
            byte[] newbuf = null;

            if (cipher.getBlockSize() > 0) {
                rest = data.length % cipher.getBlockSize();
                length = data.length + (cipher.getBlockSize() - rest);

                newbuf = new byte[length];
                System.arraycopy(data, 0, newbuf, 0, data.length);
            } else {
                newbuf = new byte[data.length];
                System.arraycopy(data, 0, newbuf, 0, data.length);
            }

            cipher.init(Cipher.ENCRYPT_MODE, symKey);
            byte[] cipherData = cipher.doFinal(newbuf);

            return Base64.encode(cipherData);
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    /**
     * queueEncrypt / queueDecryt works only for marshaled object.
     *
     * @param data
     * @param publicKey
     * @return
     * @throws IntegrationModuleException
     */
    public String queueDecrypt(byte[] data, PublicKey publicKey) throws IntegrationModuleException {

        try {
            LOG.debug("**************** SIZE BYTES ********** " + data.length);

            byte[] decodeData = Base64.decode(data);

            String key = publicKey.toString().substring(0, 16);
            final SecretKeySpec symKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, symKey);
            byte[] cipherData = cipher.doFinal(decodeData);
            LOG.debug("************** cipherData ************* " + Arrays.toString(cipherData));

            String s = new String(cipherData);// , "UTF-8");
            LOG.debug("******************** GOING OUT: " + s + " **********************");

            // int c = s.indexOf(">") +1;
            //
            // s = s.replace(s.substring(0, c),"");
            // System.out.println("******************** GOING OUT SUBSTRING: " +
            // s + " **********************");
            //
            int i = s.lastIndexOf(encryptionSignature);
            // int i = s.lastIndexOf(">") + 1;
            s = s.substring(0, i);

            LOG.debug("******************** GOING OUT SUBSTRING2: " + s + " **********************");

            return s;

        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

}
