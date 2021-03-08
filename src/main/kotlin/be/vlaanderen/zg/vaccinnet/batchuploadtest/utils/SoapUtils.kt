package be.vlaanderen.zg.vaccinnet.batchuploadtest.utils

import org.xml.sax.SAXException
import java.io.IOException
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.util.JAXBSource
import javax.xml.transform.Source
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory

object SoapUtils {
    fun validateMessage(`object`: Any) {
        try {
            val jc = JAXBContext.newInstance(`object`.javaClass)
            val source: Source = JAXBSource(jc, `object`)
            val sf: SchemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema")
            val location = "/ws/batch/UPLVaccinatieGegevens.xsd"
            val xsdUrl = `object`.javaClass.getResource(location)
            Objects.requireNonNull(
                xsdUrl,
                String.format("Location of %s.xsd (%s) is incorrect", `object`.javaClass.simpleName, location)
            )
            val schema: Schema = sf.newSchema(xsdUrl)
            val validator = schema.newValidator()
            validator.validate(source)
        } catch (e: JAXBException) {
            throw RuntimeException(e)
        } catch (e: SAXException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
