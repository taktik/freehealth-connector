package org.taktik.connector.business.therlink.domain.responses

import org.taktik.connector.business.common.domain.Patient
import org.taktik.connector.business.therlink.domain.HcParty
import org.taktik.connector.business.therlink.domain.OperationContext
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import java.util.ArrayList
import org.joda.time.LocalDate

class TherapeuticLinkResponse(patient: Patient = Patient(), hcParty: HcParty = HcParty(), type: String = "gpconsultation") : TherapeuticLink(patient, hcParty, type) {
	var operationContexts: MutableList<OperationContext> = ArrayList()

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + this.operationContexts.hashCode()
		return result
	}

	override fun equals(obj: Any?): Boolean {
		when {
			this === obj -> return true
			!super.equals(obj) -> return false
			this.javaClass != obj!!.javaClass -> return false
			else -> {
				val other = obj as TherapeuticLinkResponse?
				if (this.operationContexts != other!!.operationContexts) {
					return false
				}
				return true
			}
		}
	}

	class Builder {
		private val therlink = TherapeuticLinkResponse()

		fun withPatient(patient: Patient): TherapeuticLinkResponse.Builder {
			this.therlink.patient = patient
			return this
		}

		fun withHcParty(hcp: HcParty): TherapeuticLinkResponse.Builder {
			this.therlink.hcParty = hcp
			return this
		}

		fun addOperationContext(oc: OperationContext): TherapeuticLinkResponse.Builder {
			this.therlink.operationContexts.add(oc)
			return this
		}

		fun withStartDate(startDate: LocalDate): TherapeuticLinkResponse.Builder {
			this.therlink.startDate = startDate
			return this
		}

		fun withEndDate(endDate: LocalDate): TherapeuticLinkResponse.Builder {
			this.therlink.endDate = endDate
			return this
		}

		fun withType(string: String): TherapeuticLinkResponse.Builder {
			this.therlink.type = string
			return this
		}

		fun build(): TherapeuticLinkResponse {
			return this.therlink
		}
	}

	companion object {
		private val serialVersionUID = 1L

		val serialversionuid: Long
			get() = 1L
	}
}
