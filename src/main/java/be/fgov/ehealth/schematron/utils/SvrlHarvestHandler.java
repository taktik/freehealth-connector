package be.fgov.ehealth.schematron.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class SvrlHarvestHandler implements ContentHandler {
   static Logger LOG = LoggerFactory.getLogger(SvrlHarvestHandler.class);
   private NamespacePrefixMappings nsMap;
   private LogicalPhysicalMap locMap;

   public SvrlHarvestHandler(LogicalPhysicalMap locMap, NamespacePrefixMappings nsMap) {
      this.nsMap = nsMap;
      this.locMap = locMap;
   }

   public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
   }

   public void endDocument() throws SAXException {
      LOG.debug("SVRL harvest done; xpath count:" + this.locMap.size());
   }

   public void endElement(String arg0, String arg1, String arg2) throws SAXException {
   }

   public void endPrefixMapping(String arg0) throws SAXException {
   }

   public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
   }

   public void processingInstruction(String arg0, String arg1) throws SAXException {
   }

   public void setDocumentLocator(Locator arg0) {
   }

   public void skippedEntity(String arg0) throws SAXException {
   }

   public void startDocument() throws SAXException {
      LOG.debug("Beginning SVRL harvest");
   }

   public void startElement(String uri, String localName, String arqName, Attributes atts) throws SAXException {
      if (uri.equals("http://purl.oclc.org/dsdl/svrl")) {
         String xpath;
         if (!localName.equals("successful-report") && !localName.equals("failed-assert")) {
            if (localName.equals("ns-prefix-in-attribute-values")) {
               xpath = atts.getValue("prefix");
               String ns = atts.getValue("uri");
               this.nsMap.registerMapping(xpath, ns);
            }
         } else {
            xpath = atts.getValue("location");
            if (this.locMap.get(xpath) == null) {
               xpath = Utils.trimAttributePart(xpath);
               this.locMap.put(xpath, new PhysicalLocation());
            }
         }
      }

   }

   public void startPrefixMapping(String prefix, String url) throws SAXException {
   }
}
