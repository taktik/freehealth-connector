package be.vlaanderen.zg.vaccinnet.batchuploadtest.service

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.PathResource
import java.io.IOException
import java.io.InputStream
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.JAXBException
import javax.xml.transform.stream.StreamSource

object XmlToObjectConverter {
    @Throws(IOException::class, JAXBException::class)
    fun <O> convertFromPath(clazz: Class<O>, xmlPath: String?): O {
        PathResource(xmlPath).inputStream.use { `is` -> return convertToObject(clazz, `is`) }
    }

    @Throws(IOException::class, JAXBException::class)
    fun <O> convertFromClassPath(clazz: Class<O>, xmlPath: String?): O {
        ClassPathResource(xmlPath).inputStream.use { `is` -> return convertToObject(clazz, `is`) }
    }

    @Throws(JAXBException::class)
    private fun <O> convertToObject(clazz: Class<O>, `is`: InputStream): O {
        val jaxbContext = JAXBContext.newInstance(clazz)
        val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
        val obj = jaxbUnmarshaller.unmarshal(StreamSource(`is`))
        if (obj is JAXBElement<*>) {
            return obj.value as O
        }
        return obj as O
    }
}
