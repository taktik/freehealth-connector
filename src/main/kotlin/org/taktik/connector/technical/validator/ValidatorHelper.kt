package org.taktik.connector.technical.validator

import com.gc.iotools.stream.`is`.InputStreamFromOutputStream
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import org.apache.commons.lang.ArrayUtils
import org.slf4j.LoggerFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.handler.SchemaValidatorHandler
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import org.taktik.connector.technical.validator.impl.handler.ErrorCollectorHandler
import org.taktik.connector.technical.validator.impl.handler.ForkContentHandler
import org.taktik.connector.technical.validator.impl.handler.XOPValidationHandler
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.TimeUnit
import javax.xml.bind.util.JAXBSource
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.Source
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory
import javax.xml.validation.ValidatorHandler

class ValidatorHelper private constructor() {

    init {
        throw UnsupportedOperationException()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ValidatorHelper::class.java)
        private val SAF = SAXParserFactory.newInstance()
        private val TRF = TransformerFactory.newInstance()

        @Throws(TechnicalConnectorException::class)
        fun validate(source: Source, xop: Boolean, vararg schemaLocations: String) {
            try {
                val handler = XOPValidationHandler(xop)
                val validator = createValidatorForSchemaFiles(*schemaLocations)
                val collector = ErrorCollectorHandler(handler)
                validator.errorHandler = collector
                val parser = SAF.newSAXParser()
                parser.parse(convert(source), ForkContentHandler(handler, validator))
                handleValidationResult(collector)
            } catch (var7: Exception) {
                throw handleException(var7)
            }

        }

        @Throws(TechnicalConnectorException::class)
        fun validate(source: Source, vararg schemaLocations: String) {
            validate(source, false, *schemaLocations)
        }

        @Throws(TechnicalConnectorException::class)
        fun validate(jaxbObj: Any, rootSchemaFileLocation: String) {
            validate(jaxbObj, jaxbObj.javaClass, rootSchemaFileLocation)
        }

        @Throws(TechnicalConnectorException::class)
        fun validate(jaxbObj: Any?, xmlClass: Class<*>, rootSchemaFileLocation: String) {
            if (jaxbObj == null) {
                LOG.error("Message is null")
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, "Message is null")
            } else {
                ConnectorXmlUtils.dump(jaxbObj)
                LOG.debug("Validating with schema [$rootSchemaFileLocation]")

                try {
                    val jaxbContext = JaxbContextFactory.getJaxbContextForClass(xmlClass)
                    val payload = JAXBSource(jaxbContext, jaxbObj)
                    validate(payload as Source, rootSchemaFileLocation)
                } catch (var5: Exception) {
                    throw handleException(var5)
                }

                LOG.debug("Message is valid.")
            }
        }

        private fun handleException(e: Exception): TechnicalConnectorException {
            if (e is TechnicalConnectorException) {
                return e
            } else {
                LOG.error("Unable to validate object.", e)
                return TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, e, e.message)
            }
        }

        val validatorsCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(1, TimeUnit.HOURS).build(object: CacheLoader<List<String>, Schema>() {
            override fun load(schemaFiles: List<String>): Schema {
                val schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema")

                try {
                    var sources = arrayOfNulls<Source>(0)

                    for (schemaFile in schemaFiles) {
                        val `in` = SchemaValidatorHandler::class.java.getResourceAsStream(schemaFile) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, *arrayOf<Any>("Unable to find schemaFile $schemaFile"))

                        if (schemaFiles.size == 1) {
                            sources = ArrayUtils.add(sources, StreamSource(SchemaValidatorHandler::class.java.getResource(schemaFile).toExternalForm())) as Array<Source?>
                        } else {
                            val source = StreamSource(`in`)
                            sources = ArrayUtils.add(sources, source) as Array<Source?>
                        }
                    }

                    return schemaFactory.newSchema(sources)
                } catch (ex: Exception) {
                    throw handleException(ex)
                }

            }
        })

        @Throws(TechnicalConnectorException::class)
        protected fun createValidatorForSchemaFiles(vararg schemaFiles: String): ValidatorHandler {
            return validatorsCache.get(schemaFiles.asList()).newValidatorHandler()
        }

        @Throws(TechnicalConnectorException::class)
        private fun handleValidationResult(collector: ErrorCollectorHandler) {
            if (collector.hasExceptions("WARN")) {
                val validationWarning = collector.getExceptionList("WARN")
                val `i$` = validationWarning.iterator()

                while (`i$`.hasNext()) {
                    val exception = `i$`.next() as String
                    LOG.warn(exception)
                }
            }

            if (collector.hasExceptions("ERROR", "FATAL")) {
                val sb = StringBuilder()
                val validationErrors = collector.getExceptionList("ERROR", "FATAL")
                val iterator = validationErrors.iterator()

                while (iterator.hasNext()) {
                    val exception = iterator.next() as String
                    LOG.error(exception)
                    sb.append(exception)
                    sb.append(", ")
                }

                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, *arrayOf<Any>(sb.toString()))
            }
        }

        private fun convert(source: Source): InputStream {
            try {
                return object : InputStreamFromOutputStream<Void>() {
                    @Throws(Exception::class)
                    override fun produce(sink: OutputStream): Void? {
                        val result = StreamResult(sink)
                        val transformer = TRF.newTransformer()
                        transformer.setOutputProperty("omit-xml-declaration", "yes")
                        transformer.transform(source, result)
                        return null
                    }
                }
            } catch (var2: Exception) {
                throw IllegalArgumentException(var2)
            }

        }

        init {
            SAF.isNamespaceAware = true
        }
    }
}
