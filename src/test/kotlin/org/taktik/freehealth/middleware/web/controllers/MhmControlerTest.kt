package org.taktik.freehealth.middleware.web.controllers

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
import java.time.Instant

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MhmControlerTest : EhealthTest() {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val nisses = mapOf(
        300 to listOf("92092412781"),
        500 to listOf(),
        600 to listOf("53000196917", "36060311709", "39060737582", "40050327525", "45120834061", "47091243858", "49071920512", "39050928508", "40012324806", "43020427933"),
        900 to listOf("62032746746", "63090906482", "62052926409", "70062830279", "71083021841", "79083136235", "72080227668", "72022128232", "78031410864", "91101553229")
    )

    private val ioMemberShip = mapOf(
        100 to listOf("1998012024655", "1985081945540", "1985090148275", "2016020716504", "1998042225111", "1990061580793", "2008122707872", "1997031047621", "1965050929658", "2019050711541")
    )

    @Test
    fun sendSubscriptionRequest(){
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii5!!, password5!!)
        val subscription = this.restTemplate.exchange("http://localhost:$port/mhm/sendSubscription?hcpNihii=$nihii5&hcpName=$name5&hcpCbe=$cbe5" +
            "&patientSsin=${"92092412781"}&patientFirstName=${"Maxime"}&patientLastName=${"Mennechet"}&patientGender=${"male"}&io=${"300"}" +
            "&startDate="+20200605+"&isTrial=${"false"}" +
            "&signatureType=${"holder-eid"}&isRecovery=${"false"}&isTestForNotify=${"false"}",
        HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)

        //assertErrors("Start subscription", subscription.body)

    }


    @Test
    fun cancelSubscription(){
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii5!!, password5!!)
        val subscription = this.restTemplate.exchange("http://localhost:$port/mhm/cancelSubscription?hcpNihii=$nihii5&hcpName=$name5&hcpCbe=$cbe5" +
            "&patientSsin=${"92092412781"}&patientFirstName=${"Maxime"}&patientLastName=${"Mennechet"}&patientGender=${"male"}&io=${"300"}" +
            "&reference=${"31720200605000000727"}",
        HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

    @Test
    fun notifySubscriptionClosure(){
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii5!!, password5!!)
        val subscription = this.restTemplate.exchange("http://localhost:$port/mhm/notifySubscriptionClosure?hcpNihii=$nihii5&hcpName=$name5&hcpCbe=$cbe5" +
            "&patientSsin=${"92092412781"}&patientFirstName=${"Maxime"}&patientLastName=${"Mennechet"}&patientGender=${"male"}&io=${"300"}"+
            "&reference=${"31720200605000000424"}&endDate="+20200605+"&reason=${"202"}&decisionType=${"patientdecision"}",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

}
