package org.taktik.connector.business.dmg.service;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse;

public interface DmgService {
   ConsultGlobalMedicalFileResponse consultGlobalMedicalFile(SAMLToken var1, ConsultGlobalMedicalFileRequest var2) throws DmgBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   NotifyGlobalMedicalFileResponse notifyGlobalMedicalFile(SAMLToken var1, NotifyGlobalMedicalFileRequest var2) throws DmgBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   PostResponse postRequest(SAMLToken var1, Post var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException;

   GetResponse getRequest(SAMLToken var1, Get var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   ConfirmResponse confirmRequest(SAMLToken var1, Confirm var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;
}
