package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractPeriodicityType;
import be.apb.standards.smoa.schema.v1.DurationType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdministrationInstructionsFrequencyType",
   propOrder = {"nominator", "denominator", "text", "periodicity", "administrationMoment"}
)
public class AdministrationInstructionsFrequencyType {
   protected DurationType nominator;
   protected DurationType denominator;
   protected String text;
   protected AbstractPeriodicityType periodicity;
   protected String administrationMoment;

   public DurationType getNominator() {
      return this.nominator;
   }

   public void setNominator(DurationType value) {
      this.nominator = value;
   }

   public DurationType getDenominator() {
      return this.denominator;
   }

   public void setDenominator(DurationType value) {
      this.denominator = value;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String value) {
      this.text = value;
   }

   public AbstractPeriodicityType getPeriodicity() {
      return this.periodicity;
   }

   public void setPeriodicity(AbstractPeriodicityType value) {
      this.periodicity = value;
   }

   public String getAdministrationMoment() {
      return this.administrationMoment;
   }

   public void setAdministrationMoment(String value) {
      this.administrationMoment = value;
   }
}
