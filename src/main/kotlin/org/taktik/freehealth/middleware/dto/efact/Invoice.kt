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

package org.taktik.freehealth.middleware.dto.efact

import org.taktik.freehealth.middleware.domain.common.Patient
import java.util.LinkedList

class Invoice {
    var patient: Patient? = null
    var ioCode: String? = null
    var items: MutableList<InvoiceItem> = LinkedList()
    var reason: InvoicingTreatmentReasonCode? = null
    var invoiceRef: String? = null
    var invoiceNumber: Long? = null
    var ignorePrescriptionDate: Boolean = false
    var hospitalisedPatient: Boolean = false
    var creditNote: Boolean = false

    var relatedInvoiceIoCode: String? = null
    var relatedInvoiceNumber: Long? = null
    var relatedBatchSendNumber: Long? = null
    var relatedBatchYearMonth: Long? = null
    var startOfCoveragePeriod: Long? = null //yyyyMMdd

    var internshipNihii: String? = null
    var gnotionNihii: String? = null
}
