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

class Receipt95 {
    var accountARequestedAmount: Long = 0
    var accountARequestedAmountSign: String? = null
    var accountBRequestedAmount: Long = 0
    var accountBRequestedAmountSign: String? = null
    var accountsABTotalRequestedAmountSign: String? = null
    var accountsABTotalRequestedAmount: Long = 0
    var invoiceRecapNumber: Long = 0
    var mutualityCode: Int = 0
    var mutualityControlNumber: Int = 0
    var recordsAmount: Int = 0
    var reserve: String? = null
    var type: Int = 0

    constructor()
}
