package org.taktik.connector.business.therlink.domain.requests

import org.joda.time.DateTime
import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.Proof
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import java.util.*

class PutTherapeuticLinkRequest(id: String, dateTime: DateTime, author: Author, link: TherapeuticLink? = null, proofs: List<Proof?> = ArrayList()) : TherapeuticLinkRequestType(dateTime, id, author, link, proofs) {


	@Deprecated("")
	constructor(id: String, date: Date, author: Author, link: TherapeuticLink, vararg proofs: Proof?) : this(id, DateTime(date), author, link, proofs.toList())
	constructor(date: DateTime, id: String, author: Author, link: TherapeuticLink, vararg proofs: Proof?) : this(id, date, author, link, proofs.toList())

	@Deprecated("")
	constructor(id: String, date: Date, author: Author) : this(DateTime(date), id, author)
	constructor(date: DateTime, id: String, author: Author) : this(id, date, author)

	companion object {
		private val serialVersionUID = 1L
	}
}
