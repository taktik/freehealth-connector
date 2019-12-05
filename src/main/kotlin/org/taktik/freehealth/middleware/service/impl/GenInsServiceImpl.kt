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

import be.fgov.ehealth.genericinsurability.core.v1.*
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType.AMBULATORY_CARE
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType.HOSPITALIZED_ELSEWHERE
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestTypeType.INFORMATION
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse
import com.google.gson.Gson
import com.sun.xml.messaging.saaj.soap.impl.ElementImpl
import com.sun.xml.messaging.saaj.soap.ver1_1.DetailEntry1_1Impl
import ma.glasnost.orika.MapperFacade
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.PropertyUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.mapper.toInsurabilityInfoDto
import org.taktik.freehealth.middleware.service.GenInsService
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.math.BigDecimal
import java.time.Instant
import java.util.*
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@Service
class GenInsServiceImpl(val stsService: STSService, val mapper: MapperFacade) : GenInsService {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val freehealthGenInsService: org.taktik.connector.business.genins.service.GenInsService =
        org.taktik.connector.business.genins.service.impl.GenInsServiceImpl()
    private val GenInsErrors =
        Gson().fromJson(
            this.javaClass.getResourceAsStream("/be/errors/GenInsErrors.json").reader(Charsets.UTF_8),
            arrayOf<MycarenetError>().javaClass
        ).associateBy({ it.uid }, { it })
    private val xPathfactory = XPathFactory.newInstance()
    private val config = ConfigFactory.getConfigValidator(listOf())

    override fun getGeneralInsurabity(
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
        startDate: Instant?,
        endDate: Instant?,
        hospitalized: Boolean
    ): InsurabilityInfoDto {
        require(
            hcpQuality.equals("doctor") ||
                hcpQuality.equals("nurse") ||
                hcpQuality.equals("physiotherapist") ||
                hcpQuality.equals("dentist") ||
                hcpQuality.equals("logopedist") ||
                hcpQuality.equals("trussmaker") ||
                hcpQuality.equals("orthopedist") ||
                hcpQuality.equals("midwife") ||
                hcpQuality.equals("optician") ||
                hcpQuality.equals("podologist") ||
                hcpQuality.equals("dietician") ||
                hcpQuality.equals("hospital") ||
                hcpQuality.equals("groupofnurses") ||
                hcpQuality.equals("labo") ||
                hcpQuality.equals("retirement") ||
                hcpQuality.equals("otdpharmacy") ||
                hcpQuality.equals("medicalhouse") ||
                hcpQuality.equals("groupofdoctors") ||
                hcpQuality.equals("psychiatrichouse") ||
                hcpQuality.equals("guardpost") ||
                hcpQuality.equals("ambulanceservice")
        ) { "hcpQuality is invalid" }

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Genins operations")
        assert(patientSsin != null || io != null && ioMembership != null)

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo("genins", principal?.mcnLicense, principal?.mcnPassword)

        log.info("getGeneralInsurability called with principal "+(principal?:"<ANONYMOUS>")+" and license " + (principal?.mcnLicense ?: "<DEFAULT>"))

        val request = GetInsurabilityAsXmlOrFlatRequestType().apply {
            recordCommonInput =
                RecordCommonInputType().apply {
                    inputReference =
                        BigDecimal(IdGeneratorFactory.getIdGenerator().generateId())
                }
            commonInput = CommonInputType().apply {
                request =
                    RequestType().apply { isIsTest = false /*config.getProperty("endpoint.genins")?.contains("-acpt") ?: false*/ }
                inputReference = "" + IdGeneratorFactory.getIdGenerator().generateId()
                origin = OriginType().apply {
                    `package` = PackageType().apply {
                        license = LicenseType().apply {
                            username = packageInfo.userName
                            password = packageInfo.password
                        }
                        name = ValueRefString().apply { value = packageInfo.packageName }
                    }
                    config.getProperty("mycarenet.${PropertyUtil.retrieveProjectNameToUse("genins","mycarenet.")}.site.id")?.let{
                        if (it.isNotBlank()) {
                            siteID = ValueRefString().apply { value = it }
                        }
                    }
                    careProvider = CareProviderType().apply {
                        if(hcpQuality == "guardpost") {
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
                        }else{
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
            request = SingleInsurabilityRequestType().apply {
                insurabilityRequestDetail = InsurabilityRequestDetailType().apply {
                    insurabilityRequestType = INFORMATION
                    careReceiverId = CareReceiverIdType().apply {
                        inss = patientSsin
                        mutuality = io
                        regNrWithMut = ioMembership
                    }
                    insurabilityContactType = if (hospitalized) HOSPITALIZED_ELSEWHERE else AMBULATORY_CARE
                    insurabilityReference = "" + System.currentTimeMillis()
                    period = PeriodType().apply {
                        periodStart = startDate?.let { DateTime(it.toEpochMilli(), DateTimeZone.forID(mcnTimezone)) } ?: DateTime()
                        periodEnd = endDate?.let { DateTime(it.toEpochMilli(), DateTimeZone.forID(mcnTimezone)) } ?: periodStart
                    }
                }
            }
        }

        return try {
            val kmehrRequestMarshaller =
                MarshallerHelper(
                    GetInsurabilityAsXmlOrFlatRequestType::class.java,
                    GetInsurabilityAsXmlOrFlatRequestType::class.java
                                )
            val xmlData = kmehrRequestMarshaller.toXMLByteArray(request)

            if (log.isDebugEnabled) {
                log.debug("Genins request: {}", xmlData.toString(Charsets.UTF_8))
            }

            val genInsResponse = freehealthGenInsService.getInsurability(samlToken, request)
            val genInsResponseDTO = genInsResponse.toInsurabilityInfoDto()

            genInsResponseDTO.errors = genInsResponse.response.messageFault?.details?.details?.flatMap { extractError(xmlData, it.detailCode, it.location).toList() } ?: listOf()

            val commonOutput = CommonOutput(
                genInsResponse?.commonOutput?.inputReference?.toString(),
                genInsResponse?.commonOutput?.nipReference?.toString(),
                genInsResponse?.commonOutput?.outputReference?.toString()
            )

            genInsResponseDTO?.apply {
                this.commonOutput = commonOutput
                this.mycarenetConversation = MycarenetConversation().apply {
                    genInsResponse.soapRequest?.writeTo(this.soapRequestOutputStream())
                    genInsResponse.soapResponse?.writeTo(this.soapResponseOutputStream())
                    this.transactionRequest = xmlData.toString(Charsets.UTF_8)
                    this.transactionResponse = MarshallerHelper(GetInsurabilityResponse::class.java, GetInsurabilityResponse::class.java).toXMLByteArray(genInsResponse).toString(Charsets.UTF_8)
                }
            }

            return genInsResponseDTO

        } catch (e: javax.xml.ws.soap.SOAPFaultException) {
            InsurabilityInfoDto(
                faultMessage = e.fault.faultString,
                faultSource = e.message,
                faultCode = e.fault?.faultCode,
                transfers = listOf(),
                errors = extractError(e).toList()
            )
        }
    }

    private fun extractError(sendTransactionRequest: ByteArray, ec: String, errorUrl: String?): Set<MycarenetError> {
        //For some reason... The path starts with ../../../../ which corrsponds to the request
        return errorUrl?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = true
            val builder = factory.newDocumentBuilder()

            val xpath = xPathFactory()
            val expr = xpath.compile(url.replace(Regex("^\\.\\./\\.\\./\\.\\./\\.\\./"),"/gip:GetInsurabilityAsXmlOrFlatRequestType/gip:Request/")
                                         .replace(Regex("/(CareReceiverId|Inss|RegNrWithMut|Mutuality|InsurabilityRequestDetail|InsurabilityRequestType|Period|PeriodStart|PeriodEnd|InsurabilityContactType|InsurabilityReference)"),"/gic:$1"))
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
                        GenInsErrors.values.filter {(it.path == null || it.path == base) && it.code == ec }
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

    private fun extractError(e: javax.xml.ws.soap.SOAPFaultException): Set<MycarenetError> {
        val result = mutableSetOf<MycarenetError>()

        e.fault.detail.detailEntries.forEach { it ->
            if(it != null) {
                val detailEntry = it as DetailEntry1_1Impl
                val codeElements = detailEntry.getElementsByTagName("Code")
                for (i in 0..(codeElements.length - 1)){
                    val codeElement = codeElements?.item(i) as ElementImpl
                    result.addAll(GenInsErrors.values.filter { it.code == codeElement.value })
                }
            }
        }
        return result
    }

    private fun nodeDescr(node: Node): String {
        val localName = node.localName ?: node.nodeName?.replace(Regex(".+?:(.+)"), "$1") ?: "unknown"

        return localName
    }

    private fun xPathFactory(): XPath {
        val xpath = xPathfactory.newXPath()
        xpath.namespaceContext = object : NamespaceContext {
            override fun getNamespaceURI(prefix: String?) = when (prefix) {
                "gic" -> "urn:be:fgov:ehealth:genericinsurability:core:v1"
                "gip" -> "urn:be:fgov:ehealth:genericinsurability:protocol:v1"
                else -> null
            }

            override fun getPrefix(namespaceURI: String?) = when (namespaceURI) {
                "urn:be:fgov:ehealth:genericinsurability:core:v1" -> "gic"
                "urn:be:fgov:ehealth:genericinsurability:protocol:v1" -> "gip"
                else -> null
            }

            override fun getPrefixes(namespaceURI: String?): Iterator<Any?> =
                when (namespaceURI) {
                    "urn:be:fgov:ehealth:genericinsurability:core:v1" -> listOf("gic").iterator()
                    "urn:be:fgov:ehealth:genericinsurability:protocol:v1" -> listOf("gip").iterator()
                    else -> listOf<String>().iterator()
                }
        }
        return xpath
    }
}
