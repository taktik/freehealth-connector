package org.taktik.freehealth.middleware.web.controllers


import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage


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
        val ehboxInfo =  this.restTemplate.exchange("http://localhost:$port/ehbox", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(ehboxInfo != null)
    }

    @Test
    fun loadMessage() {
        val boxType = "INBOX"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val messages = this.restTemplate.exchange("http://localhost:$port/ehbox/$boxType?limit=100", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(messages != null)
    }

    @Test
    fun getFullMessage(){
        val idMessage = "1000010173227"
        val boxType = "INBOX"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val fullMessages = this.restTemplate.exchange("http://localhost:$port/ehbox/$boxType/$idMessage", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(fullMessages != null)
    }

    @Test
    fun moveMessage(){
        val source  = "INBOX"
        val destination  = "BININBOX"
        val messageIds = arrayListOf("1000010173227")

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val message = this.restTemplate.exchange("http://localhost:$port/ehbox/move/from/$source/to/$destination", HttpMethod.GET, HttpEntity<List<String>>(messageIds, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(message != null)
    }

    @Test
    fun deleteMessage(){
        val messageIds = arrayListOf("1000010173227")
        val source = "BININBOX"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val message = this.restTemplate.exchange("http://localhost:$port/ehbox/move/from/$source", HttpMethod.GET, HttpEntity<List<String>>(messageIds, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(message != null)
    }

    @Test
    fun sendMessage(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val msg = Gson().fromJson(
            """{
  "id": null,
  "publicationId": null,
  "publicationDateTime": 20181001,
  "expirationDateTime": 20190101,
  "customMetas": {},
  "document": {
    "title": "C’est un test",
    "textContent": "Body text",
    "filename": "rsp.txt",
    "mimeType": "text/plain",
    "signing": null
  },
  "freeText": null,
  "freeInformationTableTitle": null,
  "freeInformationTableRows": {},
  "patientInss": null,
  "annex": [],
  "copyMailTo": [],
  "documentTitle": null,
  "annexList": [],
  "useReceivedReceipt": false,
  "useReadReceipt": false,
  "hasAnnex": false,
  "hasFreeInformations": false,
  "important": true,
  "encrypted": false,
  "usePublicationReceipt": false,
  "destinations": [
    {
      "identifierType": {
        "type": "NIHII"
      },
      "id": "19234011",
      "quality": "DOCTOR",
      "applicationId": null,
      "lastName": null,
      "firstName": null,
      "organizationName": null,
      "personInOrganisation": null
    }
  ],
  "sender": {
    "identifierType": {
      "type": "NIHII"
    },
    "id": "11478761",
    "quality": "DOCTOR",
    "applicationId": null,
    "lastName": null,
    "firstName": null,
    "organizationName": null,
    "personInOrganisation": null
  }
}""".trimIndent(), DocumentMessage::class.java)
        val message = this.restTemplate.exchange("http://localhost:$port/ehbox?publicationReceipt=true&receptionReceipt=true&readReceipt=true", HttpMethod.POST, HttpEntity<DocumentMessage>(msg, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(message != null)
    }
}
