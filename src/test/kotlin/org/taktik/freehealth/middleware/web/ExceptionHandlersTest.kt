package org.taktik.freehealth.middleware.web

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTags
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
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
            ExceptionToTest(Exception("non-declared exception"), HttpStatus.INTERNAL_SERVER_ERROR)
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
            Assert.assertEquals("/exception/{id}", response.headers["X-WebMvcTags-uri"]?.firstOrNull())
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

    @ControllerAdvice
    class WebMvcTagsUriAdvice : ResponseBodyAdvice<Any> {
        override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
            return true
        }

        override fun beforeBodyWrite(body: Any?, returnType: MethodParameter, selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Any? {
            if (request is ServletServerHttpRequest && response is ServletServerHttpResponse) {
                response.headers.add("X-WebMvcTags-uri", WebMvcTags.uri(request.servletRequest, response.servletResponse).value)
            }

            return body
        }
    }
}
