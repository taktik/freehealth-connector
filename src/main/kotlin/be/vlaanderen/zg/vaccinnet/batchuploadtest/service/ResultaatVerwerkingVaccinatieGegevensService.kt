package be.vlaanderen.zg.vaccinnet.batchuploadtest.service

import be.vaccinnet.wupl.uplvaccinatiegegevens.GetResultaatVerwerkingVaccinatieGegevensRequest
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Organisation
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.SoapUtils
import org.springframework.stereotype.Service
import org.springframework.ws.client.core.WebServiceTemplate

@Service
class ResultaatVerwerkingVaccinatieGegevensService(private val webServiceTemplate: WebServiceTemplate) {

    fun run(organisation: Organisation, requestId: String) {
        val requestPayload: GetResultaatVerwerkingVaccinatieGegevensRequest =
            getResultaatVerwerkingVaccinatieGegevensRequest(organisation, requestId)
        SoapUtils.validateMessage(requestPayload)
        webServiceTemplate.marshalSendAndReceive(requestPayload)
    }

        private fun getResultaatVerwerkingVaccinatieGegevensRequest(organisation: Organisation, requestId: String): GetResultaatVerwerkingVaccinatieGegevensRequest {
            val request = GetResultaatVerwerkingVaccinatieGegevensRequest()
            request.setGebruikersId(organisation.userCode)
            request.setRequestId(requestId)
            return request
        }
}
