package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataList
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import org.taktik.freehealth.middleware.dto.dmg.DmgMessage
import org.taktik.freehealth.middleware.dto.memberdata.MemberDataBatchRequestDto
import org.taktik.freehealth.middleware.dto.memberdata.MemberInfoDto
import sun.rmi.runtime.Log


@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberDataAsyncControllerTest: EhealthTest() {

    @LocalServerPort
    private val port: Int = 0
    private val gson = Gson()

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val asyncNisses = mapOf(
        100 to listOf("84022148878", "68091400202", "30050802512", "59041744620", "03062620819", "57052511675", "93101144683", "68021229115"),
        300 to listOf("53070735020", "57010179489", "49021629574", "76110435930", "98080832693", "53040145574", "49012619462", "59040320896"),
        500 to listOf("76102424423", "37112712021", "16112106736", "68021910291", "58112438989", "48033044311", "58042802590", "46121723514"),
        600 to listOf("67120143655", "70021546287", "23102820194", "69021902691", "70083132280", "45011112215", "27121516833", "45112243029"),
        900 to listOf("57012803538", "82062220229", "45072705334", "60122945519", "99091447286", "59082410780", "25111903990", "39010315202")
    )

    private val regOa = listOf("")


    @Test
    fun sendMemberDataRequest() {
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii5!!, password5!!)
        val str = this.restTemplate.exchange("http://localhost:$port/mda/async/request/900" +
            "?hcpNihii=84450277111" +
            "&hcpSsin=$ssin5" +
            "&hcpName={name5}" +
            "&hcpQuality=medicalhouse",
            HttpMethod.POST, HttpEntity<MemberDataBatchRequestDto>(MemberDataBatchRequestDto(asyncNisses[900]?.map { MemberInfoDto(ssin = it) } ?: listOf()), createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, name5)

        Assertions.assertThat(str).isNotNull
    }



    @Test
    fun getMemberDataMessage() {
        val (keystoreId, tokenId, passPhrase) = registerMmH(restTemplate!!, port, nihii5!!, password5!!)
        val results = regOa.map {
            val messages = this.restTemplate.exchange("http://localhost:$port/mda/async/messages" +
                "?hcpNihii=84450277111" +
                "&hcpSsin=$ssin5" +
                "&hcpName={name5}" +
                "&messageNames=$it",
                HttpMethod.POST, HttpEntity<List<MemberDataResponse>>(listOf(), createHeaders(null, null, keystoreId, tokenId, passPhrase)), object : ParameterizedTypeReference<List<MemberDataList>>() {}, name5)
        }
    }
}
