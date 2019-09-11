package org.taktik.freehealth.middleware.web.controllers

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.dto.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import java.io.File
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BCPTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val patientNiss = "64032764903"
    private val patientFirstName = "KOFI ADOFO"
    private val patientLastName = "AGYEPONG"
    private val patientGender = "male"

    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    fun getGeneralInsurability(keystoreId : UUID, tokenId : String, passPhrase: String): InsurabilityInfoDto? {
        return this.restTemplate!!.exchange("http://localhost:$port/genins/${patientNiss}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), InsurabilityInfoDto::class.java).body

    }

    fun getTarification(keystoreId : UUID, tokenId : String, passPhrase: String): TarificationConsultationResult? {
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) listOf("101010") else listOf("101032")
        val codes = listOf(code)
        return this.restTemplate!!.exchange("http://localhost:$port/tarif/${patientNiss}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}", HttpMethod.POST, HttpEntity(code, createHeaders(null, null, keystoreId, tokenId, passPhrase)), TarificationConsultationResult::class.java, firstName1, lastName1).body
    }

    fun sendEattest(keystoreId : UUID, tokenId : String, passPhrase: String):SendAttestResult? {
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "101032",
            reimbursement = 21.0,
            reglementarySupplement = 4.0
                                                        )))
        return this.restTemplate!!.exchange("http://localhost:$port/eattest/send/$patientNiss?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientLastName={patientFirstName}&patientFirstName={patientLastName}&patientGender={patientGender}", HttpMethod.POST, HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)), SendAttestResult::class.java, firstName1, lastName1, patientFirstName, patientLastName, patientGender).body
    }

    @Test
    fun scenario1() {
        var savedTokenId : String? = null
        if (true) {
            //Phase 1
            val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
            savedTokenId = tokenId

            assertThat(keystoreId).isNotNull()
            assertThat(tokenId).isNotNull()

            keystoreId!!

            val genIns = this.getGeneralInsurability(keystoreId, tokenId, passPhrase)

            assertThat(genIns).isNotNull

            val tarif = this.getTarification(keystoreId, tokenId, passPhrase)

            assertThat(tarif).isNotNull

            val attest = this.sendEattest(keystoreId, tokenId, passPhrase)

            assertThat(attest).isNotNull
        }

        //Stop below and disable network
        if (true) {
            val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!, savedTokenId)

            assertThat(keystoreId).isNotNull()
            assertThat(tokenId).isNotNull()

            keystoreId!!

            val genIns = this.getGeneralInsurability(keystoreId, tokenId, passPhrase)

            assertThat(genIns).isNotNull

            val tarif = this.getTarification(keystoreId, tokenId, passPhrase)

            assertThat(tarif).isNotNull

            val attest = this.sendEattest(keystoreId, tokenId, passPhrase)

            assertThat(attest).isNotNull
        }
    }

}
