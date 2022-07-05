package be.ehealth.businessconnector.genericasync.service;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;

public interface GenAsyncService {
   PostResponse postRequest(SAMLToken var1, Post var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException;

   GetResponse getRequest(SAMLToken var1, Get var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   ConfirmResponse confirmRequest(SAMLToken var1, Confirm var2, WsAddressingHeader var3) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;
}
