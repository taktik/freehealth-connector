package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DmgControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    private val nisses = mapOf(100 to listOf("80010505329", "48070610791", "58082960392", "81042011148", "81042011148", ""),
            300 to listOf("50100727553", "61112313845", "43071621267", "09032543524", "03090525244", "59121231170"),
            500 to listOf("47081234448", "34032434459", "08032211525", "26031619091", "69010817274", "22090533441"),
            600 to listOf("00090521419", "40032408457", "92083133247", "45050634666", "30122540643", "71111654855"),
            900 to listOf("60021055234", "33011334166", "48030158922", "52012945565", "10110111079", "98051354789")
    )
    private val oas = listOf("100","300","500","600","900")
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])

    private fun assertResults(scenario: String, results: List<String?>) {
        println(scenario+"\n====================")

        results.forEachIndexed { index, it ->
            Assertions.assertThat(it?.length ?: 0 > 2 && it!!.startsWith("{"))
            val res = Gson().fromJson(it, SendAttestResult::class.java)
            println("${oas[index]}: ${res.invoicingNumber?:"-"}")
            res.acknowledge?.errors?.forEach {
                println(it.locFr+ " : "+it.msgFr)
            }
        }
    }

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        try { System.setProperty("mycarenet.license.password",this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e:NullPointerException) {
            System.setProperty("mycarenet.license.password",File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    @Test
    fun scenario1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val results = getNisses(0).map {
            this.restTemplate!!.getForObject("http://localhost:$port/gmd?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&patientSsin=${it}&requestDate=${now.minusMonths(25).toInstant(ZoneOffset.UTC).toEpochMilli()}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
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
            this.restTemplate!!.postForObject("http://localhost:$port/eattest/send/$it?hcpNihii=11478761004&hcpSsin=${ssin1}&hcpFirstName=${"Antoine"}&hcpLastName=${"Baudoux"}&hcpCbe=0635769870&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", eattest, String::class.java)
        }
        assertResults("scenario 18", results)
    }
}