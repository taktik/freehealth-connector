package org.taktik.freehealth.middleware.dto.consultrnv2

import be.fgov.ehealth.rn.baselegaldata.v1.AddressBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.AdministratorBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.CivilStatesBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.LegalCohabitationBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.NobilityTitleBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.SubregisterBaseType
import be.fgov.ehealth.rn.commons.v1.SsinType
import be.fgov.ehealth.rn.registries.commons.v1.AnomaliesType
import java.io.Serializable

class ConsultRnPersonDto(
    var register: String? = null,
    var registerInceptionDate: String? = null,
    var ssin: SsinType? = null,
    var nobilityTitle: NobilityTitleBaseType? = null,
    var name: NameInfoBaseType? = null,
    var nationalities: NationalitiesBaseType? = null,
    var birth: BirthInfoBaseType? = null,
    var gender: GenderInfoBaseType? = null,
    var civilStates: CivilStatesBaseType? = null,
    var address: AddressBaseType? = null,
    var contactAddress: ContactAddressBaseType? = null,
    var administrator: AdministratorBaseType? = null,
    var subregister: SubregisterBaseType? = null,
    var legalCohabitation: LegalCohabitationBaseType? = null,
    var anomalies: AnomaliesType? = null
) : Serializable
