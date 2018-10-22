package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType
import be.fgov.ehealth.chap4.core.v1.CommonInputType
import be.fgov.ehealth.chap4.core.v1.OriginType
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType
import be.fgov.ehealth.chap4.core.v1.SecuredContentType
import be.fgov.ehealth.chap4.protocol.v1.AbstractChap4MedicalAdvisorAgreementResponseType
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrrequest
import be.fgov.ehealth.standards.kmehr.cd.v1.CDERROR
import be.fgov.ehealth.standards.kmehr.cd.v1.CDERRORschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import com.google.gson.Gson
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.chapterIV.builders.impl.ResponseBuilderImpl
import org.taktik.connector.business.chapterIV.domain.ChapterIVBuilderResponse
import org.taktik.connector.business.chapterIV.domain.ChapterIVReferences
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues
import org.taktik.connector.business.chapterIV.utils.ACLUtils
import org.taktik.connector.business.chapterIV.utils.FolderTypeUtils
import org.taktik.connector.business.chapterIV.utils.KeyDepotHelper
import org.taktik.connector.business.chapterIV.validators.Chapter4XmlValidator
import org.taktik.connector.business.chapterIV.validators.impl.Chapter4XmlValidatorImpl
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory
import org.taktik.connector.business.chapterIV.wrapper.factory.impl.AskXmlObjectFactory
import org.taktik.connector.business.chapterIV.wrapper.factory.impl.ConsultationXmlObjectFactory
import org.taktik.connector.business.chapterIV.wrapper.impl.WrappedObjectMarshallerHelper
import org.taktik.connector.business.domain.chapter4.AgreementResponse
import org.taktik.connector.business.domain.chapter4.AgreementTransaction
import org.taktik.connector.business.domain.chapter4.Appendix
import org.taktik.connector.business.domain.chapter4.Problem
import org.taktik.connector.business.domain.chapter4.RequestType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes.CD_CHAPTER_4_DOCUMENTSEQAPPENDIX
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes.CD_CHAPTER_4_PARAGRAPH
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes.CD_CHAPTER_4_VERSESEQAPPENDIX
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.AGREEMENTENDDATE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.AGREEMENTSTARTDATE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.AGREEMENTTYPE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.CAREPROVIDERREFERENCE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.CHAPTER_4_ANNEXREFERENCE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.CHAPTER_4_REFERENCE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.CONSULTATIONENDDATE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.CONSULTATIONSTARTDATE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.DECISIONREFERENCE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.REFUSALJUSTIFICATION
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.IOREQUESTREFERENCE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.REQUESTTYPE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.RESPONSETYPE
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues.UNITNUMBER
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes.CD_ITEM_MAA
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDLNKvalues.ISANAPPENDIXOF
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDLNKvalues.MULTIMEDIA
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDMAARESPONSETYPEvalues.AGREEMENT
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDMAARESPONSETYPEvalues.INTREATMENT
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDMEDIATYPEvalues
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEX
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes.CD_TRANSACTION
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes.CD_TRANSACTION_MAA
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONvalues
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.LnkType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes.ID_HCPARTY
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes.ID_KMEHR
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes.ID_PATIENT
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.PersonType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.SenderType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.SexType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.StandardType
import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import org.taktik.connector.business.domain.kmehr.v20161201.Utils.Companion.makeXGC
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.domain.common.messages.AbstractMessage
import org.taktik.freehealth.middleware.domain.common.messages.WarningMessage
import org.taktik.freehealth.middleware.drugs.civics.AddedDocumentPreview
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import org.taktik.freehealth.middleware.drugs.logic.DrugsLogic
import org.taktik.freehealth.middleware.dto.MycarenetError
import org.taktik.freehealth.middleware.dto.efact.CommonOutput
import org.taktik.freehealth.middleware.service.Chapter4Service
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.utils.FuzzyValues
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.Serializable
import java.io.UnsupportedEncodingException
import java.math.BigDecimal
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.Arrays
import java.util.Date
import java.util.UUID
import javax.xml.bind.JAXBContext
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.soap.SOAPException
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

@Service
class Chapter4ServiceImpl(val stsService: STSService, val drugsLogic: DrugsLogic, val kgssService: KgssServiceImpl) : Chapter4Service {
    private val freehealthChapter4Service: org.taktik.connector.business.chapterIV.service.ChapterIVService =
        org.taktik.connector.business.chapterIV.service.impl.ChapterIVServiceImpl(EhealthReplyValidatorImpl())

    private var demandMessages: List<AbstractMessage> = ArrayList()
    private var consultMessages: List<AbstractMessage> = ArrayList()
    private val chapter4XmlValidator: Chapter4XmlValidator = Chapter4XmlValidatorImpl()
    private val config = ConfigFactory.getConfigValidator(emptyList())

    val chapter4ConsultationWarnings =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/Chapter4ConsultationWarnings.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val chapter4ConsultationErrors =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/Chapter4ConsultationErrors.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val chapter4NotificationWarnings =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/Chapter4AgreementWarnings.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val chapter4NotificationErrors =
        Gson().fromJson(this.javaClass.getResourceAsStream("/be/errors/Chapter4AgreementErrors.json").reader(Charsets.UTF_8), arrayOf<MycarenetError>().javaClass).associateBy({ it.uid!! }, { it })
    val xPathfactory = XPathFactory.newInstance()


    private val log = LogFactory.getLog(this::class.java)

    override fun findParagraphs(searchString: String, language: String): List<ParagraphPreview> = drugsLogic.findParagraphs(searchString, language)

    override fun findParagraphsWithCnk(cnk: Long?, language: String): List<ParagraphPreview> = drugsLogic.findParagraphsWithCnk(cnk, language)

    override fun getParagraphInfos(chapterName: String, paragraphName: String) = drugsLogic.getParagraphInfos(chapterName, paragraphName)

    override fun getMppsForParagraph(chapterName: String, paragraphName: String) = drugsLogic.getMppsForParagraph(chapterName, paragraphName)

    override fun getAddedDocuments(chapterName: String, paragraphName: String): List<AddedDocumentPreview> = drugsLogic.getAddedDocuments(chapterName, paragraphName)

    private fun buildOriginType(nihii: String, ssin: String, firstName: String, lastName: String): OriginType =
        OriginType().apply {
            val principal = SecurityContextHolder.getContext().authentication?.principal as? User

            `package` = be.fgov.ehealth.chap4.core.v1.PackageType().apply {
                name =
                    be.fgov.ehealth.chap4.core.v1.ValueRefString()
                        .apply { value = config.getProperty("genericasync.invoicing.package.name") }
                license = be.fgov.ehealth.chap4.core.v1.LicenseType().apply {
                    username = principal?.mcnLicense ?: config.getProperty("mycarenet.license.username")
                    password = principal?.mcnPassword ?: config.getProperty("mycarenet.license.password")
                }
            }
            careProvider = be.fgov.ehealth.chap4.core.v1.CareProviderType().apply {
                this.nihii = be.fgov.ehealth.chap4.core.v1.NihiiType().apply {
                    quality = "doctor"
                    value = be.fgov.ehealth.chap4.core.v1.ValueRefString().apply { value = nihii }
                }
                physicalPerson = be.fgov.ehealth.chap4.core.v1.IdType().apply {
                    this.nihii = be.fgov.ehealth.chap4.core.v1.NihiiType().apply {
                        quality = "doctor"
                        value = be.fgov.ehealth.chap4.core.v1.ValueRefString().apply { value = nihii }
                    }
                    this.ssin = be.fgov.ehealth.chap4.core.v1.ValueRefString().apply { value = ssin }
                    this.name = be.fgov.ehealth.chap4.core.v1.ValueRefString().apply { value = "$firstName $lastName" }
                }
            }
        }

    private fun createCommonInput(isTest: Boolean, commonReference: String,
        hcpNihii: String, hcpSsin: String,
        hcpFirstName: String, hcpLastName: String) = CommonInputType().apply {
        request = be.fgov.ehealth.chap4.core.v1.RequestType().apply {
            isIsTest = isTest
        }
        origin = buildOriginType(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        this.inputReference = commonReference
    }

    private fun getUnknownKey(keystoreId: String,
        keyStore: KeyStore,
        passPhrase: String,
        subTypeName: String,
        credential: Credential): KeyResult {
        val acl = ACLUtils.createAclChapterIV(subTypeName)
        val etk = KeyDepotManagerFactory.getKeyDepotManager().getETK(credential)
        if (etk == null) {
            throw IllegalArgumentException("EncryptionETK is undefined")
        } else {
            val systemETK =
                etk.etk.encoded
            return kgssService.getNewKey(keystoreId, keyStore, passPhrase, acl, systemETK)
        }
    }

    private fun createAndValidateSealedRequest(keystoreId: String,
        keyStore: KeyStore,
        passPhrase: String,
        crypto: Crypto, credential: Credential, message: Kmehrmessage,
        careReceiver: CareReceiverIdType,
        xmlObjectFactory: XmlObjectFactory,
        agreementStartDate: DateTime): SealedRequestWrapper<*> {
        try {
            val e = this.getUnknownKey(keystoreId, keyStore, passPhrase, xmlObjectFactory.getSubtypeNameToRetrieveCredentialTypeProperties(), credential)
            val request = xmlObjectFactory.createSealedRequest()
            request.agreementStartDate = agreementStartDate
            request.careReceiver = this.mapToCinCareReceiverIdType(careReceiver)
            request.sealedContent = this.getSealedContent(crypto, credential, message, e, xmlObjectFactory)
            request.unsealKeyId = e.keyId
//            this.chapter4XmlValidator.validate(request.xmlObject)

            return request
        } catch (var7: UnsupportedEncodingException) {
            log.debug("\t## The Character Encoding is not supported : throwing technical connector exception")
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.CHARACTER_ENCODING_NOTSUPPORTED, var7)
        }

    }

    private fun mapToCinCareReceiverIdType(careReceiver: CareReceiverIdType): be.cin.types.v1.CareReceiverIdType {
        val mappedCareReceiver = be.cin.types.v1.CareReceiverIdType()
        mappedCareReceiver.mutuality = careReceiver.mutuality
        mappedCareReceiver.regNrWithMut = careReceiver.regNrWithMut
        mappedCareReceiver.ssin = careReceiver.ssin
        return mappedCareReceiver
    }

    private fun getSealedContent(crypto: Crypto,
        credential: Credential,
        message: Kmehrmessage,
        unknownKey: KeyResult,
        xmlObjectFactory: XmlObjectFactory): ByteArray {
        val request = this.createAndValidateUnsealedRequest(credential, message, xmlObjectFactory)
        return crypto.seal(WrappedObjectMarshallerHelper.toXMLByteArray(request), unknownKey.secretKey, unknownKey.keyId)
    }

    private fun createAndValidateUnsealedRequest(credential: Credential,
        message: Kmehrmessage,
        xmlObjectFactory: XmlObjectFactory): UnsealedRequestWrapper<*> {
        val request = xmlObjectFactory.createUnsealedRequest()
        request.etkHcp = KeyDepotManagerFactory.getKeyDepotManager().getETK(credential).etk.encoded
        request.kmehrRequest = this.createAndValidateKmehrRequestXmlByteArray(message)
        //this.chapter4XmlValidator.validate(request.xmlObject)
        return request
    }

    private fun createAndValidateKmehrRequestXmlByteArray(message: Kmehrmessage): ByteArray {
        val kmehrrequest = Kmehrrequest().apply { this.kmehrmessage = message }
        //this.chapter4XmlValidator.validate(kmehrrequest)
        val kmehrMarshallHelper = MarshallerHelper(Kmehrrequest::class.java, Kmehrrequest::class.java)
        return kmehrMarshallHelper.toXMLByteArray(kmehrrequest)
    }

    private fun marshallAndEncryptSealedRequest(crypto: Crypto, request: SealedRequestWrapper<*>): SecuredContentType {
        val marshalledContent = WrappedObjectMarshallerHelper.toXMLByteArray(request)
        log.debug("securedContent : $marshalledContent")
        val sealedKnown = crypto.seal(KeyDepotHelper.chapterIVEncryptionToken, marshalledContent)
        val securedContent = SecuredContentType()
        securedContent.securedContent = sealedKnown
        return securedContent
    }

    private fun buildAndValidateAgreementRequest(crypto: Crypto, xmlObjectFactory: XmlObjectFactory,
        careReceiver: CareReceiverIdType,
        recordCommonInput: RecordCommonInputType,
        commonInput: CommonInputType,
        sealedRequest: SealedRequestWrapper<*>): Chap4MedicalAdvisorAgreementRequestWrapper<*> {
        val agreementRequest = xmlObjectFactory.createChap4MedicalAdvisorAgreementRequest()
        agreementRequest.careReceiver = careReceiver
        agreementRequest.recordCommonInput = recordCommonInput
        agreementRequest.commonInput = commonInput
        agreementRequest.request = this.marshallAndEncryptSealedRequest(crypto, sealedRequest)
        //this.chapter4XmlValidator.validate(agreementRequest.xmlObject)
        return agreementRequest
    }

    private fun createAgreementRequest(keystoreId: String,
        keyStore: KeyStore,
        passPhrase: String,
        crypto: Crypto,
        credential: Credential,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        message: be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage,
        isTest: Boolean,
        references: ChapterIVReferences,
        xmlObjectFactory: XmlObjectFactory,
        agreementStartDate: DateTime?): ChapterIVBuilderResponse {
        if (agreementStartDate == null) {
            throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.INPUT_PARAM_NULL, "input parameter agreementStartDate was null")
        } else {
            val folder = message.folders[0]
            val careReceiver = CareReceiverIdType().apply {
                ssin =
                    folder.patient.ids.filter { it.s == be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes.ID_PATIENT && it.value != null }
                        .firstOrNull()
                        ?.value
                mutuality = folder.patient.insurancymembership?.id?.value
                regNrWithMut =
                    folder.patient.insurancymembership?.membership?.let { if (it is Element) it.textContent else null }
            }

            val recordCommonInput =
                RecordCommonInputType().apply { inputReference = BigDecimal(references.recordCommonInputId) }
            val commonInput =
                createCommonInput(isTest, references.commonReference!!, hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
            val sealedRequest =
                this.createAndValidateSealedRequest(keystoreId, keyStore, passPhrase, crypto, credential, message, careReceiver, xmlObjectFactory, agreementStartDate)
            val resultWrapper =
                this.buildAndValidateAgreementRequest(crypto, xmlObjectFactory, careReceiver, recordCommonInput, commonInput, sealedRequest)
            val result = hashMapOf(
                "references" to references,
                "folder" to folder,
                "kmehrmessage" to message,
                "carereceiver" to careReceiver,
                "recordcommoninput" to recordCommonInput,
                "commoninput" to (commonInput as Serializable),
                "sealedrequest" to sealedRequest,
                "result" to resultWrapper)
            return ChapterIVBuilderResponse(result)
        }
    }

    override fun requestAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        requestType: RequestType,
        civicsVersion: String,
        paragraph: String,
        appendices: List<Appendix>,
        verses: List<String>?,
        incomplete: Boolean,
        start: Long,
        end: Long?,
        decisionReference: String?,
        ioRequestReference: String?): AgreementResponse {

        val isTest = config.getProperty("endpoint.ch4.admission.v1").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val ref = "" + System.currentTimeMillis()
        val references = ChapterIVReferences(true)

        val demandMessage =
            getDemandKmehrMessage(hcpNihii, hcpSsin, hcpFirstName, hcpLastName, patientSsin, patientDateOfBirth, patientFirstName, patientLastName, patientGender, requestType, references.commonReference!!, civicsVersion, start, end, verses, appendices, ref, decisionReference, ioRequestReference, paragraph)

        val bos = ByteArrayOutputStream()
        JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
            .createMarshaller().marshal(demandMessage, bos)
        val msg = bos.toByteArray()

        log.debug("Agreement request kmehr message:" + msg.toString(Charsets.UTF_8))

        val v1Message =
            JAXBContext.newInstance(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java).createUnmarshaller().unmarshal(ByteArrayInputStream(msg)) as be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage

        val responseBuilder = ResponseBuilderImpl(crypto, credential, chapter4XmlValidator)

        val agreementStartDate = FolderTypeUtils.retrieveConsultationStartDateOrAgreementStartDate(v1Message.folders[0])
        val request =
            createAgreementRequest(keystoreId.toString(), keystore, passPhrase, crypto, credential, hcpNihii, hcpSsin, hcpFirstName, hcpLastName, v1Message, isTest, references, AskXmlObjectFactory(), agreementStartDate
                ?: DateTime()).askChap4MedicalAdvisorAgreementRequest
        val response = try {
            request?.let { freehealthChapter4Service.askChap4MedicalAdvisorAgreement(samlToken, it) }
                ?: AskChap4MedicalAdvisorAgreementResponse()
        } catch (e: SoaErrorException) {
            return generateError(e, CommonOutput())
        }

        val commonOutput = CommonOutput(response.commonOutput?.inputReference ?: response.recordCommonOutput.inputReference?.toString(), response.commonOutput.nipReference, response.commonOutput.outputReference ?: response.recordCommonOutput.outputReference?.toString())

        try {
            val kmehrResponse =
                responseBuilder.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response).kmehrresponse

            val aggResponse = AgreementResponse(commonOutput = commonOutput)
            aggResponse.isAcknowledged = kmehrResponse.acknowledge != null && kmehrResponse.acknowledge.isIscomplete
            kmehrResponse.acknowledge?.let {
                aggResponse.warnings = it.warnings?.flatMap { errorType ->
                    extractError(ByteArrayOutputStream().apply { JAXBContext.newInstance(Kmehrrequest::class.java).createMarshaller().marshal(Kmehrrequest(v1Message), this) }.toByteArray(),
                                 errorType.cds.find { it.s == CDERRORschemes.CD_ERROR }?.value ?: "000", chapter4NotificationWarnings, errorType.url)
                }
                aggResponse.errors = it.errors?.flatMap { errorType ->
                    extractError(ByteArrayOutputStream().apply { JAXBContext.newInstance(Kmehrrequest::class.java).createMarshaller().marshal(Kmehrrequest(v1Message), this) }.toByteArray(),
                                 errorType.cds.find { it.s == CDERRORschemes.CD_ERROR }?.value ?: "000", chapter4NotificationErrors, errorType.url)
                }
            }

            if (kmehrResponse.kmehrmessage != null) {
                val mh =
                    MarshallerHelper(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java, be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
                aggResponse.content = mh.toXMLByteArray(kmehrResponse.kmehrmessage)
            }

            if (aggResponse.content != null) {
                kmehrResponse.kmehrmessage.apply {
                    includeMessageInResponse(aggResponse, this)
                }
            }
            if (aggResponse.isAcknowledged) {
                aggResponse.content = msg
            }

            return aggResponse
        } catch (ex: ChapterIVBusinessConnectorException) {
            return generateError(ex, commonOutput)
        }
        if (aggResponse.isAcknowledged) {
            aggResponse.content = msg
        }

        val requestMarshaller =
            MarshallerHelper(AskChap4MedicalAdvisorAgreementRequest::class.java, AskChap4MedicalAdvisorAgreementRequest::class.java)
        val requestXmlByteArray = requestMarshaller.toXMLByteArray(request);
        aggResponse.requestXML = requestXmlByteArray.toString(Charsets.UTF_8);

        val responseMarshaller =
            MarshallerHelper(AskChap4MedicalAdvisorAgreementResponse::class.java, AskChap4MedicalAdvisorAgreementResponse::class.java)
        val responseXmlByteArray = responseMarshaller.toXMLByteArray(response);
        aggResponse.responseXML = responseXmlByteArray.toString(Charsets.UTF_8);

        return aggResponse
    }

    override fun agreementRequestsConsultation(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        civicsVersion: String?,
        paragraph: String?,
        start: Long,
        end: Long?,
        reference: String?): AgreementResponse {
        val isTest = config.getProperty("endpoint.ch4.admission.v1").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val ref = reference ?: ""+System.currentTimeMillis()
        val references = ChapterIVReferences(true)

        val consultationMessage =
            getConsultationTransaction(hcpNihii, hcpSsin, hcpFirstName, hcpLastName, patientSsin, patientDateOfBirth, patientFirstName, patientLastName, patientGender, references.commonReference!!, start, end, civicsVersion,
                                       paragraph, ref)

        val bos = ByteArrayOutputStream()
        JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
            .createMarshaller().marshal(consultationMessage, bos)
        val msg = bos.toByteArray()

        if (log.isDebugEnabled) {
            val requestString = bos.toByteArray().toString(Charset.defaultCharset())
            log.debug("Sent CH4 request: $requestString")
        }

        val v1Message =
            JAXBContext.newInstance(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java).createUnmarshaller().unmarshal(ByteArrayInputStream(msg)) as be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage

        val responseBuilder = ResponseBuilderImpl(crypto, credential, chapter4XmlValidator)

        val agreementStartDate = FolderTypeUtils.retrieveConsultationStartDateOrAgreementStartDate(v1Message.folders[0])
        val request =
            createAgreementRequest(keystoreId.toString(), keystore, passPhrase, crypto, credential, hcpNihii, hcpSsin, hcpFirstName, hcpLastName, v1Message, isTest, references, ConsultationXmlObjectFactory(), agreementStartDate
                ?: DateTime()).consultChap4MedicalAdvisorAgreementRequest

        val response = try {
            request?.let { freehealthChapter4Service.consultChap4MedicalAdvisorAgreement(samlToken, it) }
                ?: ConsultChap4MedicalAdvisorAgreementResponse()
        } catch (e: SoaErrorException) {
            return generateError(e, CommonOutput())
        }

        val commonOutput = CommonOutput(response.commonOutput?.inputReference ?: response.recordCommonOutput.inputReference?.toString(), response.commonOutput.nipReference, response.commonOutput.outputReference ?: response.recordCommonOutput.outputReference?.toString())

        try {
            val retrievedKmehrResponse =
                responseBuilder.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response)

            if (log.isDebugEnabled) {
                val kmehrString = retrievedKmehrResponse.getKmehrResponseBytes().toString(Charset.defaultCharset())
                log.debug("Received CH4 response: $kmehrString")
            }

            val agreementResponse = AgreementResponse()
            val ack = retrievedKmehrResponse.kmehrresponse.acknowledge
            agreementResponse.isAcknowledged = ack.isIscomplete
            agreementResponse.warnings =
                ack.warnings.flatMap { errorType ->
                    extractError(ByteArrayOutputStream().apply { JAXBContext.newInstance(Kmehrrequest::class.java).createMarshaller().marshal(Kmehrrequest(v1Message), this) }.toByteArray(),
                                 errorType.cds.find { it.s == CDERRORschemes.CD_ERROR }?.value ?: "000", chapter4ConsultationWarnings, errorType.url)
                }
            agreementResponse.errors =
                ack.errors.flatMap { errorType ->
                    extractError(ByteArrayOutputStream().apply { JAXBContext.newInstance(Kmehrrequest::class.java).createMarshaller().marshal(Kmehrrequest(v1Message), this) }.toByteArray(),
                                 errorType.cds.find { it.s == CDERRORschemes.CD_ERROR }?.value ?: "000", chapter4ConsultationErrors, errorType.url)
                }
            if (retrievedKmehrResponse.kmehrresponse.kmehrmessage != null) {
                val mh =
                    MarshallerHelper(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java, be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
                agreementResponse.content = mh.toXMLByteArray(retrievedKmehrResponse.kmehrresponse.kmehrmessage)

                if (log.isDebugEnabled) {
                    val respString = mh.toXMLByteArray(retrievedKmehrResponse.kmehrresponse.kmehrmessage).toString(Charset.defaultCharset())
                    log.debug("Received CH4 response(formatted): $respString")
                }
            }

            if (agreementResponse.content != null) {
                retrievedKmehrResponse.kmehrresponse.kmehrmessage.apply {
                    includeMessageInResponse(agreementResponse, this)
                }
            }

            agreementResponse.commonOutput = commonOutput

            return agreementResponse
        } catch (ex: ChapterIVBusinessConnectorException) {
            return generateError(ex, commonOutput)
        }

        val requestMarshaller =
            MarshallerHelper(ConsultChap4MedicalAdvisorAgreementRequest::class.java, ConsultChap4MedicalAdvisorAgreementRequest::class.java)
        val requestXmlByteArray = requestMarshaller.toXMLByteArray(request);
        agreementResponse.requestXML = requestXmlByteArray.toString(Charsets.UTF_8);

        val responseMarshaller =
            MarshallerHelper(ConsultChap4MedicalAdvisorAgreementResponse::class.java, ConsultChap4MedicalAdvisorAgreementResponse::class.java)
        val responseXmlByteArray = responseMarshaller.toXMLByteArray(response);
        agreementResponse.responseXML = responseXmlByteArray.toString(Charsets.UTF_8);

        return agreementResponse
    }

    override fun cancelAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        decisionReference: String?,
        iorequestReference: String?): AgreementResponse {
        val folderType: FolderType
        try {
            folderType =
                getCancelTransaction(hcpNihii, hcpSsin, hcpFirstName, hcpLastName, patientSsin, patientDateOfBirth, patientFirstName, patientLastName, patientGender, decisionReference, iorequestReference)
        } catch (e: IOException) {
            return generateError(ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.UNKNOWN_ERROR, e), CommonOutput())
        }

        return agreementModification(keystoreId, tokenId, hcpNihii, hcpSsin, hcpFirstName, hcpLastName, passPhrase, folderType)
    }


    override fun closeAgreement(keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        decisionReference: String): AgreementResponse {
        val folderType: FolderType
        try {
            folderType =
                getCloseTransaction(hcpNihii, hcpSsin, hcpFirstName, hcpLastName, patientSsin, patientDateOfBirth, patientFirstName, patientLastName, patientGender, decisionReference, null)
        } catch (e: IOException) {
            return generateError(ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.UNKNOWN_ERROR, e), CommonOutput())
        }

        return agreementModification(keystoreId, tokenId, hcpNihii, hcpSsin, hcpFirstName, hcpLastName, passPhrase, folderType)
    }

    private fun agreementModification(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        folder: FolderType): AgreementResponse {
        val isTest = config.getProperty("endpoint.ch4.admission.v1").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val references = ChapterIVReferences(true)

        val demandMessage =
            getKmehrMessage(references.commonReference!!, hcpNihii, hcpSsin, hcpFirstName, hcpLastName, folder)

        val bos = ByteArrayOutputStream()
        JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
            .createMarshaller().marshal(demandMessage, bos)
        val msg = bos.toByteArray()
        val v1Message =
            JAXBContext.newInstance(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java).createUnmarshaller().unmarshal(ByteArrayInputStream(msg)) as be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage

        val responseBuilder = ResponseBuilderImpl(crypto, credential, chapter4XmlValidator)

        val agreementStartDate = FolderTypeUtils.retrieveConsultationStartDateOrAgreementStartDate(v1Message.folders[0])
        val request =
            createAgreementRequest(keystoreId.toString(), keystore, passPhrase, crypto, credential, hcpNihii,
                                   hcpSsin,
                                   hcpFirstName,
                                   hcpLastName,
                                   v1Message, isTest, references, AskXmlObjectFactory(), agreementStartDate
                                       ?: DateTime()).askChap4MedicalAdvisorAgreementRequest

        val response = try { freehealthChapter4Service.askChap4MedicalAdvisorAgreement(samlToken, request!!)
        } catch (e: SoaErrorException) {
            return generateError(e, CommonOutput())
        }

        //Parse response
        if (agreementResponse.content != null) {
            retrievedKmehrResponse.kmehrresponse.kmehrmessage.apply {
                includeMessageInResponse(agreementResponse, this)
            }
        }

        val requestMarshaller =
            MarshallerHelper(AskChap4MedicalAdvisorAgreementRequest::class.java, AskChap4MedicalAdvisorAgreementRequest::class.java)
        val requestXmlByteArray = requestMarshaller.toXMLByteArray(request);
        agreementResponse.requestXML = requestXmlByteArray.toString(Charsets.UTF_8);

        val responseMarshaller =
            MarshallerHelper(AskChap4MedicalAdvisorAgreementResponse::class.java, AskChap4MedicalAdvisorAgreementResponse::class.java)
        val responseXmlByteArray = responseMarshaller.toXMLByteArray(response);
        agreementResponse.responseXML = responseXmlByteArray.toString(Charsets.UTF_8);

        return agreementResponse
        val commonOutput = CommonOutput(response.commonOutput?.inputReference ?: response.recordCommonOutput.inputReference?.toString(), response.commonOutput.nipReference, response.commonOutput.outputReference ?: response.recordCommonOutput.outputReference?.toString())


        val retrievedKmehrResponse =
            responseBuilder.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response)

        try {
            val agreementResponse = AgreementResponse()
            agreementResponse.isAcknowledged = retrievedKmehrResponse.kmehrresponse.acknowledge.isIscomplete
            agreementResponse.warnings =
                retrievedKmehrResponse.kmehrresponse.acknowledge.warnings.flatMap { errorType ->
                    extractError(ByteArrayOutputStream().apply { JAXBContext.newInstance(Kmehrrequest::class.java).createMarshaller().marshal(Kmehrrequest(v1Message), this) }.toByteArray(),
                                 errorType.cds.find { it.s == CDERRORschemes.CD_ERROR }?.value ?: "000", chapter4NotificationWarnings, errorType.url)
                }
            agreementResponse.errors =
                retrievedKmehrResponse.kmehrresponse.acknowledge.errors.flatMap { errorType ->
                    extractError(ByteArrayOutputStream().apply { JAXBContext.newInstance(Kmehrrequest::class.java).createMarshaller().marshal(Kmehrrequest(v1Message), this) }.toByteArray(),
                                 errorType.cds.find { it.s == CDERRORschemes.CD_ERROR }?.value ?: "000", chapter4NotificationErrors, errorType.url)
                }

            if (retrievedKmehrResponse.kmehrresponse.kmehrmessage != null) {
                val mh =
                    MarshallerHelper(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java, be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
                agreementResponse.content = mh.toXMLByteArray(retrievedKmehrResponse.kmehrresponse.kmehrmessage)
            }

            //Parse response
            if (agreementResponse.content != null) {
                retrievedKmehrResponse.kmehrresponse.kmehrmessage.apply {
                    includeMessageInResponse(agreementResponse, this)
                }
            }
            agreementResponse.commonOutput = commonOutput

            return agreementResponse
        } catch (ex: ChapterIVBusinessConnectorException) {
            return generateError(ex, commonOutput)
        }
    }

    private fun includeMessageInResponse(agreementResponse: AgreementResponse, kmsg: Kmehrmessage) {
        val v1LOCAL = be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes.LOCAL
        val v1CDITEMMAA = be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes.CD_ITEM_MAA
        val v1CDMAARESPONSETYPE = be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes.CD_MAA_RESPONSETYPE
        val v1CDCHAPTER4PARAGRAPH = be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes.CD_CHAPTER_4_PARAGRAPH

        kmsg.folders.forEach { f ->
            f.transactions?.forEach { t ->
                val at = agreementResponse.addTransaction(AgreementTransaction())
                val its = t?.item
                its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == RESPONSETYPE.value() } }
                    ?.contents?.map { it.cds?.find { it.s == v1CDMAARESPONSETYPE }?.value }?.find { it != null }?.let {
                    at.isAccepted = it == AGREEMENT.value()
                    at.isInTreatment = it == INTREATMENT.value()
                }
                at.timestamp = t.date.millis + t.time.millis
                at.careProviderReference =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == CAREPROVIDERREFERENCE.value() } }
                        ?.contents?.map { it.texts?.firstOrNull()?.value }?.firstOrNull()
                at.decisionReference =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == DECISIONREFERENCE.value() } }
                        ?.contents?.map { it.ids?.find { it.s == v1LOCAL }?.value }?.find { it != null }
                at.ioRequestReference =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == IOREQUESTREFERENCE.value() } }
                        ?.contents?.map { it.ids?.find { it.s == v1LOCAL }?.value }?.find { it != null }
                at.refusalJustification =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == REFUSALJUSTIFICATION.value() } }
                        ?.contents?.find { it.texts?.size ?: 0 > 0 }?.let { it.texts?.fold(mapOf()) { acc, txt -> acc + listOf(Pair(txt.l, txt.value)) } }
                at.start =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == AGREEMENTSTARTDATE.value() } }
                        ?.contents?.map { it.date }?.find { it != null }?.toGregorianCalendar()?.time
                at.end =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == AGREEMENTENDDATE.value() } }
                        ?.contents?.map { it.date }?.find { it != null }?.toGregorianCalendar()?.time
                at.unitNumber =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == UNITNUMBER.value() } }
                        ?.contents?.map { it.decimal }?.find { it != null }?.toDouble()
                //TODO at.strength = its.find { it.cds.any { it.s == be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes.CD_ITEM_MAA && it.value == UNITNUMBER.value() } }?.contents?.map { it.decimal }?.find {it != null}?.toDouble()
                at.responseType =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == RESPONSETYPE.value() } }
                        ?.contents?.map { it.cds?.find { it.s == v1CDMAARESPONSETYPE }?.value }?.find { it != null }
                at.paragraph =
                    its?.find { it.cds.any { it.s == v1CDITEMMAA && it.value == CHAPTER_4_REFERENCE.value() } }
                        ?.contents?.map { it.cds?.find { it.s == v1CDCHAPTER4PARAGRAPH }?.value }?.find { it != null }
                val bos = ByteArrayOutputStream()
                JAXBContext.newInstance(be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
                    .createMarshaller()
                    .marshal(Kmehrmessage().apply { folders.add(be.fgov.ehealth.standards.kmehr.schema.v1.FolderType().apply { transactions.add(t) }) }, bos)
                at.content = bos.toByteArray()
            }
        }
    }

    private fun generateError(e: ChapterIVBusinessConnectorException, co: CommonOutput): AgreementResponse {
        val error = AgreementResponse()
        error.isAcknowledged = false
        error.errors = Arrays.asList(MycarenetError(code = e.errorCode, msgFr = e.message, msgNl = e.message))
        error.commonOutput = co
        return error
    }

    private fun generateError(e: SoaErrorException, co: CommonOutput): AgreementResponse {
        val error = AgreementResponse()
        error.isAcknowledged = false
        error.errors = Arrays.asList(MycarenetError(code = e.errorCode, msgFr = e.message, msgNl = e.message))
        error.commonOutput = co
        return error
    }

    private fun getDemandKmehrMessage(hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        requestType: RequestType,
        commonInput: String,
        civicsVersion: String,
        start: Long?,
        end: Long?,
        verses: List<String>?,
        appendices: List<Appendix>?,
        reference: String?,
        decisionReference: String?,
        ioRequestReference: String?,
        paragraph: String?): org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage {
        val startDate =
            start?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) } ?: LocalDateTime.now().minus(12, ChronoUnit.MONTHS)
        val endDate = end?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) }

        return getKmehrMessage(
            commonInput,
            hcpNihii,
            hcpSsin,
            hcpFirstName,
            hcpLastName,
            FolderType().apply {
                ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "1" })
                this.patient = PersonType().apply {
                    ids.add(IDPATIENT().apply { s = ID_PATIENT; sv = "1.0"; value = patientSsin })
                    firstnames.add(patientFirstName)
                    familyname = patientLastName
                    birthdate = DateType().apply { date = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(patientDateOfBirth) }
                    sex = SexType().apply {
                        cd = CDSEX().apply { s = "CD-SEX"; sv = "1.0"; value = CDSEXvalues.fromValue(patientGender) }
                    }
                }

                transactions.add(TransactionType().apply {
                    initialiseTransactionTypeWithSender(hcpNihii, hcpSsin, hcpFirstName, hcpLastName, "agreementrequest", requestType, kmehrId = "1")

                    if (requestType != RequestType.complimentaryannex) {
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = ID_KMEHR; value =
                                (headingsAndItemsAndTexts.size + 1).toString()
                            })
                            cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = AGREEMENTSTARTDATE.value() })
                            contents.add(ContentType().apply {
                                date = makeXGC(startDate.toInstant(ZoneOffset.UTC).toEpochMilli())
                            })
                        })

                        endDate?.let {
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = ID_KMEHR; value =
                                    (headingsAndItemsAndTexts.size + 1).toString()
                                })
                                cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = AGREEMENTENDDATE.value() })
                                contents.add(ContentType().apply {
                                    date = makeXGC(endDate.toInstant(ZoneOffset.UTC).toEpochMilli())
                                })
                            })
                        }
                    }

                    reference?.let {
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = ID_KMEHR; value =
                                (headingsAndItemsAndTexts.size + 1).toString()
                            })
                            cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = CAREPROVIDERREFERENCE.value() })
                            contents.add(ContentType().apply {
                                texts.add(TextType().apply { l = "FR"; value = reference })
                            })
                        })
                    }

                    decisionReference?.let {
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "3" })
                            cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = DECISIONREFERENCE.value() })
                            contents.add(ContentType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.LOCAL; sl = "OAreferencesystemname"; value =
                                    decisionReference
                                })
                            })
                        })
                    } ?: ioRequestReference?.let {
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "3" })
                            cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = IOREQUESTREFERENCE.value() })
                            contents.add(ContentType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = IDKMEHRschemes.LOCAL; sl = "OAreferencesystemname"; value =
                                    ioRequestReference
                                })
                            })
                        })
                    }

                    if (requestType != RequestType.complimentaryannex) {
                        paragraph?.let {
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = ID_KMEHR; value =
                                    (headingsAndItemsAndTexts.size + 1).toString()
                                })
                                cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = CHAPTER_4_REFERENCE.value() })
                                contents.add(ContentType().apply {
                                    cds.add(CDCONTENT().apply {
                                        s = CD_CHAPTER_4_PARAGRAPH; sv = civicsVersion; value =
                                        paragraph
                                    })
                                })
                                if (verses?.isNotEmpty() == true) {
                                    contents.add(ContentType().apply {
                                        verses?.forEach {
                                            cds.add(CDCONTENT().apply {
                                                s = CDCONTENTschemes.CD_CHAPTER_4_VERSE
                                                sv = civicsVersion; value = it
                                            })
                                        }
                                    })
                                }
                            })
                        }
                    }
                })
                appendices?.forEach { app ->
                    if (app.data != null && app.mimeType != null) {
                        transactions.add(TransactionType().apply {
                            initialiseTransactionTypeWithSender(hcpNihii,
                                                                hcpSsin,
                                                                hcpFirstName,
                                                                hcpLastName,
                                                                app.verseSeq?.let { "reglementaryappendix" }
                                                                    ?: "freeappendix", kmehrId = (transactions.size + 1).toString())

                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply {
                                    s = ID_KMEHR; value =
                                    (headingsAndItemsAndTexts.size + 1).toString()
                                })
                                cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = CHAPTER_4_ANNEXREFERENCE.value() })
                                contents.add(ContentType().apply {
                                    cds.add(CDCONTENT().apply {
                                        s = CD_CHAPTER_4_PARAGRAPH; sv = civicsVersion; value =
                                        paragraph
                                    })
                                    app.verseSeq?.let {
                                        cds.add(CDCONTENT().apply {
                                            s = CD_CHAPTER_4_VERSESEQAPPENDIX; sv =
                                            civicsVersion; value = it.toString()
                                        })
                                    }
                                    app.documentSeq?.let {
                                        cds.add(CDCONTENT().apply {
                                            s =
                                                CD_CHAPTER_4_DOCUMENTSEQAPPENDIX; sv = civicsVersion; value =
                                            it.toString()
                                        })
                                    }
                                })
                            })

                            lnks.add(LnkType().apply {
                                type = MULTIMEDIA;
                                try {
                                    mediatype = CDMEDIATYPEvalues.fromValue(app.mimeType)
                                } catch (e: Exception) {
                                    mediatype = CDMEDIATYPEvalues.TEXT_XML
                                }
                                value = app.data
                            })
                            lnks.add(LnkType().apply { type = ISANAPPENDIXOF; url = "//folder[position()=1]" })
                        })
                    }
                }
            })
    }

    @Throws(IOException::class)
    private fun getConsultationTransaction(
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        commonInput: String,
        start: Long?,
        end: Long?,
        civicsVersion: String?,
        paragraph: String?,
        reference: String?): org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage {
        val startDate = start?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) } ?: LocalDateTime.now().minus(12, ChronoUnit.MONTHS)
        val endDate = end?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) } ?: startDate.plus(23, ChronoUnit.MONTHS)

        return getKmehrMessage(
            commonInput,
            hcpNihii,
            hcpSsin,
            hcpFirstName,
            hcpLastName,
            FolderType().apply {
                ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "1" })
                this.patient = PersonType().apply {
                    ids.add(IDPATIENT().apply { s = ID_PATIENT; sv = "1.0"; value = patientSsin })
                    firstnames.add(patientFirstName)
                    familyname = patientLastName
                    birthdate = DateType().apply { date = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(patientDateOfBirth) }
                    sex = SexType().apply {
                        cd = CDSEX().apply { s = "CD-SEX"; sv = "1.0"; value = CDSEXvalues.fromValue(patientGender) }
                    }
                }


                transactions.add(TransactionType().apply {
                    initialiseTransactionTypeWithSender(hcpNihii,
                                                        hcpSsin,
                                                        hcpFirstName,
                                                        hcpLastName,
                                                        "consultationrequest")
                    headingsAndItemsAndTexts.add(ItemType().apply {
                        ids.add(IDKMEHR().apply {
                            s = ID_KMEHR; value =
                            (headingsAndItemsAndTexts.size + 1).toString()
                        })
                        cds.add(CDITEM().apply {
                            s = CD_ITEM_MAA; value =
                            CONSULTATIONSTARTDATE.value()
                        })
                        contents.add(ContentType().apply {
                            date = makeXGC(startDate.toInstant(ZoneOffset.UTC).toEpochMilli())
                        })
                    })

                    headingsAndItemsAndTexts.add(ItemType().apply {
                        ids.add(IDKMEHR().apply {
                            s = ID_KMEHR; value =
                            (headingsAndItemsAndTexts.size + 1).toString()
                        })
                        cds.add(CDITEM().apply {
                            s = CD_ITEM_MAA; value =
                            CONSULTATIONENDDATE.value()
                        })
                        contents.add(ContentType().apply {
                            date = makeXGC(endDate.toInstant(ZoneOffset.UTC).toEpochMilli())
                        })
                    })

                    reference?.let {
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = ID_KMEHR; value =
                                (headingsAndItemsAndTexts.size + 1).toString()
                            })
                            cds.add(CDITEM().apply {
                                s = CD_ITEM_MAA; value =
                                CAREPROVIDERREFERENCE.value()
                            })
                            contents.add(ContentType().apply {
                                texts.add(TextType().apply { l = "FR"; value = reference })
                            })
                        })
                    }

                    paragraph?.let { p ->
                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply {
                                s = ID_KMEHR; value =
                                (headingsAndItemsAndTexts.size + 1).toString()
                            })
                            cds.add(CDITEM().apply {
                                s = CD_ITEM_MAA; value =
                                CHAPTER_4_REFERENCE.value()
                            })
                            contents.add(ContentType().apply {
                                cds.add(CDCONTENT().apply {
                                    s = CD_CHAPTER_4_PARAGRAPH; sv =
                                    civicsVersion ?: "1"; value = p
                                })
                            })
                        })
                    }
                })
            })
    }

    private fun getKmehrMessage(commonInput: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        folderType: FolderType): org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage {
        return org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage()
            .apply {
                val inami = hcpNihii.replace("[^0-9]".toRegex(), "")
                header = HeaderType().apply {
                    standard = StandardType().apply { cd = CDSTANDARD().apply { value = "20121001" } }
                    makeXGC(Instant.now().toEpochMilli()).let {
                        date = it
                        time = it
                    }
                    ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "$inami.$commonInput" })
                    this.sender = SenderType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s = ID_HCPARTY; value = inami })
                            ids.add(IDHCPARTY().apply {
                                s = IDHCPARTYschemes.INSS; value =
                                hcpSsin
                            })
                            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; value = "persphysician" })
                            name = "$hcpFirstName $hcpLastName "
                        })
                    }
                    recipients.add(RecipientType().apply {
                        hcparties.add(HcpartyType().apply {
                            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; value = "application" })
                            name = "mycarenet"
                        })
                    })
                }
                folders.add(folderType)
            }
    }

    private fun getCancelTransaction(
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        decisionReference: String?,
        ioRequestReference: String?): FolderType {
        return FolderType().apply {
            ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "1" })
            this.patient = PersonType().apply {
                ids.add(IDPATIENT().apply { s = ID_PATIENT; sv = "1.0"; value = patientSsin })
                firstnames.add(patientFirstName)
                familyname = patientLastName
                birthdate = DateType().apply { this.date = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(patientDateOfBirth) }
                sex = SexType().apply {
                    cd = CDSEX().apply { s = "CD-SEX"; sv = "1.0"; value = CDSEXvalues.fromValue(patientGender) }
                }
            }

            transactions.add(TransactionType().apply {
                initialiseTransactionTypeWithSender(hcpNihii, hcpSsin, hcpFirstName, hcpLastName, "agreementrequest", RequestType.cancellation, null)

                decisionReference?.let {
                    headingsAndItemsAndTexts.add(ItemType().apply {
                        ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "3" })
                        val scheme = CD_ITEM_MAA
                        cds.add(CDITEM().apply { s = scheme; value = DECISIONREFERENCE.value() })
                        contents.add(ContentType().apply {
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.LOCAL; sl = "OAreferencesystemname"; value =
                                decisionReference
                            })
                        })
                    })
                } ?: ioRequestReference?.let {
                    headingsAndItemsAndTexts.add(ItemType().apply {
                        ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "3" })
                        cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = IOREQUESTREFERENCE.value() })
                        contents.add(ContentType().apply {
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.LOCAL; sl = "OAreferencesystemname"; value =
                                ioRequestReference
                            })
                        })
                    })
                } ?: throw IllegalArgumentException("Any of decisionReference or iorequestReference should be included")
            })
        }
    }

    private fun getCloseTransaction(hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        patientDateOfBirth: Long,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        decisionReference: String,
        date: Date?): FolderType {
        return FolderType().apply {
            ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "1" })
            this.patient = PersonType().apply {
                ids.add(IDPATIENT().apply { s = ID_PATIENT; sv = "1.0"; value = patientSsin })
                firstnames.add(patientFirstName)
                familyname = patientLastName
                birthdate = DateType().apply { this.date = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(patientDateOfBirth) }
                sex = SexType().apply {
                    cd = CDSEX().apply { s = "CD-SEX"; sv = "1.0"; value = CDSEXvalues.fromValue(patientGender) }
                }
            }

            transactions.add(TransactionType().apply {
                initialiseTransactionTypeWithSender(hcpNihii,
                                                    hcpSsin,
                                                    hcpFirstName,
                                                    hcpLastName,
                                                    "agreementrequest", RequestType.closure, null)

                headingsAndItemsAndTexts.add(ItemType().apply {
                    ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "3" })
                    cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = DECISIONREFERENCE.value() })
                    contents.add(ContentType().apply {
                        ids.add(IDKMEHR().apply {
                            s = IDKMEHRschemes.LOCAL; sl = "OAreferencesystemname"; value =
                            decisionReference
                        })
                    })
                })

            })
        }
    }

    private fun TransactionType.initialiseTransactionTypeWithSender(hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        maa: String,
        requestType: RequestType? = null,
        date: Date? = null,
        kmehrId: String = "1") {
        ids.add(IDKMEHR().apply { s = ID_KMEHR; value = kmehrId })
        cds.add(CDTRANSACTION().apply { s = CD_TRANSACTION; sv = "1.4"; value = CDTRANSACTIONvalues.MEDICALADVISORAGREEMENT.value() })
        cds.add(CDTRANSACTION().apply { s = CD_TRANSACTION_MAA; value = maa })
        author = AuthorType().apply { hcparties.add(createParty(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)) }
        recorddatetime = makeXGC(java.time.Instant.now().toEpochMilli())
        makeXGC(date?.time ?: java.time.Instant.now().toEpochMilli()).let {
            this.date = it
            this.time = it
        }
        isIscomplete = true
        isIsvalidated = true

        headingsAndItemsAndTexts.add(ItemType().apply {
            ids.add(IDKMEHR().apply { s = ID_KMEHR; value = kmehrId })
            cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = AGREEMENTTYPE.value() })
            contents.add(ContentType().apply {
                cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_MAA_TYPE; value = "chapter4" })
            })
        })
        requestType?.let {
            headingsAndItemsAndTexts.add(ItemType().apply {
                ids.add(IDKMEHR().apply { s = ID_KMEHR; value = "2" })
                cds.add(CDITEM().apply { s = CD_ITEM_MAA; value = REQUESTTYPE.value() })
                contents.add(ContentType().apply {
                    cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_MAA_REQUESTTYPE; value = requestType.name })
                })
            })
        }
    }

    fun createParty(hcpNihii: String, hcpSsin: String, hcpFirstName: String, hcpLastName: String): HcpartyType {
        return HcpartyType().apply {
            ids.add(IDHCPARTY().apply { s = ID_HCPARTY; sv = "1.0"; value = hcpNihii })
            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })

            this.cds.addAll(listOf(CDHCPARTY().apply {
                s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.0"; value =
                "persphysician"
            }))

            firstname = hcpFirstName
            familyname = hcpLastName
        }
    }

    private fun extractError(kmehrRequest: ByteArray, ec: String, errors: Map<String, MycarenetError>, errorUrl: String?): Set<MycarenetError> {
        return errorUrl?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = true
            val builder = factory.newDocumentBuilder()

            val xpath = xPathfactory.newXPath()
            val expr: XPathExpression? = try {
                xpath.compile(if (url.startsWith("/")) url else "/" + url)
            } catch (e: XPathExpressionException) {
                log.warn("Invalid XPATH returned: `$url‘", e); null
            }
            val result = mutableSetOf<MycarenetError>()

            (expr?.evaluate(
                builder.parse(ByteArrayInputStream(kmehrRequest)),
                XPathConstants.NODESET
                           ) as NodeList?)?.let { it ->
                if (it.length > 0) {
                    var node = it.item(0)
                    val textContent = node.textContent
                    var base = "/" + nodeDescr(node)
                    while (node.parentNode != null && node.parentNode is Element) {
                        base = "/${nodeDescr(node.parentNode)}$base"
                        node = node.parentNode
                    }
                    val elements =
                        errors.values.filter { it.path == base && it.code == ec && (it.regex == null || url.matches(Regex(".*" + it.regex + ".*"))) }
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
            return "item[${xpath.evaluate("ns3:cd[@S=\"CD-ITEM-MAA\"]", node)}]"
        }
        return localName
    }

}
