package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
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
import java.io.File
import org.taktik.connector.business.domain.chapter4.AgreementResponse
import org.taktik.connector.business.domain.chapter4.Appendix
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Calendar



@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Chapter4ControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0
    private val gson = Gson()
    //100 to listOf("60060414864", "60091251857", "89031129009", "60060414864","60060414864","60091251857","60060414864","60060414864"),
    //300 to listOf("29041126344", "61030223636", "71052406859", "04051321132","93070556427","93070556427","59010619595","59010619595"),
    //500 to listOf("82091938752", "61011059307", "53091222311", "84062204633","82091938752","82091938752","65082928176","85070555661"),
    //600 to listOf("62081044135", "86021952150", "78082429203", "29100639804","62062557123","62062557123","34022519970","42000143727"),
    //900 to listOf("00032212244", "62052926409", "93080715592", "57010116341","00032212244","62052926409","34110509660","57010116341")

    //CONSULT  100,1 : 60060414864(doc) or 80010505329(xls)
    private val nisses = mapOf(
        100 to listOf("60060414864", "57072844360", "89031129009", "80010505329"),
        300 to listOf("29041126344", "61030223636", "71052406859", "04051321132"),
        500 to listOf("82091938752", "61011059307", "53091222311", "84062204633"),
        600 to listOf("62081044135", "86021952150", "54051249622", "29100639804")
        //900 to listOf("00032212244", "62052926409", "93080715592", "57010116341","00032212244","62052926409","34110509660","57010116341")
    )
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx])

    //DEMAND
    private val nissesD = mapOf(
        100 to listOf("68021229115", "68021229115", "80010505329", "98051722943"),
        300 to listOf("43092504673", "56121131236", "49110605102", "48120830463"),
        500 to listOf("92013131713", "92013120231", "92033110545", "92013119835"),
        600 to listOf("70011634273", "71102435204", "72092536869", "73071907168")
        //900 to listOf("00032212244", "62052926409", "93080715592", "57010116341","00032212244","62052926409","34110509660","57010116341")
    )
    private fun getNissesD(idx: Int) = listOf(nissesD[100]!![idx], nissesD[300]!![idx], nissesD[500]!![idx], nissesD[600]!![idx])



    //private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    /**
     * scénario médecin en hôpital
     */

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
            val res = this.restTemplate.exchange("http://localhost:$port/chap4/consult/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1"+
                "&hcpFirstName=$firstName1"+
                "&hcpLastName=$lastName1"+
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&CivicsVersion=3"+
                "&paragraph=5090000"+
                "&start=$now"+
                "&end=$now",
                HttpMethod.GET,
                HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                String::class.java)

            val agrp = gson.fromJson(res.body, AgreementResponse::class.java)

            agrp
        }

        println("scenario 01 \n====================")

        results.forEach {
            assertThat(it!!.errors!!.size > 0 && it!!.errors!!.first().code == "180")
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

        val now = Instant.now().toEpochMilli()
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)

        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()//DateTime.now().minusYears(1).toInstant()

        val civic = "3"
        val results = getNisses(1).map {
            this.restTemplate.exchange("http://localhost:$port/chap4/consult/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&start=$lastYear" +
                "&end=$now" +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&CivicsVersion=$civic"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male",
                HttpMethod.GET,
                HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                AgreementResponse::class.java)
        }

        println("scenario 02 \n====================")

        results.forEach {
            assertThat(it!!.body!!.errors!!.size == 0 && it!!.body!!.transactions!!.size == 0)
        }
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
        val dateStart = Instant.parse("2016-03-01T00:00:00.00Z").toEpochMilli()
        val dateEnd = Instant.parse("2016-08-30T00:00:00.00Z").toEpochMilli()

        //4740000 marhce avec ce numero
        //val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"2280100"}/${"fr"}", Array<ParagraphPreview>::class.java)
        //val civic = paragraphDesc.first().paragraphVersion
        //val paragraph = paragraphDesc.first().paragraphName


        val results = getNisses(2).map {
            val res = this.restTemplate.exchange("http://localhost:$port/chap4/consult/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male" +
                //"&paragraph=$paragraph" +
                "&start=$dateStart" +
                "&end=$dateEnd"
                //+ "&civicsVersion=$civic"
                ,
                HttpMethod.GET,
                HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                AgreementResponse::class.java)
            res
        }



        println("scenario 03 \n====================")
        results.forEach {
            assertThat(it!!.body!!.content!!.size == 1 && it!!.body!!.warnings!!.size >= 1 && it!!.body!!.warnings!!.first().code == "601")
        }

        //Assertions.assertThat(results!!.content!!.size).isEqualTo(1)
        //Assertions.assertThat(results!!.warnings!!.first().cds[0].value).isEqualTo("601")

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

        val civic = "14"
        val results = getNissesD(3).map {
            val res = this.restTemplate.exchange("http://localhost:$port/chap4/consult/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&start=$lastYear"+
                "&end=$now" +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&civicsVersion=$civic",
                HttpMethod.GET,
                HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                AgreementResponse::class.java)
            res
        }



        println("scenario 04 \n====================")
        results.forEach {
            assertThat(it!!.body!!.transactions!!.size > 1)
        }
        //        Assertions.assertThat(results!!.errors).isNull()
//        Assertions.assertThat(results!!.content!!.size).isGreaterThan(1)

    }

    /**
     * fin scénario médecin en hôpital
     */
    /**
     *  scénario pharmacien en hôpital
     */
    /*
        Scénario 5 – NISS 5 : Consultation rejetée
        Objectif : Tester l’envoi d’une consultation d’accord rejetée par l’OA, et la réception de la réponse
        Reponse : code erreur 181
     */
    @Test
    fun scenario05(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/bycnk/${"1798685"}/${"fr"}", Array<ParagraphPreview>::class.java)


        val results = getNisses(4)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&start=$lastYear"+
                "&end=$now"  +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&civicsVersion=3"+
                "&reference=${"1798685"}",AgreementResponse::class.java)
        }

        println("scenario 05 \n====================")
        Assertions.assertThat(results!!.errors).isNotNull.isNotEmpty
        Assertions.assertThat(results!!.errors!!.first().code).isEqualTo("181")

    }

    /*
        Scénario 6  – NISS 6 : Consultation pour un patient qui n’a pas d’accords.
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

        val results = getNisses(5)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&start=$now"+
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&civicsVersion=$civic",AgreementResponse::class.java)
        }

        println("scenario 06 \n====================")
        Assertions.assertThat(results!!.errors).isNull()
        Assertions.assertThat(results!!.content!!.size).isEqualTo(0)
    }

    /*
        Scénario 7   – NISS 7 : Consultation pour un patient qui a un accord (modèle E)
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

        val results = getNisses(6)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&start=$now"+
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&civicsVersion=$civic",AgreementResponse::class.java)
        }

        println("scenario 07 \n====================")
        Assertions.assertThat(results!!.errors).isNull()
        Assertions.assertThat(results!!.transactions!!.size).isNotEqualTo(0);
        Assertions.assertThat(results!!.transactions[0].unitNumber).isNotEqualTo(0)
    }

    /*
        Scénario 8  – NISS 8 : Consultation pour un patient qui a un accord (modèle B ou D)
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

        val results = getNisses(7)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&start=$now"+
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&civicsVersion=$civic",AgreementResponse::class.java)
        }

        println("scenario 08 \n====================")
        Assertions.assertThat(results!!.errors).isNull()
        Assertions.assertThat(results!!.transactions[0].end).isGreaterThan(results!!.transactions[0].start)
    }

    /**
     * fin scénario pharmacien en hôpital
     */
    /**
     *  scénario accord medecin
     */

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

        val results = getNisses(0)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"1007"}" +
                "&incomplete=false"+
                "&start=$sixMonth", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }
        Assertions.assertThat(results!!.errors).isNull()
        Assertions.assertThat(results!!.transactions[0].end).isGreaterThan(results!!.transactions[0].start)

        val results2 = getNisses(0)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"1007"}" +
                "&incomplete=false"+
                "&start=$threeMonth", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }
        Assertions.assertThat(results2!!.errors).isNotEmpty.isNotNull
        Assertions.assertThat(results2!!.errors).isEqualTo(401)

        val results3 = getNisses(0)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"1008"}" +
                "&incomplete=false"+
                "&start=${Instant.parse("${results.transactions[0].end}").plus(5,ChronoUnit.DAYS).toEpochMilli()}"+
                "&decisionReference=${results.transactions[0].decisionReference}", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }

        val results4 = getNisses(0)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"1015"}" +
                "&incomplete=false"+
                "&start=${Instant.parse("${results.transactions[0].end}").plus(1,ChronoUnit.DAYS).toEpochMilli()}"+
                "&end=${Instant.parse("${results.transactions[0].end}").plus(1,ChronoUnit.DAYS).plus(6,ChronoUnit.MONTHS).toEpochMilli()}" +
                "&decisionReference=${results.transactions[0].decisionReference}", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }

        val results5 = getNisses(0)[2].let{
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"1008"}" +
                "&incomplete=true"+
                "&start=${Instant.parse("${results.transactions[0].end}").plus(1,ChronoUnit.DAYS).toEpochMilli()}"+
                "&decisionReference=${results.transactions[0].decisionReference}", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }

        val results6 = getNisses(0)[2].let {
            this.restTemplate.delete("http://localhost:$port/chap4/new/$it/$civic/${"closure"}/$paragraph?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"1008"}" +
                "&incomplete=false"+
                "&decisionReference=${results5.transactions[0].decisionReference}"
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
        val now = Instant.now().plus(3,ChronoUnit.DAYS).toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"5130000"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName

        val results = getNisses(1)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"11897"}" +
                "&incomplete=false"+
                "&start=$now", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
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

        val resultsRequest = getNisses(2)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"154"}" +
                "&incomplete=true"+
                "&start=$threeDays", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }
        Assertions.assertThat(resultsRequest!!.errors).isNull()
        Assertions.assertThat(resultsRequest!!.transactions[0].decisionReference).isNotEmpty().isNotNull()

        val resultsCancel = getNisses(2)[0].let {
            this.restTemplate.delete("http://localhost:$port/chap4/cancel/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&decisionReference=${resultsRequest!!.transactions[0].decisionReference}",AgreementResponse::class.java)
        }

        println("scenario 11 \n====================")
        //Assertions.assertThat(resultsCancel!!.transactions[0].start).isAfter(now)
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

        val resultsRequest = getNisses(3)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"11897"}" +
                "&incomplete=false"+
                "&start=$now", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }

        val resultsRequest2 = getNisses(3)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"complimentaryannexe"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"11897"}" +
                "&incomplete=false"+
                "&start=$now"+
                "&ioRequestReference=${resultsRequest!!.transactions[0].ioRequestReference},", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }

        val resultsRequest3 = getNisses(3)[2].let {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"complimentaryannexe"}/$paragraph?keystoreIdhcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&paragraph=$paragraph" +
                "&civicsVersion=$civic" +
                "&verses=${"11897"}" +
                "&incomplete=true"+
                "&start=$now"+
                "&ioRequestReference=${resultsRequest!!.transactions[0].ioRequestReference},", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
        }

        println("scenario 12 \n====================")

    }

    /**
     * fin scénario accord medecin
     */
    /**
     *  autres scénarios pharmacien et medecin
     */
    /*
        Scénario 13 – NISS 1 : Consultation rejetée
        Objectif : Tester l’envoi d’une consultation d’accord rejetée par l’OA, et la réception de la réponse.
        Reponse : code erreur 157
     */
    @Test
    fun scenario13(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val start = Instant.parse("${(LocalDateTime.now().year-2)}-01-01T00:00:00.00Z").toEpochMilli()
        val end = Instant.parse("${(LocalDateTime.now().year-1)}-12-31T00:00:00.00Z").toEpochMilli()


        val results = getNisses(0)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&start=$start"+
                "&end=$end"  +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male",AgreementResponse::class.java)
        }

        println("scenario 13 \n====================")
        Assertions.assertThat(results!!.errors).isNotNull.isNotEmpty
        Assertions.assertThat(results!!.errors!!.first().code).isEqualTo("157")

    }
    /*
        Scénario 14   – NISS 1 : Consultation rejetée
        Objectif : Tester l’envoi d’une consultation d’accord rejetée par l’OA, et la réception de la réponse.
        erreur 130
     */
    @Test
    fun scenario14(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val lastYear = Instant.now().minus(365,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/bycnk/${"1798685"}/${"fr"}", Array<ParagraphPreview>::class.java)


        val results = getNisses(4)[2].let {
            this.restTemplate.getForObject("http://localhost:$port/chap4/consult/$it?keystoreId=$keystoreId" +
                "&tokenId=$tokenId" +
                "&passPhrase=$passPhrase" +
                "&hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&start=$lastYear"+
                "&end=$now"  +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&civicsVersion=3"+
                "&reference=${"1798685"}",AgreementResponse::class.java)
        }

        println("scenario 05 \n====================")
        Assertions.assertThat(results!!.errors).isNotNull.isNotEmpty
        Assertions.assertThat(results!!.errors!!.first().code).isEqualTo("130")

    }


    @Test
    fun scenarioSAM(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val now = Instant.now().toEpochMilli()
        val sixMonth = Instant.now().minus((6*30)+3,ChronoUnit.DAYS).toEpochMilli()
        val threeMonth = Instant.now().minus((3*30)+2,ChronoUnit.DAYS).toEpochMilli()

        val paragraphDesc = this.restTemplate!!.getForObject("http://localhost:$port/chap4/sam/search/${"1200100"}/${"fr"}", Array<ParagraphPreview>::class.java)
        val civic = paragraphDesc.first().paragraphVersion
        val paragraph = paragraphDesc.first().paragraphName
        val app = Appendix(0,0, ByteArray(0) )
        val results = getNisses(1).map {
            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph" +
                "?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpFirstName=$firstName1" +
                "&hcpLastName=$lastName1" +
                "&patientDateOfBirth=${"19"+it.substring(0,6)}"+
                "&patientFirstName=ANTOINE"+
                "&patientLastName=DUCHATEAU"+
                "&patientGender=male"+
                "&verses=${"42817"}" +
                "&incomplete=false"+
                "&start=$sixMonth"+
                "&end=$now",
                HttpMethod.POST,
                HttpEntity<List<Appendix>>(listOf(app),createHeaders(null, null, keystoreId, tokenId, passPhrase)),
                AgreementResponse::class.java)
        }


        Assertions.assertThat(results != null)

//        Assertions.assertThat(results!!.errors).isNull()
//        Assertions.assertThat(results!!.transactions[0].end).isAfter(results!!.transactions[0].start)
//
//        val results2 = getNisses(0)[2].let {
//            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"newrequest"}/$paragraph?hcpNihii=$nihii1" +
//                "&hcpSsin=$ssin1" +
//                "&hcpFirstName=$firstName1" +
//                "&hcpLastName=$lastName1" +
//                "&paragraph=$paragraph" +
//                "&civicsVersion=$civic" +
//                "&verses=${"1007"}" +
//                "&incomplete=false"+
//                "&start=$threeMonth", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
//        }
//        Assertions.assertThat(results2!!.errors).isNotEmpty.isNotNull
//        Assertions.assertThat(results2!!.errors).isEqualTo(401)
//
//        val results3 = getNisses(0)[2].let {
//            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?hcpNihii=$nihii1" +
//                "&hcpSsin=$ssin1" +
//                "&hcpFirstName=$firstName1" +
//                "&hcpLastName=$lastName1" +
//                "&paragraph=$paragraph" +
//                "&civicsVersion=$civic" +
//                "&verses=${"1008"}" +
//                "&incomplete=false"+
//                "&start=${Instant.parse("${results.transactions[0].end}").plus(5,ChronoUnit.DAYS).toEpochMilli()}"+
//                "&decisionReference=${results.transactions[0].decisionReference}", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
//        }
//
//        val results4 = getNisses(0)[2].let {
//            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?hcpNihii=$nihii1" +
//                "&hcpSsin=$ssin1" +
//                "&hcpFirstName=$firstName1" +
//                "&hcpLastName=$lastName1" +
//                "&paragraph=$paragraph" +
//                "&civicsVersion=$civic" +
//                "&verses=${"1015"}" +
//                "&incomplete=false"+
//                "&start=${Instant.parse("${results.transactions[0].end}").plus(1,ChronoUnit.DAYS).toEpochMilli()}"+
//                "&end=${Instant.parse("${results.transactions[0].end}").plus(1,ChronoUnit.DAYS).plus(6,ChronoUnit.MONTHS).toEpochMilli()}" +
//                "&decisionReference=${results.transactions[0].decisionReference}", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
//        }
//
//        val results5 = getNisses(0)[2].let{
//            this.restTemplate.exchange("http://localhost:$port/chap4/new/$it/$civic/${"extension"}/$paragraph?hcpNihii=$nihii1" +
//                "&hcpSsin=$ssin1" +
//                "&hcpFirstName=$firstName1" +
//                "&hcpLastName=$lastName1" +
//                "&paragraph=$paragraph" +
//                "&civicsVersion=$civic" +
//                "&verses=${"1008"}" +
//                "&incomplete=true"+
//                "&start=${Instant.parse("${results.transactions[0].end}").plus(1,ChronoUnit.DAYS).toEpochMilli()}"+
//                "&decisionReference=${results.transactions[0].decisionReference}", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),AgreementResponse::class.java).body
//        }
//
//        val results6 = getNisses(0)[2].let {
//            this.restTemplate.delete("http://localhost:$port/chap4/new/$it/$civic/${"closure"}/$paragraph?keystoreId=$keystoreId" +
//                "&tokenId=$tokenId" +
//                "&passPhrase=$passPhrase" +
//                "&hcpNihii=$nihii1" +
//                "&hcpSsin=$ssin1" +
//                "&hcpFirstName=$firstName1" +
//                "&hcpLastName=$lastName1" +
//                "&paragraph=$paragraph" +
//                "&civicsVersion=$civic" +
//                "&verses=${"1008"}" +
//                "&incomplete=false"+
//                "&decisionReference=${results5.transactions[0].decisionReference}"
//                ,AgreementResponse::class.java)
//        }
    }

}
