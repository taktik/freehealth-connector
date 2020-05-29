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

    @Test
    fun sendSubscriptionRequest(){
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii5!!, password5!!)
        val subscription = this.restTemplate.exchange("http://localhost:$port/mhm/send/${"92092412781"}/verbose?hcpNihii=$nihii5&hcpName=$name5&hcpCbe=$cbe5" +
            "&patientFirstName=${"Maxime"}&patientLastName=${"Mennechet"}&patientGender=${"male"}&io=${""}" +
            "&startDate="+(Instant.parse("2017-01-16T00:00:00.00Z").toEpochMilli() / 1000).toInt()+"&isTrial=${"false"}" +
            "&signatureType=${""}",
        HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)

        //assertErrors("Start subscription", subscription.body)

    }

}
