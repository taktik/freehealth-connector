package org.taktik.connector.business.therlink.domain

import java.io.Serializable
import java.util.Calendar
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.ToStringBuilder
import org.joda.time.DateTime

class OperationContext(
    var recordDateTime: DateTime? = null,
    var operation: String? = null,
    var author: Author? = null,
    var proofs: MutableList<Proof> = ArrayList()
) : Serializable {
    @Deprecated("") constructor(
        operation: String,
        recordDate: Calendar,
        author: Author,
        proofs: MutableList<Proof>
    ) : this(convertToDateTime(recordDate), operation, author, proofs)

    override fun hashCode(): Int {
        val builder = HashCodeBuilder()
        builder.append(this.operation)
        builder.append(this.author)
        builder.append(this.proofs)
        builder.append(this.recordDateTime)
        return builder.toHashCode()
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        } else if (obj !is OperationContext) {
            return false
        } else if (obj === this) {
            return true
        } else {
            val other = obj as OperationContext?
            val builder = EqualsBuilder()
            builder.append(this.operation, other!!.operation)
            builder.append(this.author, other.author)
            builder.append(this.proofs, other.proofs)
            builder.append(this.recordDateTime, other.recordDateTime)
            return builder.isEquals
        }
    }

    override fun toString(): String {
        val builder = ToStringBuilder(this)
        builder.append(this.operation)
        builder.append(this.author)
        builder.append(this.proofs)
        builder.append(this.recordDateTime)
        return builder.toString()
    }

    class Builder {
        private val operationContext = OperationContext()

        fun withOperation(operation: String): OperationContext.Builder {
            this.operationContext.operation = operation
            return this
        }

        @Deprecated("")
        fun withRecordDate(recordDate: Calendar): OperationContext.Builder {
            this.operationContext.recordDateTime = DateTime(recordDate)
            return this
        }

        fun withRecordDateTime(recordDate: DateTime): OperationContext.Builder {
            this.operationContext.recordDateTime = recordDate
            return this
        }

        fun withAuthor(author: Author): OperationContext.Builder {
            this.operationContext.author = author
            return this
        }

        fun addProof(proof: Proof): OperationContext.Builder {
            this.operationContext.proofs.add(proof)
            return this
        }

        fun build(): OperationContext {
            return this.operationContext
        }
    }

    companion object {
        private const val serialVersionUID = 1L

        private fun convertToDateTime(recordDate: Calendar?): DateTime? {
            var convertedCalendarDate: DateTime? = null
            if (recordDate != null) {
                convertedCalendarDate = DateTime(recordDate.timeInMillis)
            }

            return convertedCalendarDate
        }
    }
}
