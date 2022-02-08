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
import org.taktik.freehealth.middleware.dto.daas.DaasResponse
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import java.io.File
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataAttributeServiceControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0
    private val gson = Gson()

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private fun assertErrors(scenario: String, error: String, statusCode: Int, results: DaasResponse?) {
        println(scenario + "\n====================")
    }

    @Before
    fun setUp() {
    }

    @Test
    fun BasicDAASTest(){
        val niss: String = "56441941910"
        val birthday: String = "19560419"
        val nowDate = LocalDateTime.now()
        val thenDate = nowDate.plusDays(1)
        val from: String = (nowDate.year * 10000 + nowDate.monthValue * 100 + nowDate.dayOfMonth).toString()
        val to: String = (thenDate.year * 10000 + thenDate.monthValue * 100 + thenDate.dayOfMonth).toString()
        val cause: String = "illness"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), DaasResponse::class.java, passPhrase)

        assert(daas.statusCodeValue == 200)
        assertErrors("BasicDAASTest", "", daas.statusCodeValue, daas.body)

    }

    @Test
    fun Scenario1Test(){
        val niss: String = "56441941910"
        val birthday: String = "19560419"
        val nowDate = LocalDateTime.now()
        val thenDate = nowDate.plusDays(1)
        val from: String = (nowDate.year * 10000 + nowDate.monthValue * 100 + nowDate.dayOfMonth).toString()
        val to: String = (thenDate.year * 10000 + thenDate.monthValue * 100 + thenDate.dayOfMonth).toString()
        val cause: String = "illness"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), DaasResponse::class.java, passPhrase)

        assert(daas.statusCodeValue == 200)
        assertErrors("Scenario1", "", daas.statusCodeValue, daas.body)
    }

    @Test
    fun Scenario2Test(){
        val niss: String = "97432402244"
        val birthday: String = "19970324"
        val nowDate = LocalDateTime.now()
        val thenDate = nowDate.plusDays(22)
        val from: String = (nowDate.year * 10000 + nowDate.monthValue * 100 + nowDate.dayOfMonth).toString()
        val to: String = (thenDate.year * 10000 + thenDate.monthValue * 100 + thenDate.dayOfMonth).toString()
        val cause: String = "workaccident"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), DaasResponse::class.java, passPhrase)

        assert(daas.statusCodeValue == 200)
        assertErrors("Scenario2", "", daas.statusCodeValue, daas.body)
    }

    @Test
    fun Scenario3Test(){
        val niss: String = "75411615563"
        val birthday: String = "19750116"
        val nowDate = LocalDateTime.now()
        val thenDate = nowDate.plusDays(21)
        val from: String = (nowDate.year * 10000 + nowDate.monthValue * 100 + nowDate.dayOfMonth).toString()
        val to: String = (thenDate.year * 10000 + thenDate.monthValue * 100 + thenDate.dayOfMonth).toString()
        val cause: String = "illness"
        val prolongation: String = "true"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), DaasResponse::class.java, passPhrase)

        assert(daas.statusCodeValue == 200)
        assertErrors("Scenario3", "", daas.statusCodeValue, daas.body)
    }

    @Test
    fun Scenario4Test(){
        val niss: String = "92410907531"
        val birthday: String = "19920109"
        val nowDate = LocalDateTime.now()
        val thenDate = nowDate.plusDays(6)
        val from: String = (nowDate.year * 10000 + nowDate.monthValue * 100 + nowDate.dayOfMonth).toString()
        val to: String = (thenDate.year * 10000 + thenDate.monthValue * 100 + thenDate.dayOfMonth).toString()
        val cause: String = "workaccident"
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), DaasResponse::class.java, passPhrase)

        assert(daas.statusCodeValue == 200)
        assertErrors("Scenario4", "", daas.statusCodeValue, daas.body)
    }

    @Test
    fun Scenario5Test(){
        val niss: String = "91412708740"
        val birthday: String = "19910127"
        val nowDate = LocalDateTime.now()
        val thenDate = nowDate.plusDays(30)
        val from: String = (nowDate.year * 10000 + nowDate.monthValue * 100 + nowDate.dayOfMonth).toString()
        val to: String = (thenDate.year * 10000 + thenDate.monthValue * 100 + thenDate.dayOfMonth).toString()
        val cause: String = "illness" //pregnancy
        val prolongation: String = "false"
        val total: String = "true"

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${niss}?dateOfBirth=${birthday}&from=${from}&to=${to}&cause=${cause}&prolongation=${prolongation}&total=${total}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), DaasResponse::class.java, passPhrase)

        assert(daas.statusCodeValue == 200)
        assertErrors("Scenario2", "", daas.statusCodeValue, daas.body)
    }
}
