package be.ehealth.technicalconnector.validator.impl.handler;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ForkContentHandler extends DefaultHandler {
   private ContentHandler[] handlers;

   public ForkContentHandler(ContentHandler... handlers) {
      this.handlers = handlers;
   }

   public void setDocumentLocator(Locator locator) {
      ContentHandler[] var2 = this.handlers;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ContentHandler handler = var2[var4];
         handler.setDocumentLocator(locator);
      }

   }

   public void startDocument() throws SAXException {
      ContentHandler[] var1 = this.handlers;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ContentHandler handler = var1[var3];
         handler.startDocument();
      }

   }

   public void endDocument() throws SAXException {
      ContentHandler[] var1 = this.handlers;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ContentHandler handler = var1[var3];
         handler.endDocument();
      }

   }

   public void startPrefixMapping(String prefix, String uri) throws SAXException {
      ContentHandler[] var3 = this.handlers;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ContentHandler handler = var3[var5];
         handler.startPrefixMapping(prefix, uri);
      }

   }

   public void endPrefixMapping(String prefix) throws SAXException {
      ContentHandler[] var2 = this.handlers;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ContentHandler handler = var2[var4];
         handler.endPrefixMapping(prefix);
      }

   }

   public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
      ContentHandler[] var5 = this.handlers;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         ContentHandler handler = var5[var7];
         handler.startElement(uri, localName, qName, atts);
      }

   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      ContentHandler[] var4 = this.handlers;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ContentHandler handler = var4[var6];
         handler.endElement(uri, localName, qName);
      }

   }

   public void characters(char[] ch, int start, int length) throws SAXException {
      ContentHandler[] var4 = this.handlers;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ContentHandler handler = var4[var6];
         handler.characters(ch, start, length);
      }

   }

   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
      ContentHandler[] var4 = this.handlers;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ContentHandler handler = var4[var6];
         handler.ignorableWhitespace(ch, start, length);
      }

   }

   public void processingInstruction(String target, String data) throws SAXException {
      ContentHandler[] var3 = this.handlers;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ContentHandler handler = var3[var5];
         handler.processingInstruction(target, data);
      }

   }

   public void skippedEntity(String name) throws SAXException {
      ContentHandler[] var2 = this.handlers;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ContentHandler handler = var2[var4];
         handler.skippedEntity(name);
      }

   }
}
