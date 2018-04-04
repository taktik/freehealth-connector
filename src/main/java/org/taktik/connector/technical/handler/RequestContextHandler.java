package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.handler.domain.RequestContext;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class RequestContextHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(RequestContextHandler.class);
   private static final String MESSAGE_ENDPOINT_ADDRESS = "javax.xml.ws.service.endpoint.address";
   private String suffix = "";

   public RequestContextHandler() {
   }

   public RequestContextHandler(String suffix) {
      String input = suffix;
      if (!suffix.endsWith("_")) {
         input = suffix + "_";
      }

      this.suffix = input;
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      try {
         SOAPMessage msg = context.getMessage();
         RequestContext reqContext = RequestContext.getInstance();
         reqContext.clear();
         String endPoint = (String)context.get("javax.xml.ws.service.endpoint.address");
         if (endPoint != null && !endPoint.isEmpty()) {
            reqContext.put("endpoint", endPoint);
         }

         this.addToRequestContext(msg, reqContext, "OUT");
      } catch (SOAPException var5) {
         LOG.error("SOAPException", var5.getMessage(), var5);
      } catch (IOException var6) {
         LOG.error("IOException", var6.getMessage(), var6);
      }

      return true;
   }

   public boolean handleInbound(SOAPMessageContext context) {
      try {
         SOAPMessage msg = context.getMessage();
         RequestContext reqContext = RequestContext.getInstance();
         this.addToRequestContext(msg, reqContext, "IN");
      } catch (SOAPException var4) {
         LOG.error("SOAPException", var4.getMessage(), var4);
      } catch (IOException var5) {
         LOG.error("IOException", var5.getMessage(), var5);
      }

      return true;
   }

   private void addToRequestContext(SOAPMessage msg, RequestContext reqContext, String mode) throws SOAPException, IOException {
      ByteArrayOutputStream out = null;

      try {
         out = new ByteArrayOutputStream();
         msg.writeTo(out);
         reqContext.put("PAYLOAD_" + this.suffix + mode, out.toString(Charset.UTF_8.getName()));
         reqContext.put("SOAP_" + this.suffix + mode, msg);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)out);
      }

   }

   public boolean handleFault(SOAPMessageContext c) {
      this.handleMessage(c);
      return true;
   }
}
