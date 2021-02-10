package org.taktik.freehealth.middleware.dto.consultrnv2

class RnConsultPersonMid(
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
    var nationalityCode: Int? = 0,
    var dateOfBirth: Int? = null,
    var birthPlace: BirthPlace? = null,
    var gender: String? = null,
    var residentialAddress: ResidentialAddress? = null,
    var contactAddress: ContactAddress? = null,
    var language: String

){
    class BirthPlace(
        var countryCode: Int? = 0,
        var countryIsoCode: String? = null,
        var cityCode: String? = null,
        var cityName: String? = null,
        var countryName: String? = null
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
        var cityCode: String? = null,
        var countryName: String? = null,
        var cityName: String? = null,
        var postalCode: String? = null,
        var streetName: String? = null,
        var typeCode: Int? = 0
    )
}
