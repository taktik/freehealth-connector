package be.business.connector.recipe.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MapNamespaceContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Properties;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class KmehrHelper {
   public static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";
   private static final Logger LOG;
   private Properties properties = null;
   static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
   static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
   private static final String KMEHR_ASSERT = "kmehr.assert.";
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_0;
   // $FF: synthetic field
   private static Annotation ajc$anno$0;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_1;
   // $FF: synthetic field
   private static Annotation ajc$anno$1;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_2;
   // $FF: synthetic field
   private static Annotation ajc$anno$2;

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(KmehrHelper.class);
   }

   public KmehrHelper(Properties properties) {
      this.properties = properties;
   }

   public void assertValidKmehrPrescription(InputStream xmlFile, String prescriptionType) throws IntegrationModuleException {
      byte[] xmlStream = IOUtils.getBytes(xmlFile);
      this.assertValidKmehrPrescription(xmlStream, prescriptionType);
   }

   public void assertValidNotification(InputStream xmlFile) throws IntegrationModuleException {
      byte[] xmlStream = IOUtils.getBytes(xmlFile);
      this.assertValidNotification(xmlStream);
   }

   public void assertValidFeedback(InputStream xmlFile) throws IntegrationModuleException {
      byte[] xmlStream = IOUtils.getBytes(xmlFile);
      this.assertValidFeedback(xmlStream);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "IntegrationModule#XMLNotificationValidation"
   )
   public void assertValidNotification(byte[] xmlDocument) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_0, this, this, xmlDocument);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, xmlDocument, var3};
      ProceedingJoinPoint var10001 = (new KmehrHelper$AjcClosure1(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = KmehrHelper.class.getDeclaredMethod("assertValidNotification", byte[].class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.IntegrationModule#XMLFeedbackValidation"
   )
   public void assertValidFeedback(byte[] xmlDocument) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_1, this, this, xmlDocument);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, xmlDocument, var3};
      ProceedingJoinPoint var10001 = (new KmehrHelper$AjcClosure3(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = KmehrHelper.class.getDeclaredMethod("assertValidFeedback", byte[].class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.IntegrationModule#XMLPrescriptionValidation"
   )
   public void assertValidKmehrPrescription(byte[] xmlDocument, String prescriptionType) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_2, this, this, xmlDocument, prescriptionType);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, xmlDocument, prescriptionType, var8};
      ProceedingJoinPoint var10001 = (new KmehrHelper$AjcClosure5(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = KmehrHelper.class.getDeclaredMethod("assertValidKmehrPrescription", byte[].class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private void xsdValidate(byte[] xmlDocument, String xsdPropertyName) throws IntegrationModuleException {
      try {
         String xsdName = this.properties.getProperty(xsdPropertyName);
         if (xsdName != null && (new File(xsdName)).exists()) {
            File xsd = new File(xsdName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            if (!factory.getClass().getName().startsWith("org.apache")) {
               LOG.warn("Non supported parser : " + factory.getClass().getName());
            }

            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", new String[]{xsd.getCanonicalPath()});
            DocumentBuilder builderNamespaceAware = factory.newDocumentBuilder();
            builderNamespaceAware.setErrorHandler(new ErrorHandler() {
               public void warning(SAXParseException arg0) throws SAXException {
                  KmehrHelper.LOG.warn("XSD Warning", arg0);
               }

               public void fatalError(SAXParseException arg0) throws SAXException {
                  KmehrHelper.LOG.error("XSD fatalError", arg0);
                  throw arg0;
               }

               public void error(SAXParseException arg0) throws SAXException {
                  KmehrHelper.LOG.error("XSD error", arg0);
                  throw arg0;
               }
            });
            builderNamespaceAware.parse(new ByteArrayInputStream(xmlDocument));
         } else {
            LOG.error("kmehr.XSD property is not correctly set, invalid file " + xsdPropertyName + " = " + xsdName);
            throw new RuntimeException("kmehr.XSD property is not correctly set, invalid file " + xsdPropertyName + " = " + xsdName);
         }
      } catch (IOException | ParserConfigurationException | SAXException var7) {
         throw new IntegrationModuleException(this.getLabel("error.xml.invalid"), var7);
      }
   }

   private String getLabel(String key) {
      return I18nHelper.getLabel(key);
   }

   private void validateXpath(Document doc, String prescriptionType) throws IntegrationModuleException {
      try {
         int i = 1;
         String xpathCountWithVersion1 = "";
         String xpathCountWithoutVersion1 = "";
         String xpathCountWithVersion2 = "";
         String xpathCountWithoutVersion2 = "";
         String keyCountWithoutVersion1 = "";
         String keyCountWithVersion1 = "";
         String keyCountWithoutVersion2 = "";
         String keyCountWithVersion2 = "";
         String xpathConfigWithVersion = "";
         String xpathConfigWithoutVersion = "";
         String keyWithoutVersion = "";
         String keyWithVersion = "";

         while(true) {
            String version = (String)this.properties.get("kmehr.version");
            if (StringUtils.isBlank(version)) {
               keyWithoutVersion = "kmehr.assert." + prescriptionType + "." + i;
               keyCountWithoutVersion1 = "kmehr.assert." + prescriptionType + ".1." + i;
               keyCountWithoutVersion2 = "kmehr.assert." + prescriptionType + ".2." + i;
               xpathConfigWithoutVersion = (String)this.properties.get(keyWithoutVersion);
               xpathCountWithoutVersion1 = (String)this.properties.get(keyCountWithoutVersion1);
               xpathCountWithoutVersion2 = (String)this.properties.get(keyCountWithoutVersion2);
            } else {
               version = version + ".";
               keyWithVersion = "kmehr.assert." + prescriptionType + "." + version + i;
               keyCountWithVersion1 = "kmehr.assert." + prescriptionType + ".1." + i;
               keyCountWithVersion2 = "kmehr.assert." + prescriptionType + ".2." + i;
               xpathConfigWithVersion = (String)this.properties.get(keyWithVersion);
               xpathCountWithVersion1 = (String)this.properties.get(keyCountWithVersion1);
               xpathCountWithVersion2 = (String)this.properties.get(keyCountWithVersion2);
            }

            if (StringUtils.isBlank(xpathConfigWithVersion) && StringUtils.isBlank(xpathConfigWithoutVersion) && StringUtils.isBlank(xpathCountWithVersion1) && StringUtils.isBlank(xpathCountWithVersion2) && StringUtils.isBlank(xpathCountWithoutVersion1) && StringUtils.isBlank(xpathCountWithoutVersion2)) {
               return;
            }

            String[] xpathConfigsWithVersion = StringUtils.isNoneBlank(new CharSequence[]{xpathConfigWithVersion}) ? xpathConfigWithVersion.split(";") : null;
            String[] xpathConfigsWithoutVersion = StringUtils.isNoneBlank(new CharSequence[]{xpathConfigWithoutVersion}) ? xpathConfigWithoutVersion.split(";") : null;
            String[] xpathConfCountWithVersion1 = StringUtils.isNoneBlank(new CharSequence[]{xpathCountWithVersion1}) ? xpathCountWithVersion1.split(";") : null;
            String[] xpathConfCountWithoutVersion1 = StringUtils.isNoneBlank(new CharSequence[]{xpathCountWithoutVersion1}) ? xpathCountWithoutVersion1.split(";") : null;
            String[] xpathConfCountWithVersion2 = StringUtils.isNoneBlank(new CharSequence[]{xpathCountWithVersion2}) ? xpathCountWithVersion2.split(";") : null;
            String[] xpathConfCountWithoutVersion2 = StringUtils.isNoneBlank(new CharSequence[]{xpathCountWithoutVersion2}) ? xpathCountWithoutVersion2.split(";") : null;
            String message = "";
            if (xpathConfigsWithVersion != null && xpathConfigsWithVersion.length < 2) {
               message = "Invalid configuration : '" + keyWithVersion + "=" + xpathConfigWithVersion + "'";
            }

            if (xpathConfigsWithoutVersion != null && xpathConfigsWithoutVersion.length < 2) {
               message = message + "Invalid configuration : '" + keyWithoutVersion + "=" + xpathConfigWithoutVersion + "'";
            }

            if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion1.length < 1) {
               message = "Invalid configuration : '" + keyCountWithVersion1 + "=" + xpathCountWithVersion1 + "'";
            }

            if (xpathConfCountWithoutVersion1 != null && xpathConfCountWithoutVersion1.length < 1) {
               message = message + "Invalid configuration : '" + keyCountWithoutVersion1 + "=" + xpathCountWithoutVersion1 + "'";
            }

            if (xpathConfCountWithVersion2 != null && xpathConfCountWithVersion2.length < 1) {
               message = "Invalid configuration : '" + keyCountWithVersion2 + "=" + xpathCountWithVersion2 + "'";
            }

            if (xpathConfCountWithoutVersion2 != null && xpathConfCountWithoutVersion2.length < 1) {
               message = message + "Invalid configuration : '" + keyCountWithoutVersion2 + "=" + xpathCountWithoutVersion2 + "'";
            }

            if (StringUtils.isNotBlank(message)) {
               throw new IntegrationModuleException(message);
            }

            LOG.debug("validate xpathConfigsWithVersion[" + i + "][" + xpathConfigWithVersion + "] or xpathConfigsWithoutVersion[" + i + "][" + xpathConfigWithoutVersion + "].");
            if (!this.verifyXpath(xpathConfigsWithVersion, doc) && !this.verifyXpath(xpathConfigsWithoutVersion, doc) && !this.verifyXpath(xpathConfCountWithVersion1, xpathConfCountWithVersion2, doc) && !this.verifyXpath(xpathConfCountWithoutVersion1, xpathConfCountWithoutVersion2, doc)) {
               if (xpathConfigsWithVersion != null) {
                  message = "xpathConfigsWithVersion[" + i + "][" + xpathConfigWithVersion + "] is not valide.";
               } else if (xpathConfigsWithoutVersion != null) {
                  message = "xpathConfigsWithoutVersion[" + i + "][" + xpathConfigWithoutVersion + "] is not valide.";
               } else if (xpathConfCountWithVersion1 != null && xpathConfCountWithVersion2 != null) {
                  message = "xpathConfCountWithVersion1[" + i + "][" + xpathCountWithVersion1 + "] is not valide.";
                  message = message + "or xpathConfCountWithVersion2[" + i + "][" + xpathCountWithVersion2 + "] is not valide.";
                  message = message + " or xpathConfCountWithoutVersion1[" + i + "][" + xpathCountWithoutVersion1 + "] is not valide.";
                  message = message + "or xpathConfCountWithoutVersion2[" + i + "][" + xpathCountWithoutVersion2 + "] is not valide.";
               }

               throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"));
            }

            ++i;
         }
      } catch (XPathExpressionException var24) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid"), var24);
      }
   }

   private boolean verifyXpath(String[] xpathConfigs, Document doc) throws XPathExpressionException, NumberFormatException, IntegrationModuleException {
      if (xpathConfigs == null) {
         return false;
      } else {
         String xpathStr = xpathConfigs[0];
         int min = Integer.parseInt(xpathConfigs[1].trim());
         int max = xpathConfigs.length > 2 ? Integer.parseInt(xpathConfigs[2].trim()) : Integer.MAX_VALUE;
         XPath xpath = XPathFactory.newInstance().newXPath();
         NamespaceContext nsCtx = new MapNamespaceContext();
         xpath.setNamespaceContext(nsCtx);
         NodeList nodes = (NodeList)xpath.evaluate(xpathStr, doc, XPathConstants.NODESET);
         if (nodes.getLength() >= min && nodes.getLength() <= max) {
            return true;
         } else {
            LOG.error("FAILED Xpath query : " + xpathStr);
            return false;
         }
      }
   }

   private boolean verifyXpath(String[] xpathConfigs1, String[] xpathConfigs2, Document doc) throws XPathExpressionException {
      if (xpathConfigs1 != null && xpathConfigs2 != null) {
         String xpathStr1 = xpathConfigs1[0];
         String xpathStr2 = xpathConfigs2[0];
         XPath xpath = XPathFactory.newInstance().newXPath();
         NamespaceContext nsCtx = new MapNamespaceContext();
         xpath.setNamespaceContext(nsCtx);
         Double count1 = (Double)xpath.evaluate(xpathStr1, doc, XPathConstants.NUMBER);
         Double count2 = (Double)xpath.evaluate(xpathStr2, doc, XPathConstants.NUMBER);
         if (!Objects.equals(count1, count2)) {
            LOG.error("FAILED Xpath query : " + xpathStr1 + " <==> " + xpathStr2);
            return false;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   static final void assertValidNotification_aroundBody0(KmehrHelper ajc$this, byte[] xmlDocument, JoinPoint var2) {
      ajc$this.xsdValidate(xmlDocument, "notification.XSD");
   }

   // $FF: synthetic method
   static final void assertValidFeedback_aroundBody2(KmehrHelper ajc$this, byte[] xmlDocument, JoinPoint var2) {
      ajc$this.xsdValidate(xmlDocument, "feedback.XSD");
   }

   // $FF: synthetic method
   static final void assertValidKmehrPrescription_aroundBody4(KmehrHelper ajc$this, byte[] xmlDocument, String prescriptionType, JoinPoint var3) {
      try {
         ajc$this.xsdValidate(xmlDocument, "kmehr.XSD");
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(false);
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc2 = builder.parse(new ByteArrayInputStream(xmlDocument));
         ajc$this.validateXpath(doc2, prescriptionType);
      } catch (IOException | ParserConfigurationException | SAXException var9) {
         LOG.debug("Bad Prescription : " + new String(xmlDocument));
         throw new IntegrationModuleException(ajc$this.getLabel("error.xml.invalid"), var9);
      } catch (IntegrationModuleException var10) {
         throw var10;
      } catch (Throwable var11) {
         LOG.error("Error occured : ", var11);
         throw new IntegrationModuleException(ajc$this.getLabel("error.xml.invalid"), var11);
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("KmehrHelper.java", KmehrHelper.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "assertValidNotification", "be.business.connector.recipe.utils.KmehrHelper", "[B", "xmlDocument", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 127);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "assertValidFeedback", "be.business.connector.recipe.utils.KmehrHelper", "[B", "xmlDocument", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 138);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "assertValidKmehrPrescription", "be.business.connector.recipe.utils.KmehrHelper", "[B:java.lang.String", "xmlDocument:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 151);
   }
}
