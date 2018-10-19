package org.taktik.freehealth.middleware.dto.dmg

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.Error
import org.taktik.freehealth.middleware.dto.MycarenetError

import java.io.Serializable
import java.time.Instant

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 15/06/14
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
class DmgConsultation(
    isComplete: Boolean? = null,
    io: String? = null,
    reference: String? = null,
    valueHash: String? = null,
    errors: MutableList<MycarenetError> = ArrayList(),
    var inss: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var birthday: Long? = null,
    var deceased: Long? = null,
    var sex: String? = null,
    var regNrWithMut: String? = null,
    var mutuality: String? = null,
    var hcParty: HcpartyType? = null,
    var from: Long? = null,
    var to: Long? = null,
    var payment: Boolean? = null
    ) : DmgMessage(isComplete, io, reference, valueHash, errors), Serializable
