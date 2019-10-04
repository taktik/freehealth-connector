package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.ErrorType
import org.taktik.freehealth.middleware.dto.common.StatusDto
import java.io.Serializable

class SearchPhoneticReplyDto(status: StatusDto? = null, id: String? = null, errorInformations: List<ErrorType>? = null, var persons: List<ConsultRnPersonDto>? = null) : ConsultRnReplyDto(status, id, errorInformations), Serializable
