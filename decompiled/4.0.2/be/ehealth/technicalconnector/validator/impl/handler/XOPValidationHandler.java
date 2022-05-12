package be.ehealth.technicalconnector.validator.impl.handler;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XOPValidationHandler extends DefaultHandler {
   private boolean xop;
   private int endElementAfterXOP;
   private boolean enabled;

   public XOPValidationHandler(boolean enable) {
      this.enabled = enable;
   }

   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if (this.enabled) {
         this.resetXOP();
         if ("Include".equals(localName) && "http://www.w3.org/2004/08/xop/include".equals(uri) && attributes.getValue("href") != null) {
            this.xop = true;
         }
      }

   }

   public void characters(char[] ch, int start, int length) throws SAXException {
      if (this.xop) {
         String content = StringUtils.substring(new String(ch), start, start + length);
         if (StringUtils.isNotBlank(content)) {
            this.xop = false;
         }
      }

   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (this.xop) {
         ++this.endElementAfterXOP;
      }

   }

   public boolean isXop() {
      return this.xop;
   }

   private void resetXOP() {
      if (this.endElementAfterXOP == 2) {
         this.xop = false;
         this.endElementAfterXOP = 0;
      }

   }
}
