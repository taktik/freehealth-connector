package org.taktik.freehealth.middleware.web.controllers

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
import java.io.File
import java.time.LocalDateTime
import org.taktik.connector.business.domain.chapter4.AgreementResponse
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Chapter4ControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0
    private val nihii : String = "18785633004"
    private val firstName : String = "Maxime"
    private val lastName : String = "Mennechet"

    private val nisses = mapOf(100 to listOf("73052005540", "84101727579", "39091706120", "29041433972", "97061960828", "09031001094"),
        300 to listOf("17031506487", "88022631093", "87052226861", "63042408660", "37061311820", "87120924439"),
        500 to listOf("13070421120", "12070321327", "69070608470", "58031245635", "46111636603", "09041004003"),
        600 to listOf("70021546287", "03051303986", "69021902691", "10090820056", "53081411750", "60042560332"),
        900 to listOf("72062724415", "80011446526", "60122945519", "80010512554", "32011328801", "N/A")
    )
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    /*
        Scénario 1 – NISS 1 : Consultation rejetée
        Objectif : Tester l’envoi d’une consultation d’accord rejetée par l’OA, et la réception de la réponse.
        Code erreur : 180
     */
    @Test
    fun scenario01() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"5090000"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(4).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1&hcpFirstName=$firstName&hcpLastName=$lastName&paragraph=$paragraph$&start$now&end=$now&reference=null",AgreementResponse::class.java)
        }

        println("scenario 01 \n====================")
        results.forEachIndexed { index, it ->
            Assertions.assertThat(it.errors).isNotEmpty
            Assertions.assertThat(it.errors).isEqualTo("180")
        }

    }

    /*
        Scénario 2 – NISS 2 : Consultation pour un patient qui n’a pas d’accords.
        Objectif : Tester l’envoi d’une consultation d’accord pour un patient qui n’a pas d’accord pour la
        période consultée, et la réception de la réponse.
        Response : L’OA enverra une réponse avec aucun accord trouvé.
     */
    @Test
    fun scenario02(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val civic = "3"
        val results = getNisses(4).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=null" +
                "&start=${now.minusYears(1)}" +
                "&end=$now" +
                "&reference=null",AgreementResponse::class.java)
        }

        println("scenario 02 \n====================")


    }

    /*
        Scénario 3 – NISS 3 : Consultation pour un patient qui a un accord mais qui a muté.
        Objectif : Tester l’envoi d’une consultation d’accord pour un patient qui a un accord pour la période
        consultée, et la réception de la réponse.
        Réponse avec un accord et un warning
     */
    @Test
    fun scenario03(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val civic = "3"
        val results = getNisses(4).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=2280100" +
                "&start=${LocalDateTime.of(2016, 5, 1, 0, 0, 0)}" +
                "&end=${LocalDateTime.of(2016, 7, 31, 0, 0, 0)}" +
                "&reference=null",AgreementResponse::class.java)
        }



        println("scenario 03 \n====================")

    }

    /*
        Scénario 4 – – NISS 4 : Consultation pour un patient qui a plusieurs accords.
        Objectif : Tester l’envoi d’une consultation d’accord pour un patient qui a plusieurs accords pour la
        période consultée, et la réception de la réponse.
        Réponse avec plusieurs accords trouvés.
     */
    @Test
    fun scenario04(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val civic = "3"
        val results = getNisses(4).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=null" +
                "&start=${now.minusYears(1)}"+
                "&end=$now" +
                "&reference=null",AgreementResponse::class.java)
        }



        println("scenario 04 \n====================")

    }

    /*
        Scénario 5 1 – NISS 1 : Consultation rejetée
        Objectif : Tester l’envoi d’une consultation d’accord rejetée par l’OA, et la réception de la réponse
        Reponse : code erreur 181
     */
    @Test
    fun scenario05(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = LocalDateTime.now()

        val civic = "3"
        val results = getNisses(4).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=null" +
                "&start=${now.minusYears(1)}"+
                "&end=$now" +
                "&reference=null",AgreementResponse::class.java)
        }

        println("scenario 05 \n====================")

    }
}
