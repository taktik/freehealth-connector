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

package org.taktik.connector.business.ehbox.api.utils

import org.taktik.connector.technical.utils.IdentifierType
import java.lang.reflect.Modifier
import java.util.HashMap
import kotlin.collections.Map.Entry

class QualityType private constructor(val quality: String, val identifierType: IdentifierType) {

    fun name(): String {
        return getKeyByValue(predefinedQual, this)!!
    }

    companion object {
        val AMBULANCE_RESCUER_NIHII: QualityType
        val AMBULANCE_RESCUER_SSIN: QualityType
        val APPLIED_PSYCH_BACHELOR_NIHII: QualityType
        val APPLIED_PSYCH_BACHELOR_SSIN: QualityType
        val AUDICIEN_NIHII: QualityType
        val AUDICIEN_SSIN: QualityType
        val AUDIOLOGIST_NIHII: QualityType
        val AUDIOLOGIST_SSIN: QualityType
        val CONSORTIUM_CBE: QualityType
        val CITIZEN: QualityType
        val CTRL_ORGANISM_EHP: QualityType
        val DENTIST_NIHII: QualityType
        val DENTIST_SSIN: QualityType
        val DIETICIAN_NIHII: QualityType
        val DIETICIAN_SSIN: QualityType
        val DOCTOR_NIHII: QualityType
        val DOCTOR_SSIN: QualityType
        val FAMILY_SCIENCE_BACHELOR_NIHII: QualityType
        val FAMILY_SCIENCE_BACHELOR_SSIN: QualityType
        val GERONTOLOGY_MASTER_NIHII: QualityType
        val GERONTOLOGY_MASTER_SSIN: QualityType
        val GROUP_NIHII: QualityType
        val GROUP_DOCTORS_NIHII: QualityType
        val GUARD_POST_NIHII: QualityType
        val HOME_SERVICES_NIHII: QualityType
        val HOSPITAL_NIHII: QualityType
        val IMAGING_TECHNOLOGIST_NIHII: QualityType
        val IMAGING_TECHNOLOGIST_SSIN: QualityType
        val IMPLANTPROVIDER_NIHII: QualityType
        val IMPLANTPROVIDER_SSIN: QualityType
        val INSTITUTION_CBE: QualityType
        val INSTITUTION_EHP_EHP: QualityType
        val LABO_NIHII: QualityType
        val LAB_TECHNOLOGIST_NIHII: QualityType
        val LAB_TECHNOLOGIST_SSIN: QualityType
        val LOGOPEDIST_NIHII: QualityType
        val LOGOPEDIST_SSIN: QualityType
        val MEDICAL_HOUSE_NIHII: QualityType
        val MIDWIFE_NIHII: QualityType
        val MIDWIFE_SSIN: QualityType
        val NURSE_NIHII: QualityType
        val NURSE_SSIN: QualityType
        val OCCUPATIONAL_THERAPIST_NIHII: QualityType
        val OCCUPATIONAL_THERAPIST_SSIN: QualityType
        val OFFICE_DENTISTS_NIHII: QualityType
        val OFFICE_DOCTORS_NIHII: QualityType
        val OF_BAND_NIHII: QualityType
        val OF_PHYSIOS_NIHII: QualityType
        val OPTICIEN_NIHII: QualityType
        val OPTICIEN_SSIN: QualityType
        val ORTHOPEDAGOGIST_MASTER_NIHII: QualityType
        val ORTHOPEDAGOGIST_MASTER_SSIN: QualityType
        val ORTHOPEDIST_NIHII: QualityType
        val ORTHOPEDIST_SSIN: QualityType
        val ORTHOPTIST_NIHII: QualityType
        val ORTHOPTIST_SSIN: QualityType
        val OTD_PHARMACY_NIHII: QualityType
        val PALLIATIVE_CARE_NIHII: QualityType
        val PEDIATRIC_NURSE_NIHII: QualityType
        val PEDIATRIC_NURSE_SSIN: QualityType
        val PHARMACIST_NIHII: QualityType
        val PHARMACIST_SSIN: QualityType
        val PHARMACIST_ASSISTANT_NIHII: QualityType
        val PHARMACIST_ASSISTANT_SSIN: QualityType
        val PHARMACY_NIHII: QualityType
        val PHYSIOTHERAPIST_NIHII: QualityType
        val PHYSIOTHERAPIST_SSIN: QualityType
        val PODOLOGIST_NIHII: QualityType
        val PODOLOGIST_SSIN: QualityType
        val PRACTICALNURSE_NIHII: QualityType
        val PRACTICALNURSE_SSIN: QualityType
        val PROT_ACC_NIHII: QualityType
        val PSYCHOLOGIST_NIHII: QualityType
        val PSYCHOLOGIST_SSIN: QualityType
        val PSYCHOMOTOR_THERAPY_NIHII: QualityType
        val PSYCHOMOTOR_THERAPY_SSIN: QualityType
        val PSYCH_HOUSE_NIHII: QualityType
        val READAPTATION_BACHELOR_NIHII: QualityType
        val READAPTATION_BACHELOR_SSIN: QualityType
        val RETIREMENT_NIHII: QualityType
        val SOCIAL_WORKER_NIHII: QualityType
        val SOCIAL_WORKER_SSIN: QualityType
        val SPECIALIZED_EDUCATOR_NIHII: QualityType
        val SPECIALIZED_EDUCATOR_SSIN: QualityType
        val TREATMENT_CENTER_CBE: QualityType
        val TRUSS_MAKER_NIHII: QualityType
        val TRUSS_MAKER_SSIN: QualityType

        @Deprecated("")
        val GENERAL_PRACTIONER_SSIN: QualityType

        @Deprecated("")
        val GENERAL_PRACTIONER_NIHII: QualityType

        @Deprecated("")
        val GROUPOFNURSES_NIHII: QualityType
        private var predefinedQual: MutableMap<String, QualityType>? = null

        fun getInstance(quality: String, type: IdentifierType): QualityType {
            val `i$` = predefinedQual!!.values.iterator()

            var qual: QualityType
            do {
                if (!`i$`.hasNext()) {
                    qual = QualityType(quality, type)
                    predefinedQual!!.put(quality + "_" + type.name(), qual)
                    return qual
                }

                qual = `i$`.next()
            } while (qual.quality != quality || qual.identifierType != type)

            return qual
        }

        fun valueOf(key: String): QualityType {
            return predefinedQual!![key.toUpperCase().replace("INSS".toRegex(), "SSIN")] as QualityType
        }

        fun valueOf(quality: String, type: String): QualityType {
            return predefinedQual!![(quality + "_" + type).toUpperCase().replace(
                "INSS".toRegex(),
                "SSIN"
            )] as QualityType
        }

        private fun <T, E> getKeyByValue(map: Map<T, E>?, value: E): T? {
            val `i$` = map!!.entries.iterator()

            var entry: Entry<*, *>
            do {
                if (!`i$`.hasNext()) {
                    return null
                }

                entry = `i$`.next()
            } while (value != entry.value)

            return entry.key as T
        }


        init {
            AMBULANCE_RESCUER_NIHII = QualityType("AMBULANCE_RESCUER", IdentifierType.NIHII)
            AMBULANCE_RESCUER_SSIN = QualityType("AMBULANCE_RESCUER", IdentifierType.SSIN)
            APPLIED_PSYCH_BACHELOR_NIHII = QualityType("APPLIED_PSYCH_BACHELOR", IdentifierType.NIHII)
            APPLIED_PSYCH_BACHELOR_SSIN = QualityType("APPLIED_PSYCH_BACHELOR", IdentifierType.SSIN)
            AUDICIEN_NIHII = QualityType("AUDICIEN", IdentifierType.NIHII)
            AUDICIEN_SSIN = QualityType("AUDICIEN", IdentifierType.SSIN)
            AUDIOLOGIST_NIHII = QualityType("AUDIOLOGIST", IdentifierType.NIHII)
            AUDIOLOGIST_SSIN = QualityType("AUDIOLOGIST", IdentifierType.SSIN)
            CONSORTIUM_CBE = QualityType("CONSORTIUM", IdentifierType.CBE_CONSORTIUM)
            CITIZEN = QualityType("CITIZEN", IdentifierType.SSIN)
            CTRL_ORGANISM_EHP = QualityType("CTRL_ORGANISM", IdentifierType.EHP_CTRL_ORGANISM)
            DENTIST_NIHII = QualityType("DENTIST", IdentifierType.NIHII)
            DENTIST_SSIN = QualityType("DENTIST", IdentifierType.SSIN)
            DIETICIAN_NIHII = QualityType("DIETICIAN", IdentifierType.NIHII)
            DIETICIAN_SSIN = QualityType("DIETICIAN", IdentifierType.SSIN)
            DOCTOR_NIHII = QualityType("DOCTOR", IdentifierType.NIHII)
            DOCTOR_SSIN = QualityType("DOCTOR", IdentifierType.SSIN)
            FAMILY_SCIENCE_BACHELOR_NIHII = QualityType("FAMILY_SCIENCE_BACHELOR", IdentifierType.NIHII)
            FAMILY_SCIENCE_BACHELOR_SSIN = QualityType("FAMILY_SCIENCE_BACHELOR", IdentifierType.SSIN)
            GERONTOLOGY_MASTER_NIHII = QualityType("GERONTOLOGY_MASTER", IdentifierType.NIHII)
            GERONTOLOGY_MASTER_SSIN = QualityType("GERONTOLOGY_MASTER", IdentifierType.SSIN)
            GROUP_NIHII = QualityType("GROUP", IdentifierType.NIHII_GROUPOFNURSES)
            GROUP_DOCTORS_NIHII = QualityType("GROUP_DOCTORS", IdentifierType.NIHII_GROUP_DOCTORS)
            GUARD_POST_NIHII = QualityType("GUARD_POST", IdentifierType.NIHII_GUARD_POST)
            HOME_SERVICES_NIHII = QualityType("HOME_SERVICES", IdentifierType.NIHII_HOME_SERVICES)
            HOSPITAL_NIHII = QualityType("HOSPITAL", IdentifierType.NIHII_HOSPITAL)
            IMAGING_TECHNOLOGIST_NIHII = QualityType("IMAGING_TECHNOLOGIST", IdentifierType.NIHII)
            IMAGING_TECHNOLOGIST_SSIN = QualityType("IMAGING_TECHNOLOGIST", IdentifierType.SSIN)
            IMPLANTPROVIDER_NIHII = QualityType("IMPLANTPROVIDER", IdentifierType.NIHII)
            IMPLANTPROVIDER_SSIN = QualityType("IMPLANTPROVIDER", IdentifierType.SSIN)
            INSTITUTION_CBE = QualityType("INSTITUTION", IdentifierType.CBE)
            INSTITUTION_EHP_EHP = QualityType("INSTITUTION_EHP", IdentifierType.EHP)
            LABO_NIHII = QualityType("LABO", IdentifierType.NIHII_LABO)
            LAB_TECHNOLOGIST_NIHII = QualityType("LAB_TECHNOLOGIST", IdentifierType.NIHII)
            LAB_TECHNOLOGIST_SSIN = QualityType("LAB_TECHNOLOGIST", IdentifierType.SSIN)
            LOGOPEDIST_NIHII = QualityType("LOGOPEDIST", IdentifierType.NIHII)
            LOGOPEDIST_SSIN = QualityType("LOGOPEDIST", IdentifierType.SSIN)
            MEDICAL_HOUSE_NIHII = QualityType("MEDICAL_HOUSE", IdentifierType.NIHII_MEDICAL_HOUSE)
            MIDWIFE_NIHII = QualityType("MIDWIFE", IdentifierType.NIHII)
            MIDWIFE_SSIN = QualityType("MIDWIFE", IdentifierType.SSIN)
            NURSE_NIHII = QualityType("NURSE", IdentifierType.NIHII)
            NURSE_SSIN = QualityType("NURSE", IdentifierType.SSIN)
            OCCUPATIONAL_THERAPIST_NIHII = QualityType("OCCUPATIONAL_THERAPIST", IdentifierType.NIHII)
            OCCUPATIONAL_THERAPIST_SSIN = QualityType("OCCUPATIONAL_THERAPIST", IdentifierType.SSIN)
            OFFICE_DENTISTS_NIHII = QualityType("OFFICE_DENTISTS", IdentifierType.NIHII_OFFICE_DENTISTS)
            OFFICE_DOCTORS_NIHII = QualityType("OFFICE_DOCTORS", IdentifierType.NIHII_OFFICE_DOCTORS)
            OF_BAND_NIHII = QualityType("OF_BAND", IdentifierType.NIHII_OF_BAND)
            OF_PHYSIOS_NIHII = QualityType("OF_PHYSIOS", IdentifierType.NIHII_OF_PHYSIOS)
            OPTICIEN_NIHII = QualityType("OPTICIEN", IdentifierType.NIHII)
            OPTICIEN_SSIN = QualityType("OPTICIEN", IdentifierType.SSIN)
            ORTHOPEDAGOGIST_MASTER_NIHII = QualityType("ORTHOPEDAGOGIST_MASTER", IdentifierType.NIHII)
            ORTHOPEDAGOGIST_MASTER_SSIN = QualityType("ORTHOPEDAGOGIST_MASTER", IdentifierType.SSIN)
            ORTHOPEDIST_NIHII = QualityType("ORTHOPEDIST", IdentifierType.NIHII)
            ORTHOPEDIST_SSIN = QualityType("ORTHOPEDIST", IdentifierType.SSIN)
            ORTHOPTIST_NIHII = QualityType("ORTHOPTIST", IdentifierType.NIHII)
            ORTHOPTIST_SSIN = QualityType("ORTHOPTIST", IdentifierType.SSIN)
            OTD_PHARMACY_NIHII = QualityType("OTD_PHARMACY", IdentifierType.NIHII_OTD_PHARMACY)
            PALLIATIVE_CARE_NIHII = QualityType("PALLIATIVE_CARE", IdentifierType.NIHII_PALLIATIVE_CARE)
            PEDIATRIC_NURSE_NIHII = QualityType("PEDIATRIC_NURSE", IdentifierType.NIHII)
            PEDIATRIC_NURSE_SSIN = QualityType("PEDIATRIC_NURSE", IdentifierType.SSIN)
            PHARMACIST_NIHII = QualityType("PHARMACIST", IdentifierType.NIHII)
            PHARMACIST_SSIN = QualityType("PHARMACIST", IdentifierType.SSIN)
            PHARMACIST_ASSISTANT_NIHII = QualityType("PHARMACIST_ASSISTANT", IdentifierType.NIHII)
            PHARMACIST_ASSISTANT_SSIN = QualityType("PHARMACIST_ASSISTANT", IdentifierType.SSIN)
            PHARMACY_NIHII = QualityType("PHARMACY", IdentifierType.NIHII_PHARMACY)
            PHYSIOTHERAPIST_NIHII = QualityType("PHYSIOTHERAPIST", IdentifierType.NIHII)
            PHYSIOTHERAPIST_SSIN = QualityType("PHYSIOTHERAPIST", IdentifierType.SSIN)
            PODOLOGIST_NIHII = QualityType("PODOLOGIST", IdentifierType.NIHII)
            PODOLOGIST_SSIN = QualityType("PODOLOGIST", IdentifierType.SSIN)
            PRACTICALNURSE_NIHII = QualityType("PRACTICALNURSE", IdentifierType.NIHII)
            PRACTICALNURSE_SSIN = QualityType("PRACTICALNURSE", IdentifierType.SSIN)
            PROT_ACC_NIHII = QualityType("PROT_ACC", IdentifierType.NIHII_PROT_ACC)
            PSYCHOLOGIST_NIHII = QualityType("PSYCHOLOGIST", IdentifierType.NIHII)
            PSYCHOLOGIST_SSIN = QualityType("PSYCHOLOGIST", IdentifierType.SSIN)
            PSYCHOMOTOR_THERAPY_NIHII = QualityType("PSYCHOMOTOR_THERAPY", IdentifierType.NIHII)
            PSYCHOMOTOR_THERAPY_SSIN = QualityType("PSYCHOMOTOR_THERAPY", IdentifierType.SSIN)
            PSYCH_HOUSE_NIHII = QualityType("PSYCH_HOUSE", IdentifierType.NIHII_PSYCH_HOUSE)
            READAPTATION_BACHELOR_NIHII = QualityType("READAPTATION_BACHELOR", IdentifierType.NIHII)
            READAPTATION_BACHELOR_SSIN = QualityType("READAPTATION_BACHELOR", IdentifierType.SSIN)
            RETIREMENT_NIHII = QualityType("RETIREMENT", IdentifierType.NIHII_RETIREMENT)
            SOCIAL_WORKER_NIHII = QualityType("SOCIAL_WORKER", IdentifierType.NIHII)
            SOCIAL_WORKER_SSIN = QualityType("SOCIAL_WORKER", IdentifierType.SSIN)
            SPECIALIZED_EDUCATOR_NIHII = QualityType("SPECIALIZED_EDUCATOR", IdentifierType.NIHII)
            SPECIALIZED_EDUCATOR_SSIN = QualityType("SPECIALIZED_EDUCATOR", IdentifierType.SSIN)
            TREATMENT_CENTER_CBE = QualityType("TREATMENT_CENTER", IdentifierType.CBE_TREATCENTER)
            TRUSS_MAKER_NIHII = QualityType("TRUSS_MAKER", IdentifierType.NIHII)
            TRUSS_MAKER_SSIN = QualityType("TRUSS_MAKER", IdentifierType.SSIN)
            GENERAL_PRACTIONER_SSIN = DOCTOR_SSIN
            GENERAL_PRACTIONER_NIHII = DOCTOR_NIHII
            GROUPOFNURSES_NIHII = GROUP_NIHII
            predefinedQual = HashMap()
            val fields = QualityType::class.java.declaredFields
            val `len$` = fields.size

            for (`i$` in 0 until `len$`) {
                val f = fields[`i$`]
                if (Modifier.isStatic(f.modifiers) && QualityType::class.java.isAssignableFrom(f.type) &&
                    !f.isAnnotationPresent(Deprecated::class.java)) {
                    try {
                        predefinedQual!!.put(f.name, f.get(AMBULANCE_RESCUER_NIHII) as QualityType)
                    } catch (var6: IllegalAccessException) {
                        throw IllegalArgumentException(var6)
                    }
                }
            }
        }
    }
}
