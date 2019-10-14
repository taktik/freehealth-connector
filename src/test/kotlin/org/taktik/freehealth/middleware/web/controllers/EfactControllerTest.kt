package org.taktik.freehealth.middleware.web.controllers

import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
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
import org.taktik.freehealth.middleware.dto.efact.*
import org.taktik.freehealth.middleware.dto.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatReader
import org.taktik.freehealth.utils.FuzzyValues
import java.io.File
import java.io.FileReader
import java.io.FileWriter
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
    private var sendNumber = 805

    val phoneSender = "0479905510"
    val bicSender = ""
    val ibanSender = ""

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
        results["100"]?.put("01", false); results["300"]?.put("01", false); results["500"]?.put("01", false); results["600"]?.put("01", false); results["900"]?.put("01", true)
        results["100"]?.put("02", false); results["300"]?.put("02", false); results["500"]?.put("02", false); results["600"]?.put("02", false); results["900"]?.put("02", false)
        results["100"]?.put("03", false); results["300"]?.put("03", false); results["500"]?.put("03", false); results["600"]?.put("03", false); results["900"]?.put("03", false)
        results["100"]?.put("04", false); results["300"]?.put("04", false); results["500"]?.put("04", false); results["600"]?.put("04", false); results["900"]?.put("04", false)
        results["100"]?.put("05", false); results["300"]?.put("05", false); results["500"]?.put("05", false); results["600"]?.put("05", false); results["900"]?.put("05", false)
        results["100"]?.put("06", false); results["300"]?.put("06", false); results["500"]?.put("06", false); results["600"]?.put("06", false); results["900"]?.put("06", false)
        results["100"]?.put("07", false); results["300"]?.put("07", false); results["500"]?.put("07", false); results["600"]?.put("07", false); results["900"]?.put("07", true)
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
            this.fileRef = batchRef + "fr"
            uniqueSendNumber = uniq.toLong()
            ioFederationCode = oa
            numericalRef = ((LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toLong()) * 1_000_000) + (ioFederationCode!!.toLong() * 1_000) + uniq
            sender = InvoiceSender().apply {
                nihii = nihii1?.toLong() //nihiiSender
                ssin = ssin1 //ssinSender
                bce = cbe1?.toLong()
                bic = BIC1
                iban = IBAN1
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

    private fun createBatchFlatRate(invoiceNumber: Long,
                            batchRef: String,
                            oa: String,
                            patientWithInss: org.taktik.freehealth.middleware.domain.common.Patient,
                            uniq: Int): InvoicesBatch =
        InvoicesBatch().apply {

            invoiceContent = 0
            magneticInvoice = true
            invoicingYear = 2018
            invoicingMonth = 9
            this.batchRef = batchRef
            this.fileRef = batchRef + "fr"
            uniqueSendNumber = uniq.toLong()
            ioFederationCode = oa
            numericalRef = ((LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toLong()) * 1_000_000) + (ioFederationCode!!.toLong() * 1_000) + uniq
            sender = InvoiceSender().apply {
                nihii = "12345678111".toLong() //nihiiSender
                //ssin = ssin1 //ssinSender
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
                startOfCoveragePeriod = 20160601
            })
        }

    private fun createGuardPostBatch(invoiceNumber: Long,
                            batchRef: String,
                            oa: String,
                            patientWithInss: org.taktik.freehealth.middleware.domain.common.Patient,
                            uniq: Int): InvoicesBatch =
        InvoicesBatch().apply {

            invoicingYear = 2018
            invoicingMonth = 9
            this.batchRef = batchRef
            fileRef = batchRef // FIXME this should be related to invoice
            uniqueSendNumber = uniq.toLong()
            ioFederationCode = oa
            numericalRef = ((LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toLong()) * 1_000_000) + (ioFederationCode!!.toLong() * 1_000) + uniq
            sender = InvoiceSender().apply {
                nihii = nihii4?.toLong() //nihiiSender
                ssin = ssin4 //ssinSender
                bce = cbe4?.toLong()
                bic = BIC4
                iban = IBAN4
                firstName = firstName1 // TODO
                lastName = lastName1 // TODO
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
                                  override3rdPayerCode: Int,
                                  reimbursedAmount: Int,
                                  patientFee: Int,
                                  doctorSupplement: Int,
                                  contract: String?,
                                  date: Date,
                                  endDate: Date? = null,
                                  mmNihii: String? = null): InvoiceItem {
        val invoiceItem = InvoiceItem()

        invoiceItem.insuranceRef = contract
        invoiceItem.insuranceRefDate = contract?.let { date.time }
        invoiceItem.reimbursedAmount = reimbursedAmount.toLong()
        invoiceItem.dateCode = date.time
        invoiceItem.endDateCode = if(endDate != null) endDate!!.time else null
        invoiceItem.codeNomenclature = codeNomenclature
        invoiceItem.doctorIdentificationNumber = if (mmNihii == null) nihii1 else mmNihii!!
        invoiceItem.patientFee = patientFee.toLong()
        invoiceItem.doctorSupplement = doctorSupplement.toLong()
        invoiceItem.prescriberNorm = InvoicingPrescriberCode.None
        invoiceItem.personalInterventionCoveredByThirdPartyCode = 0
        invoiceItem.transplantationCode = InvoicingTransplantationCode.None
        invoiceItem.sideCode = InvoicingSideCode.None
        invoiceItem.override3rdPayerCode = override3rdPayerCode
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
            if (this.codeResults.size == 0) {
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

    private fun getGuardPostPatient(restTemplate: TestRestTemplate,
                           port: Int,
                           niss: String,
                           keystoreId: UUID,
                           tokenId: String,
                           passPhrase: String): Patient? =
        restTemplate.exchange("http://localhost:$port/genins/$niss?hcpNihii=$nihii4&hcpSsin=$ssin4&hcpName=$name4&hcpQuality=${"guardpost"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), InsurabilityInfoDto::class.java)?.body?.let {
            Assertions.assertThat(it.faultSource).isNull()
            Assertions.assertThat(it.faultMessage).isNull()
            Assertions.assertThat(it.faultCode).isNull()
            Assertions.assertThat(it.mycarenetConversation).isNotNull
            Assertions.assertThat(it.commonOutput).isNotNull

            Patient().apply {
                lastName = it.lastName
                firstName = it.firstName
                ssin = it.inss
                gender = it.sex?.let { Gender.fromCode(it) }
                dateOfBirth = it.dateOfBirth?.toInt()
                insurabilities = it.insurabilities.map {
                    Insurability().apply {
                        Assertions.assertThat(it.mutuality).isNotNull()

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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 1, "FHCA01.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 1).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, Date()))
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
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 2, "FHCA01.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 2).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(102793, 0,1920, 200, 0, null, Date()))
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

        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 3, "FHCA02.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 3).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101075L, 0, 1920, 200, 0, null, Date()))
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
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 4, "FHCA03.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 4).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(101032, 0, 1920, 200, 0, null, DateTime().minusYears(2).toDate()))
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 5, "FHCA04.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 5).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults.first().justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().plusMonths(2).toDate()))
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

        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 6, "FHCA05.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 6).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101032,0,  1920, 200, 0, null, DateTime().toDate()))
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
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 7, "FHCA06.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 7).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101032,0,  1920, 200, 0, null, DateTime().toDate()))
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 8, "FHCA07.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 8).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults.first().justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()))
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

        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 9, "FHCA08.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 9).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101032, 0, 1820, 200, 0, null, DateTime().toDate()))
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 10, "FHCA09.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 10).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults.first().justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.first().contract,
                DateTime().toDate()))
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
                val code101032 = TarificationConsultationResult.CodeResult().apply { code = "101032"; reimbursement = TarificationConsultationResult.Payment().apply { amount= 19.20 }; patientFee = TarificationConsultationResult.Payment().apply {  amount = 2.00 } }
                val code475075 = TarificationConsultationResult.CodeResult().apply { code = "475075"; reimbursement = TarificationConsultationResult.Payment().apply { amount= 10.00 }; patientFee = TarificationConsultationResult.Payment().apply {  amount = 2.00 } }
                codeResults.addAll(listOf(code101032, code475075))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 11, "FHCA10.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 11).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codeResults[0].code),
                    consult.codeResults[0].justification,
                    ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                    ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                    0,
                    consult.codeResults.firstOrNull()?.contract,
                    DateTime().toDate()),
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codeResults[1].code),
                    consult.codeResults[1].justification,
                    ((consult.codeResults[1].reimbursement?.amount ?: .0) * 100).roundToInt(),
                    ((consult.codeResults[1].patientFee?.amount ?: .0) * 100).roundToInt(),
                    0,
                    consult.codeResults[1].contract,
                    DateTime().toDate()).apply {
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 12, "FHCA11.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 12).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()))
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 13, "FHCA12.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 13).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()))
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 14, "FHCA13.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 14).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()).apply {
                insuranceRef = null
            })
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    fun prepareGuardPostScenario14NISS1(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: UUID,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 14 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getGuardPostPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        Assertions.assertThat(patientWithInss).isNotNull()
        Assertions.assertThat(patientWithInss.insurabilities).isNotNull.isNotEmpty

        val consult = try {
            consultTarif(restTemplate, port, niss, keystoreId!!.toString(), tokenId, passPhrase, null, listOf("101032"))
        } catch (e: Exception) {
            TarificationConsultationResult().apply {
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createGuardPostBatch(mutualityCode.toLong() * 1000 + sendNumber + 14, "FHCA13.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 14).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()).apply {
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
                val code101032 = TarificationConsultationResult.CodeResult().apply { code = "101032"; reimbursement = TarificationConsultationResult.Payment().apply { amount= 19.20 }; patientFee = TarificationConsultationResult.Payment().apply {  amount = 2.00 } }
                val code475075 = TarificationConsultationResult.CodeResult().apply { code = "475075"; reimbursement = TarificationConsultationResult.Payment().apply { amount= 10.00 }; patientFee = TarificationConsultationResult.Payment().apply {  amount = 2.00 } }
                codeResults.addAll(listOf(code101032, code475075))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 15, "FHCA14.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 15).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codeResults[0].code),
                    consult.codeResults[0].justification,
                    ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                    ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                    0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()).apply {
                    insuranceRef = null
                },
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codeResults[1].code),
                    consult.codeResults[1].justification,
                    ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                    ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                    0, consult.codeResults.firstOrNull()?.contract,
                    DateTime().toDate()).apply {
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 16, "FHCA15.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 16).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()).apply {
                eidItem = EIDItem().apply {
                    readHour = cal.get(Calendar.HOUR_OF_DAY) * 100 + cal.get(Calendar.MINUTE)
                    //readDate = DateTime().toDate()
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
                val newCodeResult = TarificationConsultationResult.CodeResult()
                newCodeResult.code = "101032"
                newCodeResult.reimbursement = TarificationConsultationResult.Payment().apply { amount = 19.20; currencyUnit = "EUR" }
                newCodeResult.patientFee = TarificationConsultationResult.Payment().apply { amount = 2.00; currencyUnit = "EUR" }
                codeResults.addAll(listOf(newCodeResult))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 17, "FHCA16.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 17).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf(consult.codeResults[0].code),
                consult.codeResults[0].justification,
                ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                0, consult.codeResults.firstOrNull()?.contract, DateTime().plusYears(-2).toDate()).apply {
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
                val code101032 = TarificationConsultationResult.CodeResult().apply { code = "101032"; reimbursement = TarificationConsultationResult.Payment().apply { amount= 19.20 }; patientFee = TarificationConsultationResult.Payment().apply {  amount = 2.00 } }
                val code475075 = TarificationConsultationResult.CodeResult().apply { code = "475075"; reimbursement = TarificationConsultationResult.Payment().apply { amount= 10.00 }; patientFee = TarificationConsultationResult.Payment().apply {  amount = 2.00 } }
                codeResults.addAll(listOf(code475075, code101032))
            }
        }
        return createBatch(mutualityCode.toLong() * 1000 + sendNumber + 18, "FHCA17.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 18).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codeResults[0].code),
                    consult.codeResults[0].justification,
                    ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                    ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                    0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()).apply {
                    relatedCode = 767071L
                },
                createInvoiceItem(
                    java.lang.Long.valueOf(consult.codeResults[1].code),
                    consult.codeResults[1].justification,
                    ((consult.codeResults.firstOrNull()?.reimbursement?.amount ?: .0) * 100).roundToInt(),
                    ((consult.codeResults.firstOrNull()?.patientFee?.amount ?: .0) * 100).roundToInt(),
                    0, consult.codeResults.firstOrNull()?.contract, DateTime().toDate()).apply {
                    relatedCode = 475075L
                }))
        }
    }

    //Flatrate MH.
    fun prepareScenarioFlatRate(restTemplate: TestRestTemplate, port: Int, mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 10 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(restTemplate, port, niss, keystoreId, tokenId, passPhrase) ?: return null

        val lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)

        return createBatchFlatRate(mutualityCode.toLong() * 1000 + sendNumber + 19, "FHCAFF.$mutualityCode", mutualityCode, patientWithInss, sendNumber + 19).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(
                java.lang.Long.valueOf("109616"),
                0,
                ((15.10) * 100).roundToInt(),
                ((.0) * 100).roundToInt(),
                0, null,
                DateTime().withDayOfMonth(1).toDate(),
                DateTime().withDayOfMonth(lastDay).toDate(),
                "12345678111"
                ))
        }
    }

    protected fun writeFiles(fileName: String, mutualityCode: String, raw: String) {
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
            val invBatch = prepareScenario01(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.01"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Un code nomenclature existant mais non permis pour un medecin generaliste (NISS 1 - 102793
    @Test
    fun testScenario02NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "02")) continue
            val invBatch = prepareScenario02NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.02"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Un code nomenclature inexistant (NISS 1 - 101075)
    @Test
    fun testScenario03NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "03")) continue
            val invBatch = prepareScenario03NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.03"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour -2 ans) sans levee de prescription
    @Test
    fun testScenario04NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "04")) continue
            val invBatch = prepareScenario04NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.04"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Une prestation dans le futur (NISS 1 - date prestation > date du jour + 2 mois)
    @Test
    fun testScenario05NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "05")) continue
            val invBatch = prepareScenario05NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.05"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Une prestation non permise pour un patient affilie dans une maison medicale (NISS 3)
    @Test
    fun testScenario06NISS3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "06")) continue
            val invBatch = prepareScenario06NISS3(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.06"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Une prestation pour un patient non en regle (NISS 4)
    @Test
    fun testScenario07NISS4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "07")) continue
            val invBatch = prepareScenario07NISS4(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.07"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation dont le tarif a ete obtenu via le scenario 9 de la consultation des tarifs (NISS 2 - tiers payant non autorise sans justification particuliere)
    @Test
    fun testScenario08NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "08")) continue
            val invBatch = prepareScenario08NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.08"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation correspondant au scenario 10 de la consultation des tarifs, sans indiquer le numero d'engagement de paiement, montants differents.
    @Test
    fun testScenario09NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "09")) continue
            val invBatch = prepareScenario09NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.09"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs.
    @Test
    fun testScenario10NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "10")) continue
            val invBatch = prepareScenario10NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.10"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestations pour lesquelles un engagement de paiement a ete obtenu via le scenario 8 de la consultation des tarifs.
    @Test
    fun testScenario11NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "11")) continue
            val invBatch = prepareScenario11NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.11"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 10 de la consultation des tarifs.
    @Test
    fun testScenario12NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "12")) continue
            val invBatch = prepareScenario12NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.12"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 11 de la consultation des tarifs.
    @Test
    fun testScenario13NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "13")) continue
            val invBatch = prepareScenario13NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.13"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    @Test
    fun testScenario14NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "14")) continue
            val invBatch = prepareScenario14NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.14"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation correspondant au scenario 8 de la consultation des tarifs, sans indiquer le numero d'EP, meme montants
    @Test
    fun testScenario15NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "15")) continue
            val invBatch = prepareScenario15NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.15"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs avec la lecture du document d'identite electronique (record 52).
    @Test
    fun testScenario16NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "16")) continue
            val invBatch = prepareScenario16NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.16"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour - 2 ans) avec levee de prescription
    @Test
    fun testScenario17NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "17")) continue
            val invBatch = prepareScenario17NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.17"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    //Deux prestations dont une est une prestation relative
    @Test
    fun testScenario18NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "18")) continue
            val invBatch = prepareScenario18NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.18"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
                }
        }
    }

    @Test
    fun testGuardPostScenario14() {
        val (keystoreId, tokenId, passPhrase) = registerGuardPost(restTemplate!!, port, nihii4!!, password4!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareGuardPostScenario14NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!, tokenId, passPhrase)
                ?: continue
            this.restTemplate.exchange("http://localhost:$port/efact/batch?isGuardPost=true", HttpMethod.POST, HttpEntity<InvoicesBatch>(invBatch, createHeaders(null, null, keystoreId, tokenId, passPhrase)), EfactSendResponse::class.java)
                ?.let {
                    assert(it.body.success ?: false)
                    val fileName = "FHC.ACC.GUARDPOST.14"
                    it.body.detail?.let { writeFiles(fileName, mutualityCode, it) }
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

    private val filePrefix: String = "medispring-acc"


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
            val invBatch = prepareScenario01(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.01"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Un code nomenclature existant mais non permis pour un medecin generaliste (NISS 1 - 102793
    @Test
    fun testFlat02NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario02NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.02"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Un code nomenclature inexistant (NISS 1 - 101075)
    @Test
    fun testFlat03NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario03NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.03"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour -2 ans) sans levee de prescription
    @Test
    fun testFlat04NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario04NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.04"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation dans le futur (NISS 1 - date prestation > date du jour + 2 mois)
    @Test
    fun testFlat05NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario05NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.05"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation non permise pour un patient affilie dans une maison medicale (NISS 3)
    @Test
    fun testFlat06NISS3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario06NISS3(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.06"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation pour un patient non en regle (NISS 4)
    @Test
    fun testFlat07NISS4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario07NISS4(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.07"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation dont le tarif a ete obtenu via le scenario 9 de la consultation des tarifs (NISS 2 - tiers payant non autorise sans justification particuliere)
    @Test
    fun testFlat08NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario08NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.08"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation correspondant au scenario 10 de la consultation des tarifs, sans indiquer le numero d'engagement de paiement, montants differents.
    @Test
    fun testFlat09NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario09NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.09"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs.
    @Test
    fun testFlat10NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario10NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.10"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestations pour lesquelles un engagement de paiement a ete obtenu via le scenario 8 de la consultation des tarifs.
    @Test
    fun testFlat11NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario11NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.11"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 10 de la consultation des tarifs.
    @Test
    fun testFlat12NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario12NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.12"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 11 de la consultation des tarifs.
    @Test
    fun testFlat13NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario13NISS2(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.13"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    @Test
    fun testFlat14NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario14NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.14"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation correspondant au scenario 8 de la consultation des tarifs, sans indiquer le numero d'EP, meme montants
    @Test
    fun testFlat15NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario15NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.15"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs avec la lecture du document d'identite electronique (record 52).
    @Test
    fun testFlat16NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario16NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.16"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour - 2 ans) avec levee de prescription
    @Test
    fun testFlat17NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario17NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.17"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Deux prestations dont une est une prestation relative
    @Test
    fun testFlat18NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario18NISS1(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.18"
            writeFiles(fileName, mutualityCode, raw)
        }
    }

    //Flatrate MH.
    @Test
    fun testFlatFlatRate() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenarioFlatRate(this.restTemplate, this.port, mutualityCode, keystoreId!!.toString(), tokenId, passPhrase)
                ?: continue
            val raw = this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java)
            val fileName = "${this.filePrefix}.19"
            writeFiles(fileName, mutualityCode, raw)
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
                var file = ""
                println("Number of messages: "+it.body)
                file += "Number of messages: "+it.body.size+"\n\n\n"
                it.body.forEach {
                    if (!it.name.equals("tAck")) {

                        var ref = it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("204") }
                        println("================= Message =================")
                        println("Name: " + it.name)
                        println("OA: "+ref!!.value.toString().substring(8,11))
                        println("SendNumber: "+ref!!.value.toString().substring(11,14))
                        println("Message type: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("200") })
                        println("Message reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("204") })
                        println("Message Oa reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("205") })

                        file += "================= Message =================\n"
                        file += "Name: " + it.name+"\n"
                        file += "OA: "+ref!!.value.toString().substring(8,11)+"\n"
                        file += "SendNumber: "+ref!!.value.toString().substring(11,14)+"\n"
                        file += "Message type: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("200") }+"\n"
                        file += "Message reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("204") }+"\n"
                        file += "Message Oa reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("205") }+"\n"

                        it.message!!.forEach {

                            if (it.errorDetail != null) {

                                if (it.errorDetail!!.rejectionCode1 != "000000" && it.errorDetail!!.rejectionCode2 != "000000" && it.errorDetail!!.rejectionCode3 != "000000") {
                                    println("------- Reccord: " + it.zones.find { it.zoneDescription!!.zones!!.contains("1") } + "--------")
                                    file += "------- Reccord: " + it.zones.find { it.zoneDescription!!.zones!!.contains("1") } + "--------\n"
                                }

                                if (it.errorDetail!!.rejectionCode1 != "000000") {
                                    println("Rejection Code 1: " + it.errorDetail!!.rejectionCode1)
                                    println("Rejection descr 1: " + it.errorDetail!!.rejectionDescr1)
                                    println("Rejection zone descr 1: " + it.errorDetail!!.rejectionZoneDescr3)

                                    file += "Rejection Code 1: " + it.errorDetail!!.rejectionCode1+"\n"
                                    file += "Rejection descr 1: " + it.errorDetail!!.rejectionDescr1+"\n"
                                    file += "Rejection zone descr 1: " + it.errorDetail!!.rejectionZoneDescr1+"\n"

                                }

                                if (it.errorDetail!!.rejectionCode2 != "000000") {
                                    println("Rejection Code 2: " + it.errorDetail!!.rejectionCode2)
                                    println("Rejection descr 2: " + it.errorDetail!!.rejectionDescr2)
                                    println("Rejection zone descr 2: " + it.errorDetail!!.rejectionZoneDescr2)

                                    file += "Rejection Code 2: " + it.errorDetail!!.rejectionCode2+"\n"
                                    file += "Rejection descr 2: " + it.errorDetail!!.rejectionDescr2+"\n"
                                    file += "Rejection zone descr 2: " + it.errorDetail!!.rejectionZoneDescr2+"\n"
                                }

                                if (it.errorDetail!!.rejectionCode3 != "000000") {
                                    println("Rejection Code 3: " + it.errorDetail!!.rejectionCode3)
                                    println("Rejection descr 3: " + it.errorDetail!!.rejectionDescr3)
                                    println("Rejection zone  descr 3: " + it.errorDetail!!.rejectionZoneDescr3)

                                    file += "Rejection Code 3: " + it.errorDetail!!.rejectionCode3+"\n"
                                    file += "Rejection descr 3: " + it.errorDetail!!.rejectionDescr3+"\n"
                                    file += "Rejection zone descr 3: " + it.errorDetail!!.rejectionZoneDescr3+"\n"
                                }
                            }

                            if(it.zones.find{it.zoneDescription!!.zones!!.contains("403")} !== null){
                                file += "------- 400 ------- "+"\n"
                                file += "Signe montant demandé compte A: " + it.zones.find{it.zoneDescription!!.zones!!.contains("403")}+"\n"
                                file += "montant demandé compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("404")}+"\n"

                                file += "Signe montant accepté compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("411")}+"\n"
                                file += "montant accepté compte A: " + it.zones.find{it.zoneDescription!!.zones!!.contains("412")}+"\n"
                                file += "Signe montant refusé compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("413")}+"\n"
                                file += "montant refusé compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("414")}+"\n"
                                file += "Référence paiement compte A OA ou mutualité: " +it.zones.find{it.zoneDescription!!.zones!!.contains("423")}+"\n"

                            }

                            if(it.zones.find{it.zoneDescription!!.zones!!.contains("503")} !== null){
                                file += "------- 500 ------- "+"\n"
                                file += "Signe total montants demandés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("503")}+"\n"
                                file += "Total montants demandés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("504")}+"\n"
                                file += "Signe total montants acceptés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("511")}+"\n"
                                file += "Total montants acceptés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("512")}+"\n"
                                file += "Signe total montants refusés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("513")}+"\n"
                                file += "Total montants refusés compte A: " + it.zones.find{it.zoneDescription!!.zones!!.contains("514")}+"\n"
                            }

                        }

                    } else {
                        println("================= Tack =================")
                        println("Id: " + it.id)
                        println("Issuer" + it.tAck!!.issuer)
                        println("AppliesTo: " + it.tAck!!.appliesTo)
                        println("ResultMajor: " + it.tAck!!.resultMajor)
                        println("Tack id: " + it.tAck!!.id)
                        println("xades: " + it.xades)
                        println("hashValue: " + it.hashValue)

                        file += "================= Tack ================="
                        file += "Id: " + it.id+"\n"
                        file += "Issuer" + it.tAck!!.issuer+"\n"
                        file += "AppliesTo: " + it.tAck!!.appliesTo+"\n"
                        file += "ResultMajor: " + it.tAck!!.resultMajor+"\n"
                        file += "Tack id: " + it.tAck!!.id+"\n"
                        file += "xades: " + it.xades+"\n"
                        file += "hashValue: " + it.hashValue+"\n"
                    }
                }
                val fileName = "LoadMessage_"+(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toLong())
                File(fileName).writeText(file)
            }
    }

    @Test
    fun testGuardPostLoadMessage() {
        val (keystoreId, tokenId, passPhrase) = registerGuardPost(restTemplate!!, port, nihii4!!, password4!!)
        this.restTemplate.exchange("http://localhost:$port/efact/$nihii4/fr?ssin=$ssin4&firstName={firstName}&lastName={lastName}&isGuardPost=true", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), object : ParameterizedTypeReference<List<EfactMessage>>() {}, firstName1, lastName1)
            ?.let {
                var file = ""
                println("Number of messages: "+it.body)
                file += "Number of messages: "+it.body.size+"\n\n\n"
                it.body.forEach {
                    if (!it.name.equals("tAck")) {

                        println(it.detail)

                        try {
                            var ref = it.message!![0].zones.find { it.zoneDescription?.zones!!.contains("204") }
                            println("================= Message =================")
                            println("Name: " + it.name)
                            println("OA: "+ref!!.value.toString().substring(8,11))
                            println("SendNumber: "+ref!!.value.toString().substring(11,14))
                            println("Message type: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("200") })
                            println("Message reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("204") })
                            println("Message Oa reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("205") })

                            file += "================= Message =================\n"
                            file += "Name: " + it.name+"\n"
                            file += "OA: "+ref!!.value.toString().substring(8,11)+"\n"
                            file += "SendNumber: "+ref!!.value.toString().substring(11,14)+"\n"
                            file += "Message type: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("200") }+"\n"
                            file += "Message reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("204") }+"\n"
                            file += "Message Oa reference: " + it.message!![0].zones.find { it.zoneDescription!!.zones!!.contains("205") }+"\n"

                            it.message!!.forEach {

                                if (it.errorDetail != null) {

                                    if (it.errorDetail!!.rejectionCode1 != "000000" && it.errorDetail!!.rejectionCode2 != "000000" && it.errorDetail!!.rejectionCode3 != "000000") {
                                        println("------- Reccord: " + it.zones.find { it.zoneDescription!!.zones!!.contains("1") } + "--------")
                                        file += "------- Reccord: " + it.zones.find { it.zoneDescription!!.zones!!.contains("1") } + "--------\n"
                                    }

                                    if (it.errorDetail!!.rejectionCode1 != "000000") {
                                        println("Rejection Code 1: " + it.errorDetail!!.rejectionCode1)
                                        println("Rejection descr 1: " + it.errorDetail!!.rejectionDescr1)
                                        println("Rejection zone descr 1: " + it.errorDetail!!.rejectionZoneDescr3)

                                        file += "Rejection Code 1: " + it.errorDetail!!.rejectionCode1+"\n"
                                        file += "Rejection descr 1: " + it.errorDetail!!.rejectionDescr1+"\n"
                                        file += "Rejection zone descr 1: " + it.errorDetail!!.rejectionZoneDescr1+"\n"

                                    }

                                    if (it.errorDetail!!.rejectionCode2 != "000000") {
                                        println("Rejection Code 2: " + it.errorDetail!!.rejectionCode2)
                                        println("Rejection descr 2: " + it.errorDetail!!.rejectionDescr2)
                                        println("Rejection zone descr 2: " + it.errorDetail!!.rejectionZoneDescr2)

                                        file += "Rejection Code 2: " + it.errorDetail!!.rejectionCode2+"\n"
                                        file += "Rejection descr 2: " + it.errorDetail!!.rejectionDescr2+"\n"
                                        file += "Rejection zone descr 2: " + it.errorDetail!!.rejectionZoneDescr2+"\n"
                                    }

                                    if (it.errorDetail!!.rejectionCode3 != "000000") {
                                        println("Rejection Code 3: " + it.errorDetail!!.rejectionCode3)
                                        println("Rejection descr 3: " + it.errorDetail!!.rejectionDescr3)
                                        println("Rejection zone  descr 3: " + it.errorDetail!!.rejectionZoneDescr3)

                                        file += "Rejection Code 3: " + it.errorDetail!!.rejectionCode3+"\n"
                                        file += "Rejection descr 3: " + it.errorDetail!!.rejectionDescr3+"\n"
                                        file += "Rejection zone descr 3: " + it.errorDetail!!.rejectionZoneDescr3+"\n"
                                    }
                                }

                                if(it.zones.find{it.zoneDescription!!.zones!!.contains("403")} !== null){
                                    file += "------- 400 ------- "+"\n"
                                    file += "Signe montant demandé compte A: " + it.zones.find{it.zoneDescription!!.zones!!.contains("403")}+"\n"
                                    file += "montant demandé compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("404")}+"\n"

                                    file += "Signe montant accepté compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("411")}+"\n"
                                    file += "montant accepté compte A: " + it.zones.find{it.zoneDescription!!.zones!!.contains("412")}+"\n"
                                    file += "Signe montant refusé compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("413")}+"\n"
                                    file += "montant refusé compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("414")}+"\n"
                                    file += "Référence paiement compte A OA ou mutualité: " +it.zones.find{it.zoneDescription!!.zones!!.contains("423")}+"\n"

                                }

                                if(it.zones.find{it.zoneDescription!!.zones!!.contains("503")} !== null){
                                    file += "------- 500 ------- "+"\n"
                                    file += "Signe total montants demandés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("503")}+"\n"
                                    file += "Total montants demandés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("504")}+"\n"
                                    file += "Signe total montants acceptés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("511")}+"\n"
                                    file += "Total montants acceptés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("512")}+"\n"
                                    file += "Signe total montants refusés compte A: " +it.zones.find{it.zoneDescription!!.zones!!.contains("513")}+"\n"
                                    file += "Total montants refusés compte A: " + it.zones.find{it.zoneDescription!!.zones!!.contains("514")}+"\n"
                                }
                            }
                        } catch (err: Exception) {
                            println(err)
                            println(err.stackTrace.toString())
                        }

                    } else {
                        println("================= Tack =================")
                        println("Id: " + it.id)
                        println("Issuer" + it.tAck!!.issuer)
                        println("AppliesTo: " + it.tAck!!.appliesTo)
                        println("ResultMajor: " + it.tAck!!.resultMajor)
                        println("Tack id: " + it.tAck!!.id)
                        println("xades: " + it.xades)
                        println("hashValue: " + it.hashValue)

                        file += "================= Tack ================="
                        file += "Id: " + it.id+"\n"
                        file += "Issuer" + it.tAck!!.issuer+"\n"
                        file += "AppliesTo: " + it.tAck!!.appliesTo+"\n"
                        file += "ResultMajor: " + it.tAck!!.resultMajor+"\n"
                        file += "Tack id: " + it.tAck!!.id+"\n"
                        file += "xades: " + it.xades+"\n"
                        file += "hashValue: " + it.hashValue+"\n"
                    }
                }
                val fileName = "LoadMessage_"+(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toLong())
                File(fileName).writeText(file)
            }
    }

    @Test
    fun testLoadAndConfirmMessage() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        this.restTemplate.exchange("http://localhost:$port/efact/$nihii1/fr?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), object : ParameterizedTypeReference<List<EfactMessage>>() {}, firstName1, lastName1)
            ?.let {
                this.restTemplate.exchange("http://localhost:$port/efact/confirm/acks/$nihii1?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.PUT, HttpEntity<List<String>>(it.body.filter { it.tAck != null }.map { it.hashValue }.filterNotNull(), createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
                this.restTemplate.exchange("http://localhost:$port/efact/confirm/msgs/$nihii1?ssin=$ssin1&firstName={firstName}&lastName={lastName}", HttpMethod.PUT, HttpEntity<List<String>>(it.body.filter { it.tAck == null }.map { it.hashValue }.filterNotNull().let {it.subList(0, Math.min(20, it.size))}, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
            }
    }

    @Test
    fun testLoadAndConfirmGuardPostMessage() {
        val (keystoreId, tokenId, passPhrase) = registerGuardPost(restTemplate!!, port, nihii4!!, password4!!)
        this.restTemplate.exchange("http://localhost:$port/efact/$nihii4/fr?ssin=$ssin4&firstName={firstName}&lastName={lastName}&isGuardPost=true", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), object : ParameterizedTypeReference<List<EfactMessage>>() {}, firstName1, lastName1)
            ?.let {
                this.restTemplate.exchange("http://localhost:$port/efact/confirm/acks/$nihii4?ssin=$ssin4&firstName={firstName}&lastName={lastName}&isGuardPost=true", HttpMethod.PUT, HttpEntity<List<String>>(it.body.filter { it.tAck != null }.map { it.hashValue }.filterNotNull(), createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
                this.restTemplate.exchange("http://localhost:$port/efact/confirm/msgs/$nihii4?ssin=$ssin4&firstName={firstName}&lastName={lastName}&isGuardPost=true", HttpMethod.PUT, HttpEntity<List<String>>(it.body.filter { it.tAck == null }.map { it.hashValue }.filterNotNull().let {it.subList(0, Math.min(20, it.size))}, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
            }
    }

    @Test
    fun testReParseInDir() {
        val root = File(".")
        root.listFiles { f:File  -> f.extension == "txt" }.forEach { f ->
            val w = FileWriter(File(f.parent, "${f.nameWithoutExtension}.yml"))
            w.write(BelgianInsuranceInvoicingFormatReader("fr").parse(FileReader(f), false)!!.map { it.toString() }.joinToString("\n"))
            w.flush()
            w.close()
        }
    }


    @Test
    fun testReParse920999() {
        val fileContent = "9200990002009200000000190408300817001909930000000100               20180900817002019040800FHCA13.300   00999199900HELPDESK CARENET/MYCARENET                   00                        000025151700000000010000000000100                                                                                                                                                                                                                                                                                                                                                                                                                                                                           "
        val res = BelgianInsuranceInvoicingFormatReader("fr").parse(fileContent.reader(), false)!!.map { it.toString() }.joinToString("\n")
        println(res)
    }

    @Test
    fun testReParse920098() {
        val res1 = BelgianInsuranceInvoicingFormatReader("fr").parse("920098000200120000000050002018082300020185010444010000000000000000020180800235002018082300             00000199900SERVICE                                      00TARIFICATION            000027789555000300010000000000000                                                                                                                                                                                                                                                                                                                                                                                                                                                                           10000001000019990000000000000000235000000000000000000400114787610000000000000000000000000000000000000000000020180800000201808230508465387INB-B7D46B7DD683474886B560000GEBABEBB   0BE25001686081682                  000000           00000000000000000000000000000000000000000                                  00000000000000000000000000000000000000000000161112018083111111111111111111111111111111111101147876100011111111111111111111111111111111111111111111111111 000000 000000 000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000019000006000000000000000000000000023500000000000000000000011478761000+0000000000000000000+0000003224200000000020180800000000000000508465387INB-B7D46B7DD683474886B560000GEBABEBB   0BE25001686081682                  000000           00000000000000000000000000000000000000000                                  00000000000000000000000000000000000000000066729999999999999999999999999999999999999999999901147876100099999999999999999999999999999999999999999999999999 000000 000000 00000099999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999000002".reader(), true)!!.map { it.toString() }.joinToString("\n")
        println(res1)
    }

}

