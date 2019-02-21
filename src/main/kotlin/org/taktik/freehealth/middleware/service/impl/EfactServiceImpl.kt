package org.taktik.freehealth.middleware.service.impl

//import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatReader
import be.cin.mycarenet.esb.common.v2.CareProviderType
import be.cin.mycarenet.esb.common.v2.CommonInput
import be.cin.mycarenet.esb.common.v2.IdType
import be.cin.mycarenet.esb.common.v2.LicenseType
import be.cin.mycarenet.esb.common.v2.NihiiType
import be.cin.mycarenet.esb.common.v2.OrigineType
import be.cin.mycarenet.esb.common.v2.PackageType
import be.cin.mycarenet.esb.common.v2.ValueRefString
import be.cin.nip.async.generic.GetResponse
import be.cin.nip.async.generic.Post
import be.cin.nip.async.generic.PostResponse
import ma.glasnost.orika.MapperFacade
import org.apache.commons.codec.binary.Base64
import org.apache.commons.io.IOUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.genericasync.builders.BuilderFactory
import org.taktik.connector.business.genericasync.service.impl.GenAsyncServiceImpl
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.util.WsAddressingUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.handler.domain.WsAddressingHeader
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.efact.EfactMessage
import org.taktik.freehealth.middleware.dto.efact.EfactSendResponse
import org.taktik.freehealth.middleware.dto.efact.ErrorDetail
import org.taktik.freehealth.middleware.dto.efact.FlatFileWithMetadata
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import org.taktik.freehealth.middleware.dto.efact.Record
import org.taktik.freehealth.middleware.dto.efact.Zone
import org.taktik.freehealth.middleware.dto.efact.segments.RecordOrSegmentDescription
import org.taktik.freehealth.middleware.dto.efact.segments.ZoneDescription
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatReader
import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatWriter
import org.taktik.freehealth.middleware.service.EfactService
import org.taktik.freehealth.middleware.service.STSService
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter
import java.net.URI
import java.net.URISyntaxException
import java.text.DecimalFormat
import java.util.ArrayList
import java.util.LinkedList
import java.util.UUID
import javax.xml.ws.soap.SOAPFaultException
import kotlin.Comparator

@Service
class EfactServiceImpl(private val stsService: STSService, private val mapper: MapperFacade) : EfactService {
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val genAsyncService = GenAsyncServiceImpl("invoicing")

    override fun makeFlatFile(batch: InvoicesBatch, isTest: Boolean): String {
        require(batch.numericalRef?.let { it <= 999999999999L } ?: false) { batch.numericalRef?.let { "numericalRef is too long (12 positions max)" } ?: "numericalRef is missing" }
        requireNotNull(batch.sender) { "Sender cannot be null" }
        requireNotNull(batch.batchRef) { "BatchRef cannot be null" }
        requireNotNull(batch.uniqueSendNumber) { "UniqueSendNumber cannot be null" }

        batch.invoices.forEach {
            requireNotNull(it.invoiceNumber) { "One of the invoices has an empty invoice number" }
            requireNotNull(it.invoiceRef) { "One of the invoices has an empty invoice ref" }
            requireNotNull(it.ioCode) { "One of the invoices has an empty io code" }
            requireNotNull(it.patient) { "One of the invoices has an empty patient" }
        }

        val stringWriter = StringWriter()
        val iv = BelgianInsuranceInvoicingFormatWriter(stringWriter)

        try {
            iv.write200and300(batch.sender!!, batch.numericalRef
                ?: 0, batch.fileRef!!, if (isTest) 92 else 12, batch.uniqueSendNumber!!, batch.invoicingYear, batch.invoicingMonth, isTest)
            val metadata = makeFlatFileCore(iv, batch, isTest)

            for (k in metadata.codesPerOAMap.keys) {
                iv.write400(k, batch.numericalRef, metadata.recordsCountPerOAMap[k]!![0], metadata.codesPerOAMap[k]!!, metadata.amountPerOAMap[k]!![0])
            }
            iv.write960000(batch.ioFederationCode!!.replace(Regex("00$"), "99"), metadata.recordsCount, metadata.codes, metadata.amount)
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }

        return stringWriter.toString()
    }


    override fun makeFlatFileCoreWithMetadata(batch: InvoicesBatch, isTest: Boolean): FlatFileWithMetadata {
        require(batch.numericalRef?.let { it <= 999999999999L } ?: false) { batch.numericalRef?.let { "numericalRef is too long (12 positions max)" } ?: "numericalRef is missing" }
        requireNotNull(batch.sender) { "Sender cannot be null" }
        requireNotNull(batch.batchRef) { "BatchRef cannot be null" }
        requireNotNull(batch.uniqueSendNumber) { "UniqueSendNumber cannot be null" }

        batch.invoices.forEach {
            requireNotNull(it.invoiceNumber) { "One of the invoices has an empty invoice number" }
            requireNotNull(it.invoiceRef) { "One of the invoices has an empty invoice ref" }
            requireNotNull(it.ioCode) { "One of the invoices has an empty io code" }
            requireNotNull(it.patient) { "One of the invoices has an empty patient" }
        }

        val stringWriter = StringWriter()
        val iv = BelgianInsuranceInvoicingFormatWriter(stringWriter)

        val metadata = try {
            this.makeFlatFileCore(iv, batch, isTest)
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }

        return FlatFileWithMetadata().apply { this.flatFile = stringWriter.toString(); this.metadata = metadata }
    }

    fun makeFlatFileCore(iv: BelgianInsuranceInvoicingFormatWriter,
        batch: InvoicesBatch,
        isTest: Boolean): FlatFileWithMetadata.FlatFileMetadata {
        val metadata = FlatFileWithMetadata.FlatFileMetadata()

        var rn =
            iv.writeFileHeader(1, batch.sender!!, if (isTest) 9991999L else 1999L, batch.uniqueSendNumber!!, batch.invoicingYear, batch.invoicingMonth, batch.batchRef!!)
        metadata.recordsCount++

        for (invoice in batch.invoices.sortedWith(Comparator { i1, i2 ->
            when {
                i1.creditNote && !i2.creditNote -> -1
                i2.creditNote && !i1.creditNote -> 1
                else -> iv.getDestCode(i1.ioCode!!, batch.sender!!).compareTo(iv.getDestCode(i2.ioCode!!, batch.sender!!))
            }
        })) {
            if (invoice.items.isNotEmpty()) {
                val destCode = iv.getDestCode(invoice.ioCode!!, batch.sender!!)

                val codesPerOA: MutableList<Long> = metadata.codesPerOAMap.getOrPut(destCode) { LinkedList() }
                val amountPerOA: Array<Long> = metadata.amountPerOAMap.getOrPut(destCode) { arrayOf(0L) }  //An array to pass it by reference
                val recordsCountPerOA: Array<Long> = metadata.recordsCountPerOAMap.getOrPut(destCode) { arrayOf(0L) }  //An array to pass it by reference

                val recordCodes = ArrayList<Long>()
                var recordAmount = 0L
                var recordFee = 0L
                var recordSup = 0L
                rn =
                    iv.writeRecordHeader(rn, batch.sender!!, invoice.invoiceNumber!!, invoice.reason!!, invoice.invoiceRef!!, invoice.patient!!, invoice.ioCode!!, invoice.ignorePrescriptionDate, invoice.hospitalisedPatient, invoice.creditNote, invoice.relatedBatchSendNumber, invoice.relatedBatchYearMonth, invoice.relatedInvoiceIoCode, invoice.relatedInvoiceNumber)
                recordsCountPerOA[0]++
                metadata.recordsCount++
                for (it in invoice.items) {
                    rn = iv.writeRecordContent(rn, batch.sender!!, batch.invoicingYear, batch.invoicingMonth, invoice.patient!!, invoice.ioCode!!, it)

                    recordsCountPerOA[0]++
                    metadata.recordsCount++

                    if (it.insuranceRef != null) {
                        rn =
                            iv.writeInvolvementRecordContent(rn, batch.invoicingYear, batch.invoicingMonth, invoice.patient!!, it)
                        recordCodes.add(it.codeNomenclature)
                        recordsCountPerOA[0]++
                        metadata.recordsCount++
                    }

                    if (it.eidItem != null) {
                        rn = iv.writeEid(rn, it, invoice.patient!!, batch.sender!!)
                        recordCodes.add(it.codeNomenclature)
                        recordsCountPerOA[0]++
                        metadata.recordsCount++
                    }

                    codesPerOA!!.add(it.codeNomenclature)
                    amountPerOA!![0] += it.reimbursedAmount

                    recordCodes.add(it.codeNomenclature)
                    recordAmount += it.reimbursedAmount
                    recordFee += it.patientFee
                    recordSup += it.doctorSupplement

                }
                rn =
                    iv.writeRecordFooter(rn, batch.sender!!, invoice.invoiceNumber!!, invoice.invoiceRef!!, invoice.patient!!, invoice.ioCode!!, recordCodes, recordAmount, recordFee, recordSup)
                recordsCountPerOA[0]++
                metadata.recordsCount++

                metadata.codes.addAll(recordCodes)
                metadata.amount += recordAmount
            }
        }
        iv.writeFileFooter(rn, batch.sender!!, batch.uniqueSendNumber, batch.invoicingYear, batch.invoicingMonth, metadata.codes, metadata.amount)
        metadata.recordsCount++
        return metadata
    }

    fun sanitizeBatchNames(batch: InvoicesBatch): InvoicesBatch {
        val specialChars = Regex("[!@#&/\\\\]")
        batch.sender = batch.sender?.apply {
            firstName = firstName?.replace(specialChars, "")
            lastName = lastName?.replace(specialChars, "")
        }

        batch.invoices.replaceAll {
            it.patient = it.patient?.apply {
                firstName = firstName?.replace(specialChars, "")
                lastName = lastName?.replace(specialChars, "")
                spouseName = spouseName?.replace(specialChars, "")
                maidenName = maidenName?.replace(specialChars, "")
                partnerName = partnerName?.replace(specialChars, "")
            }
            it
        }

        return batch
    }

    override fun sendBatch(keystoreId: UUID, tokenId: UUID, passPhrase: String, batch: InvoicesBatch): EfactSendResponse {
        requireNotNull(keystoreId) { "Keystore id cannot be null" }
        requireNotNull(tokenId) { "Token id cannot be null" }
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Efact operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)

        val isTest = config.getProperty("endpoint.mcn.tarification").contains("-acpt")

        val sanitizedBatch = sanitizeBatchNames(batch)

        val fed = sanitizedBatch.ioFederationCode
        val inputReference = "" + DecimalFormat("00000000000000").format(sanitizedBatch.numericalRef ?: 0)
        val content = makeFlatFile(sanitizedBatch, isTest)

        val requestObjectBuilder = try {
            BuilderFactory.getRequestObjectBuilder("invoicing")
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }

        val blob = RequestBuilderFactory.getBlobBuilder("invoicing").build(content.toByteArray(Charsets.UTF_8))

        val messageName = "HCPFAC" // depends on content of message HCPFAC HCPAFD or HCPVWR
        blob.messageName = messageName

        // Creation of the request

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = isTest!!
            }
            origin =
                buildOriginType(sanitizedBatch.sender!!.nihii!!.toString(), sanitizedBatch.sender!!.ssin!!.toString(), sanitizedBatch.sender!!.firstName!!, sanitizedBatch.sender!!.lastName!!)
            this.inputReference = inputReference
        }

        val xades = BlobUtil.generateXades(SendRequestMapper.mapBlobToBlobType(blob), credential, "invoicing").value

        val post = requestObjectBuilder.buildPostRequest(ci, SendRequestMapper.mapBlobToCinBlob(blob), xades)
        val header: WsAddressingHeader
        try {
            header = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:post:msg"))
            header.to = URI(if (fed != null) "urn:nip:destination:io:$fed" else "")
            header.faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
            header.replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
            header.messageID = URI("" + UUID.randomUUID())
        } catch (e: URISyntaxException) {
            throw IllegalStateException(e)
        }

        val postResponse = genAsyncService.postRequest(samlToken, post, header)

        val tack = postResponse.getReturn()
        val success = tack.resultMajor != null && tack.resultMajor == "urn:nip:tack:result:major:success"

        val records = BelgianInsuranceInvoicingFormatReader("unused").parse(content.reader(), false)!!.map { mapper.map(it, Record::class.java) }
        return EfactSendResponse(success, inputReference, tack, content, records).apply {
            this.mycarenetConversation = MycarenetConversation().apply {
                this.transactionRequest = MarshallerHelper(Post::class.java, Post::class.java).toXMLByteArray(post).toString(Charsets.UTF_8)
                this.transactionResponse = MarshallerHelper(PostResponse::class.java, PostResponse::class.java).toXMLByteArray(postResponse).toString(Charsets.UTF_8)
                postResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                postResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
            }
        }
    }

    override fun loadMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        language: String
                             ): List<EfactMessage> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Efact operations")

        val isTest = config.getProperty("endpoint.mcn.tarification").contains("-acpt")

        requireNotNull(keystoreId) { "Keystore id cannot be null" }
        requireNotNull(tokenId) { "Token id cannot be null" }

        val inputReference = "" + System.currentTimeMillis()
        val requestObjectBuilder = try {
            BuilderFactory.getRequestObjectBuilder("invoicing")
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = isTest
            }
            origin = buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
            this.inputReference = inputReference
        }

        val header = try {
            WsAddressingHeader(URI("urn:be:cin:nip:async:generic:get:query")).apply {
                faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
                replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
                messageID = URI("" + UUID.randomUUID())
            }
        } catch (e: URISyntaxException) {
            throw IllegalStateException(e)
        }

        var batchSize = 256

        val eFactMessages = ArrayList<EfactMessage>()

        while (true) {
            val msgQuery = requestObjectBuilder.createMsgQuery(batchSize, true, "HCPFAC", "HCPAFD", "HCPVWR")
            val query = requestObjectBuilder.createQuery(batchSize, true)

            val getResponse: GetResponse
            try {
                getResponse =
                    genAsyncService.getRequest(samlToken, requestObjectBuilder.buildGetRequest(ci.origin, msgQuery, query), header)
            } catch (e: TechnicalConnectorException) {
                if ((e.message?.contains("SocketTimeout") == true) && batchSize > 1) {
                    batchSize /= 2
                    continue
                }
                throw IllegalStateException(e)
            } catch (e: SOAPFaultException) {
                if (e.message?.contains("Not enough time") == true) {
                    break
                }
                throw IllegalStateException(e)
            }

            eFactMessages += getResponse.getReturn().msgResponses.map { r ->
                EfactMessage().apply {
                    id = r.detail.id
                    name = r.detail.messageName

                    commonOutput = CommonOutput().apply {
                        this.inputReference = r.commonOutput.inputReference
                        this.nipReference = r.commonOutput.nipReference
                        this.outputReference = r.commonOutput.outputReference
                    }
                    try {
                        detail =
                            String(ConnectorIOUtils.decompress(IOUtils.toByteArray(r.detail.value.inputStream)), Charsets.UTF_8) //This starts with 92...

                        message = BelgianInsuranceInvoicingFormatReader(language).parse(StringReader(this.detail!!))?.map {
                            Record(mapper.map(it.description, RecordOrSegmentDescription::class.java), it.zones.map { z -> Zone(mapper.map(z.zoneDescription, ZoneDescription::class.java), z.value)}, mapper.map(it.errorDetail, ErrorDetail::class.java))
                        }
                        xades = Base64.encodeBase64String(r.xadesT.value)
                        hashValue = Base64.encodeBase64String(r.detail.hashValue)
                    } catch (e: IOException) {}
                }
            } + getResponse.getReturn().tAckResponses.map { r ->
                EfactMessage().apply {
                    id = r.tAck.appliesTo.replace("urn:nip:reference:input:".toRegex(), "")
                    name = "tAck"
                    try {
                        tAck = r.tAck
                        xades = Base64.encodeBase64String(r.xadesT.value)
                        hashValue = Base64.encodeBase64String(r.tAck.value)
                    } catch (e: IOException) {}
                }
            }

            break
        }
        return eFactMessages
    }

    override fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>
                            ): Boolean {
        if (valueHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Efact operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")

        val confirm =
            BuilderFactory.getRequestObjectBuilder("invoicing")
                .buildConfirmRequestWithHashes(buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                                               listOf(),
                                               valueHashes.map { valueHash -> java.util.Base64.getDecoder().decode(valueHash) })

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

    override fun confirmMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>
    ): Boolean {
        if (valueHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Efact operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("invoicing")
                .buildConfirmRequestWithHashes(buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                    valueHashes.map { valueHash -> java.util.Base64.getDecoder().decode(valueHash) },
                    listOf()
                    )

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }


    private fun buildOriginType(nihii: String, ssin: String, firstName: String, lastName: String): OrigineType =
        OrigineType().apply {
            val principal = SecurityContextHolder.getContext().authentication?.principal as? User

            `package` = PackageType().apply {
                name = ValueRefString().apply { value = config.getProperty("genericasync.invoicing.package.name") }
                license = LicenseType().apply {
                    this.username = principal?.mcnLicense ?: config.getProperty("mycarenet.license.username")
                    this.password = principal?.mcnPassword ?: config.getProperty("mycarenet.license.password")
                }
            }
            careProvider = CareProviderType().apply {
                this.nihii = NihiiType().apply {
                    quality = "doctor"
                    value = ValueRefString().apply { value = nihii }
                }
                physicalPerson = IdType().apply {
                    this.nihii = NihiiType().apply { value = ValueRefString().apply { value = nihii } }
                    this.ssin = ValueRefString().apply { value = ssin }
                    this.name = ValueRefString().apply { value = "$firstName $lastName" }
                }
            }
        }

}
