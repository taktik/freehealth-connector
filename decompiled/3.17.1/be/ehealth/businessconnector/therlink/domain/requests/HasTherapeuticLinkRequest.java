package be.ehealth.businessconnector.therlink.domain.requests;

import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import org.joda.time.DateTime;

public class HasTherapeuticLinkRequest extends TherapeuticLinkRequestType {
   private static final long serialVersionUID = 1L;

   public HasTherapeuticLinkRequest(DateTime date, String id, Author author, TherapeuticLink link) {
      super(id, date, author, link);
   }
}
