package org.taktik.freehealth.middleware.service.impl

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import org.hamcrest.Matchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.business.domain.kmehr.v20161201.be.ehealth.logic.recipe.xsd.v20160906.RecipeNotification
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDAYPERIODvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNK
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNKschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHEADINGschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLE
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLEvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEX
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOM
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOMschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNIT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNITschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDDAYPERIODvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDHEADING
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDITEM
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeKmehrmessageType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeadministrationquantityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeauthorType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipebasicIDKMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipecontentType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipefolderType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeheaderType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeitemType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipelifecycleType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipemedicinalProductType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipemomentType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipepatientpersonType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipequantityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipetransactionType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipetransactionheadingType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.SenderType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.SexType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.StandardType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.TelecomType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.UnitType
import org.taktik.connector.business.recipe.utils.KmehrHelper
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper.toDaytime
import org.taktik.connector.business.recipe.utils.KmehrValidator
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.recipe.CompoundPrescription
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.recipe.RegimenItem
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.icure.be.ehealth.logic.recipe.impl.KmehrPrescriptionConfig
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Companion.taktik
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion.compoundPrescriptionP2
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.Properties
import java.util.UUID
import javax.xml.bind.JAXBContext
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:/test.properties"])
class RecipeServiceImplTest {
    @Autowired
    lateinit var stsService : STSServiceImpl
    @Autowired
    lateinit var recipeService : RecipeV4ServiceImpl

    @Value("\${org.taktik.icure.keystore1.ssin}") var ssin : String? = null
    @Value("\${org.taktik.icure.keystore1.nihii}") var nihii : String? = null
    @Value("\${org.taktik.icure.keystore1.password}") var passPhrase : String? = null

    private var tokenId: UUID? = null
    private var keystoreId: UUID? = null

    private lateinit var hcp: HealthcareParty
    private val patient = RecipeTestUtils.createPatient()
    private val validator by lazy { KmehrValidator(recipeService) }

    init {
        System.setProperty("spring.output.ansi.enabled", "always")
    }

    @Before
    fun setup() {
        hcp = RecipeTestUtils.createHealthcareParty()
        keystoreId = stsService.uploadKeystore((MyTestsConfiguration::class).java.getResource("$ssin.acc-p12").readBytes())
        tokenId = stsService.requestToken(keystoreId!!, ssin!!, passPhrase!!)?.tokenId
    }


    val kmehrHelper = KmehrHelper(Properties().apply { load(javaClass.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties")) })

    @Test
    fun validatePrescription() {
        val medication = compoundPrescriptionP2()
        val kmehrPrescription = recipeService.getKmehrPrescription(
            Patient().apply { firstName = "Antoine"; lastName = "Duchateau"; dateOfBirth = 19740104; ssin = "74010414733" },
            HealthcareParty(firstName = "Antoine", lastName = "Baudoux", ssin = "79121430944", nihii = "11478761004", addresses = mutableSetOf(taktik())),
            listOf(medication),
            LocalDateTime.now(), KmehrPrescriptionConfig(), "doctor", LocalDateTime.now().plusDays(60))

	    validator.validatePrescription(kmehrPrescription, listOf(medication))
    }

	@Test
	fun fixInvalidRegimenAdministrationUnit() {
		val medications = listOf(Medication().apply {
			compoundPrescriptionV2 = CompoundPrescription.MagistralText("aaa")
			recipeInstructionForPatient = "bbb"
			beginMoment = 20180103
			regimen = mutableListOf(RegimenItem().apply {
				timeOfDay = 115500
				administratedQuantity = RegimenItem.AdministrationQuantity().apply {
					quantity = 1.0
					administrationUnit = Code("CD-ADMINISTRATIONUNIT", "INVALID", "1")
				}
			})
		})
		val kmehrPrescription = recipeService.getKmehrPrescription(
            Patient().apply { firstName = "Antoine"; lastName = "Duchateau"; dateOfBirth = 19740104; ssin = "74010414733" },
            HealthcareParty(firstName = "Antoine", lastName = "Baudoux", ssin = "79121430944", nihii = "11478761004", addresses = mutableSetOf(taktik()) ),
            medications,
            LocalDateTime.now(), KmehrPrescriptionConfig(), "doctor", LocalDateTime.now().plusDays(60))

		val quantities = (kmehrPrescription.folders.first().transactions.first().headingsAndItemsAndTexts.first() as org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType).regimen.daynumbersAndQuantitiesAndDates
			.filter { it is RecipeadministrationquantityType }
		assertTrue(quantities.isNotEmpty())
		quantities.forEach { assertEquals(RecipeV4ServiceImpl.defaultDosis, (it as RecipeadministrationquantityType).unit.cd.value) }
	}

	@Test
	fun toDaytime_time() {
		val daytime = toDaytime(RegimenItem().apply {
			timeOfDay = 120000
		})
		assertEquals(XMLGregorianCalendarImpl.parse("12:00:00"), daytime.time)
	}

	@Test
	fun toDaytime_periodAfternoon_16h() {
		val daytime = toDaytime(RegimenItem().apply {
			dayPeriod = Code("CD-DAYPERIOD", "afternoon")
		})
		assertEquals(XMLGregorianCalendarImpl.parse("16:00:00"), daytime.time)
	}

	@Test
	fun toDaytime_periodEvening_19h() {
		val daytime = toDaytime(RegimenItem().apply {
			dayPeriod = Code("CD-DAYPERIOD", "evening")
		})
		assertEquals(XMLGregorianCalendarImpl.parse("19:00:00"), daytime.time)
	}

	@Test
	fun toDaytime_periodNight_22h() {
		val daytime = toDaytime(RegimenItem().apply {
			dayPeriod = Code("CD-DAYPERIOD", "night")
		})
		assertEquals(XMLGregorianCalendarImpl.parse("22:00:00"), daytime.time)
	}

	@Test(expected = IllegalArgumentException::class)
	fun toDaytime_periodAftermeal_unsupported() {
		toDaytime(RegimenItem().apply {
			dayPeriod = Code("CD-DAYPERIOD", "aftermeal")
		})
	}

	@Test(expected = IllegalArgumentException::class)
	fun toDaytime_periodBetweenmeals_unsupported() {
		toDaytime(RegimenItem().apply {
			dayPeriod = Code("CD-DAYPERIOD", "betweenmeals")
		})
	}

	@Test
	fun toDaytime_periods_allMapped() {
		val kmehrDayPeriods = CDDAYPERIODvalues.values().map { it.value() }.toCollection(mutableSetOf()).toSet()
		val recipeDayPeriods = RecipeCDDAYPERIODvalues.values().map { it.value().value() }.toCollection(mutableSetOf()).toSet()
		assertTrue("problem with recipe customizations: connector adds unknown codes ${recipeDayPeriods - kmehrDayPeriods}", (recipeDayPeriods - kmehrDayPeriods).isEmpty())
		recipeDayPeriods.forEach {
			assertEquals(it, toDaytime(RegimenItem().apply { dayPeriod = Code("CD-DAYPERIOD", it) }).dayperiod.cd.value.value())
		}
		(kmehrDayPeriods - recipeDayPeriods).forEach {
			val time: XMLGregorianCalendar?
			try {
				time = toDaytime(RegimenItem().apply { dayPeriod = Code("CD-DAYPERIOD", it) }).time
				assertThat(it, time, Matchers.notNullValue())
			} catch (e: IllegalArgumentException) {
				// ok
			}
		}
	}

	@Test
	fun toDayTime_periodBeforeBreakfast_codeTypeVersionOk() {
		val daytime = toDaytime(RegimenItem().apply {
			dayPeriod = Code("CD-DAYPERIOD", "beforebreakfast")
		})
		assertEquals(daytime.dayperiod.cd.s, "CD-DAYPERIOD")
		assertEquals(daytime.dayperiod.cd.sv, "1.1")
	}

    @Test
    fun validateXmlNotification() {
        val notification = """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<p:notification xmlns:p="http://services.recipe.be" xmlns:ns2="http://www.w3.org/2001/04/xmlenc#" xmlns:ns3="http://www.w3.org/2000/09/xmldsig#">
    <text>This is a notification</text>
    <kmehrmessage>
        <header xmlns="http://www.ehealth.fgov.be/standards/kmehr/schema/v1">
            <standard>
                <cd S="CD-STANDARD" SV="1.19">20161201</cd>
            </standard>
            <id S="ID-KMEHR" SV="1.0">11478761004.1490777844496</id>
            <id S="LOCAL" SV="4.0.3-on7ns4_e991da3" SL="ICure"></id>
            <date>2017-03-29</date>
            <time>10:57:24.490</time>
            <sender>
                <hcparty>
                    <id S="ID-HCPARTY" SV="1.0">11478761004</id>
                    <id S="INSS" SV="1.0">74010414733</id>
                    <cd S="CD-HCPARTY" SV="1.9">persphysician</cd>
                    <firstname>Antoine</firstname>
                    <familyname>Duchâteau</familyname>
                </hcparty>
                <hcparty>
                    <cd S="CD-HCPARTY" SV="1.9">application</cd>
                    <name>ICure-4.0.3-on7ns4_e991da3</name>
                    <telecom>
                        <cd S="CD-ADDRESS" SV="1.1">work</cd>
                        <cd S="CD-TELECOM" SV="1.0">phone</cd>
                        <telecomnumber>+3223335840</telecomnumber>
                    </telecom>
                    <telecom>
                        <cd S="CD-ADDRESS" SV="1.1">work</cd>
                        <cd S="CD-TELECOM" SV="1.0">email</cd>
                        <telecomnumber>info@taktik.be</telecomnumber>
                    </telecom>
                </hcparty>
            </sender>
            <recipient>
                <hcparty>
                    <id S="ID-HCPARTY" SV="1.0">RECIPE</id>
                    <cd S="CD-HCPARTY" SV="1.9">orgpublichealth</cd>
                    <name>Recip-e</name>
                </hcparty>
            </recipient>
        </header>
        <folder xmlns="http://www.ehealth.fgov.be/standards/kmehr/schema/v1">
            <id S="ID-KMEHR" SV="1.0">1</id>
            <patient>
                <id S="ID-PATIENT" SV="1.0">82051234978</id>
                <firstname>John</firstname>
                <familyname>Doe</familyname>
                <birthdate>
                    <date>1997-03-29</date>
                </birthdate>
                <sex>
                    <cd S="CD-SEX" SV="1.1">male</cd>
                </sex>
            </patient>
            <transaction>
                <id S="ID-KMEHR" SV="1.0">1</id>
                <cd S="CD-TRANSACTION" SV="1.9">pharmaceuticalprescription</cd>
                <date>2017-03-29</date>
                <time>10:57:24.514</time>
                <author>
                    <hcparty>
                        <id S="ID-HCPARTY" SV="1.0">11478761004</id>
                        <cd S="CD-HCPARTY" SV="1.9">persphysician</cd>
                        <firstname>Antoine</firstname>
                        <familyname>Duchâteau</familyname>
                        <address>
                            <cd S="CD-ADDRESS" SV="1.1">work</cd>
                            <country>
                                <cd S="CD-COUNTRY" SV="1.2">BE</cd>
                            </country>
                            <zip>1420</zip>
                            <city>Braine-l'Alleud</city>
                            <street>av. de Finlande</street>
                            <housenumber>8</housenumber>
                        </address>
                        <telecom>
                            <cd S="CD-ADDRESS" SV="1.1">work</cd>
                            <cd S="CD-TELECOM" SV="1.0">phone</cd>
                            <telecomnumber>+3223335840</telecomnumber>
                        </telecom>
                    </hcparty>
                </author>
                <iscomplete>true</iscomplete>
                <isvalidated>true</isvalidated>
                <heading>
                    <id S="ID-KMEHR" SV="1.0">1</id>
                    <cd S="CD-HEADING" SV="1.2">prescription</cd>
                    <item>
                        <id S="ID-KMEHR" SV="1.0">0</id>
                        <cd S="CD-ITEM" SV="1.9">medication</cd>
                        <content>
                            <compoundprescription>Paracetamol Mylan compr 100x 500mg</compoundprescription>
                        </content>
                        <beginmoment>
                            <date>1970-01-18+01:00</date>
                        </beginmoment>
                        <lifecycle>
                            <cd S="CD-LIFECYCLE" SV="1.7">prescribed</cd>
                        </lifecycle>
                        <quantity>
                            <decimal>1</decimal>
                            <unit>
                                <cd S="CD-UNIT" SV="1.0">pkg</cd>
                            </unit>
                        </quantity>
                        <posology>
                            <text L="FR">2.0, 1 x daily, 2,000000 00005, null</text>
                        </posology>
                        <deliverydate>2017-03-29</deliverydate>
                    </item>
                </heading>
                <recorddatetime>2017-03-29T10:57:24.514</recorddatetime>
            </transaction>
        </folder>
    </kmehrmessage>
</p:notification>""".toByteArray()
        kmehrHelper.assertValidNotification(notification)
    }

    @Test
    fun validateNotification() {
        val notification = RecipeNotification().apply {
            text = "This is a notification"
            kmehrmessage = RecipeKmehrmessageType()
                .apply {
                header = RecipeheaderType().apply {
                    standard = StandardType().apply {
                        cd = CDSTANDARD().apply { s  = "CD-STANDARD"; sv = "1.19"; value = "20161201" }
                    }
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1" ; value = "11478761004.1490777844496" })
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sv = "4.0.3-on7ns4_e991da3"; sl = "ICure"; value = ""})
                    date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-03-29")
                    time = DatatypeFactory.newInstance().newXMLGregorianCalendar("10:57:24.490")
                    sender = SenderType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "11478761004" })
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = "74010414733" })
                            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY;sv = "1.9"; value = "persphysician" })
                            firstname = "Antoine"
                            familyname = "Duchâteau"
                        })
                        hcparties.add(HcpartyType().apply {
                            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.9"; value = "application" })
                            name = "ICure-4.0.3-on7ns4_e991da3"
                            telecoms.add(TelecomType().apply {
                                cds.add(CDTELECOM().apply { s = CDTELECOMschemes.CD_ADDRESS; sv = "1.1"; value = "work"})
                                cds.add(CDTELECOM().apply { s = CDTELECOMschemes.CD_TELECOM; sv = "1.0"; value = "phone"})
                                telecomnumber = "+3223335840"
                            })
                            telecoms.add(TelecomType().apply {
                                cds.add(CDTELECOM().apply { s = CDTELECOMschemes.CD_ADDRESS; sv = "1.1"; value = "work"})
                                cds.add(CDTELECOM().apply { s = CDTELECOMschemes.CD_TELECOM; sv = "1.0"; value = "email"})
                                telecomnumber = "info@taktik.be"
                            })
                        })
                    }
                    recipients.add(RecipientType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "RECIPE" })
                            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.9"; value = "orgpublichealth" })
                            name = "Recip-e"
                        })
                    })
                    folder = RecipefolderType().apply {
                        id = RecipebasicIDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0" ; value = "1" }
                        patient = RecipepatientpersonType().apply {
                            ids.add(IDPATIENT().apply { s = IDPATIENTschemes.ID_PATIENT; sv = "1.0"; value = "82051234978" })
                            firstnames.add("John")
                            familyname = "Doe"
                            birthdate = DateType().apply { date = DatatypeFactory.newInstance().newXMLGregorianCalendar("1997-03-29") }
                            sex = SexType().apply { cd = CDSEX().apply { s = "CD-SEX"; sv = "1.1"; value = CDSEXvalues.MALE } }
                        }
                        transaction = RecipetransactionType().apply {
                            id = RecipebasicIDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0" ; value = "1" }
                            cd = RecipeCDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; sv = "1.10"; value = "pharmaceuticalprescription" }
                            date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-03-29")
                            time = DatatypeFactory.newInstance().newXMLGregorianCalendar("10:57:24.514")
                            author = RecipeauthorType().apply {
                                hcparties.add(HcpartyType().apply {
                                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "11478761004" })
                                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.11"; value = "persphysician" })
                                    firstname = "Antoine"
                                    familyname = "Duchâteau"
                                    addresses.add(AddressType().apply {
                                        cds.add(CDADDRESS().apply { s = CDADDRESSschemes.CD_ADDRESS; sv = "1.1"; value = "11478761004" })
                                        country = CountryType().apply { cd = CDCOUNTRY().apply { s = CDCOUNTRYschemes.CD_COUNTRY; sv = "1.2"; value = "BE" } }
                                        zip = "1420"
                                        city = "Braine-l'Alleud"
                                        street = "av. de Finlande"
                                        housenumber = "8"
                                    })
                                    telecoms.add(TelecomType().apply {
                                        cds.add(CDTELECOM().apply { s = CDTELECOMschemes.CD_ADDRESS; sv = "1.1"; value = "work"})
                                        cds.add(CDTELECOM().apply { s = CDTELECOMschemes.CD_TELECOM; sv = "1.0"; value = "phone"})
                                        telecomnumber = "+3223335840"
                                    })
                                })
                                isIscomplete = true
                                isIsvalidated = true
								heading = RecipetransactionheadingType().apply {
                                    id = RecipebasicIDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0" ; value = "1" }
                                    cd = RecipeCDHEADING().apply { s = CDHEADINGschemes.CD_HEADING; sv = "1.2"; value = "prescription" }
									items.add(RecipeitemType().apply {
                                        id = RecipebasicIDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0" ; value = "1" }
                                        cd = RecipeCDITEM().apply { s = CDITEMschemes.CD_ITEM ; sv = "1.11"; value = "medication"}
                                        content = RecipecontentType().apply {
                                            medicinalproduct = RecipemedicinalProductType().apply {
                                                intendedcds.add(CDDRUGCNK().apply { s = CDDRUGCNKschemes.CD_CNK_CLUSTER; sv = "2.0"; value = "0318717"})
                                                intendedname = "Adalat Oros 30 (c) 30mg"
                                            }
                                        }
                                        beginmoment = RecipemomentType().apply { date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-03-29") }
                                        lifecycle = RecipelifecycleType().apply { cd = CDLIFECYCLE().apply { s = "CD-LIFECYCLE"; sv = "1.7"; value = CDLIFECYCLEvalues.PRESCRIBED } }
                                        quantity = RecipequantityType().apply {
                                            decimal = BigDecimal.ONE
                                            unit = UnitType().apply { cd = CDUNIT().apply { s = CDUNITschemes.CD_UNIT; sv = "1.0"; value = "pkg" } }
                                        }
                                        posology = RecipeitemType.RecipePosology()
                                            .apply { text = TextType().apply { l = "FR"; value = "2017-03-29" } }
                                        deliverydate = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-03-29")
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(RecipeNotification::class.java).createMarshaller().marshal(notification, os)
        val notificationBytes = os.toByteArray()
        try {
            kmehrHelper.assertValidNotification(notificationBytes)
        } catch (e: IntegrationModuleException) {
            println(String(notificationBytes))
            throw e
        }
    }
}
