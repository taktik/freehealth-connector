package be.ehealth.businessconnector.therlink.domain.responses;

import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

public class GetTherapeuticLinkResponse extends GeneralResponse {
   private List<TherapeuticLinkResponse> listOfTherapeuticLinks;

   public GetTherapeuticLinkResponse(DateTime dateTime, Author author, String id, TherapeuticLinkRequestType request, List<TherapeuticLinkResponse> listOfTherapeuticLinks, Acknowledge acknowledge) {
      this.dateTime = dateTime;
      this.externalAuthor = author;
      this.externalID = id;
      this.originalRequest = request;
      this.listOfTherapeuticLinks = listOfTherapeuticLinks;
      this.acknowledge = acknowledge;
   }

   public List<TherapeuticLinkResponse> getListOfTherapeuticLinks() {
      return this.listOfTherapeuticLinks;
   }

   public void setListOfTherapeuticLinks(List<TherapeuticLinkResponse> listOfTherapeuticLinks) {
      this.listOfTherapeuticLinks = listOfTherapeuticLinks;
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append(super.toString() + ", List of Therapeutic links : [");
      if (this.listOfTherapeuticLinks != null) {
         Iterator it = this.listOfTherapeuticLinks.iterator();

         while(it.hasNext()) {
            builder.append(((TherapeuticLinkResponse)it.next()).toString());
            if (it.hasNext()) {
               builder.append(", ");
            }
         }
      }

      builder.append("]");
      return builder.toString();
   }
}
