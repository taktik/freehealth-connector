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

package org.taktik.freehealth.middleware.domain

import com.fasterxml.jackson.annotation.JsonInclude
import org.apache.commons.lang3.StringUtils
import org.taktik.freehealth.middleware.dto.Code
import java.io.Serializable
import java.text.SimpleDateFormat

@JsonInclude(JsonInclude.Include.NON_NULL)
class RegimenItem : Serializable {
    //Day definition (One and only one of the three following should be not null)
    //The three are null if it applies to every day
    var date: Long? = null //yyyymmdd at this date
    var dayNumber: Int? = null //day 1 of treatment. 1 based numeration
    var weekday: Weekday? = null //on monday

    //Time of day definition (One and only one of the three following should be not null)
    //Both are null if not specified
    var dayPeriod: Code? = null //CD-DAYPERIOD
    var timeOfDay: Long? = null //hhmmss 103010

    var administratedQuantity: AdministrationQuantity? = null

    class Weekday : Serializable {
        var weekDay: Code? = null //CD-WEEKDAY
        var weekNumber: Int? = null //Can be null
    }

    class AdministrationQuantity : Serializable {
        var quantity: Double? = null
        var administrationUnit: Code? = null //CD-ADMINISTRATIONUNIT
        var unit: String? = null //Should be null

        override fun toString(): String {
            return String.format("%f %s", quantity, if (administrationUnit != null) administrationUnit!!.code else unit)
        }
    }

    override fun toString(): String {
        val df = SimpleDateFormat("dd/MM/yyyy")
        var result: String? =
            if (this.date != null) String.format(
                "the %s",
                df.format(this.date)
            ) else if (this.dayNumber != null) String.format(
                "on day %d",
                this.dayNumber
            ) else if (this.weekday != null && this.weekday!!.weekDay != null && this.weekday!!.weekDay!!.code != null) String.format(
                "on %s",
                this.weekday!!.weekDay!!.code
            ) else null

        if (this.dayPeriod != null && !StringUtils.isEmpty(this.dayPeriod!!.code)) {
            result =
                if (result != null) String.format("%s %s", result, this.dayPeriod!!.code) else this.dayPeriod!!.code
        }
        if (this.timeOfDay != null) {
            val timeOfDayDescr =
                if (this.timeOfDay == 120000L) "noon" else String.format(
                    "%d:%d",
                    this.timeOfDay!! / 10000,
                    this.timeOfDay!! / 100 % 100
                )
            result =
                if (result != null) String.format("%s at %s", result, timeOfDayDescr) else String.format(
                    "at %s",
                    timeOfDayDescr
                )
        }

        return String.format("%s, %s", this.administratedQuantity, result)
    }
}
