package org.taktik.connector.business.therlink.domain.responses

import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import org.joda.time.DateTime

class RevokeTherapeuticLinkResponse(
    dateTime: DateTime,
    author: Author,
    id: String,
    request: TherapeuticLinkRequestType,
    acknowledge: Acknowledge
) : GeneralResponse(dateTime, author, id, request, acknowledge)
