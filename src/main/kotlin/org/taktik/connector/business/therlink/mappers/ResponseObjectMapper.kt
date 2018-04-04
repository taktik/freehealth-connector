package org.taktik.connector.business.therlink.mappers

import be.fgov.ehealth.hubservices.core.v2.AcknowledgeType
import be.fgov.ehealth.hubservices.core.v2.HCPartyIdType
import be.fgov.ehealth.hubservices.core.v2.PatientIdType
import be.fgov.ehealth.hubservices.core.v2.ProofType
import be.fgov.ehealth.hubservices.core.v2.RequestType
import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkWithOperationContext
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.slf4j.LoggerFactory
import org.taktik.connector.business.common.domain.Patient
import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.HcParty
import org.taktik.connector.business.therlink.domain.OperationContext
import org.taktik.connector.business.therlink.domain.Proof
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import org.taktik.connector.business.therlink.domain.requests.BinaryProof
import org.taktik.connector.business.therlink.domain.requests.GetTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.PutTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.RevokeTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.responses.Acknowledge
import org.taktik.connector.business.therlink.domain.responses.GetTherapeuticLinkResponse
import org.taktik.connector.business.therlink.domain.responses.PutTherapeuticLinkResponse
import org.taktik.connector.business.therlink.domain.responses.RevokeTherapeuticLinkResponse
import org.taktik.connector.business.therlink.domain.responses.TherapeuticLinkResponse
import org.taktik.connector.business.therlink.domain.responses.TherapeuticLinkResponseError
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.MarshallerHelper

class ResponseObjectMapper {
	private val LOG = LoggerFactory.getLogger(ResponseObjectMapper::class.java)

	fun mapXMLToPutTherapeuticLinkResponse(xml: String) =
			this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse::class.java)?.let {
				PutTherapeuticLinkResponse(DateTime(it.response.date.year, it.response.date.monthOfYear, it.response.date.dayOfMonth, it.response.time.hourOfDay, it.response.time.minuteOfHour, it.response.time.secondOfMinute, it.response.time.millisOfSecond, DateTimeZone.getDefault()),
						this.mapAuthor(it.response.author), it.response.id.value, this.mapOriginalPutTherapeuticLinkRequest(it.response.request), this.mapAcknowledge(it.acknowledge))
			}

	fun mapXMLToRevokeTherapeuticLinkResponse(xml: String) =
			this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse::class.java)?.let {
				RevokeTherapeuticLinkResponse(DateTime(it.response.date.year, it.response.date.monthOfYear, it.response.date.dayOfMonth, it.response.time.hourOfDay, it.response.time.minuteOfHour, it.response.time.secondOfMinute, it.response.time.millisOfSecond, DateTimeZone.getDefault()),
						this.mapAuthor(it.response.author), it.response.id.value, this.mapOriginalRevokeTherapeuticLinkRequest(it.response.request), this.mapAcknowledge(it.acknowledge))
			}

	fun mapXMLToGetTherapeuticLinkResponse(xml: String) =
			this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse::class.java)?.let {
				GetTherapeuticLinkResponse(DateTime(it.response.date.year, it.response.date.monthOfYear, it.response.date.dayOfMonth, it.response.time.hourOfDay, it.response.time.minuteOfHour, it.response.time.secondOfMinute, it.response.time.millisOfSecond, DateTimeZone.getDefault()),
						this.mapAuthor(it.response.author), it.response.id.value, this.mapOriginalGetTherapeuticLinkRequest(it.response.request), it.therapeuticlinklist?.therapeuticlinks?.map { mapTherapeuticLinkResponse(it) } ?: ArrayList(), this.mapAcknowledge(it.acknowledge))

			}

	fun mapJaxbToPutTherapeuticLinkResponse(jxbResp: be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse) =
			PutTherapeuticLinkResponse(mapDateTime(jxbResp.response.date, jxbResp.response.time), this.mapAuthor(jxbResp.response.author), jxbResp.response.id.value, this.mapOriginalPutTherapeuticLinkRequest(jxbResp.response.request), this.mapAcknowledge(jxbResp.acknowledge))

	fun mapJaxbToGetTherapeuticLinkResponse(jxbResp: be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse) =
			GetTherapeuticLinkResponse(mapDateTime(jxbResp.response.date, jxbResp.response.time), mapAuthor(jxbResp.response.author), jxbResp.response.id.value, mapOriginalPutTherapeuticLinkRequest(jxbResp.response.request), jxbResp.therapeuticlinklist?.therapeuticlinks?.map { mapTherapeuticLinkResponse(it) } ?: ArrayList(), mapAcknowledge(jxbResp.acknowledge))

	fun mapJaxbToRevokeTherapeuticLinkResponse(jxbResp: be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse) =
			RevokeTherapeuticLinkResponse(mapDateTime(jxbResp.response.date, jxbResp.response.time), this.mapAuthor(jxbResp.response.author), jxbResp.response.id.value, this.mapOriginalPutTherapeuticLinkRequest(jxbResp.response.request), this.mapAcknowledge(jxbResp.acknowledge))

	fun mapAcknowledge(acknowledge: AcknowledgeType) = Acknowledge().apply {
		isComplete = acknowledge.isIscomplete
		listOfErrors.addAll(acknowledge.errors.map {
			TherapeuticLinkResponseError().apply {
				errorCode = it.cds.firstOrNull()?.value
				errorDescription = it.description.value
			}
		})
	}

	private fun mapTherapeuticLinkResponse(therLink: TherapeuticLinkWithOperationContext) =
			TherapeuticLinkResponse(mapPatient(therLink.patient), therLink.hcparties.first().let { mapHcParty(it) }, therLink.cd.value).apply {
				startDate = therLink.startdate?.let { LocalDate(it.year, it.monthOfYear, it.dayOfMonth) }
				endDate = therLink.enddate?.let { LocalDate(it.year, it.monthOfYear, it.dayOfMonth) }
				comment = therLink.comment
				operationContexts = ArrayList(therLink.operationcontexts.map { OperationContext(it.recorddatetime, it.operation.value(), mapAuthor(it.author.author), ArrayList(mapListOfProof(it.prooves))) })
			}

	private fun mapListOfProof(prooves: List<ProofType>) = prooves.map { Proof(it.cd.value).apply { binaryProof = if (it.binaryproof == null) null else BinaryProof(it.binaryproof.cd.value.value(), it.binaryproof.base64EncryptedValue.value) } }

	@Suppress("DEPRECATION")
	private fun mapHcParty(hcparty: HCPartyIdType) = HcParty().apply {
		firstName = hcparty.firstname; familyName = hcparty.familyname; name = hcparty.name; type = hcparty.cd.value
		ids.addAll(hcparty.ids.map {
			when(it.s) {
				IDHCPARTYschemes.INSS -> inss = it.value
				IDHCPARTYschemes.ID_HCPARTY -> if ("application" == hcparty.cd.value) cbe = it.value else nihii = it.value
				else -> if ("application_ID" == it.sl) applicationID = it.value
			}
			it
		})
	}

	@Suppress("DEPRECATION")
	private fun mapHcParty(hcparty: HcpartyType) = HcParty().apply {
		firstName = hcparty.firstname; familyName = hcparty.familyname; name = hcparty.name
		val type = hcparty.cds.firstOrNull()?.value ?: ""
		this.type = type
		ids.addAll(hcparty.ids)
		cds.addAll(hcparty.cds)

		hcparty.ids.forEach { id ->
			when {
				id.s == IDHCPARTYschemes.ID_HCPARTY -> if ("application" != type && "application_ID" != type) {
					if ("orgpublichealth" == type) ehp = id.value else nihii = id.value
				} else {
					cbe = id.value
				}
				id.s == IDHCPARTYschemes.INSS -> inss = id.value
				("application_ID" == id.sl || "application id" == id.sl) && "application" == type -> applicationID = id.value
				"orgpublichealth" == type || "eHP application id" == id.sl -> ehp = id.value
			}
		}
	}

	private fun mapPatient(patientIdType: PatientIdType) = Patient().apply {
		lastName = patientIdType.familyname
		firstName = patientIdType.firstname
		middleName = patientIdType.name
		patientIdType.ids.forEach {
			when(it.s) {
				IDPATIENTschemes.EID_CARDNO -> eidCardNumber = it.value
				IDPATIENTschemes.ISI_CARDNO -> isiCardNumber = it.value
				IDPATIENTschemes.SIS_CARDNO -> sisCardNumber = it.value
				IDPATIENTschemes.INSS -> inss = it.value
				else -> {}
			}
		}
	}

	private fun mapOriginalPutTherapeuticLinkRequest(request: RequestType) =
			PutTherapeuticLinkRequest(DateTime(request.date.year, request.date.monthOfYear, request.date.dayOfMonth, request.time.hourOfDay, request.time.minuteOfHour, request.time.secondOfMinute, request.time.millisOfSecond), request.id.value, this.mapAuthor(request.author))

	private fun mapOriginalRevokeTherapeuticLinkRequest(request: RequestType) =
			RevokeTherapeuticLinkRequest(DateTime(request.date.year, request.date.monthOfYear, request.date.dayOfMonth, request.time.hourOfDay, request.time.minuteOfHour, request.time.secondOfMinute, request.time.millisOfSecond),
					request.id.value, this.mapAuthor(request.author), null)

	private fun mapOriginalGetTherapeuticLinkRequest(request: RequestType) =
			GetTherapeuticLinkRequest(DateTime(request.date.year, request.date.monthOfYear, request.date.dayOfMonth, request.time.hourOfDay, request.time.minuteOfHour, request.time.secondOfMinute, request.time.millisOfSecond),
					request.id.value, this.mapAuthor(request.author), null, 999)

	private fun mapAuthor(authorType: AuthorType) = Author().apply { hcParties.addAll(authorType.hcparties.map { mapHcParty(it) }) }

	private fun mapDateTime(date: DateTime?, time: DateTime) =
			DateTime(date?.year ?: 0, date?.monthOfYear ?: 1, date?.dayOfMonth
					?: 1, time.hourOfDay, time.minuteOfHour, time.secondOfMinute, time.millisOfSecond, DateTimeZone.getDefault())

	@Throws(TechnicalConnectorException::class)
	fun <T> generateJAXB(request: String, clazz: Class<T>): T? {
		val marshaller = MarshallerHelper(clazz, clazz)
		LOG.info("XML input : $request")
		return marshaller.toObject(request)
	}
}
