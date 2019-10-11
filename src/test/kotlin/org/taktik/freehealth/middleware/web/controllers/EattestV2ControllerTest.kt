package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.junit.Before
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
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import java.io.File
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EattestV2ControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    private val nisses = mapOf(100 to listOf("73052005540", "84101727579", "39091706120", "29041433972", "97061960828", "09031001094"),
            300 to listOf("17031506487", "88022631093", "87052226861", "63042408660", "37061311820", "87120924439"),
            500 to listOf("13070421120", "12070321327", "69070608470", "58031245635", "46111636603", "09041004003"),
            600 to listOf("70021546287", "03051303986", "69021902691", "10090820056", "53081411750", "60042560332"),
            900 to listOf("72062724415", "80011446526", "60122945519", "80010512554", "32011328801", "N/A")
    )
    private val oas = listOf("100", "300", "500", "600", "900")
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])
    private val gmdManagers = listOf("17031506487", "88022631093", "87052226861", "63042408660", "37061311820", "87120924439")

    private fun assertResults(scenario: String, results: List<String?>) {
        println(scenario + "\n====================")

        results.forEachIndexed { index, it ->
            Assertions.assertThat(it?.length ?: 0 > 2 && it!!.startsWith("{"))
            val res = Gson().fromJson(it, SendAttestResult::class.java)
            println("${oas[index]}: ${res.invoicingNumber ?: "-"}")
            res.acknowledge?.errors?.forEach {
                println(it.locFr + " : " + it.msgFr)
            }
        }
    }

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        try { System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    @Test
    fun scenario1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101075",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange(
                "http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}",
                HttpMethod.POST,
                HttpEntity(
                    eattest,
                    createHeaders(null, null, keystoreId, tokenId, passPhrase)
                          ), String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 1", results)
    }

    @Test
    fun scenario2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101371",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 2", results)
    }

    @Test
    fun scenario3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "102034",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 3", results)
    }

    @Test
    fun scenario4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val nowMinus24 = LocalDateTime.now().minusDays(1)
        val yesterday = nowMinus24.year * 10000 + nowMinus24.monthValue * 100 + nowMinus24.dayOfMonth
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = yesterday,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 4", results)
    }

    @Test
    fun scenario5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(1).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 5", results)
    }

    @Test
    fun scenario6() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "103095",
                relativeService = "102034",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 6", results)
    }

    @Test
    fun scenario7() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0,
                internship = Eattest.EattestHcParty(
                        idHcParty = "10928813006" /* real riziv is 10928831006 */,
                        idSsin = ssin2,
                        cdHcParty = "persphysician",
                        firstName = "Wendy",
                        lastName = "Bertrand"
                )
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 7", results)
    }

    @Test
    fun scenario8() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin2!!, password2!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0,
                gmdManager = Eattest.EattestHcParty(
                        idHcParty = "11478761004" /* real riziv is 11478761004 */,
                        idSsin = ssin1,
                        cdHcParty = "persphysician",
                        firstName = "Antoine",
                        lastName = "Baudoux"
                )
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 8", results)
    }

    @Test
    fun scenario9() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = -21.0,
                reglementarySupplement = -4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 9", results)
    }

    @Test
    fun scenario10() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "109045",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(2).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 10", results)
    }

    @Test
    fun scenario11() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 11", results)
    }

    @Test
    fun scenario12() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin2!!, password2!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0,
                doctorSupplement = 80.0,
                gmdManager = Eattest.EattestHcParty(
                        idHcParty = "11478761004",
                        idSsin = ssin1,
                        cdHcParty = "persphysician",
                        firstName = "Antoine",
                        lastName = "Baudoux"
                )
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 12", results)
    }

    @Test
    fun scenario13() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val today = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth
        val nowMinus24 = LocalDateTime.now().minusDays(1)
        val yesterday = nowMinus24.year * 10000 + nowMinus24.monthValue * 100 + nowMinus24.dayOfMonth
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = today,
                riziv = "109045",
                reimbursement = 21.0,
                reglementarySupplement = 4.0,
                location = Eattest.EattestHcParty(idHcParty = "81165343998", cdHcParty = "orghospital"),
                requestor = Eattest.EattestRequestor(
                        date = yesterday,
                        hcp = Eattest.EattestHcParty(
                                idHcParty = "11478761004",
                                idSsin = ssin1,
                                cdHcParty = "persphysician",
                                firstName = "Antoine",
                                lastName = "Baudoux"
                        )
                )
        )))

        val results = getNisses(2).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 13", results)
    }

    @Test
    fun scenario14() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(5).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 14", results)
    }

    @Test
    fun scenario15() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "109955",
                quantity = 20,
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 15", results)
    }

    @Test
    fun scenario16() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "120050",
                reimbursement = 21.0,
                reglementarySupplement = 4.0,
                location = Eattest.EattestHcParty(idHcParty = "81165343998", cdHcParty = "orglaboratory")
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 16", results)
    }

    @Test
    fun scenario17() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0,
                internship = Eattest.EattestHcParty(
                        idHcParty = "10928831006" /* real riziv is 10928831006 */,
                        idSsin = ssin2,
                        cdHcParty = "persphysician",
                        firstName = "Wendy",
                        lastName = "Bertrand"
                )
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 17", results)
    }

    @Test
    fun scenario18() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(4).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, passPhrase, "John", "Doe", "male").body
        }
        assertResults("scenario 18", results)
    }
}
