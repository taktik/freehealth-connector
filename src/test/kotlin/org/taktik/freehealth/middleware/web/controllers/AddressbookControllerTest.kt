package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.util.ArrayList

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressbookControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val gson = Gson()

    @Test
    fun searchHcp() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val searchHcp = this.restTemplate.exchange("http://localhost:$port/ab/search/hcp/Steeman*", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java).body
        Assertions.assertThat(searchHcp != null && searchHcp.length>2 && searchHcp.startsWith("["))
        val hcps: List<HealthcareParty> = gson.fromJson(searchHcp, object : TypeToken<ArrayList<HealthcareParty>>() {}.getType())
        Assertions.assertThat(20).isLessThan(hcps.size)
        Assertions.assertThat(hcps.map { it.lastName?.toUpperCase() }).contains("DUCHATEAU")
    }

    @Test
    fun searchOrg() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val searchOrg = this.restTemplate.exchange("http://localhost:$port/ab/search/org/*clinique*", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java).body
        Assertions.assertThat(searchOrg != null && searchOrg.length>2 && searchOrg.startsWith("["))
        val hcps: List<HealthcareParty> = gson.fromJson(searchOrg, object : TypeToken<ArrayList<HealthcareParty>>() {}.getType())
        Assertions.assertThat(10).isLessThan(hcps.size)
        Assertions.assertThat(hcps.map { it.name?.toUpperCase() }).contains("CLINIQUE ST.-LUC")
    }

    @Test
    fun getHcpByNihii() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getHcp = this.restTemplate.exchange("http://localhost:$port/ab/hcp/nihii/15584039004", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java).body
        Assertions.assertThat(getHcp != null && getHcp.length>2 && getHcp.startsWith("{"))
        val hcp = gson.fromJson(getHcp, HealthcareParty::class.java)
        Assertions.assertThat("STEEMAN").isEqualToIgnoringCase(hcp.lastName)
        Assertions.assertThat("MAXIME").isEqualToIgnoringCase(hcp.firstName)
    }

    @Test
    fun getHcpBySsin() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getHcp = this.restTemplate.exchange("http://localhost:$port/ab/hcp/ssin/74010414733", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java).body
        Assertions.assertThat(getHcp != null && getHcp.length>2 && getHcp.startsWith("{"))
        val hcp = gson.fromJson(getHcp, HealthcareParty::class.java)
        Assertions.assertThat("DUCHATEAU").isEqualToIgnoringCase(hcp.lastName)
        Assertions.assertThat("ANTOINE").isEqualToIgnoringCase(hcp.firstName)
        Assertions.assertThat("10032669001").isEqualToIgnoringCase(hcp.nihii)
    }

    @Test
    fun getOrgByHce() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getOrg = this.restTemplate.exchange("http://localhost:$port/ab/org/nihii/71072393",HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java).body
        Assertions.assertThat(getOrg != null && getOrg.length>2 && getOrg.startsWith("{"))
        val hcp = gson.fromJson(getOrg, HealthcareParty::class.java)
        Assertions.assertThat("CENTRE HOSPITALIER INTERREGIONAL EDITH CAVELL").isEqualToIgnoringCase(hcp.name)
        Assertions.assertThat("HOSPITAL").isEqualToIgnoringCase(hcp.type)
        Assertions.assertThat("71072393").isEqualToIgnoringCase(hcp.ehp)
    }
}
