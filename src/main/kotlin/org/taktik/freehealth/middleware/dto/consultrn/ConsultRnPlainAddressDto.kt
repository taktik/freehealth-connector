package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.CountryType

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlainAddressType", propOrder = ["address", "country"])
class ConsultRnPlainAddressDto(var country: CountryType? = null, var address: String? = null) : Serializable
