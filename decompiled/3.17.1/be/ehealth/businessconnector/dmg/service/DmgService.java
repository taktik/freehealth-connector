package be.ehealth.businessconnector.dmg.service;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.dmg.exception.DmgBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
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
