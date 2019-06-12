package org.taktik.freehealth.middleware.domain.hub

import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.freehealth.middleware.domain.common.Error

class PutTransactionResponse(var id:List<IDKMEHR>? = null,var errors:List<Error>? = null)
