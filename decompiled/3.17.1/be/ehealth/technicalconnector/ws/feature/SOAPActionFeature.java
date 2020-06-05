package be.ehealth.technicalconnector.ws.feature;

import be.ehealth.technicalconnector.handler.SoapActionHandler;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SOAPActionFeature extends GenericFeature {
   private static final Logger LOG = LoggerFactory.getLogger(SOAPActionFeature.class);
   private final String soapAction;
   private final boolean wsiCompliant;

   public SOAPActionFeature(String soapAction, boolean wsiCompliant) {
      super(true);
      this.soapAction = soapAction;
      this.wsiCompliant = wsiCompliant;
   }

   public String getID() {
      return "be.ehealth.technicalconnector.ws.feature.soapaction";
   }

   public Map<String, Object> requestParamOptions() {
      Map<String, Object> result = new HashMap();
      if (StringUtils.isNotBlank(this.soapAction)) {
         result.put("javax.xml.ws.soap.http.soapaction.use", Boolean.TRUE);
         result.put("javax.xml.ws.soap.http.soapaction.uri", this.soapAction);
         result.put("be.ehealth.technicalconnector.handler.soapactionhandler.wsi.complaint", this.wsiCompliant);
      } else {
         LOG.warn("soapAction is blank.");
      }

      return result;
   }

   public HandlerChain getHandlers() {
      HandlerChain handlerChain = new HandlerChain();
      if (StringUtils.isNotBlank(this.soapAction)) {
         handlerChain.register(HandlerPosition.SECURITY, new SoapActionHandler());
      }

      return handlerChain;
   }
}
