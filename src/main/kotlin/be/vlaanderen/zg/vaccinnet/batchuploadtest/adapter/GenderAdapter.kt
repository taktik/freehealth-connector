package be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter

import java.lang.RuntimeException
import be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter.GenderAdapter
import be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter.LocalDateAdapter
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.OrganisationType
import kotlin.Throws
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.DatatypeConstants
import java.util.GregorianCalendar
import java.time.ZoneId
import javax.xml.validation.SchemaFactory
import java.io.IOException
import be.vlaanderen.zg.vaccinnet.batchuploadtest.service.XmlToObjectConverter
import org.springframework.boot.ApplicationArguments
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.ApplicationArgumentUtils
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Organisation
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import be.vlaanderen.zg.vaccinnet.batchuploadtest.config.CertificateCondition
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.ws.client.core.WebServiceTemplate
import org.springframework.ws.client.support.interceptor.ClientInterceptor
import be.vlaanderen.zg.vaccinnet.batchuploadtest.config.CertificateNonCondition
import org.springframework.ws.transport.http.HttpComponentsMessageSender
import org.springframework.core.io.ClassPathResource
import be.vlaanderen.zg.vaccinnet.batchuploadtest.config.BatchUploadWebServiceConfig
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.XmlDataUtils
import org.springframework.ws.soap.SoapVersion
import javax.xml.transform.stream.StreamSource
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.KmehrUtils
import java.util.UUID
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import be.vlaanderen.zg.vaccinnet.batchuploadtest.ServiceApplication
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Gender
import org.springframework.context.ApplicationContext
import be.vlaanderen.zg.vaccinnet.batchuploadtest.service.AanleverenVaccinatieGegevensService
import be.vlaanderen.zg.vaccinnet.batchuploadtest.service.ResultaatVerwerkingVaccinatieGegevensService
import be.vlaanderen.zg.vaccinnet.batchuploadtest.service.AanleverenAanvullendeVaccinatieGegevensService
import org.springframework.boot.context.event.ApplicationPreparedEvent
import java.util.TimeZone
import javax.xml.bind.annotation.adapters.XmlAdapter

class GenderAdapter : XmlAdapter<String, Gender>() {
    override fun unmarshal(v: String): Gender {
        return Gender.Companion.fromValue(v)
    }

    override fun marshal(v: Gender): String {
        return v.getValue()
    }
}
