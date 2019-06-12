package org.taktik.freehealth.middleware.dto.hub

import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.freehealth.middleware.dto.common.ErrorDto

class PutTransactionResponseDto(var id:List<IDKMEHR>? = null,var errors:List<ErrorDto>? = null)
