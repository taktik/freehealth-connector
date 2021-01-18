package org.taktik.connector.business.consultrnv2.exception.ssinInformationservice;

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import org.taktik.connector.business.common.exception.EhealthServiceV2Exception;

public class ConsultCurrentSsinException extends EhealthServiceV2Exception {
    private static final long serialVersionUID = 1L;
    private final ConsultCurrentSsinResponse consultCurrentSsinResponse;

    public ConsultCurrentSsinException(ConsultCurrentSsinResponse response){
        super(response);
        this.consultCurrentSsinResponse = response;
    }

    public ConsultCurrentSsinResponse getConsultCurrentSsinResponse() { return consultCurrentSsinResponse; }
}
