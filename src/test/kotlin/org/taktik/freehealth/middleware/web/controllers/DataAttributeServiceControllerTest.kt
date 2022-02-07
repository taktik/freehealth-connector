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
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val daas = this.restTemplate.exchange("http://localhost:$port/daas/din/${nihii1}/${"72022102793"}?dateOfBirth=19720221&from=20220201&to=20220221&cause=ziek&prolongation=false&total=false",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)

        assertErrors("BasicDAASTest", "", daas.toString())

    }
}
