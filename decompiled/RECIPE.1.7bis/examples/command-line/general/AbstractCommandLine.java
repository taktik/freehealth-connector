package be.business.connector.common;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import be.business.connector.session.SessionUtil;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class AbstractCommandLine {
    private static final PrintStream OUT = System.out;


    /**
     * Writes data to the given fileLocation
     *
     * @param fileLocation
     * @param data
     * @throws IOException
     */
    public static void writeFile(String fileLocation, String data) throws IOException {
        if (StringUtils.isNotBlank(fileLocation)) {
            File file = new File(fileLocation);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes("UTF-8"));
            fos.close();
        }
    }

    /**
     * Utility method: jaxb write a given entity to file
     *
     * @param location
     * @return the XML that has been written.
     */
    public static <T> String writeXmlToFile(final Class<T> clazz, final T entity, String location) throws GFDDPPException, IOException {
        final String data = JaxContextCentralizer.getInstance().toXml(clazz, entity);
        writeFile(location, data);
        return data;
    }

    public static <T> T readXmlFromFile(final Class<T> clazz, String location) throws IOException, GFDDPPException {
        final String data = FileUtils.readFileToString(new File(location));
        T result = JaxContextCentralizer.getInstance().toObject(clazz, data);
        return result;
    }


    protected static byte[] loadFile(String path) throws IOException {
        if (path == null) {
            return null;
        }
        return FileUtils.readFileToByteArray(new File(path));
    }

    /**
     * Initializes the system based on the configFile specified as a system property
     */
    protected static void initializeSystem() throws Exception {

        if ("true".equals(System.getProperty("mock"))) {
            // mocks do not need a configfile nor a session
            ApplicationConfig.getInstance().initialize();
        } else {
            final String configFile = StringUtils.defaultString(System.getProperty("config"));
            final String configValidationFile = StringUtils.defaultString(System.getProperty("configValidation"));
            ApplicationConfig.getInstance().initialize(configFile, configValidationFile);
            loadSystemKeystoreFile();
            loadSession();
        }
    }

    private static void loadSession() throws Exception {
        if (System.getProperty("session") == null) {
            throw new IllegalArgumentException("No session info found, please provide -Dsession=<session-file> to specify the session");
        }

        String systemKeystorePassword = EncryptionUtils.getInstance().getSystemKeystorePassword();
        File systemKeystorePath = new File(EncryptionUtils.getInstance().getSystemKeystorePath());

        final Properties configuration = PropertyHandler.getInstance().getPropertiesCopy();
        configuration.put(EncryptionUtils.PROP_KEYSTORE_PASSWORD, systemKeystorePassword);
        configuration.put(EncryptionUtils.PROP_KEYSTORE_P12_FOLDER, systemKeystorePath.getParent() + File.separator);
        configuration.put(EncryptionUtils.PROP_KEYSTORE_FILE, systemKeystorePath.getName());

        OUT.println("Loading session from " + System.getProperty("session"));
        final String session = new String(loadFile(System.getProperty("session")), "UTF-8");
        final SAMLToken samlToken = SessionUtil.createSAMLToken(configuration, session);
        SessionUtil.loadExistingSession(configuration, samlToken);
    }

    private static void loadSystemKeystoreFile() throws Exception {
        String[] listProperties = null;

        if (System.getProperty("systemKeystore") != null) {
            OUT.println("Loading system keystore properties from " + System.getProperty("systemKeystore"));
            String systemKeystore = new String(loadFile(System.getProperty("systemKeystore")), "UTF-8");
            listProperties = systemKeystore.split(";");

            for (int i = 0; i < listProperties.length; i++) {
                if (listProperties[i].equals("null")) {
                    listProperties[i] = null;
                }
            }
            ApplicationConfig.getInstance().setSystemKeystoreProperties(listProperties[0], listProperties[1], listProperties[2], listProperties[3]);

        }
    }

}
