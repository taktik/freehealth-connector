package org.taktik.freehealth.middleware.service.impl

import be.cin.encrypted.BusinessContent
import be.cin.encrypted.EncryptedKnownContent
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.messageservices.mycarenet.core.v1.RequestType
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionResponse
import be.fgov.ehealth.mycarenet.commons.core.v3.CareProviderType
import be.fgov.ehealth.mycarenet.commons.core.v3.CareReceiverIdType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v3.IdType
import be.fgov.ehealth.mycarenet.commons.core.v3.LicenseType
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v3.PackageType
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType
import be.fgov.ehealth.mycarenet.commons.core.v3.ValueRefString
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
import org.taktik.connector.business.mycarenet.attest.domain.AttestV2BuilderResponse
import org.taktik.connector.business.mycarenet.attest.domain.InputReference
import org.taktik.connector.business.mycarenet.attest.mappers.BlobMapper
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.etee.Crypto
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
import org.taktik.freehealth.utils.FuzzyValues
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.math.BigDecimal
import java.util.UUID
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
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

    override fun startSubscription(keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String,
        ioMembership: String?,
        startDate: Int,
        isTrial: Boolean,
        signatureType: String
                                  ): StartSubscriptionResultWithResponse? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for medical house subscription operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val inputReference = InputReference().inputReference

        val now = DateTime.now().withMillisOfSecond(0)

        val sendTransactionRequest =
            createStartSubscriptionRequest(
                now,
                hcpNihii,
                hcpName,
                hcpCbe,
                patientSsin,
                patientFirstName,
                patientLastName,
                patientGender,
                io,
                ioMembership,
                startDate,
                isTrial,
                signatureType
                                          )
        val unencryptedRequest = ConnectorXmlUtils.toByteArray(sendTransactionRequest as Any)
        val blobBuilder = BlobBuilderFactory.getBlobBuilder("medicalhousemembership")

        val sendSubscripionRequest = be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest().apply {

            val principal = SecurityContextHolder.getContext().authentication?.principal as? User
            val packageInfo =
                McnConfigUtil.retrievePackageInfo("medicalhousemembership", principal?.mcnLicense, principal?.mcnPassword)

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
                                quality = "orgprimarycarecenter"; value =
                                ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                            }
                    }
                }
            }
            this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
            this.issueInstant = DateTime()
            this.routing = RoutingType().apply {
                careReceiver = CareReceiverIdType().apply {
                    ssin = patientSsin
                }
                this.referenceDate = now
            }
            this.detail =
                BlobMapper.mapBlobTypefromBlob(blobBuilder.build(unencryptedRequest, "none", detailId, "text/xml", "MAA"))
            this.xades = BlobUtil.generateXades(credential, this.detail, "medicalhousemembership")
        }

        log.info("Sending subscription request {}", ConnectorXmlUtils.toString(sendSubscripionRequest))
        val sendSubscriptionResponse = freehealthMhmService.startSubscription(samlToken, sendSubscripionRequest)

        val blobType = sendSubscriptionResponse.`return`.detail
        val blob = BlobMapper.mapBlobfromBlobType(blobType)
        val unsealedData =
            crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, blob.content).contentAsByte
        val encryptedKnownContent =
            MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(
                unsealedData
                                                                                                           )
        val xades = encryptedKnownContent!!.xades
        val signatureVerificationResult = xades?.let {
            val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES)
            val options = emptyMap<String, Any>()
            builder.verify(unsealedData, it, options)
        } ?: SignatureVerificationResult().apply {
            errors.add(SignatureVerificationError.SIGNATURE_NOT_PRESENT)
        }

        val decryptedAndVerifiedResponse =
            AttestV2BuilderResponse(
                MarshallerHelper(
                    SendTransactionResponse::class.java,
                    SendTransactionResponse::class.java
                                ).toObject(encryptedKnownContent.businessContent.value), signatureVerificationResult
                                   )

        val errors = decryptedAndVerifiedResponse.sendTransactionResponse.acknowledge.errors?.flatMap { e ->
            e.cds.find { it.s == CDERRORMYCARENETschemes.CD_ERROR }?.value?.let { ec ->
                extractError(unencryptedRequest, ec, e.url)
            } ?: setOf()
        }
        val commonOutput = sendSubscriptionResponse.`return`.commonOutput

        return decryptedAndVerifiedResponse.sendTransactionResponse?.kmehrmessage?.folders?.firstOrNull()?.let { folder ->
            StartSubscriptionResultWithResponse(
                xades = xades,
                commonOutput = CommonOutput(commonOutput?.inputReference, commonOutput?.nipReference, commonOutput?.outputReference),
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionResponse =
                        MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(decryptedAndVerifiedResponse.sendTransactionResponse)
                            .toString(Charsets.UTF_8)
                    this.transactionRequest =
                        MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                            .toString(Charsets.UTF_8)
                    sendSubscriptionResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                    sendSubscriptionResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
                },
                kmehrMessage = encryptedKnownContent?.businessContent?.value
                                               )
        } ?: StartSubscriptionResultWithResponse(
            xades = xades,
            mycarenetConversation = MycarenetConversation().apply {
                this.transactionResponse =
                    MarshallerHelper(SendTransactionResponse::class.java, SendTransactionResponse::class.java).toXMLByteArray(decryptedAndVerifiedResponse.sendTransactionResponse)
                        .toString(Charsets.UTF_8)
                this.transactionRequest =
                    MarshallerHelper(SendTransactionRequest::class.java, SendTransactionRequest::class.java).toXMLByteArray(sendTransactionRequest)
                        .toString(Charsets.UTF_8)
                sendSubscriptionResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                sendSubscriptionResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
            },
            kmehrMessage = encryptedKnownContent?.businessContent?.value
                                                )
    }


    override fun cancelSubscription(keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String,
        ioMembership: String,
        reference: String,
        endDate: Int,
        reason: String): CancelSubscriptionResultWithResponse? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun endSubscription(keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String,
        ioMembership: String,
        reference: String,
        endDate: Int,
        reason: String): EndSubscriptionResultWithResponse? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createStartSubscriptionRequest(
        now: DateTime,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        io: String,
        ioMembership: String?,
        startDate: Int,
        isTrial: Boolean,
        signatureType: String): SendTransactionRequest {

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
                        patientSsin?.let {
                            ids.add(IDPATIENT().apply {
                                s = IDPATIENTschemes.ID_PATIENT; sv = "1.0"; value = it
                            })
                        }
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
                                    hcpNihii
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
                                               FuzzyValues.getJodaDateTime(startDate.toLong())?.let {
                                                   contents.add(ContentType().apply {
                                                       date = it
                                                   })
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
                        var node = it.item(0)
                        val textContent = node.textContent
                        var base = "/" + nodeDescr(node)
                        while (node.parentNode != null && node.parentNode is Element) {
                            base = "/${nodeDescr(node.parentNode)}$base"
                            node = node.parentNode
                        }
                        val elements =
                            mhmSubscriptionErrors.values.filter {
                                it.path == base && it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*")))
                            }
                        elements.forEach { it.value = textContent }
                        result.addAll(elements)
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
        if (localName == "transaction") {
            return "transaction[${xpath.evaluate("ns1:cd[@S=\"CD-TRANSACTION-MYCARENET\"]", node)}]"
        }
        if (localName == "item") {
            return "item[${xpath.evaluate("ns1:cd[@S=\"CD-ITEM-MYCARENET\" or @S=\"CD-ITEM\"]", node)}]"
        }
        if (localName == "cd" && node is Element) {
            return "cd[${node.getAttribute("S") ?: node.getAttribute("SL")}]"
        }
        return localName
    }
}
