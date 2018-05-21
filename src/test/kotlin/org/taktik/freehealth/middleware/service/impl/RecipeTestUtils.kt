package org.taktik.icure.be.ehealth.logic.recipe.impl

import org.taktik.freehealth.middleware.domain.CompoundPrescription
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.domain.Medicinalproduct
import org.taktik.freehealth.middleware.domain.Patient
import org.taktik.freehealth.middleware.domain.RegimenItem
import org.taktik.freehealth.middleware.domain.Substanceproduct
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.AddressType
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.Telecom
import org.taktik.freehealth.middleware.dto.TelecomType
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.utils.FuzzyValues
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period

/**
 * @author Bernard Paulus on 3/04/17.
 */
class RecipeTestUtils {
    companion object {
        fun createMedication(): Medication {
            val m1 = Medication()

            m1.compoundPrescriptionV2 = CompoundPrescription.MagistralText("Paracetamol Mylan compr 100x 500mg")
            setRegimen(m1)
            return m1
        }

        private fun setRegimen(m1: Medication) {
            m1.beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
            m1.regimen = mutableListOf(RegimenItem().apply {
                administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                    quantity = 2.0
                    administrationUnit = Code(
                        type = "CD-ADMINISTRATIONUNIT",
                        code = "00005" // pill
                                             )
                }
            })
        }

        fun createPatient(): Patient {
            val p = Patient()
            p.lastName = "Doe"
            p.firstName = "John"
            p.ssin = "82051234978"
            p.dateOfBirth = FuzzyValues.getFuzzyDate(LocalDateTime.now().minusYears(20)).toInt()
            p.gender = Gender.male
            return p
        }

        fun createHealthcareParty(): HealthcareParty {
            return HealthcareParty(
                firstName = "Antoine",
                lastName = "Baudoux",
                ssin = "79121430944",
                nihii = "11478761004",
                addresses = mutableSetOf(taktik())
            )
        }

        fun taktik(): Address {
            return Address(
                addressType = AddressType.work,
                houseNumber = "8",
                street = "av. de Finlande",
                postalCode = "1420",
                city = "Braine-l'Alleud",
                country = "BE",
                telecoms = mutableSetOf(Telecom(telecomType = TelecomType.phone, telecomNumber = "+3223335840" ))
            )
        }
    }

    class Medications {
        companion object {
            fun medicinalProductP0(): Medication {
                val panadolPills = Medication().apply {
                    medicinalProduct = Medicinalproduct().apply {
                        intendedcds = listOf(
                            // only reimbursed for chronic diseases ("Chr" in ssec field)
                            // => not reimbursed by default => P0
                            Code("CD-DRUG-CNK", "0605832"),
                            Code("CD-INNCLUSTER", "8038846") // paracetamol 500mg, orally
                                            )
                        intendedname = "Panadol compr. (séc.) 60x 500mg"
                    }
                }
                setRegimen(panadolPills)
                return panadolPills
            }

            fun medicinalProductP1(): Medication {
                val paracetamolTeva = Medication().apply {
                    medicinalProduct = Medicinalproduct().apply {
                        intendedcds = listOf(
                            // this pill has "b4" in its ssec field => reimbursed with conditions
                            // (see http://www.cbip.be/fr/chapters/9?frag=6437&trade_family=20925 )
                            // => we assume it is reimbursed => P1
                            Code("CD-DRUG-CNK", "2713279")
                            // astonishingly, it doesn't have the Code("CD-INNCLUSTER", "8038846") for paracetamol in our drugs db
                                            )
                        intendedname = "Paracetamol Teva compr. disp. (séc.) 100x 500mg"
                    }
                }
                setRegimen(paracetamolTeva)
                return paracetamolTeva
            }

            fun substanceProductVMPGROUP_P1(): Medication {
                val aceclofenac = Medication().apply {
                    substanceProduct = Substanceproduct().apply {
                        intendedcds = listOf(
                            Code("CD-VMPGROUP", "0000174")
                                            )
                        intendedname = "aceclofenac oraal 100 mg, 20 tabletten"
                    }
                }
                setRegimen(aceclofenac)
                return aceclofenac
            }

            fun medicinalProduct0000000P0(): Medication {
                return Medication().apply {
                    medicinalProduct = Medicinalproduct().apply {
                        intendedcds = listOf(Code("CD-DRUG-CNK", "0000000"))
                        intendedname = "La Roche Posay Cicaplast Balsem 100 ml"
                    }
                    beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().minusYears(1))
                    recipeInstructionForPatient = "3 x daags aanbrengen"
                }
            }

            fun substanceProductP0(): Medication {
                val panadolRegulatedDelivery = Medication().apply {
                    substanceProduct = Substanceproduct().apply {
                        intendedcds = listOf(
                            // not required for substance product:
                            // Code("CD-DRUG-CNK", "2523934", "8033912") // Panadol pill modified for increased latency in substance delivery 48x 665mg
                            // only reimbursed for chronically diseased patients ("Chr" in ssec field)
                            // => not reimbursed by default => P0
                            Code("CD-INNCLUSTER", "8033912")
                                            )
                        intendedname = "Panadol compr. lib. modif. Retard 48x 665mg"
                        instructionForPatient = "1 per day for three days"
                    }
                }
                panadolRegulatedDelivery.beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
                return panadolRegulatedDelivery
            }

            fun substanceProductP1(): Medication {
                val paracetamol = Medication().apply {
                    substanceProduct = Substanceproduct().apply {
                        intendedcds = listOf(Code("CD-INNCLUSTER", "8038846"))
                        intendedname = "paracetamol 500mg, orally"
                    }
                    instructionForPatient = "1 per day for three days"
                }
                paracetamol.beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
                return paracetamol
            }

            fun substanceProduct0000000P0(): Medication {
                val paracetamol = Medication().apply {
                    substanceProduct = Substanceproduct().apply {
                        intendedcds = listOf(Code("CD-INNCLUSTER", "8030496"))
                        intendedname = "bisoprolol (10 mg), orally"
                    }
                }
                paracetamol.beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
                return paracetamol
            }

            fun compoundPrescriptionP2(): Medication {
                val snakeOil = Medication().apply {
                    compoundPrescriptionV2 = CompoundPrescription.MagistralText("snake oil (very efficient version)")
                }
                setRegimen(snakeOil)
                return snakeOil
            }

            fun compoundPrescriptionP2Legacy(): Medication {
                val snakeOil = Medication().apply {
                    compoundPrescription = "snake oil (very efficient version)"
                }
                setRegimen(snakeOil)
                return snakeOil
            }
        }
    }

}
