package org.taktik.freehealth.middleware.web.controllers

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HubControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    /*
        PROD
        Réseau Santé Wallon : 1990000035 : https://hub.reseausantewallon.be/standards/hubservices/production/intrahub/intrahubservice.asmx
        Réseau Santé Bruxellois : 1990000728 : https://hub.abrumet.be/standards/hubservices/production/intrahub/intrahubservice.asmx
        Vitalink :  : https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService

        ACCEPTANCE
        Réseau Santé Wallon : 1990000035 : https://hub.reseausantewallon.be/standards/hubservices/intrahub/intrahubservice.asmx
        Réseau Santé Bruxellois : 1990000728 : https://hub.abrumet.be/standards/hubservices/intrahub/intrahubservice.asmx
        Vitalink :  : https://vitalink-acpt.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService

     */

    @Test
    fun getPatient() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getPatientResult = this.restTemplate.getForObject("http://localhost:$port/hub/patient/${"73032929895"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(getPatientResult != null && getPatientResult.length>2 && getPatientResult.startsWith("["))
    }

    @Test
    fun putPatient() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val putPatientResult = this.restTemplate.postForObject("http://localhost:$port/hub/patient/${"Duchâteau"}/${"73032929895"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&firstName=Antoine&dateOfBirth=19740104&gender=male", null, String::class.java)
        Assertions.assertThat(putPatientResult != null && putPatientResult.length>2 && putPatientResult.startsWith("["))
    }

    @Test
    fun getHcpConsent() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getPatientResult = this.restTemplate.getForObject("http://localhost:$port/hub/hcpconsent/$nihii1?hcpSsin=$ssin1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(getPatientResult != null && getPatientResult.length>2 && getPatientResult.startsWith("["))
    }

    @Test
    fun getPatientConsent() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getPatientResult = this.restTemplate.getForObject("http://localhost:$port/hub/consent/${"73032929895"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
            Assertions.assertThat(getPatientResult != null && getPatientResult.length>2 && getPatientResult.startsWith("["))
    }

    @Test
    fun putPatientConsent() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val putPatientResult = this.restTemplate.postForObject("http://localhost:$port/hub/consent/${"73032929895"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&eidCardNumber=592363302467", null, String::class.java)
        Assertions.assertThat(putPatientResult != null && putPatientResult.length>2 && putPatientResult.startsWith("["))
    }

    @Test
    fun getTransactionList() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getTransactionsListResult = this.restTemplate.getForObject("http://localhost:$port/hub/list/${"73032929895"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(getTransactionsListResult != null && getTransactionsListResult.length>2 && getTransactionsListResult.startsWith("["))
    }

    @Test
    fun getTransactionListVitalink() {
        val endpoint = "https://vitalink-acpt.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getTransactionsListResult = this.restTemplate.getForObject("http://localhost:$port/hub/list/${"72022102793"}?hcpNihii=$nihii1&hcpFirstName=$firstName1&hcpLastName=$lastName1&hcpSsin=$ssin1&breakTheGlassReason=azertyuiopqsdfghjklm&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(getTransactionsListResult != null && getTransactionsListResult.length>2 && getTransactionsListResult.startsWith("["))
    }
    @Test
    fun getTransaction() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getTransactionResult = this.restTemplate.getForObject("http://localhost:$port/hub/t/${"73032929895"}/1.0/RSWID?id=762&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpFirstName=$firstName1&hcpLastName=$lastName1&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(getTransactionResult != null && getTransactionResult.length>2 && getTransactionResult.startsWith("["))
    }

    @Test
    fun getTransactionVitalink() {
        val endpoint = "https://vitalink-acpt.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService"
        val trValue  = "/subject/72022102793/sumehr/59065/44" //"/subject/82121210976/sumehr/99634/1"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getTransactionResult = this.restTemplate.getForObject("http://localhost:$port/hub/t/${"72022102793"}/1.0/vitalinkuri?hcpNihii=${nihii1}&hcpFirstName=$firstName1&hcpLastName=$lastName1&hcpSsin=${ssin1}&breakTheGlassReason=azertyuiopqsdfghjklm&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&id={trValue}", String::class.java, trValue)
        Assertions.assertThat(getTransactionResult != null && getTransactionResult.length>2 && getTransactionResult.startsWith("["))
    }

    @Test
    fun revokeTransactionVitalink() {
        val endpoint = "https://vitalink-acpt.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService"
        val trValue  = "/subject/72022102793/sumehr/59065/44" //"/subject/82121210976/sumehr/99634/1"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getTransactionResult = this.restTemplate.delete("http://localhost:$port/hub/t/${"72022102793"}/1.0/vitalinkuri?hcpNihii=${nihii1}&hcpFirstName=$firstName1&hcpLastName=$lastName1&hcpSsin=${ssin1}&breakTheGlassReason=azertyuiopqsdfghjklm&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase&id={trValue}", trValue)
        Assertions.assertThat(getTransactionResult != null)// && getTransactionResult.length>2 && getTransactionResult.startsWith("["))
    }

    @Test
    fun putTransaction() {
        val endpoint = "https://vitalink-acpt.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val putTransactionResult = this.restTemplate.postForObject("http://localhost:$port/hub/t/${"1990001916"}/${"73032929895"}?hcpLastName=${lastName1}&hcpFirstName=${firstName1}&hcpNihii=${nihii1}&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpZip=8300&hubApplication=${"VITALINKGATEWAY"}&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String(MyTestsConfiguration::class.java.getResourceAsStream("sumehr.xml").readBytes(), Charsets.UTF_8), String::class.java)
        Assertions.assertThat(putTransactionResult != null && putTransactionResult.length>2 && putTransactionResult.startsWith("["))
    }

    @Test
    fun putTransactionRsw() {
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val putTransactionResult = this.restTemplate.postForObject("http://localhost:$port/hub/t/${"1990000035"}/${"73032929895"}?hcpLastName=${lastName1}&hcpFirstName=${firstName1}&hcpNihii=${nihii1}&hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpZip=8300&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String(MyTestsConfiguration::class.java.getResourceAsStream("sumehr.xml").readBytes(), Charsets.UTF_8), String::class.java)
        Assertions.assertThat(putTransactionResult != null && putTransactionResult.length>2 && putTransactionResult.startsWith("["))
    }

    @Test
    fun getTransactionSet(){
        val endpoint = "https://acchub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val getTransactionSetResult = this.restTemplate.getForObject("http://localhost:$port/hub/ts/${"73032929895"}/1.0/RSWID?id=890&hcpNihii=${nihii1}&hcpFirstName=$firstName1&hcpLastName=$lastName1&hcpSsin=${ssin1}&hcpZip=1000&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String::class.java)
        Assertions.assertThat(getTransactionSetResult != null && getTransactionSetResult.length>2 && getTransactionSetResult.startsWith("["))
    }


    @Test
    fun putTransactionSet(){
        val endpoint = "https://vitalink-acpt.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService"
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val putTransactionSetResult = this.restTemplate.postForObject("http://localhost:$port/hub/ts/${"1990001916"}/${"86081408794"}?hcpNihii=${nihii1}&hcpSsin=${ssin1}&hcpZip=8300&hubApplication=${"VITALINKGATEWAY"}&endpoint=$endpoint&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase=$passPhrase", String(MyTestsConfiguration::class.java.getResourceAsStream("medicationScheme.xml").readBytes(), Charsets.UTF_8), String::class.java)
        Assertions.assertThat(putTransactionSetResult != null && putTransactionSetResult.length>2 && putTransactionSetResult.startsWith("["))
    }
}
