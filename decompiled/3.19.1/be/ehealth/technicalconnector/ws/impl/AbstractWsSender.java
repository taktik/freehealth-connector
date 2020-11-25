package be.ehealth.technicalconnector.ws.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.impl.strategy.InvokeStrategy;
import be.ehealth.technicalconnector.ws.impl.strategy.InvokeStrategyContext;
import be.ehealth.technicalconnector.ws.impl.strategy.InvokeStrategyFactory;
import be.ehealth.technicalconnector.ws.impl.strategy.RetryStrategy;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.activation.DataHandler;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWsSender {
   public static final String MESSAGECONTEXT_ENDPOINT_ADDRESS = "javax.xml.ws.service.endpoint.address";
   /** @deprecated */
   @Deprecated
   public static final String PROP_RETRY_STRATEGY = "be.ehealth.technicalconnector.ws.genericsender.invokestrategy";
   private static final Logger LOG = LoggerFactory.getLogger(AbstractWsSender.class);
   private static MessageFactory mf;
   private static SOAPConnectionFactory scf;

   public GenericResponse send(GenericRequest genericRequest) throws TechnicalConnectorException {
      List<InvokeStrategy> strategies = InvokeStrategyFactory.getList((String)genericRequest.getRequestMap().get("javax.xml.ws.service.endpoint.address"));
      InvokeStrategyContext ctx = new InvokeStrategyContext(genericRequest);
      Iterator i$ = strategies.iterator();

      while(i$.hasNext()) {
         InvokeStrategy strategy = (InvokeStrategy)i$.next();
         LOG.debug("Using invoke strategy [{}]", strategy.getClass());
         if (strategy.invoke(ctx)) {
            break;
         }
      }

      if (ctx.hasException()) {
         throw ctx.getException();
      } else {
         return ctx.getResponse();
      }
   }

   protected GenericResponse call(GenericRequest genericRequest) throws TechnicalConnectorException {
      SOAPConnection conn = null;
      Handler[] chain = genericRequest.getHandlerchain();

      GenericResponse var6;
      try {
         SOAPMessageContext request = this.createSOAPMessageCtx(genericRequest);
         request.putAll(genericRequest.getRequestMap());
         request.put("javax.xml.ws.handler.message.outbound", true);
         executeHandlers(chain, request);
         conn = scf.createConnection();
         SOAPMessageContext reply = createSOAPMessageCtx(conn.call(request.getMessage(), generateEndpoint(request)));
         reply.putAll(genericRequest.getRequestMap());
         reply.put("javax.xml.ws.handler.message.outbound", false);
         ArrayUtils.reverse(chain);
         executeHandlers(chain, reply);
         var6 = new GenericResponse(reply.getMessage());
      } catch (Exception var10) {
         throw translate(var10);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)conn);
      }

      return var6;
   }

   private static SOAPMessageContext createSOAPMessageCtx(SOAPMessage msg) {
      return new SOAPMessageContextImpl(msg);
   }

   private static TechnicalConnectorException translate(Exception e) {
      if (e instanceof SOAPException) {
         RetryStrategy.RetryNotifier.activate(e);
      }

      if (e instanceof TechnicalConnectorException) {
         return (TechnicalConnectorException)e;
      } else {
         Throwable reason = ExceptionUtils.getRootCause(e);
         LOG.error("Cannot send SOAP message. Reason [{}]", reason, e);
         return new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, reason, new Object[]{"Cannot send SOAP message"});
      }
   }

   private static void executeHandlers(Handler[] handlers, SOAPMessageContext ctx) throws TechnicalConnectorException {
      Handler[] arr$ = handlers;
      int len$ = handlers.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Handler handler = arr$[i$];
         if (!handler.handleMessage(ctx)) {
            TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_WS;
            if (LOG.isErrorEnabled()) {
               LOG.error(MessageFormat.format(errorValue.getMessage(), "Error while executing handler " + handler.getClass()));
            }

            throw new TechnicalConnectorException(errorValue, new Object[]{"Error while executing handler."});
         }
      }

   }

   protected String getCurrentEndpoint(GenericRequest genericRequest) {
      return (String)genericRequest.getRequestMap().get("javax.xml.ws.service.endpoint.address");
   }

   private static URL generateEndpoint(final SOAPMessageContext request) throws MalformedURLException {
      String requestedTarget = (String)request.get("javax.xml.ws.service.endpoint.address");
      String target = EndpointDistributor.getInstance().getActiveEndpoint(requestedTarget);
      request.put("javax.xml.ws.service.endpoint.address", target);
      URL targetURL = new URL(target);
      StringBuilder context = new StringBuilder();
      context.append(targetURL.getProtocol());
      context.append("://");
      context.append(targetURL.getHost());
      if (targetURL.getPort() != -1) {
         context.append(":");
         context.append(targetURL.getPort());
      }

      return new URL(new URL(context.toString()), targetURL.getFile(), new URLStreamHandler() {
         protected URLConnection openConnection(URL url) throws IOException {
            URL target = new URL(url.toString());
            URLConnection connection = target.openConnection();
            connection.setConnectTimeout(Integer.parseInt((String)request.get("connector.soaphandler.connection.connection.timeout")));
            connection.setReadTimeout(Integer.parseInt((String)request.get("connector.soaphandler.connection.request.timeout")));
            return connection;
         }
      });
   }

   protected SOAPMessageContext createSOAPMessageCtx(GenericRequest genericRequest) throws TechnicalConnectorException {
      try {
         SOAPMessage soapMessage = mf.createMessage();
         SOAPPart soapPart = soapMessage.getSOAPPart();
         if (genericRequest.isXopEnabled()) {
            soapMessage.getMimeHeaders().addHeader("Content-Type", "application/xop+xml");
            soapPart.addMimeHeader("Content-ID", "<root.message@ehealth.fgov.be>");
            soapPart.addMimeHeader("Content-Transfer-Encoding", "8bit");
         }

         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
         SOAPBody soapBody = soapEnvelope.getBody();
         soapBody.addDocument(genericRequest.getPayload());
         Map<String, DataHandler> handlers = genericRequest.getDataHandlerMap();

         AttachmentPart part;
         for(Iterator i$ = handlers.entrySet().iterator(); i$.hasNext(); soapMessage.addAttachmentPart(part)) {
            Entry<String, DataHandler> handlerEntry = (Entry)i$.next();
            DataHandler handler = (DataHandler)handlerEntry.getValue();
            part = soapMessage.createAttachmentPart(handler);
            part.setContentType(handler.getContentType());
            if (genericRequest.isXopEnabled()) {
               part.addMimeHeader("Content-Transfer-Encoding", "binary");
               part.setContentId("<" + (String)handlerEntry.getKey() + ">");
            } else {
               part.setContentId((String)handlerEntry.getKey());
            }
         }

         return createSOAPMessageCtx(soapMessage);
      } catch (SOAPException var11) {
         throw translate(var11);
      }
   }

   static {
      try {
         mf = MessageFactory.newInstance();
         scf = SOAPConnectionFactory.newInstance();
      } catch (UnsupportedOperationException var1) {
         throw new IllegalArgumentException(var1);
      } catch (SOAPException var2) {
         throw new IllegalArgumentException(var2);
      }
   }
}
