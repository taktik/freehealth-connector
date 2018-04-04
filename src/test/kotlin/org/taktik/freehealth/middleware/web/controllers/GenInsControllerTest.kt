package org.taktik.freehealth.middleware.web.controllers

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

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GenInsControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun getGeneralInsurability() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate!!.getForObject("http://localhost:$port/genins/${"74010414733"}?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=${"11478761004"}&hcpSsin=${ssin1}&hcpName=${"Antoine Baudoux"}&hcpQuality=${"doctor"}&passPhrase=$passPhrase", String::class.java)
        assertThat(genIns != null)
    }
}