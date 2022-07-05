package be.fgov.ehealth.rn.commons.business.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public Message createMessage() {
      return new Message();
   }

   public InformationType createInformationType() {
      return new InformationType();
   }

   public InformationCBSSType createInformationCBSSType() {
      return new InformationCBSSType();
   }

   public InformationCustomerType createInformationCustomerType() {
      return new InformationCustomerType();
   }

   public InformationSupplierType createInformationSupplierType() {
      return new InformationSupplierType();
   }

   public InformationNotificationCBSSType createInformationNotificationCBSSType() {
      return new InformationNotificationCBSSType();
   }

   public InformationNotifiedType createInformationNotifiedType() {
      return new InformationNotifiedType();
   }

   public InformationCBSSBatchType createInformationCBSSBatchType() {
      return new InformationCBSSBatchType();
   }

   public SenderReceiverType createSenderReceiverType() {
      return new SenderReceiverType();
   }

   public OrganizationIdentificationType createOrganizationIdentificationType() {
      return new OrganizationIdentificationType();
   }

   public OpenPeriodType createOpenPeriodType() {
      return new OpenPeriodType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public OpenQuarterPeriodType createOpenQuarterPeriodType() {
      return new OpenQuarterPeriodType();
   }

   public QuarterPeriodType createQuarterPeriodType() {
      return new QuarterPeriodType();
   }

   public LocalizedDescriptionType createLocalizedDescriptionType() {
      return new LocalizedDescriptionType();
   }

   public LocalizedDescriptionsType createLocalizedDescriptionsType() {
      return new LocalizedDescriptionsType();
   }

   public DataFiltersType createDataFiltersType() {
      return new DataFiltersType();
   }

   public BusinessAnomaliesType createBusinessAnomaliesType() {
      return new BusinessAnomaliesType();
   }

   public BusinessAnomalyType createBusinessAnomalyType() {
      return new BusinessAnomalyType();
   }

   public CBSSFaultType createCBSSFaultType() {
      return new CBSSFaultType();
   }

   public StatusType createStatusType() {
      return new StatusType();
   }

   public StatusOkType createStatusOkType() {
      return new StatusOkType();
   }

   public SsinWithCanceledAndReplacesStatusType createSsinWithCanceledAndReplacesStatusType() {
      return new SsinWithCanceledAndReplacesStatusType();
   }

   public SsinWithCanceledAndReplacedByStatusType createSsinWithCanceledAndReplacedByStatusType() {
      return new SsinWithCanceledAndReplacedByStatusType();
   }
}
