package be.fgov.ehealth.schematron.utils;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public final class SVRLHandler extends DefaultHandler {
   private static final String FAILED_ASSERT_ELT = "svrl:failed-assert";
   private static final String TEXT_ELT = "svrl:text";
   private static final String SUCCESSFUL_REPORT_ELT = "svrl:successful-report";
   private static final String LOCATION_ATT = "location";
   private StringBuffer chars = new StringBuffer();
   private StringBuffer message = new StringBuffer();
   private String lastElement;
   private List<String> failedAssertions;
   private List<String> successfulReports;
   private boolean underAssertorReport = false;

   public SVRLHandler() {
      this.failedAssertions = new ArrayList();
      this.successfulReports = new ArrayList();
   }

   public SVRLHandler(List<String> failedAssertions, List<String> successfulReports) {
      this.failedAssertions = failedAssertions;
      this.successfulReports = successfulReports;
   }

   public void startElement(String uri, String localName, String rawName, Attributes attributes) {
      if (rawName.equals("svrl:failed-assert")) {
         this.message.append("[assert] " + attributes.getValue("location"));
         this.lastElement = "svrl:failed-assert";
         this.underAssertorReport = true;
      } else if (rawName.equals("svrl:successful-report")) {
         this.message.append("[report] " + attributes.getValue("location"));
         this.lastElement = "svrl:successful-report";
         this.underAssertorReport = true;
      } else {
         if (!rawName.equals("svrl:text") || !this.underAssertorReport) {
            return;
         }

         this.getCharacters();
      }

   }

   public void endElement(String namespaceURL, String localName, String rawName) {
      if (rawName.equals("svrl:text") && this.underAssertorReport) {
         this.message.append(" - " + this.getCharacters());
         if (this.lastElement.equals("svrl:failed-assert")) {
            this.failedAssertions.add(this.getMessage());
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
      String retstr = this.chars.toString();
      this.chars.setLength(0);
      return retstr;
   }

   private String getMessage() {
      String retstr = this.message.toString();
      this.message.setLength(0);
      return retstr;
   }
}
