package org.taktik.connector.business.therlink.domain.requests

import org.taktik.connector.business.common.domain.Patient
import org.taktik.connector.business.therlink.domain.HcParty
import org.taktik.connector.business.therlink.domain.OperationContext
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import java.text.SimpleDateFormat
import org.apache.commons.lang.builder.ToStringBuilder

class TherapeuticLinkComplete(
    patient: Patient,
    hcParty: HcParty,
    type: String,
    var operationContext: List<OperationContext>?
) : TherapeuticLink(patient, hcParty, type) {

    override fun toString(): String {
        val builder = ToStringBuilder(this)
        val df = SimpleDateFormat("dd-MM-yyyy")
        builder.append("TherapeuticLink [HCP = ")
        builder.append(this.hcParty)
        builder.append(", Patient = ")
        builder.append(this.patient)
        builder.append(", Startdate = ")
        this.startDate?.let { builder.append(df.format(it.toDateMidnight())) }
        this.endDate?.let { builder.append(df.format(it.toDateMidnight())) }

        builder.append(", Comment = ")
        builder.append(this.comment)
        builder.append(", Status = ")
        builder.append(this.status)
        builder.append(", OperationContexts = ")
        val `i$` = this.operationContext!!.iterator()

        while (`i$`.hasNext()) {
            builder.append("[")
            builder.append(`i$`.next().operation)
            builder.append("]")
        }

        builder.append("]")
        return builder.toString()
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + if (this.operationContext == null) 0 else this.operationContext!!.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        } else if (!super.equals(obj)) {
            return false
        } else if (this.javaClass != obj!!.javaClass) {
            return false
        } else {
            val other = obj as TherapeuticLinkComplete?
            if (this.operationContext == null) {
                if (other!!.operationContext != null) {
                    return false
                }
            } else if (this.operationContext != other!!.operationContext) {
                return false
            }

            return true
        }
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
