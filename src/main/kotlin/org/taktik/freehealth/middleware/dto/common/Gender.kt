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

package org.taktik.freehealth.middleware.dto.common

import java.io.Serializable

/**
 * Created by aduchate on 21/01/13, 14:56
 */
enum class Gender constructor(val code: String) : Serializable {
    male("M"), female("F"), indeterminate("I"), changed("C"), changedToMale("Y"), changedToFemale("X"), undefined("U");
    override fun toString(): String = code
    companion object { fun fromCode(code: String?): Gender? = if (code == null) null else Gender.values().firstOrNull { it.code == code } }
}