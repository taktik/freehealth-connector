package org.taktik.connector.business.dmg.session;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse;

public interface DmgService {
   ConsultGlobalMedicalFileResponse consultGlobalMedicalFile(ConsultGlobalMedicalFileRequest var1) throws DmgBusinessConnectorException, TechnicalConnectorException;

   NotifyGlobalMedicalFileResponse notifyGlobalMedicalFile(NotifyGlobalMedicalFileRequest var1) throws DmgBusinessConnectorException, TechnicalConnectorException;

   PostResponse postRequest(Post var1, WsAddressingHeader var2) throws GenAsyncBusinessConnectorException;

   GetResponse getRequest(Get var1, WsAddressingHeader var2) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   ConfirmResponse confirmRequest(Confirm var1, WsAddressingHeader var2) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;
}
