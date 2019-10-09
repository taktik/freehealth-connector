package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import be.fgov.ehealth.consultrn.commons.core.v3.BusinessAnomalyType
import be.fgov.ehealth.consultrn.core.v2.ResultType

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable

class RegisterPersonResponseDto(var result: ResultType? = null, var businessAnomalies: List<BusinessAnomalyType>? = null) : StatusResponseType(), Serializable {
}
