package org.taktik.freehealth.middleware.web.controllers

import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.common.Insurability
import org.taktik.freehealth.middleware.domain.common.InsuranceParameter
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.efact.EIDItem
import org.taktik.freehealth.middleware.dto.efact.EfactErrorMessage
import org.taktik.freehealth.middleware.dto.efact.EfactMessage
import org.taktik.freehealth.middleware.dto.efact.EfactSendResponse
import org.taktik.freehealth.middleware.dto.efact.Invoice
import org.taktik.freehealth.middleware.dto.efact.InvoiceItem
import org.taktik.freehealth.middleware.dto.efact.InvoiceSender
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import org.taktik.freehealth.middleware.dto.efact.InvoicingPrescriberCode
import org.taktik.freehealth.middleware.dto.efact.InvoicingSideCode
import org.taktik.freehealth.middleware.dto.efact.InvoicingTimeOfDay
import org.taktik.freehealth.middleware.dto.efact.InvoicingTreatmentReasonCode
import org.taktik.freehealth.middleware.dto.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatReader
import org.taktik.freehealth.utils.FuzzyValues
import java.io.File
import java.io.StringReader
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.HashMap
import java.util.UUID
import kotlin.math.roundToInt


@Suppress("PrivatePropertyName", "PropertyName")
abstract class EfactAbstractTest : EhealthTest() {
    // Add the scenario number to this to get a unique ID, then you can run all the tests without having to manually change the unique send number
    private var sendNumber = 100

    val ssinSender = "62110906574"
    val nihiiSender = 19234011004L
    val ibanSender = "BE38063122631172"
    val bicSender = "GKCCBEBB"
    val cbeSender = 999999922L //827098610L

    val firstNameSender = "Frédéric"
    val lastNameSender = "Dujardin"
    val phoneSender = "0479905510"

    private val DMG_NIHIIS_FOR_NISS2: MutableMap<String, String> = HashMap()
    private val NISS1 = "NISS1"
    private val NISS2 = "NISS2"
    private val NISS3 = "NISS3"
    private val NISS4 = "NISS4"

    protected val NISSES_BY_MUTUALITY: MutableMap<String, Map<String, String>> = HashMap()
    private val EMAILS_BY_MUTUALITY: MutableMap<String, String> = HashMap()
    private val DEFAULT_CODE_FOR_FED: MutableMap<String, String> = HashMap()

    private fun fillNisses(mutualityCode: String, niss1: String, niss2: String, niss3: String, niss4: String) {
        val map = HashMap<String, String>()
        map[NISS1] = niss1
        map[NISS2] = niss2
        map[NISS3] = niss3
        map[NISS4] = niss4
        NISSES_BY_MUTUALITY[mutualityCode] = map
    }

    protected val results = HashMap<String, MutableMap<String, Boolean>>()

    init {
        fillNisses("100", "36121015396", "86103130262", "14032816518", "91031030962")
        fillNisses("300", "64032764903", "66021250154", "74042566174", "71010908576")
        fillNisses("500", "49020508235", "83092618070", "76103012361", "62091405715")
        fillNisses("600", "59072957042", "83091041227", "49061015930", "82413101990")
        fillNisses("900", "59011214562", "73061527277", "90012333497", "78100404390")

        DMG_NIHIIS_FOR_NISS2["100"] = "17385467004"
        DMG_NIHIIS_FOR_NISS2["300"] = "10065828004"
        DMG_NIHIIS_FOR_NISS2["500"] = "16582446000"
        DMG_NIHIIS_FOR_NISS2["600"] = "14372133004"
        DMG_NIHIIS_FOR_NISS2["900"] = "15554246004"

        DEFAULT_CODE_FOR_FED["100"] = "101"
        DEFAULT_CODE_FOR_FED["300"] = "301"
        DEFAULT_CODE_FOR_FED["500"] = "511"
        DEFAULT_CODE_FOR_FED["600"] = "601"
        DEFAULT_CODE_FOR_FED["900"] = "910"

        EMAILS_BY_MUTUALITY["100"] = "mycarenet@mc.be"
        EMAILS_BY_MUTUALITY["300"] = "helpdesk.carenet@socmut.be"
        EMAILS_BY_MUTUALITY["500"] = "mycarenet@mloz.be"
        EMAILS_BY_MUTUALITY["600"] = "helpdesk.carenet@caami-hziv.fgov.be"
        EMAILS_BY_MUTUALITY["900"] = "900-carenet-mycarenet@hr-rail.be"

        results["100"] = HashMap()
        results["300"] = HashMap()
        results["500"] = HashMap()
        results["600"] = HashMap()
        results["900"] = HashMap()

        //Complete this matrix below with all tests that have been validated by the IOs
        results["100"]?.put("01", false); results["300"]?.put("01", false); results["500"]?.put("01", false); results["600"]?.put("01", false); results["900"]?.put("01", false)
        results["100"]?.put("02", false); results["300"]?.put("02", false); results["500"]?.put("02", false); results["600"]?.put("02", false); results["900"]?.put("02", false)
        results["100"]?.put("03", false); results["300"]?.put("03", false); results["500"]?.put("03", false); results["600"]?.put("03", false); results["900"]?.put("03", false)
        results["100"]?.put("04", false); results["300"]?.put("04", false); results["500"]?.put("04", false); results["600"]?.put("04", false); results["900"]?.put("04", false)
        results["100"]?.put("05", false); results["300"]?.put("05", false); results["500"]?.put("05", false); results["600"]?.put("05", false); results["900"]?.put("05", false)
        results["100"]?.put("06", false); results["300"]?.put("06", false); results["500"]?.put("06", false); results["600"]?.put("06", false); results["900"]?.put("06", false)
        results["100"]?.put("07", false); results["300"]?.put("07", false); results["500"]?.put("07", false); results["600"]?.put("07", false); results["900"]?.put("07", false)
        results["100"]?.put("08", false); results["300"]?.put("08", false); results["500"]?.put("08", false); results["600"]?.put("08", false); results["900"]?.put("08", false)
        results["100"]?.put("09", false); results["300"]?.put("09", false); results["500"]?.put("09", false); results["600"]?.put("09", false); results["900"]?.put("09", false)
        results["100"]?.put("10", false); results["300"]?.put("10", false); results["500"]?.put("10", false); results["600"]?.put("10", false); results["900"]?.put("10", false)
        results["100"]?.put("11", false); results["300"]?.put("11", false); results["500"]?.put("11", false); results["600"]?.put("11", false); results["900"]?.put("11", false)
        results["100"]?.put("12", false); results["300"]?.put("12", false); results["500"]?.put("12", false); results["600"]?.put("12", false); results["900"]?.put("12", false)
        results["100"]?.put("13", false); results["300"]?.put("13", false); results["500"]?.put("13", false); results["600"]?.put("13", false); results["900"]?.put("13", false)
        results["100"]?.put("14", false); results["300"]?.put("14", false); results["500"]?.put("14", false); results["600"]?.put("14", false); results["900"]?.put("14", false)
        results["100"]?.put("15", false); results["300"]?.put("15", false); results["500"]?.put("15", false); results["600"]?.put("15", false); results["900"]?.put("15", false)
        results["100"]?.put("16", false); results["300"]?.put("16", false); results["500"]?.put("16", false); results["600"]?.put("16", false); results["900"]?.put("16", false)
        results["100"]?.put("17", false); results["300"]?.put("17", false); results["500"]?.put("17", false); results["600"]?.put("17", false); results["900"]?.put("17", false)
        results["100"]?.put("18", false); results["300"]?.put("18", false); results["500"]?.put("18", false); results["600"]?.put("18", false); results["900"]?.put("18", false)
    }

    private fun createBatch(invoiceNumber: Long,
                            batchRef: String,
                            oa: String,
                            patientWithInss: org.taktik.freehealth.middleware.domain.common.Patient,
                            uniq: Int): InvoicesBatch =
        InvoicesBatch().apply {

            invoicingYear = 2018
            invoicingMonth = 9
            this.batchRef = batchRef
            uniqueSendNumber = uniq.toLong()
            ioFederationCode = oa
            numericalRef = ((LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toLong())*1000000) + (ioFederationCode!!.toLong() * 1000) + invoiceNumber
            sender = InvoiceSender().apply {
                nihii = nihii1?.toLong() //nihiiSender
                ssin = ssin1 //ssinSender
                bce = cbe1?.toLong()
                bic = bicSender
                iban = ibanSender
                firstName = firstName1
                lastName = lastName1
                phoneNumber = phoneSender.toLong()
            }

            invoices.add(Invoice().apply {
                patient = patientWithInss
                this.invoiceNumber = invoiceNumber
                this.invoiceRef = batchRef
                this.ioCode = patientWithInss.insurabilities.firstOrNull()?.insuranceCode ?: oa
                reason = InvoicingTreatmentReasonCode.Other
            })
        }

    private fun createInvoiceItem(codeNomenclature: Long,
                                  reimbursedAmount: Int,
                                  patientFee: Int,
                                  doctorSupplement: Int,
                                  contract: String?,
                                  date: Date): InvoiceItem {
        val invoiceItem = InvoiceItem()

        invoiceItem.insuranceRef = contract
        invoiceItem.insuranceRefDate = contract?.let { date.time }

        invoiceItem.reimbursedAmount = reimbursedAmount.toLong()
        invoiceItem.dateCode = date.time
        invoiceItem.codeNomenclature = codeNomenclature
        invoiceItem.doctorIdentificationNumber = nihii1
        invoiceItem.patientFee = patientFee.toLong()
        invoiceItem.doctorSupplement = doctorSupplement.toLong()
        invoiceItem.prescriberNorm = InvoicingPrescriberCode.None
        invoiceItem.personalInterventionCoveredByThirdPartyCode = 0
        invoiceItem.sideCode = InvoicingSideCode.None
        invoiceItem.override3rdPayerCode = 0
        invoiceItem.timeOfDay = InvoicingTimeOfDay.Other
        invoiceItem.units = 1

        return invoiceItem
    }

    private fun consultTarif(restTemplate: TestRestTemplate,
                             port: Int,
                             niss: String,
                             keystoreId: String,
                             tokenId: String,
                             passPhrase: String,
                             justification: Int?,
                             codes: List<String>,
                             nihiiGmdOwner: String? = null): TarificationConsultationResult =
        restTemplate.exchange("http://localhost:$port/tarif/$niss?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&date=${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))}" + (justification?.let { "&justification=$justification" }
            ?: "") + (nihiiGmdOwner?.let { "&gmdNihii=$nihiiGmdOwner" }
            ?: ""), HttpMethod.POST, HttpEntity(codes, createHeaders(null, null, UUID.fromString(keystoreId), tokenId, passPhrase)), TarificationConsultationResult::class.java, firstName1, lastName1).body.apply {
            if (this.codes.size == 0) {
                throw IllegalStateException("No code was returned")
            }
        }

    private fun getPatient(restTemplate: TestRestTemplate,
                           port: Int,
                           niss: String,
                           keystoreId: String,
                           tokenId: String,
                           passPhrase: String) =
        restTemplate.exchange("http://localhost:$port/genins/$niss?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, UUID.fromString(keystoreId), tokenId, passPhrase)), InsurabilityInfoDto::class.java)?.body?.let {
            Patient().apply {
                lastName = it.lastName
                firstName = it.firstName
                ssin = it.inss
                gender = it.sex?.let { Gender.fromCode(it) }
                dateOfBirth = it.dateOfBirth?.toInt()
                insurabilities = it.insurabilities.map {
                    Insurability().apply {
                        insuranceCode = it.mutuality
                        it.ct1?.let { parameters[InsuranceParameter.tc1] = it }
                        it.ct2?.let { parameters[InsuranceParameter.tc2] = it }
                    }
                }
            }
        }

    //Un patient non affilie a l'OA (NISS appartenant a un autre OA)
    fun prepareScenario01(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                          keystoreId: String,
                          tokenId: String,
                          passPhrase: String): InvoicesBatch? {
        val niss = if (mutualityCode == "300") "59011214562" else "64032764903"
        println("***** Scenario 1 - Mutuality $mutualityCode - NISS: $niss from another mutuality *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 1, "FHCA01.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 1).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), Date()))
        }
    }

    //Un code nomenclature existant mais non permis pour un medecin generaliste (NISS 1 - 102793
    fun prepareScenario02NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 2 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        return createBatch(mutualityCode.toLong() * 100 + 2, "FHCA01.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 2).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(102793, 1920, 200, 0, null, Date()))
        }
    }

    //Un code nomenclature inexistant (NISS 1 - 101075)
    fun prepareScenario03NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 3 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        return createBatch(mutualityCode.toLong() * 100 + 3, "FHCA02.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 3).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101075L, 1920, 200, 0, null, Date()))
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour -2 ans) sans levee de prescription
    fun prepareScenario04NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 4 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        return createBatch(mutualityCode.toLong() * 100 + 4, "FHCA03.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 4).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(101032, 1920, 200, 0, null, DateTime().minusYears(2).toDate()))
        }
    }

    //Une prestation dans le futur (NISS 1 - date prestation > date du jour + 2 mois)
    fun prepareScenario05NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 5 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 5, "FHCA04.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 5).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()))
        }
    }

    //Une prestation non permise pour un patient affilie dans une maison medicale (NISS 3)
    fun prepareScenario06NISS3(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS3]!!
        println("***** Scenario 6 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        return createBatch(mutualityCode.toLong() * 100 + 6, "FHCA05.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 6).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101032, 1920, 200, 0, null, DateTime().toDate()))
        }
    }

    //Une prestation pour un patient non en regle (NISS 4)
    fun prepareScenario07NISS4(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS4]!!
        println("***** Scenario 7 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        return createBatch(mutualityCode.toLong() * 100 + 7, "FHCA06.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 7).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101032, 1920, 200, 0, null, DateTime().toDate()))
        }
    }

    //Prestation dont le tarif a ete obtenu via le scenario 9 de la consultation des tarifs (NISS 2 - tiers payant non autorise sans justification particuliere)
    fun prepareScenario08NISS2(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 8 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 8, "FHCA07.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 8).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().toDate()))
        }
    }

    //Prestation correspondant au scenario 10 de la consultation des tarifs, sans indiquer le numero d'engagement de paiement, montants differents.
    fun prepareScenario09NISS2(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 9 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        return createBatch(mutualityCode.toLong() * 100 + 9, "FHCA08.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 9).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101032, 1820, 200, 0, null, DateTime().toDate()))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs.
    fun prepareScenario10NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 10 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 10, "FHCA09.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 10).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().toDate()))
        }
    }

    //Prestations pour lesquelles un engagement de paiement a ete obtenu via le scenario 8 de la consultation des tarifs.
    fun prepareScenario11NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 11 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032", "475075"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032", "475075"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 10.00 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 11, "FHCA10.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 11).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codes[0]),
                    ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    0, consult.financialContracts.firstOrNull(), DateTime().toDate()),
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codes[1]),
                    ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                    relatedCode = 767071L
                }))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 10 de la consultation des tarifs.
    fun prepareScenario12NISS2(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 12 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, 2, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 12, "FHCA11.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 12).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().toDate()))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 11 de la consultation des tarifs.
    fun prepareScenario13NISS2(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 13 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, 7, listOf("101032"), DMG_NIHIIS_FOR_NISS2[mutualityCode])
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 13, "FHCA12.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 13).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().toDate()))
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    fun prepareScenario14NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 14 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 14, "FHCA13.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 14).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                insuranceRef = null
            })
        }
    }

    //Prestation correspondant au scenario 8 de la consultation des tarifs, sans indiquer le numero d'EP, meme montants
    fun prepareScenario15NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 15 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032", "475075"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032", "475075"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 10.00 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 15, "FHCA14.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 15).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codes[0]),
                    ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                    insuranceRef = null
                },
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[1]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                                  0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                    insuranceRef = null
                    relatedCode = 767071L
                }))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs avec la lecture du document d'identite electronique (record 52).
    fun prepareScenario16NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 16 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val cal = Calendar.getInstance()

        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 16, "FHCA15.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 16).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                eidItem = EIDItem().apply {
                    readHour = cal.get(Calendar.HOUR_OF_DAY) * 100 + cal.get(Calendar.MINUTE)
                }
            })
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour - 2 ans) avec levee de prescription
    fun prepareScenario17NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 17 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("101032"))
                reimbursements.add(TarificationConsultationResult.Payment().apply { amount = 19.20 })
                patientFees.add(TarificationConsultationResult.Payment().apply { amount = 2.00 })
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 17, "FHCA16.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 17).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codes[0]),
                ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                0, consult.financialContracts.firstOrNull(), DateTime().plusYears(-2).toDate()).apply {
                insuranceRef = null
            })
        }
    }

    //Deux prestations dont une est une prestation relative
    fun prepareScenario18NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 18 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId, tokenId, passPhrase, null, listOf("475075", "101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                codes.addAll(listOf("475075", "101032"))
            }
        }
        return createBatch(mutualityCode.toLong() * 100 + 18, "FHCA17.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 18).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codes[0]),
                    ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                    relatedCode = 767071L
                },
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codes[1]),
                    ((consult.reimbursements.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    ((consult.patientFees.firstOrNull()?.amount ?: .0) * 100).roundToInt(),
                    0, consult.financialContracts.firstOrNull(), DateTime().toDate()).apply {
                    relatedCode = 475075L
                }))
        }
    }
}

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EfactControllerTest : EfactAbstractTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null


    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    private fun alreadyDone(mutualityCode: String, scenario: String): Boolean {
        val status = results[mutualityCode]?.get(scenario)
        return status ?: false
    }

    //Un patient non affilie a l'OA (NISS appartenant a un autre OA)
    @Test
    fun testScenario01() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "01")) continue
            val invBatch = prepareScenario01(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Un code nomenclature existant mais non permis pour un medecin generaliste (NISS 1 - 102793
    @Test
    fun testScenario02NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "02")) continue
            val invBatch = prepareScenario02NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Un code nomenclature inexistant (NISS 1 - 101075)
    @Test
    fun testScenario03NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "03")) continue
            val invBatch = prepareScenario03NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour -2 ans) sans levee de prescription
    @Test
    fun testScenario04NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "04")) continue
            val invBatch = prepareScenario04NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Une prestation dans le futur (NISS 1 - date prestation > date du jour + 2 mois)
    @Test
    fun testScenario05NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "05")) continue
            val invBatch = prepareScenario05NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Une prestation non permise pour un patient affilie dans une maison medicale (NISS 3)
    @Test
    fun testScenario06NISS3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "06")) continue
            val invBatch = prepareScenario06NISS3(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Une prestation pour un patient non en regle (NISS 4)
    @Test
    fun testScenario07NISS4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "07")) continue
            val invBatch = prepareScenario07NISS4(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation dont le tarif a ete obtenu via le scenario 9 de la consultation des tarifs (NISS 2 - tiers payant non autorise sans justification particuliere)
    @Test
    fun testScenario08NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "08")) continue
            val invBatch = prepareScenario08NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation correspondant au scenario 10 de la consultation des tarifs, sans indiquer le numero d'engagement de paiement, montants differents.
    @Test
    fun testScenario09NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "09")) continue
            val invBatch = prepareScenario09NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs.
    @Test
    fun testScenario10NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "10")) continue
            val invBatch = prepareScenario10NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestations pour lesquelles un engagement de paiement a ete obtenu via le scenario 8 de la consultation des tarifs.
    @Test
    fun testScenario11NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "11")) continue
            val invBatch = prepareScenario11NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 10 de la consultation des tarifs.
    @Test
    fun testScenario12NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "12")) continue
            val invBatch = prepareScenario12NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 11 de la consultation des tarifs.
    @Test
    fun testScenario13NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "13")) continue
            val invBatch = prepareScenario13NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    @Test
    fun testScenario14NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "14")) continue
            val invBatch = prepareScenario14NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation correspondant au scenario 8 de la consultation des tarifs, sans indiquer le numero d'EP, meme montants
    @Test
    fun testScenario15NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "15")) continue
            val invBatch = prepareScenario15NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs avec la lecture du document d'identite electronique (record 52).
    @Test
    fun testScenario16NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "16")) continue
            val invBatch = prepareScenario16NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour - 2 ans) avec levee de prescription
    @Test
    fun testScenario17NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "17")) continue
            val invBatch = prepareScenario17NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

    //Deux prestations dont une est une prestation relative
    @Test
    fun testScenario18NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "18")) continue
            val invBatch = prepareScenario18NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                }
        }
    }

}


@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EfactFlatFileControllerTest : EfactAbstractTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null


    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    //Un patient non affilie a l'OA (NISS appartenant a un autre OA)
    @Test
    fun testFlat01() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario01(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.01"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Un code nomenclature existant mais non permis pour un medecin generaliste (NISS 1 - 102793
    @Test
    fun testFlat02NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario02NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.02"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Un code nomenclature inexistant (NISS 1 - 101075)
    @Test
    fun testFlat03NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario03NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.03"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour -2 ans) sans levee de prescription
    @Test
    fun testFlat04NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario04NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.04"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation dans le futur (NISS 1 - date prestation > date du jour + 2 mois)
    @Test
    fun testFlat05NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario05NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.05"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation non permise pour un patient affilie dans une maison medicale (NISS 3)
    @Test
    fun testFlat06NISS3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario06NISS3(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.06"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation pour un patient non en regle (NISS 4)
    @Test
    fun testFlat07NISS4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario07NISS4(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.07"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation dont le tarif a ete obtenu via le scenario 9 de la consultation des tarifs (NISS 2 - tiers payant non autorise sans justification particuliere)
    @Test
    fun testFlat08NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario08NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.08"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation correspondant au scenario 10 de la consultation des tarifs, sans indiquer le numero d'engagement de paiement, montants differents.
    @Test
    fun testFlat09NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario09NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.09"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs.
    @Test
    fun testFlat10NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario10NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.10"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestations pour lesquelles un engagement de paiement a ete obtenu via le scenario 8 de la consultation des tarifs.
    @Test
    fun testFlat11NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario11NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.11"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 10 de la consultation des tarifs.
    @Test
    fun testFlat12NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario12NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.12"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 11 de la consultation des tarifs.
    @Test
    fun testFlat13NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario13NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.13"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    @Test
    fun testFlat14NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario14NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.14"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation correspondant au scenario 8 de la consultation des tarifs, sans indiquer le numero d'EP, meme montants
    @Test
    fun testFlat15NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario15NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.15"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs avec la lecture du document d'identite electronique (record 52).
    @Test
    fun testFlat16NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario16NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.16"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour - 2 ans) avec levee de prescription
    @Test
    fun testFlat17NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario17NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.17"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Deux prestations dont une est une prestation relative
    @Test
    fun testFlat18NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario18NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "FHC.ACC.18"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    private fun writeFiles(fileName: String, mutualityCode: String, raw: String) {
        File("$fileName.$mutualityCode.txt").bufferedWriter().let {
            it.write(raw)
            it.flush()
            it.close()
        }
        File("$fileName.$mutualityCode.yml").bufferedWriter().let {
            it.write(BelgianInsuranceInvoicingFormatReader("fr").parse(StringReader(raw))!!.map { it.toString() }.joinToString("\n"))
            it.flush()
            it.close()
        }
    }
}

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EfactMessagesLoadTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null


    @Before
    fun setUp() {
        try {
            System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        } catch (e: NullPointerException) {
            System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
        }
    }

    @Test
    fun testLoadMessage() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        this.restTemplate.exchange("http://localhost:$port/efact/$nihii1/fr?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), object : ParameterizedTypeReference<List<EfactMessage>>() {}, firstName1, lastName1)
            ?.let {
                it.body.forEach {

                }
            }
    }

    @Test
    fun testLoadAndConfirmMessage() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        this.restTemplate.exchange("http://localhost:$port/efact/$nihii1/fr?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), object : ParameterizedTypeReference<List<EfactErrorMessage>>() {}, firstName1, lastName1)
            ?.let {
                this.restTemplate.exchange("http://localhost:$port/efact/confirm/acks/$nihii1?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.PUT, HttpEntity<List<String>>(it.body.filter { it.tAck != null }.map { it.hashValue }.filterNotNull(), createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
                this.restTemplate.exchange("http://localhost:$port/efact/confirm/msgs/$nihii1?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.PUT, HttpEntity<List<String>>(it.body.filter { it.tAck == null }.map { it.hashValue }.filterNotNull(), createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
            }
    }
}

