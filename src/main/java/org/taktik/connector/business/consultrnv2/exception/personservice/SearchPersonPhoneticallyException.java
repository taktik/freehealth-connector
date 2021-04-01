package org.taktik.connector.business.consultrnv2.exception.personservice;

import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;
import org.taktik.connector.business.common.exception.EhealthServiceV2Exception;

public class SearchPersonPhoneticallyException extends EhealthServiceV2Exception {
    private static final long serialVersionUID = 1L;
    private final SearchPersonPhoneticallyResponse searchPersonPhoneticallyResponse;

    public SearchPersonPhoneticallyException(SearchPersonPhoneticallyResponse response){
        super(response);
        this.searchPersonPhoneticallyResponse = response;
    }
    public SearchPersonPhoneticallyResponse getSearchPersonPhoneticallyResponse() { return searchPersonPhoneticallyResponse; }
}
