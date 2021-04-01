package be.behealth.webservices.tsa;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public TSConsultRequest createTSConsultRequest() {
      return new TSConsultRequest();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public TimeStampIdentification createTimeStampIdentification() {
      return new TimeStampIdentification();
   }

   public TSConsultResponse createTSConsultResponse() {
      return new TSConsultResponse();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public TSConsultTSBagRequest createTSConsultTSBagRequest() {
      return new TSConsultTSBagRequest();
   }

   public TSConsultTSBagResponse createTSConsultTSBagResponse() {
      return new TSConsultTSBagResponse();
   }

   public TSBagType createTSBagType() {
      return new TSBagType();
   }

   public TimeStampBagType createTimeStampBagType() {
      return new TimeStampBagType();
   }

   public JournalEntryType createJournalEntryType() {
      return new JournalEntryType();
   }
}
