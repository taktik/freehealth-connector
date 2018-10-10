/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.format.efact

class InvoiceReceipt {
    var contactPersonLastName: String? = null
    var contactPersonFirstName: String? = null
    var contactPersonPhone: String? = null
    var instructionsVersion: Int = 0
    var invoiceCreationDate: Int = 0
    var invoiceReference: String? = null
    var invoiceType: Int = 0
    var invoicingType: Int = 0
    var invoicingYearMonth: Int = 0
    var reserve: String? = null
    var sendNumber: Int = 0
}
