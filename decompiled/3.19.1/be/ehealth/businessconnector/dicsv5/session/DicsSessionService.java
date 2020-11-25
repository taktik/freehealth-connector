package be.ehealth.businessconnector.dicsv5.session;

import be.ehealth.businessconnector.dicsv5.exception.DicsException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.dics.protocol.v5.FindAmpRequest;
import be.fgov.ehealth.dics.protocol.v5.FindAmpResponse;
import be.fgov.ehealth.dics.protocol.v5.FindAmppRequest;
import be.fgov.ehealth.dics.protocol.v5.FindAmppResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCommentedClassificationRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCommentedClassificationResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCompanyRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCompanyResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingFormulaRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingFormulaResponse;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingIngredientRequest;
import be.fgov.ehealth.dics.protocol.v5.FindCompoundingIngredientResponse;
import be.fgov.ehealth.dics.protocol.v5.FindLegislationTextRequest;
import be.fgov.ehealth.dics.protocol.v5.FindLegislationTextResponse;
import be.fgov.ehealth.dics.protocol.v5.FindListOfAmpRequest;
import be.fgov.ehealth.dics.protocol.v5.FindListOfAmpResponse;
import be.fgov.ehealth.dics.protocol.v5.FindNonMedicinalProductRequest;
import be.fgov.ehealth.dics.protocol.v5.FindNonMedicinalProductResponse;
import be.fgov.ehealth.dics.protocol.v5.FindReferencesRequest;
import be.fgov.ehealth.dics.protocol.v5.FindReferencesResponse;
import be.fgov.ehealth.dics.protocol.v5.FindReimbursementRequest;
import be.fgov.ehealth.dics.protocol.v5.FindReimbursementResponse;
import be.fgov.ehealth.dics.protocol.v5.FindVmpGroupRequest;
import be.fgov.ehealth.dics.protocol.v5.FindVmpGroupResponse;
import be.fgov.ehealth.dics.protocol.v5.FindVmpRequest;
import be.fgov.ehealth.dics.protocol.v5.FindVmpResponse;
import be.fgov.ehealth.dics.protocol.v5.FindVtmRequest;
import be.fgov.ehealth.dics.protocol.v5.FindVtmResponse;
import be.fgov.ehealth.dics.protocol.v5.ValidateProductIdRequest;
import be.fgov.ehealth.dics.protocol.v5.ValidateProductIdResponse;
import be.fgov.ehealth.dics.protocol.v5.ValidateSamIdRequest;
import be.fgov.ehealth.dics.protocol.v5.ValidateSamIdResponse;

public interface DicsSessionService {
   FindAmpResponse findAmp(FindAmpRequest var1) throws TechnicalConnectorException, DicsException;

   FindCompanyResponse findCompany(FindCompanyRequest var1) throws TechnicalConnectorException, DicsException;

   FindVmpResponse findVmp(FindVmpRequest var1) throws TechnicalConnectorException, DicsException;

   FindLegislationTextResponse findLegislationText(FindLegislationTextRequest var1) throws TechnicalConnectorException, DicsException;

   FindReimbursementResponse findReimbursement(FindReimbursementRequest var1) throws TechnicalConnectorException, DicsException;

   FindReferencesResponse findReferences(FindReferencesRequest var1) throws TechnicalConnectorException, DicsException;

   FindVmpGroupResponse findVmpGroup(FindVmpGroupRequest var1) throws TechnicalConnectorException, DicsException;

   FindVtmResponse findVtm(FindVtmRequest var1) throws TechnicalConnectorException, DicsException;

   FindCommentedClassificationResponse findCommentedClassification(FindCommentedClassificationRequest var1) throws TechnicalConnectorException, DicsException;

   FindCompoundingIngredientResponse findIngredient(FindCompoundingIngredientRequest var1) throws TechnicalConnectorException, DicsException;

   FindCompoundingFormulaResponse findFormula(FindCompoundingFormulaRequest var1) throws TechnicalConnectorException, DicsException;

   FindAmppResponse findAmpp(FindAmppRequest var1) throws TechnicalConnectorException, DicsException;

   FindNonMedicinalProductResponse findNonMedicinalProduct(FindNonMedicinalProductRequest var1) throws TechnicalConnectorException, DicsException;

   FindListOfAmpResponse findListOfAmp(FindListOfAmpRequest var1) throws TechnicalConnectorException, DicsException;

   ValidateSamIdResponse validateSamId(ValidateSamIdRequest var1) throws TechnicalConnectorException, DicsException;

   ValidateProductIdResponse validateProductId(ValidateProductIdRequest var1) throws TechnicalConnectorException, DicsException;
}
