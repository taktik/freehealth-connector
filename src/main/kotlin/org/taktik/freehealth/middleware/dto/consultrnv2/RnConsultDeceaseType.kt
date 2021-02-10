package org.taktik.freehealth.middleware.dto.consultrnv2

import be.fgov.ehealth.rn.baselegaldata.v1.LocationType
import com.sun.org.apache.xpath.internal.operations.Bool

class RnConsultDeceaseType(
    var isDecease: Boolean? = false,
    var deceaseDate: String? = null,
    var deceasePlace: LocationType? = null
)
