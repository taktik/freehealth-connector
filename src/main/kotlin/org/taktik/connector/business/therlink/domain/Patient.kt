package org.taktik.connector.business.therlink.domain

import org.taktik.connector.business.therlink.exception.TherLinkBusinessConnectorException
import org.taktik.connector.business.therlink.exception.TherLinkBusinessConnectorExceptionValues
import java.io.Serializable

@Deprecated("")
class Patient : org.taktik.connector.business.common.domain.Patient, Serializable {
    var name: String? = null

    var sis: String
        get() = this.sisCardNumber
        set(sis) {
            this.sisCardNumber = sis
        }

    constructor() {}

    constructor(inss: String) {
        this.inss = inss
    }

    override fun getIsiCardNumber(): String {
        throw UnsupportedOperationException("this operation won't be supported for therlink specific Patient type : use the org.taktik.connector.business.common.domain.Patient class instead!")
    }

    override fun setIsiCardNumber(isiCardNumber: String) {
        throw UnsupportedOperationException("this operation won't be supported for therlink specific Patient type : use the org.taktik.connector.business.common.domain.Patient class instead!")
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + if (this.name == null) 0 else this.name!!.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        val superEqualsResult = super.equals(obj)
        if (superEqualsResult) {
            if (this === obj) {
                return true
            }

            if (obj == null) {
                return false
            }

            if (this.javaClass != obj.javaClass) {
                return false
            }

            val other = obj as Patient?
            if (this.name == null) {
                if (other!!.name != null) {
                    return false
                }
            } else if (this.name != other!!.name) {
                return false
            }
        }

        return superEqualsResult
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("Patient ").append("[ inss=").append(this.inss).append("]")
        return builder.toString()
    }

    @Deprecated("")
    class Builder {
        private val patient = Patient()

        @Deprecated("")
        fun withFirstName(value: String): Patient.Builder {
            this.patient.firstName = value
            return this
        }

        @Deprecated("")
        fun withFamilyName(value: String): Patient.Builder {
            this.patient.lastName = value
            return this
        }

        @Deprecated("")
        fun withName(value: String): Patient.Builder {
            this.patient.name = value
            return this
        }

        @Deprecated("")
        fun withInss(value: String): Patient.Builder {
            this.patient.inss = value
            return this
        }

        @Deprecated("")
        fun withSis(value: String): Patient.Builder {
            this.patient.sis = value
            return this
        }

        @Deprecated("")
        fun withEid(eid: String): Patient.Builder {
            this.patient.eidCardNumber = eid
            return this
        }

        @Deprecated("")
        @Throws(TherLinkBusinessConnectorException::class)
        fun build(): Patient {
            val hasName = this.patient.firstName == null && this.patient.lastName == null && this.patient.name != null
            val hasCompleteName =
                this.patient.firstName != null && this.patient.lastName != null && this.patient.name == null
            return if (!hasName && !hasCompleteName) {
                throw TherLinkBusinessConnectorException(
                    TherLinkBusinessConnectorExceptionValues.VALIDATION_ERROR,
                    *arrayOf<Any>("Patient should have a firstName and a FamilyName, (X)OR a name and nothing else")
                )
            } else {
                this.patient
            }
        }
    }

    companion object {
        private const val serialVersionUID = -1781258533446951948L
    }
}
