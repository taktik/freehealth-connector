package org.taktik.connector.business.consultrnv2.exception.personservice;

import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import org.taktik.connector.business.common.exception.EhealthServiceV2Exception;

public class SearchPersonBySsinException extends EhealthServiceV2Exception {
    private static final long serialVersionUID = 1L;
    private final SearchPersonBySsinResponse searchPersonBySsinResponse;

    public SearchPersonBySsinException(SearchPersonBySsinResponse response){
        super(response.getStatus());
        this.searchPersonBySsinResponse = response;
    }

    public SearchPersonBySsinResponse getSearchPersonBySsinResponse() { return searchPersonBySsinResponse; }
}
