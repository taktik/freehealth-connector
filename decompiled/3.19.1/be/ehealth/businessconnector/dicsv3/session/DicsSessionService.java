package be.ehealth.businessconnector.dicsv3.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.dics.protocol.v3.FindAmpRequest;
import be.fgov.ehealth.dics.protocol.v3.FindAmpResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCommentedClassificationRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCommentedClassificationResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCompanyRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCompanyResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingFormulaRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingFormulaResponse;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingIngredientRequest;
import be.fgov.ehealth.dics.protocol.v3.FindCompoundingIngredientResponse;
import be.fgov.ehealth.dics.protocol.v3.FindLegislationTextRequest;
import be.fgov.ehealth.dics.protocol.v3.FindLegislationTextResponse;
import be.fgov.ehealth.dics.protocol.v3.FindReferencesRequest;
import be.fgov.ehealth.dics.protocol.v3.FindReferencesResponse;
import be.fgov.ehealth.dics.protocol.v3.FindReimbursementRequest;
import be.fgov.ehealth.dics.protocol.v3.FindReimbursementResponse;
import be.fgov.ehealth.dics.protocol.v3.FindVmpGroupRequest;
import be.fgov.ehealth.dics.protocol.v3.FindVmpGroupResponse;
import be.fgov.ehealth.dics.protocol.v3.FindVmpRequest;
import be.fgov.ehealth.dics.protocol.v3.FindVmpResponse;
import be.fgov.ehealth.dics.protocol.v3.FindVtmRequest;
import be.fgov.ehealth.dics.protocol.v3.FindVtmResponse;

public interface DicsSessionService {
   FindAmpResponse findAmp(FindAmpRequest var1) throws TechnicalConnectorException;

   FindCommentedClassificationResponse findCommentedClassification(FindCommentedClassificationRequest var1) throws TechnicalConnectorException;

   FindCompanyResponse findCompany(FindCompanyRequest var1) throws TechnicalConnectorException;

   FindCompoundingIngredientResponse findIngredient(FindCompoundingIngredientRequest var1) throws TechnicalConnectorException;

   FindVmpGroupResponse findVmpGroup(FindVmpGroupRequest var1) throws TechnicalConnectorException;

   FindVtmResponse findVtm(FindVtmRequest var1) throws TechnicalConnectorException;

   FindCompoundingFormulaResponse findFormula(FindCompoundingFormulaRequest var1) throws TechnicalConnectorException;

   FindLegislationTextResponse findLegislationText(FindLegislationTextRequest var1) throws TechnicalConnectorException;

   FindReferencesResponse findReferences(FindReferencesRequest var1) throws TechnicalConnectorException;

   FindReimbursementResponse findReimbursement(FindReimbursementRequest var1) throws TechnicalConnectorException;

   FindVmpResponse findVmp(FindVmpRequest var1) throws TechnicalConnectorException;
}
