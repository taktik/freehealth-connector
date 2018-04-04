package be.ehealth.business.mycarenetcommons.builders.util;

import be.ehealth.business.mycarenetcommons.mapper.SendRequestMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendRequestType;
import org.joda.time.DateTime;

public final class RequestBuilderUtil {
   public static void fillInputToMycarenetRequest(SendRequestType sendRequest, CommonInput commonInput, Routing routing, Blob blob, byte[] xadesValue, String projectName) throws TechnicalConnectorException {
      sendRequest.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      sendRequest.setIssueInstant(new DateTime());
      sendRequest.setCommonInput(SendRequestMapper.mapCommonInput(commonInput));
      sendRequest.setRouting(SendRequestMapper.mapRouting(routing));
      sendRequest.setDetail(SendRequestMapper.mapBlobToBlobType(blob));
      sendRequest.setXades(BlobUtil.generateXades(sendRequest.getDetail(), xadesValue, projectName));
   }
}
