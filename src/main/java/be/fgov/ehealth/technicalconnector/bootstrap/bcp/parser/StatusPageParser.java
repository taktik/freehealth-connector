package be.fgov.ehealth.technicalconnector.bootstrap.bcp.parser;

import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.EndPointInformation;
import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.lang.ArrayUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class StatusPageParser {
   private static final String NS_BCP = "urn:be:fgov:ehealth:bcp:protocol:v1";
   private static final QName QNAME_SERVICE = new QName("urn:be:fgov:ehealth:bcp:protocol:v1", "Service");
   private static final QName QNAME_ENDPOINT = new QName("urn:be:fgov:ehealth:bcp:protocol:v1", "Endpoint");

   private StatusPageParser() {
      throw new UnsupportedOperationException();
   }

   public static EndPointInformation parse(String xml) throws TechnicalConnectorException {
      ByteArrayInputStream is = new ByteArrayInputStream(ConnectorIOUtils.toBytes(xml, Charset.UTF_8));

      try {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setNamespaceAware(true);
         SAXParser saxParser = factory.newSAXParser();
         StatusPageParser.SaxHandler handler = new StatusPageParser.SaxHandler();
         saxParser.parse(is, handler);
         return handler.getInfo();
      } catch (Exception var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var5, new Object[0]);
      }
   }

   static class SaxHandler extends DefaultHandler {
      private EndPointInformation info = new EndPointInformation();
      private String serviceName;
      private String activeEndpoint;
      private String defaultEnpoint;
      private Map<Integer, String> endpoints = new TreeMap();
      private Integer order;
      private boolean active;
      private String endpoint;

      public EndPointInformation getInfo() {
         return this.info;
      }

      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         if ("urn:be:fgov:ehealth:bcp:protocol:v1".equals(uri)) {
            if (localName.equals(StatusPageParser.QNAME_SERVICE.getLocalPart())) {
               this.serviceName = attributes.getValue("", "Id");
               this.endpoints.clear();
            } else if (localName.equals(StatusPageParser.QNAME_ENDPOINT.getLocalPart())) {
               this.order = Integer.parseInt(attributes.getValue("", "Order"));
               this.active = "ACTIVE".equals(attributes.getValue("", "Status"));
            }
         }

      }

      public void characters(char[] ch, int start, int length) throws SAXException {
         this.endpoint = (new String(ArrayUtils.clone(ch), start, length)).trim();
      }

      public void endElement(String uri, String localName, String qName) throws SAXException {
         if ("urn:be:fgov:ehealth:bcp:protocol:v1".equals(uri)) {
            if (localName.equals(StatusPageParser.QNAME_SERVICE.getLocalPart())) {
               this.info.register(this.serviceName, this.activeEndpoint, this.defaultEnpoint, this.endpoints.values());
               this.activeEndpoint = null;
            } else if (localName.equals(StatusPageParser.QNAME_ENDPOINT.getLocalPart())) {
               this.endpoints.put(this.order, this.endpoint);
               if (this.active) {
                  this.activeEndpoint = this.endpoint;
               }

               if (this.order.equals(Integer.valueOf(0))) {
                  this.defaultEnpoint = this.endpoint;
               }

               this.active = false;
            }
         }

      }
   }
}
