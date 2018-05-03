package org.taktik.connector.business.therlink.domain.requests

import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.Proof
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import java.util.Date
import org.joda.time.DateTime
import java.util.ArrayList

class GetTherapeuticLinkRequest(
    id: String,
    dateTime: DateTime,
    author: Author,
    link: TherapeuticLink? = null,
    proofs: List<Proof?> = ArrayList()
) : TherapeuticLinkRequestType(dateTime, id, author, link, proofs) {
    var maxRows: Int = 0

    @Deprecated("") constructor(
        id: String,
        date: Date,
        author: Author,
        link: TherapeuticLink?,
        maxRows: Int,
        vararg proofs: Proof?
    ) : this(id, DateTime(date), author, link, proofs.toList()) {
        this.maxRows = maxRows
    }

    constructor(
        date: DateTime,
        id: String,
        author: Author,
        link: TherapeuticLink?,
        maxRows: Int,
        vararg proofs: Proof?
    ) : this(id, date, author, link, proofs.toList()) {
        this.maxRows = maxRows
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
