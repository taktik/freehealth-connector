package be.vlaanderen.zg.vaccinnet.batchuploadtest.service

import be.vaccinnet.wupl.uplvaccinatiegegevens.GetResultaatVerwerkingVaccinatieGegevensRequest
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.SoapUtils
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
@Slf4j
class ResultaatVerwerkingVaccinatieGegevensService {
    private val webServiceTemplate: WebServiceTemplate? = null
    private val applicationArguments: ApplicationArguments? = null
    fun run() {
        val requestPayload: GetResultaatVerwerkingVaccinatieGegevensRequest =
            getResultaatVerwerkingVaccinatieGegevensRequest
        SoapUtils.validateMessage(requestPayload)
        webServiceTemplate.marshalSendAndReceive(requestPayload)
    }

    private val getResultaatVerwerkingVaccinatieGegevensRequest: GetResultaatVerwerkingVaccinatieGegevensRequest
        private get() {
            val request =
                GetResultaatVerwerkingVaccinatieGegevensRequest()
            val organisation: Organisation = XmlDataUtils.getOrganisation(applicationArguments)
            request.setGebruikersId(organisation.getUserCode())
            val requestId: String = ApplicationArgumentUtils.getRequestId(applicationArguments)
            request.setRequestId(requestId)
            return request
        }
}
