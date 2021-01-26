package org.taktik.freehealth.middleware.dto.consultrnv2

import be.fgov.ehealth.commons.core.v2.Status
import be.fgov.ehealth.rn.commons.business.v1.SsinWithCanceledAndReplacesStatusType
import org.joda.time.DateTime
import java.io.Serializable
import java.lang.Exception

class RnConsultSearchPersonBySsinResponseDto(
    var ssin: SsinWithCanceledAndReplacesStatusType? = null,
    var result: RnConsultSearchByNissResultDto? = null,
    var status: Status? = null,
    var id: String? = null,
    var inResponseTo: String? = null,
    var issueInstant: DateTime? = null,
    var xmlConversations: RnConsultConversationDto? = null,
    var exception: Exception? = null
): Serializable
