package be.apb.gfddpp.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import be.apb.gfddpp.validation.exception.SingleMessageValidationException;

public class SingleMessageValidationHelper {
//	private static final Logger LOG = Logger.getLogger(SingleMessageValidationHelper.class);
	private static final String SINGLEMESSAGE_XSD = "singlemessage.xsd";
	private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";

	private URL xsdPath = null;
	private PropertyHandler propertyHandler;

	public SingleMessageValidationHelper() {
		propertyHandler = new PropertyHandler("/smc.properties");
	}

	public SingleMessageValidationHelper(URL xsdPath) {
		this.xsdPath = xsdPath;
	}

	/**
	 * Assert valid kmehr prescription.
	 *
	 * @param xmlFile the xml file
	 * @param prescriptionType the prescription type
	 * @throws SingleMessageValidationException 
	 */
	public void assertValidSingleMessage(InputStream xmlFile) throws SingleMessageValidationException {
		String xmlString = getString(xmlFile);
		assertValidSingleMessage(xmlString);
	}

	public void assertValidSingleMessage(String xmlDocument) throws SingleMessageValidationException {
		xsdValidate(xmlDocument);
	}

	public void assertValidSingleMessage(byte[] xmlDocument) throws SingleMessageValidationException {
		try {
			xsdValidate(new String(xmlDocument, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
//			LOG.error("", e);
		}
	}

	public byte[] getBytes(InputStream inputStream) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) >= 0) {
				baos.write(buffer, 0, len);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String getString(InputStream inputStream) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) >= 0) {
				baos.write(buffer, 0, len);
			}
			return baos.toString("UTF-8");
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Xsd validate.
	 *
	 * @param xmlDocument the xml document
	 * @param xsdPropertyName the xsd property name
	 * @throws SingleMessageValidationException 
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void xsdValidate(String xmlDocument) throws SingleMessageValidationException {
		try {

			if (xsdPath == null && propertyHandler != null) {
				xsdPath = getClass().getResource(propertyHandler.getProperty(SINGLEMESSAGE_XSD));
			}

			//			URL url = this.getClass().getResource(xsdPath);

//			LOG.debug("***************************** XSD VALIDATION: XSD PATH = " + xsdPath + "***************************************");
//			LOG.debug("***************************** XSD VALIDATION: XSD URL = " + xsdPath.toString() + "***************************************");
			//			LOG.debug("***************************** XSD VALIDATION: FILE FOUND = " + new File(url.toURI()).exists() + "***************************************");

			if (xsdPath == null) {
				//					|| ( url !=null && !new File(url.toURI()).exists()) 
//				LOG.error("Property is not correctly set, invalid file " + SINGLEMESSAGE_XSD + " = " + xsdPath);
				throw new RuntimeException("Property is not correctly set");
			}

			//			File xsd = new File(url.toURI());

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setNamespaceAware(true);
			factory.setValidating(true);
			factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA_NS_URI);
			//	factory.setAttribute(	JAXP_SCHEMA_SOURCE, 	 url);

			factory.setAttribute(JAXP_SCHEMA_SOURCE, new String[] { xsdPath.toString() });

			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new ErrorHandler() {
				public void warning(SAXParseException arg0) throws SAXException {
//					LOG.warn("XSD Warning", arg0);
				}

				public void fatalError(SAXParseException arg0) throws SAXException {
//					LOG.debug("XSD fatalError", arg0);
					throw arg0;
				}

				public void error(SAXParseException arg0) throws SAXException {
//					LOG.debug("XSD error", arg0);
					throw arg0;
				}
			});
			documentBuilder.parse(new ByteArrayInputStream(xmlDocument.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new SingleMessageValidationException(e);
		}
	}

	public InputStream getResourceAsStream(String path) throws IOException {
		File f = new File(path);
		System.out.println(f.getAbsolutePath());
		if (f.exists()) {
//			LOG.info("Loading " + path + " from file system");
			return new FileInputStream(f);
		} else {
//			LOG.info("Loading " + path + " from classpath");
			URL url = SingleMessageValidationHelper.class.getResource(path);
			InputStream stream = SingleMessageValidationHelper.class.getResourceAsStream(path);

			System.out.println(SingleMessageValidationHelper.class.getPackage().getName());
			if (stream == null) {
				throw new IOException("Invalid resource " + path);
			}
			return stream;
		}
	}
}
