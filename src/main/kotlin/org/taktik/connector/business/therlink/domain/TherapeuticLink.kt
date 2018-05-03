package org.taktik.connector.business.therlink.domain

import org.taktik.connector.business.therlink.domain.requests.TherapeuticLinkStatus
import java.io.Serializable
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.ToStringBuilder
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.taktik.connector.business.common.domain.Patient

open class TherapeuticLink(
    var patient: Patient = Patient(),
    var hcParty: HcParty = HcParty(),
    var type: String = "gpconsultation"
) : Serializable {
    var startDate: LocalDate? = null
    var endDate: LocalDate? = null
    var comment: String? = null
    var status: TherapeuticLinkStatus? = null

    override fun hashCode(): Int {
        val builder = HashCodeBuilder()
        builder.append(this.comment)
        builder.append(this.endDate)
        builder.append(this.hcParty)
        builder.append(this.patient)
        builder.append(this.startDate)
        builder.append(this.status)
        return builder.toHashCode()
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        } else if (obj !is TherapeuticLink) {
            return false
        } else if (obj === this) {
            return true
        } else {
            val other = obj as TherapeuticLink?
            val builder = EqualsBuilder()
            builder.append(this.comment, other!!.comment)
            builder.append(this.endDate, other.endDate)
            builder.append(this.hcParty, other.hcParty)
            builder.append(this.patient, other.patient)
            builder.append(this.startDate, other.startDate)
            builder.append(this.status, other.status)
            return builder.isEquals
        }
    }

    override fun toString(): String {
        val builder = ToStringBuilder(this)
        builder.append(this.comment)
        builder.append(this.endDate)
        builder.append(this.hcParty)
        builder.append(this.patient)
        builder.append(this.startDate)
        builder.append(this.status)
        return builder.toString()
    }

    class Builder {
        private val link = TherapeuticLink()

        fun withPatient(patient: org.taktik.connector.business.common.domain.Patient): TherapeuticLink.Builder {
            this.link.patient = patient
            return this
        }

        fun withHcParty(hcp: HcParty): TherapeuticLink.Builder {
            this.link.hcParty = hcp
            return this
        }

        fun withStartDate(date: LocalDate?): TherapeuticLink.Builder {
            if (date != null) {
                this.link.startDate = date
            }

            return this
        }

        fun withEndDate(date: LocalDate?): TherapeuticLink.Builder {
            if (date != null) {
                this.link.endDate = date
            }

            return this
        }

        fun withStartDateTime(date: DateTime?): TherapeuticLink.Builder {
            if (date != null) {
                this.link.startDate = LocalDate(date.millis)
            }

            return this
        }

        fun withEndDateTime(date: DateTime?): TherapeuticLink.Builder {
            if (date != null) {
                this.link.endDate = LocalDate(date.millis)
            }

            return this
        }

        fun withType(type: String): TherapeuticLink.Builder {
            this.link.type = type
            return this
        }

        fun withComment(comment: String): TherapeuticLink.Builder {
            this.link.comment = comment
            return this
        }

        fun build(): TherapeuticLink {
            return this.link
        }

        fun withStatus(status: TherapeuticLinkStatus): TherapeuticLink.Builder {
            this.link.status = status
            return this
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
