package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.messageservices.core.v1.RequestType
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionRequest
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionResponse
import be.fgov.ehealth.messageservices.core.v1.SelectRetrieveTransactionType
import be.fgov.ehealth.messageservices.core.v1.TransactionType
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.Error
import org.taktik.connector.business.domain.etarif.TarificationConsultationResult
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareReceiverId
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.TarificationService
import java.io.UnsupportedEncodingException
import java.time.LocalDateTime
import java.util.UUID

@Service
class TarificationServiceImpl(private val stsService: STSService) : TarificationService {
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val log = LogFactory.getLog(this.javaClass)
    private val freehealthTarificationService = org.taktik.connector.business.tarification.impl.TarificationServiceImpl()

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
                              codes: List<String>): TarificationConsultationResult {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")

        try {
            val isTest = config.getProperty("endpoint.mcn.tarification").contains("-acpt")
            val dateTime = DateTime().withMillisOfSecond(0).withZone(null)
            val kmehrUUID = dateTime.toString("YYYYddhhmmssSS")

            val now = DateTime.now().withMillisOfSecond(0)

            val req = RetrieveTransactionRequest().apply {
                val author = AuthorType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.10"; value = "persphysician" })
                        firstname = hcpFirstName
                        familyname = hcpLastName
                    })
                }

                this.request = RequestType().apply {
                    id = IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "$hcpNihii.$kmehrUUID" }
                    this.author = author
                    date = now; time = now
                }
                this.select = SelectRetrieveTransactionType().apply {
                    transaction = TransactionType().apply {
                        var h = 1
                        this.author = author
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                        cds.add(CDTRANSACTION().apply { s=CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv="1.1"; value = "tariff" })
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                            cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv="1.0"; value = "encounterdatetime" })
                            contents.add(ContentType().apply { date = DateTime(consultationDate.year, consultationDate.monthValue, consultationDate.dayOfMonth, consultationDate.hour, consultationDate.minute) })
                        })
                        headingsAndItemsAndTexts.addAll(codes.map { code ->
                            ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv="1.0"; value = "claim" })
                                contents.add(ContentType().apply { cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_NIHDI; sv = "1.0"; value = code }) })
                        }})
                        justification?.let { j ->
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv="1.0"; value = "justification" })
                                contents.add(ContentType().apply { cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_MYCARENET_JUSTIFICATION; sv = "1.0"; value = j }) })
                            })
                        }
                        gmdNihii?.let { g ->
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv="1.0"; value = "gmdmanager" })
                                contents.add(ContentType().apply { hcparty = HcpartyType().apply {
                                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = g })
                                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.10"; value = "persphysician" })
                                }})
                            })
                        }
                    }
                }
            }


            val kmehrRequestMarshaller =
                MarshallerHelper(RetrieveTransactionRequest::class.java, RetrieveTransactionRequest::class.java)
            val xmlByteArray = kmehrRequestMarshaller.toXMLByteArray(req)
            if (xmlByteArray != null && config.getBooleanProperty(
                    "be.ehealth.businessconnector.dmg.builders.impl.dumpMessages",
                    false
                                                                 )) {
                log.debug("RequestObjectBuilder : created blob content: " + String(xmlByteArray))
            }

            val blob =
                BlobBuilderFactory.getBlobBuilder("mcn.tarification").build(xmlByteArray)

            val request = TarificationConsultationRequest().apply {
                this.commonInput = be.fgov.ehealth.mycarenet.commons.core.v2.CommonInputType().apply {
                    this.request = be.fgov.ehealth.mycarenet.commons.core.v2.RequestType().apply {
                        this.isIsTest = isTest
                    }
                    this.origin = be.fgov.ehealth.mycarenet.commons.core.v2.OriginType().apply {
                        this.`package` = be.fgov.ehealth.mycarenet.commons.core.v2.PackageType().apply {
                            this.name =
                                be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                    .apply { this.value = config.getProperty("mcn.registration.package.name") }
                            this.license = be.fgov.ehealth.mycarenet.commons.core.v2.LicenseType().apply {
                                this.username = config.getProperty("mycarenet.license.username")
                                this.password = config.getProperty("mycarenet.license.password")
                            }
                        }
                        this.careProvider = be.fgov.ehealth.mycarenet.commons.core.v2.CareProviderType().apply {
                            this.nihii = be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType().apply {
                                this.quality = "doctor"
                                this.value =
                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                        .apply { this.value = hcpNihii }
                            }
                            this.physicalPerson = be.fgov.ehealth.mycarenet.commons.core.v2.IdType().apply {
                                this.ssin =
                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                        .apply { this.value = hcpSsin }
                            }
                        }
                    }
                }
                this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
                this.issueInstant = DateTime()
                this.routing = SendRequestMapper.mapRouting(Routing(CareReceiverId(patientSsin), DateTime()))
                this.detail = SendRequestMapper.mapBlobToBlobType(blob)
            }

            val consultTarificationResponse = freehealthTarificationService.consultTarification(samlToken, request)
            val detail = consultTarificationResponse.getReturn().detail
            val content = BlobBuilderFactory.getBlobBuilder("mcn.tarification").checkAndRetrieveContent(SendRequestMapper.mapBlobTypeToBlob(detail))

            val helper =
                MarshallerHelper(RetrieveTransactionResponse::class.java, RetrieveTransactionResponse::class.java)
            val commonInputResponse = helper.toObject(content)

            val result = TarificationConsultationResult()

            val errorsMyCarenetType = commonInputResponse.acknowledge.errors
            for (errorMyCarenetType in errorsMyCarenetType) {
                val error = Error()
                var errorCodes: StringBuilder? = null
                for (errorCode in errorMyCarenetType.cds) {
                    if (errorCodes != null) {
                        errorCodes.append(" ").append(errorCode.value)
                    } else {
                        errorCodes = StringBuilder(errorCode.value)
                    }
                }
                if (errorCodes != null) {
                    error.code = errorCodes.toString()
                    error.descr = errorMyCarenetType.description.value
                    result.errors.add(error)
                }
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

            return result
        } catch (e: ConnectorException) {
            throw IllegalStateException(e)
        } catch (e: UnsupportedEncodingException) {
            throw IllegalStateException(e)
        }

    }
}
