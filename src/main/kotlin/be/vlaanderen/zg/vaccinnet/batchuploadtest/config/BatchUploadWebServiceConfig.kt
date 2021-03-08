package be.vlaanderen.zg.vaccinnet.batchuploadtest.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.PathResource
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.ws.client.core.WebServiceTemplate
import org.springframework.ws.client.support.interceptor.ClientInterceptor
import org.springframework.ws.soap.SoapVersion
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler
import org.springframework.ws.transport.http.HttpComponentsMessageSender
import org.taktik.connector.technical.config.ConfigFactory
import java.io.File

@Configuration
class BatchUploadWebServiceConfig {
    private val config: org.taktik.connector.technical.config.Configuration =
        ConfigFactory.getConfigValidatorFor("VACCINETKEYSTORE_PASSWORD", "KEYSTORE_DIR", "VACCINETKEYSTORE_LOCATION")

    @Bean
    @Conditional(
        CertificateCondition::class
    )
    fun webServiceTemplateCertificate(
        @Value("\${vaccinnet.batchupload.url.upload}") uploadUrl: String,
        messageFactory: SaajSoapMessageFactory,
        securityInterceptor: XwsSecurityInterceptor,
        marshaller: Jaxb2Marshaller
    ): WebServiceTemplate {
        val webServiceTemplate: WebServiceTemplate = createWebServiceTemplate(uploadUrl, messageFactory, marshaller)
        webServiceTemplate.interceptors = arrayOf<ClientInterceptor>(securityInterceptor)
        return webServiceTemplate
    }

    @Bean
    @Conditional(
        CertificateNonCondition::class
    )
    fun webServiceTemplateNonCertificate(
        @Value("\${vaccinnet.batchupload.url.upload}") uploadUrl: String,
        messageFactory: SaajSoapMessageFactory,
        marshaller: Jaxb2Marshaller
    ): WebServiceTemplate {
        return createWebServiceTemplate(uploadUrl, messageFactory, marshaller)
    }

    private fun createWebServiceTemplate(
        uploadUrl: String,
        messageFactory: SaajSoapMessageFactory,
        marshaller: Jaxb2Marshaller
    ): WebServiceTemplate {
        val wsTemplate = WebServiceTemplate()
        wsTemplate.messageFactory = messageFactory
        wsTemplate.marshaller = marshaller
        wsTemplate.unmarshaller = marshaller
        wsTemplate.setMessageSender(HttpComponentsMessageSender())
        wsTemplate.defaultUri = uploadUrl
        return wsTemplate
    }

    @Bean
    @Conditional(
        CertificateCondition::class
    )
    fun securityInterceptor(callbackHandler: KeyStoreCallbackHandler?): XwsSecurityInterceptor {
        val securityInterceptor = XwsSecurityInterceptor()
        securityInterceptor.setPolicyConfiguration(ClassPathResource("securityPolicy.xml"))
        securityInterceptor.setCallbackHandler(callbackHandler)
        return securityInterceptor
    }

    @Bean
    @Conditional(
        CertificateCondition::class
    )
    fun callbackHandler(
        keyStore: KeyStoreFactoryBean
    ): KeyStoreCallbackHandler {
        val callbackHandler = KeyStoreCallbackHandler()
        callbackHandler.setKeyStore(keyStore.getObject())
        callbackHandler.setDefaultAlias(ALIAS)
        callbackHandler.setPrivateKeyPassword(config.getProperty("VACCINETKEYSTORE_PASSWORD"))
        return callbackHandler
    }

    @Bean
    @Conditional(
        CertificateCondition::class
    )
    fun keyStore(): KeyStoreFactoryBean {
        val keyStore = KeyStoreFactoryBean()
        keyStore.setLocation(PathResource(File(File(config.getProperty("KEYSTORE_DIR")), config.getProperty("VACCINETKEYSTORE_LOCATION")).absolutePath))
        keyStore.setPassword(config.getProperty("VACCINETKEYSTORE_PASSWORD"))
        return keyStore
    }

    @Bean
    fun messageFactory(): SaajSoapMessageFactory {
        val messageFactory = SaajSoapMessageFactory()
        messageFactory.setSoapVersion(SoapVersion.SOAP_11)
        return messageFactory
    }

    @Bean
    fun marshaller(): Jaxb2Marshaller {
        val marshaller = Jaxb2Marshaller()
        marshaller.setPackagesToScan("be")
        return marshaller
    }

    companion object {
        private const val ALIAS = "authentication"
    }
}
