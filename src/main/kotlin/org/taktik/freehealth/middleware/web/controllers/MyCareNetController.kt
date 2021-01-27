package org.taktik.freehealth.middleware.web.controllers

import be.cin.encrypted.EncryptedKnownContent
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.UnsealConnectorException
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.service.CryptoService
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import java.util.*
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@RestController
@RequestMapping("/mycarenet")
class MyCareNetController(val cryptoService: CryptoService) {
    val detailXmlPath = "//*[local-name() = 'Detail']"

    @PostMapping("/detail/decode", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_XML_VALUE])
    fun decodeDetail(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody rawContent: String
    ) : String {
        val detailContentNodeList = resolveAsNodeList(detailXmlPath, rawContent)

        if (detailContentNodeList.length == 0) {
            throw IllegalArgumentException("At least one detail cannot be found")
        }

        var detailsContent = ""
        for (i in 0 until detailContentNodeList.length) {
            var detailContentDisclosed = getDetailContentDisclosed(keystoreId, passPhrase, detailContentNodeList.item(i).textContent)

            if (i > 0) {
                detailContentDisclosed = detailContentDisclosed.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "")
            }

            detailsContent += detailContentDisclosed
        }

        return detailsContent
    }

    private fun getDetailContentDisclosed(
        keystoreId: UUID,
        passPhrase: String,
        detailContent: String
    ): String {
        // Decode detail content
        val detailContentDecoded = ConnectorIOUtils.base64Decode(detailContent.toByteArray(Charsets.UTF_8), false)

        // Try to decrypt decoded detail content
        var detailContentDisclosed: ByteArray =
            try {
                val detailContentDecodedAndDecrypted = cryptoService.decrypt(keystoreId, passPhrase, detailContentDecoded)
                val encryptedKnownContent = MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(detailContentDecodedAndDecrypted)

                encryptedKnownContent!!.businessContent.value
            }
            catch (ex: UnsealConnectorException) {
                detailContentDecoded
            }

        // Try to decompress disclosed detail content
        try {
            val detailContentDecompressed = ConnectorIOUtils.decompress(detailContentDisclosed)

            if (detailContentDecompressed.isNotEmpty()) {
                detailContentDisclosed = detailContentDecompressed
            }
        }
        catch (ex: TechnicalConnectorException) {
            // noop
        }

        return String(detailContentDisclosed)
    }

    private fun resolveAsNodeList(path: String, xmlSource: String): NodeList {
        val xpath = XPathFactory.newInstance().newXPath()

        return xpath.evaluate(path, InputSource(StringReader(xmlSource)), XPathConstants.NODESET) as NodeList
    }
}
