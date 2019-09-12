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

package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.consent.ConsentMessageDto
import java.util.*

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsentControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null
    private val gson: Gson = Gson()

    @Test
    fun getConsent() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val consent =
            this.restTemplate.exchange(
                "http://localhost:$port/consent/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}",
                HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
                firstName1,
                lastName1
            ).body
        assertThat(consent != null)
        val msg = gson.fromJson(consent, ConsentMessageDto::class.java)
        assertThat(msg.complete).isTrue()
    }

    @Test
    fun revokeConsent() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val consent =
            gson.fromJson(
                this.restTemplate.exchange(
                    "http://localhost:$port/consent/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}",
                    HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
                    firstName1,
                    lastName1,
                    passPhrase
                ).body, ConsentMessageDto::class.java
            )
        val revoke =
            this.restTemplate.exchange(
                "http://localhost:$port/consent/revoke/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}",
                HttpMethod.POST, HttpEntity(consent.consent, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
                firstName1,
                lastName1
            ).body
        assertThat(revoke != null)
        val msg = gson.fromJson(revoke, ConsentMessageDto::class.java)
        assertThat(msg.complete).isTrue()
        //Reestablish
        this.restTemplate.exchange(
            "http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}",
            HttpMethod.POST, HttpEntity(null, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
            String::class.java,
            firstName1,
            lastName1
        ).body
    }

    @Test
    fun registerConsent() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        //First revoke
        val consent =
            gson.fromJson(
                this.restTemplate.exchange(
                    "http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}",
                    HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
                    firstName1,
                    lastName1
                ).body, ConsentMessageDto::class.java
            )
        val revoke =
            this.restTemplate.exchange(
                "http://localhost:$port/consent/revoke/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}",
                HttpMethod.POST, HttpEntity(consent.consent, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
                String::class.java,
                firstName1,
                lastName1
            ).body

        val newConsent =
            this.restTemplate.exchange(
                "http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}",
                HttpMethod.POST, HttpEntity(null, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java,
                String::class.java,
                firstName1,
                lastName1
            ).body
        assertThat(newConsent != null)
        val msg = gson.fromJson(newConsent, ConsentMessageDto::class.java)
        assertThat(msg.complete).isTrue()
    }
}
