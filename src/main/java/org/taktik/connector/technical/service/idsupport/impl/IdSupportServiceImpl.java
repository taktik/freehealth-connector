package org.taktik.connector.technical.service.idsupport.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.idsupport.IdSupportService;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.ws.ServiceFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.idsupport.core.v2.IdentificationData;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdRequest;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdResponse;
import java.util.Arrays;
import javax.xml.soap.SOAPException;
import org.joda.time.DateTime;

public class IdSupportServiceImpl implements IdSupportService {
   private EhealthReplyValidator validator;

   public IdSupportServiceImpl(EhealthReplyValidator validator) {
      this.validator = validator;
   }

   public VerifyIdResponse verifyId(VerifyIdRequest request, SAMLToken samlToken) throws TechnicalConnectorException {
      return this.verifyIdAndGenerateToken(request, samlToken);
   }

   public VerifyIdResponse verifyId(String legalContext, Id ssin, Id cardNumber, SAMLToken samlToken) throws TechnicalConnectorException {
      return this.verifyId(legalContext, identificationData(ssin, cardNumber), samlToken);
   }

   public VerifyIdResponse verifyId(String legalContext, Id barcode, SAMLToken samlToken) throws TechnicalConnectorException {
      return this.verifyId(legalContext, identificationData(barcode), samlToken);
   }

   private VerifyIdResponse verifyId(String legalContext, IdentificationData identificationData, SAMLToken samlToken) throws TechnicalConnectorException {
      VerifyIdRequest request = getBasicRequest(legalContext, "ID_" + IdGeneratorFactory.getIdGenerator("uuid").generateId());
      request.setIdentificationData(identificationData);
      return this.verifyId(request, samlToken);
   }

   private VerifyIdResponse verifyIdAndGenerateToken(VerifyIdRequest verifyIdRequest, SAMLToken samlToken) throws TechnicalConnectorException {
      try {
         GenericRequest genericRequest = ServiceFactory.getIdSupportV2Service(samlToken);
         genericRequest.setPayload((Object)verifyIdRequest);
         VerifyIdResponse response = (VerifyIdResponse)org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(genericRequest).asObject(VerifyIdResponse.class);
         this.validator.validateReplyStatus((StatusResponseType)response);
         return response;
      } catch (SOAPException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var4.getMessage(), var4});
      }
   }

   private static VerifyIdRequest getBasicRequest(String legalContext, String id) {
      VerifyIdRequest request = new VerifyIdRequest();
      request.setId(id);
      request.setLegalContext(legalContext);
      request.setIssueInstant(new DateTime());
      return request;
   }

   private static IdentificationData identificationData(Id... ids) {
      IdentificationData identificationData = new IdentificationData();
      identificationData.getIds().addAll(Arrays.asList(ids));
      return identificationData;
   }
}
