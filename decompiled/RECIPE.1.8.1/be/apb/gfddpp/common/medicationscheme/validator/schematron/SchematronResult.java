package be.apb.gfddpp.common.medicationscheme.validator.schematron;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SchematronResult {
   private String systemID;
   private String svrl;
   private final List<String> failedAssertions = new ArrayList();
   private final List<String> warningAssertions = new ArrayList();
   private final List<String> successfulReports = new ArrayList();

   public SchematronResult(String systemID) {
      this.systemID = systemID;
   }

   public String getSystemID() {
      return this.systemID;
   }

   public boolean isValid() {
      return this.failedAssertions.size() == 0;
   }

   public void setSVRL(String svrl) throws IOException, SAXException, ParserConfigurationException {
      this.svrl = this.removeXMLheader(svrl);
      this.parseSVRL();
   }

   public String getSVRLAsString() {
      return this.svrl;
   }

   public List<String> getErrors() {
      return this.failedAssertions;
   }

   public List<String> getWarnings() {
      return this.warningAssertions;
   }

   private void parseSVRL() throws IOException, SAXException, ParserConfigurationException {
      SVRLHandler handler = new SVRLHandler(this.failedAssertions, this.warningAssertions, this.successfulReports);
      InputSource is = new InputSource(new StringReader(this.svrl));
      is.setEncoding("UTF-16");
      SAXParserFactory.newInstance().newSAXParser().parse(is, handler);
   }

   private String removeXMLheader(String svrlAsXml) {
      int firstLineEnd = svrlAsXml.indexOf(10);
      String xmlTag = "<?xml ";
      return !svrlAsXml.startsWith(xmlTag) && !svrlAsXml.startsWith(xmlTag, 1) && !svrlAsXml.startsWith(xmlTag, 2) && !svrlAsXml.startsWith(xmlTag, 3) ? svrlAsXml : svrlAsXml.substring(firstLineEnd + 1);
   }
}
