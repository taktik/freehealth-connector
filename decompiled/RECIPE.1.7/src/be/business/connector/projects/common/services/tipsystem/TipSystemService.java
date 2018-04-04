package org.taktik.connector.business.recipeprojects.common.services.tipsystem;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.apb.gfddpp.services.tipsystem.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedCheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;

public interface TipSystemService {
	  SimpleResponseType registerData(SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException;
	  SimpleResponseType updateData(SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException;
	  SimpleResponseType deleteData(SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException;
	  RoutedSealedResponseType getProductFilter(RoutedSealedRequestType paramRoutedSealedRequestType) throws IntegrationModuleException;
	  RoutedSealedResponseType getSystemServices(RoutedSealedRequestType paramRoutedSealedRequestType) throws IntegrationModuleException;
	  RoutedSealedResponseType retrieveStatusMessages(RoutedSealedRequestType paramRoutedSealedRequestType) throws IntegrationModuleException;
	  SimpleResponseType sendStatusMessages(SealedMessageRequestType paramSealedMessageRequestType) throws IntegrationModuleException;
	  RoutedCheckAliveResponseType checkAliveTIP(CheckAliveRequestType paramCheckAliveRequestType) throws IntegrationModuleException;
}
