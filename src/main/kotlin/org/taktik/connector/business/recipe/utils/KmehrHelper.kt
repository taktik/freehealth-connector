/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.taktik.connector.business.recipe.utils

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils
import org.taktik.connector.business.recipeprojects.core.utils.MapNamespaceContext
import org.apache.commons.lang3.StringUtils
import org.apache.log4j.Logger
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import org.xml.sax.ErrorHandler
import org.xml.sax.SAXException
import org.xml.sax.SAXParseException

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.Properties

/**
 * The Class KmehrHelper.
 */
class KmehrHelper(val properties: Properties) {

    /**
     * Assert valid kmehr prescription.
     *
     * @param xmlFile the xml file
     * @param prescriptionType the prescription type
     */
    @Throws(IntegrationModuleException::class)
    fun assertValidKmehrPrescription(xmlFile: InputStream, prescriptionType: String) {
        val xmlStream = IOUtils.getBytes(xmlFile)
        assertValidKmehrPrescription(xmlStream, prescriptionType)
    }

    /**
     * Assert valid notification.
     *
     * @param xmlFile the xml file
     */
    @Throws(IntegrationModuleException::class)
    fun assertValidNotification(xmlFile: InputStream) {
        val xmlStream = IOUtils.getBytes(xmlFile)
        assertValidNotification(xmlStream)
    }

    /**
     * Assert valid feedback.
     *
     * @param xmlFile the xml file
     */
    @Throws(IntegrationModuleException::class)
    fun assertValidFeedback(xmlFile: InputStream) {
        val xmlStream = IOUtils.getBytes(xmlFile)
        assertValidFeedback(xmlStream)
    }

    /**
     * Assert valid notification.
     *
     * @param xmlDocument the xml document
     */
    @Throws(IntegrationModuleException::class)
    fun assertValidNotification(xmlDocument: ByteArray) {
        xsdValidate(xmlDocument, "notification.XSD")
    }

    /**
     * Assert valid notification.
     *
     * @param xmlDocument the xml document
     */
    @Throws(IntegrationModuleException::class)
    private fun assertValidFeedback(xmlDocument: ByteArray) {
        xsdValidate(xmlDocument, "feedback.XSD")
    }

    /**
     * Valid xml.
     *
     * @param xmlDocument the xml document
     * @param prescriptionType the prescription type
     */
    @Throws(IntegrationModuleException::class)
    fun assertValidKmehrPrescription(xmlDocument: ByteArray, prescriptionType: String) {
        try {
            xsdValidate(xmlDocument, "kmehr.XSD")

            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = false
            val builder = factory.newDocumentBuilder()
            val doc2: Document
            doc2 = builder.parse(ByteArrayInputStream(xmlDocument))

            validateXpath(doc2, prescriptionType)
        } catch (e: SAXException) {
            LOG.debug("Bad Prescription : " + String(xmlDocument))
            throw IntegrationModuleException(getLabel("error.xml.invalid"), e)
        } catch (e: IOException) {
            LOG.debug("Bad Prescription : " + String(xmlDocument))
            throw IntegrationModuleException(getLabel("error.xml.invalid"), e)
        } catch (e: ParserConfigurationException) {
            LOG.debug("Bad Prescription : " + String(xmlDocument))
            throw IntegrationModuleException(getLabel("error.xml.invalid"), e)
        } catch (e: IntegrationModuleException) {
            throw e
        } catch (t: Throwable) {
            LOG.error("Error occured : ", t)
            throw IntegrationModuleException(getLabel("error.xml.invalid"), t)
        }
    }

    /**
     * Xsd validate.
     *
     * @param xmlDocument the xml document
     * @param xsdPropertyName the xsd property name
     */
    @Throws(IntegrationModuleException::class)
    private fun xsdValidate(xmlDocument: ByteArray, xsdPropertyName: String) {
        try {

            val xsdName = properties!!.getProperty(xsdPropertyName)

            if (xsdName == null || !File(xsdName).exists()) {
                LOG.error("kmehr.XSD property is not correctly set, invalid file $xsdPropertyName = $xsdName")
                throw RuntimeException("kmehr.XSD property is not correctly set, invalid file $xsdPropertyName = $xsdName")
            }
            val xsd = File(xsdName)

            val factory = DocumentBuilderFactory.newInstance()
            if (!factory.javaClass.name.startsWith("org.apache")) {
                LOG.warn("Non supported parser : " + factory.javaClass.name)
            }

            factory.isNamespaceAware = true
            factory.isValidating = true
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA_NS_URI)
            factory.setAttribute(
                JAXP_SCHEMA_SOURCE, arrayOf(xsd.canonicalPath)
            )

            val builderNamespaceAware = factory.newDocumentBuilder()
            builderNamespaceAware.setErrorHandler(object : ErrorHandler {
                @Throws(SAXException::class)
                override fun warning(arg0: SAXParseException) {
                    LOG.warn("XSD Warning", arg0)
                }

                @Throws(SAXException::class)
                override fun fatalError(arg0: SAXParseException) {
                    LOG.error("XSD fatalError", arg0)
                    throw arg0
                }

                @Throws(SAXException::class)
                override fun error(arg0: SAXParseException) {
                    LOG.error("XSD error", arg0)
                    throw arg0
                }
            })
            builderNamespaceAware.parse(ByteArrayInputStream(xmlDocument))
        } catch (e: SAXException) {
            throw IntegrationModuleException(getLabel("error.xml.invalid"), e)
        } catch (e: IOException) {
            throw IntegrationModuleException(getLabel("error.xml.invalid"), e)
        } catch (e: ParserConfigurationException) {
            throw IntegrationModuleException(getLabel("error.xml.invalid"), e)
        }
    }

    /**
     * Gets the label.
     *
     * @param key the key
     * @return the label
     */
    private fun getLabel(key: String): String {
        return I18nHelper.getLabel(key)
    }

    /**
     * Validate xpath.
     *
     * @param doc the doc
     * @param prescriptionType the prescription type
     * @throws XPathExpressionException the x path expression exception
     */
    @Throws(IntegrationModuleException::class)
    private fun validateXpath(doc: Document, prescriptionType: String) {
        try {
            var i = 1
            var xpathCountWithVersion1 = ""
            var xpathCountWithoutVersion1 = ""
            var xpathCountWithVersion2 = ""
            var xpathCountWithoutVersion2 = ""
            var keyCountWithoutVersion1 = ""
            var keyCountWithVersion1 = ""
            var keyCountWithoutVersion2 = ""
            var keyCountWithVersion2 = ""
            var xpathConfigWithVersion = ""
            var xpathConfigWithoutVersion = ""
            var keyWithoutVersion = ""
            var keyWithVersion = ""
            do {
                var version = properties!!["kmehr.version"] as String
                if (StringUtils.isBlank(version)) {
                    keyWithoutVersion = KMEHR_ASSERT + prescriptionType + "." + i
                    keyCountWithoutVersion1 = KMEHR_ASSERT + prescriptionType + ".1." + i
                    keyCountWithoutVersion2 = KMEHR_ASSERT + prescriptionType + ".2." + i
                    xpathConfigWithoutVersion = properties[keyWithoutVersion] as String
                    xpathCountWithoutVersion1 = properties[keyCountWithoutVersion1] as String
                    xpathCountWithoutVersion2 = properties[keyCountWithoutVersion2] as String
                } else {
                    version += "."
                    keyWithVersion = KMEHR_ASSERT + prescriptionType + "." + version + i
                    keyCountWithVersion1 = KMEHR_ASSERT + prescriptionType + ".1." + i
                    keyCountWithVersion2 = KMEHR_ASSERT + prescriptionType + ".2." + i
                    xpathConfigWithVersion = properties[keyWithVersion] as String
                    xpathCountWithVersion1 = properties[keyCountWithVersion1] as String
                    xpathCountWithVersion2 = properties[keyCountWithVersion2] as String
                }
                // Expecting a property
                // kmehr.assert.PP.1 = "/xpath"/, min occurs, [max occurs]
                if (StringUtils.isBlank(xpathConfigWithVersion) && StringUtils.isBlank(xpathConfigWithoutVersion) && StringUtils.isBlank(
                        xpathCountWithVersion1
                    ) && StringUtils.isBlank(
                        xpathCountWithVersion2
                    ) && StringUtils.isBlank(xpathCountWithoutVersion1) && StringUtils.isBlank(xpathCountWithoutVersion2)) {
                    break
                }

                val xpathConfigsWithVersion =
                    if (StringUtils.isNoneBlank(xpathConfigWithVersion)) xpathConfigWithVersion.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() else null
                val xpathConfigsWithoutVersion =
                    if (StringUtils.isNoneBlank(xpathConfigWithoutVersion)) xpathConfigWithoutVersion.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() else null
                val xpathConfCountWithVersion1 =
                    if (StringUtils.isNoneBlank(xpathCountWithVersion1)) xpathCountWithVersion1.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() else null
                val xpathConfCountWithoutVersion1 =
                    if (StringUtils.isNoneBlank(xpathCountWithoutVersion1)) xpathCountWithoutVersion1.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() else null
                val xpathConfCountWithVersion2 =
                    if (StringUtils.isNoneBlank(xpathCountWithVersion2)) xpathCountWithVersion2.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() else null
                val xpathConfCountWithoutVersion2 =
                    if (StringUtils.isNoneBlank(xpathCountWithoutVersion2)) xpathCountWithoutVersion2.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() else null

                var message = ""
                if (xpathConfigsWithVersion != null && xpathConfigsWithVersion.size < 2) {
                    message = "Invalid configuration : '$keyWithVersion=$xpathConfigWithVersion'"
                }
                if (xpathConfigsWithoutVersion != null && xpathConfigsWithoutVersion.size < 2) {
                    message += "Invalid configuration : '$keyWithoutVersion=$xpathConfigWithoutVersion'"
                }
                if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion1.size < 1) {
                    message = "Invalid configuration : '$keyCountWithVersion1=$xpathCountWithVersion1'"
                }
                if (xpathConfCountWithoutVersion1 != null && xpathConfCountWithoutVersion1.size < 1) {
                    message += "Invalid configuration : '$keyCountWithoutVersion1=$xpathCountWithoutVersion1'"
                }
                if (xpathConfCountWithVersion2 != null && xpathConfCountWithVersion2.size < 1) {
                    message = "Invalid configuration : '$keyCountWithVersion2=$xpathCountWithVersion2'"
                }
                if (xpathConfCountWithoutVersion2 != null && xpathConfCountWithoutVersion2.size < 1) {
                    message += "Invalid configuration : '$keyCountWithoutVersion2=$xpathCountWithoutVersion2'"
                }
                if (StringUtils.isNotBlank(message)) {
                    throw IntegrationModuleException(message)
                }
                LOG.debug("validate xpathConfigsWithVersion[$i][$xpathConfigWithVersion] or xpathConfigsWithoutVersion[$i][$xpathConfigWithoutVersion].")
                if (!verifyXpath(xpathConfigsWithVersion, doc) && !verifyXpath(
                        xpathConfigsWithoutVersion,
                        doc
                    ) && !verifyXpath(xpathConfCountWithVersion1, xpathConfCountWithVersion2, doc) && !verifyXpath(
                        xpathConfCountWithoutVersion1,
                        xpathConfCountWithoutVersion2,
                        doc
                    )) {
                    if (xpathConfigsWithVersion != null) {
                        message = "xpathConfigsWithVersion[$i][$xpathConfigWithVersion] is not valide."
                    } else if (xpathConfigsWithoutVersion != null) {
                        message = "xpathConfigsWithoutVersion[$i][$xpathConfigWithoutVersion] is not valide."
                    } else if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion2 != null) {
                        message = "xpathConfCountWithVersion1[$i][$xpathCountWithVersion1] is not valide."
                        message += "or xpathConfCountWithVersion2[$i][$xpathCountWithVersion2] is not valide."
                        message += " or xpathConfCountWithoutVersion1[$i][$xpathCountWithoutVersion1] is not valide."
                        message += "or xpathConfCountWithoutVersion2[$i][$xpathCountWithoutVersion2] is not valide."
                    }
                    throw IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"))
                }
                i = i + 1
            } while (true)
        } catch (e: XPathExpressionException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"), e)
        }
    }

    @Throws(XPathExpressionException::class, NumberFormatException::class, IntegrationModuleException::class)
    private fun verifyXpath(xpathConfigs: Array<String>?, doc: Document): Boolean {
        if (xpathConfigs == null) {
            return false
        }
        val xpathStr = xpathConfigs[0]
        val min = Integer.parseInt(xpathConfigs[1].trim { it <= ' ' })
        val max = if (xpathConfigs.size > 2) Integer.parseInt(xpathConfigs[2].trim { it <= ' ' }) else Integer.MAX_VALUE

        val xpath = XPathFactory.newInstance().newXPath()
        val nsCtx = MapNamespaceContext()
        xpath.namespaceContext = nsCtx
        val nodes = xpath.evaluate(xpathStr, doc, XPathConstants.NODESET) as NodeList

        if (nodes.length < min || nodes.length > max) {
            LOG.error("FAILED Xpath query : " + xpathStr)
            return false
            //throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"));
        }
        return true
    }

    @Throws(XPathExpressionException::class)
    private fun verifyXpath(xpathConfigs1: Array<String>?, xpathConfigs2: Array<String>?, doc: Document): Boolean {
        if (xpathConfigs1 == null || xpathConfigs2 == null) {
            return false
        }
        val xpathStr1 = xpathConfigs1[0]
        val xpathStr2 = xpathConfigs2[0]

        val xpath = XPathFactory.newInstance().newXPath()
        val nsCtx = MapNamespaceContext()
        xpath.namespaceContext = nsCtx
        val count1 = xpath.evaluate(xpathStr1, doc, XPathConstants.NUMBER) as Double
        val count2 = xpath.evaluate(xpathStr2, doc, XPathConstants.NUMBER) as Double

        if (count1 != count2) {
            LOG.error("FAILED Xpath query : $xpathStr1 <==> $xpathStr2")
            return false
        }
        return true
    }

    companion object {

        /**
         * The Constant W3C_XML_SCHEMA_NS_URI.
         */
        private val W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema"

        /**
         * The Constant LOG.
         */
        private val LOG = Logger.getLogger(KmehrHelper::class.java)

        private val JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource"

        private val JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage"
        private val KMEHR_ASSERT = "kmehr.assert."
    }
}
