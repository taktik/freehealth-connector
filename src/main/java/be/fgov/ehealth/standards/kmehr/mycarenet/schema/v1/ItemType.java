package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;
import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDITEM;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.LnkType;
import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHR;
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
   name = "itemType",
   propOrder = {"confidentiality", "ids", "cds", "contents", "texts", "author", "beginmoment", "endmoment", "iscomplete", "isvalidated", "lifecycle", "isrelevant", "severity", "certainty", "temporality", "urgency", "quantity", "strength", "frequency", "sites", "cost", "dayperiods", "duration", "posology", "regimen", "deliverydate", "renewal", "route", "batch", "instructionforoverdosing", "instructionforpatient", "instructionforreimbursement", "issubstitutionallowed", "feedback", "locals", "recorddatetime", "lnks"}
)
public class ItemType implements Serializable {
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
   protected List<CDITEM> cds;
   @XmlElement(
      name = "content"
   )
   protected List<ContentType> contents;
   @XmlElement(
      name = "text"
   )
   protected List<TextType> texts;
   protected AuthorType author;
   protected MomentType beginmoment;
   protected MomentType endmoment;
   protected Boolean iscomplete;
   protected Boolean isvalidated;
   protected LifecycleType lifecycle;
   protected Boolean isrelevant;
   protected SeverityType severity;
   protected CertaintyType certainty;
   protected TemporalityType temporality;
   protected UrgencyType urgency;
   protected QuantityType quantity;
   protected StrengthType strength;
   protected FrequencyType frequency;
   @XmlElement(
      name = "site"
   )
   protected List<SiteType> sites;
   protected CostType cost;
   @XmlElement(
      name = "dayperiod"
   )
   protected List<DayperiodType> dayperiods;
   protected DurationType duration;
   protected Posology posology;
   protected Regimen regimen;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime deliverydate;
   protected RenewalType renewal;
   protected RouteType route;
   protected String batch;
   protected TextType instructionforoverdosing;
   protected TextType instructionforpatient;
   protected TextType instructionforreimbursement;
   protected Boolean issubstitutionallowed;
   protected Feedback feedback;
   @XmlElement(
      name = "local"
   )
   protected List<LocalitemattributeType> locals;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime recorddatetime;
   @XmlElement(
      name = "lnk"
   )
   protected List<LnkType> lnks;

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

   public List<CDITEM> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public List<ContentType> getContents() {
      if (this.contents == null) {
         this.contents = new ArrayList();
      }

      return this.contents;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }

   public AuthorType getAuthor() {
      return this.author;
   }

   public void setAuthor(AuthorType value) {
      this.author = value;
   }

   public MomentType getBeginmoment() {
      return this.beginmoment;
   }

   public void setBeginmoment(MomentType value) {
      this.beginmoment = value;
   }

   public MomentType getEndmoment() {
      return this.endmoment;
   }

   public void setEndmoment(MomentType value) {
      this.endmoment = value;
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

   public LifecycleType getLifecycle() {
      return this.lifecycle;
   }

   public void setLifecycle(LifecycleType value) {
      this.lifecycle = value;
   }

   public Boolean isIsrelevant() {
      return this.isrelevant;
   }

   public void setIsrelevant(Boolean value) {
      this.isrelevant = value;
   }

   public SeverityType getSeverity() {
      return this.severity;
   }

   public void setSeverity(SeverityType value) {
      this.severity = value;
   }

   public CertaintyType getCertainty() {
      return this.certainty;
   }

   public void setCertainty(CertaintyType value) {
      this.certainty = value;
   }

   public TemporalityType getTemporality() {
      return this.temporality;
   }

   public void setTemporality(TemporalityType value) {
      this.temporality = value;
   }

   public UrgencyType getUrgency() {
      return this.urgency;
   }

   public void setUrgency(UrgencyType value) {
      this.urgency = value;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }

   public StrengthType getStrength() {
      return this.strength;
   }

   public void setStrength(StrengthType value) {
      this.strength = value;
   }

   public FrequencyType getFrequency() {
      return this.frequency;
   }

   public void setFrequency(FrequencyType value) {
      this.frequency = value;
   }

   public List<SiteType> getSites() {
      if (this.sites == null) {
         this.sites = new ArrayList();
      }

      return this.sites;
   }

   public CostType getCost() {
      return this.cost;
   }

   public void setCost(CostType value) {
      this.cost = value;
   }

   public List<DayperiodType> getDayperiods() {
      if (this.dayperiods == null) {
         this.dayperiods = new ArrayList();
      }

      return this.dayperiods;
   }

   public DurationType getDuration() {
      return this.duration;
   }

   public void setDuration(DurationType value) {
      this.duration = value;
   }

   public Posology getPosology() {
      return this.posology;
   }

   public void setPosology(Posology value) {
      this.posology = value;
   }

   public Regimen getRegimen() {
      return this.regimen;
   }

   public void setRegimen(Regimen value) {
      this.regimen = value;
   }

   public DateTime getDeliverydate() {
      return this.deliverydate;
   }

   public void setDeliverydate(DateTime value) {
      this.deliverydate = value;
   }

   public RenewalType getRenewal() {
      return this.renewal;
   }

   public void setRenewal(RenewalType value) {
      this.renewal = value;
   }

   public RouteType getRoute() {
      return this.route;
   }

   public void setRoute(RouteType value) {
      this.route = value;
   }

   public String getBatch() {
      return this.batch;
   }

   public void setBatch(String value) {
      this.batch = value;
   }

   public TextType getInstructionforoverdosing() {
      return this.instructionforoverdosing;
   }

   public void setInstructionforoverdosing(TextType value) {
      this.instructionforoverdosing = value;
   }

   public TextType getInstructionforpatient() {
      return this.instructionforpatient;
   }

   public void setInstructionforpatient(TextType value) {
      this.instructionforpatient = value;
   }

   public TextType getInstructionforreimbursement() {
      return this.instructionforreimbursement;
   }

   public void setInstructionforreimbursement(TextType value) {
      this.instructionforreimbursement = value;
   }

   public Boolean isIssubstitutionallowed() {
      return this.issubstitutionallowed;
   }

   public void setIssubstitutionallowed(Boolean value) {
      this.issubstitutionallowed = value;
   }

   public Feedback getFeedback() {
      return this.feedback;
   }

   public void setFeedback(Feedback value) {
      this.feedback = value;
   }

   public List<LocalitemattributeType> getLocals() {
      if (this.locals == null) {
         this.locals = new ArrayList();
      }

      return this.locals;
   }

   public DateTime getRecorddatetime() {
      return this.recorddatetime;
   }

   public void setRecorddatetime(DateTime value) {
      this.recorddatetime = value;
   }

   public List<LnkType> getLnks() {
      if (this.lnks == null) {
         this.lnks = new ArrayList();
      }

      return this.lnks;
   }
}
