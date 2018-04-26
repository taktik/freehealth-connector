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

package org.taktik.freehealth.middleware

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import ma.glasnost.orika.CustomConverter
import ma.glasnost.orika.MapperFacade
import ma.glasnost.orika.impl.DefaultMapperFactory
import ma.glasnost.orika.metadata.Type
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import org.taktik.freehealth.middleware.dto.common.KmehrId
import org.w3._2005._05.xmlmime.Base64Binary
import java.time.Instant
import java.util.*

@Configuration
class MapperConfiguration {
	@Bean
	fun mapper(): MapperFacade? {
		val factory = DefaultMapperFactory.Builder().build()

		val converterFactory = factory.getConverterFactory()
		converterFactory.registerConverter(object : CustomConverter<LocalDate, Long>() {
			override fun convert(source: LocalDate, destinationType: Type<out Long>): Long? {
				return source.yearOfEra * 10000L + source.monthOfYear * 100L + source.dayOfMonth
			}
		})

		converterFactory.registerConverter(object : CustomConverter<Long, LocalDate>() {
			override fun convert(source: Long, destinationType: Type<out LocalDate>): LocalDate? {
				return LocalDate((source / 10000).toInt(), ((source / 100) % 100).toInt(), (source % 100).toInt())
			}
		})


		converterFactory.registerConverter(object : CustomConverter<Instant, Long>() {
			override fun convert(source: Instant, destinationType: Type<out Long>): Long? {
				return source.toEpochMilli()
			}
		})

		converterFactory.registerConverter(object : CustomConverter<Long, Instant>() {
			override fun convert(source: Long?, destinationType: Type<out Instant>): Instant {
				return Instant.ofEpochMilli(source!!)
			}
		})

		converterFactory.registerConverter(object : CustomConverter<be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY, KmehrId>() {
			override fun convert(source: be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY, destinationType: Type<out KmehrId>): KmehrId {
				return KmehrId().apply {
					s = source.s.value()
					sl = source.sl
					sv = source.sv
					value = source.value
				}
			}
		})

		converterFactory.registerConverter(object : CustomConverter<KmehrId, be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY>() {
			override fun convert(source: KmehrId, destinationType: Type<out be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY>): be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY {
				return be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY().apply {
					s = IDHCPARTYschemes.fromValue(source.s)
					sl = source.sl
					sv = source.sv
					value = source.value
				}
			}
		})

		converterFactory.registerConverter(object : CustomConverter<be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY, KmehrCd>() {
			override fun convert(source: be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY, destinationType: Type<out KmehrCd>): KmehrCd {
				return KmehrCd().apply {
					s = source.s.value()
					sl = source.sl
					sv = source.sv
					value = source.value
				}
			}
		})

		converterFactory.registerConverter(object : CustomConverter<KmehrCd, be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY>() {
			override fun convert(source: KmehrCd, destinationType: Type<out be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY>): be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY {
				return be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY().apply {
					s = CDHCPARTYschemes.fromValue(source.s)
					sl = source.sl
					sv = source.sv
					value = source.value
				}
			}
		})

		converterFactory.registerConverter(object : CustomConverter<AddressType, Address>() {
			override fun convert(source: AddressType, destinationType: Type<out Address>): Address {
				return Address(
						addressType = source.cds.firstOrNull()?.value?.let { org.taktik.freehealth.middleware.dto.AddressType.valueOf(it.toLowerCase()) }
								?: org.taktik.freehealth.middleware.dto.AddressType.home,
						street = source.street,
						houseNumber = source.housenumber,
						postboxNumber = source.postboxnumber,
						postalCode = source.zip,
						city = source.city,
						country = source.country?.cd?.value
				)
			}
		})

		converterFactory.registerConverter(object : CustomConverter<CountryType, String>() {
			override fun convert(source: CountryType, destinationType: Type<out String>): String {
				return source.cd.value
			}
		})

		converterFactory.registerConverter(object : CustomConverter<Instant, Instant>() {
			override fun convert(source: Instant, destinationType: Type<out Instant>): Instant {
				return Instant.ofEpochSecond(source.epochSecond, source.nano.toLong())
			}
		})
		converterFactory.registerConverter(object : CustomConverter<org.w3._2005._05.xmlmime.Base64Binary, String>() {
			override fun convert(base64Binary: Base64Binary, type: Type<out String>): String {
				return Base64.getEncoder().encodeToString(base64Binary.value)
			}
		})

		converterFactory.registerConverter(object : CustomConverter<DateTime, Instant>() {
			override fun convert(source: DateTime, destinationType: Type<out Instant>): Instant {
				return Instant.ofEpochMilli(source.millis)
			}
		})

		converterFactory.registerConverter(object : CustomConverter<DateTime, Long>() {
			override fun convert(source: DateTime, destinationType: Type<out Long>): Long? {
				return source.millis
			}
		})

		return factory.mapperFacade
	}
}