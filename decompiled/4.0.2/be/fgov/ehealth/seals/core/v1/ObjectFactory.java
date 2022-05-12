package be.fgov.ehealth.seals.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public OriginalDataType createOriginalDataType() {
      return new OriginalDataType();
   }

   public ChoiceEncodedDataErrorType createChoiceEncodedDataErrorType() {
      return new ChoiceEncodedDataErrorType();
   }

   public EncodedDataType createEncodedDataType() {
      return new EncodedDataType();
   }

   public ChoiceDecodedDataErrorType createChoiceDecodedDataErrorType() {
      return new ChoiceDecodedDataErrorType();
   }

   public DecodedDataType createDecodedDataType() {
      return new DecodedDataType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }
}
