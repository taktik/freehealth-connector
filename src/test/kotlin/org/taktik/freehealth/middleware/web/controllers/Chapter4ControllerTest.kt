package org.taktik.freehealth.middleware.web.controllers

import org.assertj.core.api.Assertions
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
import org.taktik.freehealth.middleware.MyTestsConfiguration
import java.io.File
import java.time.LocalDateTime
import org.taktik.connector.business.domain.chapter4.AgreementResponse
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

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
        val now = Instant.now().toEpochMilli()

        val results = getNisses(0).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/3?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1&hcpFirstName=$firstName&hcpLastName=$lastName&paragraph=5090000$&start=$now&end=$now&reference=null",AgreementResponse::class.java)
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
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()//DateTime.now().minusYears(1).toInstant()

        val civic = "3"
        val results = getNisses(1).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=null" +
                "&start=$lastYear" +
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
        val dateStart = Instant.parse("2016-05-01T00:00:00.00Z").toEpochMilli()
        val dateEnd = Instant.parse("2016-07-31T00:00:00.00Z").toEpochMilli()


        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"2280100"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(2).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$dateStart" +
                "&end=$dateEnd" +
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
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val civic = "3"
        val results = getNisses(3).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=null" +
                "&start=$lastYear"+
                "&end=$now" +
                "&reference=null",AgreementResponse::class.java)
        }



        println("scenario 04 \n====================")

    }

    /*
        Scénario 5 – NISS 1 : Consultation rejetée
        Objectif : Tester l’envoi d’une consultation d’accord rejetée par l’OA, et la réception de la réponse
        Reponse : code erreur 181
     */
    @Test
    fun scenario05(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/bycnk/${"1798685"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(0).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$lastYear"+
                "&end=$now" +
                "&reference=null",AgreementResponse::class.java)
        }

        println("scenario 05 \n====================")

    }

    /*
        Scénario 6  – NISS 2 : Consultation pour un patient qui n’a pas d’accords.
    Objectif : Tester l’envoi d’une consultation d’accord pour un patient qui n’a pas d’accord pour la date
    consultée, et la réception de la réponse.
     */
    @Test
    fun scenario06(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/bycnk/${"2881076"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(1).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$now"+
                "&end=null" +
                "&reference=null",AgreementResponse::class.java)
        }

        println("scenario 06 \n====================")

    }

    /*
        Scénario 7   – NISS 3 : Consultation pour un patient qui a un accord (modèle E)
        Objectif : Tester l’envoi d’une consultation d’accord pour un patient qui a un accord (modèle E) pour la
        date consultée, et la réception de la réponse.
     */
    @Test
    fun scenario07(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/bycnk/${"3123544"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(1).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$now"+
                "&end=null" +
                "&reference=null",AgreementResponse::class.java)
        }

        println("scenario 07 \n====================")

    }

    /*
        Scénario 8  – NISS 4 : Consultation pour un patient qui a un accord (modèle B ou D)
        Objectif : Tester l’envoi d’une consultation d’accord (modèle B ou D) pour un patient qui a un accord
        pour la date consultée, et la réception de la réponse.
     */
    @Test
    fun scenario08(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/bycnk/${"2881076"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(1).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$now"+
                "&end=null" +
                "&reference=null",AgreementResponse::class.java)
        }

        println("scenario 08 \n====================")

    }

    /*
        Scénario 9  – NISS 1 : Test des : Nouvelle demande – Prolongation - Clôture /// Accord
        – Rejet – Refus – Mise en attente /// (sans annexe).
     */
    @Test
    fun scenario09(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val sixMonth = Instant.now().minus((6*30)+3,ChronoUnit.DAYS).toEpochMilli()
        val threeMonth = Instant.now().minus((3*30)+2,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"1200100"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(0).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$sixMonth"+
                "&end=null" +
                "&reference=1007",AgreementResponse::class.java)
        }
        println("scenario 09 part 1\n====================")


        val results2 = getNisses(0).map {
            this.restTemplate.getForObject("http://localhost:$port/chap4/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName" +
                "&hcpLastName=$lastName" +
                "&paragraph=$paragraph" +
                "&start=$threeMonth"+
                "&end=null" +
                "&reference=1007",AgreementResponse::class.java)
        }
        println("scenario 09 part 2\n====================")
    }
}
