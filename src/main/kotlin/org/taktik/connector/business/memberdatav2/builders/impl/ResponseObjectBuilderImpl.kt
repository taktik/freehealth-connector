package org.taktik.connector.business.memberdatav2.builders.impl

import be.cin.encrypted.EncryptedKnownContent
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult
import be.fgov.ehealth.technicalconnector.signature.impl.DomUtils
import oasis.names.tc.saml._2_0.protocol.Response
import org.slf4j.LoggerFactory
import org.taktik.connector.business.memberdatav2.builders.ResponseObjectBuilder
import org.taktik.connector.business.memberdatav2.domain.MemberDataBuilderResponse
import org.taktik.connector.business.mycarenetcommons.mapper.v3.BlobMapper.mapBlobfromBlobType
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap.ModuleBootstrapHook
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.util.HashMap

class ResponseObjectBuilderImpl : ResponseObjectBuilder, ModuleBootstrapHook {
    @Throws(TechnicalConnectorException::class)
    override fun handleConsultationResponse(consultResponse: MemberDataConsultationResponse, crypto: Crypto): MemberDataBuilderResponse? {
        val blobType = consultResponse.getReturn().detail
        val blob =
            mapBlobfromBlobType(blobType)
        return if (blob.content.isNotEmpty()) {
            var data = blob.content
            if (blob.contentEncryption != null && !blob.contentEncryption.isEmpty()) {
                val unsealedData = crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).contentAsByte
                val encryptedKnownContent = MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(unsealedData)
                data = encryptedKnownContent!!.businessContent.value
            }
            if (data != null && ConfigFactory.getConfigValidator()
                    .getBooleanProperty("org.taktik.connector.business.memberdatasync.builders.impl.dumpMessages", false)) {
                LOG.debug("ResponseObjectBuilder : Blob content: {}", String(data))
            }
            try {
                val signatureVerificationResults =
                    verifyAll(data)
                MemberDataBuilderResponse(consultResponse, data, signatureVerificationResults)
            } catch (var7: Exception) {
                LOG.error("Error processing MemberDataConsultationResponse with id {0}", consultResponse.id, var7)
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var7, *arrayOfNulls(0))
            }
        } else {
            null
        }
    }

    @Throws(TechnicalConnectorException::class)
    fun verifyAll(signedByteArray: ByteArray?): Map<String?, SignatureVerificationResult?> {
        val signedContent = ConnectorXmlUtils.toDocument(signedByteArray)
        val signatureList =
            DomUtils.getMatchingChilds(signedContent, "http://www.w3.org/2000/09/xmldsig#", "Signature")
        val signatureVerificationResults: MutableMap<String?, SignatureVerificationResult?> = HashMap()
        for (i in 0 until signatureList.length) {
            val signatureElement = signatureList.item(i) as Element
            val signatureVerificationResult =
                SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES)
                    .verify(signedContent as Document, signatureElement, mutableMapOf())
            signatureVerificationResults[deriveXPathExpressionFrom(signatureElement)] = signatureVerificationResult
        }
        return signatureVerificationResults
    }

    private fun deriveXPathExpressionFrom(node: Node?): String {
        return if (node != null && node.nodeType.toInt() == 1) {
            val parent = node.parentNode
            val childNodes = parent.childNodes
            var index = 0
            var found = 0
            for (i in 0 until childNodes.length) {
                val current = childNodes.item(i)
                if (current.nodeName == node.nodeName) {
                    if (current === node) {
                        found = index + 1
                    }
                    ++index
                }
            }
            var strIdx = "[$found]"
            if (index == 1) {
                strIdx = ""
            }
            val xPathExpression =
                StringBuilder("/").append("*[local-name() = '")
                    .append(node.localName)
                    .append("' and namespace-uri()='")
                    .append(node.namespaceURI)
                    .append("']")
                    .append(strIdx)
            deriveXPathExpressionFrom(node.parentNode) + xPathExpression.toString()
        } else {
            ""
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(BlobType::class.java)
        JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse::class.java)
        JaxbContextFactory.initJaxbContext(Response::class.java)
        JaxbContextFactory.initJaxbContext(EncryptedKnownContent::class.java)
    }

    companion object {
        private val LOG =
            LoggerFactory.getLogger(ResponseObjectBuilderImpl::class.java)
        private const val PROP_DUMP_MESSAGES =
            "org.taktik.connector.business.memberdatasync.builders.impl.dumpMessages"
    }
}
