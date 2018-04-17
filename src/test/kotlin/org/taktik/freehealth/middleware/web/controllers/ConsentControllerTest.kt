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
import com.google.gson.reflect.TypeToken
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.consent.ConsentMessageDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import java.util.*

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsentControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null
    private val gson : Gson = Gson()

    @Test
    fun getConsent() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val consent = this.restTemplate.getForObject("http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&passPhrase={passPhrase}", String::class.java, firstName1, lastName1, passPhrase)
        assertThat(consent != null)
        val msg = gson.fromJson(consent, ConsentMessageDto::class.java)
        assertThat(msg.complete).isTrue()
    }

    @Test
    fun revokeConsent() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val consent = gson.fromJson(this.restTemplate.getForObject("http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&passPhrase={passPhrase}", String::class.java, firstName1, lastName1, passPhrase), ConsentMessageDto::class.java)
        val revoke = this.restTemplate.postForObject("http://localhost:$port/consent/revoke/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}", consent.consent, String::class.java, firstName1, lastName1, passPhrase)
        assertThat(revoke != null)
        val msg = gson.fromJson(revoke, ConsentMessageDto::class.java)
        assertThat(msg.complete).isTrue()
        //Reestablish
        this.restTemplate.postForObject("http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}", null, String::class.java, firstName1, lastName1, passPhrase)
    }

    @Test
    fun registerConsent() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        //First revoke
        val consent = gson.fromJson(this.restTemplate.getForObject("http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&passPhrase={passPhrase}", String::class.java, firstName1, lastName1, passPhrase), ConsentMessageDto::class.java)
        val revoke = this.restTemplate.postForObject("http://localhost:$port/consent/revoke/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}", consent.consent, String::class.java, firstName1, lastName1, passPhrase)

        val newConsent = this.restTemplate.postForObject("http://localhost:$port/consent/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}", null, String::class.java, firstName1, lastName1, passPhrase)
        assertThat(newConsent != null)
        val msg = gson.fromJson(newConsent, ConsentMessageDto::class.java)
        assertThat(msg.complete).isTrue()
    }



}