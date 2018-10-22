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

package org.taktik.freehealth.middleware.dto.genins

import org.taktik.freehealth.middleware.dto.InfoRequest.InfoRequestDto
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import java.io.Serializable
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 28/05/13
 * Time: 09:07
 * To change this template use File | Settings | File Templates.
 */
class InsurabilityInfoDto(
    var commonOutput: CommonOutput? = null,
    var mycarenetConversation: MycarenetConversation? = null,
    val inss: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val dateOfBirth: Long? = null,
    val deceased: Long? = null,
    val sex: String? = null,
    val hospitalizedInfo: HospitalizedInfoDto? = null,
    val medicalHouseInfo: MedicalHouseInfoDto? = null,
    val insurabilities: List<InsurabilityItemDto> = listOf(),
    var errors: List<MycarenetError> = ArrayList(),
    var faultMessage: String? = null,
    var faultSource: String? = null,
    var faultCode: String? = null,
    val generalSituation: String? = null,
    val paymentByIo: Boolean = false,
    val specialSocialCategory: Boolean = false,
    val transfers: List<TransferDto>? = null) : Serializable

