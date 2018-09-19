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
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.common.Insurability
import org.taktik.freehealth.middleware.domain.common.InsuranceParameter
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.efact.EIDItem
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
import org.taktik.freehealth.utils.FuzzyValues
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.HashMap
import kotlin.math.roundToInt

@Suppress("PrivatePropertyName")
@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EfactControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    // Add the scenario number to this to get a unique ID, then you can run all the tests without having to manually change the unique send number
    var sendNumber = 1

    private final val DMG_NIHIIS_FOR_NISS2: MutableMap<String, String> = HashMap()
    private final val NISS1 = "NISS1"
    private final val NISS2 = "NISS2"
    private final val NISS3 = "NISS3"
    private final val NISS4 = "NISS4"

    private final val NISSES_BY_MUTUALITY: MutableMap<String, Map<String, String>> = HashMap()
    private final val EMAILS_BY_MUTUALITY: MutableMap<String, String> = HashMap()
    private final val DEFAULT_CODE_FOR_FED: MutableMap<String, String> = HashMap()

    private fun fillNisses(mutualityCode: String, niss1: String, niss2: String, niss3: String, niss4: String) {
        val map = HashMap<String, String>()
        map[NISS1] = niss1
        map[NISS2] = niss2
        map[NISS3] = niss3
        map[NISS4] = niss4
        NISSES_BY_MUTUALITY[mutualityCode] = map
    }

    private val results = HashMap<String, MutableMap<String, Boolean>>()

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

        results["100"]?.put("01", false);results["300"]?.put("01", false);results["500"]?.put("01", false);results["600"]?.put("01", false);results["900"]?.put("01", true)
        results["100"]?.put("02", false);results["300"]?.put("02", true);results["500"]?.put("02", false);results["600"]?.put("02", false);results["900"]?.put("02", true)
        results["100"]?.put("03", false);results["300"]?.put("03", true);results["500"]?.put("03", false);results["600"]?.put("03", false);results["900"]?.put("03", true)
        results["100"]?.put("04", false);results["300"]?.put("04", false);results["500"]?.put("04", false);results["600"]?.put("04", false);results["900"]?.put("04", true)
        results["100"]?.put("05", false);results["300"]?.put("05", false);results["500"]?.put("05", false);results["600"]?.put("05", false);results["900"]?.put("05", true)
        results["100"]?.put("06", false);results["300"]?.put("06", false);results["500"]?.put("06", false);results["600"]?.put("06", false);results["900"]?.put("06", true)
        results["100"]?.put("07", false);results["300"]?.put("07", false);results["500"]?.put("07", false);results["600"]?.put("07", false);results["900"]?.put("07", true)
        results["100"]?.put("08", false);results["300"]?.put("08", true);results["500"]?.put("08", false);results["600"]?.put("08", false);results["900"]?.put("08", true)
        results["100"]?.put("09", false);results["300"]?.put("09", true);results["500"]?.put("09", false);results["600"]?.put("09", false);results["900"]?.put("09", true)
        results["100"]?.put("10", false);results["300"]?.put("10", false);results["500"]?.put("10", false);results["600"]?.put("10", false);results["900"]?.put("10", true)
        results["100"]?.put("11", false);results["300"]?.put("11", false);results["500"]?.put("11", false);results["600"]?.put("11", false);results["900"]?.put("11", true)
        results["100"]?.put("12", false);results["300"]?.put("12", false);results["500"]?.put("12", false);results["600"]?.put("12", false);results["900"]?.put("12", true)
        results["100"]?.put("13", false);results["300"]?.put("13", false);results["500"]?.put("13", false);results["600"]?.put("13", false);results["900"]?.put("13", true)
        results["100"]?.put("14", false);results["300"]?.put("14", true);results["500"]?.put("14", false);results["600"]?.put("14", false);results["900"]?.put("14", true)
        results["100"]?.put("15", false);results["300"]?.put("15", true);results["500"]?.put("15", false);results["600"]?.put("15", false);results["900"]?.put("15", true)
        results["100"]?.put("16", false);results["300"]?.put("16", false);results["500"]?.put("16", false);results["600"]?.put("16", false);results["900"]?.put("16", true)
        results["100"]?.put("17", false);results["300"]?.put("17", true);results["500"]?.put("17", false);results["600"]?.put("17", false);results["900"]?.put("17", true)
        results["100"]?.put("18", false);results["300"]?.put("18", true);results["500"]?.put("18", false);results["600"]?.put("18", false);results["900"]?.put("18", true)
    }

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

    fun prepareScenario01(mutualityCode: String, keystoreId: String, tokenId: String, passPhrase: String): InvoicesBatch? {
        val niss = if (mutualityCode == "300") "59011214562" else "64032764903"
        println("***** Scenario 1 - Mutuality $mutualityCode - NISS: $niss from another mutuality *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif (niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA01.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), Date()))
        }
    }

    @Test
    fun testFlat01() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario01(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.01.$mutualityCode.txt8").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Un patient non affilie a l'OA (NISS appartenant a un autre OA)
    @Test
    fun testScenario01() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "01")) continue
            val invBatch = prepareScenario01(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario02NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 2 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(102793L, 1920, 200, 0, null, Date()))
        }
    }

    @Test
    fun testFlat02NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario02NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Un code nomenclature existant mais non permis pour un medecin generaliste (NISS 1 - 102793
    @Test
    fun testScenario02NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "02")) continue
            val invBatch = prepareScenario02NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario03NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 3 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null

        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()?.items?.add(createInvoiceItem(101075L, 1920, 200, 0, null, Date()))
        }
    }

    @Test
    fun testFlat03NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario03NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Un code nomenclature inexistant (NISS 1 - 101075)
    @Test
    fun testScenario03NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "03")) continue
            val invBatch = prepareScenario03NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario04NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 4 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusYears(-3).toDate()).apply {
                insuranceRef =
                    null
            })
        }
    }

    @Test
    fun testFlat04NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario04NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour -2 ans) sans levee de prescription
    @Test
    fun testScenario04NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "04")) continue
            val invBatch = prepareScenario04NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario05NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 5 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat05NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario05NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Une prestation dans le futur (NISS 1 - date prestation > date du jour + 2 mois)
    @Test
    fun testScenario05NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "05")) continue
            val invBatch = prepareScenario05NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario06NISS3(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS3]!!
        println("***** Scenario 6 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null

        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(101032, 1920, 200, 0, null, DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat06NISS3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario06NISS3(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Une prestation non permise pour un patient affilie dans une maison medicale (NISS 3)
    @Test
    fun testScenario06NISS3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "06")) continue
            val invBatch = prepareScenario06NISS3(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario07NISS4(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS4]!!
        println("***** Scenario 7 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(101032, 1920, 200, 0, null, DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat07NISS4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario07NISS4(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Une prestation pour un patient non en regle (NISS 4)
    @Test
    fun testScenario07NISS4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "07")) continue
            val invBatch = prepareScenario07NISS4(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario08NISS2(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 8 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat08NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario08NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation dont le tarif a ete obtenu via le scenario 9 de la consultation des tarifs (NISS 2 - tiers payant non autorise sans justification particuliere)
    @Test
    fun testScenario08NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "08")) continue
            val invBatch = prepareScenario08NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario09NISS2(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 9 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null

        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(101032, 1820, 200, 0, null, DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat09NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario09NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation correspondant au scenario 10 de la consultation des tarifs, sans indiquer le numero d'engagement de paiement, montants differents.
    @Test
    fun testScenario09NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "09")) continue
            val invBatch = prepareScenario09NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario10NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 10 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat10NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario10NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs.
    @Test
    fun testScenario10NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "10")) continue
            val invBatch = prepareScenario10NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario11NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 11 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult =
            consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032", "475075"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()),
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[1]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                    relatedCode =
                        767071L
                }))
        }
    }

    @Test
    fun testFlat11NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario11NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestations pour lesquelles un engagement de paiement a ete obtenu via le scenario 8 de la consultation des tarifs.
    @Test
    fun testScenario11NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "11")) continue
            val invBatch = prepareScenario11NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario12NISS2(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 12 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null

        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, 2, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat12NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario12NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 10 de la consultation des tarifs.
    @Test
    fun testScenario12NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "12")) continue
            val invBatch = prepareScenario12NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario13NISS2(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS2]!!
        println("***** Scenario 13 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult =
            consultTarif(niss, keystoreId, tokenId, passPhrase, 7, listOf("101032"), DMG_NIHIIS_FOR_NISS2[mutualityCode])
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()))
        }
    }

    @Test
    fun testFlat13NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario13NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 11 de la consultation des tarifs.
    @Test
    fun testScenario13NISS2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "13")) continue
            val invBatch = prepareScenario13NISS2(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario14NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 14 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                insuranceRef =
                    null
            })
        }
    }

    @Test
    fun testFlat14NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario14NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation correspondant au scenario 7 de la consultation des tarifs, sans indiquer le numero d'EP.
    @Test
    fun testScenario14NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "14")) continue
            val invBatch = prepareScenario14NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario15NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 15 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null

        val consult =
            consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032", "475075"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                    insuranceRef =
                        null
                },
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[1]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                    insuranceRef =
                        null; relatedCode = 767071L
                }))
        }
    }

    @Test
    fun testFlat15NISS108NISS() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario15NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation correspondant au scenario 8 de la consultation des tarifs, sans indiquer le numero d'EP, meme montants
    @Test
    fun testScenario15NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "15")) continue
            val invBatch = prepareScenario15NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario16NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 16 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val cal = Calendar.getInstance()

        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                eidItem = EIDItem().apply {
                    readHour = cal.get(Calendar.HOUR_OF_DAY) * 100 + cal.get(Calendar.MINUTE)
                }
            })
        }
    }

    @Test
    fun testFlat16NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario16NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Prestation pour laquelle un engagement de paiement a ete obtenu via le scenario 7 de la consultation des tarifs avec la lecture du document d'identite electronique (record 52).
    @Test
    fun testScenario16NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "16")) continue
            val invBatch = prepareScenario16NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario17NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 17 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult = consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("101032"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()
                ?.items?.add(createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusYears(-3).toDate()).apply {
                insuranceRef =
                    null
            })
        }
    }

    @Test
    fun testFlat17NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario17NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Une prestation datant de plus de 2 ans (NISS 1 - date prestation < date du jour - 2 ans) avec levee de prescription
    @Test
    fun testScenario17NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "17")) continue
            val invBatch = prepareScenario17NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    fun prepareScenario18NISS1(mutualityCode: String,
                               keystoreId: String,
                               tokenId: String,
                               passPhrase: String): InvoicesBatch? {

        val niss = NISSES_BY_MUTUALITY[mutualityCode]!![NISS1]!!
        println("***** Scenario 18 - Mutuality $mutualityCode - NISS: $niss *****")
        val patientWithInss = getPatient(niss, keystoreId, tokenId, passPhrase) ?: return null
        val consult =
            consultTarif(niss, keystoreId, tokenId, passPhrase, null, listOf("475075", "590030"))
        return createBatch((10 * 10 + Integer.valueOf(mutualityCode.substring(0, 1))).toLong(), "FHCA18.$mutualityCode", mutualityCode, patientWithInss, sendNumber++).apply {
            invoices.firstOrNull()?.items?.addAll(listOf(
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[0]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                    relatedCode =
                        767071L
                },
                createInvoiceItem(java.lang.Long.valueOf(consult.codes[1]), ((consult.reimbursements.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), ((consult.patientFees.firstOrNull()?.amount
                    ?: .0) * 100).roundToInt(), 0, consult.financialContracts.firstOrNull(), DateTime().plusMonths(2).toDate()).apply {
                    relatedCode =
                        475075L
                }))
        }
    }

    @Test
    fun testFlat18NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            val invBatch = prepareScenario18NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            File("FHC.ACC.18.$mutualityCode.txt").bufferedWriter()
                .write(this.restTemplate.postForObject("http://localhost:$port/efact/flat/test", invBatch, String::class.java))
        }
    }

    //Deux prestations dont une est une prestation relative
    @Test
    fun testScenario18NISS1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        for (mutualityCode in NISSES_BY_MUTUALITY.keys) {
            if (alreadyDone(mutualityCode, "18")) continue
            val invBatch = prepareScenario18NISS1(mutualityCode, keystoreId!!.toString(), tokenId, passPhrase) ?: continue
            this.restTemplate.postForObject("http://localhost:$port/efact/batch?keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}", invBatch, EfactSendResponse::class.java, passPhrase)
                ?.let {
                    assert(it.success ?: false)
                }
        }
    }

    private fun createBatch(invoiceNumber: Long,
                            batchRef: String,
                            oa: String,
                            patientWithInss: org.taktik.freehealth.middleware.domain.common.Patient,
                            uniq: Int): InvoicesBatch =
        InvoicesBatch().apply {
            invoicingYear = 2015
            invoicingMonth = 9
            this.batchRef = batchRef
            uniqueSendNumber = uniq.toLong()
            ioFederationCode = oa
            numericalRef = (FuzzyValues.currentFuzzyDateTime % 10_000_000_000)

            sender = InvoiceSender().apply {
                nihii = nihii1!!.toLong()
                ssin = ssin1!!
                bce = cbe1!!.toLong()
                bic = BIC1
                iban = IBAN1
                firstName = firstName1
                lastName = lastName1
                phoneNumber = 3223335840L
            }

            invoices.add(Invoice().apply {
                patient = patientWithInss
                this.invoiceNumber = invoiceNumber
                this.invoiceRef = batchRef
                this.ioCode = oa
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

    private fun consultTarif(niss: String, keystoreId: String, tokenId: String, passPhrase: String,
                             justification: Int?,
                             codes: List<String>,
                             nihiiGmdOwner: String? = null): TarificationConsultationResult =
        this.restTemplate!!.postForObject("http://localhost:$port/tarif/$niss?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&keystoreId=$keystoreId&tokenId=$tokenId&passPhrase={passPhrase}&date=${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))}" + (justification?.let { "&justification=$justification" }
            ?: "") + (nihiiGmdOwner?.let { "&gmdNihii=$nihiiGmdOwner" }
            ?: ""), codes, TarificationConsultationResult::class.java, firstName1, lastName1, passPhrase)

    private fun getPatient(niss: String, keystoreId: String, tokenId: String, passPhrase: String) =
        this.restTemplate!!.getForObject("http://localhost:$port/genins/$niss?keystoreId=$keystoreId&tokenId=$tokenId&hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}&passPhrase={passPhrase}", InsurabilityInfoDto::class.java, passPhrase)?.let {
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

}
