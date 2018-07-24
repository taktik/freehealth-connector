package org.taktik.icure.be.ehealth.logic.recipe.impl.examples

import org.taktik.freehealth.middleware.domain.Compound
import org.taktik.freehealth.middleware.domain.CompoundPrescription
import org.taktik.freehealth.middleware.domain.Duration
import org.taktik.freehealth.middleware.domain.KmehrQuantity
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.domain.MedicationRenewal
import org.taktik.freehealth.middleware.domain.Medicinalproduct
import org.taktik.freehealth.middleware.domain.Patient
import org.taktik.freehealth.middleware.domain.RegimenItem
import org.taktik.freehealth.middleware.domain.ReimbursementInstructions
import org.taktik.freehealth.middleware.domain.Substance
import org.taktik.freehealth.middleware.domain.Substanceproduct
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.AddressType
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.Telecom
import org.taktik.freehealth.middleware.dto.TelecomType
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.service.impl.examples.PrescriptionExample
import org.taktik.freehealth.utils.FuzzyValues
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

/**
 * prescription examples
 * @author Bernard Paulus on 18/04/17.
 */

fun prescriptionExample1() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(adalatOrosMedication()))

fun prescriptionExample2() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(laRochePosayMedication()))

fun prescriptionExample3() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(oxygenMedication()))

fun prescriptionExample4() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(adalatOrosMedication(), laRochePosayMedication(), ranitidineHCLMedication()))

fun prescriptionExample5() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(ranitidineHCLMedication()))

fun prescriptionExample5Legacy() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(ranitidineHCLMedicationLegacy()))

fun prescriptionExample6() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(dermovateCremeMedication6()))

fun prescriptionExample7() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(monoxidineMedication()))

fun prescriptionExample8() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(bisoprololMedication90daysDuration()))

fun prescriptionExample9() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(brufenForteMedication()))

fun prescriptionExample10() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(solatolMylanMedication()))

fun prescriptionExample11() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(adalatOrosMedication(), dermovateCremeMedication11()), LocalDateTime.of(2017, 2, 1, 10,0,0))

fun prescriptionExample12() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(yasminDragMedication()))

fun prescriptionExample13() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(terraCortrilMedication()))

fun prescriptionExample14() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(belsarMedication()))

fun prescriptionExample15() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(augmentinMedication()))

fun prescriptionExample16() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(bisoprololMedication90Tabs()))

fun prescriptionExample17() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(bisoprololMedicationSubstanceNoCNK()))

fun prescriptionExample18() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(erythromycineFormularyCompoundMedication()))

fun prescriptionExample19() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(betnelanCetomacrCremeCompoundMedication()))

fun prescriptionExample20() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(aceclofenac10daysSubstanceVMPGROUP()))

fun prescriptionExample21() =
    PrescriptionExample(donaldDuckHealthcareParty(), fredFlintstonePatient(), listOf(aceclofenacSubstanceVMPGROUP()))

// base methods
fun adalatOrosMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0318717"))
            intendedname = "ADALAT OROS 30 COMP 28 X 30 MG"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "1 tablet per dag"
    }
}

fun laRochePosayMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0000000"))
            intendedname = "La Roche Posay Cicaplast Balsem 100 ml"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "3 x daags aanbrengen"
    }
}

fun oxygenMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0000000"))
            intendedname = "Traitement oxygène gazeuse pour 1 mois"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "10 L/min pendant environ 15 minutes"
    }
}

fun ranitidineHCLMedication(): Medication {
    return Medication().apply {
        compoundPrescriptionV2 = CompoundPrescription.MagistralText("""
								R/	Ranitidine.HCL 										1.675g
									Mononatriumfosfaat dihydraat 				0.3g
									Dinatriumfosfaat dihydraat 						1.3g
									Water 													30g
									Sterke oranjeschiltinctuur 						qs
									Geconserveerde enkelvoudige siroop 	ad 100ml

									dt 300 ml
							""")
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "2 x daags 5 ml"
    }
}

fun ranitidineHCLMedicationLegacy(): Medication {
    return Medication().apply {
        compoundPrescription = """
								R/	Ranitidine.HCL 										1.675g
									Mononatriumfosfaat dihydraat 				0.3g
									Dinatriumfosfaat dihydraat 						1.3g
									Water 													30g
									Sterke oranjeschiltinctuur 						qs
									Geconserveerde enkelvoudige siroop 	ad 100ml

									dt 300 ml
							"""
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "2 x daags 5 ml"
    }
}

fun dermovateCremeMedication6(): Medication {
    val medication = dermovateCremeMedication()
    medication.apply {
        endMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(30))
        instructionForPatient = "appliquer 2 foix par jour pdt 30 jours"
    }
    return medication
}

fun dermovateCremeMedication11(): Medication {
    val medication = dermovateCremeMedication()
    medication.apply {
        instructionForPatient = "2x per dag aanbrengen"
    }
    return medication
}

fun dermovateCremeMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0035717"))
            intendedname = "DERMOVATE CREME 1 X 30 G  0,05%"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        temporality = Code("CD-TEMPORALITY", "acute")
    }
}

fun monoxidineMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "2312577"))
            intendedname = "MOXONIDINE SANDOZ COMP 100 X 0,4 MG"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        temporality = Code("CD-TEMPORALITY", "chronic")
        instructionForPatient = "1 tablet dagelijks, morgens"
        regimen = mutableListOf(RegimenItem().apply {
            dayPeriod = Code("CD-DAYPERIOD", "morning")
            administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                quantity = 1.0
                administrationUnit = Code("CD-ADMINISTRATIONUNIT", "00005")
            }
        })
        intakeRoute = Code("CD-DRUG-ROUTE", "00060")
        recipeInstructionForPatient = "Inslikken en doorspoelen met een glas water"
    }
}

fun bisoprololMedication90daysDuration(): Medication {
    return bisoprololMedication().apply {
        substanceProduct!!.intendedname = "bisoprolol (10 mg), oraal"
        duration = Duration().apply {
            value = 90.0
            unit = Code("CD-TIMEUNIT", "d")
        }
        instructionForPatient = "1 tablet per dag tijdens ontbijt, therapie voor 90 dagen"
    }
}

fun bisoprololMedication90Tabs(): Medication {
    return bisoprololMedication().apply {
        substanceProduct!!.intendedname = "bisoprolol (10 mg) oraal, 90 tabletten"
        instructionForPatient = "1 tablet per dag tijdens ontbijt"
    }
}

fun bisoprololMedicationSubstanceNoCNK(): Medication {
    // possible but strongly discouraged in practice
    return bisoprololMedication90daysDuration().apply {
        substanceProduct!!.intendedcds = listOf(Code("CD-INNCLUSTER", "0000000"))
    }
}

fun bisoprololMedication(): Medication {
    return Medication().apply {
        substanceProduct = Substanceproduct().apply {
            intendedcds = listOf(Code("CD-INNCLUSTER", "8030496"))
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        temporality = Code("CD-TEMPORALITY", "chronic")

        regimen = mutableListOf(RegimenItem().apply {
            dayPeriod = Code("CD-DAYPERIOD", "duringbreakfast")
            administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                quantity = 1.0
                administrationUnit = Code("CD-ADMINISTRATIONUNIT", "00005")
            }
        })
        intakeRoute = Code("CD-DRUG-ROUTE", "00060")
    }
}

fun brufenForteMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0867556"))
            intendedname = "BRUFEN FORTE TABL 30 X 600 MG"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        temporality = Code("CD-TEMPORALITY", "acute")
        duration = Duration().apply {
            value = 5.0
            unit = Code("CD-TIMEUNIT", "d")
        }
        instructionForPatient = "3 tabletten per dag, gedurende 5 dagen"
    }
}

fun solatolMylanMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "1560929"))
            intendedname = "SOTALOL MYLAN 160 MG TABL 56X160MG"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        temporality = Code("CD-TEMPORALITY", "chronic")
        instructionForPatient = "Dagelijks: halve tablet voor ontbijt, halve tablet voor avondmaal"
        regimen = mutableListOf(
            RegimenItem().apply {
                dayPeriod = Code("CD-DAYPERIOD", "beforebreakfast")
                administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                    quantity = 0.5
                    administrationUnit = Code("CD-ADMINISTRATIONUNIT", "00005")
                }
            },
            RegimenItem().apply {
                dayPeriod = Code("CD-DAYPERIOD", "beforedinner")
                administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                    quantity = 0.5
                }
            }
                               )
        intakeRoute = Code("CD-DRUG-ROUTE", "00060")
        recipeInstructionForPatient = "Inslikken en doorspoelen met een glas water"
    }
}

fun yasminDragMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "1596915"))
            intendedname = "YASMIN DRAG  3 X 21"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "1 tablet per dag"
        renewal = MedicationRenewal().apply {
            allowedRenewals = 3
            delayBetweenDeliveries = Duration().apply {
                value = 3.0
                unit = Code("CD-TIMEUNIT", "mo")
            }
        }
    }
}

fun terraCortrilMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0132407"))
            intendedname = "TERRA-CORTRIL UNG OPHT/OTIC 1X 3,5G"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "2 x per dag een kleine hoeveelheid aanbrengen in het oor"
        intakeRoute = Code("CD-DRUG-ROUTE", "001")
    }
}

fun belsarMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "2115517"))
            intendedname = "BELSAR COMP 98 X 20 MG"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "1 par jour"
        instructionsForReimbursement = ReimbursementInstructions.CHRONIC_KINDEY_DISEASE
    }
}

fun augmentinMedication(): Medication {
    return Medication().apply {
        medicinalProduct = Medicinalproduct().apply {
            intendedcds = listOf(Code("CD-DRUG-CNK", "0029025"))
            intendedname = "AUGMENTIN COMP 16 X 500MG"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "3 comprimés par jour"
        substitutionAllowed = false
    }
}

fun erythromycineFormularyCompoundMedication(): Medication {
    val erythromycine = "ERYTHROMYCINE SOL. HYDRO-ALC. 4% FTM2"
    return Medication().apply {
        compoundPrescriptionV2 = CompoundPrescription.FormularyReference.Formulary().apply {
            formularyId = "TMF2"
            reference = Code(
                type = "CD-FORMULARYREFERENCE",
                code = "0589028").apply {
                label = mutableMapOf("nl" to erythromycine, "fr" to erythromycine)
            }
            quantity = KmehrQuantity(BigDecimal(300), Code("CD-UNIT", "ml"))
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "één - tot tweemaal per dag aanbrengen"
    }
}

fun betnelanCetomacrCremeCompoundMedication(): Medication {
    val cetomacr = "GEBUF. CETOMACR. CREME  TMF2"
    return Medication().apply {
        compoundPrescriptionV2 = CompoundPrescription.Compounds().apply {
            compounds.add(Compound().apply {
                medicinalProduct = Medicinalproduct().apply {
                    intendedcds = listOf(Code("CD-DRUG-CNK", "0103861"))
                    intendedname = "BETNELAN V CREME 1 X 30 G  0,1%"
                }
                quantity = KmehrQuantity(BigDecimal(30), Code("CD-UNIT", "gm"))
            })
            compounds.add(Compound().apply {
                substanceProduct = Substance().apply {
                    substance = Code(type = "CD-SUBSTANCE-CNK", code = "0587089").apply {
                        label = mutableMapOf("nl" to cetomacr, "fr" to cetomacr)
                    }
                }
                quantity = KmehrQuantity(BigDecimal(30), Code("CD-UNIT", "gm"))
            })
        }

        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        instructionForPatient = "appliquer 2 x par jour"
    }
}

fun aceclofenac10daysSubstanceVMPGROUP(): Medication {
    return aceclofenacSubstanceVMPGROUP().apply {
        substanceProduct!!.intendedname = "aceclofenac oraal 100 mg"
        duration = Duration().apply { value = 10.0; unit = Code("CD-TIMEUNIT", "d") }
        instructionForPatient = "1 tablet bij ontbijt, 1 tablet bij avondmaal gedurende 10 dagen"
    }
}

fun aceclofenacSubstanceVMPGROUP(): Medication {
    return Medication().apply {
        substanceProduct = Substanceproduct().apply {
            intendedcds = listOf(Code("CD-VMPGROUP", "0000174"))
            intendedname = "aceclofenac oraal 100 mg, 20 tabletten"
        }
        beginMoment = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1))
        temporality = Code("CD-TEMPORALITY", "chronic")
        instructionForPatient = "1 tablet bij ontbijt, 1 tablet bij avondmaal"
        regimen = mutableListOf(
            RegimenItem().apply {
                dayPeriod = Code("CD-DAYPERIOD", "duringbreakfast")
                administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                    quantity = 1.0
                    administrationUnit = Code("CD-ADMINISTRATIONUNIT", "00005")
                }
            },
            RegimenItem().apply {
                dayPeriod = Code("CD-DAYPERIOD", "duringdinner")
                administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                    quantity = 1.0
                    administrationUnit = Code("CD-ADMINISTRATIONUNIT", "00005")
                }
            }
                               )
        intakeRoute = Code("CD-DRUG-ROUTE", "00060")
    }
}

fun fredFlintstonePatient(): Patient {
    return Patient().apply {
        ssin = "76020727360"
        firstName = "Fred"
        lastName = "Flintstone"
        dateOfBirth = FuzzyValues.getFuzzyDate(LocalDateTime.of(1976, 2, 7, 0, 0)).toInt()
        gender = Gender.male
    }
}

fun donaldDuckHealthcareParty(): HealthcareParty {
    return HealthcareParty(
        nihii = "19006951001",
        firstName = "Donald",
        lastName = "Duck",
        addresses = mutableSetOf(
            Address(AddressType.hospital,
                    country = "be",
                    postalCode = "1000",
                    city = "Brussel",
                    street = "Grote Markt",
                    houseNumber = "7",
                    telecoms = mutableSetOf(Telecom(TelecomType.phone, "02/221.21.21"))
                   )))
}

