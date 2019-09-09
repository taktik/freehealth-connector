package org.taktik.connector.technical.service.idsupport;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdRequest;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdResponse;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface IdSupportService {
   VerifyIdResponse verifyId(VerifyIdRequest request, SAMLToken samlToken) throws TechnicalConnectorException;

   VerifyIdResponse verifyId(String legalContext, Id ssin, Id cardNumber, SAMLToken samlToken) throws TechnicalConnectorException;

   VerifyIdResponse verifyId(String legalContext, Id barcode, SAMLToken samlToken) throws TechnicalConnectorException;
}
