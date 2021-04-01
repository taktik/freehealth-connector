package be.ehealth.businessconnector.civicsv2.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.samcivics.schemas.v2.FindCNKRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindCNKResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphTextRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindParagraphTextResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindPublicCNKRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindPublicCNKResponse;
import be.fgov.ehealth.samcivics.schemas.v2.FindReimbursementConditionsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.FindReimbursementConditionsResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetAddedDocumentsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetAddedDocumentsResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphExclusionsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphExclusionsResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphIncludedSpecialtiesRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetParagraphIncludedSpecialtiesResponse;
import be.fgov.ehealth.samcivics.schemas.v2.GetProfessionalAuthorizationsRequest;
import be.fgov.ehealth.samcivics.schemas.v2.GetProfessionalAuthorizationsResponse;

public interface CivicsSessionService {
   FindParagraphTextResponse findParagraphText(FindParagraphTextRequest var1) throws TechnicalConnectorException;

   GetParagraphExclusionsResponse getParagraphExclusions(GetParagraphExclusionsRequest var1) throws TechnicalConnectorException;

   GetParagraphIncludedSpecialtiesResponse getParagraphIncludedSpecialities(GetParagraphIncludedSpecialtiesRequest var1) throws TechnicalConnectorException;

   GetProfessionalAuthorizationsResponse getProfessionalAuthorizations(GetProfessionalAuthorizationsRequest var1) throws TechnicalConnectorException;

   GetAddedDocumentsResponse getAddedDocuments(GetAddedDocumentsRequest var1) throws TechnicalConnectorException;

   FindReimbursementConditionsResponse findReimbursementConditions(FindReimbursementConditionsRequest var1) throws TechnicalConnectorException;

   FindParagraphResponse findParagraph(FindParagraphRequest var1) throws TechnicalConnectorException;

   FindCNKResponse findCNK(FindCNKRequest var1) throws TechnicalConnectorException;

   FindPublicCNKResponse findPublicCNK(FindPublicCNKRequest var1) throws TechnicalConnectorException;
}
