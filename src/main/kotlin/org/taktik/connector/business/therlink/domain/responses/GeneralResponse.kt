package org.taktik.connector.business.therlink.domain.responses

import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.apache.commons.lang.builder.ToStringBuilder
import org.joda.time.DateTime

open class GeneralResponse(var dateTime: DateTime, var externalAuthor: Author, var externalID: String, var originalRequest: TherapeuticLinkRequestType, var acknowledge: Acknowledge) {

	override fun toString(): String {
		val builder = ToStringBuilder(this)
		val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
		builder.append("GeneralResponse [externalID = ")
		builder.append(this.externalID)
		builder.append(", externalAuthor = ")
		builder.append(this.externalAuthor.toString())
		if (this.dateTime != null) {
			builder.append(", dateTime = ")
			builder.append(df.format(this.dateTime!!.toDate()))
		}

		builder.append(", originalRequest = ")
		builder.append(this.originalRequest.toString())
		builder.append(", acknowledge = ")
		builder.append(this.acknowledge.toString())
		builder.append("]")
		return builder.toString()
	}
}
