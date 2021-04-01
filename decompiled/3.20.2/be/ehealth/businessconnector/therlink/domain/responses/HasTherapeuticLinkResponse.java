package be.ehealth.businessconnector.therlink.domain.responses;

import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import org.joda.time.DateTime;

public class HasTherapeuticLinkResponse extends GeneralResponse {
   public HasTherapeuticLinkResponse(DateTime dateTime, Author author, String id, TherapeuticLinkRequestType request, Acknowledge ack) {
      this.dateTime = dateTime;
      this.externalAuthor = author;
      this.externalID = id;
      this.originalRequest = request;
      this.acknowledge = ack;
   }
}
