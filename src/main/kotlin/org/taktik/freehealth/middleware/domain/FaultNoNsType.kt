package org.taktik.freehealth.middleware.domain

import be.cin.types.v1.DetailsType
import be.cin.types.v1.StringLangType

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable

@XmlRootElement(name = "Fault")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultType", propOrder = ["faultCode", "faultSource", "message", "details"])
class FaultNoNsType : Serializable {
    @XmlElement(name = "FaultCode", required = true)
    var faultCode: String? = null
    @XmlElement(name = "FaultSource", required = true)
    var faultSource: String? = null
    @XmlElement(name = "Message", required = true)
    var message: StringLangType? = null
    @XmlElement(name = "Details", required = true)
    var details: DetailsType? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}
