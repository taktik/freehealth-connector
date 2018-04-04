package org.taktik.connector.business.recipeprojects.common.services.tipsystem;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.apb.gfddpp.services.tipsystem.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedCheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface TipSystemService {
	  SimpleResponseType registerData(SAMLToken samlToken, Credential credential, SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  SimpleResponseType updateData(SAMLToken samlToken, Credential credential, SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  SimpleResponseType deleteData(SAMLToken samlToken, Credential credential, SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  RoutedSealedResponseType getProductFilter(SAMLToken samlToken, Credential credential, RoutedSealedRequestType paramRoutedSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  RoutedSealedResponseType getSystemServices(SAMLToken samlToken, Credential credential, RoutedSealedRequestType paramRoutedSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  RoutedSealedResponseType retrieveStatusMessages(SAMLToken samlToken, Credential credential, RoutedSealedRequestType paramRoutedSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  SimpleResponseType sendStatusMessages(SAMLToken samlToken, Credential credential, SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  RoutedCheckAliveResponseType checkAliveTIP(SAMLToken samlToken, Credential credential, CheckAliveRequestType paramCheckAliveRequestType) throws IntegrationModuleException, TechnicalConnectorException;
}
