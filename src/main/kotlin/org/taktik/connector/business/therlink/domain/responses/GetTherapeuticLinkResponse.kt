package org.taktik.connector.business.therlink.domain.responses

import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import org.apache.commons.lang.builder.ToStringBuilder
import org.joda.time.DateTime

class GetTherapeuticLinkResponse(dateTime: DateTime, author: Author, id: String, request: TherapeuticLinkRequestType, var listOfTherapeuticLinks: List<TherapeuticLinkResponse> = ArrayList(), acknowledge: Acknowledge) : GeneralResponse(dateTime, author, id, request, acknowledge) {

	override fun toString(): String {
		val builder = ToStringBuilder(this)
		builder.append(super.toString() + ", List of Therapeutic links : [")
		if (this.listOfTherapeuticLinks != null) {
			val it = this.listOfTherapeuticLinks!!.iterator()

			while (it.hasNext()) {
				builder.append(it.next().toString())
				if (it.hasNext()) {
					builder.append(", ")
				}
			}
		}

		builder.append("]")
		return builder.toString()
	}
}
