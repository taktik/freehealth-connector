package be.vlaanderen.zg.vaccinnet.batchuploadtest.model

import be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter.GenderAdapter
import be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter.LocalDateAdapter
import java.time.LocalDate
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter

@XmlRootElement(name = "patient")
class Patient(val lastName: String? = null, val firstName: String? = null, val nationalRegistryNumber: String? = null) {

    @get:XmlJavaTypeAdapter(value = GenderAdapter::class)
    val gender: Gender? = null

    @get:XmlJavaTypeAdapter(value = LocalDateAdapter::class)
    val birthDate: LocalDate? = null
    private val usualLanguage: String? = null
}
