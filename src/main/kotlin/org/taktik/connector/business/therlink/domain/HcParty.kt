package org.taktik.connector.business.therlink.domain

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.apache.commons.lang.builder.EqualsBuilder
import org.taktik.connector.business.therlink.exception.TherLinkBusinessConnectorException
import org.taktik.connector.business.therlink.exception.TherLinkBusinessConnectorExceptionValues

import java.io.Serializable
import java.util.ArrayList

class HcParty : Serializable {
	var type: String? = null


	@get:Deprecated("")
	@set:Deprecated("")
	var applicationID: String? = null


	@get:Deprecated("")
	@set:Deprecated("")
	var nihii: String? = null


	@get:Deprecated("")
	@set:Deprecated("")
	var inss: String? = null


	@get:Deprecated("")
	@set:Deprecated("")
	var hubId: String? = null


	@get:Deprecated("")
	@set:Deprecated("")
	var cbe: String? = null
	var name: String? = null
	var firstName: String? = null
	var familyName: String? = null


	@get:Deprecated("")
	@set:Deprecated("")
	var ehp: String? = null
	val ids = ArrayList<IDHCPARTY>()
	val cds = ArrayList<CDHCPARTY>()

	fun getIds(): List<IDHCPARTY> {
		return this.ids
	}

	fun setIds(ids: List<IDHCPARTY>) {
		this.ids.clear()
		this.ids.addAll(ids)
	}

	fun getCds(): List<CDHCPARTY> {
		return this.cds
	}

	fun setCds(cds: List<CDHCPARTY>) {
		this.cds.clear()
		this.cds.addAll(cds)
	}

	override fun equals(obj: Any?): Boolean {
		if (this === obj) {
			return true
		} else if (obj == null) {
			return false
		} else if (this.javaClass != obj.javaClass) {
			return false
		} else {
			val other = obj as HcParty?
			return EqualsBuilder().append(this.cds, other!!.getCds()).append(this.ids, other.getIds()).append(this.familyName, other.familyName).append(this.firstName, other.firstName).append(this.name, other.name).append(this.type, other.type).isEquals
		}
	}

	override fun hashCode(): Int {
		var result = 1
		result = 31 * result + if (this.applicationID == null) 0 else this.applicationID!!.hashCode()
		result = 31 * result + if (this.cbe == null) 0 else this.cbe!!.hashCode()
		result = 31 * result + if (this.cds == null) 0 else this.cds.hashCode()
		result = 31 * result + if (this.ehp == null) 0 else this.ehp!!.hashCode()
		result = 31 * result + if (this.familyName == null) 0 else this.familyName!!.hashCode()
		result = 31 * result + if (this.firstName == null) 0 else this.firstName!!.hashCode()
		result = 31 * result + if (this.hubId == null) 0 else this.hubId!!.hashCode()
		result = 31 * result + if (this.ids == null) 0 else this.ids.hashCode()
		result = 31 * result + if (this.inss == null) 0 else this.inss!!.hashCode()
		result = 31 * result + if (this.name == null) 0 else this.name!!.hashCode()
		result = 31 * result + if (this.nihii == null) 0 else this.nihii!!.hashCode()
		result = 31 * result + if (this.type == null) 0 else this.type!!.hashCode()
		return result
	}

	override fun toString(): String {
		val builder = StringBuilder()
		builder.append("HcParty ").append("[")
		builder.append("hubId=").append(this.hubId).append(", ")
		builder.append("type=").append(this.type).append(", ")
		builder.append("nihii=").append(this.nihii).append(", ")
		builder.append("inss=").append(this.inss).append("]")
		return builder.toString()
	}

	class Builder {
		private val hcp = HcParty()

		fun withFirstName(value: String?): HcParty.Builder {
			this.hcp.firstName = value
			return this
		}

		fun withFamilyName(value: String?): HcParty.Builder {
			this.hcp.familyName = value
			return this
		}

		fun withName(value: String?): HcParty.Builder {
			this.hcp.name = value
			return this
		}

		fun withInss(value: String?): HcParty.Builder {
			this.hcp.inss = value
			return this
		}

		fun withCbe(value: String?): HcParty.Builder {
			this.hcp.cbe = value
			return this
		}

		fun withNihii(value: String?): HcParty.Builder {
			this.hcp.nihii = value
			return this
		}

		fun withType(value: String?): HcParty.Builder {
			this.hcp.type = value
			return this
		}

		fun withApplicationID(string: String?): HcParty.Builder {
			this.hcp.applicationID = string
			return this
		}

		@Throws(TherLinkBusinessConnectorException::class)
		fun build(): HcParty {
			this.validateNameConstraint()
			this.validateAtLeastOneNihiiOrInss()
			this.validateForApplicationRule()
			return this.hcp
		}

		@Throws(TherLinkBusinessConnectorException::class)
		private fun validateForApplicationRule() {
			if (this.hcp.applicationID != null && (!this.hcp.type!!.equals("application", ignoreCase = true) || this.hcp.nihii != null || this.hcp.inss != null)) {
				throw TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.HCP_NOT_VALID, "If HcParty has an applicationID, then type should be 'application' and niss neither nihii should be filled in")
			}
		}

		@Throws(TherLinkBusinessConnectorException::class)
		private fun validateAtLeastOneNihiiOrInss() {
			if (this.hcp.nihii == null && this.hcp.inss == null && this.hcp.applicationID == null && this.hcp.ehp == null) {
				val msg = "At least nihii or inss should be defined"
				throw TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.HCP_NOT_VALID, msg)
			}
		}

		@Throws(TherLinkBusinessConnectorException::class)
		private fun validateNameConstraint() {
			if (!this.hasName() && !this.hasCompleteName() && this.hasAtLeastOneName()) {
				throw TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.VALIDATION_ERROR, "Hcp should have a firstName and a FamilyName, (X)OR a name and nothing else")
			}
		}

		private fun hasAtLeastOneName(): Boolean {
			return this.hcp.firstName != null || this.hcp.familyName != null && this.hcp.name != null
		}

		private fun hasCompleteName(): Boolean {
			return this.hcp.firstName != null && this.hcp.familyName != null && this.hcp.name == null
		}

		private fun hasName(): Boolean {
			return this.hcp.firstName == null && this.hcp.familyName == null && this.hcp.name != null
		}

		fun withEhp(string: String): HcParty.Builder {
			this.hcp.ehp = string
			return this
		}
	}

	companion object {
		private const val serialVersionUID = 1L
	}
}
