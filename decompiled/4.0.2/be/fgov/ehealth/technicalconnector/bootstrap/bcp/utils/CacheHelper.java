package be.fgov.ehealth.technicalconnector.bootstrap.bcp.utils;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.SOAPUtils;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.io.StringWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CacheHelper {
   private static final Logger LOG = LoggerFactory.getLogger(CacheHelper.class);
   private static final TransformerFactory TF = TransformerFactory.newInstance();

   private CacheHelper() {
   }

   private static String getKey(Source payload, String xsltLocation) throws TechnicalConnectorException {
      try {
         Transformer keyTransformer = TF.newTransformer(new StreamSource(ConnectorIOUtils.getResourceAsStream(xsltLocation)));
         StringWriter writer = new StringWriter();
         keyTransformer.transform(payload, new StreamResult(writer));
         return writer.toString();
      } catch (Exception var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var4, new Object[0]);
      }
   }

   private static String getValue(Source payload) throws TechnicalConnectorException {
      try {
         Transformer valueTransformer = TF.newTransformer();
         StringWriter writer = new StringWriter();
         valueTransformer.transform(payload, new StreamResult(writer));
         return writer.toString();
      } catch (Exception var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var3, new Object[0]);
      }
   }

   public static void put(Source keySource, Source value, CacheInformation cacheInfo) throws TechnicalConnectorException {
      if (cacheInfo != null && cacheInfo.isKeyTransformType(CacheInformation.KeyTransformType.XSLT)) {
         String key = getKey(keySource, cacheInfo.getKeyTransfromLocation());
         LOG.debug("Using cache-key {}", key);
         cacheInfo.getCache().put(key, getValue(value));
      }

   }

   public static GenericResponse get(Source keySource, CacheInformation cacheInfo) throws TechnicalConnectorException {
      if (cacheInfo.isKeyTransformType(CacheInformation.KeyTransformType.XSLT)) {
         String key = getKey(keySource, cacheInfo.getKeyTransfromLocation());
         LOG.debug("Using key [{}] for lookup", key);
         Cache<String, String> cache = cacheInfo.getCache();
         String content = (String)cache.get(key);
         if (StringUtils.isBlank(content)) {
            throw new IllegalStateException("Unable to get response from cache");
         } else {
            return new GenericResponse(SOAPUtils.newSOAPMessage(content));
         }
      } else {
         throw new IllegalStateException("Unable to get response from cache");
      }
   }
}
