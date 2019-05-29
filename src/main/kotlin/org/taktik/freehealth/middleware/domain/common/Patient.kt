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

package org.taktik.freehealth.middleware.domain.common

import com.fasterxml.jackson.annotation.JsonInclude
import org.taktik.freehealth.middleware.domain.common.FinancialInstitutionInformation
import org.taktik.freehealth.middleware.domain.common.Insurability
import org.taktik.freehealth.middleware.domain.common.PersonalStatus
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.common.Gender
import java.util.*

@Suppress("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Patient : Person {
    override var firstName: String? = null
    override var lastName: String? = null //Is usually either maidenName or spouseName
    var alias: String? = null
    var active = true
    var ssin: String? = null
    override var civility: String? = null
    override var gender: Gender? = Gender.unknown
    var maidenName: String? = null // Never changes (nom de jeune fille)
    var spouseName: String? = null // Name of the spouse after marriage
    var partnerName: String? = null // Name of the partner, sometimes equal to spouseName
    var personalStatus: PersonalStatus? =
        PersonalStatus.unknown

    var dateOfBirth: Int? = null // YYYYMMDD if unknown, 00, ex:20010000 or
    var dateOfDeath: Int? = null // YYYYMMDD if unknown, 00, ex:20010000 or
    var placeOfBirth: String? = null
    var placeOfDeath: String? = null
    var education: String? = null
    var profession: String? = null
    var note: String? = null
    var warning: String? = null
    var nationality: String? = null

    //No guarantee of unicity
    var externalId: String? = null

    override var addresses: MutableSet<Address> = HashSet()
    var insurabilities: List<Insurability> = ArrayList()
    override var languages: MutableList<String> =
        ArrayList() //alpha-2 code http://www.loc.gov/standards/iso639-2/ascii_8bits.html
    var financialInstitutionInformation: List<FinancialInstitutionInformation> = ArrayList()

    var parameters: Map<String, List<String>> = HashMap()

    var patientProfessions: List<Code> = ArrayList()

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (dateOfBirth == null) 0 else dateOfBirth!!.hashCode()
        result = prime * result + if (firstName == null) 0 else firstName!!.hashCode()
        result = prime * result + if (lastName == null) 0 else lastName!!.hashCode()
        result = prime * result + if (ssin == null) 0 else ssin!!.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) {
            return false
        }
        if (other is Patient) {
            if (dateOfBirth == null) {
                if (other.dateOfBirth != null) return false
            } else if (dateOfBirth != other.dateOfBirth) return false
            if (firstName == null) {
                if (other.firstName != null) return false
            } else if (firstName != other.firstName) return false
            if (lastName == null) {
                if (other.lastName != null) return false
            } else if (lastName != other.lastName) return false
            if (ssin == null) {
                if (other.ssin != null) return false
            } else if (ssin != other.ssin) return false
            return true
        } else {
            return false
        }
    }

    fun mergeFrom(other: Patient) {
        if (this.firstName == null && other.firstName != null) {
            this.firstName = other.firstName
        }
        if (this.lastName == null && other.lastName != null) {
            this.lastName = other.lastName
        }
        if (this.ssin == null && other.ssin != null) {
            this.ssin = other.ssin
        }
        if (this.civility == null && other.civility != null) {
            this.civility = other.civility
        }
        if (this.gender == null && other.gender != null && other.gender !== Gender.unknown) {
            this.gender = other.gender
        }
        if (this.maidenName == null && other.maidenName != null) {
            this.maidenName = other.maidenName
        }
        if (this.spouseName == null && other.spouseName != null) {
            this.spouseName = other.spouseName
        }
        if (this.partnerName == null && other.partnerName != null) {
            this.partnerName = other.partnerName
        }
        if (this.personalStatus == null && other.personalStatus != null) {
            this.personalStatus = other.personalStatus
        }
        if (this.dateOfBirth == null && other.dateOfBirth != null) {
            this.dateOfBirth = other.dateOfBirth
        }
        if (this.dateOfDeath == null && other.dateOfDeath != null) {
            this.dateOfDeath = other.dateOfDeath
        }
        if (this.placeOfBirth == null && other.placeOfBirth != null) {
            this.placeOfBirth = other.placeOfBirth
        }
        if (this.placeOfDeath == null && other.placeOfDeath != null) {
            this.placeOfDeath = other.placeOfDeath
        }
        if (this.education == null && other.education != null) {
            this.education = other.education
        }
        if (this.profession == null && other.profession != null) {
            this.profession = other.profession
        }
        if (this.note == null && other.note != null) {
            this.note = other.note
        }
        if (this.nationality == null && other.nationality != null) {
            this.nationality = other.nationality
        }
        if (this.externalId == null && other.externalId != null) {
            this.externalId = other.externalId
        }

        for (fromAddress in other.addresses) {
            val destAddress =
                this.addresses.stream().filter { address -> address.addressType === fromAddress.addressType }.findAny()
            if (destAddress.isPresent) {
                destAddress.orElseThrow<IllegalStateException>({ IllegalStateException() }).mergeFrom(fromAddress)
            } else {
                this.addresses.add(fromAddress)
            }
        }
    }

    fun forceMergeFrom(other: Patient) {
        if (other.firstName != null) {
            this.firstName = other.firstName
        }
        if (other.lastName != null) {
            this.lastName = other.lastName
        }
        if (other.ssin != null) {
            this.ssin = other.ssin
        }
        if (other.civility != null) {
            this.civility = other.civility
        }
        if (other.gender != null && other.gender !== Gender.unknown) {
            this.gender = other.gender
        }
        if (other.maidenName != null) {
            this.maidenName = other.maidenName
        }
        if (other.spouseName != null) {
            this.spouseName = other.spouseName
        }
        if (other.partnerName != null) {
            this.partnerName = other.partnerName
        }
        if (other.personalStatus != null) {
            this.personalStatus = other.personalStatus
        }
        if (other.dateOfBirth != null) {
            this.dateOfBirth = other.dateOfBirth
        }
        if (other.dateOfDeath != null) {
            this.dateOfDeath = other.dateOfDeath
        }
        if (other.placeOfBirth != null) {
            this.placeOfBirth = other.placeOfBirth
        }
        if (other.placeOfDeath != null) {
            this.placeOfDeath = other.placeOfDeath
        }
        if (other.education != null) {
            this.education = other.education
        }
        if (other.profession != null) {
            this.profession = other.profession
        }
        if (other.note != null) {
            this.note = other.note
        }
        if (other.nationality != null) {
            this.nationality = other.nationality
        }
        if (other.externalId != null) {
            this.externalId = other.externalId
        }

        this.forceMergeAddresses(other.addresses)
    }

    private fun forceMergeAddresses(otherAddresses: Set<Address>) {
        for (fromAddress in otherAddresses) {
            val destAddress =
                this.addresses.stream().filter { address -> address.addressType === fromAddress.addressType }.findAny()
            if (destAddress.isPresent) {
                destAddress.orElseThrow<IllegalStateException>({ IllegalStateException() }).forceMergeFrom(fromAddress)
            } else {
                this.addresses.add(fromAddress)
            }
        }
    }
}
