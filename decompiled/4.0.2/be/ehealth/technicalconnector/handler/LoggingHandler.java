package be.ehealth.technicalconnector.handler;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(LoggingHandler.class);
   static final String MESSAGE_ENDPOINT_ADDRESS = "javax.xml.ws.service.endpoint.address";

   public LoggingHandler() {
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      SOAPMessage msg = context.getMessage();
      if (msg != null && LOG.isInfoEnabled()) {
         String endPoint = (String)context.get("javax.xml.ws.service.endpoint.address");
         String soapAction = ArrayUtils.toString(msg.getMimeHeaders().getHeader("SOAPAction"));
         LOG.info("Invoking webservice on url: [{}] with SOAPAction(s) {}", endPoint, soapAction);
      }

      if (LOG.isDebugEnabled()) {
         dumpMessage(msg, "OUT", LOG);
      }

      return true;
   }

   public boolean handleInbound(SOAPMessageContext context) {
      SOAPMessage msg = context.getMessage();
      if (LOG.isInfoEnabled()) {
         String[] correlationIDs = msg.getMimeHeaders().getHeader("X-CorrelationID");
         if (ArrayUtils.isNotEmpty(correlationIDs)) {
            LOG.info("Retrieved response with X-CorrelationID [{}]", StringUtils.join(correlationIDs));
         }
      }

      if (LOG.isDebugEnabled()) {
         dumpMessage(msg, "IN", LOG);
      }

      return true;
   }

   public boolean handleFault(SOAPMessageContext c) {
      this.handleMessage(c);
      return true;
   }
}
