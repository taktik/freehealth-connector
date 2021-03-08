package be.vlaanderen.zg.vaccinnet.batchuploadtest.utils

import be.vlaanderen.zg.vaccinnet.batchuploadtest.service.XmlToObjectConverter
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.KmehrmessageType
import java.io.IOException
import javax.xml.bind.JAXBException

object XmlDataUtils {
    val templateKmehr: KmehrmessageType
        get() = try {
            XmlToObjectConverter.convertFromClassPath(KmehrmessageType::class.java, "kmehr-template.xml")
        } catch (e: IOException) {
            throw RuntimeException("Kmehr template not loaded", e)
        } catch (e: JAXBException) {
            throw RuntimeException("Kmehr template not loaded", e)
        }
}
