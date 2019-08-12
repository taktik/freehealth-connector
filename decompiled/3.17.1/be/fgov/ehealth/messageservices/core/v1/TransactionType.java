package be.fgov.ehealth.messageservices.core.v1;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import be.ehealth.technicalconnector.adapter.XmlTimeAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION;
import be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.ConfidentialityType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HeadingType;
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType;
import be.fgov.ehealth.standards.kmehr.schema.v1.TextWithLayoutType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "transactionType",
   propOrder = {"confidentiality", "ids", "cds", "date", "time", "author", "redactor", "iscomplete", "isvalidated", "expirationdate", "headingsAndItemsAndTexts", "recorddatetime", "begindate", "enddate"}
)
public class TransactionType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected ConfidentialityType confidentiality;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd"
   )
   protected List<CDTRANSACTION> cds;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   protected AuthorType author;
   protected AuthorType redactor;
   protected Boolean iscomplete;
   protected Boolean isvalidated;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expirationdate;
   @XmlElements({@XmlElement(
   name = "heading",
   type = HeadingType.class
), @XmlElement(
   name = "item",
   type = ItemType.class
), @XmlElement(
   name = "text",
   type = TextType.class
), @XmlElement(
   name = "text-with-layout",
   type = TextWithLayoutType.class,
   nillable = true
), @XmlElement(
   name = "lnk",
   type = LnkType.class
)})
   protected List<Object> headingsAndItemsAndTexts;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime recorddatetime;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime begindate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime enddate;

   public ConfidentialityType getConfidentiality() {
      return this.confidentiality;
   }

   public void setConfidentiality(ConfidentialityType value) {
      this.confidentiality = value;
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDTRANSACTION> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public DateTime getTime() {
      return this.time;
   }

   public void setTime(DateTime value) {
      this.time = value;
   }

   public AuthorType getAuthor() {
      return this.author;
   }

   public void setAuthor(AuthorType value) {
      this.author = value;
   }

   public AuthorType getRedactor() {
      return this.redactor;
   }

   public void setRedactor(AuthorType value) {
      this.redactor = value;
   }

   public Boolean isIscomplete() {
      return this.iscomplete;
   }

   public void setIscomplete(Boolean value) {
      this.iscomplete = value;
   }

   public Boolean isIsvalidated() {
      return this.isvalidated;
   }

   public void setIsvalidated(Boolean value) {
      this.isvalidated = value;
   }

   public DateTime getExpirationdate() {
      return this.expirationdate;
   }

   public void setExpirationdate(DateTime value) {
      this.expirationdate = value;
   }

   public List<Object> getHeadingsAndItemsAndTexts() {
      if (this.headingsAndItemsAndTexts == null) {
         this.headingsAndItemsAndTexts = new ArrayList();
      }

      return this.headingsAndItemsAndTexts;
   }

   public DateTime getRecorddatetime() {
      return this.recorddatetime;
   }

   public void setRecorddatetime(DateTime value) {
      this.recorddatetime = value;
   }

   public DateTime getBegindate() {
      return this.begindate;
   }

   public void setBegindate(DateTime value) {
      this.begindate = value;
   }

   public DateTime getEnddate() {
      return this.enddate;
   }

   public void setEnddate(DateTime value) {
      this.enddate = value;
   }
}
