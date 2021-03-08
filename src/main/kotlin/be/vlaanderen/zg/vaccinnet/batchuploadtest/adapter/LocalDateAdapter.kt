package be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter

import java.time.LocalDate
import javax.xml.bind.annotation.adapters.XmlAdapter

class LocalDateAdapter : XmlAdapter<String?, LocalDate>() {
    override fun unmarshal(v: String?): LocalDate {
        return LocalDate.parse(v)
    }

    override fun marshal(v: LocalDate): String? {
        return v.toString()
    }
}
