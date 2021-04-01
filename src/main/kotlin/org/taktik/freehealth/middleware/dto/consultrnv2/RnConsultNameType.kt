package org.taktik.freehealth.middleware.dto.consultrnv2

import java.io.Serializable

class RnConsultNameType(
    var lastName: String? = null,
    var firstName: String? = null,
    var inceptionDate: String? = null
) : Serializable
