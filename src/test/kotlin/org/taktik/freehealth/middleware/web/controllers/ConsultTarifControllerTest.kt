package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.business.domain.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import java.io.File
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsultTarifControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    private val nisses = mapOf(100 to listOf("36121015396", "86103130262", "14032816518", "91031030962"),
                               300 to listOf("64032764903", "66021250154", "74042566174", "71010908576"),
                               500 to listOf("49020508235", "83092618070", "76103012361", "62091405715"),
                               600 to listOf("59072957042", "83091041227", "49061015930", "82413101990"),
                               900 to listOf("59011214562", "73061527277", "90012333497", "78100404390")
                              )
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])
    private val gmdManagers = listOf("17031506487", "88022631093", "87052226861", "63042408660", "37061311820", "87120924439")

    private fun assertResults(scenario: String, reimbursement: Double, results: List<TarificationConsultationResult?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
            assertThat(it!!.reimbursements).isNotNull.isNotEmpty
            assertThat(it!!.reimbursements[0].amount).isEqualTo(reimbursement)
        }
    }

    private fun assertErrors(scenario: String, error: String, results: List<TarificationConsultationResult?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
            assertThat(it!!.errors).isNotNull.isNotEmpty
            assertThat(it!!.errors[0].code).isEqualTo(error)
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
        val results = listOf( nisses[300]!![0], nisses[900]!![0]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("101075"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 1", "130",  results)
    }

    @Test
    fun scenario2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[100]!![0], nisses[300]!![0], nisses[600]!![0], nisses[900]!![0]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("102793"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 2", "172",  results)
    }

    @Test
    fun scenario3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[300]!![0], nisses[600]!![0], nisses[900]!![0]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("102034"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 3", "171",  results)
    }

    @Test
    fun scenario4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val date = LocalDateTime.now().minusMonths(3)
        val results = listOf(nisses[900]!![0]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&encounterdatetime=$date&passPhrase={passPhrase}", listOf("101032"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 4", "166",  results)
    }

    @Test
    fun scenario5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val date = LocalDateTime.now()
        val competence = nihii1!!.subSequence(8,11)
        var code = "101032"
        if(competence=="003" || competence=="002")
            code = "101010"
        val results = listOf( nisses[900]!![2]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf(code,"475075"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 5", "169",  results)
    }

    @Test
    fun scenario6() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val date = LocalDateTime.now()
        val competence = nihii1!!.subSequence(8,11)
        var code = "101032"
        if(competence=="003" || competence=="002")
            code = "101010"
        val results = listOf(nisses[900]!![3]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf(code), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 6", "170",  results)
    }

    @Test
    fun scenario7() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[900]!![0]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("101032"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 7", 19.59,  results)
    }

    @Test
    fun scenario8() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[900]!![0]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("101032","112210"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 8", 19.59,  results)
    }

    @Test
    fun scenario9() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[900]!![1]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("101032"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 9", 15.09,  results)
    }

    @Test
    fun scenario10() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[900]!![1]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&justification=2", listOf("101032"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 10", 15.09,  results)
    }

    @Test
    fun scenario11() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val results = listOf(nisses[900]!![1]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&justification=7&gmdmanager=$nihii1", listOf("101032"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 11", 15.09,  results)
    }
}
