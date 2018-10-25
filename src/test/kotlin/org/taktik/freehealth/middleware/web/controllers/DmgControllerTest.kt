package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.dmg.DmgConsultation
import org.taktik.freehealth.middleware.dto.dmg.DmgNotification
import org.taktik.freehealth.middleware.dto.dmg.DmgRegistration
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DmgControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0
    private val gson = Gson()

    private val nisses = mapOf(
            100 to listOf("80010505329", "57072844360", "89031129009", "68021229115", "85120710797", "98051722943"),
            300 to listOf("67080109676", "89081161411", "63110211264", "62042440412", "80051632636", "74081601944"),
            500 to listOf("76120326564", "51062338241", "86122619245", "84020756632", "64101100413", "03091735863"),
            600 to listOf("38062738335", "38062738335", "61041563530", "38062738335", "47112429153", "")//,
            //900 to listOf("60021055234", "33011334166", "48030158922", "52012945565", "10110111079", "98051354789")
    )

//    100 to listOf("80010505329", "48070610791", "58082960392", "81042011148", "81042011148", ""),
//    300 to listOf("50100727553", "61112313845", "43071621267", "09032543524", "03090525244", "59121231170"),
//    500 to listOf("47081234448", "34032434459", "08032211525", "26031619091", "69010817274", "22090533441"),
//    600 to listOf("00090521419", "40032408457", "92083133247", "45050634666", "30122540643", "71111654855"),
//    900 to listOf("60021055234", "33011334166", "48030158922", "52012945565", "10110111079", "98051354789")

    private val oas = listOf("100", "300", "500", "600")
    private val regOa = listOf("300") //do not register to all OA at the same time, deregister is not possible
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx])//, nisses[900]!![idx])

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    @Test
    fun scenario1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val results = getNisses(0).map {
            val res = this.restTemplate.exchange("http://localhost:$port/gmd" +
                "?hcpNihii=$nihii1&hcpSsin=" +
                "$ssin1&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientSsin=$it" +
                "&requestDate=${now.minusMonths(25).toInstant(ZoneOffset.UTC).toEpochMilli()}",
                                                 HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
            val dmgc = gson.fromJson(res.body, DmgConsultation::class.java)

            dmgc
        }

        results.forEach {
            assertThat(it.errors).isNotEmpty
            assertThat(it.errors.first().code).isIn("120", "145")
        }
    }

    @Test
    fun scenarioSAM() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val results = getNisses(0).map {
            val res = this.restTemplate.exchange("http://localhost:$port/gmd" +
                "?hcpNihii=$nihii1&hcpSsin=" +
                "$ssin1&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientSsin=$it" +
                "&requestDate=${now.minusMonths(12).toInstant(ZoneOffset.UTC).toEpochMilli()}",
                HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
            val dmgc = gson.fromJson(res.body, DmgConsultation::class.java)

            dmgc
        }

        results.forEach {
            assertThat(it.errors).isNotEmpty
            assertThat(it.errors.first().code).isIn("120", "145")
        }
    }

    @Test
    fun scenario2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val results = getNisses(1).map {
            val res = this.restTemplate.exchange("http://localhost:$port/gmd" +
                "?hcpNihii=$nihii1&hcpSsin=" +
                "$ssin1&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientSsin=$it",
                                                 HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
            val dmgc = gson.fromJson(res.body, DmgConsultation::class.java)

            dmgc
        }

        results.forEach {
            assertThat(it.errors).isEmpty()
        }
    }

    @Test
    fun scenario4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = getNisses(3).map {
            val res = this.restTemplate.exchange("http://localhost:$port/gmd/notify/101010"  +
                "?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientSsin=$it" +
                "&patientFirstName=XXX&patientLastName=XXX" +
                "&patientGender=female",
                HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
            val dmgc = gson.fromJson(res.body, DmgNotification::class.java)

            dmgc
        }

        results.forEach {
            assertThat(it.errors).isEmpty()
        }
    }

    @Test
    fun scenario5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val results = getNisses(4).map {
            val res = this.restTemplate.exchange("http://localhost:$port/gmd/notify/101076"  +
                "?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientSsin=$it" +
                "&patientFirstName=XXX&patientLastName=XXX" +
                "&patientGender=female" +
                "&requestDate=${now.minusDays(2).toInstant(ZoneOffset.UTC).toEpochMilli()}",
                HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
            val dmgc = gson.fromJson(res.body, DmgNotification::class.java)

            dmgc
        }

        results.forEach {
            assertThat(it.errors).isEmpty()
        }
    }

    @Test
    fun scenario3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val results = getNisses(2).map {
            val res = this.restTemplate.exchange("http://localhost:$port/gmd" +
                "?hcpNihii=$nihii1&hcpSsin=" +
                "$ssin1&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientSsin=$it",
                HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
            val dmgc = gson.fromJson(res.body, DmgConsultation::class.java)

            dmgc
        }

        results.forEach {
            assertThat(it.errors).isEmpty()
        }
    }

    @Test
    fun scenarioREG() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = regOa.map {
            val str = this.restTemplate.exchange("http://localhost:$port/gmd/register/$it" +
                "?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName={firstName1}" +
                "&hcpLastName={lastName1}" +
                "&bic=$BIC1&iban=$IBAN1",
                HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
            val dmgr = gson.fromJson(str.body, DmgRegistration::class.java)

            dmgr
        }

        results.forEach {
            assertThat(it.errors).isEmpty()
        }
    }

    @Test
    fun scenarioMsgList() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val str = this.restTemplate.exchange("http://localhost:$port/gmd/reqlist" +
                                                 "?hcpNihii=$nihii1" +
                                                 "&hcpSsin=$ssin1" +
                                                 "&hcpFirstName={firstName1}" +
                                                 "&hcpLastName={lastName1}",
                                             HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
        val ok = gson.fromJson(str.body, Boolean::class.java)

        assertThat(ok).isTrue()
    }
}
