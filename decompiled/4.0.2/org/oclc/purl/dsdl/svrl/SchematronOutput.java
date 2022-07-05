package org.oclc.purl.dsdl.svrl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"text", "ns", "nsPrefixInAttributeValues", "activePattern", "firedRule", "failedAssert", "successfulReport"}
)
@XmlRootElement(
   name = "schematron-output"
)
public class SchematronOutput {
   protected List<String> text;
   protected List<Ns> ns;
   @XmlElement(
      name = "ns-prefix-in-attribute-values"
   )
   protected List<NsPrefixInAttributeValues> nsPrefixInAttributeValues;
   @XmlElement(
      name = "active-pattern"
   )
   protected List<ActivePattern> activePattern;
   @XmlElement(
      name = "fired-rule"
   )
   protected List<FiredRule> firedRule;
   @XmlElement(
      name = "failed-assert"
   )
   protected List<FailedAssert> failedAssert;
   @XmlElement(
      name = "successful-report"
   )
   protected List<SuccessfulReport> successfulReport;
   @XmlAttribute(
      name = "title"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String title;
   @XmlAttribute(
      name = "phase"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String phase;
   @XmlAttribute(
      name = "schemaVersion"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String schemaVersion;

   public SchematronOutput() {
   }

   public List<String> getText() {
      if (this.text == null) {
         this.text = new ArrayList();
      }

      return this.text;
   }

   public List<Ns> getNs() {
      if (this.ns == null) {
         this.ns = new ArrayList();
      }

      return this.ns;
   }

   public List<NsPrefixInAttributeValues> getNsPrefixInAttributeValues() {
      if (this.nsPrefixInAttributeValues == null) {
         this.nsPrefixInAttributeValues = new ArrayList();
      }

      return this.nsPrefixInAttributeValues;
   }

   public List<ActivePattern> getActivePattern() {
      if (this.activePattern == null) {
         this.activePattern = new ArrayList();
      }

      return this.activePattern;
   }

   public List<FiredRule> getFiredRule() {
      if (this.firedRule == null) {
         this.firedRule = new ArrayList();
      }

      return this.firedRule;
   }

   public List<FailedAssert> getFailedAssert() {
      if (this.failedAssert == null) {
         this.failedAssert = new ArrayList();
      }

      return this.failedAssert;
   }

   public List<SuccessfulReport> getSuccessfulReport() {
      if (this.successfulReport == null) {
         this.successfulReport = new ArrayList();
      }

      return this.successfulReport;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String value) {
      this.title = value;
   }

   public String getPhase() {
      return this.phase;
   }

   public void setPhase(String value) {
      this.phase = value;
   }

   public String getSchemaVersion() {
      return this.schemaVersion;
   }

   public void setSchemaVersion(String value) {
      this.schemaVersion = value;
   }
}
