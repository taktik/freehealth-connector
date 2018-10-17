package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.messageservices.core.v1.*
import be.fgov.ehealth.mycarenet.commons.core.v2.IdType
import be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v2.PartyType
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse
import be.fgov.ehealth.standards.kmehr.cd.v1.*
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
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
import org.taktik.freehealth.middleware.dto.MycarenetError
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.TarificationService
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.io.UnsupportedEncodingException
import java.time.LocalDateTime
import java.util.UUID
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
                              codes: List<String>): TarificationConsultationResult {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")

        try {
            val isTest = config.getProperty("endpoint.mcn.tarification").contains("-acpt")
            val now = DateTime().withMillisOfSecond(0).withZone(null)
            val kmehrUUID = now.toString("YYYYddhhmmssSS")
            val reqId = "$hcpNihii.$kmehrUUID"

            var careProviderFirstName =  hcpFirstName;
            var careProviderLastName =  hcpLastName;

            traineeSupervisorFirstName?.let {
                careProviderFirstName =  it;
            }
            traineeSupervisorLastName?.let {
                careProviderLastName =  it;
            }

            val csDT = DateTime(consultationDate.year, consultationDate.monthValue, consultationDate.dayOfMonth, 0, 0)
            val req = RetrieveTransactionRequest().apply {
                val author = AuthorType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = "persphysician" })
                        firstname = careProviderFirstName
                        familyname = careProviderLastName
                    })
                }

                this.request = RequestType().apply {
                    id = IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = reqId }
                    this.author = author
                    date = now; time = now
                }
                this.select = SelectRetrieveTransactionType().apply {
                    patient = PatientType().apply {
                        ids.add(IDPATIENT().apply { s = IDPATIENTschemes.ID_PATIENT; sv = "1.0"; value = patientSsin })
                    }
                    transaction = TransactionType().apply {
                        var h = 1
                        this.author = author
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

                        var careProviderSsin =  hcpSsin;
                        var careProviderNihii =  hcpNihii;

                        traineeSupervisorSsin?.let {
                            careProviderSsin =  it;
                        }
                        traineeSupervisorNihii?.let {
                            careProviderNihii =  it;
                        }

                        this.careProvider = be.fgov.ehealth.mycarenet.commons.core.v2.CareProviderType().apply {
                            this.nihii = be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType().apply {
                                this.quality = "doctor"
                                this.value =
                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                        .apply { this.value = careProviderNihii }
                            }
                            this.physicalPerson = be.fgov.ehealth.mycarenet.commons.core.v2.IdType().apply {
                                this.ssin =
                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                        .apply { this.value = careProviderSsin }
                            }
                        }
                        traineeSupervisorSsin?.let {
                            this.sender = PartyType().apply {
                                physicalPerson = IdType().apply {
                                    this.ssin =
                                        be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString().apply { this.value = hcpSsin }
                                    careProviderNihii?.let {
                                        this.nihii =
                                            NihiiType().apply {
                                                this.quality = "doctor"
                                                this.value =
                                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                                        .apply { this.value = hcpNihii }
                                            }
                                    }
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

            val result = TarificationConsultationResult()

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

            result.retrieveTransactionRequest = xmlByteArray.toString(Charsets.UTF_8);

            val kmehrRequestMarshallerResp =
                MarshallerHelper(RetrieveTransactionResponse::class.java, RetrieveTransactionResponse::class.java)
            val xmlByteArrayResp = kmehrRequestMarshallerResp.toXMLByteArray(commonInputResponse)
            result.commonInputResponse = xmlByteArrayResp.toString(Charsets.UTF_8);


            val kmehrRequestMarshallerNIPP =
                MarshallerHelper(TarificationConsultationResponse::class.java, TarificationConsultationResponse::class.java)
            val xmlByteArrayNIPP = kmehrRequestMarshallerNIPP.toXMLByteArray(consultTarificationResponse)
            result.tarificationConsultationResponse = xmlByteArrayNIPP.toString(Charsets.UTF_8);

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
            return "cd[${node.getAttribute("S") ?: node.getAttribute("SL")}]"
        }
        return localName
    }
}
