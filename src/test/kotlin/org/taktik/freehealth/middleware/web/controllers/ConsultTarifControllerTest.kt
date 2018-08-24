package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
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
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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
    private val gmdManagers = listOf("17031506487", "10065828004", "16582446000", "14372133004", "15554246004")

    private fun assertResults(scenario: String, reimbursement: Double, results: List<TarificationConsultationResult?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
            assertThat(it!!.reimbursements).isNotNull.isNotEmpty
            assertThat(it!!.reimbursements[0].amount).isEqualTo(reimbursement)
        }
    }

    private fun assertResults(scenario: String, justification: Int, results: List<TarificationConsultationResult?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
            assertThat(it!!.justification).isEqualTo(justification)
        }
    }

    private fun assertErrors(scenario: String, error: String, results: List<TarificationConsultationResult?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
            assertThat(it!!.errors).isNotNull.isNotEmpty
            assertThat(it!!.errors[0].code).isEqualTo(error)
        }
    }

    private fun assertErrors(scenario: String, errors: List<String>, results: List<TarificationConsultationResult?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
            assertThat(it!!.errors).isNotNull.isNotEmpty
            assertThat(errors).contains(it!!.errors[0].code)
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

    /*
        NISS 1
        Consultation code nomenclature inexistant
        Tester l’envoi d’une consultation de tarif rejetée par l’OA, et la réception de la réponse.
        IN: CN 101075
        OUT: Rejet code erreur 130
    */
    @Test
    fun scenario1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val results = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("101075"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 1", "130",  results)
    }

    /*
        NISS 1
        Consultation code nomenclature que le médecin généraliste ne peut pas attester.
        Tester l’envoi d’une consultation de tarif rejetée par l’OA, et la réception de la réponse.
        IN: CN 102793
        OUT: Rejet code erreur 171 ou 172
     */
    @Test // Problem with NISS 49020508235 (OA 500 NISS 1 Success)
    fun scenario2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val results = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("102793"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 2", listOf("171","172"),  results)
    }

    /*
        NISS 1
        Consultation avec code nomenclature réservé aux médecins spécialistes alors que la consultation est
        effectuée par un médecin généraliste.
        Tester l’envoi d’une consultation de tarif rejetée par l’OA, et la réception de la réponse.
        IN: CN 102034
        OUT: Rejet code erreur 171
     */
    @Test
    fun scenario3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val results = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf("102034"), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 3", "171",  results)
    }

    /*
        NISS 1
        Consultation avec une date de consultation de plus de 2 mois dans le passé.
        Tester l’envoi d’une consultation de tarif rejetée par l’OA, et la réception de la réponse.
        IN: CN 101010 for qualification 001 & 002
            OR CN 101032 for qualification 003, 004, 005, 006
        OUT: Rejet code erreur 166
     */
    @Test
    fun scenario4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) "101010" else "101032"

        val results = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&date=${now.minusMonths(3).format(DateTimeFormatter.ofPattern("yyyyMMdd"))}", listOf(code), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 4", "166",  results)
    }

    /*
        NISS 3
        Consultation pour un patient avec contrat Maison Médicale
        Tester l’envoi d’une consultation de tarif pour un patient ayant un contrat maison médicale, et la réception de la réponse.
        IN: {CN 101010 for qualification 001 & 002}
            OR {CN 101032 for qualification 003, 004, 005, 006 AND CN 475075}
        OUT: Rejet code erreur 169
     */
    @Test
    fun scenario5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) "101010" else "101032"

        val results = getNisses(2).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf(code), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 5", "169",  results)
    }

    /*
        NISS 4
        Consultation pour un patient non en règle
        Tester l’envoi d’une consultation de tarif pour un patient non en règle, et la réception de la réponse.
        IN: CN 101010 for qualification 001 & 002
            OR CN 101032 for qualification 003, 004, 005, 006
        OUT: Rejet code erreur 170
     */
    @Test // Problem with NISS 71010908576 (OA 300 NISS 4 Success)
    fun scenario6() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) "101010" else "101032"

        val results = getNisses(3).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf(code), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertErrors("scenario 6", "170",  results)
    }

    /*
        NISS 1
        Consultation pour un code nomenclature, avec engagement de paiement.
        Tester l’envoi d’une consultation de tarif pour une prestation pour laquelle le médecin reçoit un engagement de paiement, et la réception de la réponse.
        IN: CN 101010 for qualification 001 & 002
            OR CN 101032 for qualification 003, 004, 005, 006
        OUT: CD-MYCARENETJUSTIFICATION = 3
     */
    @Test
    fun scenario7() { //Probleme with NISS 59072957042 (600 first) code 170 patient doesn't conform to regulation
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) "101010" else "101032"

        val results = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", listOf(code), TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 7", 19.59,  results)
    }

    /*
        NISS 1
        Consultation pour plusieurs codes nomenclature, avec engagement de paiement.
        Tester l’envoi d’une consultation de tarif pour plusieurs prestations pour lesquelles le médecin reçoit un engagement de paiement, et la réception de la réponse.
        IN: {CN 101010 for qualification 001 & 002}
            OR {CN 101032 for qualification 003, 004, 005, 006 AND CN 475075}
        OUT: CD-MYCARENETJUSTIFICATION = 3
     */
    @Test // Problem with NISS 64032764903 (OA 300 NISS 1 justification 0)
    fun scenario8() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) listOf("101010") else listOf("101032","475075")

        val results = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", code, TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 8", 3,  results)
    }

    /*
        NISS 1
        Consultation sans engagement de paiement.
        Tester l’envoi d’une consultation de tarif pour une prestation pour laquelle le médecin ne reçoit pas d’engagement de paiement, et la réception de la réponse.
        IN: CN 101010 for qualification 001 & 002
            OR CN 101032 for qualification 003, 004, 005, 006
        OUT: CD-MYCARENETJUSTIFICATION = 9
     */
    @Test // Problem with NISS 66021250154 (300 second)
    fun scenario9() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) listOf("101010") else listOf("101032")

        val results = getNisses(1).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", code, TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 9", 9,  results)
    }

    /*
        NISS 2
        Consultation avec justification tiers-payant.
        Tester l’envoi d’une consultation de tarif pour laquelle le médecin justifie l’application du tiers-payant, et la réception de la réponse.
        IN: {CN 101010 for qualification 001 & 002
            OR CN 101032 for qualification 003, 004, 005, 006}
            AND CD-MYCARENETJUSTIFICATION = 2
        OUT: CD-MYCARENETJUSTIFICATION = 2
     */
    @Test
    fun scenario10() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) listOf("101010") else listOf("101032")

        val results = listOf(nisses[100]!![1], nisses[300]!![1], nisses[900]!![1]).map {
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&justification=2", code, TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 10", 2,  results)
    }

    /*
        NISS 2
        Consultation avec notion ‘G’ + indication du médecin détenteur du DMG.
        Tester l’envoi d’une consultation de tarif pour laquelle le médecin justifie l’utilisation de la notion ‘G’, et la réception de la réponse.
        IN: {CN 101010 for qualification 001 & 002
            OR CN 101032 for qualification 003, 004, 005, 006}
            AND CD-MYCARENETJUSTIFICATION = 7
        OUT: CD-MYCARENETJUSTIFICATION = 7
     */
    @Test
    fun scenario11() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()
        val code = if(nihii1!!.endsWith("001") || nihii1!!.endsWith("002")) listOf("101010") else listOf("101032")

        val results = getNisses(1).map {
            val gmdNihii = gmdManagers[getNisses(1).indexOf(it)]
            this.restTemplate.postForObject("http://localhost:$port/tarif/$it?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&justification=7&gmdNihii=$gmdNihii", code, TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)
        }
        assertResults("scenario 11", 7,  results)
    }
}
