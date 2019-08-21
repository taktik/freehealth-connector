package org.taktik.connector.business.therlink.domain.requests

import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import org.joda.time.DateTime

class HasTherapeuticLinkRequest(date: DateTime, id: String, author: Author, link: TherapeuticLink) : TherapeuticLinkRequestType(id, date, author, link) {
    companion object {
        private val serialVersionUID = 1L
    }
}
