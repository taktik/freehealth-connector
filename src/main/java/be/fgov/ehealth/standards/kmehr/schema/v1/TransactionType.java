package be.fgov.ehealth.standards.kmehr.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;
import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import org.taktik.connector.technical.adapter.XmlTimeAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION;
import be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "transactionType",
   propOrder = {"confidentiality", "ids", "cds", "date", "time", "author", "redactor", "iscomplete", "isvalidated", "expirationdate", "heading", "item", "text", "textWithLayout", "lnk", "recorddatetime", "version"}
)
public class TransactionType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected ConfidentialityType confidentiality;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDTRANSACTION> cds;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   @XmlElement(
      required = true
   )
   protected AuthorType author;
   protected AuthorType redactor;
   protected boolean iscomplete;
   protected boolean isvalidated;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expirationdate;
   protected List<HeadingType> heading;
   protected List<ItemType> item;
   protected List<TextType> text;
   @XmlElement(
      name = "text-with-layout",
      nillable = true
   )
   protected List<TextWithLayoutType> textWithLayout;
   protected List<LnkType> lnk;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime recorddatetime;
   protected String version;

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

   public boolean isIscomplete() {
      return this.iscomplete;
   }

   public void setIscomplete(boolean value) {
      this.iscomplete = value;
   }

   public boolean isIsvalidated() {
      return this.isvalidated;
   }

   public void setIsvalidated(boolean value) {
      this.isvalidated = value;
   }

   public DateTime getExpirationdate() {
      return this.expirationdate;
   }

   public void setExpirationdate(DateTime value) {
      this.expirationdate = value;
   }

   public List<HeadingType> getHeading() {
      if (this.heading == null) {
         this.heading = new ArrayList();
      }

      return this.heading;
   }

   public List<ItemType> getItem() {
      if (this.item == null) {
         this.item = new ArrayList();
      }

      return this.item;
   }

   public List<TextType> getText() {
      if (this.text == null) {
         this.text = new ArrayList();
      }

      return this.text;
   }

   public List<TextWithLayoutType> getTextWithLayout() {
      if (this.textWithLayout == null) {
         this.textWithLayout = new ArrayList();
      }

      return this.textWithLayout;
   }

   public List<LnkType> getLnk() {
      if (this.lnk == null) {
         this.lnk = new ArrayList();
      }

      return this.lnk;
   }

   public DateTime getRecorddatetime() {
      return this.recorddatetime;
   }

   public void setRecorddatetime(DateTime value) {
      this.recorddatetime = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }
}
