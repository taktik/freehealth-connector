/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.service.impl

import be.cin.encrypted.BusinessContent
import be.cin.encrypted.EncryptedKnownContent
import be.cin.mycarenet.esb.common.v2.CommonInput
import be.cin.mycarenet.esb.common.v2.OrigineType
import be.cin.nip.async.generic.Get
import be.cin.nip.async.generic.MsgQuery
import be.cin.nip.async.generic.Post
import be.cin.nip.async.generic.PostResponse
import be.cin.nip.async.generic.Query
import be.cin.types.v1.DetailType
import be.cin.types.v1.DetailsType
import be.cin.types.v1.FaultType
import be.cin.types.v1.StringLangType
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.mycarenet.commons.core.v3.CareProviderType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v3.IdType
import be.fgov.ehealth.mycarenet.commons.core.v3.LicenseType
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v3.PackageType
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType
import be.fgov.ehealth.mycarenet.commons.core.v3.ValueRefString
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import com.google.gson.Gson
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import com.sun.xml.messaging.saaj.soap.impl.ElementImpl
import com.sun.xml.messaging.saaj.soap.ver1_1.DetailEntry1_1Impl
import ma.glasnost.orika.MapperFacade
import net.sf.saxon.xpath.XPathFactoryImpl
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.NameIDType
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Subject
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.SubjectConfirmation
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.SubjectConfirmationDataType
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.AttributeQuery
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.common.GenAsyncResponse
import org.taktik.connector.business.genericasync.builders.BuilderFactory
import org.taktik.connector.business.genericasync.service.impl.GenAsyncServiceImpl
import org.taktik.connector.business.memberdata.builders.impl.ResponseObjectBuilderImpl
import org.taktik.connector.business.memberdata.validators.impl.MemberDataXmlValidatorImpl
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetcommons.mapper.v3.BlobMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.mapper.DomainBlobMapper
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.PropertyUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.WsAddressingUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.handler.domain.WsAddressingHeader
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.domain.memberdata.MdaStatus
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataAck
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataBatchRequest
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataList
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataMessage
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MemberDataService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.icure.cin.saml.extensions.AttributeQueryList
import org.taktik.icure.cin.saml.extensions.ExtensionsType
import org.taktik.icure.cin.saml.extensions.Facet
import org.taktik.icure.cin.saml.extensions.ResponseList
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.Response
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
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants

@Service
class MemberDataServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService, val mapper: MapperFacade) : MemberDataService {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"
    private val genAsyncService = GenAsyncServiceImpl("mda")

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val MemberDataErrors =
        Gson().fromJson(
            this.javaClass.getResourceAsStream("/be/errors/MemberDataErrors.json").reader(Charsets.UTF_8),
            arrayOf<MycarenetError>().javaClass
        ).associateBy({ it.uid }, { it })
    private val xPathfactory = XPathFactoryImpl()
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)
    private val memberDataService = org.taktik.connector.business.memberdata.service.impl.MemberDataServiceImpl()

    private fun makeAuthor(hcpNihii: String, hcpName: String): AuthorType {
        return AuthorType().apply {
            hcparties.add(makeHcparty(hcpNihii, hcpName))
        }
    }

    private fun makeHcparty(hcpNihii: String, hcpName: String): HcpartyType {
        return HcpartyType().apply {
            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = "medicalhouse" })
            name = hcpName
        }
    }

    override fun sendMemberDataRequest(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpName: String,
        requestType: String,
        io: String?,
        startDate: Instant,
        endDate: Instant,
        passPhrase: String,
        hospitalized: Boolean?,
        mdaRequest: MemberDataBatchRequest
    ): GenAsyncResponse {
        val encryptRequest = false
        validateQuality(hcpQuality)
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for MDA operations")

        val istest = config.getProperty("endpoint.dmg.notification.v1").contains("-acpt")
        val author = makeAuthor(hcpNihii, hcpName)
        val inputReference =
            IdGeneratorFactory.getIdGenerator().generateId()//.let { if (istest) "T" + it.substring(1) else it }
        val now = DateTime().withMillisOfSecond(0)
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)
        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo("genins", principal?.mcnLicense, principal?.mcnPassword)

        val postHeader = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:post:msg")).apply {
            faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
            replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
            messageID = URI("uuid:" + UUID.randomUUID())
        }

        val issueInstantDateTime = DateTime()
        val issueInstant = XMLGregorianCalendarImpl(issueInstantDateTime.toGregorianCalendar())
        val samlFacets = mdaRequest.facets?.map { mapper.map(it, Facet::class.java) }

        val attrQueries = mdaRequest.members.map {
            val inputRef = "" + IdGeneratorFactory.getIdGenerator().generateId()
            val requestId = IdGeneratorFactory.getIdGenerator("xsid").generateId()
            getAttrQuery(inputRef, issueInstant, samlFacets, it.hospitalized, requestType, hcpNihii, it.ssin, io, it.ioMembership, startDate, endDate)
        }

        val unEncryptedQuery = ConnectorXmlUtils.toByteArray(AttributeQueryList().apply { attributeQueries.addAll(attrQueries) })
        val blobBuilder = BlobBuilderFactory.getBlobBuilder("genericasync")
        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();

        val marshallerHelper = MarshallerHelper(MemberDataConsultationRequest::class.java, MemberDataConsultationRequest::class.java)

        val marshallRequest = marshallerHelper.toObject(unEncryptedQuery)?.apply {
            this.detail = unEncryptedQuery?.let { aqb -> BlobMapper.mapBlobTypefromBlob(blobBuilder.build(aqb, "none", detailId, "text/xml", "MDA")) }
        }?.let { marshallerHelper.toXMLByteArray(it) }


        val blob = unEncryptedQuery.let { aqb ->
            if (encryptRequest) {
                val identifierTypeString = config.getProperty("memberdata.keydepot.identifiertype", "CBE")
                val identifierValue = config.getLongProperty("memberdata.keydepot.identifiervalue", 820563481L)
                val applicationId = config.getProperty("memberdata.keydepot.application", "MYCARENET")
                val identifierSource = 48
                val identifier = IdentifierType.lookup(identifierTypeString, null as String?, identifierSource)

                val mbEtk = if (identifier == null) {
                    throw IllegalStateException("invalid configuration : identifier with type ]$identifierTypeString[ for source ETKDEPOT not found")
                } else {
                    keyDepotManager.getEtkSet(IdentifierType.CBE, identifierValue, applicationId, keystoreId, false)
                }

                crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, mbEtk, ConnectorXmlUtils.toByteArray(
                    EncryptedKnownContent().apply {
                        replyToEtk = keyDepotManager.getETK(credential, keystoreId).encoded
                        businessContent = BusinessContent().apply {
                            id = detailId
                            value = aqb
                        }
                    })).let {
                    blobBuilder.build(it, "none", detailId, "text/xml", "MDA", "encryptedForKnownBED")
                }
            } else blobBuilder.build(aqb, "none", detailId, "text/xml", "MDA")
        }

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = istest!!
            }
            origin = OrigineType().apply {
                `package` = be.cin.mycarenet.esb.common.v2.PackageType().apply {
                    license = be.cin.mycarenet.esb.common.v2.LicenseType().apply {
                        username = packageInfo.userName
                        password = packageInfo.password
                    }
                    name = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = packageInfo.packageName }
                }
                careProvider = be.cin.mycarenet.esb.common.v2.CareProviderType().apply {
                    nihii =
                        be.cin.mycarenet.esb.common.v2.NihiiType().apply {
                            quality = hcpQuality; value =
                            be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                        }

                    organization = be.cin.mycarenet.esb.common.v2.IdType().apply {
                        nihii =
                            be.cin.mycarenet.esb.common.v2.NihiiType().apply {
                                quality = hcpQuality; value =
                                be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                            }
                    }
                }
            }

            this.inputReference = inputReference
        }

        // no xades needed for MDA async
        val post = BuilderFactory.getRequestObjectBuilder("mda")
            .buildPostRequest(ci, SendRequestMapper.mapBlobToCinBlob(blob), null)
        val postResponse = genAsyncService.postRequest(samlToken, post, postHeader)
        return GenAsyncResponse().apply {
            result = postResponse.`return`.resultMajor == "urn:nip:tack:result:major:success"
            this.tack = postResponse.`return`
            mycarenetConversation = MycarenetConversation().apply {
                this.transactionRequest =
                    MarshallerHelper(Post::class.java, Post::class.java).toXMLByteArray(post).toString(Charsets.UTF_8)
                this.transactionResponse =
                    MarshallerHelper(PostResponse::class.java, PostResponse::class.java).toXMLByteArray(postResponse)
                        .toString(Charsets.UTF_8)
                postResponse?.soapResponse?.writeTo(this.soapResponseOutputStream())
                postResponse?.soapRequest?.writeTo(this.soapRequestOutputStream())
            }
        }
    }

    override fun getMemberDataMessages(keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpName: String, messageNames: List<String>?): MemberDataList? {

        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for MDA operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

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
            origin = buildOriginType(hcpNihii, hcpName)
        }

        val response = genAsyncService.getRequest(samlToken, get, getHeader)

        val b64 = Base64.getEncoder()

        return try {
            MemberDataList(
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionRequest = org.taktik.connector.technical.utils.MarshallerHelper(be.cin.nip.async.generic.Get::class.java, be.cin.nip.async.generic.Get::class.java).toXMLByteArray(get).toString(kotlin.text.Charsets.UTF_8)
                    this.transactionResponse = org.taktik.connector.technical.utils.MarshallerHelper(be.cin.nip.async.generic.GetResponse::class.java, be.cin.nip.async.generic.GetResponse::class.java).toXMLByteArray(response).toString(kotlin.text.Charsets.UTF_8)
                    response?.soapResponse?.writeTo(this.soapResponseOutputStream())
                    soapRequest = MarshallerHelper(Get::class.java, Get::class.java).toXMLByteArray(get).toString(Charsets.UTF_8)
                },
                acks = response.`return`.tAckResponses?.map {
                    MemberDataAck(
                        major = it.tAck.resultMajor,
                        minor = it.tAck.resultMinor,
                        message = it.tAck.resultMessage,
                        date = null
                    )
                },
                memberDataMessageList = response.`return`.msgResponses?.map {
                    var data: ByteArray? = if (it.detail.contentEncoding == "deflate") ConnectorIOUtils.decompress(DomainBlobMapper.mapToBlob(it.detail).content) else DomainBlobMapper.mapToBlob(it.detail).content
                    val responseList = if (it.detail.contentEncryption == "encryptedForKnownRecipient") {
                        val unsealedData = crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).contentAsByte
                        val encryptedKnownContent = MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(unsealedData)
                        MarshallerHelper(ResponseList::class.java, ResponseList::class.java).toObject(
                            if (encryptedKnownContent.businessContent.contentEncoding == "deflate")
                                ConnectorIOUtils.decompress(encryptedKnownContent.businessContent.value) else encryptedKnownContent.businessContent.value
                        )
                    } else {
                        MarshallerHelper(ResponseList::class.java, ResponseList::class.java).toObject(data)
                    }

                    MemberDataMessage(
                        commonOutput = CommonOutput(
                            inputReference = it.commonOutput.inputReference,
                            outputReference = it.commonOutput.outputReference,
                            nipReference = it.commonOutput.nipReference
                        ),
                        errors = null,
                        genericErrors = null,
                        reference = null,
                        appliesTo = null,
                        complete = null,
                        io = null,
                        memberDataResponse = responseList.responses.map {
                            MemberDataResponse(
                                assertions = it.anies.map{
                                    MarshallerHelper(Assertion::class.java, Assertion::class.java).toObject(it)
                                },
                                status = MdaStatus(
                                    it.status.statusCode?.value,
                                    it.status.statusCode?.statusCode?.value
                                ),
                                errors = it.status?.statusDetail?.anies?.map {
                                    FaultType().apply {
                                        faultCode = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "FaultCode").item(0)?.textContent
                                        faultSource = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "FaultSource").item(0)?.textContent
                                        message = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Message").item(0)?.let {
                                            StringLangType().apply {
                                                value = it.textContent
                                                lang = it.attributes.getNamedItem("lang")?.textContent
                                            }
                                        }

                                        it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Detail").let {
                                            if (it.length > 0) {
                                                details = DetailsType()
                                            }
                                            for (i in 0 until it.length) {
                                                details.details.add(DetailType().apply {
                                                    it.item(i).let {
                                                        detailCode = (it as Element).getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "DetailCode").item(0)?.textContent
                                                        detailSource = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "DetailSource").item(0)?.textContent
                                                        location = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Location").item(0)?.textContent
                                                        message = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Message").item(0)?.let {
                                                            StringLangType().apply {
                                                                value = it.textContent
                                                                lang = it.attributes.getNamedItem("lang")?.textContent
                                                            }
                                                        }
                                                    }
                                                })
                                            }
                                        }
                                    }
                                }
                            )
                        },
                        valueHash = it.detail?.hashValue?.let { b64.encodeToString(it)}
                    )

                },
                date = null
            )
        }catch (e:SOAPFaultException){
            return MemberDataList(
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionRequest = org.taktik.connector.technical.utils.MarshallerHelper(be.cin.nip.async.generic.Get::class.java, be.cin.nip.async.generic.Get::class.java).toXMLByteArray(get).toString(kotlin.text.Charsets.UTF_8)
                    this.transactionResponse = org.taktik.connector.technical.utils.MarshallerHelper(be.cin.nip.async.generic.GetResponse::class.java, be.cin.nip.async.generic.GetResponse::class.java).toXMLByteArray(response).toString(kotlin.text.Charsets.UTF_8)
                    response?.soapResponse?.writeTo(this.soapResponseOutputStream())
                    soapRequest = MarshallerHelper(Get::class.java, Get::class.java).toXMLByteArray(get).toString(Charsets.UTF_8)
                },
                acks = null,
                date = null,
                memberDataMessageList =  null
            )
        }

    }

    override fun confirmMemberDataMessages(keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpName: String, mdaMessagesHashes: List<String>): Boolean {
        if (mdaMessagesHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for MDA operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("mda")
                .buildConfirmRequestWithHashes(
                    buildOriginType(hcpNihii, hcpName),
                    mdaMessagesHashes.map { it -> ConnectorXmlUtils.toByteArray(it) },
                    listOf())

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

    override fun confirmMemberDataAcks(keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpName: String, mdaAcksHashes: List<String>): Boolean {
        if (mdaAcksHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for MDA operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("mda")
                .buildConfirmRequestWithHashes(buildOriginType(hcpNihii, hcpName),
                    listOf(),
                    mdaAcksHashes.map { valueHash -> Base64.getDecoder().decode(valueHash) })

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }


    private fun buildOriginType(hcpNihii: String, hcpName: String): OrigineType =
        OrigineType().apply {
            `package` = be.cin.mycarenet.esb.common.v2.PackageType().apply {
                name = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = config.getProperty("genericasync.dmg.package.name") }
                license = be.cin.mycarenet.esb.common.v2.LicenseType().apply {
                    username = config.getProperty("mycarenet.license.username")
                    password = config.getProperty("mycarenet.license.password")
                }
            }
            careProvider = be.cin.mycarenet.esb.common.v2.CareProviderType().apply {
                this.nihii = be.cin.mycarenet.esb.common.v2.NihiiType().apply {
                    quality = "medicalhouse"
                    value = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = hcpNihii }
                }
            }
        }


    override fun getMemberData(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String,
        patientSsin: String?,
        io: String?,
        ioMembership: String?,
        startDate: Instant,
        endDate: Instant,
        hospitalized: Boolean?,
        requestType: String?,
        facets: List<Facet>?
    ): MemberDataResponse {
        val encryptRequest = true
        validateQuality(hcpQuality)

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Genins operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        assert(patientSsin != null || io != null && ioMembership != null)

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo("genins", principal?.mcnLicense, principal?.mcnPassword)

        log.info("getMemberData called with principal " + (principal?._id
            ?: "<ANONYMOUS>") + " and license " + (principal?.mcnLicense ?: "<DEFAULT>"))

        val inputRef = "" + IdGeneratorFactory.getIdGenerator().generateId()
        val requestId = IdGeneratorFactory.getIdGenerator("xsid").generateId()

        val issueInstantDateTime = DateTime()
        val issueInstant = XMLGregorianCalendarImpl(issueInstantDateTime.toGregorianCalendar())

        val blobBuilder = BlobBuilderFactory.getBlobBuilder("memberdata")
        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();

        val attrQuery =
            getAttrQuery(inputRef, issueInstant, facets, hospitalized, requestType, hcpNihii, patientSsin, io, ioMembership, startDate, endDate)
        val unEncryptedQuery = ConnectorXmlUtils.toByteArray(attrQuery as Any)

        val request = MemberDataConsultationRequest().apply {
            commonInput = CommonInputType().apply {
                request =
                    RequestType().apply { isIsTest = true /*config.getProperty("endpoint.genins")?.contains("-acpt") ?: false*/ }
                inputReference = inputRef
                origin = OriginType().apply {
                    `package` = PackageType().apply {
                        license = LicenseType().apply {
                            username = packageInfo.userName
                            password = packageInfo.password
                        }
                        name = ValueRefString().apply { value = packageInfo.packageName }
                    }
                    config.getProperty("mycarenet.${PropertyUtil.retrieveProjectNameToUse("genins", "mycarenet.")}.site.id")?.let {
                        if (it.isNotBlank()) {
                            siteID = ValueRefString().apply { value = it }
                        }
                    }
                    careProvider = CareProviderType().apply {
                        if ((hcpQuality == "guardpost") || (hcpQuality == "medicalhouse")) {
                            // nihii11 is required with guardpost
                            nihii =
                                NihiiType().apply {
                                    quality = hcpQuality; value =
                                    ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                                }
                            organization = IdType().apply {
                                nihii =
                                    NihiiType().apply {
                                        quality = hcpQuality; value =
                                        ValueRefString().apply { value = hcpNihii.padEnd(11, '0') }
                                    }
                            }
                        } else {
                            nihii =
                                NihiiType().apply {
                                    quality = hcpQuality; value =
                                    ValueRefString().apply { value = hcpNihii }
                                }
                            physicalPerson = IdType().apply {
                                name = ValueRefString().apply { value = hcpName }
                                ssin = ValueRefString().apply { value = hcpSsin }
                                nihii =
                                    NihiiType().apply {
                                        quality = hcpQuality; value =
                                        ValueRefString().apply { value = hcpNihii }
                                    }
                            }
                        }
                    }
                }
            }
            this.id = requestId
            this.issueInstant = issueInstantDateTime


            this.detail = unEncryptedQuery.let { aqb ->
                if (encryptRequest) {
                    val identifierTypeString = config.getProperty("memberdata.keydepot.identifiertype", "CBE")
                    val identifierValue = config.getLongProperty("memberdata.keydepot.identifiervalue", 820563481L)
                    val applicationId = config.getProperty("memberdata.keydepot.application", "MYCARENET")
                    val identifierSource = 48
                    val identifier = IdentifierType.lookup(identifierTypeString, null as String?, identifierSource)

                    val mbEtk = if (identifier == null) {
                        throw IllegalStateException("invalid configuration : identifier with type ]$identifierTypeString[ for source ETKDEPOT not found")
                    } else {
                        keyDepotManager.getEtkSet(IdentifierType.CBE, identifierValue, applicationId, keystoreId, false)
                    }

                    crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, mbEtk, ConnectorXmlUtils.toByteArray(
                        EncryptedKnownContent().apply {
                            replyToEtk = keyDepotManager.getETK(credential, keystoreId).encoded
                            businessContent = BusinessContent().apply {
                                id = detailId
                                value = aqb
                                contentType = "text/xml"
                            }
                        })).let {
                        BlobMapper.mapBlobTypefromBlob(blobBuilder.build(it, "none", detailId, "text/xml", "MDA", "encryptedForKnownBED"))
                    }
                } else BlobMapper.mapBlobTypefromBlob(blobBuilder.build(aqb, "none", detailId, "text/xml", "MDA"))
            }
        }
        MemberDataXmlValidatorImpl().validate(request)

        val consultMemberData = memberDataService.consultMemberData(samlToken, request)
        val marshallerHelper = MarshallerHelper(MemberDataConsultationRequest::class.java, MemberDataConsultationRequest::class.java)
        val xmlRequest = marshallerHelper.toXMLByteArray(request)

        val unencryptedXmlRequest = marshallerHelper.toObject(xmlRequest)?.apply {
            this.detail = unEncryptedQuery?.let { aqb -> BlobMapper.mapBlobTypefromBlob(blobBuilder.build(aqb, "none", detailId, "text/xml", "MDA")) }
        }?.let { marshallerHelper.toXMLByteArray(it) }

        return ResponseObjectBuilderImpl().handleConsultationResponse(consultMemberData, crypto)?.let {
            val code1 = it.response.status.statusCode?.value
            val code2 = it.response.status.statusCode?.statusCode?.value
            MemberDataResponse(
                it.assertions,
                MdaStatus(code1, code2),
                mycarenetConversation = MycarenetConversation().apply {
                    this.transactionResponse =
                        MarshallerHelper(Response::class.java, Response::class.java).toXMLByteArray(it.response)
                            .toString(Charsets.UTF_8)
                    this.transactionRequest = (unencryptedXmlRequest ?: xmlRequest).toString(Charsets.UTF_8)

                    consultMemberData.soapResponse?.writeTo(this.soapResponseOutputStream())
                    soapRequest = MarshallerHelper(MemberDataConsultationRequest::class.java, MemberDataConsultationRequest::class.java).toXMLByteArray(request).toString(Charsets.UTF_8)
                },
                errors = it.response.status?.statusDetail?.anies?.map {
                    FaultType().apply {
                        faultCode = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "FaultCode").item(0)?.textContent
                        faultSource = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "FaultSource").item(0)?.textContent
                        message = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Message").item(0)?.let {
                            StringLangType().apply {
                                value = it.textContent
                                lang = it.attributes.getNamedItem("lang")?.textContent
                            }
                        }

                        it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Detail").let {
                            if (it.length > 0) {
                                details = DetailsType()
                            }
                            for (i in 0 until it.length) {
                                details.details.add(DetailType().apply {
                                    it.item(i).let {
                                        detailCode = (it as Element).getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "DetailCode").item(0)?.textContent
                                        detailSource = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "DetailSource").item(0)?.textContent
                                        location = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Location").item(0)?.textContent
                                        message = it.getElementsByTagNameWithOrWithoutNs("urn:be:cin:types:v1", "Message").item(0)?.let {
                                            StringLangType().apply {
                                                value = it.textContent
                                                lang = it.attributes.getNamedItem("lang")?.textContent
                                            }
                                        }
                                    }
                                })
                            }

                        }
                    }
                },
                commonOutput = it.consultationResponse?.`return`?.commonOutput
            )?.apply {
                this.errors?.forEach {
                    it.details?.details?.forEach { d ->
                        this.myCarenetErrors += extractError(unEncryptedQuery, code1, code2, d.location, d.detailCode).toList()
                    }
                }
            }
        } ?: MemberDataResponse()
    }

    private fun validateQuality(hcpQuality: String) {
        require(
            hcpQuality == "doctor" ||
                hcpQuality == "nurse" ||
                hcpQuality == "physiotherapist" ||
                hcpQuality == "dentist" ||
                hcpQuality == "logopedist" ||
                hcpQuality == "trussmaker" ||
                hcpQuality == "orthopedist" ||
                hcpQuality == "midwife" ||
                hcpQuality == "optician" ||
                hcpQuality == "podologist" ||
                hcpQuality == "dietician" ||
                hcpQuality == "hospital" ||
                hcpQuality == "groupofnurses" ||
                hcpQuality == "labo" ||
                hcpQuality == "retirement" ||
                hcpQuality == "otdpharmacy" ||
                hcpQuality == "medicalhouse" ||
                hcpQuality == "groupofdoctors" ||
                hcpQuality == "psychiatrichouse" ||
                hcpQuality == "guardpost" ||
                hcpQuality == "ambulanceservice"
        ) { "hcpQuality is invalid" }
    }

    private fun getAttrQuery(inputRef: String,
                             issueInstant: XMLGregorianCalendarImpl,
                             facets: List<Facet>?,
                             hospitalized: Boolean?,
                             requestType: String?,
                             hcpNihii: String,
                             patientSsin: String?,
                             io: String?,
                             ioMembership: String?,
                             startDate: Instant,
                             endDate: Instant) = AttributeQuery().apply {
        id = "_$inputRef"
        this.issueInstant = issueInstant
        this.version = "2.0"
        extensions = ExtensionsType().apply {
            this.facets.addAll(facets ?: listOf(
                Facet().apply {
                    id = "urn:be:cin:nippin:insurability"
                    dimensions.add(Facet.Dimension().apply {
                        id = "requestType"
                        value = if (requestType == "invoicing") "invoicing" else "information"
                    })
                    dimensions.add(Facet.Dimension().apply {
                        id = "contactType"; value =
                        if (hospitalized == true) "hospitalized" else "other"
                    })
                },
                Facet().apply {
                    id = "urn:be:cin:nippin:chronicCondition"
                },
                Facet().apply {
                    id = "urn:be:cin:nippin:carePath"
                    dimensions.add(Facet.Dimension().apply { id = "carePathType"; value = "diabetes" })
                    dimensions.add(Facet.Dimension().apply {
                        id = "carePathType"; value =
                        "renalinsufficiency"
                    })
                },
                Facet().apply {
                    id = "urn:be:cin:nippin:referencePharmacy"
                }))
        }
        issuer = NameIDType().apply {
            this.format = "urn:be:cin:nippin:nihii11"
            this.value = hcpNihii.padEnd(11, '0')
        }
        subject = Subject().apply {
            this.nameID = NameIDType().apply {
                when {
                    patientSsin != null && io == null -> {
                        format = "urn:be:fgov:person:ssin"
                        value = patientSsin
                    }
                    patientSsin != null && io != null -> {
                        format = "urn:be:cin:nippin:member:ssin@mut"
                        value = "$patientSsin@$io"
                    }
                    ioMembership != null && io != null -> {
                        format = "urn:be:cin:nippin:careReceiver:registrationNumber@mut"
                        value = "$ioMembership@$io"
                    }
                }
            }
            subjectConfirmations.add(SubjectConfirmation().apply {
                method = "urn:be:cin:nippin:memberIdentification"
                subjectConfirmationData = SubjectConfirmationDataType().apply {
                    startDate.let {
                        notBefore =
                            XMLGregorianCalendarImpl(DateTime(it.toEpochMilli(), DateTimeZone.forID(mcnTimezone)).toGregorianCalendar())
                    }
                    endDate.let {
                        notOnOrAfter =
                            XMLGregorianCalendarImpl(DateTime(it.toEpochMilli(), DateTimeZone.forID(mcnTimezone)).toGregorianCalendar())
                    }
                }
            })
        }
    }


    private fun extractError(sendTransactionRequest: ByteArray, code1: String?, code2: String?, errorUrl: String?, detailCode: String?): Set<MycarenetError> {
        //For some reason... The path starts with ../../../../ which corresponds to the request
        return errorUrl?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = false
            val builder = factory.newDocumentBuilder()

            val xpath = xPathFactory()
            val expr = xpath.compile(if (url.startsWith("/")) url else "/$url")
            val result = mutableSetOf<MycarenetError>()

            (expr.evaluate(
                builder.parse(ByteArrayInputStream(sendTransactionRequest)),
                XPathConstants.NODESET
            ) as NodeList).let { it ->
                if (it.length > 0) {
                    var node = it.item(0)
                    val textContent = if (node.hasChildNodes() && node.childNodes.length > 1) ConnectorXmlUtils.toString(node) else node.textContent
                    var base = "/" + nodeDescr(node)
                    while (node.parentNode != null && node.parentNode is Element) {
                        base = "/${nodeDescr(node.parentNode)}$base"
                        node = node.parentNode
                    }
                    var elements =
                        MemberDataErrors.values.filter { (it.path == null || it.path == base) && it.code == code1 && (code2 == null || it.subCode == code2) && (detailCode == null || it.detailCode == detailCode) }

                    if (elements.isEmpty()) {
                        val oBase = base.replace(Regex("\\[.+?\\]"), "")
                        elements =
                            MemberDataErrors.values.filter { (it.path == null || it.path == oBase) && it.code == code1 && (code2 == null || it.subCode == code2) && (detailCode == null || it.detailCode == detailCode) }
                    }

                    elements.forEach { it.value = textContent }
                    result.addAll(elements)
                } else {
                    result.add(
                        MycarenetError(
                            code = code1,
                            subCode = code2,
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

    private fun extractError(e: javax.xml.ws.soap.SOAPFaultException): Set<MycarenetError> {
        val result = mutableSetOf<MycarenetError>()

        e.fault.detail.detailEntries.forEach { it ->
            if (it != null) {
                val detailEntry = it as DetailEntry1_1Impl
                val codeElements = detailEntry.getElementsByTagName("Code")
                for (i in 0..(codeElements.length - 1)) {
                    val codeElement = codeElements?.item(i) as ElementImpl
                    result.addAll(MemberDataErrors.values.filter { it.code == codeElement.value })
                }
            }
        }
        return result
    }

    private fun nodeDescr(node: Node): String {
        val localName = node.localName ?: node.nodeName?.replace(Regex(".+?:(.+)"), "$1") ?: "unknown"
        val id = node.attributes?.getNamedItem("id")?.textContent

        return if (id != null) "$localName[$id]" else localName
    }

    private fun xPathFactory(): XPath {
        val xpath = xPathfactory.newXPath()
        xpath.namespaceContext = object : NamespaceContext {
            override fun getNamespaceURI(prefix: String?) = when (prefix) {
                else -> null
            }

            override fun getPrefix(namespaceURI: String?) = when (namespaceURI) {
                else -> null
            }

            override fun getPrefixes(namespaceURI: String?): Iterator<Any?> =
                when (namespaceURI) {
                    else -> listOf<String>().iterator()
                }
        }
        return xpath
    }
}

private fun Element.getElementsByTagNameWithOrWithoutNs(ns: String, name: String): NodeList {
    return this.getElementsByTagNameNS(ns, name).let { if (it.length > 0) it else this.getElementsByTagName(name) }
}
