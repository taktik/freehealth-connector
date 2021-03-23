package org.taktik.freehealth.middleware.web.controllers


import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.assertj.core.api.Assertions
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


@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class VaccinetControllerTest : EhealthTest(){

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun getVaccinations(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssinVaccinet!!, passwordVaccinet!!)
        val patientId = "74010414733"
        val vaccinations =  this.restTemplate.exchange("http://localhost:$port/vaccinnet/$patientId?softwareId=taktik_medispring&vaccinnetId=${nihiiVaccinet}&since=20200101", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(vaccinations != null)
    }

    @Test
    fun deleteMessage(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssinVaccinet!!, passwordVaccinet!!)
        val patientId = ""
        val vaccinationId = "123"
        val vaccinations =  this.restTemplate.exchange("http://localhost:$port/vaccinnet/$patientId/$vaccinationId?softwareId=taktik_medispring&vaccinnetId=${nihiiVaccinet}", HttpMethod.DELETE, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(vaccinations != null)
    }

    @Test
    fun addVaccination(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssinVaccinet!!, passwordVaccinet!!)
        val patientId = ""
        val msg = Kmehrmessage()
        val message = this.restTemplate.exchange("http://localhost:$port/vaccinnet/$patientId?softwareId=taktik_medispring&vaccinnetId=${nihiiVaccinet}", HttpMethod.POST, HttpEntity<Kmehrmessage>(msg, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(message != null)
    }

}
