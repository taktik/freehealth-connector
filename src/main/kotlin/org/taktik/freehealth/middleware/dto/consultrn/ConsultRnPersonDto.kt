package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.EncodedSSINType
import be.fgov.ehealth.consultrn._1_0.core.OriginType
import java.io.Serializable

class ConsultRnPersonDto(var SSIN: EncodedSSINType? = null, var personData: ConsultRnPersonDataDto? = null,
    var modificationDate: String? = null, var origin: OriginType? = null) : Serializable
