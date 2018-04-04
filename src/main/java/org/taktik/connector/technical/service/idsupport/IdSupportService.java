package org.taktik.connector.technical.service.idsupport;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdRequest;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdResponse;

public interface IdSupportService {
   VerifyIdResponse verifyId(VerifyIdRequest var1) throws TechnicalConnectorException;

   VerifyIdResponse verifyId(String var1, Id var2, Id var3) throws TechnicalConnectorException;

   VerifyIdResponse verifyId(String var1, Id var2) throws TechnicalConnectorException;
}
