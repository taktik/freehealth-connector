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

package org.taktik.freehealth.middleware.mapper

import be.fgov.ehealth.genericinsurability.core.v1.HospitalizedType
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityItemType
import be.fgov.ehealth.genericinsurability.core.v1.MedicalHouseType
import be.fgov.ehealth.genericinsurability.core.v1.PeriodType
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse
import org.taktik.freehealth.middleware.dto.genins.HospitalizedInfoDto
import org.taktik.freehealth.middleware.dto.genins.MedicalHouseInfoDto
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.dto.genins.InsurabilityItemDto
import org.taktik.freehealth.middleware.dto.genins.PeriodDto
import org.taktik.freehealth.middleware.dto.genins.TransferDto
import org.taktik.freehealth.utils.FuzzyValues

fun GetInsurabilityResponse.toInsurabilityInfoDto(): InsurabilityInfoDto =
    InsurabilityInfoDto(inss = response.careReceiverDetail?.inss,
                        firstName = response.careReceiverDetail?.firstName,
                        lastName = response.careReceiverDetail?.lastName,
                        dateOfBirth = response.careReceiverDetail?.birthday?.let { FuzzyValues.getFuzzyDate(it) },
                        deceased = response.careReceiverDetail?.deceased?.let { FuzzyValues.getFuzzyDate(it) },
                        sex = response.careReceiverDetail?.sex?.value(),
                        hospitalizedInfo = response?.insurabilityResponseDetail?.hospitalized?.toHospitalizedInfoDto(),
                        medicalHouseInfo = response?.insurabilityResponseDetail?.medicalHouse?.toMedicalHouseInfoDto(),
                        transfers = response?.insurabilityResponseDetail?.generalSituation?.transfers?.map { TransferDto().apply { direction = it.direction?.value(); io = it.insuranceOrg; date = FuzzyValues.getFuzzyDate(it.transferDate) }} ?: listOf(),
                        insurabilities = response?.insurabilityResponseDetail?.insurabilityList?.insurabilityItems?.map { it.toInsurabilityInfoDto() } ?: listOf(),
                        generalSituation = response?.insurabilityResponseDetail?.generalSituation?.event?.value(),
                        faultMessage = response.messageFault?.details?.details?.joinToString("\n"),
                        faultSource = response.messageFault?.faultSource,
                        faultCode = response.messageFault?.faultCode?.value(),
                        paymentByIo = response?.insurabilityResponseDetail?.payment?.isPaymentByIo ?: false,
                        specialSocialCategory = response?.insurabilityResponseDetail?.payment?.isSpecialSocialCategory
                            ?: false)

fun InsurabilityItemType.toInsurabilityInfoDto(): InsurabilityItemDto = InsurabilityItemDto(
    regNrWithMut = regNrWithMut,
    mutuality = mutuality,
    ct1 = cT1,
    ct2 = cT2,
    paymentApproval = paymentApproval,
    insurabilityDate = insurabilityDate?.let { FuzzyValues.getFuzzyDate(it) },
    period = period?.toPeriodDto()
)

private fun PeriodType.toPeriodDto(): PeriodDto? =
    PeriodDto(periodStart = periodStart?.let { FuzzyValues.getFuzzyDate(it) },
              periodEnd = periodEnd?.let { FuzzyValues.getFuzzyDate(it) })

fun HospitalizedType.toHospitalizedInfoDto(): HospitalizedInfoDto = HospitalizedInfoDto(
    hospital = hospital,
    admissionDate = admissionDate?.let { FuzzyValues.getFuzzyDate(it) },
    admissionService = admissionService
)

fun MedicalHouseType.toMedicalHouseInfoDto(): MedicalHouseInfoDto =
    MedicalHouseInfoDto(periodStart = periodStart?.let { FuzzyValues.getFuzzyDate(it) },
                        periodEnd = periodEnd?.let { FuzzyValues.getFuzzyDate(it) },
                        isNurse = isNurse,
                        isMedical = isMedical,
                        isKine = isKine
    )
