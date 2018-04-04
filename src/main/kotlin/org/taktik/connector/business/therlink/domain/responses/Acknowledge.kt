package org.taktik.connector.business.therlink.domain.responses

import java.util.ArrayList
import org.apache.commons.lang.builder.ToStringBuilder

class Acknowledge {
	var isComplete: Boolean = false
	var listOfErrors: MutableList<TherapeuticLinkResponseError> = ArrayList()

	override fun toString(): String {
		val builder = ToStringBuilder(this)
		builder.append("Complete? : " + this.isComplete)
		if (this.listOfErrors != null) {
			val it = this.listOfErrors!!.iterator()

			while (it.hasNext()) {
				val next = it.next()
				builder.append("[")
				builder.append("Error code : " + next.errorCode!!)
				builder.append("ErrorDescription : " + next.errorDescription!!)
				builder.append("]")
				if (it.hasNext()) {
					builder.append(", ")
				}
			}
		}

		return builder.toString()
	}

	class Builder {
		private val ack = Acknowledge()

		fun withComplete(complete: Boolean): Acknowledge.Builder {
			this.ack.isComplete = complete
			return this
		}

		fun addError(error: TherapeuticLinkResponseError): Acknowledge.Builder {
			this.ack.listOfErrors.add(error)
			return this
		}

		fun build(): Acknowledge {
			return this.ack
		}
	}
}
