package org.taktik.freehealth.middleware.dto.consultrnv2

import be.fgov.ehealth.commons.core.v2.Status
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonDeclarationType
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonResultType
import org.joda.time.DateTime
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.freehealth.middleware.dto.common.ErrorDto

class RnConsultRegisterPersonResponseDto(
    var id: String? = null,
    var inResponseTo: String? = null,
    var issueInstant: DateTime? = null,
    var status: Status? = null,
    var declaration: RegisterPersonDeclarationType? = null,
    var result: RegisterPersonResultType? = null,
    var xmlConversation: RnConsultConversationDto?= null,
    var error: ErrorDto? = null
    )
