package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListResponse
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class EhboxControllerTest : EhealthTest(){

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun getInfo(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val ehboxInfo =  this.restTemplate.getForObject("http://localhost:$port/ehbox?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(ehboxInfo != null)
    }

    @Test
    fun loadMessage() {
        val boxType = "INBOX"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val messages = this.restTemplate.getForObject("http://localhost:$port/ehbox/$boxType?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&limit=100", String::class.java)
        Assertions.assertThat(messages != null)
    }

    @Test
    fun getFullMessage(){
        val idMessage = "1000010173227"
        val boxType = "INBOX"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val fullMessages = this.restTemplate.getForObject("http://localhost:$port/ehbox/$boxType/$idMessage?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(fullMessages != null)
    }

    @Test
    fun moveMessage(){
        val source  = "INBOX"
        val destination  = "BININBOX"
        val messageIds = arrayListOf("1000010173227")

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val message = this.restTemplate.postForObject("http://localhost:$port/ehbox/move/from/$source/to/$destination?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", messageIds, String::class.java)
        Assertions.assertThat(message != null)
    }

    @Test
    fun deleteMessage(){
        val messageIds = arrayListOf("1000010173227")
        val source = "BININBOX"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val message = this.restTemplate.postForObject("http://localhost:$port/ehbox/move/from/$source?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", messageIds, String::class.java)
        Assertions.assertThat(message != null)
    }
}