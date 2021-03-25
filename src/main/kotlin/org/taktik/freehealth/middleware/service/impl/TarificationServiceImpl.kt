package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.messageservices.core.v1.*
import be.fgov.ehealth.mycarenet.commons.core.v2.IdType
import be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v2.PartyType
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse
import be.fgov.ehealth.standards.kmehr.cd.v1.*
import be.fgov.ehealth.standards.kmehr.id.v1.*
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import com.google.gson.Gson
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.etarif.TarificationConsultationResult
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareReceiverId
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.TarificationService
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.io.UnsupportedEncodingException
import java.time.LocalDateTime
import java.util.*
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@Service
class TarificationServiceImpl(private val stsService: STSService) : TarificationService {
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val log = LogFactory.getLog(this.javaClass)
    private val freehealthTarificationService = org.taktik.connector.business.tarification.impl.TarificationServiceImpl()
    private val ConsultTarifErrors =
        Gson().fromJson(
            this.javaClass.getResourceAsStream("/be/errors/ConsultTarifErrors.json").reader(Charsets.UTF_8),
            arrayOf<MycarenetError>().javaClass
        ).associateBy({ it.uid }, { it })
    private val xPathfactory = XPathFactory.newInstance()

    override fun consultTarif(keystoreId: UUID,
                              tokenId: UUID,
                              hcpFirstName: String,
                              hcpLastName: String,
                              hcpNihii: String,
                              hcpSsin: String,
                              passPhrase: String,
                              patientSsin: String?,
                              consultationDate: LocalDateTime,
                              justification: String?,
                              gmdNihii: String?,
                              traineeSupervisorSsin: String?,
                              traineeSupervisorNihii: String?,
                              traineeSupervisorFirstName: String?,
                              traineeSupervisorLastName: String?,
                              guardPostNihii: String?,
                              guardPostSsin: String?,
                              codes: List<String>): TarificationConsultationResult {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Tarif operations")

        try {
            val isTest = config.getProperty("endpoint.mcn.tarification").contains("-acpt")
            val now = DateTime().withMillisOfSecond(0).withZone(null)
            val kmehrUUID = now.toString("YYYYddhhmmssSS")
            val requestAuthorNihii = (guardPostNihii ?: hcpNihii).padEnd(11, '0')
            val requestAuthorSsin = guardPostSsin ?: hcpSsin
            val reqId = "${(guardPostNihii ?: hcpNihii).padEnd(11, '0')}.$kmehrUUID"

            //  The author is always the caller
            val author = AuthorType().apply {
                hcparties.add(HcpartyType().apply {
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =  hcpNihii })
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = "persphysician" })
                    firstname = hcpFirstName
                    familyname = hcpLastName
                })
            }

            //  The supervisor
            val supervisor = AuthorType().apply {
                hcparties.add(HcpartyType().apply {
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =  traineeSupervisorNihii })
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = traineeSupervisorSsin })
                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = "persphysician" })
                    firstname = traineeSupervisorFirstName
                    familyname = traineeSupervisorLastName
                })
            }

            val csDT = DateTime(consultationDate.year, consultationDate.monthValue, consultationDate.dayOfMonth, 0, 0)
            val req = RetrieveTransactionRequest().apply {

                this.request = RequestType().apply {
                    id = IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = reqId }
                    this.author = AuthorType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =  requestAuthorNihii })
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = requestAuthorSsin })
                            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = if (guardPostNihii?.isEmpty() != false) "persphysician" else "guardpost" })
                            firstname = hcpFirstName
                            familyname = hcpLastName
                        })
                    }
                    date = now; time = now
                }
                this.select = SelectRetrieveTransactionType().apply {
                    patient = PatientType().apply {
                        ids.add(IDPATIENT().apply { s = IDPATIENTschemes.ID_PATIENT; sv = "1.0"; value = patientSsin })
                    }
                    transaction = TransactionType().apply {
                        var h = 1

                        traineeSupervisorNihii?.let {
                            this.author = supervisor
                        } ?: run {
                            this.author = author
                        }

                        cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv = "1.1"; value = "tariff" })
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                            cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "encounterdatetime" })
                            contents.add(ContentType().apply { date = csDT })
                        })
                        headingsAndItemsAndTexts.addAll(codes.map { code ->
                            ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "claim" })
                                contents.add(ContentType().apply { cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_NIHDI; sv = "1.0"; value = code }) })
                            }
                        })
                        justification?.let { j ->
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "justification" })
                                contents.add(ContentType().apply { cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_MYCARENET_JUSTIFICATION; sv = "1.0"; value = j }) })
                            })
                        }
                        gmdNihii?.let { g ->
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "gmdmanager" })
                                contents.add(ContentType().apply {
                                    hcparty = HcpartyType().apply {
                                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = g })
                                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = "persphysician" })
                                    }
                                })
                            })
                        }
                    }
                }
            }

            val kmehrRequestMarshaller =
                MarshallerHelper(RetrieveTransactionRequest::class.java, RetrieveTransactionRequest::class.java)
            val xmlByteArray = kmehrRequestMarshaller.toXMLByteArray(req)

            if (xmlByteArray != null && config.getBooleanProperty("mcn.tarification.dumpMessages", false)) {
                log.debug("RequestObjectBuilder : created blob content: " + String(xmlByteArray))
            }

            val blob =
                BlobBuilderFactory.getBlobBuilder("mcn.tarification").build(xmlByteArray)

            val request = TarificationConsultationRequest().apply {
                this.commonInput = be.fgov.ehealth.mycarenet.commons.core.v2.CommonInputType().apply {
                    this.inputReference = kmehrUUID
                    this.request = be.fgov.ehealth.mycarenet.commons.core.v2.RequestType().apply {
                        this.isIsTest = isTest
                    }
                    this.origin = be.fgov.ehealth.mycarenet.commons.core.v2.OriginType().apply {
                        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

                        this.`package` = be.fgov.ehealth.mycarenet.commons.core.v2.PackageType().apply {
                            this.name =
                                be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                    .apply { this.value = config.getProperty("mcn.registration.package.name") }
                            this.license = be.fgov.ehealth.mycarenet.commons.core.v2.LicenseType().apply {
                                this.username = principal?.mcnLicense ?: config.getProperty("mycarenet.license.username")
                                this.password = principal?.mcnPassword ?: config.getProperty("mycarenet.license.password")
                            }
                        }

                        this.careProvider = be.fgov.ehealth.mycarenet.commons.core.v2.CareProviderType().apply {
                            this.nihii = be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType().apply {
                                this.quality = if (guardPostNihii?.isEmpty() != false) "doctor" else "guardpost"
                                this.value =
                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                        .apply { this.value = requestAuthorNihii }
                            }
                            if (guardPostNihii?.isEmpty() != false) {
                                this.physicalPerson = be.fgov.ehealth.mycarenet.commons.core.v2.IdType().apply {
                                    this.ssin =
                                        be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                            .apply { this.value = hcpSsin }
                                }
                            }
                        }
                    }
                }
                this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
                this.issueInstant = DateTime()
                this.routing = SendRequestMapper.mapRouting(Routing(CareReceiverId(patientSsin), csDT))
                this.detail = SendRequestMapper.mapBlobToBlobType(blob)
            }

            var consultTarificationResponse = freehealthTarificationService.consultTarification(samlToken, request)

            val detail = consultTarificationResponse.getReturn().detail
            val content = BlobBuilderFactory.getBlobBuilder("mcn.tarification").checkAndRetrieveContent(SendRequestMapper.mapBlobTypeToBlob(detail))

            val helper =
                MarshallerHelper(RetrieveTransactionResponse::class.java, RetrieveTransactionResponse::class.java)
            val commonInputResponse = helper.toObject(content)

            val commonOutput =
                CommonOutput(
                    consultTarificationResponse.`return`?.commonOutput?.inputReference?.toString(),
                    consultTarificationResponse.`return`?.commonOutput?.nipReference?.toString(),
                    consultTarificationResponse.`return`?.commonOutput?.outputReference?.toString())

            val result = TarificationConsultationResult().apply {
                this.mycarenetConversation = MycarenetConversation().apply {
                    consultTarificationResponse.soapRequest?.writeTo(this.soapRequestOutputStream())
                    consultTarificationResponse.soapResponse?.writeTo(this.soapResponseOutputStream())
                    this.transactionRequest = MarshallerHelper(TarificationConsultationRequest::class.java, TarificationConsultationRequest::class.java)
                        .toXMLByteArray(request)
                        .toString(Charsets.UTF_8)
                    this.transactionResponse = MarshallerHelper(RetrieveTransactionResponse::class.java, RetrieveTransactionResponse::class.java)
                        .toXMLByteArray(commonInputResponse)
                        .toString(Charsets.UTF_8)
                }
               this.commonOutput = commonOutput
            }

            val errors = commonInputResponse.acknowledge.errors?.flatMap { e ->
                e.cds.find { it.s == CDERRORMYCARENETschemes.CD_ERROR }?.value?.let { ec ->
                    extractError(xmlByteArray, ec, e.url)
                } ?: setOf()
            }

            val kmehrmessage = commonInputResponse.kmehrmessage
            if (kmehrmessage != null && kmehrmessage!!.folders != null && kmehrmessage!!.folders.size > 0) {
                result.niss = patientSsin

                val folder = kmehrmessage!!.folders.get(0)
                if (folder.patient != null) {
                    result.fill(folder.patient)
                }
                if (folder.transactions != null) {
                    result.fill(folder.transactions)
                }
            }

            result.errors = errors

            return result
        } catch (e: ConnectorException) {
            throw IllegalStateException(e)
        } catch (e: UnsupportedEncodingException) {
            throw IllegalStateException(e)
        }

    }

    private fun extractError(sendTransactionRequest: ByteArray, ec: String, errorUrl: String?): Set<MycarenetError> {
        return errorUrl?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = true
            val builder = factory.newDocumentBuilder()

            val xpath = xPathfactory.newXPath()
            val expr = xpath.compile(if (url.startsWith("/")) url else "/" + url)
            val result = mutableSetOf<MycarenetError>()

            (expr.evaluate(
                builder.parse(ByteArrayInputStream(sendTransactionRequest)),
                XPathConstants.NODESET
            ) as NodeList).let { it ->
                if (it.length > 0) {
                    var node = it.item(0)
                    val textContent = node.textContent
                    var base = "/" + nodeDescr(node)
                    while (node.parentNode != null && node.parentNode is Element) {
                        base = "/${nodeDescr(node.parentNode)}$base"
                        node = node.parentNode
                    }
                    val elements =
                        ConsultTarifErrors.values.filter {
                            it.path == base && it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*")))
                        }
                    if (elements.isEmpty()) {
                        //IOs sometimes are overeager to provide us with precise xpath. Let's try again while truncating after the item
                        base = base.replace(Regex("(.+/item.+?)/.*"), "$1")
                        ConsultTarifErrors.values.filter {
                            it.path == base && it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*")))
                        }
                    }

                    elements.forEach { it.value = textContent }
                    result.addAll(elements)
                } else {
                    result.add(
                        MycarenetError(
                            code = ec,
                            path = url,
                            msgFr = "Erreur générique, xpath invalide",
                            msgNl = "Onbekend foutmelding, xpath ongeldig"
                        )
                    )
                }
            }
            if (result.isEmpty()) {
                result.add(
                    MycarenetError(
                        code = ec,
                        path = url,
                        msgFr = "Erreur générique, xpath invalide",
                        msgNl = "Onbekend foutmelding, xpath ongeldig"
                                  ))

            }
            result
        } ?: setOf()
    }

    private fun nodeDescr(node: Node): String {
        val localName = node.localName ?: node.nodeName?.replace(Regex(".+?:(.+)"), "$1") ?: "unknown"
        val xpath = xPathfactory.newXPath()
        xpath.namespaceContext = object : NamespaceContext {
            override fun getNamespaceURI(prefix: String?) = when (prefix) {
                "ns2" -> "http://www.ehealth.fgov.be/messageservices/core/v1"
                "ns3" -> "http://www.ehealth.fgov.be/standards/kmehr/schema/v1"
                else -> null
            }

            override fun getPrefix(namespaceURI: String?) = when (namespaceURI) {
                "http://www.ehealth.fgov.be/messageservices/core/v1" -> "ns2"
                "http://www.ehealth.fgov.be/standards/kmehr/schema/v1" -> "ns3"
                else -> null
            }

            override fun getPrefixes(namespaceURI: String?): Iterator<Any?> =
                when (namespaceURI) {
                    "http://www.ehealth.fgov.be/messageservices/core/v1" -> listOf("ns2").iterator()
                    "http://www.ehealth.fgov.be/standards/kmehr/schema/v1" -> listOf("ns3").iterator()
                    else -> listOf<String>().iterator()
                }
        }
        if (localName == "transaction") {
            return "transaction[${xpath.evaluate("ns2:cd[@S=\"CD-TRANSACTION-MYCARENET\"]", node)}]"
        }
        if (localName == "item") {
            return "item[${xpath.evaluate("ns3:cd[@S=\"CD-ITEM-MYCARENET\" or @S=\"CD-ITEM\"]", node)}]"
        }
        if (localName == "cd" && node is Element) {
            if (node.getAttribute("SL").isNotEmpty()) {
                return "cd[${node.getAttribute("SL")}]"
            }
            return "cd[${node.getAttribute("S")}]"
        }
        return localName
    }
}
