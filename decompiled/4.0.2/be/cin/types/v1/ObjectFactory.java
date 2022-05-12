package be.cin.types.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public StringLangType createStringLangType() {
      return new StringLangType();
   }

   public DetailType createDetailType() {
      return new DetailType();
   }

   public FaultType createFaultType() {
      return new FaultType();
   }

   public DetailsType createDetailsType() {
      return new DetailsType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public CareReceiverIdType createCareReceiverIdType() {
      return new CareReceiverIdType();
   }

   public Blob createBlob() {
      return new Blob();
   }
}
