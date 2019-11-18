package org.taktik.freehealth.middleware.web.controllers

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
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse

import java.io.File
import java.time.Instant

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberDataControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val nisses = mapOf(
        100 to listOf("79090527932","50011440637","36081126424","17011612777","790905 217M93","59041744620","68040413141","72060416706","79090527932","97031458484","18010312870","29031620344","31051928426","17011612777","29041042905","02041306532","88082528989","720604015M60","58042644917","13102125767"),
        300 to listOf("61061423190","52072113288","33072519291","17011701166","0121974140661","24110207459","61100611586","57010179489","78052535583","26020325521","49021629574","95031045894","94042125550","17011312473","61031739707","69072541839","72121339535","621085E+12","89041331132","92062115525"),
        500 to listOf("09052224428","50000148253","33040808211","17011631979","0819018511509","48112112044","98092035007","02040505390","84021730491","56040313113","42012609415","51021908641","63040240711","17011809450","33040808211","85010536219","82091014381","810921544219","82080517003","00000000000"),
        600 to listOf("45021812602","72072320188","16020808228","15011820591","0615007639744","54071402460","27101406159","86052640376","45021812602","92070850968","26120934416","51120124705","48062301752","14011618454","42032920621","88091034505","??","0609003009338","54071402460","27101406159"),
        900 to listOf("73050819368","50010403034","19081826340","17012401843","0446347301700","28030407427","81082855769","50010403034","73050819368","26011100128","24060902854","95021931359","91020551103","17012401843","31011514068","96020250510","51010604775","0386015000200","28030407427","11011238210")
    )
    private fun getNisses(idx: Int) = listOf(nisses[100]!![idx], nisses[300]!![idx], nisses[500]!![idx], nisses[600]!![idx], nisses[900]!![idx])

    private fun assertErrors(scenario: String, error: String, results: MemberDataResponse?) {
        println(scenario + "\n====================")
    }

    private fun assertErrors(scenario: String, error: String, results: List<MemberDataResponse?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
        }
    }

    private fun assertErrors(scenario: String, errors: List<String>, results: List<MemberDataResponse?>) {
        println(scenario + "\n====================")
        results.forEachIndexed { index, it ->
        }
    }

    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    @Test
    fun getGeneralInsurability() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        Assertions.assertThat(genIns != null)
    }

    @Test
    fun getGeneralInsurabilityError1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414734"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        assertErrors("Error with digit check","1",genIns.body)
    }

//    @Test
//    fun getGeneralInsurabilityError2() { // the fhc don't accept incorrect ssin
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("Invalid format","2",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError3() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"00000000097"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("OA unknown","3",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError4() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("Invalid format","4",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError5() { // the fhc don't accept empty ssin
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${""}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("INSS Not present","5",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError6() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("OA not present","6",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError7() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("regNrWithMut not present","7",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError8() { // is managed by the fhc server
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("The RequestType is empty","8",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError9() { // is managed by the fhc server
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("This RequestType is not allowed","9",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError10() { // is managed by the fhc server
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}&date=",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("The StartDate is empty","10",genIns.body)
//    }

    @Test
    fun getGeneralInsurabilityError11() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}&date=" + Instant.parse("2099-01-15T00:00:00.00Z").toEpochMilli(),
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        assertErrors("The StartDate is after now","11",genIns.body)
    }

//    @Test
//    fun getGeneralInsurabilityError12() { // is managed by the fhc server
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("The EndDate is empty","12",genIns.body)
//    }

    @Test
    fun getGeneralInsurabilityError13() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}" +
            "&date=" + Instant.parse("2017-01-16T00:00:00.00Z").toEpochMilli() +
            "&endDate=" + Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli(),
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        assertErrors("The EndDate before startDate","13",genIns.body)
    }

    @Test
    fun getGeneralInsurabilityError14() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}" +
            "&date=" + Instant.parse("2017-01-16T00:00:00.00Z").toEpochMilli() +
            "&endDate=" + Instant.parse("9999-01-15T00:00:00.00Z").toEpochMilli(),
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        assertErrors("The request for a period is not allowed","14",genIns.body)
    }

//    @Test
//    fun getGeneralInsurabilityError15() { // is managed by the fhc server
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("The ContactType of the request is empty","15",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError16() { // is managed by the fhc server
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("The ContactType of the request is not alllowed","16",genIns.body)
//    }

    @Test
    fun getGeneralInsurabilityError17() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"00000000097"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        assertErrors("Inss not found on Routing algorithm","17",genIns.body)
    }

//    @Test
//    fun getGeneralInsurabilityError18() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("Invalid Period","18",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError19() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("No IO Found","19",genIns.body)
//    }

//    @Test
//    fun getGeneralInsurabilityError20() { // I don't know how to test
//        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
//        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
//            HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse::class.java, passPhrase)
//        assertErrors("Multi IO Found","20",genIns.body)
//    }

    @Test
    fun getGeneralInsurabilityError21() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}" +
            "&date=" + Instant.parse("1000-01-15T00:00:00.00Z").toEpochMilli(),
                                                HttpMethod.GET, HttpEntity<Void>(createHeaders(null,null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        assertErrors("Invalid Period","21",genIns.body)
    }

    @Test
    fun OneDayScenario01(){//ok partout
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)


        val genIns = getNisses(0).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }

        genIns.forEach {

        }

    }

    @Test
    fun OneDayScenario02(){//ok sauf 600
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)


        val genIns = getNisses(1).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun OneDayScenario03(){//ok
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)


        val genIns = getNisses(2).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun OneDayScenario04(){//ok
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)


        val genIns = getNisses(3).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun OneDayScenario05(){//ok
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)

        val io = listOf<String>("126","305","526","615","910")
        var index=0;

        val genIns = getNisses(4).map{
            this.restTemplate.exchange("http://localhost:$port/mda/${io[index++]}/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase).body

        }


        genIns.map{

        }

    }

    @Test
    fun OneDayScenario06(){//ok
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)


        val genIns = getNisses(5).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun OneDayScenario07(){//ok sauf 300
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)


        val genIns = getNisses(6).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii1" +
                "&hcpSsin=$ssin1" +
                "&hcpName=$name1" +
                "&date=${Instant.parse("2017-01-15T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    //test with medicale house

    @Test
    fun PeriodScenario01(){//ok pour tous sauf le 600
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(7).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{


        }

    }

    @Test
    fun PeriodScenario02(){//ok
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(8).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{


        }

    }

    @Test
    fun PeriodScenario03(){//ok sauf 600
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(9).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{


        }

    }


    @Test
    fun PeriodScenario04(){//tous deceder mais que le 300 dans la periode
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(10).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{


        }

    }

    @Test
    fun PeriodScenario05(){//ok
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(11).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{


        }

    }

    @Test
    fun PeriodScenario06(){//ok sauf 500
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(12).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun PeriodScenario07(){//ok sauf 600 (down)
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(13).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun PeriodScenario08(){//ok
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(14).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun PeriodScenario11(){//ok
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(15).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun PeriodScenario12(){//ok sauf 500 600
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(16).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun PeriodScenario13(){//ok sauf 100 300
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)

        val io = listOf<String>("121","319","509","609","930")
        var index=0;

        val genIns = getNisses(17).map{
            this.restTemplate.exchange("http://localhost:$port/mda/${io[index++]}/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun PeriodScenario14(){//ok
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(18).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{
            it.assertions.map{

            }

        }

    }

    @Test
    fun PeriodScenario15(){//ok sauf 500 600
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii8_3!!, password3!!)


        val genIns = getNisses(19).map{
            this.restTemplate.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii3" +
                "&hcpSsin=$ssin3" +
                "&hcpName=$name3" +
                "&date=${Instant.parse("2016-01-01T00:00:00.00Z").toEpochMilli()}"+
                "&endDate=${Instant.parse("2016-12-31T00:00:00.00Z").toEpochMilli()}"+
                "&hcpQuality=${"medicalhouse"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java).body

        }


        genIns.map{

        }

    }

    @Test
    fun guardPostScenario() {
        val (keystoreId, tokenId, passPhrase) = registerGuardPost(restTemplate!!, port, nihii4!!, password4!!)

//        val nisses = getNisses(0)

        val nisses = listOf("36121015396", "64032764903", "49020508235", "59072957042", "59011214562")

        val results: List<ResponseEntity<MemberDataResponse>> = nisses.map {
            this.restTemplate!!.exchange("http://localhost:$port/mda/$it?hcpNihii=$nihii4&hcpSsin=$ssin4&hcpName=$name4&hcpQuality=${"guardpost"}",
                                         HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), MemberDataResponse::class.java, passPhrase)
        }

        val genIns: List<MemberDataResponse> = results.map { it.body }

        genIns.forEach {










        }
    }
}
