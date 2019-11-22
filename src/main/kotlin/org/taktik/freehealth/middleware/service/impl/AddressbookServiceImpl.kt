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

import be.fgov.ehealth.addressbook.core.v1.IndividualContactInformationType
import be.fgov.ehealth.addressbook.core.v1.OrganizationContactInformationType
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.AddressType
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.Telecom
import org.taktik.freehealth.middleware.dto.TelecomType
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.AddressbookService
import org.taktik.freehealth.middleware.service.STSService
import java.util.*

@Service
class AddressbookServiceImpl(val stsService: STSService) : AddressbookService {
    private val freehealthTokenAddressbookService: org.taktik.connector.business.addressbook.service.AddressbookTokenService =
        org.taktik.connector.business.addressbook.service.impl.AddressbookTokenServiceImpl(
            EhealthReplyValidatorImpl()
        )

    override fun searchHcp(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        queryLastName: String,
        queryFirstName: String?,
        type: String
    ): List<HealthcareParty> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val searchProfessionals =
            freehealthTokenAddressbookService.searchProfessionals(samlToken, SearchProfessionalsRequest().apply {
                firstName = queryFirstName; lastName = queryLastName; issueInstant = DateTime.now(); profession = type
                offset = 0; maxElements = 100
            })
        return searchProfessionals.healthCareProfessionals.filter {
            queryFirstName == null || it.firstName != null && it.firstName.startsWith(
                queryFirstName,
                true
            )
        }.map {
            HealthcareParty(firstName = it.firstName,
                            lastName = it.lastName,
                            ssin = it.ssin,
                            nihii = (it.professions.find { it.professionCodes.any { it.value == "PHYSICIAN" } }
                                ?: it.professions.firstOrNull())?.nihii,
                            gender = Gender.fromCode(it.gender) ?: Gender.unknown)
        }
    }

    override fun searchOrg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        name: String,
        type: String
    ): List<HealthcareParty> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val searchProfessionals =
            freehealthTokenAddressbookService.searchOrganizations(samlToken, SearchOrganizationsRequest().apply {
                institutionName = name; issueInstant = DateTime.now(); institutionType = type
                offset = 0; maxElements = 100
            })
        return searchProfessionals.healthCareOrganizations.map {
            HealthcareParty(
                name = it.names.joinToString { it.value },
                nihii = if (it.id.type == "NIHII") it.id.value else null,
                cbe = if (it.id.type == "CBE") it.id.value else null,
                ehp = if (it.id.type == "HCI") it.id.value else null
            )
        }
    }

    override fun getHcp(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        nihii: String?,
        ssin: String?,
        language: String
    ): HealthcareParty? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val professionalContactInfo =
            freehealthTokenAddressbookService.getProfessionalContactInfo(
                samlToken,
                GetProfessionalContactInfoRequest().apply {
                    this.nihii = nihii; this.ssin = ssin; issueInstant = DateTime.now()
                })
        return professionalContactInfo.individualContactInformation?.let { makeHealthcareParty(it, language) }
    }

    override fun getOrg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ehp: String?,
        cbe: String?,
        nihii: String?,
        language: String
    ): HealthcareParty? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val professionalContactInfo =
            freehealthTokenAddressbookService.getOrganizationContactInfo(
                samlToken,
                GetOrganizationContactInfoRequest().apply {
                    this.nihii = nihii; this.cbe = cbe; this.ehp = ehp; issueInstant = DateTime.now()
                })
        return professionalContactInfo.organizationContactInformation?.let { makeHealthcareParty(it, language) }
    }

    private fun makeHealthcareParty(it: IndividualContactInformationType, language: String): HealthcareParty {
        val professionalInformation =
            it.professionalInformations.find {
                it.profession?.professionCodes?.any { it.value == "PHYSICIAN" } ?: false
            } ?: it.professionalInformations.firstOrNull()
        return HealthcareParty(
            firstName = it.firstName,
            lastName = it.lastName,
            ssin = it.ssin,
            gender = Gender.fromCode(it.gender) ?: Gender.unknown,
            nihii = professionalInformation?.profession?.nihii
        ).apply {
            addresses.addAll(professionalInformation?.addresses?.map {
                Address(addressType = AddressType.work,
                        street = (it.street.descriptions.find { it.lang == language }
                            ?: it.street.descriptions.firstOrNull())?.value,
                        houseNumber = it.houseNumber,
                        postboxNumber = it.postBox,
                        country = (it.country.descriptions.find { it.lang == language }
                            ?: it.country.descriptions.firstOrNull())?.value,
                        postalCode = it.municipality?.zipCode?.toString(),
                        city = (it.municipality.descriptions.find { it.lang == language }
                            ?: it.municipality.descriptions.firstOrNull())?.value).apply {
                    telecoms.addAll(professionalInformation.healthCareAdditionalInformations?.filter { it.type == "Mail" }?.map {
                        Telecom(
                            telecomType = TelecomType.email,
                            telecomNumber = it.value
                        )
                    } ?: listOf())
                }
            } ?: listOf())
        }
    }

    private fun makeHealthcareParty(org: OrganizationContactInformationType, language: String): HealthcareParty {
        return HealthcareParty(name = org.names.find { language == it.lang }?.value
            ?: org.names.joinToString { it.value },
                               ehp = if (org.id.type == "HCI") org.id.value else null,
                               type = org.organizationTypeCodes?.find { it.type == "code" }?.value
        ).apply {
            addresses.addAll(org.addresses?.map {
                Address(addressType = AddressType.work,
                        street = (it.street.descriptions.find { it.lang == language }
                            ?: it.street.descriptions.firstOrNull())?.value,
                        houseNumber = it.houseNumber,
                        postboxNumber = it.postBox,
                        country = (it.country.descriptions.find { it.lang == language }
                            ?: it.country.descriptions.firstOrNull())?.value,
                        postalCode = it.municipality?.zipCode?.toString(),
                        city = (it.municipality.descriptions.find { it.lang == language }
                            ?: it.municipality.descriptions.firstOrNull())?.value).apply {
                    telecoms.addAll(org.healthCareAdditionalInformations?.filter { it.type == "Mail" }?.map {
                        Telecom(
                            telecomType = TelecomType.email,
                            telecomNumber = it.value
                        )
                    } ?: listOf())
                }
            } ?: listOf())
        }
    }
}
