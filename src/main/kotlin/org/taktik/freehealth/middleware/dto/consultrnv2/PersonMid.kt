package org.taktik.freehealth.middleware.dto.consultrnv2

import be.fgov.ehealth.rn.baselegaldata.v1.AddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.CivilStatesDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.DeceaseInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesDeclarationType

class PersonMid(
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
    var nationalityCode: Int? = 0,
    var dateOfBirth: Int? = null,
    var birthPlace: BirthPlace? = null,
    var gender: String? = null,
    var residentialAddress: ResidentialAddress? = null,
    var contactAddress: ContactAddress? = null

){
    class BirthPlace(
        var countryCode: Int? = 0,
        var cityCode: String? = null,
        var cityName: String? = null
    )

    class ResidentialAddress(
        var countryCode: Int? = 0,
        var countryIsoCode: String? = null,
        var countryName: String?= null,
        var cityName: String? = null,
        var postalCode: String? = null,
        var streetName: String? = null,
        var houseNumber: String? = null,
        var boxNumber: String? = null
    )

    class ContactAddress(
        var countryCode: Int? = 0,
        var countryIsoCode: String? = null,
        var countryName: String? = null,
        var cityName: String? = null,
        var postalCode: String? = null,
        var streetCode: String? = null,
        var streetName: String? = null,
        var houseNumber: String? = null,
        var boxNumber: String? = null,
        var typeCode: Int? = 0
    )
}
