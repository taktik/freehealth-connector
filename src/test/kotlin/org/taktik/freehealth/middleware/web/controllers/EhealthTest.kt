package org.taktik.freehealth.middleware.web.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.util.LinkedMultiValueMap
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.UUIDType
import java.util.*

@TestPropertySource(locations = arrayOf("classpath:/test.properties"))
open class EhealthTest {

    /* test.properties should be added in src/test/resources with 4 lines :
    org.taktik.icure.keystore1.ssin=79121430944 //Ssin dr 1
    org.taktik.icure.keystore1.password=<keystore pass dr 1>
    org.taktik.icure.keystore2.ssin=69022608813 //Ssin dr 2
    org.taktik.icure.keystore2.password=<keystore pass dr 2>
    */
    /* acc keystores (.acc-p12) should be copied to src/test/resources/org/taktik/freehealth/middleware */
    @Value("\${org.taktik.icure.keystore1.cbe}") var cbe1: String? = null
    @Value("\${org.taktik.icure.keystore2.cbe}") var cbe2: String? = null
    @Value("\${org.taktik.icure.keystore3.cbe}") var cbe3: String? = null
    @Value("\${org.taktik.icure.keystore1.nihii}") var nihii1: String? = null
    @Value("\${org.taktik.icure.keystore2.nihii}") var nihii2: String? = null
    @Value("\${org.taktik.icure.keystore3.nihii}") var nihii3: String? = null
    @Value("\${org.taktik.icure.keystore3.nihii8}") var nihii8_3: String? = null
    @Value("\${org.taktik.icure.keystore1.ssin}") var ssin1: String? = null
    @Value("\${org.taktik.icure.keystore2.ssin}") var ssin2: String? = null
    @Value("\${org.taktik.icure.keystore3.ssin}") var ssin3: String? = null
    @Value("\${org.taktik.icure.keystore1.password}") var password1: String? = null
    @Value("\${org.taktik.icure.keystore2.password}") var password2: String? = null
    @Value("\${org.taktik.icure.keystore3.password}") var password3: String? = null
    @Value("\${org.taktik.icure.keystore1.name}") var name1: String? = null
    @Value("\${org.taktik.icure.keystore2.name}") var name2: String? = null
    @Value("\${org.taktik.icure.keystore3.name}") var name3: String? = null
    @Value("\${org.taktik.icure.keystore1.firstName}") var firstName1: String? = null
    @Value("\${org.taktik.icure.keystore2.firstName}") var firstName2: String? = null
    @Value("\${org.taktik.icure.keystore3.firstName}") var firstName3: String? = null
    @Value("\${org.taktik.icure.keystore1.lastName}") var lastName1: String? = null
    @Value("\${org.taktik.icure.keystore2.lastName}") var lastName2: String? = null
    @Value("\${org.taktik.icure.keystore3.lastName}") var lastName3: String? = null
    @Value("\${org.taktik.icure.keystore1.BIC}") var BIC1: String? = null
    @Value("\${org.taktik.icure.keystore2.BIC}") var BIC2: String? = null
    @Value("\${org.taktik.icure.keystore3.BIC}") var BIC3: String? = null
    @Value("\${org.taktik.icure.keystore1.IBAN}") var IBAN1: String? = null
    @Value("\${org.taktik.icure.keystore2.IBAN}") var IBAN2: String? = null
    @Value("\${org.taktik.icure.keystore3.IBAN}") var IBAN3: String? = null

  init {
   	System.setProperty("spring.output.ansi.enabled", "always")
   }

    protected fun uploadKeystore(path: String, port: Int, restTemplate: TestRestTemplate): UUID? {
        val map = LinkedMultiValueMap<String, Any>()
        map.add("file", FileSystemResource(path))
        val result = restTemplate.postForObject("http://localhost:$port/sts/keystore", HttpEntity(map, HttpHeaders().apply { contentType = MediaType.MULTIPART_FORM_DATA }), UUIDType::class.java)
        val keystoreId = result.uuid
        return keystoreId
    }

    protected fun register(restTemplate: TestRestTemplate, port: Int, ssin: String, passPhrase: String): Triple<UUID?, String, String> {
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("$ssin.acc-p12").path, port, restTemplate)
        val res = restTemplate.exchange("http://localhost:$port/sts/token?ssin=$ssin", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, null, passPhrase)), SamlTokenResult::class.java, passPhrase)
        val tokenId = res.body?.let { it.tokenId }
        return Triple(keystoreId, tokenId.toString(), passPhrase)
    }

    protected fun registerMmH(restTemplate: TestRestTemplate, port: Int, nihii: String, passPhrase: String): Triple<UUID?, String, String> {
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("$nihii.acc-p12").path, port, restTemplate)
        val res = restTemplate.exchange("http://localhost:$port/sts/token?isMedicalHouse=true&ssin=$nihii", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, null, passPhrase)), SamlTokenResult::class.java, passPhrase)
        val tokenId = res.body?.let { it.tokenId }
        return Triple(keystoreId, tokenId.toString(), passPhrase)
    }

    protected fun createHeaders(username: String?, password: String?, keystoreId: UUID? = null, tokenId: String? = null, passPhrase: String? = null): HttpHeaders {
        return object : HttpHeaders() {
            init {
                username?.let {
                    val auth = "$it:$password"
                    val encodedAuth = Base64.getEncoder().encode(
                        auth.toByteArray(Charsets.US_ASCII))
                    val authHeader = "Basic " + String(encodedAuth)

                    set("Authorization", authHeader)
                }
                keystoreId?.let { set("X-FHC-keystoreId", it.toString()) }
                tokenId?.let { set("X-FHC-tokenId", it) }
                passPhrase?.let { set("X-FHC-passPhrase", it) }
            }
        }
    }
}
