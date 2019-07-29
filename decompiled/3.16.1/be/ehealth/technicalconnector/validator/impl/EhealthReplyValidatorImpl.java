package be.ehealth.technicalconnector.validator.impl;

import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhealthReplyValidatorImpl implements EhealthReplyValidator {
   private static final Logger LOG = LoggerFactory.getLogger(EhealthReplyValidatorImpl.class);
   public static final String EHEALTH_SUCCESS_CODE_100 = "100";
   public static final String EHEALTH_SUCCESS_CODE_200 = "200";
   private static final String EHEALTH_SUCCESS_URN = "urn:be:fgov:ehealth:2.0:status:Success";

   public boolean validateReplyStatus(ResponseType response) throws SoaErrorException {
      String code = response.getStatus().getCode();
      if (!"100".equals(code) && !"200".equals(code)) {
         LOG.error("Error Status received : " + code + " " + ((LocalisedString)response.getStatus().getMessages().get(0)).getValue());
         throw new SoaErrorException(code, response);
      } else {
         return true;
      }
   }

   public boolean validateReplyStatus(be.fgov.ehealth.commons._1_0.protocol.ResponseType response) throws SoaErrorException {
      String code = response.getStatus().getCode();
      if (!"100".equals(code)) {
         LOG.error("Error Status received : " + code + " " + ((be.fgov.ehealth.commons._1_0.core.LocalisedString)response.getStatus().getMessages().get(0)).getValue());
         throw new SoaErrorException(code, response);
      } else {
         return true;
      }
   }

   public boolean validateReplyStatus(StatusResponseType response) throws SoaErrorException {
      String code = response.getStatus().getStatusCode().getValue();
      if (!"urn:be:fgov:ehealth:2.0:status:Success".equals(code)) {
         LOG.error("Error Status received : " + code + " " + response.getStatus().getStatusMessage());
         throw new SoaErrorException(code, response);
      } else {
         return true;
      }
   }
}
