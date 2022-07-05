package be.ehealth.businessconnector.dmg.session;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.dmg.exception.DmgBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
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
