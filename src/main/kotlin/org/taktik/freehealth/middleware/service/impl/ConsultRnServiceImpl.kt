package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.commons._1_0.core.PeriodType
import be.fgov.ehealth.consultrn._1_0.core.EncodedSSINType
import be.fgov.ehealth.consultrn._1_0.core.ErrorType
import be.fgov.ehealth.consultrn._1_0.core.GenderEnumType
import be.fgov.ehealth.consultrn._1_0.core.GenderType
import be.fgov.ehealth.consultrn._1_0.core.InscriptionType
import be.fgov.ehealth.consultrn._1_0.protocol.PhoneticCriteriaType
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest
import be.fgov.ehealth.consultrn.commons.core.v3.BirthRequestType
import be.fgov.ehealth.consultrn.commons.core.v3.MiddleNameType
import be.fgov.ehealth.consultrn.commons.core.v3.NameType
import be.fgov.ehealth.consultrn.commons.core.v3.NationalitiesType
import be.fgov.ehealth.consultrn.commons.core.v3.NationalityType
import be.fgov.ehealth.consultrn.commons.core.v3.PersonNameRequestType
import be.fgov.ehealth.consultrn.commons.core.v3.PersonRequestType
import be.fgov.ehealth.consultrn.commons.core.v3.ResidentialAddressRequestType
import be.fgov.ehealth.consultrn.commons.core.v3.WhereRequestType
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.springframework.stereotype.Service
import org.taktik.connector.business.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterPersonException
import org.taktik.connector.business.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException
import org.taktik.connector.business.consultrn.service.impl.ConsultrnServiceImpl
import org.taktik.connector.business.ssinhistory.service.impl.SsinHistoryTokenServiceImpl
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import org.taktik.freehealth.middleware.dto.consultrn.RegisterPersonResponseDto
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.ConsultRnService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@Service
class ConsultRnServiceImpl(private val stsService: STSService) : ConsultRnService {
    private val log = LogFactory.getLog(this.javaClass)
    val backingService = ConsultrnServiceImpl(EhealthReplyValidatorImpl())
    val historyService = SsinHistoryTokenServiceImpl(EhealthReplyValidatorImpl())

    override fun identify(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): SearchBySSINReply {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        return try {
            backingService.search(samlToken, SearchBySSINRequest().apply {
                applicationID = "0"
                inscription = InscriptionType().apply {
                    this.ssin = EncodedSSINType().apply {
                        value = ssin
                    }
                    qualityCode = "6"
                    period = PeriodType().apply {
                        val begin =
                            DateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0)
                                .withZoneRetainFields(DateTimeZone.UTC)
                        beginDate = begin
                        endDate = begin.plusYears(2)
                    }
                }
            })
        } catch (ex: ConsultrnIdentifyPersonException) {
            ex.searchBySSINReply
        }
    }

    override fun history(keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String): ConsultCurrentSsinResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        return historyService.consultCurrentSsin(samlToken, ConsultCurrentSsinRequest().apply {
            id = "ID${System.currentTimeMillis()}"
            issueInstant = DateTime.now()
            this.ssin = ssin
        })
    }

    override fun search(keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        dateOfBirth: Int,
        lastName: String,
        firstName: String?,
        middleName: String?,
        gender: String,
        tolerance: Int,
        limit: Int): SearchPhoneticReply {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        return try {
            backingService.search(samlToken, SearchPhoneticRequest().apply {
                applicationID = "0"
                phoneticCriteria = PhoneticCriteriaType().apply {
                    this.lastName = lastName
                    this.firstName = firstName
                    this.middleName = middleName
                    this.birthDate = dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                    this.gender = (try {
                        GenderEnumType.fromValue(gender)
                    } catch (e: Exception) {
                        null
                    })?.let { GenderType().apply { value = it } }
                    this.maximum = limit.toBigInteger()
                    this.tolerance = tolerance.toBigInteger()
                }
            })
        } catch (ex: ConsultrnPhoneticSearchException) {
            ex.searchPhoneticReply
        }
    }

    override fun registerPerson(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        mid: PersonMid
                               ): RegisterPersonResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for GMD operations")

        return backingService.registerPerson(samlToken, RegisterPersonRequest().apply {
            id = "ID${System.currentTimeMillis()}"
            applicationID = "0"
            issueInstant = DateTime.now()
            person = PersonRequestType().apply {
                name = PersonNameRequestType().apply {
                    this.lastName = mid.lastName
                    this.firstName = mid.firstName
                }
                birth = BirthRequestType().apply {
                    birthDate = mid.dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                    birthPlace = mid.birthPlace?.let {
                        WhereRequestType().apply {
                            countryCode = it.countryCode
                            cityCode = it.cityCode
                            it.cityName?.let { cityNames.add(NameType().apply { value = it }) }
                        }
                    }
                }
                mid.nationalityCode?.let {
                    nationalities =
                        NationalitiesType().apply {
                            nationalities.add(NationalityType().apply {
                                nationalityCode =
                                    it
                            })
                        }
                }
                this.gender =
                    mid.gender?.let {
                        be.fgov.ehealth.consultrn.commons.core.v3.GenderType()
                            .apply { genderCode = it.substring(0, 1).toUpperCase() }
                    }
                this.residentialAddress = mid.residentialAddress?.let {
                    ResidentialAddressRequestType().apply {
                        countryCode = it.countryCode
                        cityCode = it.cityCode
                        postalCode = it.postalCode
                        it.cityName?.let { cityNames.add(NameType().apply { value = it }) }
                        it.streetName?.let { streetNames.add(NameType().apply { value = it }) }
                    }
                }
            }
        })

    }
}
