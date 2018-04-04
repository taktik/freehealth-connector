package org.taktik.connector.business.therlink.domain.responses

class TherapeuticLinkResponseError {
	var errorCode: String? = null
	var errorDescription: String? = null

	class Builder {
		private val error = TherapeuticLinkResponseError()

		fun withErrorCode(code: String): TherapeuticLinkResponseError.Builder {
			this.error.errorCode = code
			return this
		}

		fun withErrorDescription(desc: String): TherapeuticLinkResponseError.Builder {
			this.error.errorDescription = desc
			return this
		}

		fun build(): TherapeuticLinkResponseError {
			return this.error
		}
	}
}
