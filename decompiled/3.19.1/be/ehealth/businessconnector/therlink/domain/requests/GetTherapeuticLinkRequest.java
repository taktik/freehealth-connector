package be.ehealth.businessconnector.therlink.domain.requests;

import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import java.util.Date;
import org.joda.time.DateTime;

public class GetTherapeuticLinkRequest extends TherapeuticLinkRequestType {
   private static final long serialVersionUID = 1L;
   private int maxRows;

   public int getMaxRows() {
      return this.maxRows;
   }

   public void setMaxRows(int maxRows) {
      this.maxRows = maxRows;
   }

   /** @deprecated */
   @Deprecated
   public GetTherapeuticLinkRequest(String id, Date date, Author author, TherapeuticLink link, int maxRows, Proof... proofs) {
      super(id, date, author, link, proofs);
      this.maxRows = maxRows;
   }

   public GetTherapeuticLinkRequest(DateTime date, String id, Author author, TherapeuticLink link, int maxRows, Proof... proofs) {
      super(id, date, author, link, proofs);
      this.maxRows = maxRows;
   }
}
