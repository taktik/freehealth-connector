package be.apb.gfddpp.common.medicationscheme.validator.schematron;

import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SVRLHandler extends DefaultHandler {
   private static final String FAILED_ASSERT_ELT = "svrl:failed-assert";
   private static final String TEXT_ELT = "svrl:text";
   private static final String SUCCESSFUL_REPORT_ELT = "svrl:successful-report";
   private static final String LOCATION_ATT = "location";
   private static final String ROLE_ATT = "role";
   private StringBuffer chars = new StringBuffer();
   private StringBuffer message = new StringBuffer();
   private String lastElement;
   private List<String> failedAssertions;
   private List<String> warningAssertions;
   private boolean warning;
   private List<String> successfulReports;
   private boolean underAssertorReport;

   public SVRLHandler(List<String> failedAssertions, List<String> warningAssertions, List<String> successfulReports) {
      this.failedAssertions = failedAssertions;
      this.warningAssertions = warningAssertions;
      this.successfulReports = successfulReports;
   }

   public void startElement(String uri, String localName, String rawName, Attributes attributes) {
      if (rawName.equals("svrl:failed-assert")) {
         if ("WARNING".equalsIgnoreCase(attributes.getValue("role"))) {
            this.warning = true;
         } else {
            this.warning = false;
         }

         this.lastElement = "svrl:failed-assert";
         this.underAssertorReport = true;
      } else if (rawName.equals("svrl:successful-report")) {
         this.message.append("[report] ").append(attributes.getValue("location"));
         this.lastElement = "svrl:successful-report";
         this.underAssertorReport = true;
      } else if (rawName.equals("svrl:text") && this.underAssertorReport) {
         this.getCharacters();
      }

   }

   public void endElement(String namespaceURL, String localName, String rawName) {
      if (rawName.equals("svrl:text") && this.underAssertorReport) {
         this.message.append(this.getCharacters());
         if (this.lastElement.equals("svrl:failed-assert")) {
            if (this.warning) {
               this.warningAssertions.add(this.getMessage());
               this.warning = false;
            } else {
               this.failedAssertions.add(this.getMessage());
            }
         } else {
            this.successfulReports.add(this.getMessage());
         }

         this.underAssertorReport = false;
      }

      this.lastElement = "";
   }

   public void characters(char[] ch, int start, int length) {
      this.chars.append(ch, start, length);
   }

   private String getCharacters() {
      String retstr = this.chars.toString().trim();
      this.chars.setLength(0);
      return retstr;
   }

   private String getMessage() {
      String retstr = this.message.toString();
      this.message.setLength(0);
      return retstr;
   }
}
