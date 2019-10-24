package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import java.util.concurrent.TimeUnit;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionTimeOutHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectionTimeOutHandler.class);
   private static final String REQUEST_TIMEOUT = "com.sun.xml.internal.ws.request.timeout";
   private static final String CONNECT_TIMEOUT = "com.sun.xml.internal.ws.connect.timeout";
   public static final String REQUEST_TIMEOUT_PROP = "connector.soaphandler.connection.request.timeout";
   public static final String CONNECT_TIMEOUT_PROP = "connector.soaphandler.connection.connection.timeout";
   private Configuration config = ConfigFactory.getConfigValidator();
   private static final String DEFAULT_TIME_OUT = "30000";

   public boolean handleOutbound(SOAPMessageContext context) {
      String endpoint = (String) context.get("javax.xml.ws.service.endpoint.address");
      String[] parts = endpoint != null ? endpoint.split("/+") : null;
      String endpointProperty = (parts != null && parts.length>=4) ? parts[parts.length-2] + "." + parts[parts.length-1] : null;
      String requestTimeOut = "120000"; //this.getDuration("connector.soaphandler.connection.request.timeout", endpointProperty);
      LOG.debug("Setting request timeout on: {} milliseconds.", requestTimeOut);
      context.put("com.sun.xml.internal.ws.request.timeout", requestTimeOut);
      context.put("connector.soaphandler.connection.request.timeout", requestTimeOut);
      String connectTimeOut = "120000"; //this.getDuration("connector.soaphandler.connection.connection.timeout", endpointProperty);
      LOG.debug("Setting connect timeout on: {} milliseconds.", connectTimeOut);
      context.put("com.sun.xml.internal.ws.connect.timeout", connectTimeOut);
      context.put("connector.soaphandler.connection.connection.timeout", connectTimeOut);
      return true;
   }

   private String getDuration(String timeoutProp, String endpointProperty) {
      String endpointTimeoutProp = endpointProperty != null ? timeoutProp + "." + endpointProperty : null;
      if (endpointTimeoutProp != null && (this.config.hasProperty(endpointTimeoutProp) || this.config.hasDurationProperty(endpointTimeoutProp))) {
         return this.config.hasDurationProperty(endpointTimeoutProp) ? Long.toString(this.config.getDurationProperty("connector.soaphandler.connection.request.timeout", 60L, TimeUnit.SECONDS).convert(TimeUnit.MILLISECONDS)) : this.config.getProperty(endpointTimeoutProp, "60000");
      }

      return this.config.hasDurationProperty(timeoutProp) ? Long.toString(this.config.getDurationProperty("connector.soaphandler.connection.request.timeout", 60L, TimeUnit.SECONDS).convert(TimeUnit.MILLISECONDS)) : this.config.getProperty(timeoutProp, "60000");
   }

   public boolean handleFault(SOAPMessageContext context) {
      return this.handleMessage(context);
   }
}
