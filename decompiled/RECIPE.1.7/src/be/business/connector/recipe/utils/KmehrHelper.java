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
package org.taktik.connector.business.recipe.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;

import org.perf4j.aop.Profiled;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils;
import org.taktik.connector.business.recipeprojects.core.utils.MapNamespaceContext;
import java.util.Arrays;
import javax.xml.namespace.NamespaceContext;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class KmehrHelper.
 */
public class KmehrHelper {

    /**
     * The Constant W3C_XML_SCHEMA_NS_URI.
     */
    public static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = Logger.getLogger(KmehrHelper.class);

    /**
     * The properties.
     */
    private Properties properties = null;

    static final String JAXP_SCHEMA_SOURCE
            = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    static final String JAXP_SCHEMA_LANGUAGE
            = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    /**
     * Instantiates a new kmehr helper.
     *
     * @param properties the properties
     */
    public KmehrHelper(Properties properties) {
        super();
        this.properties = properties;
    }

    /**
     * Assert valid kmehr prescription.
     *
     * @param xmlFile the xml file
     * @param prescriptionType the prescription type
     * @throws IntegrationModuleException
     */
    public void assertValidKmehrPrescription(InputStream xmlFile, String prescriptionType) throws IntegrationModuleException {
        byte[] xmlStream = IOUtils.getBytes(xmlFile);
        assertValidKmehrPrescription(xmlStream, prescriptionType);
    }

    /**
     * Assert valid notification.
     *
     * @param xmlFile the xml file
     * @throws IntegrationModuleException
     */
    public void assertValidNotification(InputStream xmlFile) throws IntegrationModuleException {
        byte[] xmlStream = IOUtils.getBytes(xmlFile);
        assertValidNotification(xmlStream);
    }

    /**
     * Assert valid feedback.
     *
     * @param xmlFile the xml file
     * @throws IntegrationModuleException
     */
    public void assertValidFeedback(InputStream xmlFile) throws IntegrationModuleException {
        byte[] xmlStream = IOUtils.getBytes(xmlFile);
        assertValidFeedback(xmlStream);
    }

    /**
     * Assert valid notification.
     *
     * @param xmlDocument the xml document
     * @throws IntegrationModuleException
     */
    @Profiled(logFailuresSeparately = true, tag = "IntegrationModule#XMLNotificationValidation")
    public void assertValidNotification(byte[] xmlDocument) throws IntegrationModuleException {
        xsdValidate(xmlDocument, "notification.XSD");
    }

    /**
     * Assert valid notification.
     *
     * @param xmlDocument the xml document
     * @throws IntegrationModuleException
     */
    @Profiled(logFailuresSeparately = true, tag = "0.IntegrationModule#XMLFeedbackValidation")
    public void assertValidFeedback(byte[] xmlDocument) throws IntegrationModuleException {
        xsdValidate(xmlDocument, "feedback.XSD");
    }

    /**
     * Valid xml.
     *
     * @param xmlDocument the xml document
     * @param prescriptionType the prescription type
     * @return true, if successful
     * @throws IntegrationModuleException
     */
    @Profiled(logFailuresSeparately = true, tag = "0.IntegrationModule#XMLPrescriptionValidation")
    public void assertValidKmehrPrescription(byte[] xmlDocument, String prescriptionType) throws IntegrationModuleException {
        try {
            xsdValidate(xmlDocument, "kmehr.XSD");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc2;
            doc2 = builder.parse(new ByteArrayInputStream(xmlDocument));

            validateXpath(doc2, prescriptionType);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOG.debug("Bad Prescription : " + new String(xmlDocument));
            throw new IntegrationModuleException(getLabel("error.xml.invalid"), e);
        } catch (IntegrationModuleException e) {
            throw e;
        } catch (Throwable t) {
            LOG.error("Error occured : ", t);
            throw new IntegrationModuleException(getLabel("error.xml.invalid"), t);
        }
    }

    /**
     * Xsd validate.
     *
     * @param xmlDocument the xml document
     * @param xsdPropertyName the xsd property name
     * @throws ParserConfigurationException the parser configuration exception
     * @throws SAXException the sAX exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void xsdValidate(byte[] xmlDocument, String xsdPropertyName)
            throws IntegrationModuleException {
        try {

            String xsdName = properties.getProperty(xsdPropertyName);

            if (xsdName == null || !new File(xsdName).exists()) {
                LOG.error("kmehr.XSD property is not correctly set, invalid file " + xsdPropertyName + " = " + xsdName);
                throw new RuntimeException("kmehr.XSD property is not correctly set, invalid file " + xsdPropertyName + " = " + xsdName);
            }
            File xsd = new File(xsdName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            if (!factory.getClass().getName().startsWith("org.apache")) {
                LOG.warn("Non supported parser : " + factory.getClass().getName());
            }

            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA_NS_URI);
            factory.setAttribute(
                    JAXP_SCHEMA_SOURCE,
                    new String[]{xsd.getCanonicalPath()});

            DocumentBuilder builderNamespaceAware = factory.newDocumentBuilder();
            builderNamespaceAware.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException arg0) throws SAXException {
                    LOG.warn("XSD Warning", arg0);
                }

                @Override
                public void fatalError(SAXParseException arg0) throws SAXException {
                    LOG.error("XSD fatalError", arg0);
                    throw arg0;
                }

                @Override
                public void error(SAXParseException arg0) throws SAXException {
                    LOG.error("XSD error", arg0);
                    throw arg0;
                }
            });
            builderNamespaceAware.parse(new ByteArrayInputStream(xmlDocument));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new IntegrationModuleException(getLabel("error.xml.invalid"), e);
        }
    }

    /**
     * Gets the label.
     *
     * @param key the key
     * @return the label
     */
    private String getLabel(String key) {
        return I18nHelper.getLabel(key);
    }

    /**
     * Validate xpath.
     *
     * @param doc the doc
     * @param prescriptionType the prescription type
     * @throws XPathExpressionException the x path expression exception
     */
    private void validateXpath(Document doc, String prescriptionType)
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
                if (StringUtils.isBlank(xpathConfigWithVersion) && StringUtils.isBlank(xpathConfigWithoutVersion) && StringUtils.isBlank(xpathCountWithVersion1) && StringUtils.isBlank(
                        xpathCountWithVersion2) && StringUtils.isBlank(xpathCountWithoutVersion1) && StringUtils.isBlank(xpathCountWithoutVersion2)) {
                    break;
                }

                String[] xpathConfigsWithVersion = StringUtils.isNoneBlank(xpathConfigWithVersion) ? xpathConfigWithVersion.split(";") : null;
                String[] xpathConfigsWithoutVersion = StringUtils.isNoneBlank(xpathConfigWithoutVersion) ? xpathConfigWithoutVersion.split(";") : null;
                String[] xpathConfCountWithVersion1 = StringUtils.isNoneBlank(xpathCountWithVersion1) ? xpathCountWithVersion1.split(";") : null;
                String[] xpathConfCountWithoutVersion1 = StringUtils.isNoneBlank(xpathCountWithoutVersion1) ? xpathCountWithoutVersion1.split(";") : null;
                String[] xpathConfCountWithVersion2 = StringUtils.isNoneBlank(xpathCountWithVersion2) ? xpathCountWithVersion2.split(";") : null;
                String[] xpathConfCountWithoutVersion2 = StringUtils.isNoneBlank(xpathCountWithoutVersion2) ? xpathCountWithoutVersion2.split(";") : null;

                String message = "";
                if (xpathConfigsWithVersion != null && xpathConfigsWithVersion.length < 2) {
                    message = "Invalid configuration : '" + keyWithVersion + "=" + xpathConfigWithVersion + "'";
                }
                if (xpathConfigsWithoutVersion != null && xpathConfigsWithoutVersion.length < 2) {
                    message += "Invalid configuration : '" + keyWithoutVersion + "=" + xpathConfigWithoutVersion + "'";
                }
                if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion1.length < 1) {
                    message = "Invalid configuration : '" + keyCountWithVersion1 + "=" + xpathCountWithVersion1 + "'";
                }
                if (xpathConfCountWithoutVersion1 != null && xpathConfCountWithoutVersion1.length < 1) {
                    message += "Invalid configuration : '" + keyCountWithoutVersion1 + "=" + xpathCountWithoutVersion1 + "'";
                }
                if (xpathConfCountWithVersion2 != null && xpathConfCountWithVersion2.length < 1) {
                    message = "Invalid configuration : '" + keyCountWithVersion2 + "=" + xpathCountWithVersion2 + "'";
                }
                if (xpathConfCountWithoutVersion2 != null && xpathConfCountWithoutVersion2.length < 1) {
                    message += "Invalid configuration : '" + keyCountWithoutVersion2 + "=" + xpathCountWithoutVersion2 + "'";
                }
                if (StringUtils.isNotBlank(message)) {
                    throw new IntegrationModuleException(message);
                }
                LOG.debug("validate xpathConfigsWithVersion[" + i + "][" + xpathConfigWithVersion + "] or xpathConfigsWithoutVersion[" + i + "][" + xpathConfigWithoutVersion + "].");
                if (!verifyXpath(xpathConfigsWithVersion, doc) && !verifyXpath(xpathConfigsWithoutVersion, doc) && !verifyXpath(xpathConfCountWithVersion1, xpathConfCountWithVersion2, doc) &&
                        !verifyXpath(xpathConfCountWithoutVersion1, xpathConfCountWithoutVersion2, doc)) {
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
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"));
                }
                i = i + 1;
            } while (true);
        } catch (XPathExpressionException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"), e);
        }
    }
    private static final String KMEHR_ASSERT = "kmehr.assert.";

    private boolean verifyXpath(String[] xpathConfigs, Document doc) throws XPathExpressionException, NumberFormatException, IntegrationModuleException {
        if (xpathConfigs == null) {
            return false;
        }
        String xpathStr = xpathConfigs[0];
        int min = Integer.parseInt(xpathConfigs[1].trim());
        int max = xpathConfigs.length > 2 ? Integer.parseInt(xpathConfigs[2].trim()) : Integer.MAX_VALUE;

        XPath xpath = XPathFactory.newInstance().newXPath();
        NamespaceContext nsCtx = new MapNamespaceContext();
        xpath.setNamespaceContext(nsCtx);
        NodeList nodes = (NodeList) xpath.evaluate(xpathStr, doc, XPathConstants.NODESET);

        if (nodes.getLength() < min || nodes.getLength() > max) {
            LOG.error("FAILED Xpath query : " + xpathStr);
            return false;
            //throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"));
        }
        return true;
    }

    private boolean verifyXpath(String[] xpathConfigs1, String[] xpathConfigs2, Document doc) throws XPathExpressionException {
        if (xpathConfigs1 == null || xpathConfigs2 == null) {
            return false;
        }
        String xpathStr1 = xpathConfigs1[0];
        String xpathStr2 = xpathConfigs2[0];

        XPath xpath = XPathFactory.newInstance().newXPath();
        NamespaceContext nsCtx = new MapNamespaceContext();
        xpath.setNamespaceContext(nsCtx);
        Double count1 = (Double) xpath.evaluate(xpathStr1, doc, XPathConstants.NUMBER);
        Double count2 = (Double) xpath.evaluate(xpathStr2, doc, XPathConstants.NUMBER);

        if (!Objects.equals(count1, count2)) {
            LOG.error("FAILED Xpath query : " + xpathStr1 + " <==> " + xpathStr2);
            return false;
        }
        return true;
    }
}
