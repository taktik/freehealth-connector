package be.fgov.ehealth.schematron.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class LocationAnalyzer extends DefaultHandler implements ContentHandler, LexicalHandler {
   private static final Logger LOG = LoggerFactory.getLogger(LocationAnalyzer.class);
   private LogicalPhysicalMap locMap;
   private NamespacePrefixMappings nsMap;
   private TreeContext tc = new TreeContext();
   private Locator loc;

   public LocationAnalyzer(LogicalPhysicalMap locMap, NamespacePrefixMappings nsMap) {
      this.locMap = locMap;
      this.nsMap = nsMap;
   }

   public void endDocument() throws SAXException {
      LOG.debug("Map enrichment done.");
   }

   public void endElement(String arg0, String arg1, String arg2) throws SAXException {
      this.tc.onEndElement();
   }

   public void setDocumentLocator(Locator loc) {
      this.loc = loc;
   }

   public void startDocument() throws SAXException {
      LOG.debug("Beginning map enrichment");
   }

   public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
      this.tc.onStartElement(uri, localName);
      this.locMap.handleMapping(this.tc, this.loc);
      if (uri.length() > 0) {
         String regPrefix = this.nsMap.prefixForNs(uri);
         if (regPrefix == null) {
            String prefix = null;
            if (qName.indexOf(":") != -1) {
               prefix = qName.substring(0, qName.indexOf(":"));
            }

            if (prefix != null) {
               this.nsMap.registerMapping(prefix, uri);
            } else {
               this.nsMap.registerUnprefixed(uri);
            }
         }
      }

   }

   public void comment(char[] arg0, int arg1, int arg2) throws SAXException {
   }

   public void endCDATA() throws SAXException {
   }

   public void endDTD() throws SAXException {
   }

   public void endEntity(String arg0) throws SAXException {
   }

   public void startCDATA() throws SAXException {
   }

   public void startDTD(String arg0, String arg1, String arg2) throws SAXException {
   }

   public void startEntity(String arg0) throws SAXException {
   }
}
