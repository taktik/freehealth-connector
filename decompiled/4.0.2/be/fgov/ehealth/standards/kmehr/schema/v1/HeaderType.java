package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import be.ehealth.technicalconnector.adapter.XmlTimeAdapter;
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
   name = "headerType",
   propOrder = {"confidentiality", "standard", "ids", "date", "time", "sender", "recipients", "urgency", "acknowledgment", "texts", "lnks", "expirationdate", "externalsource"}
)
public class HeaderType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected ConfidentialityType confidentiality;
   @XmlElement(
      required = true
   )
   protected StandardType standard;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDKMEHR> ids;
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
   protected SenderType sender;
   @XmlElement(
      name = "recipient",
      required = true
   )
   protected List<RecipientType> recipients;
   protected UrgencyType urgency;
   protected AcknowledgmentType acknowledgment;
   @XmlElement(
      name = "text"
   )
   protected List<TextType> texts;
   @XmlElement(
      name = "lnk"
   )
   protected List<LnkType> lnks;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expirationdate;
   protected Externalsource externalsource;

   public HeaderType() {
   }

   public ConfidentialityType getConfidentiality() {
      return this.confidentiality;
   }

   public void setConfidentiality(ConfidentialityType value) {
      this.confidentiality = value;
   }

   public StandardType getStandard() {
      return this.standard;
   }

   public void setStandard(StandardType value) {
      this.standard = value;
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
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

   public SenderType getSender() {
      return this.sender;
   }

   public void setSender(SenderType value) {
      this.sender = value;
   }

   public List<RecipientType> getRecipients() {
      if (this.recipients == null) {
         this.recipients = new ArrayList();
      }

      return this.recipients;
   }

   public UrgencyType getUrgency() {
      return this.urgency;
   }

   public void setUrgency(UrgencyType value) {
      this.urgency = value;
   }

   public AcknowledgmentType getAcknowledgment() {
      return this.acknowledgment;
   }

   public void setAcknowledgment(AcknowledgmentType value) {
      this.acknowledgment = value;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }

   public List<LnkType> getLnks() {
      if (this.lnks == null) {
         this.lnks = new ArrayList();
      }

      return this.lnks;
   }

   public DateTime getExpirationdate() {
      return this.expirationdate;
   }

   public void setExpirationdate(DateTime value) {
      this.expirationdate = value;
   }

   public Externalsource getExternalsource() {
      return this.externalsource;
   }

   public void setExternalsource(Externalsource value) {
      this.externalsource = value;
   }
}
