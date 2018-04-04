package org.taktik.connector.business.genericasync.session;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;

public interface GenAsyncService {
   PostResponse postRequest(Post var1, WsAddressingHeader var2) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   GetResponse getRequest(Get var1, WsAddressingHeader var2) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   ConfirmResponse confirmRequest(Confirm var1, WsAddressingHeader var2) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, SessionManagementException;
}
