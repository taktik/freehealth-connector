package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
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
      String requestTimeOut = this.getDuration("connector.soaphandler.connection.request.timeout");
      LOG.debug("Setting request timeout on: {} milliseconds.", requestTimeOut);
      context.put("com.sun.xml.internal.ws.request.timeout", requestTimeOut);
      context.put("connector.soaphandler.connection.request.timeout", requestTimeOut);
      String connectTimeOut = this.getDuration("connector.soaphandler.connection.connection.timeout");
      LOG.debug("Setting connect timeout on: {} milliseconds.", connectTimeOut);
      context.put("com.sun.xml.internal.ws.connect.timeout", connectTimeOut);
      context.put("connector.soaphandler.connection.connection.timeout", connectTimeOut);
      return true;
   }

   private String getDuration(String requestTimeoutProp) {
      return this.config.hasDurationProperty(requestTimeoutProp) ? Long.toString(this.config.getDurationProperty("connector.soaphandler.connection.request.timeout", 30L, TimeUnit.SECONDS).convert(TimeUnit.MILLISECONDS)) : this.config.getProperty(requestTimeoutProp, "30000");
   }

   public boolean handleFault(SOAPMessageContext context) {
      return this.handleMessage(context);
   }
}
