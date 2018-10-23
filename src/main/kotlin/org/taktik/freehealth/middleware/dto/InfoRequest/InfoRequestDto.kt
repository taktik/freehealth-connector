package org.taktik.freehealth.middleware.dto.InfoRequest

import java.io.Serializable

class InfoRequestDto(var xmlRequest: String? = null,
                     var xmlResponse: String? = null,
                     var intermediateRequest: MutableList<IntermediateRequest>? = null,
                     var outputReferences: OutputReferences? = null
): Serializable

class OutputReferences : Serializable {
    var inputReference: String? = null
    var outputReference: String? = null
    var nipReference: String? = null
}

class IntermediateRequest : Serializable {
    var xmlRequest: String? = null
    var xmlResponse: String? = null
    var url: String? = null
}
