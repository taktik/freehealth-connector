package org.taktik.freehealth.middleware.web

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.NestedServletException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import java.util.*
import javax.xml.namespace.QName
import javax.xml.soap.SOAPConstants
import javax.xml.soap.SOAPFactory
import javax.xml.ws.soap.SOAPFaultException

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExceptionHandlersTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    companion object {
        val EXCEPTIONS_TO_TEST = listOf(
            // standard exceptions
            ExceptionToTest(
                TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, "technical connector issue"),
                TechnicalConnectorExceptionValues.ERROR_TECHNICAL.httpStatus
            ),
            ExceptionToTest(MissingKeystoreException("missing keystore exception"), HttpStatus.UNAUTHORIZED),
            ExceptionToTest(MissingTokenException("missing token exception"), HttpStatus.UNAUTHORIZED),
            ExceptionToTest(IllegalArgumentException("illegal argument exception"), HttpStatus.BAD_REQUEST),
            ExceptionToTest(NumberFormatException("number format exception"), HttpStatus.BAD_REQUEST),
            ExceptionToTest(
                SOAPFaultException(
                    SOAPFactory
                        .newInstance(SOAPConstants.SOAP_1_1_PROTOCOL)
                        .createFault("faultString", QName("FaultCodeType", "faultCode"))
                ),
                HttpStatus.BAD_GATEWAY
            ),

            // null message
            ExceptionToTest(IllegalArgumentException(), HttpStatus.BAD_REQUEST, "unknown reason"),

            // exception with cause
            NestedServletException("exception with cause", MissingTokenException("missing token exception"))
                .let { ExceptionToTest(it.cause!!, HttpStatus.UNAUTHORIZED, it.cause!!.message!!) },

            // non-declared exception
            NestedServletException("Request processing failed", Exception("non-declared exception"))
                .let { ExceptionToTest(it.cause!!, HttpStatus.INTERNAL_SERVER_ERROR, it.toString()) }
        )
    }

    @Test
    fun handleException() {
        EXCEPTIONS_TO_TEST.forEach {
            val response = this.restTemplate.getForEntity("http://localhost:$port/exception/${it.id}", Map::class.java)

            println(response.body)

            Assert.assertEquals(it.expectedStatus, response.statusCode)
            Assert.assertEquals(it.expectedStatus.value(), response.body["status"])
            Assert.assertEquals(it.expectedMessage, response.body["message"])
        }
    }

    class ExceptionToTest(
        val exception: Throwable,
        val expectedStatus: HttpStatus,
        val expectedMessage: String = exception.message!!
    ) {
        val id = UUID.randomUUID()!!
    }

    @RestController
    @RequestMapping("/exception")
    class ExceptionControllerTest {
        @GetMapping("/{id}")
        fun throwException(@PathVariable id: UUID) {
            EXCEPTIONS_TO_TEST
                .find { it.id == id }!!
                .apply { throw exception }
        }
    }
}
