package org.taktik.connector.business.chapterIV.utils;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.utils.IdentifierType;
import java.util.ArrayList;
import java.util.List;

public final class KeyDepotHelper {
   private static final String CH4_IDENTIFIER_TYPE_PROPERTY = "chapterIV.keydepot.identifiertype";
   private static final String CH4_IDENTIFIER_SUBTYPE_PROPERTY = "chapterIV.keydepot.identifiersubtype";
   private static final String CH4_IDENTIFIER_VALUE_PROPERTY = "chapterIV.keydepot.identifiervalue";
   private static final String CH4_APPLICATION_PROPERTY = "chapterIV.keydepot.application";
   private static ConfigValidator configValidator;

   public static EncryptionToken getChapterIVEncryptionToken() throws TechnicalConnectorException {
      String identifierTypeString = configValidator.getProperty("chapterIV.keydepot.identifiertype");
      String identifierSubTypeString = configValidator.getProperty("chapterIV.keydepot.identifiersubtype");
      int identifierSource = 48;
      IdentifierType identifier = IdentifierType.lookup(identifierTypeString, (String)null, identifierSource);
      if (identifier == null) {
         throw new IllegalStateException("invalid configuration : identifier with type ]" + identifierTypeString + "[ and subtype ]" + identifierSubTypeString + "[ for source ETKDEPOT not found");
      } else {
         return KeyDepotManagerFactory.getKeyDepotManager().getEtk(identifier, configValidator.getLongProperty("chapterIV.keydepot.identifiervalue", 0L), configValidator.getProperty("chapterIV.keydepot.application"));
      }
   }

   static {
      List<String> neededProperties = new ArrayList();
      neededProperties.add("chapterIV.keydepot.application");
      neededProperties.add("chapterIV.keydepot.identifiertype");
      neededProperties.add("chapterIV.keydepot.identifiersubtype");
      neededProperties.add("chapterIV.keydepot.identifiervalue");
      configValidator = ConfigFactory.getConfigValidator(neededProperties);
   }
}
