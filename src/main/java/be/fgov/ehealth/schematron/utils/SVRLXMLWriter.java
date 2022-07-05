package be.fgov.ehealth.schematron.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.helpers.XMLFilterImpl;

public class SVRLXMLWriter extends XMLFilterImpl {
   public String ETAGO = "<";
   public String ETAGC = ">";
   public String PIO = "<?";
   public String PIC = "?>";
   private final Attributes EMPTY_ATTS = new AttributesImpl();
   private Hashtable prefixTable;
   private Hashtable forcedDeclTable;
   private Hashtable doneDeclTable;
   private int elementLevel = 0;
   private Writer output;
   private NamespaceSupport nsSupport;
   private int prefixCounter = 0;

   public SVRLXMLWriter() {
      this.init((Writer)null);
   }

   public SVRLXMLWriter(Writer writer) {
      this.init(writer);
   }

   public SVRLXMLWriter(XMLReader xmlreader) {
      super(xmlreader);
      this.init((Writer)null);
   }

   public SVRLXMLWriter(XMLReader xmlreader, Writer writer) {
      super(xmlreader);
      this.init(writer);
   }

   private void init(Writer writer) {
      this.setOutput(writer);
      this.nsSupport = new NamespaceSupport();
      this.prefixTable = new Hashtable();
      this.forcedDeclTable = new Hashtable();
      this.doneDeclTable = new Hashtable();
   }

   public void reset() {
      this.elementLevel = 0;
      this.prefixCounter = 0;
      this.nsSupport.reset();
   }

   public void flush() throws IOException {
      this.output.flush();
   }

   public void setOutput(Writer writer) {
      if (writer == null) {
         this.output = new OutputStreamWriter(System.out);
      } else {
         this.output = writer;
      }

   }

   public void setPrefix(String uri, String prefix) {
      this.prefixTable.put(uri, prefix);
   }

   public String getPrefix(String uri) {
      return (String)this.prefixTable.get(uri);
   }

   public void forceNSDecl(String uri) {
      this.forcedDeclTable.put(uri, Boolean.TRUE);
   }

   public void forceNSDecl(String uri, String prefix) {
      this.setPrefix(uri, prefix);
      this.forceNSDecl(uri);
   }

   public void startDocument() throws SAXException {
      this.reset();
      this.write(this.ETAGO + "?xml version=\"1.0\" standalone=\"yes\"?>\n\n");
      super.startDocument();
   }

   public void endDocument() throws SAXException {
      this.write('\n');
      super.endDocument();

      try {
         this.flush();
      } catch (IOException var2) {
         throw new SAXException(var2);
      }
   }

   public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
      ++this.elementLevel;
      this.nsSupport.pushContext();
      this.write(this.ETAGO);
      this.writeName(uri, localName, qName, true);
      this.writeAttributes(atts);
      if (this.elementLevel == 1) {
         this.forceNSDecls();
      }

      this.writeNSDecls();
      this.write(this.ETAGC);
      super.startElement(uri, localName, qName, atts);
   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      this.write(this.ETAGO + "/");
      this.writeName(uri, localName, qName, true);
      this.write(this.ETAGC);
      if (this.elementLevel == 1) {
         this.write('\n');
      }

      super.endElement(uri, localName, qName);
      this.nsSupport.popContext();
      --this.elementLevel;
   }

   public void characters(char[] ch, int start, int len) throws SAXException {
      this.writeEsc(ch, start, len, false);
      super.characters(ch, start, len);
   }

   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
      this.writeEsc(ch, start, length, false);
      super.ignorableWhitespace(ch, start, length);
   }

   public void processingInstruction(String target, String data) throws SAXException {
      this.write(this.PIO);
      this.write(target);
      this.write(' ');
      this.write(data);
      this.write(this.PIC);
      if (this.elementLevel < 1) {
         this.write('\n');
      }

      super.processingInstruction(target, data);
   }

   public void emptyElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
      this.nsSupport.pushContext();
      this.write(this.ETAGO);
      this.writeName(uri, localName, qName, true);
      this.writeAttributes(atts);
      if (this.elementLevel == 1) {
         this.forceNSDecls();
      }

      this.writeNSDecls();
      this.write("/" + this.ETAGC);
      super.startElement(uri, localName, qName, atts);
      super.endElement(uri, localName, qName);
   }

   public void startElement(String uri, String localName) throws SAXException {
      this.startElement(uri, localName, "", this.EMPTY_ATTS);
   }

   public void startElement(String localName) throws SAXException {
      this.startElement("", localName, "", this.EMPTY_ATTS);
   }

   public void endElement(String uri, String localName) throws SAXException {
      this.endElement(uri, localName, "");
   }

   public void endElement(String localName) throws SAXException {
      this.endElement("", localName, "");
   }

   public void emptyElement(String uri, String localName) throws SAXException {
      this.emptyElement(uri, localName, "", this.EMPTY_ATTS);
   }

   public void emptyElement(String localName) throws SAXException {
      this.emptyElement("", localName, "", this.EMPTY_ATTS);
   }

   public void dataElement(String uri, String localName, String qName, Attributes atts, String content) throws SAXException {
      this.startElement(uri, localName, qName, atts);
      this.characters(content);
      this.endElement(uri, localName, qName);
   }

   public void dataElement(String uri, String localName, String content) throws SAXException {
      this.dataElement(uri, localName, "", this.EMPTY_ATTS, content);
   }

   public void dataElement(String localName, String content) throws SAXException {
      this.dataElement("", localName, "", this.EMPTY_ATTS, content);
   }

   public void characters(String data) throws SAXException {
      char[] ch = data.toCharArray();
      this.characters(ch, 0, ch.length);
   }

   private void forceNSDecls() {
      Enumeration prefixes = this.forcedDeclTable.keys();

      while(prefixes.hasMoreElements()) {
         String prefix = (String)prefixes.nextElement();
         this.doPrefix(prefix, (String)null, true);
      }

   }

   private String doPrefix(String uri, String qName, boolean isElement) {
      String defaultNS = this.nsSupport.getURI("");
      if ("".equals(uri)) {
         if (isElement && defaultNS != null) {
            this.nsSupport.declarePrefix("", "");
         }

         return null;
      } else {
         String prefix;
         if (isElement && defaultNS != null && uri.equals(defaultNS)) {
            prefix = "";
         } else {
            prefix = this.nsSupport.getPrefix(uri);
         }

         if (prefix != null) {
            return prefix;
         } else {
            prefix = (String)this.doneDeclTable.get(uri);
            if (prefix != null && ((!isElement || defaultNS != null) && "".equals(prefix) || this.nsSupport.getURI(prefix) != null)) {
               prefix = null;
            }

            if (prefix == null) {
               prefix = (String)this.prefixTable.get(uri);
               if (prefix != null && ((!isElement || defaultNS != null) && "".equals(prefix) || this.nsSupport.getURI(prefix) != null)) {
                  prefix = null;
               }
            }

            if (prefix == null && qName != null && !"".equals(qName)) {
               int i = qName.indexOf(58);
               if (i == -1) {
                  if (isElement && defaultNS == null) {
                     prefix = "";
                  }
               } else {
                  prefix = qName.substring(0, i);
               }
            }

            while(prefix == null || this.nsSupport.getURI(prefix) != null) {
               prefix = "__NS" + ++this.prefixCounter;
            }

            this.nsSupport.declarePrefix(prefix, uri);
            this.doneDeclTable.put(uri, prefix);
            return prefix;
         }
      }
   }

   private void write(char c) throws SAXException {
      try {
         this.output.write(c);
      } catch (IOException var3) {
         throw new SAXException(var3);
      }
   }

   private void write(String s) throws SAXException {
      try {
         this.output.write(s);
      } catch (IOException var3) {
         throw new SAXException(var3);
      }
   }

   private void writeAttributes(Attributes atts) throws SAXException {
      int len = atts.getLength();

      for(int i = 0; i < len; ++i) {
         char[] ch = atts.getValue(i).toCharArray();
         this.write(' ');
         this.writeName(atts.getURI(i), atts.getLocalName(i), atts.getQName(i), false);
         this.write("=\"");
         this.writeEsc(ch, 0, ch.length, true);
         this.write('"');
      }

   }

   private void writeEsc(char[] ch, int start, int length, boolean isAttVal) throws SAXException {
      for(int i = start; i < start + length; ++i) {
         switch (ch[i]) {
            case '"':
               if (isAttVal) {
                  this.write("&quot;");
               } else {
                  this.write('"');
               }
               break;
            case '&':
               this.write("&amp;");
               break;
            case '<':
               this.write("&lt;");
               break;
            case '>':
               this.write("&gt;");
               break;
            default:
               if (ch[i] > 127) {
                  this.write("&#x");
                  this.write(Integer.toString(ch[i], 16));
                  this.write(';');
               } else {
                  this.write(ch[i]);
               }
         }
      }

   }

   private void writeNSDecls() throws SAXException {
      Enumeration prefixes = this.nsSupport.getDeclaredPrefixes();

      while(prefixes.hasMoreElements()) {
         String prefix = (String)prefixes.nextElement();
         String uri = this.nsSupport.getURI(prefix);
         if (uri == null) {
            uri = "";
         }

         char[] ch = uri.toCharArray();
         this.write(' ');
         if ("".equals(prefix)) {
            this.write("xmlns=\"");
         } else {
            this.write("xmlns:");
            this.write(prefix);
            this.write("=\"");
         }

         this.writeEsc(ch, 0, ch.length, true);
         this.write('"');
      }

   }

   private void writeName(String uri, String localName, String qName, boolean isElement) throws SAXException {
      String prefix = this.doPrefix(uri, qName, isElement);
      if (prefix != null && !"".equals(prefix)) {
         this.write(prefix);
         this.write(':');
      }

      this.write(localName);
   }
}
