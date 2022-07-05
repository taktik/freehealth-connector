package be.ehealth.businessconnector.therlink.builders.impl;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.therlink.builders.CommonObjectBuilder;
import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public final class CommonObjectBuilderImpl implements CommonObjectBuilder {
   public CommonObjectBuilderImpl() {
   }

   /** @deprecated */
   @Deprecated
   public TherapeuticLink createTherapeuticLink(Patient patient, String hcpType, String therLinkType, Date startDate, Date endDate, String comment, HcParty hcp) throws TechnicalConnectorException {
      return this.createTherapeuticLink(this.mapToDateTime(startDate), this.mapToDateTime(endDate), patient, hcpType, therLinkType, comment, hcp);
   }

   private DateTime mapToDateTime(Date date) {
      return date != null ? new DateTime(date) : null;
   }

   public Author createAuthor(List<HcParty> list) throws TechnicalConnectorException {
      Author author = new Author();
      author.getHcParties().addAll(list);
      return author;
   }

   public String createKmehrID() throws TechnicalConnectorException {
      return HcPartyUtil.retrieveMainAuthorId("therlink");
   }

   public TherapeuticLink createTherapeuticLink(DateTime startDate, DateTime endDate, Patient patient, String hcpType, String therLinkType, String comment, HcParty concernedHcp) throws TechnicalConnectorException {
      TherapeuticLink therLink = new TherapeuticLink(patient, concernedHcp, comment);
      therLink.setType(therLinkType);
      if (startDate != null) {
         therLink.setStartDate(new LocalDate(startDate));
      }

      if (endDate != null) {
         therLink.setEndDate(new LocalDate(endDate));
      }

      therLink.setComment(comment);
      return therLink;
   }
}
