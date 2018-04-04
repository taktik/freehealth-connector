package org.taktik.connector.business.genericasync.service;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface GenAsyncService {
   PostResponse postRequest(SAMLToken var1, Post var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException;

   GetResponse getRequest(SAMLToken var1, Get var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   ConfirmResponse confirmRequest(SAMLToken var1, Confirm var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;
}
