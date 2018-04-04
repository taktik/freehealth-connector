package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.taktik.freehealth.middleware.domain.SamlTokenResult
import java.net.URLEncoder
import java.util.*


@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class STSControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null
    private val gson : Gson = Gson()

    @Test
    fun uploadKeystore() {
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("${ssin1}.acc-p12").path, port, restTemplate!!)
        assertThat(keystoreId != null)
    }

    @Test
    fun requestToken() {
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("79121430944.acc-p12").path, port, restTemplate!!)
        val ssin = ssin1
        val passPhrase = password1
        val res = this.restTemplate!!.getForObject("http://localhost:$port/sts/token/$keystoreId?passPhrase=$passPhrase&ssin=$ssin", String::class.java)
        assertThat(res != null)
        val saml = gson.fromJson(res, SamlTokenResult::class.java)
        assertThat(saml.tokenId).isNotNull()
        assertThat(saml.token).startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Assertion")
        assertThat(saml.token).containsIgnoringCase("OU=ANTOINE BAUDOUX")
    }

    @Test
    fun registerToken() {
    }
}