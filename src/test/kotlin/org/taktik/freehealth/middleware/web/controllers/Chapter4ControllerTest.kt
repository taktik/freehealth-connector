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
import org.taktik.connector.business.domain.chapter4.AgreementResponse
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import java.time.Instant
import java.time.temporal.ChronoUnit

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Chapter4ControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    private val nisses = mapOf(100 to listOf("73052005540", "84101727579", "39091706120", "29041433972", "97061960828", "09031001094"),
        300 to listOf("17031506487", "88022631093", "87052226861", "63042408660", "37061311820", "87120924439"),
        500 to listOf("13070421120", "12070321327", "69070608470", "74010414733","58031245635", "46111636603", "09041004003"),
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


        val results = getNisses(3)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/3?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1&hcpFirstName=$firstName1&hcpLastName=$lastName1&patientDateOfBirth=${"19"+it.substring(0,6)}&patientFirstName=ANTOINE&patientLastName=DUCHATEAU&patientGender=male&paragraph=5090000&start=$now&end=$now",AgreementResponse::class.java)
        }

        println("scenario 01 \n====================")
        /*results.forEachIndexed { index, it ->
            Assertions.assertThat(it.errors).isNotEmpty
            Assertions.assertThat(it.errors).isEqualTo("180")
        }*/

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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=null" +
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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
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
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it/$civic?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
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
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"1007"}" +
                "&incomplete=false"+
                "&start=$sixMonth"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }
        println("scenario 09 part 1\n====================")


        val results2 = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"1007"}" +
                "&incomplete=false"+
                "&start=$threeMonth"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }
        println("scenario 09 part 2\n====================")

        val results3 = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"1008"}" +
                "&incomplete=false"+
                "&start=${results.first().content}"+
                "&end=null" +
                "&decisionReference=${results.first().content}" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        val results4 = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"1015"}" +
                "&incomplete=false"+
                "&start=${results.first().content}"+
                "&end=${results.first().content}" +
                "&decisionReference=${results.first().content}" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        val results5 = getNisses(0).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"1008"}" +
                "&incomplete=true"+
                "&start=${results.first().content}"+
                "&end=null" +
                "&decisionReference=${results.first().content}" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        val results6 = getNisses(0).map {
            this.restTemplate.delete("http://localhost:$port/chap4/new/$it/$civic/${"closure"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&decisionReference=${results5.first().content}"
                ,AgreementResponse::class.java)
        }
    }

    /*
        Scénario 10 – NISS 2 : Nouvelle demande avec annexe mise en attente d’une décision
        ultérieure du médecin conseil
        Objectif : Tester l’envoi d’une nouvelle demande d’accord avec annexe mise à la disposition du médecin
        conseil, et la réception de la réponse
     */
    @Test
    fun scenario10(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"5130000"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(1).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"11897"}" +
                "&incomplete=false"+
                "&start=$now"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        println("scenario 10 \n====================")

    }

    /*
        Scénario 11 – NISS 3 :Annulation d’un accord.
        Objectif : Tester l’envoi d’une annulation d’un accord, et la réception de la réponse.
     */
    @Test
    fun scenario11(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)

        val now = Instant.now().toEpochMilli()
        val threeDays = Instant.now().plus(3,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"160101"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val resultsRequest = getNisses(2).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"154"}" +
                "&incomplete=true"+
                "&start=$threeDays"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        val resultsCancel = getNisses(2).map {
            this.restTemplate.delete("http://localhost:$port/chap4/cancel/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&decisionReference=${resultsRequest.first().content}"+
                "&iorequestReference=null",AgreementResponse::class.java)
        }

        println("scenario 11 \n====================")

    }


    /*
        Scénario 12 – NISS 4 : Nouvelle demande avec annexe envoyée en plusieurs étapes par
        le médecin prescripteur
     */
    @Test
    fun scenario12(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)

        val now = Instant.now().toEpochMilli()
        val threeDays = Instant.now().plus(3,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"5130000"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val resultsRequest = getNisses(3).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"11897"}" +
                "&incomplete=false"+
                "&start=$now"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=null"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        val resultsRequest2 = getNisses(3).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"complimentaryannexe"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"11897"}" +
                "&incomplete=false"+
                "&start=$now"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=${resultsRequest.first().content},"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        val resultsRequest3 = getNisses(3).map {
            this.restTemplate.postForObject("http://localhost:$port/chap4/new/$it/$civic/${"complimentaryannexe"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&verses=${"11897"}" +
                "&incomplete=true"+
                "&start=$now"+
                "&end=null" +
                "&decisionReference=null" +
                "&ioRequestReference=${resultsRequest.first().content},"+
                "&appendices=null",{},AgreementResponse::class.java)
        }

        println("scenario 12 \n====================")

    }
}
