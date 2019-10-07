package org.taktik.freehealth.middleware.dto.common

import be.fgov.ehealth.commons._1_0.core.LocalisedString

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import java.io.Serializable
import java.util.ArrayList

class StatusDto(var code: String? = null, var messages: List<LocalisedString>? = null) : Serializable
