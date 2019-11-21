package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.junit.Assert
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

    private var tabAttesSV2C3: MutableList<SendAttestResult> = mutableListOf<SendAttestResult>()

    private val nisses = mapOf(100 to listOf("73052005540", "84101727579", "39091706120", "29041433972", "97061960828", "09031001094","71010639352","","","","85020205931"),
            300 to listOf("17031506487", "88022631093", "87052226861", "63042408660", "37061311820", "87120924439","77012714983","","","","99071367692"),
            500 to listOf("13070421120", "12070321327", "69070608470", "58031245635", "46111636603", "09041004003","62110906574","","","","46111636603"),
            600 to listOf("70021546287", "03051303986", "69021902691", "10090820056", "53081411750", "60042560332","53081411750","","","","70021546287"),
            900 to listOf("72062724415", "80011446526", "60122945519", "80010512554", "32011328801", "N/A","72062724415","","","","32011328801")
    )
    private val oas = listOf("100", "300", "500", "600", "900")
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])
    private val gmdManagers = listOf("11772830004","19330813004","14455473004","10830643004","17219973004")
    private fun getNissesv2(idx: Int) = listOf(nisses[100]!![6+idx], nisses[300]!![6+idx], nisses[500]!![6+idx], nisses[600]!![6+idx], nisses[900]!![6+idx])


    private fun assertResults(scenario: String, results: List<String?>) {
        println(scenario + "\n====================")

        results.forEachIndexed { index, it ->
            Assertions.assertThat(it?.length ?: 0 > 2 && it!!.startsWith("{"))
            val res = Gson().fromJson(it, SendAttestResult::class.java)
            Assert.assertEquals(((res.attest!=null && res.acknowledge?.errors?.isEmpty()!!) || res.acknowledge?.errors?.any { err -> err.code.equals("190") }!!),false)
            println("${oas[index]}: ${res.invoicingNumber ?: "errors or empty ${res.acknowledge?.errors?.map { err -> err.code }.toString()}"}")
        }
    }

    private fun assertResultsError(scenario: String, results: List<String?>, errorCode: String){
        println(scenario + "\n====================")

        results.forEachIndexed { index, it ->
            Assertions.assertThat(it?.length ?: 0 > 2 && it!!.startsWith("{"))
            val res = Gson().fromJson(it, SendAttestResult::class.java)
            if(res?.acknowledge?.errors?.isNotEmpty()!!){
                Assert.assertNotNull(res.acknowledge?.errors?.find { error -> error.code.equals(errorCode) })
                println("${oas[index]}: wanted error($errorCode) - got error (${res.acknowledge?.errors?.map { err -> err.code }.toString()})")
            }else{
                Assert.assertEquals(res.attest,null)
                println("${oas[index]}: wanted error($errorCode) - got 0 error but eattest empty")
            }
        }
    }

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password",
                this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password",
                File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }


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
                "http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}",
                HttpMethod.POST,
                HttpEntity(
                    eattest,
                    createHeaders(null, null, keystoreId, tokenId, passPhrase)
                          ), String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 1", results,"130")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 2", results,"195")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 3", results,"171")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 4", results,"191")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 5", results,"169")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 6", results,"185")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 7", results,"111")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 8", results,"167")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 9", results,"130")
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario 10", results,"100")
    }


    fun scenario11() {/**@Todo test mal écrit besoin info OA pour ID data*/
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
                date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
                riziv = "101032",
                reimbursement = 21.0,
                reglementarySupplement = 4.0
        )))

        val results = getNisses(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 11", results)
    }


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
                        cdHcParty = "persphysician",
                        firstName = "Antoine",
                        lastName = "Baudoux"
                )
        )))

        var idx = 0;
        val results = getNisses(0).map {
            eattest.codes.forEach { code -> code.gmdManager?.idSsin = gmdManagers[idx] }
            idx++
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 12", results)
    }


    fun scenario13() {/**@todo inami du requestor est erroné*/
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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 13", results)
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 14", results)
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 15", results)
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 16", results)
    }


    fun scenario17() {/**@todo revoir l'inami du stagiaire*/
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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 17", results)
    }


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
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
 String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario 18", results)
    }


    fun v2scenario1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "740460",
            reimbursement = 21.0,
            reglementarySupplement = 4.0
        )))

        val results = getNissesv2(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario v2 1", results,"219")
    }


    fun v2scenario2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "101032",
            reimbursement = 21.0,
            reglementarySupplement = 4.0
        )))

        val results = getNissesv2(4).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResultsError("scenario v2 2", results,"196")
    }

    fun v2scenario3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "101032",
            reimbursement = 21.0,
            reglementarySupplement = 4.0
        )))

        val results = getNissesv2(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0070&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario v2 3", results)
    }

    @Test
    fun v2scenario4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "101032",
            reimbursement = 21.0,
            reglementarySupplement = 4.0,
            justification = "03"
        )))

        val results = getNissesv2(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0070&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario v2 4", results)
    }


    fun v2scenario5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "103132",
            reimbursement = 21.0,
            reglementarySupplement = 4.0
        )))

        val results = getNissesv2(4).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0000&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
        }
        assertResults("scenario v2 5", results)
    }

    fun v2scenario1Cancel() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "101032",
            reimbursement = 21.0,
            reglementarySupplement = 4.0,
            justification = "03"
        )))

        val results = getNissesv2(0).map {
            val invoice = this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0070&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
            val res = Gson().fromJson(invoice, SendAttestResult::class.java)
            if(res.attest!=null){
                this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&eAttestRef={attestRef}&reason={reason}", HttpMethod.DELETE,HttpEntity(null, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                        String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase,res.invoicingNumber,"X").body
            } else{
                invoice
            }
        }

        assertResults("scenario v2 cancel 1", results)
    }

    fun v2scenario2Cancel() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val eattest = Eattest(listOf(Eattest.EattestCode(
            date = now.year * 10000 + now.monthValue * 100 + now.dayOfMonth,
            riziv = "101032",
            reimbursement = 21.0,
            reglementarySupplement = 4.0,
            justification = "03"
        )))
        val results = getNissesv2(0).map {
            val invoice = this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&treatmentReason=0070&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", HttpMethod.POST,HttpEntity(eattest, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase).body
            val res = Gson().fromJson(invoice, SendAttestResult::class.java)
            if(res.attest!=null){
                tabAttesSV2C3.add(res)
                this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&eAttestRef={attestRef}&reason={reason}", HttpMethod.DELETE,HttpEntity(null, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                    String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase,res.invoicingNumber,"F").body
            } else{
                tabAttesSV2C3.add(res)
                invoice
            }
        }

        assertResults("scenario v2 cancel 2", results)
    }

    fun v2scenario3Cancel() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        var idx=0
        val results = getNissesv2(0).map {
            val attest = tabAttesSV2C3[idx++];
            if(attest.attest!=null)
                this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&eAttestRef={attestRef}&reason={reason}", HttpMethod.DELETE,HttpEntity(null, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                    String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase,attest.invoicingNumber,"F").body
            else
                null
        }

        assertResultsError("scenario v2 cancel 3", results,"240")
    }


    fun v2scenario4Cancel() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val results = getNissesv2(0).map {
            this.restTemplate.exchange("http://localhost:$port/eattestv2/send/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&hcpCbe=$cbe1&patientFirstName={patientFirstName}&patientLastName={patientLastName}&patientGender={patientGender}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&eAttestRef={attestRef}&reason={reason}", HttpMethod.DELETE,HttpEntity(null, createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java, firstName1, lastName1, "John", "Doe", "male", passPhrase,"000-1-191016-5866545-00","F").body
        }

        assertResultsError("scenario v2 cancel 4", results,"141")
    }

}
