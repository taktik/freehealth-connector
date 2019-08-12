package be.cin.encrypted;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public EncryptedKnownContent createEncryptedKnownContent() {
      return new EncryptedKnownContent();
   }

   public BusinessContent createBusinessContent() {
      return new BusinessContent();
   }
}
