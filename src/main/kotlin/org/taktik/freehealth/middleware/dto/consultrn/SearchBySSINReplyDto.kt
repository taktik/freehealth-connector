package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.ErrorType
import be.fgov.ehealth.consultrn._1_0.core.PersonType
import be.fgov.ehealth.consultrn._1_0.protocol.ConsultRnReplyType
import org.taktik.freehealth.middleware.dto.common.StatusDto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable

class SearchBySSINReplyDto(status: StatusDto? = null, id: String? = null, errorInformations: List<ErrorType>? = null, var person: ConsultRnPersonDto? = null) : ConsultRnReplyDto(status, id, errorInformations), Serializable
