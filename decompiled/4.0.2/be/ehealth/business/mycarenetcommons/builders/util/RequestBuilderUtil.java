package be.ehealth.business.mycarenetcommons.builders.util;

import be.ehealth.business.mycarenetcommons.mapper.SendRequestMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendRequestType;
import java.util.Set;
import org.joda.time.DateTime;

public final class RequestBuilderUtil {
   private static final String IDENTIFIER_TYPE_PROPERTY = ".keydepot.identifiertype";
   private static final String IDENTIFIER_VALUE_PROPERTY = ".keydepot.identifiervalue";
   private static final String APPLICATION_ID_PROPERTY = ".keydepot.application";
   private static final long ETK_IDENTIFIER_DEFAULT_VALUE = 820563481L;
   private static Configuration config = ConfigFactory.getConfigValidator();

   private RequestBuilderUtil() {
   }

   public static void fillInputToMycarenetRequest(SendRequestType sendRequest, CommonInput commonInput, Routing routing, Blob blob, byte[] xadesValue, String projectName) throws TechnicalConnectorException {
      sendRequest.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      sendRequest.setIssueInstant(new DateTime());
      sendRequest.setCommonInput(SendRequestMapper.mapCommonInput(commonInput));
      sendRequest.setRouting(SendRequestMapper.mapRouting(routing));
      sendRequest.setDetail(SendRequestMapper.mapBlobToBlobType(blob));
      sendRequest.setXades(BlobUtil.generateXades(sendRequest.getDetail(), xadesValue, projectName));
   }

   public static Set<EncryptionToken> getEtk(String projectName) throws TechnicalConnectorException {
      String identifierTypeString = config.getProperty(projectName + ".keydepot.identifiertype", "CBE");
      Long identifierValue = config.getLongProperty(projectName + ".keydepot.identifiervalue", 820563481L);
      String applicationId = config.getProperty(projectName + ".keydepot.application", "MYCARENET");
      int identifierSource = 48;
      IdentifierType identifier = IdentifierType.lookup(identifierTypeString, (String)null, identifierSource);
      if (identifier == null) {
         throw new IllegalStateException("invalid configuration : identifier with type ]" + identifierTypeString + "[ for source ETKDEPOT not found");
      } else {
         return KeyDepotManagerFactory.getKeyDepotManager().getETKs(IdentifierType.CBE, identifierValue, applicationId);
      }
   }
}
