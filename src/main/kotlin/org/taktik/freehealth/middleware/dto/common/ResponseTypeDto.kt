package org.taktik.freehealth.middleware.dto.common

import java.io.Serializable

open class ResponseTypeDto(var status: StatusDto? = null, var id: String? = null) : Serializable
