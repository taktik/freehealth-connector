package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.EncodedSSINType
import be.fgov.ehealth.consultrn._1_0.core.OriginType
import be.fgov.ehealth.consultrn._1_0.core.PersonDataType
import java.io.Serializable

class ConsultRnPersonDto(var ssin: EncodedSSINType? = null, var personData: PersonDataType? = null,
    var modificationDate: String? = null, var origin: OriginType? = null) : Serializable
