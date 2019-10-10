package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.AddressType
import be.fgov.ehealth.consultrn._1_0.core.BirthDeceaseType
import be.fgov.ehealth.consultrn._1_0.core.CivilStateType
import be.fgov.ehealth.consultrn._1_0.core.GenderType
import be.fgov.ehealth.consultrn._1_0.core.NameType
import be.fgov.ehealth.consultrn._1_0.core.NationalityType

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import java.io.Serializable

class ConsultRnPersonDataDto(var birth: BirthDeceaseType? = null,
    var name: NameType? = null,
    var gender: GenderType? = null,
    var nationality: NationalityType? = null,
    var civilstate: CivilStateType? = null,
    var decease: BirthDeceaseType? = null,
    var address: ConsultRnAddressDto? = null) : Serializable
