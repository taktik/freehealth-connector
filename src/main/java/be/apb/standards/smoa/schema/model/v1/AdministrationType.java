package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.CDDAYPERIOD;
import be.apb.standards.smoa.schema.v1.CDSITE;
import be.apb.standards.smoa.schema.v1.CDTEMPORALITY;
import be.apb.standards.smoa.schema.v1.DurationType;
import be.apb.standards.smoa.schema.v1.MomentType;
import be.apb.standards.smoa.schema.v1.QuantityType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdministrationType",
   propOrder = {"beginmoment", "endmoment", "temporality", "quantity", "frequency", "site", "dayperiod", "duration", "posology", "regimen", "renewal", "route", "instructionforoverdosing", "instructionforpatient", "instructionforreimbursement", "issubstitutionallowed"}
)
public class AdministrationType {
   protected MomentType beginmoment;
   protected MomentType endmoment;
   protected Temporality temporality;
   protected QuantityType quantity;
   protected AdministrationInstructionsFrequencyType frequency;
   protected List<Site> site;
   @XmlSchemaType(
      name = "token"
   )
   protected List<CDDAYPERIOD> dayperiod;
   protected DurationType duration;
   protected PosologyType posology;
   protected RegimenType regimen;
   protected DurationType renewal;
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String route;
   protected String instructionforoverdosing;
   protected String instructionforpatient;
   protected String instructionforreimbursement;
   protected Boolean issubstitutionallowed;

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

   public Temporality getTemporality() {
      return this.temporality;
   }

   public void setTemporality(Temporality value) {
      this.temporality = value;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }

   public AdministrationInstructionsFrequencyType getFrequency() {
      return this.frequency;
   }

   public void setFrequency(AdministrationInstructionsFrequencyType value) {
      this.frequency = value;
   }

   public List<Site> getSite() {
      if (this.site == null) {
         this.site = new ArrayList();
      }

      return this.site;
   }

   public List<CDDAYPERIOD> getDayperiod() {
      if (this.dayperiod == null) {
         this.dayperiod = new ArrayList();
      }

      return this.dayperiod;
   }

   public DurationType getDuration() {
      return this.duration;
   }

   public void setDuration(DurationType value) {
      this.duration = value;
   }

   public PosologyType getPosology() {
      return this.posology;
   }

   public void setPosology(PosologyType value) {
      this.posology = value;
   }

   public RegimenType getRegimen() {
      return this.regimen;
   }

   public void setRegimen(RegimenType value) {
      this.regimen = value;
   }

   public DurationType getRenewal() {
      return this.renewal;
   }

   public void setRenewal(DurationType value) {
      this.renewal = value;
   }

   public String getRoute() {
      return this.route;
   }

   public void setRoute(String value) {
      this.route = value;
   }

   public String getInstructionforoverdosing() {
      return this.instructionforoverdosing;
   }

   public void setInstructionforoverdosing(String value) {
      this.instructionforoverdosing = value;
   }

   public String getInstructionforpatient() {
      return this.instructionforpatient;
   }

   public void setInstructionforpatient(String value) {
      this.instructionforpatient = value;
   }

   public String getInstructionforreimbursement() {
      return this.instructionforreimbursement;
   }

   public void setInstructionforreimbursement(String value) {
      this.instructionforreimbursement = value;
   }

   public Boolean isIssubstitutionallowed() {
      return this.issubstitutionallowed;
   }

   public void setIssubstitutionallowed(Boolean value) {
      this.issubstitutionallowed = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"cd"}
   )
   public static class Temporality {
      @XmlElement(
         required = true
      )
      @XmlSchemaType(
         name = "token"
      )
      protected CDTEMPORALITY cd;

      public CDTEMPORALITY getCd() {
         return this.cd;
      }

      public void setCd(CDTEMPORALITY value) {
         this.cd = value;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"cd", "text"}
   )
   public static class Site {
      @XmlSchemaType(
         name = "token"
      )
      protected CDSITE cd;
      protected String text;

      public CDSITE getCd() {
         return this.cd;
      }

      public void setCd(CDSITE value) {
         this.cd = value;
      }

      public String getText() {
         return this.text;
      }

      public void setText(String value) {
         this.text = value;
      }
   }
}
