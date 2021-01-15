package org.taktik.connector.business.consultrnv2.exception.ssinInformationservice;

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;
import org.taktik.connector.business.common.exception.EhealthServiceV2Exception;

public class ConsultRelatedSsinsException extends EhealthServiceV2Exception {
    private static final long serialVersionUID = 1L;
    private final ConsultRelatedSsinsResponse consultRelatedSsinsResponse;

    public ConsultRelatedSsinsException(ConsultRelatedSsinsResponse response){
        super(response.getStatus());
        this.consultRelatedSsinsResponse = response;
    }

    public ConsultRelatedSsinsResponse getConsultRelatedSsinsResponse() { return consultRelatedSsinsResponse; }
}
