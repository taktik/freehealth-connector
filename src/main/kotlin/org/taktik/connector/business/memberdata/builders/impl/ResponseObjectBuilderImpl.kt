package org.taktik.connector.business.memberdata.builders.impl

import be.cin.encrypted.EncryptedKnownContent
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult
import org.apache.commons.compress.utils.Charsets
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.taktik.connector.business.memberdata.builders.ResponseObjectBuilder
import org.taktik.connector.business.memberdata.domain.MemberDataBuilderResponse
import org.taktik.connector.business.mycarenetcommons.mapper.v3.BlobMapper
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.ExtensionsType
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.Response
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import java.io.StringWriter
import java.util.Collections
import java.util.HashMap
import java.util.LinkedList

class ResponseObjectBuilderImpl : ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {

    @Throws(TechnicalConnectorException::class)
    override fun handleConsultationResponse(consultResponse: MemberDataConsultationResponse, crypto: Crypto): MemberDataBuilderResponse? {
        try {
            val tf = TransformerFactory.newInstance()
            var serializer: Transformer? = null
            serializer = tf.newTransformer()
            serializer!!.setOutputProperty("omit-xml-declaration", "yes")

            val blobType = consultResponse.getReturn().detail
            val blob = BlobMapper.mapBlobfromBlobType(blobType)
            if (blob.content.size <= 0) {
                return null
            } else {
                var data: ByteArray? = blob.content
                val doc: Document
                if (blob.contentEncryption != null && !blob.contentEncryption.isEmpty()) {
                    val unsealedData = crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).contentAsByte
                    val encryptedKnownContent = MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(unsealedData)
                    data = encryptedKnownContent!!.businessContent.value
                    doc = ConnectorXmlUtils.toDocument(data)
                } else {
                    doc = ConnectorXmlUtils.toDocument(consultResponse.getReturn().detail.value)
                }

                if (data != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages", false)!!) {
                    LOG.debug("ResponseObjectBuilder : Blob content: " + String(data))
                }

                val nodes = doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion")

                val jaxbContext = JaxbContextFactory.getJaxbContextForClass(Assertion::class.java, ExtensionsType::class.java)
                val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES)
                val options = emptyMap<String, Any>()
                val signatureVerificationResults = HashMap<String, SignatureVerificationResult>()

                val assertions = LinkedList<Assertion>()

                for (i in 0 until nodes.length) {
                    val node = nodes.item(i)
                    val assertion = jaxbContext.createUnmarshaller().unmarshal(DOMSource(node)) as Assertion
                    assertions.add(assertion)
                    if (assertion != null) {
                        if (assertion.signature != null) {
                            val sw = StringWriter()
                            serializer.transform(DOMSource(node), StreamResult(sw))
                            val signatureVerificationResult = builder.verify(sw.toString().toByteArray(Charsets.UTF_8), options)
                            signatureVerificationResults[assertion.id] = signatureVerificationResult
                        }
                    }
                }
                return MemberDataBuilderResponse(assertions, consultResponse, JaxbContextFactory.getJaxbContextForClass(Response::class.java, ExtensionsType::class.java).createUnmarshaller().unmarshal(DOMSource(doc)) as Response, signatureVerificationResults)
            }
        } catch (e: TransformerException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, e)
        } catch (e: JAXBException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, e)
        }

    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(BlobType::class.java)
        JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse::class.java)
        JaxbContextFactory.initJaxbContext(Response::class.java)
        JaxbContextFactory.initJaxbContext(EncryptedKnownContent::class.java)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl::class.java)
    }
}
