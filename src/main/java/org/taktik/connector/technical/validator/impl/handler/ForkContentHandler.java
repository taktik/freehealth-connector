package org.taktik.connector.technical.validator.impl.handler;

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
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.setDocumentLocator(locator);
      }

   }

   public void startDocument() throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.startDocument();
      }

   }

   public void endDocument() throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.endDocument();
      }

   }

   public void startPrefixMapping(String prefix, String uri) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.startPrefixMapping(prefix, uri);
      }

   }

   public void endPrefixMapping(String prefix) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.endPrefixMapping(prefix);
      }

   }

   public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.startElement(uri, localName, qName, atts);
      }

   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.endElement(uri, localName, qName);
      }

   }

   public void characters(char[] ch, int start, int length) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.characters(ch, start, length);
      }

   }

   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.ignorableWhitespace(ch, start, length);
      }

   }

   public void processingInstruction(String target, String data) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.processingInstruction(target, data);
      }

   }

   public void skippedEntity(String name) throws SAXException {
      ContentHandler[] arr$ = this.handlers;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentHandler handler = arr$[i$];
         handler.skippedEntity(name);
      }

   }
}
