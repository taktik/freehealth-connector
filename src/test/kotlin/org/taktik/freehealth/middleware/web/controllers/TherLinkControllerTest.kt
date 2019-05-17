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
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import java.util.*

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TherLinkControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val gson: Gson = Gson()

    private fun getTherapeuticLink(): TherapeuticLinkDto {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therLink = this.restTemplate.getForObject("http://localhost:$port/therlink/${"74010414733"}/$nihii1?keystoreId=$keystoreId&tokenId=$tokenId&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientSsin=${"74010414733"}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&passPhrase={passPhrase}", String::class.java, firstName1, lastName1, passPhrase)
        assertThat(therLink != null)
        val tlmsg: TherapeuticLinkMessageDto = gson.fromJson(therLink, object : TypeToken<TherapeuticLinkMessageDto>() {}.getType())
        assertThat(tlmsg.therapeuticLinks.size).isGreaterThan(0)
        return tlmsg.therapeuticLinks.first()
    }

    @Test
    fun getTherLink() {
        val therapeuticLink = this.getTherapeuticLink();
        assertThat(therapeuticLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
        assertThat(therapeuticLink?.type).isEqualToIgnoringCase("gpconsultation")
        assertThat((therapeuticLink?.endDate ?: 0) - (therapeuticLink?.startDate ?: 0)).isGreaterThan(10000L)
    }

    @Test
    fun doesTherLinkExist() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therapeuticLink = this.getTherapeuticLink();

        val exists = this.restTemplate.postForObject("http://localhost:$port/therlink/check?keystoreId=$keystoreId&tokenId=$tokenId&&passPhrase={passPhrase}", therapeuticLink!!, String::class.java, passPhrase)
        val existingLink = gson.fromJson(exists, TherapeuticLinkDto::class.java)
        assertThat(existingLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
        assertThat(existingLink?.type).isEqualToIgnoringCase("gpconsultation")
        assertThat((existingLink?.endDate ?: 0) - (existingLink?.startDate ?: 0)).isGreaterThan(10000L)
    }

    @Test
    fun revokeTherLink() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therLink = this.restTemplate.getForObject("http://localhost:$port/therlink/${"74010414733"}/$nihii1?keystoreId=$keystoreId&tokenId=$tokenId&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&passPhrase={passPhrase}", String::class.java, firstName1, lastName1, passPhrase)
        assertThat(therLink != null)
        val tlmsg: TherapeuticLinkMessageDto = gson.fromJson(therLink, object : TypeToken<TherapeuticLinkMessageDto>() {}.getType())
        assertThat(tlmsg.therapeuticLinks.size).isGreaterThan(0)
        val therapeuticLink = tlmsg.therapeuticLinks.first()
        assertThat(therapeuticLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
        val revoke = this.restTemplate.postForObject("http://localhost:$port/therlink/revoke?keystoreId=$keystoreId&tokenId=$tokenId&&passPhrase={passPhrase}", therapeuticLink!!, String::class.java, passPhrase)
        assertThat(revoke.length).isGreaterThan(0)
        //Recreate link
         this.restTemplate.postForObject("http://localhost:$port/therlink/register?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${"11478761004"}&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientSsin=${"74010414733"}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}", null, String::class.java, firstName1, lastName1, passPhrase)
    }

    @Test
    fun registerTherLink() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therLink = this.restTemplate.postForObject("http://localhost:$port/therlink/register?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientSsin=${"74010414733"}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&passPhrase={passPhrase}", null, String::class.java, firstName1, lastName1, passPhrase)
        assertThat(therLink != null)
        val tlmsg = gson.fromJson(therLink, TherapeuticLinkMessageDto::class.java)
        val therapeuticLink = tlmsg.therapeuticLinks.first();
        assertThat(therapeuticLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
    }
}
