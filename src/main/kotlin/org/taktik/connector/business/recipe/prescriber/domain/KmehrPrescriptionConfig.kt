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

package org.taktik.icure.be.ehealth.logic.recipe.impl

import javax.xml.datatype.XMLGregorianCalendar

class KmehrPrescriptionConfig {
    val header = Header()
    val iCure = ICure()
    val prescription = Prescription()

    inner class Header {
        var _idKhmerId: String? = null
        var date: XMLGregorianCalendar? = null
        var time: XMLGregorianCalendar? = null
        var recorddatetime: XMLGregorianCalendar? = null
        var messageId: String? = null
        fun getIdKmehr() = this@KmehrPrescriptionConfig.prescription.inami + '.' + _idKhmerId
    }

    inner class Prescription {
        var inami: String? = null
        var language: String? = null
        var substanceDb: String? = null
    }

    inner class ICure {
        var name: String? = null
        var version: String? = null
        var phone: String? = null
        var mail: String? = null
        var id: String? = null
        var prettyName: String? = null
    }
}
