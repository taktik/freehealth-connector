package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.PersonType
import be.fgov.ehealth.consultrn._1_0.protocol.ConsultRnReplyType

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable

class SearchBySSINReplyDto(var person: ConsultRnPersonDto? = null) : ConsultRnReplyDto(), Serializable
