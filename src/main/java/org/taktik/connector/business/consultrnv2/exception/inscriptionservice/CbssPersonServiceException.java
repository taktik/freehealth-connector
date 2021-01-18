package org.taktik.connector.business.consultrnv2.exception.inscriptionservice;

import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;
import org.taktik.connector.business.common.exception.EhealthServiceV2Exception;

public class CbssPersonServiceException extends EhealthServiceV2Exception {
    private static final long serialVersionUID = 1L;
    private final RegisterPersonResponse registerPersonResponse;

    public CbssPersonServiceException(RegisterPersonResponse response){
        super(response);
        this.registerPersonResponse = response;
    }

    public RegisterPersonResponse getRegisterPersonResponse() { return registerPersonResponse; }

}
