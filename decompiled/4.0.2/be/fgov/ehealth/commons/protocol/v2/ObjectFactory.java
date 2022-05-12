package be.fgov.ehealth.commons.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public AuthorRequestType createAuthorRequestType() {
      return new AuthorRequestType();
   }

   public PaginationRequestType createPaginationRequestType() {
      return new PaginationRequestType();
   }

   public AuthorPaginationRequestType createAuthorPaginationRequestType() {
      return new AuthorPaginationRequestType();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }

   public StatusResponseType createStatusResponseType() {
      return new StatusResponseType();
   }

   public PaginationResponseType createPaginationResponseType() {
      return new PaginationResponseType();
   }

   public PaginationStatusResponseType createPaginationStatusResponseType() {
      return new PaginationStatusResponseType();
   }
}
