package be.fgov.ehealth.schematron.domain;

import be.fgov.ehealth.schematron.utils.EnrichmentFilter;
import be.fgov.ehealth.schematron.utils.LocationAnalyzer;
import be.fgov.ehealth.schematron.utils.LogicalPhysicalMap;
import be.fgov.ehealth.schematron.utils.NamespacePrefixMappings;
import be.fgov.ehealth.schematron.utils.SVRLHandler;
import be.fgov.ehealth.schematron.utils.SVRLXMLWriter;
import be.fgov.ehealth.schematron.utils.SvrlHarvestHandler;
import be.fgov.ehealth.schematron.utils.XmlUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.oclc.purl.dsdl.svrl.SchematronOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public final class SchematronResult {
   private static final Logger LOG = LoggerFactory.getLogger(SchematronResult.class);
   private static final String XSLT_SCH_TO_XML = "/sch_to_xml.xslt";
   private static JAXBContext ctx;
   private final List<String> failedAssertions = new ArrayList();
   private final List<String> successfulReports = new ArrayList();
   private LogicalPhysicalMap locMap = new LogicalPhysicalMap();
   private NamespacePrefixMappings nsMap = new NamespacePrefixMappings();
   private byte[] svrl;
   private File input;

   public SchematronResult(String inSvrl) {
      Validate.isTrue(StringUtils.isNotBlank(inSvrl));
      inSvrl = XmlUtils.removeProcessInstruction(inSvrl);

      try {
         this.svrl = inSvrl.getBytes("UTF-8");
         this.parseSVRL(inSvrl);
      } catch (Exception var3) {
         throw new IllegalArgumentException(var3);
      }
   }

   public void setInput(File input) {
      this.input = input;
   }

   public boolean isValid() {
      return this.failedAssertions.size() == 0;
   }

   public String getEHealthReport() throws MalformedURLException {
      return this.getEHealthReport(Locale.ENGLISH);
   }

   public String getEHealthReport(Locale inLocale) throws MalformedURLException {
      Validate.notNull(this.input);
      byte[] annotatedSvrl = this.annotateWithLocators(this.svrl, this.input);
      ByteArrayOutputStream boas = null;

      try {
         boas = new ByteArrayOutputStream();
         TransformerFactory tFactory = TransformerFactory.newInstance();
         Transformer transformer = tFactory.newTransformer(new StreamSource(SchematronResult.class.getResourceAsStream("/sch_to_xml.xslt")));
         transformer.setParameter("language", inLocale.getLanguage());
         transformer.transform(new StreamSource(new ByteArrayInputStream(annotatedSvrl)), new StreamResult(boas));
         String var6 = new String(boas.toByteArray(), "UTF-8");
         return var6;
      } catch (Exception var10) {
         LOG.error("", var10);
      } finally {
         IOUtils.closeQuietly(boas);
      }

      return null;
   }

   public SchematronOutput getSVRL() throws JAXBException {
      return (SchematronOutput)ctx.createUnmarshaller().unmarshal(new ByteArrayInputStream(this.svrl));
   }

   public String getSVRLAsString() {
      try {
         return new String(this.svrl, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         LOG.error("", var2);
         return null;
      }
   }

   public String[] getFailedMessages() {
      return (String[])this.failedAssertions.toArray(new String[0]);
   }

   public String[] getReportMessages() {
      return (String[])this.successfulReports.toArray(new String[0]);
   }

   private void parseSVRL(String svrl) throws IOException, SAXException, ParserConfigurationException {
      SVRLHandler handler = new SVRLHandler(this.failedAssertions, this.successfulReports);
      InputSource is = new InputSource(new StringReader(svrl));
      is.setEncoding("UTF-16");
      SAXParserFactory.newInstance().newSAXParser().parse(is, handler);
   }

   private byte[] annotateWithLocators(byte[] svrl, File input) {
      try {
         XMLReader parser = XMLReaderFactory.createXMLReader();
         parser.setContentHandler(new SvrlHarvestHandler(this.locMap, this.nsMap));
         parser.parse(new InputSource(new ByteArrayInputStream(svrl)));
         parser = XMLReaderFactory.createXMLReader();
         parser.setContentHandler(new LocationAnalyzer(this.locMap, this.nsMap));
         parser.parse(new InputSource(new FileInputStream(input)));
         LOG.debug("Mapped " + this.locMap.size() + " XPaths to physical locations");
         XMLReader reader = XMLReaderFactory.createXMLReader();
         EnrichmentFilter filter = new EnrichmentFilter(this.locMap, this.nsMap);
         filter.setParent(reader);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         filter.setContentHandler(new SVRLXMLWriter(new OutputStreamWriter(baos)));
         filter.parse(new InputSource(new ByteArrayInputStream(svrl)));
         return baos.toByteArray();
      } catch (Exception var7) {
         LOG.error("Unable to enrich with locations", var7);
         return svrl;
      }
   }

   static {
      try {
         ctx = JAXBContext.newInstance(new Class[]{SchematronOutput.class});
      } catch (JAXBException var1) {
         throw new IllegalArgumentException(var1);
      }
   }
}
