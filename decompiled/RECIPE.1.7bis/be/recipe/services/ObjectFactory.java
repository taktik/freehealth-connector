package be.recipe.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _RecipeException_QNAME = new QName("http://services.recipe.be", "RecipeException");
   private static final QName _RecipeExceptionDetails_QNAME = new QName("http://services.recipe.be", "recipeExceptionDetails");

   public ListOpenPrescriptionResponse createListOpenPrescriptionResponse() {
      return new ListOpenPrescriptionResponse();
   }

   public RevokePrescriptionResponse createRevokePrescriptionResponse() {
      return new RevokePrescriptionResponse();
   }

   public RecipeExceptionDetails createRecipeExceptionDetails() {
      return new RecipeExceptionDetails();
   }

   public PartyIdentification createPartyIdentification() {
      return new PartyIdentification();
   }

   public Ping createPing() {
      return new Ping();
   }

   public UpdateFeedbackFlag createUpdateFeedbackFlag() {
      return new UpdateFeedbackFlag();
   }

   public UploadFile createUploadFile() {
      return new UploadFile();
   }

   public CreatePrescriptionResponse createCreatePrescriptionResponse() {
      return new CreatePrescriptionResponse();
   }

   public ListFeedbacks createListFeedbacks() {
      return new ListFeedbacks();
   }

   public GetPrescriptionForPrescriber createGetPrescriptionForPrescriber() {
      return new GetPrescriptionForPrescriber();
   }

   public PingResponse createPingResponse() {
      return new PingResponse();
   }

   public ListFeedbacksResponse createListFeedbacksResponse() {
      return new ListFeedbacksResponse();
   }

   public RecipeExceptionDetails.ErrorMap.Entry createRecipeExceptionDetailsErrorMapEntry() {
      return new RecipeExceptionDetails.ErrorMap.Entry();
   }

   public SendNotification createSendNotification() {
      return new SendNotification();
   }

   public GetPrescriptionForPrescriberResponse createGetPrescriptionForPrescriberResponse() {
      return new GetPrescriptionForPrescriberResponse();
   }

   public UpdateFeedbackFlagResponse createUpdateFeedbackFlagResponse() {
      return new UpdateFeedbackFlagResponse();
   }

   public RecipeExceptionDetails.ErrorMap createRecipeExceptionDetailsErrorMap() {
      return new RecipeExceptionDetails.ErrorMap();
   }

   public ListOpenPrescription createListOpenPrescription() {
      return new ListOpenPrescription();
   }

   public UploadFileResponse createUploadFileResponse() {
      return new UploadFileResponse();
   }

   public RecipeError createRecipeError() {
      return new RecipeError();
   }

   public CreatePrescription createCreatePrescription() {
      return new CreatePrescription();
   }

   public RevokePrescription createRevokePrescription() {
      return new RevokePrescription();
   }

   public SendNotificationResponse createSendNotificationResponse() {
      return new SendNotificationResponse();
   }

   @XmlElementDecl(
      namespace = "http://services.recipe.be",
      name = "RecipeException"
   )
   public JAXBElement<RecipeExceptionDetails> createRecipeException(RecipeExceptionDetails value) {
      return new JAXBElement(_RecipeException_QNAME, RecipeExceptionDetails.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://services.recipe.be",
      name = "recipeExceptionDetails"
   )
   public JAXBElement<RecipeExceptionDetails> createRecipeExceptionDetails(RecipeExceptionDetails value) {
      return new JAXBElement(_RecipeExceptionDetails_QNAME, RecipeExceptionDetails.class, (Class)null, value);
   }
}
