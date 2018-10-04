package org.taktik.freehealth.middleware.dto.efact

import com.fasterxml.jackson.annotation.JsonIgnore
import org.taktik.freehealth.middleware.dto.efact.segments.RecordOrSegmentDescription
import org.taktik.freehealth.middleware.dto.efact.segments.Segment200Description

class Record<T:RecordOrSegmentDescription>(@JsonIgnore var description: T? = null, var zones: List<Zone> = ArrayList()) {
    override fun toString(): String {
        return description.toString()+":\n"+zones.map { "\t"+it.toString() }.joinToString("\n")
    }
}
