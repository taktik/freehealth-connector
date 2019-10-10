package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.OriginType
import be.fgov.ehealth.consultrn._1_0.core.PlainAddressType
import be.fgov.ehealth.consultrn._1_0.core.StandardAddressType

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = ["standardAddress", "plainAddress"])
class ConsultRnAddressDto(var standardAddress: StandardAddressType? = null, var plainAddress: ConsultRnPlainAddressDto? = null,
    var modificationDate: String? = null, var origin: OriginType? = null) : Serializable {
}
