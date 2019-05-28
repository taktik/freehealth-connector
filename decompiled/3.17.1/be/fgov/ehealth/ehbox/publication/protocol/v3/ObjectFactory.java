package be.fgov.ehealth.ehbox.publication.protocol.v3;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SendMessageRequest createSendMessageRequest() {
      return new SendMessageRequest();
   }

   public DestinationContextType createDestinationContextType() {
      return new DestinationContextType();
   }

   public ContentContextType createContentContextType() {
      return new ContentContextType();
   }

   public SendMessageResponse createSendMessageResponse() {
      return new SendMessageResponse();
   }

   public Recipient createRecipient() {
      return new Recipient();
   }

   public PublicationContentType createPublicationContentType() {
      return new PublicationContentType();
   }

   public ContentSpecificationType createContentSpecificationType() {
      return new ContentSpecificationType();
   }

   public PublicationAnnexType createPublicationAnnexType() {
      return new PublicationAnnexType();
   }

   public PublicationDocumentType createPublicationDocumentType() {
      return new PublicationDocumentType();
   }

   public Substitute createSubstitute() {
      return new Substitute();
   }
}
