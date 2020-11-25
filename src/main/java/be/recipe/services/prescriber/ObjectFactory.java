package be.recipe.services.prescriber;

import be.recipe.services.core.RecipeError;
import be.recipe.services.core.RecipeExceptionDetails;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _RecipeExceptionDetails_QNAME = new QName("http:/services.recipe.be/prescriber", "recipeExceptionDetails");
   private static final QName _RecipeException_QNAME = new QName("http:/services.recipe.be/prescriber", "RecipeException");
   private static final QName _RecipeError_QNAME = new QName("http:/services.recipe.be/prescriber", "recipeError");

   public ValidationPropertiesResult createValidationPropertiesResult() {
      return new ValidationPropertiesResult();
   }

   public ValidationPropertiesResult.Properties createValidationPropertiesResultProperties() {
      return new ValidationPropertiesResult.Properties();
   }

   public ValidationPropertiesResult.XsdValidationFiles createValidationPropertiesResultXsdValidationFiles() {
      return new ValidationPropertiesResult.XsdValidationFiles();
   }

   public CreatePrescriptionResult createCreatePrescriptionResult() {
      return new CreatePrescriptionResult();
   }

   public SendNotificationParam createSendNotificationParam() {
      return new SendNotificationParam();
   }

   public ValidationPropertiesParam createValidationPropertiesParam() {
      return new ValidationPropertiesParam();
   }

   public RevokePrescriptionResponse createRevokePrescriptionResponse() {
      return new RevokePrescriptionResponse();
   }

   public ListFeedbacksParam createListFeedbacksParam() {
      return new ListFeedbacksParam();
   }

   public ListFeedbacksResult createListFeedbacksResult() {
      return new ListFeedbacksResult();
   }

   public ListFeedbackItem createListFeedbackItem() {
      return new ListFeedbackItem();
   }

   public GetPrescriptionForPrescriberParam createGetPrescriptionForPrescriberParam() {
      return new GetPrescriptionForPrescriberParam();
   }

   public GetPrescriptionForPrescriberResponse createGetPrescriptionForPrescriberResponse() {
      return new GetPrescriptionForPrescriberResponse();
   }

   public UpdateFeedbackFlag createUpdateFeedbackFlag() {
      return new UpdateFeedbackFlag();
   }

   public SendNotification createSendNotification() {
      return new SendNotification();
   }

   public GetPrescriptionStatusResponse createGetPrescriptionStatusResponse() {
      return new GetPrescriptionStatusResponse();
   }

   public ListFeedbacks createListFeedbacks() {
      return new ListFeedbacks();
   }

   public GetPrescriptionStatusResult createGetPrescriptionStatusResult() {
      return new GetPrescriptionStatusResult();
   }

   public PutVisionResult createPutVisionResult() {
      return new PutVisionResult();
   }

   public RevokePrescriptionParam createRevokePrescriptionParam() {
      return new RevokePrescriptionParam();
   }

   public SendNotificationResult createSendNotificationResult() {
      return new SendNotificationResult();
   }

   public GetPrescriptionStatusParam createGetPrescriptionStatusParam() {
      return new GetPrescriptionStatusParam();
   }

   public ListFeedbacksResponse createListFeedbacksResponse() {
      return new ListFeedbacksResponse();
   }

   public CreatePrescription createCreatePrescription() {
      return new CreatePrescription();
   }

   public ListOpenRidsResult createListOpenRidsResult() {
      return new ListOpenRidsResult();
   }

   public CreatePrescriptionParam createCreatePrescriptionParam() {
      return new CreatePrescriptionParam();
   }

   public SendNotificationResponse createSendNotificationResponse() {
      return new SendNotificationResponse();
   }

   public ListRidsHistoryParam createListRidsHistoryParam() {
      return new ListRidsHistoryParam();
   }

   public ListOpenRidsResponse createListOpenRidsResponse() {
      return new ListOpenRidsResponse();
   }

   public PutVisionParam createPutVisionParam() {
      return new PutVisionParam();
   }

   public ValidationProperties createValidationProperties() {
      return new ValidationProperties();
   }

   public UpdateFeedbackFlagParam createUpdateFeedbackFlagParam() {
      return new UpdateFeedbackFlagParam();
   }

   public PutVisionResponse createPutVisionResponse() {
      return new PutVisionResponse();
   }

   public ListOpenRids createListOpenRids() {
      return new ListOpenRids();
   }

   public CreatePrescriptionResponse createCreatePrescriptionResponse() {
      return new CreatePrescriptionResponse();
   }

   public GetPrescriptionForPrescriber createGetPrescriptionForPrescriber() {
      return new GetPrescriptionForPrescriber();
   }

   public RevokePrescriptionResult createRevokePrescriptionResult() {
      return new RevokePrescriptionResult();
   }

   public ListRidsHistory createListRidsHistory() {
      return new ListRidsHistory();
   }

   public UpdateFeedbackFlagResult createUpdateFeedbackFlagResult() {
      return new UpdateFeedbackFlagResult();
   }

   public GetPrescriptionForPrescriberResult createGetPrescriptionForPrescriberResult() {
      return new GetPrescriptionForPrescriberResult();
   }

   public UpdateFeedbackFlagResponse createUpdateFeedbackFlagResponse() {
      return new UpdateFeedbackFlagResponse();
   }

   public GetPrescriptionStatus createGetPrescriptionStatus() {
      return new GetPrescriptionStatus();
   }

   public ValidationPropertiesResponse createValidationPropertiesResponse() {
      return new ValidationPropertiesResponse();
   }

   public ListOpenRidsParam createListOpenRidsParam() {
      return new ListOpenRidsParam();
   }

   public RevokePrescription createRevokePrescription() {
      return new RevokePrescription();
   }

   public PutVision createPutVision() {
      return new PutVision();
   }

   public ListRidsHistoryResponse createListRidsHistoryResponse() {
      return new ListRidsHistoryResponse();
   }

   public ListRidsHistoryResult createListRidsHistoryResult() {
      return new ListRidsHistoryResult();
   }

   public ListRidsHistoryResultItem createListRidsHistoryResultItem() {
      return new ListRidsHistoryResultItem();
   }

   public ValidationPropertiesResult.Properties.Entry createValidationPropertiesResultPropertiesEntry() {
      return new ValidationPropertiesResult.Properties.Entry();
   }

   public ValidationPropertiesResult.XsdValidationFiles.Entry createValidationPropertiesResultXsdValidationFilesEntry() {
      return new ValidationPropertiesResult.XsdValidationFiles.Entry();
   }

   @XmlElementDecl(
      namespace = "http:/services.recipe.be/prescriber",
      name = "recipeExceptionDetails"
   )
   public JAXBElement<RecipeExceptionDetails> createRecipeExceptionDetails(RecipeExceptionDetails value) {
      return new JAXBElement(_RecipeExceptionDetails_QNAME, RecipeExceptionDetails.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http:/services.recipe.be/prescriber",
      name = "RecipeException"
   )
   public JAXBElement<RecipeExceptionDetails> createRecipeException(RecipeExceptionDetails value) {
      return new JAXBElement(_RecipeException_QNAME, RecipeExceptionDetails.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http:/services.recipe.be/prescriber",
      name = "recipeError"
   )
   public JAXBElement<RecipeError> createRecipeError(RecipeError value) {
      return new JAXBElement(_RecipeError_QNAME, RecipeError.class, (Class)null, value);
   }
}
