package be.business.connector.session;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.SAML10Converter;
import be.ehealth.technicalconnector.session.SessionItem;

/**
 * @author Liesje Demuynck.
 */
public class SessionUtilCommandLine {
    /**
     * The Constant OUT.
     */
    private static final PrintStream OUT = System.out;

    public static void main(String[] args) throws Exception {

        if ("true".equals(System.getProperty("mock"))) {
            // mocks do not need a configfile nor a session
            OUT.println("Session creation not supported for mocks");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("null")) {
                args[i] = null;
            }
        }

        if (args.length == 7 && args[0].equals("createSession")) {
            createSession(args[1], args[2], args[3], args[4], args[5], args[6]);

            OUT.println("Session created");
        } else if (args.length == 9 && args[0].equals("createFallbackSession")) {
            createFallbackSession(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);

            OUT.println("Session created");

        } else {
            if (args.length > 0) {
                OUT.println("ERROR : Invalid number of arguments");
            }
            showHelp();
        }
    }

    private static void createSession(String path, String pathKeystorePropertiesFile, String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, final String systemKeystoreRizivKBO)
            throws Exception {

        final Properties configuration = buildConfiguration(systemKeystorePassword, systemKeystorePath, systemKeystoreDirectory, systemKeystoreRizivKBO);
        createSystemKeystorePropertiesFile(pathKeystorePropertiesFile, systemKeystorePassword, systemKeystorePath, systemKeystoreDirectory, systemKeystoreRizivKBO);
        final SessionItem sessionItem = SessionUtil.createSession(SessionType.EID_SESSION, configuration, null, null);

        if (path != null){
            FileUtils.writeStringToFile(new File(path), SAML10Converter.toXMLString(sessionItem.getSAMLToken().getAssertion()));
            OUT.println("Session saved in " + path);
        }

    }

    private static void createFallbackSession(String path, String pathKeystorePropertiesFile,
                                              String niss, String passphrase,
                                              String systemKeystorePassword,
                                              String systemKeystorePath,
                                              String systemKeystoreDirectory,
                                              String systemKeystoreRizivKBO) throws Exception {


        final Properties configuration = buildConfiguration(systemKeystorePassword, systemKeystorePath, systemKeystoreDirectory, systemKeystoreRizivKBO);

        createSystemKeystorePropertiesFile(pathKeystorePropertiesFile, systemKeystorePassword, systemKeystorePath, systemKeystoreDirectory, systemKeystoreRizivKBO);
        final SessionItem sessionItem = SessionUtil.createSession(SessionType.FALLBACK_SESSION, configuration, niss, passphrase);

        if (path != null) {
            FileUtils.writeStringToFile(new File(path), SAML10Converter.toXMLString(sessionItem.getSAMLToken().getAssertion()), StandardCharsets.UTF_8);
            OUT.println("Session saved in " + path);
        }
    }

    private static Properties buildConfiguration(final String systemKeystorePassword, final String systemKeystorePath, final String systemKeystoreDirectory, final String systemKeystoreRizivKBO) throws IOException{
        final String configFileName = StringUtils.defaultString(System.getProperty("config"));
        final String validationFileName = StringUtils.defaultString(System.getProperty("configValidation"));

        if ((StringUtils.isBlank(configFileName) || !new File(configFileName).isFile())) {
            throw new IllegalStateException("Unable to create session: config file is not provided or invalid");
        }
        if ((StringUtils.isBlank(validationFileName) || !new File(validationFileName).isFile())) {
            throw new IllegalStateException("Unable to create session: validation file is not provided or invalid");
        }

        final Properties configuration = new Properties();
        final File configFile = new File(configFileName);
        final File validationFile = new File(configFileName);
        configuration.load(FileUtils.openInputStream(configFile));
        configuration.load(FileUtils.openInputStream(validationFile));
        String configPath = configFile.getParent().replace("\\", "/");
        for (String propertyName : configuration.stringPropertyNames()) {
            String propertyValue = configuration.getProperty(propertyName);
            configuration.setProperty(propertyName, propertyValue.replaceAll("%CONF%", configPath));
        }

        if (StringUtils.isNotBlank(systemKeystorePassword)) {
            configuration.put(EncryptionUtils.PROP_KEYSTORE_PASSWORD, systemKeystorePassword);
        }
        if (StringUtils.isNotBlank(systemKeystorePath)) {
            final File keystorePathFile = new File(systemKeystorePath);
            if (!keystorePathFile.exists() || !keystorePathFile.isFile()) {
                throw new IllegalStateException("Unable to create session: Provided systemKeystorePath is invalid");
            }
            configuration.put(EncryptionUtils.PROP_KEYSTORE_P12_FOLDER, keystorePathFile.getParentFile().getAbsolutePath() + File.separator);
            configuration.put(EncryptionUtils.PROP_KEYSTORE_FILE, keystorePathFile.getName());
        }
        if (StringUtils.isNotBlank(systemKeystoreDirectory) && StringUtils.isNotBlank(systemKeystoreRizivKBO)) {
            File matchingFile = null;
            final File directory = new File(systemKeystoreDirectory);
            if (directory.exists() && directory.isDirectory()) {
                File[] matchingFiles = directory.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
						return name.contains(systemKeystoreRizivKBO)
								&& (name.endsWith(".int-p12") || name.endsWith(".acc-p12") || name.endsWith(".p12"));
                    }
                });
                if(matchingFiles.length >= 1){
                    matchingFile = matchingFiles[0];
                }
            }

            if(matchingFile == null){
                throw new IllegalStateException("Unable to create session: Unable to find unique keystore file based on provided combination of keystore directory and riziv/kbo number.");
            }
            configuration.put(EncryptionUtils.PROP_KEYSTORE_P12_FOLDER, new File(systemKeystoreDirectory).getAbsolutePath() + File.separator);
            configuration.put(EncryptionUtils.PROP_KEYSTORE_FILE, matchingFile.getName());
        }
        return configuration;

    }

    public static void createSystemKeystorePropertiesFile(String pathKeystorePropertiesFile, String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreRizivKBO) throws IOException {
        if (pathKeystorePropertiesFile != null) {
            String line = systemKeystorePassword + ";" + systemKeystorePath + ";" + systemKeystoreDirectory + ";" + systemKeystoreRizivKBO;
            FileUtils.writeStringToFile(new File(pathKeystorePropertiesFile), line);
            OUT.println("Keystore properties saved in " + pathKeystorePropertiesFile);
        }
    }


    private static void showHelp() {
        StringBuffer sb = new StringBuffer();

        OUT.println("Usage :  java <classpath> <-Doption1=option2> "
                + SessionUtilCommandLine.class.getPackage() + "."
                + SessionUtilCommandLine.class.getName()
                + " [action] <arg1>...");

        OUT.println("");
        OUT.println("------------ Action available ------------");
        OUT.println(" createSession <arg1> <arg2> <arg3> <arg4> <arg5> <arg6>");
        OUT.println("	  arg1 : path to session file");
        OUT.println("	  arg2 : path to keystore file");
        OUT.println("	  arg3 : system keystore password");
        OUT.println("	  arg4 : system keystore path");
        OUT.println("	  arg5 : system keystore directory");
        OUT.println("	  arg6 : system keystore riziv");
		OUT.println("  ex : createSession c:/session.xml c:/systemKeystore.xml mypassword c:/P12/myKeystore.p12 c:/P12 62599147");
        OUT.println("");

		OUT.println(" createFallbackSession <arg1> <arg2> <arg3> <arg4> <arg5> <arg6> <arg7> <arg8>");
        OUT.println("	  arg1 : path to session file");
        OUT.println("	  arg2 : path to keystore file");
		OUT.println("	  arg3 : niss");
		OUT.println("	  arg4 : password");
		OUT.println("	  arg5 : system keystore password");
		OUT.println("	  arg6 : system keystore path");
		OUT.println("	  arg7 : system keystore directory");
		OUT.println("	  arg8 : system keystore riziv");
		OUT.println(
				"  ex : createFallbackSession c:/session.xml c:/systemKeystore.xml 84071230581 mypassword mypassword  c:/P12/ c:/P12/myKeystore.p12 62599147");
        OUT.println("");

        OUT.println("");
        OUT.println("-------------- OPTIONS ------------");
        OUT.println(" -Dconfig=<path to the config file> : specified a config file");

        OUT.println(sb.toString());
    }

}
