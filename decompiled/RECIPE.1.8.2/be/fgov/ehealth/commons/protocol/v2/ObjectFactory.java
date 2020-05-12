package be.fgov.ehealth.commons.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public PaginationResponseType createPaginationResponseType() {
      return new PaginationResponseType();
   }

   public AuthorRequestType createAuthorRequestType() {
      return new AuthorRequestType();
   }

   public AuthorPaginationRequestType createAuthorPaginationRequestType() {
      return new AuthorPaginationRequestType();
   }

   public PaginationRequestType createPaginationRequestType() {
      return new PaginationRequestType();
   }

   public PaginationStatusResponseType createPaginationStatusResponseType() {
      return new PaginationStatusResponseType();
   }

   public StatusResponseType createStatusResponseType() {
      return new StatusResponseType();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }
}
