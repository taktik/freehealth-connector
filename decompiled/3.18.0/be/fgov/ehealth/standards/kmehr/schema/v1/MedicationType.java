package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDMEDICATION;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "medicationType",
   propOrder = {"magistral", "inn", "cd", "tradename", "presentation", "strength", "route", "batch", "numberofpackage", "_package", "quantityperpackage", "instructionforoverdosing", "instructionforpatient", "instructionforreimbursement", "issubstitutionallowed"}
)
public class MedicationType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected TextType magistral;
   protected TextType inn;
   protected CDMEDICATION cd;
   protected String tradename;
   protected PresentationType presentation;
   protected StrengthType strength;
   protected RouteType route;
   protected String batch;
   protected BigDecimal numberofpackage;
   @XmlElement(
      name = "package"
   )
   protected PackageType _package;
   protected BigDecimal quantityperpackage;
   protected TextType instructionforoverdosing;
   protected TextType instructionforpatient;
   protected TextType instructionforreimbursement;
   protected Boolean issubstitutionallowed;

   public TextType getMagistral() {
      return this.magistral;
   }

   public void setMagistral(TextType value) {
      this.magistral = value;
   }

   public TextType getInn() {
      return this.inn;
   }

   public void setInn(TextType value) {
      this.inn = value;
   }

   public CDMEDICATION getCd() {
      return this.cd;
   }

   public void setCd(CDMEDICATION value) {
      this.cd = value;
   }

   public String getTradename() {
      return this.tradename;
   }

   public void setTradename(String value) {
      this.tradename = value;
   }

   public PresentationType getPresentation() {
      return this.presentation;
   }

   public void setPresentation(PresentationType value) {
      this.presentation = value;
   }

   public StrengthType getStrength() {
      return this.strength;
   }

   public void setStrength(StrengthType value) {
      this.strength = value;
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

   public BigDecimal getNumberofpackage() {
      return this.numberofpackage;
   }

   public void setNumberofpackage(BigDecimal value) {
      this.numberofpackage = value;
   }

   public PackageType getPackage() {
      return this._package;
   }

   public void setPackage(PackageType value) {
      this._package = value;
   }

   public BigDecimal getQuantityperpackage() {
      return this.quantityperpackage;
   }

   public void setQuantityperpackage(BigDecimal value) {
      this.quantityperpackage = value;
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
}
