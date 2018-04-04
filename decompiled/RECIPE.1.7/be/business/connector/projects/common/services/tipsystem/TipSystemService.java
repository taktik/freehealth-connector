package org.taktik.connector.business.recipeprojects.common.services.tipsystem;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.apb.gfddpp.services.tipsystem.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedCheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;

public interface TipSystemService {
   SimpleResponseType registerData(SealedMessageRequestType var1) throws IntegrationModuleException;

   SimpleResponseType updateData(SealedMessageRequestType var1) throws IntegrationModuleException;

   SimpleResponseType deleteData(SealedMessageRequestType var1) throws IntegrationModuleException;

   RoutedSealedResponseType getProductFilter(RoutedSealedRequestType var1) throws IntegrationModuleException;

   RoutedSealedResponseType getSystemServices(RoutedSealedRequestType var1) throws IntegrationModuleException;

   RoutedSealedResponseType retrieveStatusMessages(RoutedSealedRequestType var1) throws IntegrationModuleException;

   SimpleResponseType sendStatusMessages(SealedMessageRequestType var1) throws IntegrationModuleException;

   RoutedCheckAliveResponseType checkAliveTIP(CheckAliveRequestType var1) throws IntegrationModuleException;
}
