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

import java.util.*

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 22/06/13
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
class PrescriptionFullWithFeedback(
    creationDate: Date,
    encryptionKeyId: String,
    rid: String,
    isFeedbackAllowed: Boolean = false,
    patientId: String? = null,
    notificationWasSent: Boolean? = false,
    var nihii: String? = null,
    var patientName: String? = null,
    var medicines: MutableList<String> = ArrayList(),
    var deliverableFrom: Date? = null,
    var deliverableTo: Date? = null,
    var feedbacks: List<Feedback> = emptyList(),
    var fullAuthorName: String? = null
) : Prescription(creationDate, encryptionKeyId, rid, isFeedbackAllowed, patientId, notificationWasSent)
