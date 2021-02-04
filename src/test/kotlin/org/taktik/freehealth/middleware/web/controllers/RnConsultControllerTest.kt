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
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultPersonMid

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RnConsultControllerTest: EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun searchPersonBySsin(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"92092412781"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhonetically(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19920924"}/${"mennechet"}?firstName=${"maxime"}&middleName=${""}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhoneticallyWithGender(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19960000"}/${"wathelet"}?firstName=${"julien"}&middleName=${""}&gender=${"MALE"}&tolerance=2",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhoneticallyWithCountryCode(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"1977"}/${"mennechet"}?firstName=${"max"}&middleName=${""}&countryCode=${111}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun searchPersonPhoneticallyWithCountryCodeAndCityCode(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19920924"}/${"mennechet"}?firstName=${"max"}&middleName=${""}&countryCode=${111}&cityCode=${"20"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The user get the current information about a person cancelled
    fun searchPersonBySsinSc1(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"56000308828"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The user get the current information about a person replaced
    fun searchPersonBySsinSc2(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"49242300517"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The user get the current information about a person that doesn’t exists in CBSS register
    fun searchPersonBySsinSc3(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"81490230530"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned the current information the person with a residential address
    fun searchPersonBySsinSc4(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"75410233908"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned the current information the person with a contact address
    fun searchPersonBySsinSc5(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"70481606005"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned the current information the person with a contact address and a residential address
    fun searchPersonBySsinSc6(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"92440106511"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //The service returned a business error because the individual identifier is invalid
    fun searchPersonBySsinSc7(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personBySsin = this.restTemplate.exchange("http://localhost:$port/rnconsult/bySsin/${"56000308818"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person with first name, last name, birthdate
    fun searchPersonPhoneticallySc1(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19700816"}/${"pluton"}?firstName=${"rita"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person without first name
    fun searchPersonPhoneticallySc2(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19700816"}/${"pluton"}?firstName=${""}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person with no matching last name
    fun searchPersonPhoneticallySc3(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19700816"}/${"mars"}?firstName=${"rita"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //Search information about a person with no matching last name
    fun searchPersonPhoneticallySc4(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/phonetically/${"19700816"}/${"pluton"}?firstName=${"rita"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    // Base on mid birth
    fun registerPersonBasedOnBirth(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val registerPerson = this.restTemplate.exchange("http://localhost:$port/rnconsult", HttpMethod.POST, HttpEntity(
            RnConsultPersonMid(
                lastName = "jenesaispascomment",
                firstName = "julienne",
                birthPlace = RnConsultPersonMid.BirthPlace(
                    countryCode = 111,
                    cityName = "ans"
                ),
                contactAddress = null,
                dateOfBirth = 20000101,
                gender = "female",
                middleName = null,
                nationalityCode = 150,
                residentialAddress = null,
                language = "fr"
            ),
            createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
    }

    @Test
    // Base on contact address in belgium
    fun registerPersonBasedOnAddressInBelgium(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val registerPerson = this.restTemplate.exchange("http://localhost:$port/rnconsult", HttpMethod.POST, HttpEntity(
            RnConsultPersonMid(
                lastName = "atchoum",
                firstName = "baboum",
                birthPlace = null,
                contactAddress = RnConsultPersonMid.ContactAddress(
                    countryCode = 150,
                    cityCode = "102",
                    streetName = "atchoumboboum",
                    typeCode = 0
                ),
                dateOfBirth = 20000101,
                gender = null,
                middleName = null,
                nationalityCode = 150,
                residentialAddress = null,
                language = "fr"
            ),
            createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
    }

    @Test
    // Base on residential address
    fun registerPersonBasedOnResidentialAddress(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val registerPerson = this.restTemplate.exchange("http://localhost:$port/rnconsult", HttpMethod.POST, HttpEntity(
            RnConsultPersonMid(
                lastName = "dalas",
                firstName = "corben",
                birthPlace = null,
                contactAddress = null,
                dateOfBirth = 20000804,
                gender = null,
                middleName = null,
                nationalityCode = null,
                residentialAddress = RnConsultPersonMid.ResidentialAddress(
                    countryCode = 159,
                    cityName = "New-York",
                    streetName = "badaboum"
                ),
                language = "fr"
            ),
            createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
    }


    @Test
    //Verify history of niss
    fun consultCurrentSsin(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val personPhonetically = this.restTemplate.exchange("http://localhost:$port/rnconsult/history/${"92092412781"}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    //VerifyId
    fun verifyId(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val veridyId = this.restTemplate.exchange("http://localhost:$port/rnconsult/verifyId?ssin=${"84091304237"}&cardNumber=${"591112548495"}&barCoded=${""}",
            HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

}
