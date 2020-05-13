package be.ehealth.business.common.helper;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.core.v2.StatusDetail;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.CollectionUtils;
import org.w3c.dom.Node;

public final class EhealthServiceHelper {
   private EhealthServiceHelper() {
   }

   public static <T extends ResponseType> T callEhealthServiceV1(SAMLToken token, GenericRequest service, Object request, Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator ehealthReplyValidator) throws TechnicalConnectorException {
      try {
         sessionValidator.validateToken(token);
         service.setPayload(request);
         T response = (ResponseType)ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
         ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public static <T extends be.fgov.ehealth.commons._1_0.protocol.ResponseType> T callEhealthService_1_0(SAMLToken token, GenericRequest service, Object request, Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator ehealthReplyValidator) throws TechnicalConnectorException {
      try {
         sessionValidator.validateToken(token);
         service.setPayload(request);
         T response = (be.fgov.ehealth.commons._1_0.protocol.ResponseType)ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
         ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public static <T extends StatusResponseType> T callEhealthServiceV2(SAMLToken token, GenericRequest service, Object request, Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator ehealthReplyValidator) throws TechnicalConnectorException {
      try {
         sessionValidator.validateToken(token);
         service.setPayload(request);
         T response = (StatusResponseType)ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
         ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public static <T> List<T> toList(StatusDetail statusDetail, Class<T> clazz) {
      List<T> list = new ArrayList();
      if (statusDetail != null && CollectionUtils.isNotEmpty(statusDetail.getAnies())) {
         Iterator i$ = statusDetail.getAnies().iterator();

         while(i$.hasNext()) {
            Object object = i$.next();
            list.add(ConnectorXmlUtils.toObject(ConnectorXmlUtils.toByteArray((Node)object), clazz));
         }
      }

      return list;
   }

   public static <T> T getFirst(StatusDetail statusDetail, Class<T> clazz) {
      List<T> list = toList(statusDetail, clazz);
      return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
   }
}
