package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultSupplyProblemType",
   propOrder = {"expectedEndOn", "reportedBy", "reportedOn", "contactName", "contactMail", "contactCompany", "phone", "reason", "derogationImports"}
)
public class ConsultSupplyProblemType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExpectedEndOn",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expectedEndOn;
   @XmlElement(
      name = "ReportedBy"
   )
   protected String reportedBy;
   @XmlElement(
      name = "ReportedOn",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime reportedOn;
   @XmlElement(
      name = "ContactName"
   )
   protected String contactName;
   @XmlElement(
      name = "ContactMail"
   )
   protected String contactMail;
   @XmlElement(
      name = "ContactCompany"
   )
   protected String contactCompany;
   @XmlElement(
      name = "Phone"
   )
   protected String phone;
   @XmlElement(
      name = "Reason"
   )
   protected ConsultTextType reason;
   @XmlElement(
      name = "DerogationImport"
   )
   protected List<ConsultDerogationImportType> derogationImports;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultSupplyProblemType() {
   }

   public DateTime getExpectedEndOn() {
      return this.expectedEndOn;
   }

   public void setExpectedEndOn(DateTime value) {
      this.expectedEndOn = value;
   }

   public String getReportedBy() {
      return this.reportedBy;
   }

   public void setReportedBy(String value) {
      this.reportedBy = value;
   }

   public DateTime getReportedOn() {
      return this.reportedOn;
   }

   public void setReportedOn(DateTime value) {
      this.reportedOn = value;
   }

   public String getContactName() {
      return this.contactName;
   }

   public void setContactName(String value) {
      this.contactName = value;
   }

   public String getContactMail() {
      return this.contactMail;
   }

   public void setContactMail(String value) {
      this.contactMail = value;
   }

   public String getContactCompany() {
      return this.contactCompany;
   }

   public void setContactCompany(String value) {
      this.contactCompany = value;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String value) {
      this.phone = value;
   }

   public ConsultTextType getReason() {
      return this.reason;
   }

   public void setReason(ConsultTextType value) {
      this.reason = value;
   }

   public List<ConsultDerogationImportType> getDerogationImports() {
      if (this.derogationImports == null) {
         this.derogationImports = new ArrayList();
      }

      return this.derogationImports;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
