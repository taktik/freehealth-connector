package org.taktik.connector.business.mycarenetdomaincommons.builders.util;

import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendRequestType;
import org.joda.time.DateTime;
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil;
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.sts.security.Credential;

public final class RequestBuilderUtil {
   private static final String IDENTIFIER_TYPE_PROPERTY = ".keydepot.identifiertype";
   private static final String IDENTIFIER_VALUE_PROPERTY = ".keydepot.identifiervalue";
   private static final String APPLICATION_ID_PROPERTY = ".keydepot.application";
   private static final long ETK_IDENTIFIER_DEFAULT_VALUE = 820563481L;
   private static Configuration config = ConfigFactory.getConfigValidator();

   private RequestBuilderUtil() {
   }

   public static void fillInputToMycarenetRequest(SendRequestType sendRequest, CommonInput commonInput, Credential credential, Routing routing, Blob blob, byte[] xadesValue, String projectName) throws TechnicalConnectorException {
      sendRequest.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      sendRequest.setIssueInstant(new DateTime());
      sendRequest.setCommonInput(SendRequestMapper.mapCommonInput(commonInput));
      sendRequest.setRouting(SendRequestMapper.mapRouting(routing));
      sendRequest.setDetail(SendRequestMapper.mapBlobToBlobType(blob));
      sendRequest.setXades(BlobUtil.generateXades(sendRequest.getDetail(), credential, xadesValue, projectName));
   }
}
