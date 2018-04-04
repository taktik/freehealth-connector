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

package org.taktik.freehealth.middleware.dao

import com.google.gson.Gson
import org.springframework.stereotype.Repository
import org.taktik.freehealth.middleware.dto.Code

@Repository
class CodeDao(gson: Gson) {
    class CodesMap {
        val version: String? = null
        val date: String? = null
        val status: String? = null
        val name: String? = null
        val codes: Map<String, Map<String, String>> = HashMap()
    }

    val fedCountryMap = null //gson.fromJson(javaClass.getResourceAsStream("/cdFedCountry.json").reader(Charsets.UTF_8), CodesMap::class.java)
    val administrationunitMap = null // gson.fromJson(javaClass.getResourceAsStream("/cdAdministrationUnit.json").reader(Charsets.UTF_8), CodesMap::class.java)
    val innclusterMap = null //gson.fromJson(javaClass.getResourceAsStream("/cdInncluster.json").reader(Charsets.UTF_8), CodesMap::class.java)

    fun isValid(code: Code): Boolean {
        return codesMap(code.type)?.let { it.codes[code.code] != null } ?: false
    }

    fun codesMap(type : String) : CodesMap? {
       return when(type) {
           "CD-ADMINISTRATIONUNIT" -> administrationunitMap
           "CD-FED-COUNTRY" -> fedCountryMap
           "CD-INNCLUSTER" -> innclusterMap
           else -> null
       }
    }

    fun getCodeByLabel(label: String, type: String): Code? {
        return codesMap(type)?.let { it.codes.entries.find { it.value.containsValue(label) }?.let { Code(type, it.key).apply { this.label.putAll(it.value) }} }
    }

    fun ensureValid(code: Code, ofType: String, orDefault: Code?): Code? {
        return if (code.type != ofType) orDefault else codesMap(code.type)?.let { it.codes[code.code]?.let { Code(ofType, code.code).apply { this.label.putAll(it) } } } ?: orDefault
    }

    fun findCode(type: String, code: String, version: String): Code? {
        return codesMap(type)?.codes?.get(code)?.let { Code(type, code).apply { this.label.putAll(it) }}
    }

    fun findCodesByLabel(lang: String, type: String, searchString: String):List<Code> {
        return codesMap(type)?.let { it.codes.entries.filter { it.value[lang]?.contains(searchString,ignoreCase = true) ?: false }.map { Code(type, it.key).apply { this.label.putAll(it.value) }} } ?: arrayListOf()
    }
}