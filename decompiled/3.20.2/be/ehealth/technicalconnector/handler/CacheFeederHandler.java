package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.utils.CacheHelper;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class CacheFeederHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(CacheFeederHandler.class);
   private static EndpointDistributor distributor = EndpointDistributor.getInstance();
   private String endpoint;
   private Source request;

   public boolean handleOutbound(SOAPMessageContext context) {
      this.endpoint = (String)context.get("javax.xml.ws.service.endpoint.address");
      if (distributor.mustCache(this.endpoint)) {
         try {
            Node body = context.getMessage().getSOAPBody().cloneNode(true);
            this.request = new DOMSource(ConnectorXmlUtils.getFirstChildElement(body));
         } catch (SOAPException var3) {
            LOG.trace("Unable to determine endpoint and payload", var3);
         }
      }

      return true;
   }

   public boolean handleInbound(SOAPMessageContext context) {
      if (distributor.mustCache(this.endpoint)) {
         try {
            CacheInformation cacheInformation = distributor.getCacheInformation(this.endpoint);
            Node body = context.getMessage().getSOAPBody().cloneNode(true);
            Source response = new DOMSource(ConnectorXmlUtils.getFirstChildElement(body));
            CacheHelper.put(this.request, response, cacheInformation);
         } catch (Exception var5) {
            LOG.error("Unable to put request into cache", var5);
         }
      } else {
         LOG.debug("Request for endpoint [{}], not cached", this.endpoint);
      }

      return true;
   }
}
