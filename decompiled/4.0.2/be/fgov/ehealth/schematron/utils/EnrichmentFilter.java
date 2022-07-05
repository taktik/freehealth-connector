package be.fgov.ehealth.schematron.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

public class EnrichmentFilter extends XMLFilterImpl {
   private static Logger LOG = LoggerFactory.getLogger(EnrichmentFilter.class);
   private NamespacePrefixMappings nsMap;
   private LogicalPhysicalMap locMap;
   private Stack<String> ancestors = new Stack();

   public EnrichmentFilter(LogicalPhysicalMap locMap, NamespacePrefixMappings nsMap) {
      this.nsMap = nsMap;
      this.locMap = locMap;
   }

   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes atts) throws SAXException {
      this.ancestors.push(localName);
      boolean isSvrlElement = namespaceURI.equals("http://purl.oclc.org/dsdl/svrl");
      AttributesImpl atts2;
      if (isSvrlElement && (localName.equals("successful-report") || localName.equals("failed-assert"))) {
         String pseudo = ((Attributes)atts).getValue("location");
         String fixed = this.nsMap.fixupXpath(pseudo);
         atts2 = new AttributesImpl((Attributes)atts);
         atts2.removeAttribute(atts2.getIndex("location"));
         atts2.addAttribute("", "location", "location", "CDATA", fixed);
         atts = atts2;
         PhysicalLocation loc = (PhysicalLocation)this.locMap.get(Utils.trimAttributePart(pseudo));
         if (loc == null) {
            LOG.warn("Cannot find location of pseudo-XPath: <" + pseudo + ">");
         } else {
            atts = loc.addAsAttributes(atts2);
         }
      } else if (isSvrlElement && localName.equals("ns-prefix-in-attribute-values")) {
         return;
      }

      super.startElement(namespaceURI, localName, qualifiedName, (Attributes)atts);
      if (isSvrlElement && localName.equals("schematron-output")) {
         ArrayList<AttributesImpl> attsArray = this.nsMap.asAttributes();
         Iterator<AttributesImpl> iter = attsArray.iterator();

         while(iter.hasNext()) {
            atts2 = (AttributesImpl)iter.next();
            LOG.debug("Creating SVRL mapping for namespace: " + atts2.getValue("uri"));
            this.getContentHandler().startElement("http://purl.oclc.org/dsdl/svrl", "ns-prefix-in-attribute-values", "ns-prefix-in-attribute-values", atts2);
            this.getContentHandler().endElement("http://purl.oclc.org/dsdl/svrl", "ns-prefix-in-attribute-values", "ns-prefix-in-attribute-values");
         }
      }

   }

   public void characters(char[] ch, int start, int length) throws SAXException {
      for(int i = start; i < start + length; ++i) {
         char c = ch[i];
         if (c != ' ' && c != '\t' && c != '\n') {
            super.characters(ch, start, length);
            return;
         }
      }

   }

   public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
      this.ancestors.pop();
      if (!namespaceURI.equals("http://purl.oclc.org/dsdl/svrl") || !localName.equals("ns-prefix-in-attribute-values")) {
         super.endElement(namespaceURI, localName, qualifiedName);
      }
   }
}
