package org.taktik.freehealth.middleware.dto.efact
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
/**
 * Created with IntelliJ IDEA.
 * User: Julien Wathelet
 * Date: 19/10/01
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
enum class InvoicingTransplantationCode private constructor(val code: Int) {
    None(0),
    RefersToRecipient(1),
    RefersToDonor(2);

    companion object {
        fun referTransplantation(transplantation: Int): InvoicingTransplantationCode? {
            for (s in InvoicingTransplantationCode.values()) {
                if (s.code == transplantation) {
                    return s
                }
            }
            return null

        }
    }

}
