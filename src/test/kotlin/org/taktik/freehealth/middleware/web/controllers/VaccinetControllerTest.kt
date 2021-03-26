package org.taktik.freehealth.middleware.web.controllers


import be.fgov.ehealth.standards.kmehr.cd.v1.*
import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import be.fgov.ehealth.standards.kmehr.id.v1.*
import be.fgov.ehealth.standards.kmehr.schema.v1.*
import be.fgov.ehealth.standards.kmehr.schema.v1.DateType

import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.utils.FuzzyValues
import java.math.BigDecimal
import java.util.*
import javax.xml.datatype.DatatypeFactory


@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class VaccinetControllerTest : EhealthTest(){

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun getVaccinations(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssinVaccinet!!, passwordVaccinet!!)
        val patientId = "72022102793"
        val vaccinations =  this.restTemplate.exchange("http://localhost:$port/vaccinnet/$patientId?softwareId=taktik_medispring&vaccinnetId=${nihiiVaccinet}&since=20200101", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(vaccinations != null)
    }

    @Test
    fun deleteMessage(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssinVaccinet!!, passwordVaccinet!!)
        val patientId = ""
        val vaccinationId = "123"
        val vaccinations =  this.restTemplate.exchange("http://localhost:$port/vaccinnet/$patientId/$vaccinationId?softwareId=taktik_medispring&vaccinnetId=${nihiiVaccinet}", HttpMethod.DELETE, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(vaccinations != null)
    }

    @Test
    fun addVaccination(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssinVaccinet!!, passwordVaccinet!!)
        val patientId = "72022102793"
        val patientName = "Sam"
        val patientLastName = "Jocqué"
        val msg = Kmehrmessage().apply {
            header = HeaderType().apply {
                standard = StandardType().apply {
                    cd = CDSTANDARD().apply { s  = "CD-STANDARD"; sv = "1.5"; value = "20120701" }
                }
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1" ; value = "07142016083331.1" })
                date = DateTime.now()
                time = DateTime.now()
                sender = SenderType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "11478761004" })
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY;sv = "1.0"; value = "persphysician" })
                        name = "Veerle Piessens"
                    })
                }
                recipients.add(RecipientType().apply {
                    hcparties.add(HcpartyType().apply {
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.0"; value = "orgpublichealth" })
                        name = "VAZG"
                    })
                })
            }
            folders.add(
                FolderType().apply {
                    ids.add(IDKMEHR().apply { s=IDKMEHRschemes.ID_KMEHR; sv="1"; value="1" })
                    patient = PersonType().apply{
                        ids.add(IDPATIENT().apply{s=IDPATIENTschemes.ID_PATIENT; sv="1.0"; value=patientId})
                        firstnames.add(patientName)
                        familyname = patientLastName
                        birthdate = DateType().apply { date = DateTime(1972,2,21,0,0)}
                        sex = SexType().apply { cd = CDSEX().apply { s= "CD-SEX"; sv="1.0"; value=CDSEXvalues.MALE } }
                    }
                    transactions.add(
                        TransactionType().apply {
                            ids.add(IDKMEHR().apply { s=IDKMEHRschemes.ID_KMEHR; sv="1"; value="1" })
                            cds.add(CDTRANSACTION().apply {s=CDTRANSACTIONschemes.CD_TRANSACTION; sv="1.0" })
                            date = DateTime.now()
                            time = DateTime.now()
                            author = AuthorType().apply {
                                hcparties.add(HcpartyType().apply {
                                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "11478761004" })
                                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY;sv = "1.0"; value = "persphysician" })
                                    name = "Veerle Piessens"
                                })
                            }
                            isIscomplete = true
                            isIsvalidated = true
                            item.add(ItemType().apply {
                                ids.add(IDKMEHR().apply { s=IDKMEHRschemes.ID_KMEHR; sv="1"; value="1" })
                                cds.add(CDITEM().apply { s=CDITEMschemes.CD_ITEM ;sv="1.0"; value="vaccine" })
                                contents.add(ContentType().apply {
                                    medicinalproduct = MedicinalProductType().apply {
                                        intendedcds.add(CDDRUGCNK().apply { s = CDDRUGCNKschemes.CD_DRUG_CNK; sv="1.0"; value="1665363" })
                                        intendedname = "INFANRIX HEXA DOS 1 SUSP INJ IM 0,5"
                                    }
                                })
                                beginmoment = MomentType().apply { date = DateTime(2021,3,25,0,0) }
                                quantity = QuantityType().apply { decimal = BigDecimal.ONE }
                                batch = "LTNR123"
                            })
                        }
                    )
                }
            )
        }
        val message = this.restTemplate.exchange("http://localhost:$port/vaccinnet/$patientId?softwareId=taktik_medispring&vaccinnetId=${nihiiVaccinet}", HttpMethod.POST, HttpEntity<Kmehrmessage>(msg, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        Assertions.assertThat(message != null)
    }

}
