package org.taktik.freehealth.middleware.dto.efact

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.taktik.freehealth.middleware.dto.efact.segments.RecordOrSegmentDescription

@JsonIgnoreProperties("description")
class Record(var description: RecordOrSegmentDescription? = null, var zones: List<Zone> = ArrayList(), var errorDetail : ErrorDetail? = null) {
    override fun toString(): String {
        return (description?.toString() ?: "") + ":\n" + zones.map { "\t"+it.toString() }.joinToString("\n")
    }
}
