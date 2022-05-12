package be.fgov.ehealth.errors.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public LocalisedStringType createLocalisedStringType() {
      return new LocalisedStringType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }
}
