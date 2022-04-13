package be.business.connector.common;

import static be.business.connector.core.utils.RecipeConstants.CONFIG;
import static be.business.connector.core.utils.RecipeConstants.CONFIG_VALIDATION;
import static be.business.connector.core.utils.RecipeConstants.MOCK;
import static be.business.connector.core.utils.RecipeConstants.SESSION;
import static be.business.connector.core.utils.RecipeConstants.SYSTEM_KEYSTORE;
import static be.business.connector.core.utils.RecipeConstants.TRUE;
import static be.business.connector.core.utils.RecipeConstants.YYYY_MM_DD;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.session.SessionUtil;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.recipe.common.exceptions.RecipeException;

public class AbstractCommandLine {

	private static final PrintStream OUT = System.out;

	public AbstractCommandLine() {
	}

    /**
     * Writes data to the given fileLocation
     *
     * @param fileLocation
     * @param data
     * @throws IOException
     */
	public static void writeFile(final String fileLocation, final String data) throws IOException {
        if (StringUtils.isNotBlank(fileLocation)) {
			final File file = new File(fileLocation);
			try (FileOutputStream fos = new FileOutputStream(file);) {
				fos.write(data.getBytes(UTF_8));
			}
        }
    }

    /**
	 * Utility method: jaxb write a given entity to file
	 *
	 * @param location
	 * @return the XML that has been written.
	 * @throws RecipeException
	 * @throws IOException
	 */
	public static <T> String writeXmlToFile(final Class<T> clazz, final T entity, final String location) throws RecipeException, IOException {
        final String data = JaxContextCentralizer.getInstance().toXml(clazz, entity);
        writeFile(location, data);
        return data;
    }

	public static <T> T readXmlFromFile(final Class<T> clazz, final String location) throws RecipeException, IOException {
        final String data = FileUtils.readFileToString(new File(location));
		return JaxContextCentralizer.getInstance().toObject(clazz, data);
    }


	protected static byte[] loadFile(final String path) throws IOException {
        if (path == null) {
			return new byte[0];
        }
		final byte[] file = FileUtils.readFileToByteArray(new File(path));
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 3);
		final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		String prescription = new String(file);
		prescription = prescription.replaceAll("EXPIRATION_DATE", sdf.format(calendar.getTime()));
		return prescription.getBytes();
    }

    /**
     * Initializes the system based on the configFile specified as a system property
     */
    protected static void initializeSystem() throws Exception {
		final String configFile = StringUtils.defaultString(System.getProperty(CONFIG));
		final String configValidationFile = StringUtils.defaultString(System.getProperty(CONFIG_VALIDATION));
        ApplicationConfig.getInstance().initialize(configFile, configValidationFile);
		if (!TRUE.equals(System.getProperty(MOCK))) {
            loadSystemKeystoreFile();
            loadSession();
        }
    }

    private static void loadSession() throws Exception {
		if (System.getProperty(SESSION) == null) {
            throw new IllegalArgumentException("No session info found, please provide -Dsession=<session-file> to specify the session");
        }

		final String systemKeystorePassword = EncryptionUtils.getInstance().getSystemKeystorePassword();
		final File systemKeystorePath = new File(EncryptionUtils.getInstance().getSystemKeystorePath());

        final Properties configuration = PropertyHandler.getInstance().getPropertiesCopy();
        configuration.put(EncryptionUtils.PROP_KEYSTORE_PASSWORD, systemKeystorePassword);
        configuration.put(EncryptionUtils.PROP_KEYSTORE_P12_FOLDER, systemKeystorePath.getParent() + File.separator);
        configuration.put(EncryptionUtils.PROP_KEYSTORE_FILE, systemKeystorePath.getName());

        OUT.println("Loading session from " + System.getProperty(SESSION));
		final String session = new String(loadFile(System.getProperty(SESSION)), UTF_8);
        final SAMLToken samlToken = SessionUtil.createSAMLToken(configuration, session);
        SessionUtil.loadExistingSession(configuration, samlToken);
    }

	private static void loadSystemKeystoreFile() throws IOException {
		if (System.getProperty(SYSTEM_KEYSTORE) != null) {
            OUT.println("Loading system keystore properties from " + System.getProperty(SYSTEM_KEYSTORE));
			final String systemKeystore = new String(loadFile(System.getProperty(SYSTEM_KEYSTORE)), UTF_8);
			final String[] listProperties = systemKeystore.split(";");

            for (int i = 0; i < listProperties.length; i++) {
                if (listProperties[i].equals("null")) {
                    listProperties[i] = null;
                }
            }
            ApplicationConfig.getInstance().setSystemKeystoreProperties(listProperties[0], listProperties[1], listProperties[2], listProperties[3]);
        }
    }
}