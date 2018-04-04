package org.taktik.connector.business.recipeprojects.common.utils;

import org.taktik.connector.business.recipeprojects.common.utils.xmlunit.RecursiveNameAttributeAndTextQualifier;
import org.taktik.connector.business.recipeprojects.common.utils.xmlunit.RegexDifferenceListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.ElementQualifier;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.w3c.dom.Document;

public class XmlAsserter {
   private static final Logger LOG = Logger.getLogger(XmlAsserter.class);
   private static Map<Class, JAXBContext> jaxbContextMap = new HashMap();
   private static DifferenceListener listener = new RegexDifferenceListener();
   private static ElementQualifier qualifier = new RecursiveNameAttributeAndTextQualifier();

   static {
      XMLUnit.setIgnoreAttributeOrder(true);
      XMLUnit.setIgnoreComments(true);
      XMLUnit.setIgnoreWhitespace(true);
   }

   public static void assertSimilar(Object expected, Object actual) {
      assertSimilar(toString(expected), toString(actual));
   }

   public static void assertSimilar(DOMSource expected, DOMSource actual) {
      assertSimilar(toString(expected), toString(actual));
   }

   public static void assertSimilar(Document expected, Document actual) {
      assertSimilar(toString(expected), toString(actual));
   }

   public static void assertSimilar(String expected, String actual) {
      boolean similar = isSimilar(expected, actual);
      Assert.assertTrue("both xmls should be similar", similar);
   }

   public static boolean isSimilar(String expected, String actual) {
      return assertXml(expected, actual).isSimilar();
   }

   public static List<Difference> getDifferences(DOMSource expected, DOMSource actual) {
      return assertXml(toString(expected), toString(actual)).getDifferences();
   }

   public static XmlAsserter.AssertResult assertXml(String expected, String actual) {
      XmlAsserter.AssertResult result = new XmlAsserter.AssertResult();

      try {
         LOG.debug("expected  : " + linearize(expected));
         LOG.debug("actual    : " + linearize(actual));
         Diff diff = new Diff(expected, actual);
         diff.overrideDifferenceListener(listener);
         diff.overrideElementQualifier(qualifier);
         result.setSimilar(diff.similar());
         LOG.debug("Similar : " + result.isSimilar());
         DetailedDiff detDiff = new DetailedDiff(diff);
         List<Difference> differences = detDiff.getAllDifferences();
         Iterator var7 = differences.iterator();

         while(var7.hasNext()) {
            Difference difference = (Difference)var7.next();
            if (!difference.isRecoverable()) {
               LOG.debug(difference.toString() + " expected :" + difference.getControlNodeDetail() + " but was :" + difference.getTestNodeDetail() + "  " + difference.getDescription());
               result.getDifferences().add(difference);
            }
         }
      } catch (Exception var8) {
         LOG.error(var8.getMessage(), var8);
         Assert.fail(var8.getMessage());
      }

      return result;
   }

   private static String linearize(String xml) {
      String result;
      for(result = xml.replaceAll("[\t\n\r]", ""); result.contains(" <"); result = result.replace(" <", "<")) {
         ;
      }

      return result;
   }

   private static String toString(Document doc) {
      return toString(new DOMSource(doc));
   }

   private static String toString(DOMSource domSource) {
      try {
         StringWriter writer = new StringWriter();
         StreamResult result = new StreamResult(writer);
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         transformer.transform(domSource, result);
         return writer.toString();
      } catch (Exception var5) {
         LOG.error(var5.getMessage(), var5);
         Assert.fail(var5.getMessage());
         return null;
      }
   }

   private static String toString(Object obj) {
      if (obj.getClass().isAnnotationPresent(XmlType.class)) {
         return convert(obj);
      } else if (obj instanceof InputStream) {
         return convert((InputStream)obj);
      } else {
         LOG.info("obj instanceof " + obj.getClass().getName() + " using default toString() method.");
         return obj.toString();
      }
   }

   private static String convert(InputStream is) {
      try {
         StringWriter writer = new StringWriter();
         IOUtils.copy((InputStream)is, (Writer)writer, (String)"UTF-8");
         return writer.toString();
      } catch (IOException var2) {
         LOG.error(var2.getMessage(), var2);
         Assert.fail(var2.getMessage());
         return null;
      }
   }

   private static String convert(Object obj) {
      try {
         Class clazz = obj.getClass();
         if (!jaxbContextMap.containsKey(clazz)) {
            jaxbContextMap.put(clazz, JAXBContext.newInstance(clazz));
         }

         JAXBContext context = (JAXBContext)jaxbContextMap.get(clazz);
         Marshaller marshaller = context.createMarshaller();
         StringWriter writer = new StringWriter();
         if (obj.getClass().isAnnotationPresent(XmlRootElement.class)) {
            marshaller.marshal(obj, (Writer)writer);
         } else if (obj.getClass().isAnnotationPresent(XmlType.class)) {
            JAXBElement jaxbElement = new JAXBElement(new QName("", obj.getClass().getSimpleName()), clazz, obj);
            marshaller.marshal(jaxbElement, (Writer)writer);
         }

         return writer.toString();
      } catch (Exception var6) {
         LOG.error(var6.getMessage(), var6);
         Assert.fail(var6.getMessage());
         return null;
      }
   }

   public static class AssertResult {
      private boolean similar;
      private List<Difference> differences = new ArrayList();

      void setSimilar(boolean similar) {
         this.similar = similar;
      }

      public boolean isSimilar() {
         return this.similar;
      }

      public List<Difference> getDifferences() {
         return this.differences;
      }
   }
}
