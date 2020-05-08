package be.apb.gfddpp.common.xml;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import org.w3c.dom.Node;

public class XmlBindingTool<E> {
   private Unmarshaller unmarshaller;
   private Marshaller marshaller;

   public XmlBindingTool(Class<E> rootElementClass) throws JAXBException {
      JAXBContext context = JAXBContext.newInstance(rootElementClass);
      this.unmarshaller = context.createUnmarshaller();
      this.marshaller = context.createMarshaller();
      this.marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
   }

   public E parseXML(byte[] input) throws JAXBException {
      XmlBindingTool.CollectingValidationEventHandler handler = new XmlBindingTool.CollectingValidationEventHandler();
      this.unmarshaller.setEventHandler(handler);
      E object = this.unmarshaller.unmarshal(new ByteArrayInputStream(input));
      if (!handler.getMessages().isEmpty()) {
         throw new JAXBException(generateErrorMessage(handler.getMessages()));
      } else {
         return object;
      }
   }

   public void generateXML(E rootElement, Writer writer) throws JAXBException {
      this.marshaller.marshal(rootElement, writer);
   }

   private static String generateErrorMessage(List<String> messages) {
      StringBuilder errorMessage = new StringBuilder("XML parse errors:");
      Iterator var2 = messages.iterator();

      while(var2.hasNext()) {
         String message = (String)var2.next();
         errorMessage.append("\n");
         errorMessage.append(message);
      }

      return errorMessage.toString();
   }

   private static class CollectingValidationEventHandler implements ValidationEventHandler {
      private List<String> messages;

      private CollectingValidationEventHandler() {
         this.messages = new ArrayList();
      }

      public List<String> getMessages() {
         return this.messages;
      }

      public boolean handleEvent(ValidationEvent event) {
         String severity;
         boolean continueParsing;
         switch(event.getSeverity()) {
         case 0:
            severity = "Warning";
            continueParsing = true;
            break;
         case 1:
            severity = "Error";
            continueParsing = true;
            break;
         case 2:
            severity = "Fatal error";
            continueParsing = false;
            break;
         default:
            throw new IllegalArgumentException("unknown severity");
         }

         StringBuilder builder = new StringBuilder(severity);
         builder.append(" parsing ");
         builder.append(this.getLocationDescription(event));
         builder.append(" due to ");
         builder.append(event.getMessage());
         this.messages.add(builder.toString());
         return continueParsing;
      }

      private String getLocationDescription(ValidationEvent event) {
         ValidationEventLocator locator = event.getLocator();
         if (locator == null) {
            return "XML with location unavailable";
         } else {
            StringBuffer msg = new StringBuffer();
            URL url = locator.getURL();
            Object obj = locator.getObject();
            Node node = locator.getNode();
            int line = locator.getLineNumber();
            if (url == null && line == -1) {
               if (obj != null) {
                  msg.append("obj: ");
                  msg.append(obj);
               } else if (node != null) {
                  msg.append("node: ");
                  msg.append(node);
               }
            } else {
               msg.append("line ");
               msg.append(line);
               if (url != null) {
                  msg.append(" of ");
                  msg.append(url);
               }
            }

            return msg.toString();
         }
      }

      // $FF: synthetic method
      CollectingValidationEventHandler(Object x0) {
         this();
      }
   }
}
