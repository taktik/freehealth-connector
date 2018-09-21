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

import be.fgov.ehealth.genericinsurability.core.v1.CareProviderType
import be.fgov.ehealth.genericinsurability.core.v1.CareReceiverIdType
import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType
import be.fgov.ehealth.genericinsurability.core.v1.IdType
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType.AMBULATORY_CARE
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType.HOSPITALIZED_ELSEWHERE
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestDetailType
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestTypeType.INFORMATION
import be.fgov.ehealth.genericinsurability.core.v1.LicenseType
import be.fgov.ehealth.genericinsurability.core.v1.NihiiType
import be.fgov.ehealth.genericinsurability.core.v1.OriginType
import be.fgov.ehealth.genericinsurability.core.v1.PackageType
import be.fgov.ehealth.genericinsurability.core.v1.PeriodType
import be.fgov.ehealth.genericinsurability.core.v1.RecordCommonInputType
import be.fgov.ehealth.genericinsurability.core.v1.RequestType
import be.fgov.ehealth.genericinsurability.core.v1.SingleInsurabilityRequestType
import be.fgov.ehealth.genericinsurability.core.v1.ValueRefString
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType
import ma.glasnost.orika.MapperFacade
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.PropertyUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.mapper.toInsurabilityInfoDto
import org.taktik.freehealth.middleware.service.GenInsService
import org.taktik.freehealth.middleware.service.STSService
import java.math.BigDecimal
import java.util.*

@Service
class GenInsServiceImpl(val stsService: STSService, val mapper: MapperFacade) : GenInsService {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val freehealthGenInsService: org.taktik.connector.business.genins.service.GenInsService =
        org.taktik.connector.business.genins.service.impl.GenInsServiceImpl()
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
        startDate: Date?,
        endDate: Date?,
        hospitalized: Boolean
    ): InsurabilityInfoDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Genins operations")
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
                    siteID =
                        ValueRefString().apply {
                            value =
                                config.getProperty(
                                    "mycarenet.${PropertyUtil.retrieveProjectNameToUse(
                                        "genins",
                                        "mycarenet."
                                    )}.site.id"
                                )
                        }
                    careProvider = CareProviderType().apply {
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
                        periodStart = startDate?.let { DateTime(it.time) } ?: DateTime()
                        periodEnd = endDate?.let { DateTime(it.time) } ?: periodStart
                    }
                }
            }
        }

        return try {
            if (log.isDebugEnabled) {
                val kmehrRequestMarshaller =
                    MarshallerHelper(
                        GetInsurabilityAsXmlOrFlatRequestType::class.java,
                        GetInsurabilityAsXmlOrFlatRequestType::class.java
                    )
                val xmlString = kmehrRequestMarshaller.toXMLByteArray(request).toString(Charsets.UTF_8)
                log.debug("Genins request: {}", xmlString)
            }

            freehealthGenInsService.getInsurability(samlToken, request).toInsurabilityInfoDto()
        } catch (e: javax.xml.ws.soap.SOAPFaultException) {
            InsurabilityInfoDto(
                faultMessage = e.fault.faultString,
                faultSource = e.message,
                faultCode = e.fault?.faultCode,
                transfers = listOf()
                               )
        }
    }
}
