package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.chapter4.AgreementResponse
import org.taktik.connector.business.domain.chapter4.Appendix
import org.taktik.freehealth.middleware.drugs.civics.AddedDocumentPreview
import org.taktik.freehealth.middleware.drugs.civics.ParagraphInfos
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import java.util.UUID

interface Chapter4Service {
    fun getAddedDocuments(chapterName: String, paragraphName: String): List<AddedDocumentPreview>
    fun findParagraphs(searchString: String, language: String): List<ParagraphPreview>
    fun findParagraphsWithCnk(cnk: Long?, language: String): List<ParagraphPreview>
    fun agreementRequestsConsultation(keystoreId: UUID,
                                      tokenId: UUID,
                                      hcpNihii: String,
                                      hcpSsin: String,
                                      hcpFirstName: String,
                                      hcpLastName: String,
                                      passPhrase: String,
                                      patientSsin: String,
                                      patientDateOfBirth: Long,
                                      patientFirstName: String,
                                      patientLastName: String,
                                      patientGender: String,
                                      civicsVersion: String?,
                                      paragraph: String?,
                                      start: Long,
                                      end: Long?,
                                      reference: String?): AgreementResponse
    fun requestAgreement(keystoreId: UUID,
                         tokenId: UUID,
                         hcpNihii: String,
                         hcpSsin: String,
                         hcpFirstName: String,
                         hcpLastName: String,
                         passPhrase: String,
                         patientSsin: String,
                         patientDateOfBirth: Long,
                         patientFirstName: String,
                         patientLastName: String,
                         patientGender: String,
                         requestType: org.taktik.connector.business.domain.chapter4.RequestType,
                         civicsVersion: String,
                         paragraph: String,
                         appendices: List<Appendix>,
                         verses: List<String>?,
                         incomplete: Boolean,
                         start: Long,
                         end: Long?,
                         decisionReference: String?,
                         ioRequestReference: String?): AgreementResponse
    fun cancelAgreement(keystoreId: UUID,
                        tokenId: UUID,
                        hcpNihii: String,
                        hcpSsin: String,
                        hcpFirstName: String,
                        hcpLastName: String,
                        passPhrase: String,
                        patientSsin: String,
                        patientDateOfBirth: Long,
                        patientFirstName: String,
                        patientLastName: String,
                        patientGender: String,
                        decisionReference: String?,
                        iorequestReference: String?): AgreementResponse
    fun closeAgreement(keystoreId: UUID,
                       tokenId: UUID,
                       hcpNihii: String,
                       hcpSsin: String,
                       hcpFirstName: String,
                       hcpLastName: String,
                       passPhrase: String,
                       patientSsin: String,
                       patientDateOfBirth: Long,
                       patientFirstName: String,
                       patientLastName: String,
                       patientGender: String,
                       decisionReference: String): AgreementResponse

    fun getParagraphInfos(chapterName: String, paragraphName: String): ParagraphInfos?
}
