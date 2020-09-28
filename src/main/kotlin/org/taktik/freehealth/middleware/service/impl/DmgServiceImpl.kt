package org.taktik.freehealth.middleware.service.impl

import be.cin.mycarenet.esb.common.v2.*
import be.cin.mycarenet.esb.common.v2.CareProviderType
import be.cin.mycarenet.esb.common.v2.IdType
import be.cin.mycarenet.esb.common.v2.LicenseType
import be.cin.mycarenet.esb.common.v2.NihiiType
import be.cin.mycarenet.esb.common.v2.PackageType
import be.cin.mycarenet.esb.common.v2.ValueRefString
import be.cin.nip.async.generic.Get
import be.cin.nip.async.generic.MsgQuery
import be.cin.nip.async.generic.PostResponse
import be.cin.nip.async.generic.Query
import be.cin.nip.async.generic.TAckResponse
import be.cin.nip.sync.reg.v1.RegistrationStatus
import be.fgov.ehealth.globalmedicalfile.core.v1.*
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendRequestType
import be.fgov.ehealth.messageservices.core.v1.*
import be.fgov.ehealth.messageservices.core.v1.RequestType
import be.fgov.ehealth.messageservices.core.v1.TransactionType
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceResponse
import be.fgov.ehealth.standards.kmehr.cd.v1.*
import be.fgov.ehealth.standards.kmehr.id.v1.*
import be.fgov.ehealth.standards.kmehr.schema.v1.*
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import com.google.gson.Gson
import org.apache.commons.lang.ArrayUtils
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.springframework.security.core.context.SecurityContextHolder
import org.taktik.connector.business.common.domain.Patient
import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.business.dmg.builders.ResponseObjectBuilderFactory
import org.taktik.connector.business.dmg.domain.DMGReferences
import org.taktik.connector.business.dmg.domain.DmgBuilderResponse
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorExceptionValues
import org.taktik.connector.business.domain.common.GenAsyncResponse
import org.taktik.connector.business.domain.dmg.*
import org.taktik.connector.business.genericasync.builders.BuilderFactory
import org.taktik.connector.business.genericasync.service.impl.GenAsyncServiceImpl
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareReceiverId
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import org.taktik.connector.business.mycarenetdomaincommons.util.WsAddressingUtil
import org.taktik.connector.business.registration.helper.ResponseHelper
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.handler.domain.WsAddressingHeader
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.DmgService
import org.taktik.freehealth.middleware.service.STSService
import org.w3._2005._05.xmlmime.Base64Binary
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.net.URI
import java.time.Instant
import java.util.*
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.ws.soap.SOAPFaultException
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

@org.springframework.stereotype.Service
class DmgServiceImpl(private val stsService: STSService) : DmgService {
    private val log = LogFactory.getLog(this.javaClass)
    private val gson = Gson()
    private val genAsyncService = GenAsyncServiceImpl("dmg")
    private val config = ConfigFactory.getConfigValidator(listOf())

    val dmgRegistrationErrors =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/DmgRegistrationErrors.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val dmgConsultationErrors =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/DmgConsultationErrors.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val dmgNotificationErrors =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/DmgNotificationErrors.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val dmgListsConsultationErrors =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/DmgListsConsultationErrors.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val xPathfactory = XPathFactory.newInstance()


    private val replyValidator = EhealthReplyValidatorImpl()

    override fun registerDoctor(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        oa: String,
        bic: String,
        iban: String
                               ): DmgRegistration {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)

        val isTest = config.getProperty("endpoint.mcn.registration").contains("-acpt")

        val request =
            ("<reg:registrations xmlns:p=\"urn:be:cin:mycarenet:esb:common:v2\"\n" +
                "xmlns:reg=\"urn:be:cin:nip:sync:reg:v1\"\n" + "xmlns:other=\"urn:other\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:schemaLocation=\"urn:be:cin:nip:sync:reg:v1Registrations-v1.xsd\"\n" +
                "StartDateTime=\"replaceWithDateYYYY-MM-DD\" >\n" + "<reg:registrant>\n" +
                "<reg:CareProvider>\n" + "<p:Nihii>\n" + "<p:Quality>doctor</p:Quality>\n" +
                "<p:Value>replaceWithNihiiNumber</p:Value>\n" +
                "</p:Nihii>\n" +
                "</reg:CareProvider>\n" +
                "</reg:registrant>\n" +
                "<reg:registration serviceName=\"GMD\" >\n" +
                "<reg:bankAccount bic=\"replaceWithBic\" iban=\"replaceWithIban\"/>\n" +
                "</reg:registration>\n" +
                "</reg:registrations>").replace("replaceWithDateYYYY-MM-DD".toRegex(),
                                                DateTime().toString("yyyy-MM-dd")
                                               )
                .replace("replaceWithNihiiNumber".toRegex(), hcpNihii).replace("replaceWithBic".toRegex(), bic)
                .replace("replaceWithIban".toRegex(), iban.toUpperCase())

        val sysProp =
            java.lang.System.getProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema")

        java.lang.System.setProperty(
            "javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema",
            "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory"
                                    )
        val binaryRequest = request.toByteArray(charset("UTF8"))
        val blob = RequestBuilderFactory.getBlobBuilder("mcn.registration").build(binaryRequest)

        val careReceiver = CareReceiverId(null).apply { mutuality = oa }

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

        val mcRequest = RegisterToMycarenetServiceRequest().apply {
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
                            this.username = principal?.mcnLicense ?: config.getProperty("mycarenet.license.username")
                            this.password = principal?.mcnPassword ?: config.getProperty("mycarenet.license.password")
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
            this.routing = SendRequestMapper.mapRouting(Routing(careReceiver, DateTime()))
            this.detail = SendRequestMapper.mapBlobToBlobType(blob)

            this.xades = BlobUtil.generateXades(credential, this.detail, "mcn.registration")
        }

        val start = System.currentTimeMillis()
        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(
            org.taktik.connector.business.registration.service.ServiceFactory.getRegistrationService(samlToken).apply {
                setPayload(mcRequest)
                setSoapAction("urn:be:fgov:ehealth:mycarenet:registration:protocol:v1:RegisterToMycarenetService")
            })
        val stop = System.currentTimeMillis()

        val intermediateResponse = try {
            xmlResponse.asObject(RegisterToMycarenetServiceResponse::class.java)
        } catch (e: SOAPFaultException) {
            RegisterToMycarenetServiceResponse()
        }

        intermediateResponse.upstreamTiming = (stop - start).toInt()
        intermediateResponse.soapRequest = xmlResponse.request
        intermediateResponse.soapResponse = xmlResponse.soapMessage

        val registrationsAnswer = intermediateResponse?.`return`?.detail?.value?.let { ResponseHelper.toObject(it) }

        return DmgRegistration().apply {
            isSuccess = registrationsAnswer?.registrationAnswer?.status == RegistrationStatus.SUCCESS
            isComplete = true
            if (registrationsAnswer?.registrationAnswer?.status != RegistrationStatus.SUCCESS) {
                errors.addAll(registrationsAnswer?.registrationAnswer?.answerDetails?.flatMap { extractError(binaryRequest, it.detailCode, dmgRegistrationErrors, it.location) } ?: listOf())
            }
            this.mycarenetConversation = MycarenetConversation().apply {
                this.transactionResponse = MarshallerHelper(RegisterToMycarenetServiceResponse::class.java, RegisterToMycarenetServiceResponse::class.java).toXMLByteArray(intermediateResponse).toString(Charsets.UTF_8)
                this.transactionRequest = MarshallerHelper(RegisterToMycarenetServiceRequest::class.java, RegisterToMycarenetServiceRequest::class.java).toXMLByteArray(mcRequest).toString(Charsets.UTF_8)
                intermediateResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                intermediateResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
            }
            this.commonOutput = CommonOutput(
                intermediateResponse?.`return`?.commonOutput?.inputReference,
                intermediateResponse?.`return`?.commonOutput?.nipReference,
                intermediateResponse?.`return`?.commonOutput?.outputReference
                                            )
        }
    }

    fun fillGmdRequest(gmdRequest: SendRequestType,
        isTest: Boolean,
        blob: Blob,
        credential: Credential,
        references: DMGReferences,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientInfo: Patient,
        referenceDate: DateTime,
        generateXades: Boolean) {
        this.checkInputParameters(references.inputReference, patientInfo, referenceDate, blob)
        gmdRequest.apply {
            commonInput = createCommonInputType(isTest, hcpNihii, hcpSsin, references)
            routing = RoutingType().apply {
                careReceiver =
                    CareReceiverIdType().apply {
                        ssin = patientInfo.inss; regNrWithMut =
                        patientInfo.regNrWithMut; mutuality = patientInfo.mutuality
                    }
                this.referenceDate = referenceDate
            }
            detail = BlobType().apply {
                this.value = blob.content
                this.id = blob.id
                this.contentEncoding = blob.contentEncoding
                this.hashValue = blob.hashValue
                this.contentType = blob.contentType
            }
            val xadesValue = if (generateXades && ArrayUtils.isEmpty(ArrayUtils.EMPTY_BYTE_ARRAY)) {
                SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(
                    credential, ConnectorXmlUtils.toByteArray(this), mapOf<kotlin.String?, kotlin.Any?>(
                    "baseURI" to detail.id, "transformerList" to listOf("http://www.w3.org/2000/09/xmldsig#base64")))
            } else {
                ArrayUtils.clone(ArrayUtils.EMPTY_BYTE_ARRAY)
            }
            if (!ArrayUtils.isEmpty(xadesValue)) {
                xadesT = Base64Binary().apply {
                    this.value = xadesValue
                    this.contentType = "text/xml"
                }
            }
        }
    }

    private fun createCommonInputType(isTest: Boolean,
        hcpNihii: String,
        hcpSsin: String,
        references: DMGReferences): CommonInputType {
        return CommonInputType().apply {
            this.request = be.fgov.ehealth.globalmedicalfile.core.v1.RequestType().apply {
                this.isIsTest = isTest
            }
            this.origin = OriginType().apply {
                val principal = SecurityContextHolder.getContext().authentication?.principal as? User

                this.`package` = be.fgov.ehealth.globalmedicalfile.core.v1.PackageType().apply {
                    this.name =
                        be.fgov.ehealth.globalmedicalfile.core.v1.ValueRefString()
                            .apply { this.value = config.getProperty("genericasync.dmg.package.name") }
                    this.license = be.fgov.ehealth.globalmedicalfile.core.v1.LicenseType().apply {
                        this.username = principal?.mcnLicense ?: config.getProperty("mycarenet.license.username")
                        this.password = principal?.mcnPassword ?: config.getProperty("mycarenet.license.password")
                    }
                }
                this.careProvider = be.fgov.ehealth.globalmedicalfile.core.v1.CareProviderType().apply {
                    this.nihii = be.fgov.ehealth.globalmedicalfile.core.v1.NihiiType().apply {
                        this.quality = "doctor"
                        this.value =
                            be.fgov.ehealth.globalmedicalfile.core.v1.ValueRefString()
                                .apply { this.value = hcpNihii }
                    }
                    this.physicalPerson = be.fgov.ehealth.globalmedicalfile.core.v1.IdType().apply {
                        this.ssin =
                            be.fgov.ehealth.globalmedicalfile.core.v1.ValueRefString()
                                .apply { this.value = hcpSsin }
                    }
                }
            }
            this.inputReference = references.inputReference
        }
    }

    fun buildSendConsultRequest(
        isTest: Boolean,
        credential: Credential,
        references: DMGReferences,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientInfo: Patient,
        referenceDate: DateTime,
        request: SelectRetrieveTransaction
                               ): ConsultGlobalMedicalFileRequest {
        val req = RetrieveTransactionRequest().apply {
            this.request = RequestType().apply {
                this.id =
                    IDKMEHR().apply {
                        this.s = IDKMEHRschemes.ID_KMEHR; this.sv = "1.0"; this.value = hcpNihii + "." +
                        references.kmehrIdSuffix
                    }
                this.author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
                this.date = DateTime()
                this.time = DateTime()
            }
            this.select = request
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
            BlobBuilderFactory.getBlobBuilder("dmg").build(xmlByteArray, "none", "_" + references.blobId, "text/xml")
                .apply {
                    messageName = "GMD-CONSULT-HCP"
                }

        return ConsultGlobalMedicalFileRequest().apply {
            fillGmdRequest(
                this,
                isTest,
                blob,
                credential,
                references,
                hcpNihii,
                hcpSsin,
                hcpFirstName,
                hcpLastName,
                patientInfo,
                referenceDate,
                false)
        }
    }

    fun buildSendNotifyRequest(
        isTest: Boolean,
        credential: Credential,
        references: DMGReferences,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientInfo: Patient,
        referenceDate: DateTime,
        kmehrMessage: Kmehrmessage
                              ): NotifyGlobalMedicalFileRequest {
        val req = SendTransactionRequest().apply {
            this.request = RequestType().apply {
                this.id =
                    IDKMEHR().apply {
                        this.s = IDKMEHRschemes.ID_KMEHR; this.sv = "1.0"; this.value = hcpNihii + "." +
                        references.kmehrIdSuffix
                    }
                this.author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
                this.date = DateTime()
                this.time = DateTime()
            }
            this.kmehrmessage = kmehrMessage
        }

        val kmehrRequestMarshaller =
            MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java)
        val xmlByteArray = kmehrRequestMarshaller.toXMLByteArray(req)
        if (xmlByteArray != null && config.getBooleanProperty("be.ehealth.businessconnector.dmg.builders.impl.dumpMessages", false)) {
            log.debug("RequestObjectBuilder : created blob content: " + String(xmlByteArray))
        }
        val blob =
            BlobBuilderFactory.getBlobBuilder("dmg").build(xmlByteArray, "none", "_" + references.blobId, "text/xml")
                .apply {
                    messageName = "GMD-CONSULT-HCP"
                }

        return NotifyGlobalMedicalFileRequest().apply {
            fillGmdRequest(
                this,
                isTest,
                blob,
                credential,
                references,
                hcpNihii,
                hcpSsin,
                hcpFirstName,
                hcpLastName,
                patientInfo,
                referenceDate,
                true
                          )
        }
    }

    override fun notifyDmg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        traineeSupervisorSsin: String?,
        traineeSupervisorNihii: String?,
        traineeSupervisorFirstName: String?,
        traineeSupervisorLastName: String?,
        patientSsin: String?,
        oa: String?,
        regNrWithMut: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String?,
        nomenclature: String,
        requestDate: Date
                          ): DmgNotification {
        val now = DateTime().withMillisOfSecond(0)

        assert(patientSsin != null || oa != null && regNrWithMut != null)
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)

        // DMGReferences ref = DmgTestUtils.createDmgReferenceForTest();
        val ref = DMGReferences(true)

        val pI = org.taktik.connector.business.common.domain.Patient().apply {
            this.inss = patientSsin
            this.mutuality = oa
            this.regNrWithMut = regNrWithMut
        }

        val dateReference = DateTime()
        val istest = config.getProperty("endpoint.dmg.notification.v1").contains("-acpt")
        val author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        val supervisor = traineeSupervisorNihii?.let {nihii -> traineeSupervisorSsin?.let {ssin -> traineeSupervisorFirstName?.let {firstName -> traineeSupervisorLastName?.let {lastName -> makeHcparty(nihii, ssin, firstName, lastName)} } } }
        val kmehrmessage = Kmehrmessage().apply {
            header = HeaderType().apply {
                sender = SenderType().apply { hcparties.addAll(author.hcparties) }
                standard = StandardType().apply {
                    cd = CDSTANDARD().apply { s = "CD-STANDARD"; sv = "1.8"; value = "20131001" }
                }
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                date = now; time = now
                recipients.add(RecipientType().apply {
                    hcparties.add(HcpartyType().apply {
                        name = "mycarenet"
                        cds.add(CDHCPARTY().apply {
                            s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value =
                            "application"
                        })
                    })
                })
            }
            folders.add(FolderType().apply {
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                patient = PersonType().apply {
                    patientSsin?.let {
                        ids.add(IDPATIENT().apply {
                            sv = "1.0"; value = it; s =
                            IDPATIENTschemes.ID_PATIENT
                        })
                    }
                    familyname = patientLastName
                    firstnames.add(patientFirstName)
                    insurancymembership =
                        regNrWithMut?.let {
                            MemberinsuranceType().apply {
                                membership = regNrWithMut; id =
                                oa?.let { IDINSURANCE().apply { sv = "1.0"; value = it } }
                            }
                        }
                    sex =
                        patientGender?.let {
                            SexType().apply {
                                cd =
                                    CDSEX().apply { s = "CD-SEX"; sv = "1.0"; value = CDSEXvalues.fromValue(it) }
                            }
                        }
                }
                transactions.add(be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType().apply {
                    this.author = author
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                    cds.add(CDTRANSACTION().apply {
                        sv = "1.0"; value = "gmd"; s =
                        CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET
                    })
                    date = now; time = now
                    isIscomplete = true; isIsvalidated = true
                    item.add(ItemType().apply {
                        cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "gmdmanager" })
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                        contents.add(ContentType().apply { hcparty = supervisor ?: author.hcparties.first() })
                    })
                    item.add(ItemType().apply {
                        cds.add(CDITEM().apply {
                            s = CDITEMschemes.CD_ITEM; sv = "1.0"; value =
                            "encounterdatetime"
                        })
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "2"; sv = "1.0" })
                        contents.add(ContentType().apply { date = DateTime(requestDate.time) })
                    })
                    item.add(ItemType().apply {
                        cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "claim" })
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "3"; sv = "1.0" })
                        contents.add(ContentType().apply {
                            cds.add(CDCONTENT().apply {
                                s = CDCONTENTschemes.CD_NIHDI; sv = "1.0"; value = nomenclature
                            })
                        })
                    })
                })
            })
        }
        val gmdRequest =
            buildSendNotifyRequest(
                istest,
                credential,
                ref,
                hcpNihii,
                hcpSsin,
                hcpFirstName,
                hcpLastName,
                pI,
                dateReference,
                kmehrmessage
                                  )

        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(GenericRequest().apply {
            setEndpoint(
                config.getProperty(
                    "endpoint.dmg.notification.v1",
                    "\$uddi{uddi:ehealth-fgov-be:business:globalmedicalfilenotification:v1}"
                                  )
                       )
            setCredential(samlToken, TokenType.SAML)
            addDefaulHandlerChain()
            addHandlerChain(
                HandlerChainUtil.buildChainWithValidator(
                    "validation.incoming.message.dmg.notification.v1",
                    "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd"
                                                        )
                           )
            setPayload(gmdRequest)
            setSoapAction("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1:NotifyGlobalMedicalFile")
        })

        val intermediateResponse = xmlResponse.asObject(NotifyGlobalMedicalFileResponse::class.java).apply {
            replyValidator.validateReplyStatus(this)
        }

        intermediateResponse.soapRequest = xmlResponse.request
        intermediateResponse.soapResponse = xmlResponse.soapMessage

        val response = ResponseObjectBuilderFactory.getResponseObjectBuilder()
            .handleSendResponseType(intermediateResponse)

        if (response.ehealthStatus != "200") {
            throw RuntimeException("Wrong status code" + response.ehealthStatus)
        }

        val dmg = DmgNotification(response.sendTransactionResponse.acknowledge.isIscomplete).apply {
            this.errors.addAll(response.sendTransactionResponse.acknowledge.errors?.filterNotNull()?.flatMap { et ->
                et.cds.firstOrNull()?.let { cd ->
                    this@DmgServiceImpl.extractError(
                        gmdRequest.detail.value,
                        cd.value,
                        dmgNotificationErrors,
                        et.url
                                                    )
                } ?: setOf()
            } ?: listOf())
            response.sendTransactionResponse.kmehrmessage?.let {
                it.folders.forEach {
                    it.transactions.find { it.cds.any { it.value.toLowerCase() == "gmd" } }?.let {
                        it.item.forEach {
                            if (it.cds.any { it.value.toLowerCase() == "gmdmanager" }) {
                                from = it.beginmoment?.date?.toInstant()?.millis?.let { Instant.ofEpochMilli(it) }
                                hcParty = it.contents.map { it.hcparty }.filterNotNull().first()
                            }
                            if (it.cds.any { it.value.toLowerCase() == "payment" }) {
                                payment = it.contents.map { it.isBoolean }.filterNotNull().first()
                            }
                        }
                    }
                }
            }

            this.mycarenetConversation = MycarenetConversation().apply {
                this.transactionResponse = MarshallerHelper(NotifyGlobalMedicalFileResponse::class.java, NotifyGlobalMedicalFileResponse::class.java).toXMLByteArray(intermediateResponse).toString(Charsets.UTF_8)
                this.transactionRequest = MarshallerHelper(NotifyGlobalMedicalFileRequest::class.java, NotifyGlobalMedicalFileRequest::class.java).toXMLByteArray(gmdRequest).toString(Charsets.UTF_8)
                intermediateResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                intermediateResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
            }
            this.commonOutput = CommonOutput(
                intermediateResponse?.`return`?.commonOutput?.inputReference,
                intermediateResponse?.`return`?.commonOutput?.nipReference,
                intermediateResponse?.`return`?.commonOutput?.outputReference
                                            )
        }

        return dmg
    }

    override fun consultDmg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String?,
        patientGender: String?,
        oa: String?,
        regNrWithMut: String?,
        requestDate: Date
                           ): DmgConsultation {
        var now = DateTime()
        assert(patientSsin != null || oa != null && regNrWithMut != null)
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for DMG operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)

        val ref = DMGReferences(true)

        val pI = Patient().apply {
            this.inss = patientSsin
            this.mutuality = oa
            this.regNrWithMut = regNrWithMut
        }

        val dateReference = DateTime()
        val istest = config.getProperty("endpoint.dmg.consultation.v1").contains("-acpt")
        val request = SelectRetrieveTransaction().apply {
            patient = PatientType().apply {
                patientSsin?.let {
                    ids.add(IDPATIENT().apply {
                        sv = "1.0"; value = it; s =
                        IDPATIENTschemes.ID_PATIENT
                    })
                }
                insurancymembership =
                    regNrWithMut?.let {
                        MemberinsuranceType().apply {
                            membership = regNrWithMut; id =
                            oa?.let { IDINSURANCE().apply { sv = "1.0"; value = it } }
                        }
                    }
                sex =
                    patientGender?.let {
                        SexType().apply {
                            cd =
                                CDSEX().apply { s = "CD-SEX"; sv = "1.0"; value = CDSEXvalues.fromValue(it) }
                        }
                    }
            }
            transaction = TransactionType().apply {
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                cds.add(CDTRANSACTION().apply {
                    sv = "1.0"; value = "gmd"; s =
                    CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET
                })
                begindate = DateTime(requestDate.time)
            }
        }

        val consultRequest =
            this.buildSendConsultRequest(
                istest,
                credential,
                ref,
                hcpNihii,
                hcpSsin,
                hcpFirstName,
                hcpLastName,
                pI,
                dateReference,
                request
                                        )

        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(GenericRequest().apply {
            setEndpoint(
                config.getProperty(
                    "endpoint.dmg.consultation.v1",
                    "\$uddi{uddi:ehealth-fgov-be:business:globalmedicalfileconsultation:v1}"
                                  )
                       )
            setCredential(samlToken, TokenType.SAML)
            addDefaulHandlerChain()
            addHandlerChain(
                HandlerChainUtil.buildChainWithValidator(
                    "validation.incoming.message.dmg.consultation.v1",
                    "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd"
                                                        )
                           )
            setPayload(consultRequest)
            setSoapAction("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1:ConsultGlobalMedicalFile")

        });

        return try {
            val response = ResponseObjectBuilderFactory.getResponseObjectBuilder()
                .handleSendResponseType(xmlResponse.asObject(
                    NotifyGlobalMedicalFileResponse::class.java).apply {
                    replyValidator.validateReplyStatus(this)
                })


            DmgConsultation(response.sendTransactionResponse.acknowledge.isIscomplete).apply {
                this.errors.addAll(response.sendTransactionResponse.acknowledge.errors?.filterNotNull()?.flatMap { et ->
                    et.cds.firstOrNull()?.let { cd ->
                        this@DmgServiceImpl.extractError(
                            consultRequest.detail.value,
                            cd.value,
                            dmgConsultationErrors,
                            et.url
                                                        )
                    } ?: setOf()
                } ?: listOf())
                response.sendTransactionResponse.kmehrmessage?.let {
                    it.folders.firstOrNull()?.let {
                        it.patient?.let {
                            lastName = it.familyname
                            firstName = it.firstnames.joinToString(" ")
                            it.sex?.let { sex = it.cd.value.value() }
                            it.birthdate?.let { birthday = Instant.ofEpochMilli(it.date.millis) }
                            it.ids.find { it.s == IDPATIENTschemes.ID_PATIENT || it.s == IDPATIENTschemes.INSS }
                                ?.let { inss = it.value }
                            it.insurancymembership?.let {
                                mutuality = it.id.value; it.membership?.let {
                                this.regNrWithMut = (it as? Node)?.textContent
                            }
                            }
                        }
                        it.transactions.find { it.cds.any { it.value.toLowerCase() == "gmd" } }?.let {
                            it.item.forEach {
                                if (it.cds.any { it.value.toLowerCase() == "gmdmanager" }) {
                                    it.beginmoment?.date?.let { from = Instant.ofEpochMilli(it.millis) }
                                    it.endmoment?.date?.let { to = Instant.ofEpochMilli(it.millis) }
                                    hcParty = it.contents.map { it.hcparty }.filterNotNull().first()
                                }
                                if (it.cds.any { it.value.toLowerCase() == "payment" }) {
                                    payment = it.contents.map { it.isBoolean }.filterNotNull().first()
                                }
                            }
                        }
                    }
                }
                this.commonOutput = CommonOutput(
                    response?.originalResponse?.`return`?.commonOutput?.inputReference,
                    response?.originalResponse?.`return`?.commonOutput?.nipReference,
                    response?.originalResponse?.`return`?.commonOutput?.outputReference
                                                )
                this.mycarenetConversation = MycarenetConversation().apply {
                    this.transactionResponse = MarshallerHelper(DmgBuilderResponse::class.java, DmgBuilderResponse::class.java).toXMLByteArray(response).toString(Charsets.UTF_8)
                    this.transactionRequest = MarshallerHelper(ConsultGlobalMedicalFileRequest::class.java, ConsultGlobalMedicalFileRequest::class.java).toXMLByteArray(consultRequest).toString(Charsets.UTF_8)
                    xmlResponse?.soapMessage?.writeTo(this.soapResponseOutputStream())
                    xmlResponse?.request?.writeTo(this.soapRequestOutputStream())
                }
            }
        } catch (e:SOAPFaultException) {
            DmgConsultation(false).apply {
                this.errors.add(MycarenetError(code = e.fault.faultCode, msgFr =  e.fault.faultString, msgNl = e.fault.faultString))
                this.mycarenetConversation = MycarenetConversation().apply {
                    this.transactionRequest = MarshallerHelper(ConsultGlobalMedicalFileRequest::class.java, ConsultGlobalMedicalFileRequest::class.java).toXMLByteArray(consultRequest).toString(Charsets.UTF_8)
                    xmlResponse?.soapMessage?.writeTo(this.soapResponseOutputStream())
                    xmlResponse?.request?.writeTo(this.soapRequestOutputStream())
                }
            }
        }

    }

    override fun confirmDmgMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        dmgMessagesHashes: List<String>
                                   ): Boolean {
        if (dmgMessagesHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("dmg")
                .buildConfirmRequestWithHashes(
                    buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                    dmgMessagesHashes.map { valueHash -> Base64.getDecoder().decode(valueHash) },
                    listOf())

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

    override fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        dmgAcksHashes: List<String>
                            ): Boolean {
        if (dmgAcksHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("dmg")
                .buildConfirmRequestWithHashes(buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                                               listOf(),
                                               dmgAcksHashes.map { valueHash -> Base64.getDecoder().decode(valueHash) })

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

    override fun getDmgMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        messageNames: List<String>?
                               ): DmgsList {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        val getHeader = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:get:query")).apply {
            messageID = URI(IdGeneratorFactory.getIdGenerator("uuid").generateId())
        }

        val get = Get().apply {
            msgQuery = MsgQuery().apply {
                isInclude = true
                max = 100
                messageNames?.let { this.messageNames.addAll(it) }
            }
            tAckQuery = Query().apply {
                isInclude = true
                max = 100
            }
            origin = buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        }
        val response = genAsyncService.getRequest(samlToken, get, getHeader)

        val b64 = Base64.getEncoder()
        return DmgsList().apply {
            mycarenetConversation = MycarenetConversation().apply {
                this.transactionRequest = org.taktik.connector.technical.utils.MarshallerHelper(be.cin.nip.async.generic.Get::class.java, be.cin.nip.async.generic.Get::class.java).toXMLByteArray(get).toString(kotlin.text.Charsets.UTF_8)
                this.transactionResponse = org.taktik.connector.technical.utils.MarshallerHelper(be.cin.nip.async.generic.GetResponse::class.java, be.cin.nip.async.generic.GetResponse::class.java).toXMLByteArray(response).toString(kotlin.text.Charsets.UTF_8)
                response?.soapResponse?.writeTo(this.soapResponseOutputStream())
                response?.soapRequest?.writeTo(this.soapRequestOutputStream())
            }
            acks = response.`return`.tAckResponses?.map {
                DmgAcknowledge(it.tAck.resultMajor, it.tAck.resultMinor, it.tAck.resultMessage).apply {
                    io = it.tAck.issuer.replace("urn:nip:issuer:io:".toRegex(), "")
                    reference = it.tAck.reference
                    appliesTo = it.tAck.appliesTo
                    valueHash = b64.encodeToString(it.tAck.value)
                }
            } ?: listOf()
            messages = response.`return`.msgResponses?.map { r ->
                val nipReference = r.commonOutput.nipReference
                val inputReference = r.commonOutput.inputReference
                val outputReference = r.commonOutput.outputReference

                val encodedHashValue = b64.encodeToString(r.detail.hashValue)

                ResponseObjectBuilderFactory.getResponseObjectBuilder().handleAsyncResponse(r)?.let { dec ->
                    dec.retrieveTransactionResponse?.let { retrieveTransactionResponse ->
                        createDmgsList(retrieveTransactionResponse, nipReference, inputReference, outputReference, encodedHashValue)
                    } ?: dec.kmehrmessage?.let {
                        createClosureOrExtension(it, nipReference, encodedHashValue)
                    }
                }
            } ?: listOf()
        }
    }

    protected fun createClosureOrExtension(it: Kmehrmessage, nipReference: String?, encodedHashValue: String?): DmgMessageWithPatient? {
        val io =
            it.header?.sender?.hcparties?.firstOrNull()
                ?.ids?.find { it.s == IDHCPARTYschemes.ID_INSURANCE }?.value
        return it.folders.firstOrNull()?.let { f ->
            f.transactions?.firstOrNull()?.let { t ->
                when {
                    t.cds.any { cd -> cd.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && cd.value == "gmdextension" } ->
                        DmgExtension().apply {
                            this.io = io
                            f.patient?.let { fillDmgMessage(this, it) }
                            reference = nipReference
                            valueHash = encodedHashValue
                            t.item.find { it.cds.any { it.value.toLowerCase() == "gmdmanager" } }?.let {
                                hcParty = it.contents.map { it.hcparty }.filterNotNull().first()
                            }
                            t.item.find { it.cds.any { it.value.toLowerCase() == "encounterdatetime" } }?.let {
                                it.contents.find { it.date != null }?.let { encounterDate = it.date.toDate() }
                            }
                            t.item.find { it.cds.any { it.value.toLowerCase() == "claim" } }?.let {
                                it.contents.forEach {
                                    it.cds?.find { it.s == CDCONTENTschemes.CD_NIHDI }
                                        ?.let { claim = it.value }
                                }
                            }
                        }
                    t.cds.any { cd -> cd.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && cd.value == "gmdclosure" } ->
                        DmgClosure().apply {
                            this.io = io
                            f.patient?.let { fillDmgMessage(this, it) }
                            reference = nipReference
                            valueHash = encodedHashValue
                            t.item.filter { it.cds.any { it.value.toLowerCase() == "gmdmanager" } }.forEach {
                                it.contents.map { it.hcparty }.firstOrNull()?.let { hcp ->
                                    it.beginmoment?.date?.let { beginOfNewDmg = it.toDate(); newHcParty = hcp }
                                    it.endmoment?.date?.let {
                                        endOfPreviousDmg = it.toDate(); previousHcParty =
                                        hcp
                                    }
                                    null
                                }
                            }
                        }
                    else -> null
                }
            }
        }
    }

    protected fun createDmgsList(retrieveTransactionResponse: RetrieveTransactionResponse, nipReference: String?, inputReference: String?, outputReference: String?, encodedHashValue: String?): DmgsList {
        return DmgsList().apply {
            io =
                retrieveTransactionResponse.response?.author?.hcparties?.find { it.ids.isNotEmpty() && it.cds.any { it.s == CDHCPARTYschemes.CD_HCPARTY && it.value == "orginsurance" } }
                    ?.ids?.firstOrNull()?.value
            reference = nipReference
            valueHash = encodedHashValue

            appliesTo = inputReference
            commonOutput = CommonOutput().apply {
                this.nipReference = nipReference
                this.inputReference = inputReference
                this.outputReference = outputReference
            }


            retrieveTransactionResponse.acknowledge?.errors?.let {
                errors.addAll(listOf() /* TODO */)
            }
            if (retrieveTransactionResponse.acknowledge?.isIscomplete == true) {
                retrieveTransactionResponse.kmehrmessage?.let { km ->
                    date = km.header.date.toDate()
                    inscriptions.addAll(km.folders?.map {
                        DmgInscription().apply {
                            it.patient?.let { fillDmgMessage(this, it) }
                            it.transactions.find { it.cds.any { it.value.toLowerCase() == "gmd" } }?.let {
                                it.item.find { it.cds.any { it.value.toLowerCase() == "gmdmanager" } }
                                    ?.let {
                                        it.beginmoment?.date?.let { from = it.toDate() }
                                        it.endmoment?.date?.let { to = it.toDate() }
                                        hcParty = it.contents.map { it.hcparty }.filterNotNull().first()
                                    }
                                it.item.filter { it.cds.any { it.value.toLowerCase() == "payment" } }
                                    .forEachIndexed { idx, i ->
                                        i.cost?.decimal?.let { setPaymentAmount(idx + 1, it.toDouble()) }
                                        i.cost?.unit?.let { setPaymentCurrency(idx + 1, it.cd?.value) }
                                        i.beginmoment?.let { setPaymentDate(idx + 1, it.date?.toDate()) }
                                    }
                            }
                        }
                    } ?: listOf())
                }
            }
        }
    }

    override fun postDmgsListRequest(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        oa: String?,
        requestDate: Date
                                    ): GenAsyncResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        val istest = config.getProperty("endpoint.dmg.notification.v1").contains("-acpt")
        val author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        val inputReference =
            IdGeneratorFactory.getIdGenerator().generateId()//.let { if (istest) "T" + it.substring(1) else it }
        val now = DateTime().withMillisOfSecond(0)

        val postHeader = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:post:msg")).apply {
            to = URI(if (oa != null) "urn:nip:destination:io:$oa" else "")
            faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
            replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
            messageID = URI("uuid:" + UUID.randomUUID())
        }

        val retrieveTransactionRequest = RetrieveTransactionRequest().apply {
            request = RequestType().apply {
                id = IDKMEHR().apply {
                    this.s = IDKMEHRschemes.ID_KMEHR; this.sv = "1.0"; this.value = "$hcpNihii.$inputReference"
                }
                this.author = author
                date = now
                time = now
            }

            select = SelectRetrieveTransactionType().apply {
                transaction = TransactionType().apply {
                    this.author = author
                    begindate = DateTime(requestDate)
                    cds.add(CDTRANSACTION().apply {
                        s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv = "1.0"; value = "gmd"
                    })
                }
            }
        }

        val blob = RequestBuilderFactory.getBlobBuilder("genericasync").build(
            ConnectorXmlUtils.toByteArray(retrieveTransactionRequest),
            "deflate",
            "_" + UUID.randomUUID().toString(),
            "text/xml"
                                                                             ).apply {
            messageName = "GMD-CONSULT-HCP"
        }

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = istest!!
            }
            origin = buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
            this.inputReference = inputReference
        }
        // no xades needed for dmg async
        val post = BuilderFactory.getRequestObjectBuilder("dmg")
                .buildPostRequest(ci, SendRequestMapper.mapBlobToCinBlob(blob), null)
        val postResponse = genAsyncService.postRequest(samlToken, post, postHeader)
        return GenAsyncResponse().apply {
            result = postResponse.`return`.resultMajor == "urn:nip:tack:result:major:success"
            this.tack = postResponse.`return`
            mycarenetConversation = MycarenetConversation().apply {
                this.transactionRequest = MarshallerHelper(be.cin.nip.async.generic.Post::class.java, be.cin.nip.async.generic.Post::class.java).toXMLByteArray(post).toString(Charsets.UTF_8)
                this.transactionResponse = MarshallerHelper(PostResponse::class.java, PostResponse::class.java).toXMLByteArray(postResponse).toString(Charsets.UTF_8)
                postResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                postResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
            }
        }
    }

    fun fillDmgMessage(msg: DmgMessageWithPatient, patient: PersonType) {
        msg.apply {
            patient?.let {
                lastName = it.familyname
                firstName = it.firstnames.joinToString(" ")
                it.sex?.let { sex = it.cd.value.value() }
                it.birthdate?.let { birthday = it.date.toDate() }
                it.ids.find { it.s == IDPATIENTschemes.ID_PATIENT || it.s == IDPATIENTschemes.INSS }
                    ?.let { inss = it.value }
                it.insurancymembership?.let {
                    mutuality = it.id.value; it.membership?.let {
                    this.regNrWithMut =
                        it.toString()
                }
                }
            }
        }
    }

    private fun makeAuthor(hcpNihii: String, hcpSsin: String, hcpFirstName: String, hcpLastName: String): AuthorType {
        return AuthorType().apply {
            hcparties.add(makeHcparty(hcpNihii, hcpSsin, hcpFirstName, hcpLastName))
        }
    }

    private fun makeHcparty(hcpNihii: String, hcpSsin: String, hcpFirstName: String, hcpLastName: String): HcpartyType {
        return HcpartyType().apply {
            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = "persphysician" })
            firstname = hcpFirstName
            familyname = hcpLastName
        }
    }

    private fun checkInputParameters(referenceId: String, patientInfo: Patient, referenceDate: DateTime, blob: Blob?) {
        this.checkParameterNotNull(referenceId, "DmgReferences")
        if (blob?.content?.isNotEmpty() == true) {
            this.checkStringParameterNotNullOrEmpty(blob.contentType, "Blob contentType")
            this.checkStringParameterNotNullOrEmpty(blob.id, "Blob id")
            this.checkParameterNotNull(referenceDate, "Reference date")
            this.checkParameterNotNull(patientInfo, "Patient info")
            if (patientInfo.inss == null || patientInfo.inss.isEmpty()) {
                if (patientInfo.mutuality == null || patientInfo.mutuality.isEmpty()) {
                    throw DmgBusinessConnectorException(
                        DmgBusinessConnectorExceptionValues.PARAMETER_NULL,
                        "Ssin and mutuality (No valid patient information)"
                                                       )
                }

                if (patientInfo.regNrWithMut == null || patientInfo.regNrWithMut.isEmpty()) {
                    throw DmgBusinessConnectorException(
                        DmgBusinessConnectorExceptionValues.PARAMETER_NULL,
                        "Ssin and registration number (No valid patient information)"
                                                       )
                }
            }
        } else {
            throw DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, "Blob Content")
        }
    }

    private fun checkStringParameterNotNullOrEmpty(contentType: String?, parameterName: String) {
        if (contentType == null || contentType.isEmpty()) {
            throw DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, parameterName)
        }
    }

    private fun checkParameterNotNull(references: Any?, parameterName: String) {
        if (references == null) {
            throw DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, parameterName)
        }
    }

    private fun buildOriginType(nihii: String, ssin: String, firstname: String, lastname: String): OrigineType =
        OrigineType().apply {
            `package` = PackageType().apply {
                name = ValueRefString().apply { value = config.getProperty("genericasync.dmg.package.name") }
                license = LicenseType().apply {
                    username = config.getProperty("mycarenet.license.username")
                    password = config.getProperty("mycarenet.license.password")
                }
            }
            careProvider = CareProviderType().apply {
                this.nihii = NihiiType().apply {
                    quality = "doctor"
                    value = ValueRefString().apply { value = nihii }
                }
                physicalPerson = IdType().apply {
                    this.ssin = ValueRefString().apply { value = ssin }
                }
            }
        }

    private fun extractError(kmehrRequest: ByteArray, ec: String, errors: Map<String, MycarenetError>, errorUrl: String?): Set<MycarenetError> {
        val url = errorUrl?.let { if (it.isNotEmpty()) it else null }
        var textContent: String? = null
        val result = mutableSetOf<MycarenetError>()
        val base = url?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()

            factory.isNamespaceAware = true
            val builder = factory.newDocumentBuilder()

            val xpath = xPathfactory.newXPath()
            val expr: XPathExpression? = try {
                xpath.compile(if (url.startsWith("/")) url else "/" + url)
            } catch (e: XPathExpressionException) {
                log.warn("Invalid XPATH returned: `$url‘", e); null
            }

            (expr?.evaluate(
                builder.parse(ByteArrayInputStream(kmehrRequest)),
                XPathConstants.NODESET
                           ) as NodeList?)?.let { it ->
                if (it.length > 0) {
                    var node = it.item(0)
                    textContent = node.textContent
                    var base = "/" + nodeDescr(node)
                    while (node.parentNode != null && node.parentNode is Element) {
                        base = "/${nodeDescr(node.parentNode)}$base"
                        node = node.parentNode
                    }
                    base
                } else {
                    result.add(
                        MycarenetError(
                            code = ec,
                            path = url,
                            msgFr = "Erreur générique, xpath invalide",
                            msgNl = "Onbekend foutmelding, xpath ongeldig"
                                      )
                              )
                    null
                }
            }
        }

        var elements = errors.values.filter { (base == null || it.path == base) && it.code == ec && (it.regex == null || (url?.matches(Regex(".*" + it.regex + ".*")) ?: true)) }
        if (base != null && elements.isEmpty()) {
            //IOs sometimes are overeager to provide us with precise xpath. Let's try again while truncating after the item
            val trimmedBase = base.replace(Regex("(.+/item.+?)/.*"), "$1")
            elements = errors.values.filter {
                it.path == trimmedBase && it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*")))
            }
        }
        if (elements.isEmpty()) {
            elements = errors.values.filter { it.code == ec }
        }
        elements.forEach { it.value = textContent }
        result.addAll(elements)

        return result
    }


    private fun nodeDescr(node: Node): String {
        val localName = node.localName ?: node.nodeName?.replace(Regex(".+?:(.+)"), "$1") ?: "unknown"

        val xpath = xPathfactory.newXPath()
        xpath.namespaceContext = object : NamespaceContext {
            override fun getNamespaceURI(prefix: String?) = when (prefix) {
                "ns3" -> "http://www.ehealth.fgov.be/standards/kmehr/schema/v1"
                else -> null
            }

            override fun getPrefix(namespaceURI: String?) = when (namespaceURI) {
                "http://www.ehealth.fgov.be/standards/kmehr/schema/v1" -> "ns3"
                else -> null
            }

            override fun getPrefixes(namespaceURI: String?): Iterator<Any?> =
                when (namespaceURI) {
                    "http://www.ehealth.fgov.be/standards/kmehr/schema/v1" -> listOf("ns3").iterator()
                    else -> listOf<String>().iterator()
                }
        }
        if (localName == "item") {
            return "item[${xpath.evaluate("ns3:cd[@S=\"CD-ITEM\"]", node)}]"
        }
        return localName
    }

}
