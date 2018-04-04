package org.taktik.connector.business.therlink.domain

import org.taktik.connector.business.therlink.domain.requests.BinaryProof
import java.io.Serializable
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.ToStringBuilder

class Proof @JvmOverloads constructor(var type: String?, var binaryProof: BinaryProof? = null) : Serializable {

	override fun hashCode(): Int {
		val builder = HashCodeBuilder()
		builder.append(this.binaryProof)
		builder.append(this.type)
		return builder.toHashCode()
	}

	override fun equals(obj: Any?): Boolean {
		if (obj == null) {
			return false
		} else if (obj !is Proof) {
			return false
		} else if (obj === this) {
			return true
		} else {
			val other = obj as Proof?
			val builder = EqualsBuilder()
			builder.append(this.binaryProof, this.binaryProof)
			builder.append(this.type, other!!.type)
			return builder.isEquals
		}
	}

	override fun toString(): String {
		val builder = ToStringBuilder(this)
		builder.append(this.binaryProof)
		builder.append(this.type)
		return builder.toString()
	}

	companion object {
		private const val serialVersionUID = 1L
	}
}
