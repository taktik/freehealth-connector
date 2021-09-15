package org.taktik.freehealth.middleware.service.impl

import be.cin.types.v1.FaultType
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.messageservices.mycarenet.core.v1.RequestType
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionResponse
import be.fgov.ehealth.mycarenet.commons.core.v3.CareProviderType
import be.fgov.ehealth.mycarenet.commons.core.v3.CareReceiverIdType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v3.LicenseType
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v3.PackageType
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType
import be.fgov.ehealth.mycarenet.commons.core.v3.ValueRefString
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDCONTENT
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDCONTENTschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDERRORMYCARENETschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDITEM
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDITEMschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDSEX
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDSEXvalues
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDSTANDARD
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDTRANSACTION
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDTRANSACTIONschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDINSURANCE
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDINSURANCEschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDPATIENTschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.ContentType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.FolderType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.HeaderType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.ItemType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.Kmehrmessage
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.MemberinsuranceType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.PersonType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.RecipientType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.SenderType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.SexType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.StandardType
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.TransactionType
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult
import com.google.gson.Gson
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.mhm.validator.impl.MhmXmlValidatorImpl
import org.taktik.connector.business.mycarenet.attest.domain.InputReference
import org.taktik.connector.business.mycarenet.attest.mappers.BlobMapper
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.StartSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MhmService
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.math.BigDecimal
import java.util.UUID
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.ws.soap.SOAPFaultException
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@Service
class MhmServiceImpl(private val stsService: STSService) : MhmService {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val freehealthMhmService: org.taktik.connector.business.mhm.MhmService =
        org.taktik.connector.business.mhm.impl.MhmServiceImpl()

    private val mhmSubscriptionErrors =
        Gson().fromJson(
            this.javaClass.getResourceAsStream("/be/errors/mhmSubscriptionError.json").reader(Charsets.UTF_8),
            arrayOf<MycarenetError>().javaClass
        ).associateBy({ it.uid }, { it })

    private val xPathFactory = XPathFactory.newInstance()

    fun NodeList.forEach(action: (Node) -> Unit) {
        (0 until this.length).asSequence().map { this.item(it) }.forEach { action(it) }
    }

    override fun sendSubscription(keystoreId: UUID,
                                  tokenId: UUID,
                                  passPhrase: String,
                                  hcpNihii: String,
                                  hcpName: String,
                                  patientSsin: String?,
                                  patientFirstName: String,
                                  patientLastName: String,
                                  patientGender: String,
                                  io: String?,
                                  ioMembership: String?,
                                  startDate: Int,
                                  isTrial: Boolean,
                                  signatureType: String,
                                  isRecovery: Boolean,
                                  isTestForNotify: Boolean
    ): StartSubscriptionResultWithResponse? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for medical house subscription operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        log.debug("sendSubscription called with principal "+(principal?._id?:"<ANONYMOUS>")+" and license " + (principal?.mcnLicense ?: "<DEFAULT>"))

        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val inputReference = InputReference().inputReference

        val now = DateTime.now().withMillisOfSecond(0)

        val sendTransactionRequest: SendTransactionRequest =
            createStartSubscriptionRequest(
                now,
                hcpNihii,
                hcpName,
                patientSsin,
                patientFirstName,
                patientLastName,
                patientGender,
                io,
                ioMembership,
                startDate,
                isTrial,
                signatureType,
                isRecovery,
                isTestForNotify
            )
        val unencryptedRequest = ConnectorXmlUtils.toByteArray(sendTransactionRequest as Any)
        val blobBuilder = BlobBuilderFactory.getBlobBuilder("medicalhousemembership")

        val sendSubscripionRequest = be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest().apply {

            val principal = SecurityContextHolder.getContext().authentication?.principal as? User
            val packageInfo =
                McnConfigUtil.retrievePackageInfo("medicalhousemembership", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

            this.commonInput = CommonInputType().apply {
                request =
                    be.fgov.ehealth.mycarenet.commons.core.v3.RequestType()
                        .apply { isIsTest = config.getProperty("endpoint.mcn.medicalhousemembership")?.contains("-acpt") ?: false }
                this.inputReference = inputReference
                origin = OriginType().apply {
                    `package` = PackageType().apply {
                        license = LicenseType().apply {
                            username = packageInfo.userName
                            password = packageInfo.password
                        }
                        name = ValueRefString().apply { value = packageInfo.packageName }
                    }
                    careProvider = CareProviderType().apply {
                        nihii =
                            NihiiType().apply {
                                quality = "medicalhouse"; value =
                                ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                            }
                    }
                }
            }
            this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
            this.issueInstant = DateTime()
            this.routing = RoutingType().apply {
                careReceiver = CareReceiverIdType().apply {
                    patientSsin?.let {
                        ssin = patientSsin
                    }
                    ioMembership?.let {
                        mutuality = io
                        regNrWithMut = ioMembership
                    }
                }
                this.referenceDate = now
            }
            this.detail =
                BlobMapper.mapBlobTypefromBlob(blobBuilder.build(unencryptedRequest, "none", detailId, "text/xml", "MAA"))
            this.xades = BlobUtil.generateXades(credential, this.detail, "medicalhousemembership")
        }

        log.info("Sending subscription request {}", ConnectorXmlUtils.toString(sendSubscripionRequest))
        MhmXmlValidatorImpl().validate(sendSubscripionRequest)

        return try {
            val marshallerHelper = MarshallerHelper(SendSubscriptionRequest::class.java, SendSubscriptionRequest::class.java)
            val xmlRequest = marshallerHelper.toXMLByteArray(sendSubscripionRequest)

            val sendSubscriptionResponse = freehealthMhmService.sendSubscription(samlToken, sendSubscripionRequest, "urn:be:fgov:ehealth:mycarenet:medicalHouseMembership:protocol:v1:SendSubscription")

            log.info("Response: " + ConnectorXmlUtils.toString(sendSubscriptionResponse.`return`))

            val sendTransactionResponse = MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toObject(sendSubscriptionResponse.`return`.detail.value)

            val xades = sendSubscriptionResponse.`return`.xadesT?.value
            val signatureVerificationResult = xades.let {
                val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T)
                val options = emptyMap<String, Any>()
                builder.verify(sendSubscriptionResponse.`return`.detail.value, it, options)
            } ?: SignatureVerificationResult().apply {
                errors.add(SignatureVerificationError.SIGNATURE_NOT_PRESENT)
            }

            val errors = sendTransactionResponse.acknowledge.errors?.flatMap { e ->
                e.cds.find { it.s == CDERRORMYCARENETschemes.CD_ERROR }?.value?.let { ec ->
                    extractError(unencryptedRequest, ec, e.url)
                } ?: setOf()
            }

            if (errors != null && errors.isNotEmpty()) {
                val author = sendTransactionResponse?.response?.author
                val faultSource = author?.hcparties?.first()?.ids?.first()?.value

                if (faultSource != null) {
                    errors.forEach { it.faultSource = faultSource }
                }
            }

            val commonOutput = sendSubscriptionResponse.`return`.commonOutput

            sendTransactionResponse?.kmehrmessage?.folders?.firstOrNull()?.let { folder ->
                StartSubscriptionResultWithResponse(
                    xades = xades,
                    commonOutput = CommonOutput(commonOutput?.inputReference, commonOutput?.nipReference, commonOutput?.outputReference),
                    mycarenetConversation = MycarenetConversation().apply {
                        this.transactionResponse =
                            MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(sendTransactionResponse)
                                .toString(Charsets.UTF_8)
                        this.transactionRequest =
                            MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                                .toString(Charsets.UTF_8)
                        sendSubscriptionResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                        soapRequest = MarshallerHelper(SendSubscriptionRequest::class.java, SendSubscriptionRequest::class.java).toXMLByteArray(sendSubscripionRequest).toString(Charsets.UTF_8)
                    },
                    kmehrMessage = unencryptedRequest,
                    reference = folder.transactions.find { it.cds.any { it.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && (it.value == "maaagreement" || it.value == "maa") } }?.let {
                        it.item?.find { it.cds.any { it.s == CDITEMschemes.CD_ITEM_MYCARENET && it.value == "decisionreference" } }.let {
                            it?.contents?.firstOrNull()?.ids?.firstOrNull()?.value
                        }
                    },
                    subscriptionsStartDate = folder.transactions.find { it.cds.any { it.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET &&(it.value == "maaagreement" || it.value == "maa") } }?.let {
                        it.item?.find { it.cds.any { it.s == CDITEMschemes.CD_ITEM_MYCARENET && it.value == "agreementstartdate" } }.let {
                            it?.contents?.firstOrNull()?.date.let {
                                it?.toString("yyyyMMdd")!!.toInt()
                            }
                        }
                    },
                    inscriptionDate = startDate,
                    errors = errors,
                    genericErrors = null
                )
            } ?: StartSubscriptionResultWithResponse(
                xades = xades,
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionResponse =
                        MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(sendTransactionResponse)
                            .toString(Charsets.UTF_8)
                    this.transactionRequest =
                        MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                            .toString(Charsets.UTF_8)
                    sendSubscriptionResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                    soapRequest = MarshallerHelper(SendSubscriptionRequest::class.java, SendSubscriptionRequest::class.java).toXMLByteArray(sendSubscripionRequest).toString(Charsets.UTF_8)
                },
                kmehrMessage = unencryptedRequest,
                reference = null,
                subscriptionsStartDate = null,
                inscriptionDate = null,
                errors = errors,
                genericErrors = null
            )
        } catch (e:SOAPFaultException) {
            StartSubscriptionResultWithResponse(
                xades = null,
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionRequest = MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest).toString(Charsets.UTF_8)
                    soapRequest = MarshallerHelper(SendSubscriptionRequest::class.java, SendSubscriptionRequest::class.java).toXMLByteArray(sendSubscripionRequest).toString(Charsets.UTF_8)
                },
                kmehrMessage = unencryptedRequest,
                reference = null,
                subscriptionsStartDate = null,
                inscriptionDate = null,
                genericErrors = listOf(FaultType().apply {
                    faultSource = e.message
                    faultCode = e.fault?.faultCode
                })
            )
        }
    }


    override fun cancelSubscription(keystoreId: UUID,
                                    tokenId: UUID,
                                    passPhrase: String,
                                    hcpNihii: String,
                                    hcpName: String,
                                    patientSsin: String?,
                                    patientFirstName: String,
                                    patientLastName: String,
                                    patientGender: String,
                                    io: String?,
                                    ioMembership: String?,
                                    reference: String): CancelSubscriptionResultWithResponse? {

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for medical house cancel subscription operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val inputReference = InputReference().inputReference

        val now = DateTime.now().withMillisOfSecond(0)

        val sendTransactionRequest =
            createCancelSubscriptionRequest(
                now,
                hcpNihii,
                hcpName,
                patientSsin,
                patientFirstName,
                patientLastName,
                patientGender,
                io,
                ioMembership,
                reference
            )

        val unencryptedRequest = ConnectorXmlUtils.toByteArray(sendTransactionRequest as Any)
        val blobBuilder = BlobBuilderFactory.getBlobBuilder("medicalhousemembership")

        val sendCancelSubscripionRequest = be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionRequest().apply {

            val principal = SecurityContextHolder.getContext().authentication?.principal as? User
            val packageInfo =
                McnConfigUtil.retrievePackageInfo("medicalhousemembership", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

            this.commonInput = CommonInputType().apply {
                request =
                    be.fgov.ehealth.mycarenet.commons.core.v3.RequestType()
                        .apply { isIsTest = config.getProperty("endpoint.mcn.medicalhousemembership")?.contains("-acpt") ?: false }
                this.inputReference = inputReference
                origin = OriginType().apply {
                    `package` = PackageType().apply {
                        license = LicenseType().apply {
                            username = packageInfo.userName
                            password = packageInfo.password
                        }
                        name = ValueRefString().apply { value = packageInfo.packageName }
                    }
                    careProvider = CareProviderType().apply {
                        nihii =
                            NihiiType().apply {
                                quality = "medicalhouse"; value =
                                ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                            }
                    }
                }
            }
            this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
            this.issueInstant = DateTime()
            this.routing = RoutingType().apply {
                careReceiver = CareReceiverIdType().apply {
                    patientSsin?.let {
                        ssin = patientSsin
                    }
                    ioMembership?.let {
                        mutuality = io
                        regNrWithMut = ioMembership
                    }
                }
                this.referenceDate = now
            }
            this.detail =
                BlobMapper.mapBlobTypefromBlob(blobBuilder.build(unencryptedRequest, "none", detailId, "text/xml", "MAACANCELLATION"))
            this.xades = BlobUtil.generateXades(credential, this.detail, "medicalhousemembership")
        }

        log.info("Sending cancel subscription request {}", ConnectorXmlUtils.toString(sendCancelSubscripionRequest))
        return try {
        val sendCancelSubscriptionResponse = freehealthMhmService.cancelSubscription(samlToken, sendCancelSubscripionRequest, "urn:be:fgov:ehealth:mycarenet:medicalHouseMembership:protocol:v1:CancelSubscription")

        log.info("Response: "+ConnectorXmlUtils.toString(sendCancelSubscriptionResponse.`return`))

        val sendTransactionResponse = MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toObject(sendCancelSubscriptionResponse.`return`.detail.value)

        val xades = sendCancelSubscriptionResponse.`return`.xadesT?.value
        val signatureVerificationResult = xades.let {
            val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T)
            val options = emptyMap<String, Any>()
            builder.verify(sendCancelSubscriptionResponse.`return`.detail.value, it, options)
        } ?: SignatureVerificationResult().apply {
            errors.add(SignatureVerificationError.SIGNATURE_NOT_PRESENT)
        }

        val errors = sendTransactionResponse.acknowledge.errors?.flatMap { e ->
            e.cds.find { it.s == CDERRORMYCARENETschemes.CD_ERROR }?.value?.let { ec ->
                extractError(unencryptedRequest, ec, e.url)
            } ?: setOf()
        }

        if (errors != null && errors.isNotEmpty()) {
            val author = sendTransactionResponse?.response?.author
            val faultSource = author?.hcparties?.first()?.ids?.first()?.value

            if (faultSource != null) {
                errors.forEach { it.faultSource = faultSource }
            }
        }

        val commonOutput = sendCancelSubscriptionResponse.`return`.commonOutput

        return sendTransactionResponse?.kmehrmessage?.folders?.firstOrNull()?.let { folder ->
            CancelSubscriptionResultWithResponse(
                xades = xades,
                commonOutput = CommonOutput(commonOutput?.inputReference, commonOutput?.nipReference, commonOutput?.outputReference),
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionResponse =
                        MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(sendTransactionResponse)
                            .toString(Charsets.UTF_8)
                    this.transactionRequest =
                        MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                            .toString(Charsets.UTF_8)
                    sendCancelSubscriptionResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                    soapRequest = MarshallerHelper(CancelSubscriptionRequest::class.java, CancelSubscriptionRequest::class.java).toXMLByteArray(sendCancelSubscripionRequest).toString(Charsets.UTF_8)
                },
                kmehrMessage = unencryptedRequest,
                decisionReference = folder.transactions.find { it.cds.any { it.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && it.value == "maacancellation" } }?.let {
                    it.item?.find{it.cds.any { it.s == CDITEMschemes.CD_ITEM_MYCARENET && it.value == "decisionreference" }}.let{
                        it?.contents?.firstOrNull()?.ids?.firstOrNull()?.value
                    }
                },
                subscriptionsCancelDate = folder.transactions.find { it.cds.any { it.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && it.value == "maacancellation" } }?.let {
                    it.date.toString("yyyyMMdd")!!.toInt()
                },
                errors = errors,
                genericErrors = null
            )
        } ?: CancelSubscriptionResultWithResponse(
            xades = xades,
            mycarenetConversation = MycarenetConversation().apply {
                this.transactionResponse =
                    MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(sendTransactionResponse)
                        .toString(Charsets.UTF_8)
                this.transactionRequest =
                    MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                        .toString(Charsets.UTF_8)
                sendCancelSubscriptionResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                soapRequest = MarshallerHelper(CancelSubscriptionRequest::class.java, CancelSubscriptionRequest::class.java).toXMLByteArray(sendCancelSubscripionRequest).toString(Charsets.UTF_8)
            },
            kmehrMessage = unencryptedRequest,
            decisionReference = null,
            subscriptionsCancelDate = null,
            errors = errors,
            genericErrors = null
        )
        } catch (e:SOAPFaultException) {
            CancelSubscriptionResultWithResponse(
                xades = null,
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionRequest = MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest).toString(Charsets.UTF_8)
                    soapRequest = MarshallerHelper(CancelSubscriptionRequest::class.java, CancelSubscriptionRequest::class.java).toXMLByteArray(sendCancelSubscripionRequest).toString(Charsets.UTF_8)
                },
                kmehrMessage = unencryptedRequest,
                genericErrors = listOf(FaultType().apply {
                    faultSource = e.message
                    faultCode = e.fault?.faultCode
                })
            )
        }

    }

    override fun notifySubscriptionClosure(keystoreId: UUID,
                                           tokenId: UUID,
                                           passPhrase: String,
                                           hcpNihii: String,
                                           hcpName: String,
                                           patientSsin: String?,
                                           patientFirstName: String,
                                           patientLastName: String,
                                           patientGender: String,
                                           io: String?,
                                           ioMembership: String?,
                                           reference: String,
                                           endDate: Int,
                                           reason: String,
                                           decisionType: String): EndSubscriptionResultWithResponse? {

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for medical house notify subscription closure operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val inputReference = InputReference().inputReference

        val now = DateTime.now().withMillisOfSecond(0)

        val sendTransactionRequest =
            createNotifySubscriptionClosureRequest(
                now,
                hcpNihii,
                hcpName,
                patientSsin,
                patientFirstName,
                patientLastName,
                patientGender,
                io,
                ioMembership,
                reference,
                endDate,
                reason,
                decisionType
            )

        val unencryptedRequest = ConnectorXmlUtils.toByteArray(sendTransactionRequest as Any)
        val blobBuilder = BlobBuilderFactory.getBlobBuilder("medicalhousemembership")

        val sendNotifySubscripionClosureRequest = be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureRequest().apply {

            val principal = SecurityContextHolder.getContext().authentication?.principal as? User
            val packageInfo =
                McnConfigUtil.retrievePackageInfo("medicalhousemembership", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

            this.commonInput = CommonInputType().apply {
                request =
                    be.fgov.ehealth.mycarenet.commons.core.v3.RequestType()
                        .apply { isIsTest = config.getProperty("endpoint.mcn.medicalhousemembership")?.contains("-acpt") ?: false }
                this.inputReference = inputReference
                origin = OriginType().apply {
                    `package` = PackageType().apply {
                        license = LicenseType().apply {
                            username = packageInfo.userName
                            password = packageInfo.password
                        }
                        name = ValueRefString().apply { value = packageInfo.packageName }
                    }
                    careProvider = CareProviderType().apply {
                        nihii =
                            NihiiType().apply {
                                quality = "medicalhouse"; value =
                                ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                            }
                    }
                }
            }
            this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
            this.issueInstant = DateTime()
            this.routing = RoutingType().apply {
                careReceiver = CareReceiverIdType().apply {
                    patientSsin?.let {
                        ssin = patientSsin
                    }
                    ioMembership?.let {
                        mutuality = io
                        regNrWithMut = ioMembership
                    }
                }
                this.referenceDate = now
            }
            this.detail =
                BlobMapper.mapBlobTypefromBlob(blobBuilder.build(unencryptedRequest, "none", detailId, "text/xml", "MAACLOSURE"))
            this.xades = BlobUtil.generateXades(credential, this.detail, "medicalhousemembership")
        }

        log.info("Sending notify subscription closure request {}", ConnectorXmlUtils.toString(sendNotifySubscripionClosureRequest))

        return try {
            val sendNotifySubscriptionClosureResponse = freehealthMhmService.notifySubscriptionClosure(samlToken, sendNotifySubscripionClosureRequest, "urn:be:fgov:ehealth:mycarenet:medicalHouseMembership:protocol:v1:NotifySubscriptionClosure")

            log.info("Response: " + ConnectorXmlUtils.toString(sendNotifySubscriptionClosureResponse.`return`))

            val sendTransactionResponse = MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toObject(sendNotifySubscriptionClosureResponse.`return`.detail.value)

            val xades = sendNotifySubscriptionClosureResponse.`return`.xadesT?.value
            val signatureVerificationResult = xades.let {
                val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T)
                val options = emptyMap<String, Any>()
                builder.verify(sendNotifySubscriptionClosureResponse.`return`.detail.value, it, options)
            } ?: SignatureVerificationResult().apply {
                errors.add(SignatureVerificationError.SIGNATURE_NOT_PRESENT)
            }

            val errors = sendTransactionResponse.acknowledge.errors?.flatMap { e ->
                e.cds.find { it.s == CDERRORMYCARENETschemes.CD_ERROR }?.value?.let { ec ->
                    extractError(unencryptedRequest, ec, e.url)
                } ?: setOf()
            }

            if (errors != null && errors.isNotEmpty()) {
                val author = sendTransactionResponse?.response?.author
                val faultSource = author?.hcparties?.first()?.ids?.first()?.value

                if (faultSource != null) {
                    errors.forEach { it.faultSource = faultSource }
                }
            }

            val commonOutput = sendNotifySubscriptionClosureResponse.`return`.commonOutput

            return sendTransactionResponse?.kmehrmessage?.folders?.firstOrNull()?.let { folder ->
                EndSubscriptionResultWithResponse(
                    xades = xades,
                    commonOutput = CommonOutput(commonOutput?.inputReference, commonOutput?.nipReference, commonOutput?.outputReference),
                    mycarenetConversation = MycarenetConversation().apply {
                        this.transactionResponse =
                            MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(sendTransactionResponse)
                                .toString(Charsets.UTF_8)
                        this.transactionRequest =
                            MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                                .toString(Charsets.UTF_8)
                        sendNotifySubscriptionClosureResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                        soapRequest = MarshallerHelper(NotifySubscriptionClosureRequest::class.java, NotifySubscriptionClosureRequest::class.java).toXMLByteArray(sendNotifySubscripionClosureRequest).toString(Charsets.UTF_8)
                    },
                    kmehrMessage = unencryptedRequest,
                    reference = folder.transactions.find { it.cds.any { it.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && it.value == "maaclosure" } }?.let {
                        it.item?.find { it.cds.any { it.s == CDITEMschemes.CD_ITEM_MYCARENET && it.value == "decisionreference" } }.let {
                            it?.contents?.firstOrNull()?.ids?.firstOrNull()?.value
                        }
                    },
                    subscriptionsEndDate = folder.transactions.find { it.cds.any { it.s == CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET && it.value == "maaclosure" } }?.let {
                        it.item?.find { it.cds.any { it.s == CDITEMschemes.CD_ITEM_MYCARENET && it.value == "agreementenddate" } }.let {
                            it?.contents?.firstOrNull()?.date.let {
                                it?.toString("yyyyMMdd")!!.toInt()
                            }
                        }
                    },
                    errors = errors,
                    genericErrors = null
                )
            } ?: EndSubscriptionResultWithResponse(
                xades = xades,
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionResponse =
                        MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(sendTransactionResponse)
                            .toString(Charsets.UTF_8)
                    this.transactionRequest =
                        MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                            .toString(Charsets.UTF_8)
                    sendNotifySubscriptionClosureResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                    soapRequest = MarshallerHelper(NotifySubscriptionClosureRequest::class.java, NotifySubscriptionClosureRequest::class.java).toXMLByteArray(sendNotifySubscripionClosureRequest).toString(Charsets.UTF_8)
                },
                kmehrMessage = unencryptedRequest,
                errors = errors,
                subscriptionsEndDate = null,
                reference = null,
                genericErrors = null
            )
        } catch (e:SOAPFaultException) {
            EndSubscriptionResultWithResponse(
                xades = null,
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionRequest = MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest).toString(Charsets.UTF_8)
                    soapRequest = MarshallerHelper(NotifySubscriptionClosureRequest::class.java, NotifySubscriptionClosureRequest::class.java).toXMLByteArray(sendNotifySubscripionClosureRequest).toString(Charsets.UTF_8)
                },
                kmehrMessage = unencryptedRequest,
                genericErrors = listOf(FaultType().apply {
                    faultSource = e.message
                    faultCode = e.fault?.faultCode
                })
            )
        }

    }

    private fun createStartSubscriptionRequest(
        now: DateTime,
        hcpNihii: String,
        hcpName: String,
        patientSsin: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String?,
        ioMembership: String?,
        startDate: Int,
        isTrial: Boolean,
        signatureType: String,
        isRecovery: Boolean,
        isTestForNotify: Boolean): SendTransactionRequest {

        val refDateTime = now

        val requestAuthorNihii = hcpNihii
        val requestAuthorCdHcParty = "orgprimaryhealthcarecenter"

        return SendTransactionRequest().apply {
            messageProtocoleSchemaVersion = BigDecimal("1.27")
            request = RequestType().apply {
                id =
                    IDKMEHR().apply {
                        s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = requestAuthorNihii.padEnd(11, '0') + "." +
                        refDateTime.toString("yyyyMMddHHmmss")
                    }
                author = AuthorType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply {
                            s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                            requestAuthorNihii.padEnd(11, '0')
                        })
                        cds.add(CDHCPARTY().apply {
                            s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                            requestAuthorCdHcParty
                        })
                        name = hcpName
                    })
                }
                date = now; time = now
            }

            kmehrmessage = Kmehrmessage().apply {
                header = HeaderType().apply {
                    standard =
                        StandardType().apply {
                            cd =
                                CDSTANDARD().apply { s = "CD-STANDARD"; sv = "1.28"; value = "20181201" }
                        }
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
                    date = refDateTime; time = refDateTime
                    sender = SenderType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply {
                                s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                                requestAuthorNihii.padEnd(11, '0')
                            })
                            cds.add(CDHCPARTY().apply {
                                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                requestAuthorCdHcParty
                            })
                            name = hcpName
                        })
                    }
                    recipients.add(RecipientType().apply {
                        hcparties.add(HcpartyType().apply {
                            cds.add(CDHCPARTY().apply {
                                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                "application"
                            })
                            name = "mycarenet"
                        })
                    })
                }
                folders.add(FolderType().apply {
                    var trnsId = 1

                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
                    patient = PersonType().apply {
                        ids.add(IDPATIENT().apply {
                                s = IDPATIENTschemes.ID_PATIENT;
                                sv = "1.0";
                                patientSsin?.let {
                                    value = it
                                }
                        })
                        firstnames.add(patientFirstName)
                        familyname = patientLastName
                        sex =
                            SexType().apply {
                                cd =
                                    CDSEX().apply {
                                        s = "CD-SEX"; sv = "1.1"; value = try {
                                        CDSEXvalues.fromValue(patientGender)
                                    } catch (e: Exception) {
                                        CDSEXvalues.UNKNOWN
                                    }
                                    }
                            }
                        ioMembership?.let {
                            insurancymembership = MemberinsuranceType().apply {
                                id = IDINSURANCE().apply { s = IDINSURANCEschemes.ID_INSURANCE; sv = "1.0"; value = io }
                                membership = it
                            }
                        }
                    }
                    transactions.addAll(listOf(TransactionType().apply {
                        var itemId = 1
                        val author = AuthorType().apply {
                            hcparties.add(HcpartyType().apply {
                                ids.add(IDHCPARTY().apply {
                                    s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = requestAuthorNihii.padEnd(11, '0')
                                })
                                cds.add(CDHCPARTY().apply {
                                    s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                    requestAuthorCdHcParty
                                })
                                name = hcpName
                            })
                        }

                        ids.add(IDKMEHR().apply {
                            s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                            (trnsId++).toString()
                        })
                        cds.add(CDTRANSACTION().apply {
                            s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv =
                            "1.5"; value = "maa"
                        })
                        date = refDateTime; time = refDateTime
                        this.author = author
                        isIscomplete = true
                        isIsvalidated = true
                        item.addAll(listOf(ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                (itemId++).toString()
                            })
                            cds.add(CDITEM().apply {
                                s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                "agreementtype"
                            })
                            contents.add(ContentType().apply {
                                cds.add(CDCONTENT().apply {
                                    s = CDCONTENTschemes.LOCAL; sv = "1.1"; sl =
                                    "MAA-TYPE"; value = "packagemedicalhouse"
                                })
                            })
                        },
                            ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                    (itemId++).toString()
                                })
                                cds.add(CDITEM().apply {
                                    s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                    "agreementstartdate"
                                })

                                if (isRecovery){
                                    /*01/01/1900*/
                                    contents.add(ContentType().apply {
                                        date = dateTime(19000101)
                                    })

                                }else if(isTestForNotify){
                                    /*01/01/1800*/
                                    contents.add(ContentType().apply {
                                        date = dateTime(18000101)
                                    })

                                }else{
                                    //FuzzyValues.getJodaDateTime(startDate.toLong())?.let {
                                    contents.add(ContentType().apply {
                                        date = dateTime(startDate)
                                    })
                                    //}
                                }

                            },
                            ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                    (itemId++).toString()
                                })
                                cds.add(CDITEM().apply {
                                    s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                    "istrialperiod"
                                })
                                contents.add(ContentType().apply {
                                    isBoolean = isTrial
                                })
                            },
                            ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                    (itemId++).toString()
                                })
                                cds.add(CDITEM().apply {
                                    s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                    "documentidentity"
                                })
                                contents.add(ContentType().apply {
                                    cds.add(CDCONTENT().apply {
                                        s = CDCONTENTschemes.LOCAL; sv =
                                        "1.0"; sl = "SIGNATURE-TYPE"; value = signatureType
                                    })
                                })
                            }))
                    }))
                })
            }
        }
    }

    private fun createCancelSubscriptionRequest(
        now: DateTime,
        hcpNihii: String,
        hcpName : String,
        patientSsin: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String?,
        ioMembership: String?,
        reference: String
    ) : SendTransactionRequest{
        val refDateTime = now

        val requestAuthorNihii = hcpNihii
        val requestAuthorCdHcParty = "orgprimaryhealthcarecenter"

        return SendTransactionRequest().apply {
            messageProtocoleSchemaVersion = BigDecimal("1.27")
            request = RequestType().apply {
                id =
                    IDKMEHR().apply {
                        s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = requestAuthorNihii.padEnd(11, '0') + "." +
                        refDateTime.toString("yyyyMMddHHmmss")
                    }
                author = AuthorType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply {
                            s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                            requestAuthorNihii.padEnd(11, '0')
                        })
                        cds.add(CDHCPARTY().apply {
                            s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                            requestAuthorCdHcParty
                        })
                        name = hcpName
                    })
                }
                date = now; time = now
            }

            kmehrmessage = Kmehrmessage().apply {
                header = HeaderType().apply {
                    standard =
                        StandardType().apply {
                            cd =
                                CDSTANDARD().apply { s = "CD-STANDARD"; sv = "1.28"; value = "20181201" }
                        }
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
                    date = refDateTime; time = refDateTime
                    sender = SenderType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply {
                                s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                                requestAuthorNihii.padEnd(11, '0')
                            })
                            cds.add(CDHCPARTY().apply {
                                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                requestAuthorCdHcParty
                            })
                            name = hcpName
                        })
                    }
                    recipients.add(RecipientType().apply {
                        hcparties.add(HcpartyType().apply {
                            cds.add(CDHCPARTY().apply {
                                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                "application"
                            })
                            name = "mycarenet"
                        })
                    })
                }

                folders.add(FolderType().apply {
                    var trnsId = 1

                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
                    patient = PersonType().apply {
                        ids.add(IDPATIENT().apply {
                            s = IDPATIENTschemes.ID_PATIENT;
                            sv = "1.0";
                            patientSsin?.let {
                                value = it
                            }
                        })
                        firstnames.add(patientFirstName)
                        familyname = patientLastName
                        sex =
                            SexType().apply {
                                cd =
                                    CDSEX().apply {
                                        s = "CD-SEX"; sv = "1.1"; value = try {
                                        CDSEXvalues.fromValue(patientGender)
                                    } catch (e: Exception) {
                                        CDSEXvalues.UNKNOWN
                                    }
                                    }
                            }
                        ioMembership?.let {
                            insurancymembership = MemberinsuranceType().apply {
                                id = IDINSURANCE().apply { s = IDINSURANCEschemes.ID_INSURANCE; sv = "1.0"; value = io }
                                membership = it
                            }
                        }
                    }
                    transactions.addAll(listOf(TransactionType().apply {
                        var itemId = 1
                        val author = AuthorType().apply {
                            hcparties.add(HcpartyType().apply {
                                ids.add(IDHCPARTY().apply {
                                    s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                                    requestAuthorNihii.padEnd(11, '0')
                                })
                                cds.add(CDHCPARTY().apply {
                                    s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                    requestAuthorCdHcParty
                                })
                                name = hcpName
                            })
                        }

                        ids.add(IDKMEHR().apply {
                            s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                            (trnsId++).toString()
                        })
                        cds.add(CDTRANSACTION().apply {
                            s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv =
                            "1.5"; value = "maacancellation"
                        })
                        date = refDateTime; time = refDateTime
                        this.author = author
                        isIscomplete = true
                        isIsvalidated = true
                        item.addAll(listOf(
                            ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                    (itemId++).toString()
                                })
                                cds.add(CDITEM().apply {
                                    s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                    "agreementtype"
                                })
                                contents.add(ContentType().apply {
                                    cds.add(CDCONTENT().apply {
                                        s = CDCONTENTschemes.LOCAL; sv = "1.1"; sl =
                                        "MAA-TYPE"; value = "packagemedicalhouse"
                                    })
                                })
                            },
                            ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                    (itemId++).toString()
                                })
                                cds.add(CDITEM().apply {
                                    s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                    "decisionreference"
                                })
                                contents.add(ContentType().apply {
                                    ids.add(IDKMEHR().apply {
                                        s = IDKMEHRschemes.LOCAL;
                                        sv = "1.0";
                                        sl = "IOreferencesystemname";
                                        value = reference
                                    })
                                })
                            }))
                    }))
                })
            }
        }
    }

    private fun createNotifySubscriptionClosureRequest(
        now: DateTime,
        hcpNihii: String,
        hcpName : String,
        patientSsin: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String?,
        ioMembership: String?,
        reference: String,
        endDate: Int,
        reason: String,
        decisionType: String
    ): SendTransactionRequest{
        val refDateTime = now

        val requestAuthorNihii = hcpNihii
        val requestAuthorCdHcParty = "orgprimaryhealthcarecenter"

        return SendTransactionRequest().apply {
            messageProtocoleSchemaVersion = BigDecimal("1.27")
            request = RequestType().apply {
                id =
                    IDKMEHR().apply {
                        s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = requestAuthorNihii.padEnd(11, '0') + "." +
                        refDateTime.toString("yyyyMMddHHmmss")
                    }
                author = AuthorType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply {
                            s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                            requestAuthorNihii.padEnd(11, '0')
                        })
                        cds.add(CDHCPARTY().apply {
                            s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                            requestAuthorCdHcParty
                        })
                        name = hcpName
                    })
                }
                date = now; time = now
            }

            kmehrmessage = Kmehrmessage().apply {
                header = HeaderType().apply {
                    standard =
                        StandardType().apply {
                            cd =
                                CDSTANDARD().apply { s = "CD-STANDARD"; sv = "1.28"; value = "20181201" }
                        }
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
                    date = refDateTime; time = refDateTime
                    sender = SenderType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply {
                                s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                                requestAuthorNihii.padEnd(11, '0')
                            })
                            cds.add(CDHCPARTY().apply {
                                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                requestAuthorCdHcParty
                            })
                            name = hcpName
                        })
                    }
                    recipients.add(RecipientType().apply {
                        hcparties.add(HcpartyType().apply {
                            cds.add(CDHCPARTY().apply {
                                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                "application"
                            })
                            name = "mycarenet"
                        })
                    })
                }
                folders.add(FolderType().apply {
                    var trnsId = 1

                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
                    patient = PersonType().apply {
                        ids.add(IDPATIENT().apply {
                            s = IDPATIENTschemes.ID_PATIENT;
                            sv = "1.0";
                            patientSsin?.let {
                                value = it
                            }
                        })
                        firstnames.add(patientFirstName)
                        familyname = patientLastName
                        sex =
                            SexType().apply {
                                cd =
                                    CDSEX().apply {
                                        s = "CD-SEX"; sv = "1.1"; value = try {
                                        CDSEXvalues.fromValue(patientGender)
                                    } catch (e: Exception) {
                                        CDSEXvalues.UNKNOWN
                                    }
                                    }
                            }
                        ioMembership?.let {
                            insurancymembership = MemberinsuranceType().apply {
                                id = IDINSURANCE().apply { s = IDINSURANCEschemes.ID_INSURANCE; sv = "1.0"; value = io }
                                membership = it
                            }
                        }
                    }
                    transactions.addAll(listOf(TransactionType().apply {
                        var itemId = 1
                        val author = AuthorType().apply {
                            hcparties.add(HcpartyType().apply {
                                ids.add(IDHCPARTY().apply {
                                    s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =
                                    requestAuthorNihii.padEnd(11, '0')
                                })
                                cds.add(CDHCPARTY().apply {
                                    s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.15"; value =
                                    requestAuthorCdHcParty
                                })
                                name = hcpName
                            })
                        }

                        ids.add(IDKMEHR().apply {
                            s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                            (trnsId++).toString()
                        })
                        cds.add(CDTRANSACTION().apply {
                            s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv =
                            "1.5"; value = "maaclosure"
                        })
                        date = refDateTime; time = refDateTime
                        this.author = author
                        isIscomplete = true
                        isIsvalidated = true
                        item.addAll(listOf(
                            ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                    (itemId++).toString()
                                })
                                cds.add(CDITEM().apply {
                                    s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                    "agreementtype"
                                })
                                contents.add(ContentType().apply {
                                    cds.add(CDCONTENT().apply {
                                        s = CDCONTENTschemes.LOCAL; sv = "1.1"; sl =
                                        "MAA-TYPE"; value = "packagemedicalhouse"
                                    })
                                })
                            }, ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                (itemId++).toString()
                            })
                            cds.add(CDITEM().apply {
                                s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                "decisionreference"
                            })
                            contents.add(ContentType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.LOCAL;
                                    sv = "1.0";
                                    sl = "IOreferencesystemname";
                                    value = reference
                                })
                            })
                        }, ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                (itemId++).toString()
                            })
                            cds.add(CDITEM().apply {
                                s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                "agreementenddate"
                            })
                            //FuzzyValues.getJodaDateTime(endDate.toLong())?.let {
                            contents.add(ContentType().apply {
                                date = dateTime(endDate)
                            })
                            //}
                        },ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                (itemId++).toString()
                            })
                            cds.add(CDITEM().apply {
                                s = CDITEMschemes.CD_ITEM_MYCARENET; sv = "1.6"; value =
                                "closurejustification"
                            })
                            contents.add(ContentType().apply {
                                cds.add(CDCONTENT().apply {
                                    s = CDCONTENTschemes.LOCAL; sv = "1.0"; sl =
                                    "MAA-CLOSUREDECISION"; value = decisionType
                                })
                            })
                            contents.add(ContentType().apply {
                                cds.add(CDCONTENT().apply {
                                    s = CDCONTENTschemes.LOCAL; sv = "1.0"; sl =
                                    "MAA-CLOSUREJUSTIFICATION"; value = reason
                                })
                            })
                        }))
                    }))
                })
            }
        }
    }

    private fun dateTime(intDate: Int?) = intDate?.let {
        DateTime(0).withYear(intDate / 10000).withMonthOfYear((intDate / 100) % 100)
            .withDayOfMonth(intDate % 100)
    }

    private fun extractError(sendTransactionRequest: ByteArray, ec: String, errorUrl: String?): Set<MycarenetError> {
        return errorUrl?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = true
            val builder = factory.newDocumentBuilder()

            val result = mutableSetOf<MycarenetError>()
            val curratedUrl = if (url.startsWith("/")) url else "/" + url

            try {
                val xpath = xPathFactory.newXPath()
                val expr = xpath.compile(curratedUrl)

                (expr.evaluate(
                    builder.parse(ByteArrayInputStream(sendTransactionRequest)),
                    XPathConstants.NODESET
                ) as NodeList).let { it ->
                    if (it.length > 0) {
                        val startNode = it.item(0)
                        val textContent = startNode.textContent
                        var elements: List<MycarenetError>
                        var skip = 0
                        do {
                            var skipped = skip
                            var node = startNode
                            var base = if (skipped == 0) { "/" + nodeDescr(node) } else ""
                            while (node.parentNode != null && node.parentNode is Element) {
                                if (skipped <= 1) { base = "/${nodeDescr(node.parentNode)}$base" } else skipped--
                                node = node.parentNode
                            }
                            elements =
                                mhmSubscriptionErrors.values.filter {
                                    it.path.equals(base, true) && it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*")))
                                }
                            if (skipped>1) { break }
                            skip++
                        } while (elements.isEmpty())

                        if (elements.isEmpty()) {
                            elements =
                                mhmSubscriptionErrors.values.filter {
                                    it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*")))
                                }
                        }
                        elements.forEach { it.value = textContent }
                        result.addAll(elements.map { it.clone() })
                    } else {
                        result.add(
                            MycarenetError(
                                code = ec,
                                path = curratedUrl,
                                msgFr = "Erreur générique, xpath invalide",
                                msgNl = "Onbekend foutmelding, xpath ongeldig"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                result.add(
                    MycarenetError(
                        code = ec,
                        path = curratedUrl,
                        msgFr = "Erreur générique, xpath invalide : " + e.message,
                        msgNl = "Onbekend foutmelding, xpath ongeldig : " + e.message
                    )
                )
            }
            result
        } ?: setOf()
    }

    private fun nodeDescr(node: Node): String {
        val localName = node.localName ?: node.nodeName?.replace(Regex(".+?:(.+)"), "$1") ?: "unknown"
        val xpath = xPathFactory.newXPath()
        xpath.namespaceContext = object : NamespaceContext {
            override fun getNamespaceURI(prefix: String?) = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1"
            override fun getPrefix(namespaceURI: String?) = "ns1"
            override fun getPrefixes(namespaceURI: String?): Iterator<Any?> =
                if (namespaceURI == "http://www.ehealth.fgov.be/standards/kmehr/schema/v1") listOf("ns1").iterator() else listOf<String>().iterator()
        }
        /*if (localName == "transaction") {
            return "transaction[${xpath.evaluate("ns1:cd[@S=\"CD-TRANSACTION-MYCARENET\"]", node)}]"
        }*/
        return when {
            localName == "item" -> "item[${xpath.evaluate("ns1:cd[@S=\"CD-ITEM-MYCARENET\" or @S=\"CD-ITEM\"]", node)}]"
            localName == "cd" && node is Element -> {
                if (node.getAttribute("SL")?.isNotEmpty() == true) "cd[${node.getAttribute("SL")}]" else "cd[${node.getAttribute("S")}]"
            }
            else -> localName
        }
    }
}
