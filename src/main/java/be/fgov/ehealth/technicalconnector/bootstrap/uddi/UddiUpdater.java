package be.fgov.ehealth.technicalconnector.bootstrap.uddi;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.xml.soap.SOAPException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class UddiUpdater {
   public static final String PROP_UDDI_LOCAL_CACHE_DIR = "uddi.local.cache.dir";
   private static final String REQ_FIND_BUSINESS = "<find_business xmlns=\"urn:uddi-org:api_v3\"><authInfo/><findQualifiers><findQualifier>approximateMatch</findQualifier></findQualifiers><name>eHealth-platform</name></find_business>";
   private static final String NS_UDDI_API_V3 = "urn:uddi-org:api_v3";
   private static final Logger LOG = LoggerFactory.getLogger(UddiUpdater.class);
   private static ConfigValidator config = ConfigFactory.getConfigValidator();

   private UddiUpdater() {
      throw new UnsupportedOperationException();
   }

   public static void launch() throws Exception {
      String dirLocation = config.getProperty("uddi.local.cache.dir", System.getProperty("java.io.tmpdir"));
      File dir = new File(dirLocation);
      if (!dir.exists() && !dir.mkdirs()) {
         throw new IOException("Unable to create directory. [" + dirLocation + "]");
      } else {
         File file = new File(dirLocation, "uddi-local.properties");
         if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Unable to create file. [" + file.getAbsolutePath() + "]");
         } else {
            Properties props = new Properties();
            props.putAll(update("acc", config.getProperty("endpoint.uddi.acc", "https://services-acpt.ehealth.fgov.be/registry/uddi/inquiry")));
            props.putAll(update("prd", config.getProperty("endpoint.uddi.prod", "https://services.ehealth.fgov.be/registry/uddi/inquiry")));
            FileOutputStream fos = new FileOutputStream(file);

            try {
               LOG.debug("Storing: " + file.getAbsolutePath());
               props.store(fos, "UddiUpdater");
            } finally {
               ConnectorIOUtils.closeQuietly((Object)fos);
            }

         }
      }
   }

   private static Properties update(String prefix, String endpoint) {
      Properties props = new Properties();

      try {
         Element businessList = invokeUddi("<find_business xmlns=\"urn:uddi-org:api_v3\"><authInfo/><findQualifiers><findQualifier>approximateMatch</findQualifier></findQualifiers><name>eHealth-platform</name></find_business>", endpoint);
         String getServiceDetail = generateGetServiceDetail(businessList);
         Element serviceDetail = invokeUddi(getServiceDetail, endpoint);
         NodeList bindingTemplates = serviceDetail.getElementsByTagNameNS("urn:uddi-org:api_v3", "bindingTemplate");

         for(int i = 0; i < bindingTemplates.getLength(); ++i) {
            Element bindingTemplate = (Element)bindingTemplates.item(i);
            String key = bindingTemplate.getAttribute("serviceKey");
            String value = endpoint(bindingTemplate);
            if (StringUtils.isNotEmpty(value)) {
               props.setProperty(prefix + "-" + key, value);
            }
         }
      } catch (Exception var11) {
         LOG.error("Unable to load endpoints from uddi " + endpoint, var11);
      }

      return props;
   }

   private static String endpoint(Element bindingTemplate) {
      NodeList accessPoints = bindingTemplate.getElementsByTagNameNS("urn:uddi-org:api_v3", "accessPoint");

      for(int j = 0; j < accessPoints.getLength(); ++j) {
         Element accessPoint = (Element)accessPoints.item(j);
         if ("endPoint".equals(accessPoint.getAttribute("useType"))) {
            return accessPoint.getTextContent();
         }
      }

      return "";
   }

   private static Element invokeUddi(String payload, String endpoint) throws TechnicalConnectorException, SOAPException {
      GenericRequest request = new GenericRequest();
      request.setEndpoint(endpoint);
      request.setPayload(payload);
      request.setDefaultHandlerChain();
      return (Element)ServiceFactory.getGenericWsSender().send(request).asNode();
   }

   private static String generateGetServiceDetail(Element businessList) {
      StringBuilder sb = new StringBuilder();
      sb.append("<get_serviceDetail xmlns=\"urn:uddi-org:api_v3\"><authInfo/>");
      NodeList list = businessList.getElementsByTagNameNS("urn:uddi-org:api_v3", "serviceInfo");

      for(int i = 0; i < list.getLength(); ++i) {
         Element el = (Element)list.item(i);
         sb.append("<serviceKey>");
         sb.append(el.getAttribute("serviceKey"));
         sb.append("</serviceKey>");
      }

      sb.append("</get_serviceDetail>");
      return sb.toString();
   }
}
