package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import oasis.names.tc.saml._1_0.assertion.Assertion;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class SAML10Converter {
   public static final String OASIS_NAMESPACE = "oasis.names.tc.saml._1_0.protocol:oasis.names.tc.saml._1_0.assertion";

   /** @deprecated */
   @Deprecated
   public static Element toElement(Assertion assertionType) {
      return toElement(toXMLString(assertionType));
   }

   /** @deprecated */
   @Deprecated
   public static String toXMLString(Assertion assertion) {
      try {
         JAXBContext context = JAXBContext.newInstance(Assertion.class);
         StringWriter writer = new StringWriter();
         Marshaller marshaller = context.createMarshaller();
         marshaller.marshal(assertion, writer);
         return writer.toString();
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   /** @deprecated */
   @Deprecated
   public static Assertion toAssertion(String assertion) throws IntegrationModuleException {
      try {
         JAXBContext context = JAXBContext.newInstance("oasis.names.tc.saml._1_0.protocol:oasis.names.tc.saml._1_0.assertion");
         Unmarshaller unmarshaller = context.createUnmarshaller();
         return (Assertion)unmarshaller.unmarshal(new StringReader(assertion));
      } catch (Exception var3) {
         throw new IntegrationModuleException(var3);
      }
   }

   public static Element toElement(String assertion) {
      try {
         DOMParser parser = new DOMParser();
         InputSource source = new InputSource(new StringReader(assertion));
         parser.parse(source);
         return parser.getDocument().getDocumentElement();
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public static String toXMLString(Element element) {
      try {
         Source source = new DOMSource(element);
         StringWriter stringWriter = new StringWriter();
         Result result = new StreamResult(stringWriter);
         TransformerFactory factory = TransformerFactory.newInstance();
         Transformer transformer = factory.newTransformer();
         transformer.transform(source, result);
         return stringWriter.getBuffer().toString();
      } catch (Exception var6) {
         throw new RuntimeException(var6);
      }
   }
}
