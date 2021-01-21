package org.taktik.freehealth.middleware.web.controllers

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

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RnConsultV2ControllerTest: EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun searchPersonBySsin(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"92092412781"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhonetically(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19920924"}/${"mennechet"}?firstName=${"max"}&middleName=${""}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhoneticallyWithGender(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19960000"}/${"wathelet"}?firstName=${"julien"}&middleName=${""}&gender=${"MALE"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhoneticallyWithCountryCode(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"1977"}/${"mennechet"}?firstName=${"max"}&middleName=${""}&countryCode=${111}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhoneticallyWithCountryCodeAndCityCode(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19920924"}/${"mennechet"}?firstName=${"max"}&middleName=${""}&countryCode=${111}&cityCode=${"20"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The user get the current information about a person cancelled
    fun searchPersonBySsinSc1(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"56000308828"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The user get the current information about a person replaced
    fun searchPersonBySsinSc2(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"49242300517"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The user get the current information about a person that doesn’t exists in CBSS register
    fun searchPersonBySsinSc3(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"81490230530"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned the current information the person with a residential address
    fun searchPersonBySsinSc4(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"75410233908"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned the current information the person with a contact address
    fun searchPersonBySsinSc5(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"70481606005"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned the current information the person with a contact address and a residential address
    fun searchPersonBySsinSc6(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"92440106511"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned a business error because the individual identifier is invalid
    fun searchPersonBySsinSc7(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/consultrnv2/bySsin/${"56000308818"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person with first name, last name, birthdate
    fun searchPersonPhoneticallySc1(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19700816"}/${"pluton"}?firstName=${"rita"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person without first name
    fun searchPersonPhoneticallySc2(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19700816"}/${"pluton"}?firstName=${""}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person with no matching last name
    fun searchPersonPhoneticallySc3(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19700816"}/${"mars"}?firstName=${"rita"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person with no matching last name
    fun searchPersonPhoneticallySc4(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/consultrnv2/phonetically/${"19700816"}/${"pluton"}?firstName=${"rita"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

}
