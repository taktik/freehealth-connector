package be.fgov.ehealth.technicalconnector.bootstrap.bcp.parser;

import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.shutdown.DeleteFileOnExitShutdownHook;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fgov.ehealth.bcp.protocol.v1.Endpoint;
import be.fgov.ehealth.bcp.protocol.v1.StatusType;
import be.fgov.ehealth.bcp.protocol.v2.Service;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.EndPointInformation;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StatusPageParser {
   private static final Logger LOG = LoggerFactory.getLogger(StatusPageParser.class);
   private static final String URI_BCP_V2 = "urn:be:fgov:ehealth:bcp:protocol:v2";
   private static final String URI_BCP_V1 = "urn:be:fgov:ehealth:bcp:protocol:v1";

   private StatusPageParser() {
      throw new UnsupportedOperationException();
   }

   public static EndPointInformation parse(String xml) throws TechnicalConnectorException {
      EndPointInformation info = new EndPointInformation();
      ByteArrayInputStream is = new ByteArrayInputStream(ConnectorIOUtils.toBytes(xml, Charset.UTF_8));

      try {
         XMLInputFactory xmlif = XMLInputFactory.newInstance();
         XMLStreamReader xmlr = xmlif.createXMLStreamReader(is);
         JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{Service.class, be.fgov.ehealth.bcp.protocol.v1.Service.class});
         Unmarshaller um = jaxbContext.createUnmarshaller();
         xmlr.nextTag();
         xmlr.require(1, (String)null, "ServiceList");

         while(true) {
            while(true) {
               while(xmlr.getEventType() != 8) {
                  if (xmlr.getEventType() == 1) {
                     if (xmlr.getLocalName() == "Service" && xmlr.getNamespaceURI() == "urn:be:fgov:ehealth:bcp:protocol:v2") {
                        xmlr.require(1, "urn:be:fgov:ehealth:bcp:protocol:v2", "Service");
                        Service service = (Service)um.unmarshal(xmlr);
                        add(info, service);
                     } else if (xmlr.getLocalName() == "Service" && xmlr.getNamespaceURI() == "urn:be:fgov:ehealth:bcp:protocol:v1") {
                        xmlr.require(1, "urn:be:fgov:ehealth:bcp:protocol:v1", "Service");
                        be.fgov.ehealth.bcp.protocol.v1.Service service = (be.fgov.ehealth.bcp.protocol.v1.Service)um.unmarshal(xmlr);
                        add(info, service);
                     } else {
                        xmlr.next();
                     }
                  } else {
                     xmlr.next();
                  }
               }

               return info;
            }
         }
      } catch (Exception var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var8, new Object[0]);
      }
   }

   private static void add(EndPointInformation info, be.fgov.ehealth.bcp.protocol.v1.Service service) {
      List<String> endpoints = new ArrayList();
      String defaultEndpoint = null;
      String activeEndpoint = null;
      List<Endpoint> endpointList = service.getEndpoints();
      Iterator i$ = endpointList.iterator();

      while(i$.hasNext()) {
         Endpoint endpoint = (Endpoint)i$.next();
         endpoints.add(endpoint.getValue());
         if (endpoint.getStatus() == StatusType.ACTIVE) {
            activeEndpoint = endpoint.getValue();
         }

         if (endpoint.getOrder().toString(10) == "0") {
            defaultEndpoint = endpoint.getValue();
         }
      }

      info.register(service.getId(), activeEndpoint, defaultEndpoint, endpoints, (CacheInformation)null);
   }

   private static void add(EndPointInformation info, Service service) {
      List<String> endpoints = new ArrayList();
      String defaultEndpoint = null;
      String activeEndpoint = null;
      List<be.fgov.ehealth.bcp.protocol.v2.Endpoint> endpointList = service.getEndpointList().getEndpoints();
      Iterator i$ = endpointList.iterator();

      while(i$.hasNext()) {
         be.fgov.ehealth.bcp.protocol.v2.Endpoint endpoint = (be.fgov.ehealth.bcp.protocol.v2.Endpoint)i$.next();
         endpoints.add(endpoint.getValue());
         if (endpoint.getStatus() == be.fgov.ehealth.bcp.protocol.v2.StatusType.ACTIVE) {
            activeEndpoint = endpoint.getValue();
         }

         if (endpoint.getOrder().toString(10) == "0") {
            defaultEndpoint = endpoint.getValue();
         }
      }

      CacheInformation cacheInformation = null;
      if (service.getCache() != null) {
         CacheInformation.CacheType cacheType = CacheInformation.CacheType.valueOf(service.getCache().getStrategy());
         CacheInformation.ExpiryType expiryType = CacheInformation.ExpiryType.valueOf(service.getCache().getExpiry().getType().toUpperCase());
         CacheInformation.KeyTransformType keyTransformType = CacheInformation.KeyTransformType.valueOf(service.getCache().getKey().getTranform().toUpperCase());
         String keyTranformLocation = null;
         if (service.getCache().getKey().isInline()) {
            try {
               File file = File.createTempFile(service.getId().replaceAll(":", "_"), ".xslt");
               DeleteFileOnExitShutdownHook.deleteOnExit(file);
               FileWriter fw = new FileWriter(file);
               IOUtils.write(service.getCache().getKey().getValue(), fw);
               fw.flush();
               fw.close();
               keyTranformLocation = file.getAbsolutePath();
            } catch (IOException var14) {
               LOG.debug("Unable to create inline XSLT file.", var14);
            }
         } else {
            keyTranformLocation = service.getCache().getKey().getValue();
         }

         Duration duration = null;
         javax.xml.datatype.Duration xmlDuration = service.getCache().getExpiry().getDuration();
         if (xmlDuration != null) {
            duration = new Duration(xmlDuration.getTimeInMillis(Calendar.getInstance()));
         } else {
            String value = service.getCache().getExpiry().getValue();
            if (StringUtils.isNotEmpty(value)) {
               duration = Duration.parse(value);
            }
         }

         cacheInformation = new CacheInformation(service.getId(), cacheType, keyTransformType, keyTranformLocation, expiryType, duration);
      }

      info.register(service.getId(), activeEndpoint, defaultEndpoint, endpoints, cacheInformation);
   }
}
