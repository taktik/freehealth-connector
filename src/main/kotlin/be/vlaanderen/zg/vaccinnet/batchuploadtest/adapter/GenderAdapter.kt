package be.vlaanderen.zg.vaccinnet.batchuploadtest.adapter

import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Gender
import javax.xml.bind.annotation.adapters.XmlAdapter

class GenderAdapter : XmlAdapter<String, Gender>() {
    override fun unmarshal(v: String): Gender {
        return Gender.Companion.fromValue(v)
    }

    override fun marshal(v: Gender): String {
        return v.value
    }
}
