package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import java.io.File

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataAttributeServiceControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0
    private val gson = Gson()

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private fun assertErrors(scenario: String, error: String, results: String?) {
        println(scenario + "\n====================")
    }

    @Before
    fun setUp() {
    }

    @Test
    fun BasicDAASTest(){
        val niss: String = "56441941910"
        val birthday: String = "19560419"
        val from: String = "20220207"
        val to: String = "20220208"
        val cause: String = "illness"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)

        assertErrors("BasicDAASTest", "", daas.toString())

    }

    fun Scenario1(){
        val niss: String = "56441941910"
        val birthday: String = "19560419"
        val from: String = "20220207"
        val to: String = "20220208"
        val cause: String = "illness"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)

        assertErrors("Scenario1", "", daas.toString())

    }

    fun Scenario2(){
        val niss: String = "97432402244"
        val birthday: String = "19970324"
        val from: String = "20220207"
        val to: String = "20220229"
        val cause: String = "workaccident"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)

        assertErrors("Scenario2", "", daas.toString())

    }
}
