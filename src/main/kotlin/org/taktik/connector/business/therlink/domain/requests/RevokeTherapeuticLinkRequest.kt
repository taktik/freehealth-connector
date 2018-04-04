package org.taktik.connector.business.therlink.domain.requests

import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.Proof
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import java.util.Date
import org.joda.time.DateTime

class RevokeTherapeuticLinkRequest(date: DateTime, id: String, author: Author, link: TherapeuticLink?, proofs: List<Proof?> = ArrayList()) : TherapeuticLinkRequestType(date, id, author, link, proofs) {
	@Deprecated("")
	constructor(date: Date, id: String, author: Author, link: TherapeuticLink?, vararg proofs: Proof?) : this(DateTime(date), id, author, link, proofs.toList())
	constructor(date: DateTime, id: String, author: Author, link: TherapeuticLink?, vararg proofs: Proof?) : this(date, id, author, link, proofs.toList())

	companion object {
		private val serialVersionUID = 1L
	}
}
