/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.business.connector.recipe.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleValidationException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MapNamespaceContext;
import be.business.connector.core.utils.PropertyHandler;

/**
 * The Class KmehrHelper.
 */
public class KmehrHelper {

	/** The Constant W3C_XML_SCHEMA_NS_URI. */
	public static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";

	/** The Constant KMEHR_ASSERT. */
	private static final String KMEHR_ASSERT = "kmehr.assert.";

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(KmehrHelper.class);

	/** The properties. */
	private static final Properties properties = PropertyHandler.getInstance().getProperties();

	/** The Constant JAXP_SCHEMA_SOURCE. */
	static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	/** The Constant JAXP_SCHEMA_LANGUAGE. */
	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/**
	 * Instantiates a new {@link KmehrHelper}.
	 *
	 * @param properties
	 *            the properties
	 */
	public KmehrHelper() {
		super();
	}

	/**
	 * Assert valid kmehr prescription.
	 *
	 * @param xmlFile
	 *            the xml file
	 * @param prescriptionType
	 *            the prescription type
	 * @throws IntegrationModuleException
	 */
	public void assertValidKmehrPrescription(final InputStream xmlFile, final String prescriptionType) throws IntegrationModuleException {
		final byte[] xmlStream = IOUtils.getBytes(xmlFile);
		assertValidKmehrPrescription(xmlStream, prescriptionType);
	}

	/**
	 * Assert valid notification.
	 *
	 * @param xmlFile
	 *            the xml file
	 * @throws IntegrationModuleException
	 */
	public void assertValidNotification(final InputStream xmlFile) throws IntegrationModuleException, SAXException {
		final byte[] xmlStream = IOUtils.getBytes(xmlFile);
		assertValidNotification(xmlStream);
	}

	/**
	 * Assert valid feedback.
	 *
	 * @param xmlFile
	 *            the xml file
	 * @throws IntegrationModuleException
	 */
	public void assertValidFeedback(final List<String> errors, final InputStream xmlFile) throws IntegrationModuleException, SAXException {
		final byte[] xmlStream = IOUtils.getBytes(xmlFile);
		assertValidFeedback(xmlStream);
		if (CollectionUtils.isNotEmpty(errors)) {
			throw new IntegrationModuleValidationException(getLabel("error.xml.invalid"), errors);
		}
	}

	/**
	 * Assert valid notification.
	 *
	 * @param xmlDocument
	 *            the xml document
	 * @throws IntegrationModuleException
	 */
	@Profiled(logFailuresSeparately = true, tag = "IntegrationModule#XMLNotificationValidation")
	public void assertValidNotification(final byte[] xmlDocument) throws IntegrationModuleException {
		final List<String> errors = new ArrayList<String>();
		validateXsd(errors, xmlDocument, "notification.XSD");
		if (CollectionUtils.isNotEmpty(errors)) {
			throw new IntegrationModuleValidationException(getLabel("error.xml.invalid"), errors);
		}
	}

	/**
	 * Assert valid notification.
	 *
	 * @param xmlDocument
	 *            the xml document
	 * @throws IntegrationModuleException
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.IntegrationModule#XMLFeedbackValidation")
	public void assertValidFeedback(final byte[] xmlDocument) throws IntegrationModuleException {
		final List<String> errors = new ArrayList<>();
		errors.addAll(validateXsd(new ArrayList<String>(), xmlDocument, "feedback.XSD"));
		if (CollectionUtils.isNotEmpty(errors)) {
			throw new IntegrationModuleValidationException(getLabel("error.xml.invalid"), errors);
		}
	}

	/**
	 * Valid xml.
	 *
	 * @param xmlDocument
	 *            the xml document
	 * @param prescriptionType
	 *            the prescription type
	 * @return true, if successful
	 * @throws IntegrationModuleException
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.IntegrationModule#XMLPrescriptionValidation")
	public void assertValidKmehrPrescription(final byte[] xmlDocument, final String prescriptionType) throws IntegrationModuleException {
		try {
			final List<String> errors = new ArrayList<String>();
			validateXsd(errors, xmlDocument, "kmehr.XSD");

			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document kmehrDocument = builder.parse(new ByteArrayInputStream(xmlDocument));

			validateXpath(errors, kmehrDocument, prescriptionType);
			if (CollectionUtils.isNotEmpty(errors)) {
				throw new IntegrationModuleValidationException(getLabel("error.xml.invalid"), errors);
			}
		} catch (IOException | ParserConfigurationException e) {
			LOG.debug("Bad Prescription : " + new String(xmlDocument));
			throw new IntegrationModuleException(getLabel("error.xml.invalid"), e);
		} catch (final IntegrationModuleException e) {
			LOG.error("Error occured : ", e);
			throw e;
		} catch (final IntegrationModuleValidationException e) {
			LOG.error("Error occured : ", e);
			throw e;
		} catch (final Throwable t) {
			LOG.error("Error occured : ", t);
			throw new IntegrationModuleException(getLabel("error.xml.invalid"), t);
		}
	}

	/**
	 * Xsd validate.
	 *
	 * @param xmlDocument
	 *            the xml document
	 * @param xsdPropertyName
	 *            the xsd property name
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<String> validateXsd(final List<String> errors, final byte[] xmlDocument, final String xsdPropertyName)
			throws IntegrationModuleException {

		if (ArrayUtils.isEmpty(xmlDocument)) {
			throw new IntegrationModuleValidationException(getLabel("error.xml.invalid"), errors);
		}
		try {
			final String documentString = new String(xmlDocument);
			String xsdVersion = "19";
			if (documentString.contains("20160601")) {
				xsdVersion = "17";
			} else if (documentString.contains("20190301")) {
				xsdVersion = "28";
			}
			final String xsdName = properties.getProperty(xsdPropertyName + "." + xsdVersion);

			if (xsdName == null || !new File(xsdName).exists()) {
				LOG.error(xsdPropertyName + "." + xsdVersion + " property is not correctly set, invalid file " + xsdPropertyName + " = " + xsdName);
				throw new RuntimeException(
						xsdPropertyName + "." + xsdVersion + " property is not correctly set, invalid file " + xsdPropertyName + " = " + xsdName);
			}
			final File xsd = new File(xsdName);

			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			if (!factory.getClass().getName().startsWith("org.apache")) {
				LOG.warn("Non supported parser : " + factory.getClass().getName());
			}

			factory.setNamespaceAware(true);
			factory.setValidating(true);
			factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA_NS_URI);
			factory.setAttribute(JAXP_SCHEMA_SOURCE, new String[] { xsd.getCanonicalPath() });

			final DocumentBuilder builderNamespaceAware = factory.newDocumentBuilder();
			builderNamespaceAware.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(final SAXParseException arg0) throws SAXException {
					LOG.warn("XSD Warning", arg0);
				}

				@Override
				public void fatalError(final SAXParseException arg0) throws SAXException {
					LOG.error("XSD fatalError", arg0);
					throw arg0;
				}

				@Override
				public void error(final SAXParseException arg0) throws SAXException {
					LOG.error("XSD error", arg0);
					throw arg0;
				}
			});
			builderNamespaceAware.parse(new ByteArrayInputStream(xmlDocument));
		} catch (IOException | ParserConfigurationException e) {
			throw new IntegrationModuleException(getLabel("error.xml.invalid"), e);
		} catch (final SAXException e) {
			final int lineNbr = ((SAXParseException) e).getLineNumber();
			final int columnNbr = ((SAXParseException) e).getColumnNumber();
			final String msg = ((SAXParseException) e).getMessage();
			errors.add(msg + ". LineNumber: " + lineNbr + ", columnNumber: " + columnNbr);
			return errors;
		}
		return errors;
	}

	/**
	 * Gets the label.
	 *
	 * @param key
	 *            the key
	 * @return the label
	 */
	private String getLabel(final String key) {
		return I18nHelper.getLabel(key);
	}

	/**
	 * Validate xpath.
	 *
	 * @param doc
	 *            the doc
	 * @param prescriptionType
	 *            the prescription type
	 * @throws XPathExpressionException
	 *             the x path expression exception
	 */
	private List<String> validateXpath(final List<String> errors, final Document doc, final String prescriptionType)
			throws IntegrationModuleException {
		try {

			int i = 1;
			String xpathCountWithVersion1 = "", xpathCountWithoutVersion1 = "", xpathCountWithVersion2 = "", xpathCountWithoutVersion2 = "";
			String keyCountWithoutVersion1 = "", keyCountWithVersion1 = "", keyCountWithoutVersion2 = "", keyCountWithVersion2 = "";
			String xpathConfigWithVersion = "", xpathConfigWithoutVersion = "";
			String keyWithoutVersion = "", keyWithVersion = "";
			do {
				String version = (String) properties.get("kmehr.version");
				if (StringUtils.isBlank(version)) {
					keyWithoutVersion = KMEHR_ASSERT + prescriptionType + "." + i;
					keyCountWithoutVersion1 = KMEHR_ASSERT + prescriptionType + ".1." + i;
					keyCountWithoutVersion2 = KMEHR_ASSERT + prescriptionType + ".2." + i;
					xpathConfigWithoutVersion = (String) properties.get(keyWithoutVersion);
					xpathCountWithoutVersion1 = (String) properties.get(keyCountWithoutVersion1);
					xpathCountWithoutVersion2 = (String) properties.get(keyCountWithoutVersion2);
				} else {
					version += ".";
					keyWithVersion = KMEHR_ASSERT + prescriptionType + "." + version + i;
					keyCountWithVersion1 = KMEHR_ASSERT + prescriptionType + ".1." + i;
					keyCountWithVersion2 = KMEHR_ASSERT + prescriptionType + ".2." + i;
					xpathConfigWithVersion = (String) properties.get(keyWithVersion);
					xpathCountWithVersion1 = (String) properties.get(keyCountWithVersion1);
					xpathCountWithVersion2 = (String) properties.get(keyCountWithVersion2);
				}
				// Expecting a property
				// kmehr.assert.PP.1 = "/xpath"/, min occurs, [max occurs]
				if (StringUtils.isBlank(xpathConfigWithVersion) && StringUtils.isBlank(xpathConfigWithoutVersion)
						&& StringUtils.isBlank(xpathCountWithVersion1) && StringUtils.isBlank(xpathCountWithVersion2)
						&& StringUtils.isBlank(xpathCountWithoutVersion1) && StringUtils.isBlank(xpathCountWithoutVersion2)) {
					break;
				}

				final String[] xpathConfigsWithVersion = StringUtils.isNoneBlank(xpathConfigWithVersion) ? xpathConfigWithVersion.split(";") : null;
				final String[] xpathConfigsWithoutVersion = StringUtils.isNoneBlank(xpathConfigWithoutVersion) ? xpathConfigWithoutVersion.split(";")
						: null;
				final String[] xpathConfCountWithVersion1 = StringUtils.isNoneBlank(xpathCountWithVersion1) ? xpathCountWithVersion1.split(";")
						: null;
				final String[] xpathConfCountWithoutVersion1 = StringUtils.isNoneBlank(xpathCountWithoutVersion1)
						? xpathCountWithoutVersion1.split(";")
						: null;
				final String[] xpathConfCountWithVersion2 = StringUtils.isNoneBlank(xpathCountWithVersion2) ? xpathCountWithVersion2.split(";")
						: null;
				final String[] xpathConfCountWithoutVersion2 = StringUtils.isNoneBlank(xpathCountWithoutVersion2)
						? xpathCountWithoutVersion2.split(";")
						: null;

				String message = "";
				// if (xpathConfigsWithVersion != null && xpathConfigsWithVersion.length < 2) {
				// message = "Invalid configuration : '" + keyWithVersion + "=" + xpathConfigWithVersion + "'";
				// }
				// if (xpathConfigsWithoutVersion != null && xpathConfigsWithoutVersion.length < 2) {
				// message += "Invalid configuration : '" + keyWithoutVersion + "=" + xpathConfigWithoutVersion + "'";
				// }
				// if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion1.length < 1) {
				// message = "Invalid configuration : '" + keyCountWithVersion1 + "=" + xpathCountWithVersion1 + "'";
				// }
				// if (xpathConfCountWithoutVersion1 != null && xpathConfCountWithoutVersion1.length < 1) {
				// message += "Invalid configuration : '" + keyCountWithoutVersion1 + "=" + xpathCountWithoutVersion1 + "'";
				// }
				// if (xpathConfCountWithVersion2 != null && xpathConfCountWithVersion2.length < 1) {
				// message = "Invalid configuration : '" + keyCountWithVersion2 + "=" + xpathCountWithVersion2 + "'";
				// }
				// if (xpathConfCountWithoutVersion2 != null && xpathConfCountWithoutVersion2.length < 1) {
				// message += "Invalid configuration : '" + keyCountWithoutVersion2 + "=" + xpathCountWithoutVersion2 + "'";
				// }
				if (StringUtils.isNotBlank(message)) {
					errors.add(message);
				}
				LOG.debug("validate xpathConfigsWithVersion[" + i + "][" + xpathConfigWithVersion + "] or xpathConfigsWithoutVersion[" + i + "]["
						+ xpathConfigWithoutVersion + "].");
				if (!verifyXpath(xpathConfigsWithVersion, doc) && !verifyXpath(xpathConfigsWithoutVersion, doc)
						&& !verifyXpath(xpathConfCountWithVersion1, xpathConfCountWithVersion2, doc)
						&& !verifyXpath(xpathConfCountWithoutVersion1, xpathConfCountWithoutVersion2, doc)) {
					if (xpathConfigsWithVersion != null) {
						message = "xpathConfigsWithVersion[" + i + "][" + xpathConfigWithVersion + "] is not valide.";
					} else if (xpathConfigsWithoutVersion != null) {
						message = "xpathConfigsWithoutVersion[" + i + "][" + xpathConfigWithoutVersion + "] is not valide.";
					} else if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion2 != null) {
						message = "xpathConfCountWithVersion1[" + i + "][" + xpathCountWithVersion1 + "] is not valide.";
						message += "or xpathConfCountWithVersion2[" + i + "][" + xpathCountWithVersion2 + "] is not valide.";
						message += " or xpathConfCountWithoutVersion1[" + i + "][" + xpathCountWithoutVersion1 + "] is not valide.";
						message += "or xpathConfCountWithoutVersion2[" + i + "][" + xpathCountWithoutVersion2 + "] is not valide.";
					}
					errors.add(message);
					return errors;
				}
				i = i + 1;
			} while (true);
		} catch (final XPathExpressionException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"), e);
		}
		return errors;
	}

	private boolean verifyXpath(final String[] xpathConfigs, final Document doc) {
		if (xpathConfigs == null) {
			return false;
		}
		int min = 0;
		int max = 0;
		if (xpathConfigs.length == 3) {
			min = Integer.parseInt(xpathConfigs[1].trim());
			max = Integer.parseInt(xpathConfigs[2].trim());
		} else if (xpathConfigs.length == 2) {
			min = Integer.parseInt(xpathConfigs[1].trim());
			max = Integer.MAX_VALUE;
		} else if (xpathConfigs.length == 1) {
			min = Integer.MIN_VALUE;
			max = Integer.MAX_VALUE;
		}
		String xpathStr = xpathConfigs[0];

		final XPath xpath = XPathFactory.newInstance().newXPath();
		final NamespaceContext nsCtx = new MapNamespaceContext();
		xpath.setNamespaceContext(nsCtx);
		try {
			final NodeList nodes = (NodeList) xpath.evaluate(xpathStr, doc, XPathConstants.NODESET);

			if (nodes.getLength() < min || nodes.getLength() > max) {
				LOG.error("FAILED Xpath query : " + xpathStr);
				return false;
			}
			return true;
		} catch (final XPathExpressionException ex) {
			try {
				final boolean ok = (boolean) xpath.evaluate(xpathStr, doc, XPathConstants.BOOLEAN);
				if (!ok) {
					LOG.error("FAILED Xpath query : " + xpathStr);
					return false;
				}
				return true;
			} catch (final XPathExpressionException ex1) {
				LOG.error("FAILED Xpath query : " + ex1.getMessage());
				return false;
			}
		}
	}

	private boolean verifyXpath(final String[] xpathConfigs1, final String[] xpathConfigs2, final Document doc) throws XPathExpressionException {
		if (xpathConfigs1 == null || xpathConfigs2 == null) {
			return false;
		}
		final String xpathStr1 = xpathConfigs1[0];
		final String xpathStr2 = xpathConfigs2[0];

		final XPath xpath = XPathFactory.newInstance().newXPath();
		final NamespaceContext nsCtx = new MapNamespaceContext();
		xpath.setNamespaceContext(nsCtx);
		final Double count1 = (Double) xpath.evaluate(xpathStr1, doc, XPathConstants.NUMBER);
		final Double count2 = (Double) xpath.evaluate(xpathStr2, doc, XPathConstants.NUMBER);

		if (!Objects.equals(count1, count2)) {
			LOG.error("FAILED Xpath query : " + xpathStr1 + " <==> " + xpathStr2);
			return false;
		}
		return true;
	}
}
