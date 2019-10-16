package be.fgov.ehealth.hubservices.core.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _Therapeuticlink_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/core/v2", "therapeuticlink");

   public TherapeuticLinkType createTherapeuticLinkType() {
      return new TherapeuticLinkType();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public AuthorWithPatientAndPersonType createAuthorWithPatientAndPersonType() {
      return new AuthorWithPatientAndPersonType();
   }

   public AcknowledgeType createAcknowledgeType() {
      return new AcknowledgeType();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }

   public PatientIdType createPatientIdType() {
      return new PatientIdType();
   }

   public HCPartyIdType createHCPartyIdType() {
      return new HCPartyIdType();
   }

   public TransactionIdType createTransactionIdType() {
      return new TransactionIdType();
   }

   public ConsentType createConsentType() {
      return new ConsentType();
   }

   public ConsentWithStatusType createConsentWithStatusType() {
      return new ConsentWithStatusType();
   }

   public ConsentHCPartyType createConsentHCPartyType() {
      return new ConsentHCPartyType();
   }

   public TherapeuticLinkListType createTherapeuticLinkListType() {
      return new TherapeuticLinkListType();
   }

   public TherapeuticLinkWithOperationContext createTherapeuticLinkWithOperationContext() {
      return new TherapeuticLinkWithOperationContext();
   }

   public OperationContextType createOperationContextType() {
      return new OperationContextType();
   }

   public AccessRightType createAccessRightType() {
      return new AccessRightType();
   }

   public AccessRightListType createAccessRightListType() {
      return new AccessRightListType();
   }

   public SelectGetTransactionListType createSelectGetTransactionListType() {
      return new SelectGetTransactionListType();
   }

   public BasicTransactionWithPeriodType createBasicTransactionWithPeriodType() {
      return new BasicTransactionWithPeriodType();
   }

   public SelectGetTransactionType createSelectGetTransactionType() {
      return new SelectGetTransactionType();
   }

   public BasicTransactionType createBasicTransactionType() {
      return new BasicTransactionType();
   }

   public SelectRevokeTransactionType createSelectRevokeTransactionType() {
      return new SelectRevokeTransactionType();
   }

   public SelectGetPatientType createSelectGetPatientType() {
      return new SelectGetPatientType();
   }

   public SelectGetHCPartyType createSelectGetHCPartyType() {
      return new SelectGetHCPartyType();
   }

   public SelectGetHCPartyConsentType createSelectGetHCPartyConsentType() {
      return new SelectGetHCPartyConsentType();
   }

   public SelectGetHCPartyPatientConsentType createSelectGetHCPartyPatientConsentType() {
      return new SelectGetHCPartyPatientConsentType();
   }

   public GetTherapeuticLinkSelectType createGetTherapeuticLinkSelectType() {
      return new GetTherapeuticLinkSelectType();
   }

   public SelectGetAccessRightType createSelectGetAccessRightType() {
      return new SelectGetAccessRightType();
   }

   public SelectRevokeAccessRightType createSelectRevokeAccessRightType() {
      return new SelectRevokeAccessRightType();
   }

   public BasicHcPartyType createBasicHcPartyType() {
      return new BasicHcPartyType();
   }

   public SelectGetPatientAuditTrailType createSelectGetPatientAuditTrailType() {
      return new SelectGetPatientAuditTrailType();
   }

   public SelectRequestPublicationType createSelectRequestPublicationType() {
      return new SelectRequestPublicationType();
   }

   public HasTherapeuticLinkSelectType createHasTherapeuticLinkSelectType() {
      return new HasTherapeuticLinkSelectType();
   }

   public DeclareTransactionRequest createDeclareTransactionRequest() {
      return new DeclareTransactionRequest();
   }

   public DeclareTransactionResponse createDeclareTransactionResponse() {
      return new DeclareTransactionResponse();
   }

   public PutTransactionRequest createPutTransactionRequest() {
      return new PutTransactionRequest();
   }

   public PutTransactionResponse createPutTransactionResponse() {
      return new PutTransactionResponse();
   }

   public GetTransactionListRequest createGetTransactionListRequest() {
      return new GetTransactionListRequest();
   }

   public GetTransactionListResponse createGetTransactionListResponse() {
      return new GetTransactionListResponse();
   }

   public GetTransactionRequest createGetTransactionRequest() {
      return new GetTransactionRequest();
   }

   public GetTransactionResponse createGetTransactionResponse() {
      return new GetTransactionResponse();
   }

   public RevokeTransactionRequest createRevokeTransactionRequest() {
      return new RevokeTransactionRequest();
   }

   public RevokeTransactionResponse createRevokeTransactionResponse() {
      return new RevokeTransactionResponse();
   }

   public PutPatientRequest createPutPatientRequest() {
      return new PutPatientRequest();
   }

   public PutPatientResponse createPutPatientResponse() {
      return new PutPatientResponse();
   }

   public GetPatientRequest createGetPatientRequest() {
      return new GetPatientRequest();
   }

   public GetPatientResponse createGetPatientResponse() {
      return new GetPatientResponse();
   }

   public PutPatientConsentRequest createPutPatientConsentRequest() {
      return new PutPatientConsentRequest();
   }

   public PutPatientConsentResponse createPutPatientConsentResponse() {
      return new PutPatientConsentResponse();
   }

   public GetPatientConsentRequest createGetPatientConsentRequest() {
      return new GetPatientConsentRequest();
   }

   public SelectGetPatientConsentType createSelectGetPatientConsentType() {
      return new SelectGetPatientConsentType();
   }

   public GetPatientConsentResponse createGetPatientConsentResponse() {
      return new GetPatientConsentResponse();
   }

   public RevokePatientConsentRequest createRevokePatientConsentRequest() {
      return new RevokePatientConsentRequest();
   }

   public RevokePatientConsentResponse createRevokePatientConsentResponse() {
      return new RevokePatientConsentResponse();
   }

   public GetPatientConsentStatusRequest createGetPatientConsentStatusRequest() {
      return new GetPatientConsentStatusRequest();
   }

   public GetPatientConsentStatusResponse createGetPatientConsentStatusResponse() {
      return new GetPatientConsentStatusResponse();
   }

   public PutHCPartyRequest createPutHCPartyRequest() {
      return new PutHCPartyRequest();
   }

   public PutHCPartyResponse createPutHCPartyResponse() {
      return new PutHCPartyResponse();
   }

   public GetHCPartyRequest createGetHCPartyRequest() {
      return new GetHCPartyRequest();
   }

   public GetHCPartyResponse createGetHCPartyResponse() {
      return new GetHCPartyResponse();
   }

   public PutHCPartyConsentRequest createPutHCPartyConsentRequest() {
      return new PutHCPartyConsentRequest();
   }

   public PutHCPartyConsentResponse createPutHCPartyConsentResponse() {
      return new PutHCPartyConsentResponse();
   }

   public GetHCPartyConsentRequest createGetHCPartyConsentRequest() {
      return new GetHCPartyConsentRequest();
   }

   public GetHCPartyConsentResponse createGetHCPartyConsentResponse() {
      return new GetHCPartyConsentResponse();
   }

   public RevokeHCPartyConsentRequest createRevokeHCPartyConsentRequest() {
      return new RevokeHCPartyConsentRequest();
   }

   public RevokeHCPartyConsentResponse createRevokeHCPartyConsentResponse() {
      return new RevokeHCPartyConsentResponse();
   }

   public PutTherapeuticLinkBulkRequest createPutTherapeuticLinkBulkRequest() {
      return new PutTherapeuticLinkBulkRequest();
   }

   public PutTherapeuticLinkRequest createPutTherapeuticLinkRequest() {
      return new PutTherapeuticLinkRequest();
   }

   public PutTherapeuticLinkResponseType createPutTherapeuticLinkResponseType() {
      return new PutTherapeuticLinkResponseType();
   }

   public ProofType createProofType() {
      return new ProofType();
   }

   public GetTherapeuticLinkRequest createGetTherapeuticLinkRequest() {
      return new GetTherapeuticLinkRequest();
   }

   public GetTherapeuticLinkResponse createGetTherapeuticLinkResponse() {
      return new GetTherapeuticLinkResponse();
   }

   public RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest() {
      return new RevokeTherapeuticLinkRequest();
   }

   public RevokeTherapeuticLinkResponse createRevokeTherapeuticLinkResponse() {
      return new RevokeTherapeuticLinkResponse();
   }

   public HasTherapeuticLinkRequest createHasTherapeuticLinkRequest() {
      return new HasTherapeuticLinkRequest();
   }

   public HasTherapeuticLinkResponse createHasTherapeuticLinkResponse() {
      return new HasTherapeuticLinkResponse();
   }

   public PutAccessRightRequest createPutAccessRightRequest() {
      return new PutAccessRightRequest();
   }

   public PutAccessRightResponse createPutAccessRightResponse() {
      return new PutAccessRightResponse();
   }

   public GetAccessRightRequest createGetAccessRightRequest() {
      return new GetAccessRightRequest();
   }

   public GetAccessRightResponse createGetAccessRightResponse() {
      return new GetAccessRightResponse();
   }

   public RevokeAccessRightRequest createRevokeAccessRightRequest() {
      return new RevokeAccessRightRequest();
   }

   public RevokeAccessRightResponse createRevokeAccessRightResponse() {
      return new RevokeAccessRightResponse();
   }

   public KmehrHeaderDeclareTransactionType createKmehrHeaderDeclareTransactionType() {
      return new KmehrHeaderDeclareTransactionType();
   }

   public FolderType createFolderType() {
      return new FolderType();
   }

   public FolderTypeUnbounded createFolderTypeUnbounded() {
      return new FolderTypeUnbounded();
   }

   public TransactionSummaryType createTransactionSummaryType() {
      return new TransactionSummaryType();
   }

   public KmehrHeaderGetTransactionListType createKmehrHeaderGetTransactionListType() {
      return new KmehrHeaderGetTransactionListType();
   }

   public HCPartyAdaptedType createHCPartyAdaptedType() {
      return new HCPartyAdaptedType();
   }

   public GetPatientAuditTrailRequest createGetPatientAuditTrailRequest() {
      return new GetPatientAuditTrailRequest();
   }

   public GetPatientAuditTrailResponse createGetPatientAuditTrailResponse() {
      return new GetPatientAuditTrailResponse();
   }

   public TransactionAccessListType createTransactionAccessListType() {
      return new TransactionAccessListType();
   }

   public TransactionWithAuthorType createTransactionWithAuthorType() {
      return new TransactionWithAuthorType();
   }

   public RequestPublicationResponse createRequestPublicationResponse() {
      return new RequestPublicationResponse();
   }

   public RequestPublicationRequest createRequestPublicationRequest() {
      return new RequestPublicationRequest();
   }

   public BasicConsentType createBasicConsentType() {
      return new BasicConsentType();
   }

   public PutTherapeuticExclusionRequest createPutTherapeuticExclusionRequest() {
      return new PutTherapeuticExclusionRequest();
   }

   public PutTherapeuticExclusionResponse createPutTherapeuticExclusionResponse() {
      return new PutTherapeuticExclusionResponse();
   }

   public GetTherapeuticExclusionRequest createGetTherapeuticExclusionRequest() {
      return new GetTherapeuticExclusionRequest();
   }

   public GetTherapeuticExclusionResponse createGetTherapeuticExclusionResponse() {
      return new GetTherapeuticExclusionResponse();
   }

   public GetTherapeuticExclusionHistoryRequest createGetTherapeuticExclusionHistoryRequest() {
      return new GetTherapeuticExclusionHistoryRequest();
   }

   public GetTherapeuticExclusionHistoryResponse createGetTherapeuticExclusionHistoryResponse() {
      return new GetTherapeuticExclusionHistoryResponse();
   }

   public TherapeuticExclusionListType createTherapeuticExclusionListType() {
      return new TherapeuticExclusionListType();
   }

   public TherapeuticExclusionWithOperationContext createTherapeuticExclusionWithOperationContext() {
      return new TherapeuticExclusionWithOperationContext();
   }

   public RevokeTherapeuticExclusionRequest createRevokeTherapeuticExclusionRequest() {
      return new RevokeTherapeuticExclusionRequest();
   }

   public RevokeTherapeuticExclusionResponse createRevokeTherapeuticExclusionResponse() {
      return new RevokeTherapeuticExclusionResponse();
   }

   public TherapeuticExclusionType createTherapeuticExclusionType() {
      return new TherapeuticExclusionType();
   }

   public TherapeuticExclusionHistorySelectType createTherapeuticExclusionHistorySelectType() {
      return new TherapeuticExclusionHistorySelectType();
   }

   public TherapeuticExclusionSelectType createTherapeuticExclusionSelectType() {
      return new TherapeuticExclusionSelectType();
   }

   public Therapeuticlinkrequest createTherapeuticlinkrequest() {
      return new Therapeuticlinkrequest();
   }

   public Transactionaccess createTransactionaccess() {
      return new Transactionaccess();
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/core/v2",
      name = "therapeuticlink"
   )
   public JAXBElement<TherapeuticLinkType> createTherapeuticlink(TherapeuticLinkType value) {
      return new JAXBElement(_Therapeuticlink_QNAME, TherapeuticLinkType.class, (Class)null, value);
   }
}
