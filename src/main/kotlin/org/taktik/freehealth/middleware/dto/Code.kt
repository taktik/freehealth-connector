/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import kotlin.collections.HashSet

@JsonInclude(JsonInclude.Include.NON_NULL)
class Code(var type: String? = null, var code: String? = null, var version: String? = null) {
    //ex: type ICD (type + version + code combination must be unique) (or from tags -> CD-ITEM), code I06.2 (or from tags -> healthcareelement). Local codes are encoded as LOCAL:SLLOCALFROMMYSOFT, version must be lexicographically sortable
    var regions: Set<String> = HashSet() //ex: be,fr
    var level: Int? = null //ex: 0 = System, not to be modified by user, 1 = optional, created or modified by user
    var label: MutableMap<String, String> =
        HashMap() //ex: {en: Rheumatic Aortic Stenosis, fr: Sténose rhumatoïde de l'Aorte}
    var links: List<String> = LinkedList() //Links towards related codes
    var searchTerms: Map<String, Set<String>> = HashMap()//Extra search terms/ language
    var data: String? = null

    constructor(typeAndCodeAndVersion: String) : this(
        typeAndCodeAndVersion.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0],
        typeAndCodeAndVersion.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1],
        typeAndCodeAndVersion.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2]
    )

    override fun toString(): String {
        return this.type + ":" + this.code + ":" + this.version
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this.javaClass != other.javaClass) return false
        val code1 = other as Code?
        return type == code1!!.type && code == code1.code && version == code1.version
    }

    override fun hashCode(): Int {
        return Objects.hash(regions, type, code, version, level)
    }
}
