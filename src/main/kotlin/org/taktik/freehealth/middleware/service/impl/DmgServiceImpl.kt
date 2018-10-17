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
import be.cin.nip.async.generic.Query
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
import com.google.gson.reflect.TypeToken
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
import org.taktik.freehealth.middleware.domain.common.BusinessError
import org.taktik.freehealth.middleware.service.DmgService
import org.taktik.freehealth.middleware.service.STSService
import org.w3._2005._05.xmlmime.Base64Binary
import java.net.URI
import java.time.Instant
import java.util.*

@org.springframework.stereotype.Service
class DmgServiceImpl(private val stsService: STSService) : DmgService {
    private val log = LogFactory.getLog(this.javaClass)
    private val gson = Gson()
    private val genAsyncService = GenAsyncServiceImpl("dmg")
    private val config = ConfigFactory.getConfigValidator(listOf())

    private val errorMessages = try {
        this.javaClass.getResourceAsStream("errors.json")
            ?.let {
                gson.fromJson<List<BusinessError>>(it.reader(), object : TypeToken<ArrayList<BusinessError>>() {}.type)
            }
    } catch (_: Exception) {
        null
    } ?: listOf()

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
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)

        val isTest = config.getProperty("endpoint.mcn.registration").contains("-acpt")

        //val mapper = RegistrationRequestBuilderFactory.getRequestObjectBuilder()

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
                DateTime().toString("YYYY-MM-dd")
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    )
                .replace("replaceWithNihiiNumber".toRegex(), hcpNihii).replace("replaceWithBic".toRegex(), bic)
                .replace("replaceWithIban".toRegex(), iban.toUpperCase())

        val sysProp =
            java.lang.System.getProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema")

        java.lang.System.setProperty(
            "javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema",
            "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory"
                                    )
        val blob = RequestBuilderFactory.getBlobBuilder("mcn.registration").build(request.toByteArray(charset("UTF8")))

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

            this.xades = BlobUtil.generateXades(this.detail, credential, "mcn.registration")
        }
        val registrationsAnswer =
            ResponseHelper.toObject(
                org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(
                    org.taktik.connector.business.registration.service.ServiceFactory.getRegistrationService(samlToken).apply {
                        setPayload(mcRequest)
                        setSoapAction("urn:be:fgov:ehealth:mycarenet:registration:protocol:v1:RegisterToMycarenetService")
                    }).asObject(RegisterToMycarenetServiceResponse::class.java).`return`.detail.value)

        return DmgRegistration().apply {
            isSuccess = registrationsAnswer.registrationAnswer.status == RegistrationStatus.SUCCESS
            isComplete = true
            if (registrationsAnswer.registrationAnswer.status != RegistrationStatus.SUCCESS) {
                errors.addAll(registrationsAnswer.registrationAnswer.answerDetails.map { cd ->
                    org.taktik.connector.business.domain.Error(
                        cd.detailCode,
                        cd.location,
                        cd.detailSource,
                        errorMessages.find { it.code == cd.detailCode && it.context == "CINNIC" }?.message
                                                              )
                })
            }
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
                       referenceDate: DateTime) {
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
            val xadesValue = if (ArrayUtils.isEmpty(ArrayUtils.EMPTY_BYTE_ARRAY) && false) {
                SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(
                    credential, ConnectorXmlUtils.toByteArray(this), mapOf<kotlin.String?, kotlin.Any?>(
                    "baseURI" to detail.id, "tranformerList" to listOf("http://www.w3.org/2000/09/xmldsig#base64")))
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
                referenceDate)
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
            this.kmehrmessage = kmehrmessage
        }

        val kmehrRequestMarshaller =
            MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java)
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
                referenceDate
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
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)

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
                            s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.0"; value =
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
                                    CDSEX().apply { sv = "1.0"; value = CDSEXvalues.fromValue(it) }
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
                        ItemType().apply {
                            cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "gmdmanager" })
                            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                            contents.add(ContentType().apply { hcparty = author.hcparties.first() })
                        }
                    })
                    item.add(ItemType().apply {
                        ItemType().apply {
                            cds.add(CDITEM().apply {
                                s = CDITEMschemes.CD_ITEM; sv = "1.0"; value =
                                "encounterdatetime"
                            })
                            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "2"; sv = "1.0" })
                            contents.add(ContentType().apply { date = DateTime(requestDate.time) })
                        }
                    })
                    item.add(ItemType().apply {
                        ItemType().apply {
                            cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.0"; value = "claim" })
                            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "3"; sv = "1.0" })
                            contents.add(ContentType().apply {
                                cds.add(CDCONTENT().apply {
                                    s =
                                        CDCONTENTschemes.CD_NIHDI; sv = "1.0"; value = nomenclature
                                })
                            })
                        }
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

        val response =
            ResponseObjectBuilderFactory.getResponseObjectBuilder()
                .handleSendResponseType(
                    org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(GenericRequest().apply {
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
                    }).asObject(NotifyGlobalMedicalFileResponse::class.java).apply {
                        replyValidator.validateReplyStatus(
                            this
                                                          )
                    })

        if (response.ehealthStatus != "200") {
            throw RuntimeException("Wrong status code" + response.ehealthStatus)
        }

        val dmg = DmgNotification(response.sendTransactionResponse.acknowledge.isIscomplete).apply {
            this.errors.addAll(response.sendTransactionResponse.acknowledge.errors?.filterNotNull()?.flatMap { et ->
                et.cds.map { cd ->
                    org.taktik.connector.business.domain.Error(
                        cd.value,
                        et.url,
                        et.description?.value,
                        errorMessages.find { it.code == cd.value && it.context == "CINNIC" && it.subcontext == "DMG-NOTIF" }?.message
                                                              )
                }
            } ?: listOf())
            response.sendTransactionResponse.kmehrmessage?.let {
                it.folders.forEach {
                    it.transactions.find { it.cds.any { it.value.toLowerCase() == "gmd" } }?.let {
                        it.item.forEach {
                            if (it.cds.any { it.value.toLowerCase() == "gmdmanager" }) {
                                from = it.beginmoment?.date?.toDate()
                                hcParty = it.contents.map { it.hcparty }.filterNotNull().first()
                            }
                            if (it.cds.any { it.value.toLowerCase() == "payment" }) {
                                payment = it.contents.map { it.isBoolean }.filterNotNull().first()
                            }
                        }
                    }
                }
            }
        }

//        val requestMarshaller =
//            MarshallerHelper(SelectRetrieveTransaction::class.java, SelectRetrieveTransaction::class.java)
//        val requestXmlByteArray = requestMarshaller.toXMLByteArray(request);
//        dmg.requestXML = requestXmlByteArray.toString(Charsets.UTF_8);

        val consultRequestMarshaller =
            MarshallerHelper(NotifyGlobalMedicalFileRequest::class.java, NotifyGlobalMedicalFileRequest::class.java)
        val consultRequestXmlByteArray = consultRequestMarshaller.toXMLByteArray(gmdRequest);
        dmg.gmdRequestXML = consultRequestXmlByteArray.toString(Charsets.UTF_8);

        val responseMarshaller =
            MarshallerHelper(DmgBuilderResponse::class.java, DmgBuilderResponse::class.java)
        val responseXmlByteArray = responseMarshaller.toXMLByteArray(response);
        dmg.responseXML = responseXmlByteArray.toString(Charsets.UTF_8);

        return dmg;
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
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)

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
                                CDSEX().apply { sv = "1.0"; value = CDSEXvalues.fromValue(it) }
                        }
                    }
            }
            transaction = TransactionType().apply {
                this.author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1"; sv = "1.0" })
                cds.add(CDTRANSACTION().apply {
                    sv = "1.0"; value = "gmd"; s =
                    CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET
                })
                begindate = DateTime(requestDate.time)
                isIscomplete = true; isIsvalidated = true
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

        val response =
            ResponseObjectBuilderFactory.getResponseObjectBuilder()
                .handleSendResponseType(
                    org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(GenericRequest().apply {
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
                    }).asObject(NotifyGlobalMedicalFileResponse::class.java).apply {
                        replyValidator.validateReplyStatus(
                            this
                        )
                    })

        if (!response.ehealthStatus.equals("200")) {
            throw RuntimeException("Wrong status code" + response.ehealthStatus)
        }


        val dmg = DmgConsultation(response.sendTransactionResponse.acknowledge.isIscomplete).apply {
            this.errors.addAll(response.sendTransactionResponse.acknowledge.errors?.filterNotNull()?.flatMap { et ->
                et.cds.map { cd ->
                    org.taktik.connector.business.domain.Error(
                        cd.value,
                        et.url,
                        et.description?.value,
                        errorMessages.find { it.code == cd.value && it.context == "CINNIC" && it.subcontext == "DMG-NOTIF" }?.message
                    )
                }
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
                            this.regNrWithMut =
                                it.toString()
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
        }

        val requestMarshaller =
            MarshallerHelper(SelectRetrieveTransaction::class.java, SelectRetrieveTransaction::class.java)
        val requestXmlByteArray = requestMarshaller.toXMLByteArray(request);
        dmg.requestXML = requestXmlByteArray.toString(Charsets.UTF_8);

        val consultRequestMarshaller =
            MarshallerHelper(ConsultGlobalMedicalFileRequest::class.java, ConsultGlobalMedicalFileRequest::class.java)
        val consultRequestXmlByteArray = consultRequestMarshaller.toXMLByteArray(consultRequest);
        dmg.consultRequestXML = consultRequestXmlByteArray.toString(Charsets.UTF_8);

        val responseMarshaller =
            MarshallerHelper(DmgBuilderResponse::class.java, DmgBuilderResponse::class.java)
        val responseXmlByteArray = responseMarshaller.toXMLByteArray(response);
        dmg.responseXML = responseXmlByteArray.toString(Charsets.UTF_8);

        return dmg;
    }

    override fun confirmDmgMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        dmgMessages: List<DmgMessage>
                                   ): Boolean {
        if (dmgMessages.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("dmg")
                .buildConfirmRequestWithHashes(
                    buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                    dmgMessages.map { dmgMessage ->
                        Base64.getDecoder()
                            .decode(dmgMessage.valueHash)
                    },
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
        dmgTacks: List<DmgAcknowledge>
                            ): Boolean {
        if (dmgTacks.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("dmg")
                .buildConfirmRequestWithHashes(buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                                               listOf(),
                                               dmgTacks.map { ack -> Base64.getDecoder().decode(ack.valueHash) })

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
        oa: String,
        messageNames: List<String>?
                               ): List<DmgMessage> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")

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
        return response.`return`.tAckResponses.map {
            DmgAcknowledge(it.tAck.resultMajor, it.tAck.resultMinor, it.tAck.resultMessage).apply {
                io = it.tAck.issuer.replace("urn:nip:issuer:io:".toRegex(), "")
                reference = it.tAck.reference
                valueHash = b64.encodeToString(it.tAck.value)
            }
        } + response.`return`.msgResponses.map { r ->
            ResponseObjectBuilderFactory.getResponseObjectBuilder().handleAsyncResponse(r)?.let { dec ->
                dec.retrieveTransactionResponse?.let { rtr ->
                    DmgsList().apply {
                        io =
                            rtr.response?.author?.hcparties?.find { it.ids.isNotEmpty() && it.cds.any { it.s == CDHCPARTYschemes.CD_HCPARTY && it.value == "orginsurance" } }
                                ?.ids?.firstOrNull()?.value
                        reference = r.commonOutput.nipReference
                        valueHash = b64.encodeToString(r.detail.hashValue)
                        rtr.acknowledge?.errors?.let {
                            errors.addAll(it.flatMap { et ->
                                et.cds.map { cd ->
                                    org.taktik.connector.business.domain.Error(
                                        cd.value,
                                        et.url,
                                        et.description?.value,
                                        errorMessages.find { it.code == cd.value && it.context == "CINNIC" && it.subcontext == "DMG-LISTREQ" }?.message
                                                                              )
                                }
                            })
                        }
                        if (rtr.acknowledge?.isIscomplete == true) {
                            dec.kmehrmessage?.let { km ->
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
                                                    i.cost.decimal?.let { setPaymentAmount(idx + 1, it.toDouble()) }
                                                    i.cost.unit?.let { setPaymentCurrency(idx + 1, it.cd?.value) }
                                                    i.beginmoment?.let { setPaymentDate(idx + 1, it.date?.toDate()) }
                                                }
                                        }
                                    }
                                } ?: listOf())
                            }
                        }
                    }
                } ?: dec.kmehrmessage?.let {
                    val io =
                        dec.kmehrmessage?.header?.sender?.hcparties?.firstOrNull()
                            ?.ids?.find { it.s == IDHCPARTYschemes.ID_INSURANCE }?.value
                    it.folders.firstOrNull()?.let { f ->
                        f.transactions?.firstOrNull()?.let { t ->
                            when {
                                t.cds.any { cd -> cd.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && cd.value == "gmdextension" } -> DmgExtension().apply {
                                    this.io = io
                                    f.patient?.let { fillDmgMessage(this, it) }
                                    reference = r.commonOutput.nipReference
                                    valueHash = b64.encodeToString(r.detail.hashValue)
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
                                t.cds.any { cd -> cd.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && cd.value == "gmdclosure" } -> DmgClosure().apply {
                                    this.io = io
                                    f.patient?.let { fillDmgMessage(this, it) }
                                    reference = r.commonOutput.nipReference
                                    valueHash = b64.encodeToString(r.detail.hashValue)
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
            }
        }.filterNotNull()
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
                                    ): Boolean {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")

        val istest = config.getProperty("endpoint.dmg.notification.v1").contains("-acpt")
        val author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        val inputReference =
            IdGeneratorFactory.getIdGenerator().generateId().let { if (istest) "T" + it.substring(1) else it }
        val now = DateTime().withMillisOfSecond(0)

        val postHeader = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:post:msg")).apply {
            to = URI(if (oa != null) "urn:nip:destination:io:$oa" else "")
            faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
            replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
            messageID = URI("uuid:" + UUID.randomUUID())
        }

        val retrieveTransactionRequest = RetrieveTransactionRequest().apply {
            request = RequestType().apply {
                id = IDKMEHR()//TODO HcPartyUtil.createKmehrId(DmgConstants.PROJECT_IDENTIFIER, inputReference)
                this.author = author
                date = now
                time = now
            }

            select = SelectRetrieveTransactionType().apply {
                transaction = TransactionType().apply {
                    this.author = author
                    begindate = DateTime(requestDate)
                    cds.add(CDTRANSACTION().apply {
                        s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv =
                        "1.0"; value = "gmd"
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
        val post =
            BuilderFactory.getRequestObjectBuilder("dmg")
                .buildPostRequest(ci, SendRequestMapper.mapBlobToCinBlob(blob), null)
        val postResponse = genAsyncService.postRequest(samlToken, post, postHeader)
        return postResponse.`return`.resultMajor == "urn:nip:tack:result:major:success"
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
            hcparties.add(HcpartyType().apply {
                ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.0"; value = "persphysician" })
                firstname = hcpFirstName
                familyname = hcpLastName
            })
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
}
