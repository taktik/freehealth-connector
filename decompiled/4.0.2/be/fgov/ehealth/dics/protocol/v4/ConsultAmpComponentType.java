package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigInteger;
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
   name = "ConsultAmpComponentType",
   propOrder = {"dividable", "scored", "crushable", "containsAlcohol", "sugarFree", "modifiedReleaseType", "specificDrugDevice", "dimensions", "name", "note", "pharmaceuticalForms", "routeOfAdministrations", "realActualIngredients"}
)
public class ConsultAmpComponentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Dividable"
   )
   protected String dividable;
   @XmlElement(
      name = "Scored"
   )
   protected String scored;
   @XmlElement(
      name = "Crushable"
   )
   protected String crushable;
   @XmlElement(
      name = "ContainsAlcohol"
   )
   protected String containsAlcohol;
   @XmlElement(
      name = "SugarFree"
   )
   protected Boolean sugarFree;
   @XmlElement(
      name = "ModifiedReleaseType"
   )
   protected BigInteger modifiedReleaseType;
   @XmlElement(
      name = "SpecificDrugDevice"
   )
   protected BigInteger specificDrugDevice;
   @XmlElement(
      name = "Dimensions"
   )
   protected String dimensions;
   @XmlElement(
      name = "Name"
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "Note"
   )
   protected ConsultTextType note;
   @XmlElement(
      name = "PharmaceuticalForm",
      required = true
   )
   protected List<PharmaceuticalFormWithStandardsType> pharmaceuticalForms;
   @XmlElement(
      name = "RouteOfAdministration",
      required = true
   )
   protected List<RouteOfAdministrationWithStandardsType> routeOfAdministrations;
   @XmlElement(
      name = "RealActualIngredient",
      required = true
   )
   protected List<ConsultRealActualIngredientType> realActualIngredients;
   @XmlAttribute(
      name = "SequenceNr",
      required = true
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger sequenceNr;
   @XmlAttribute(
      name = "VmpComponentCode"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger vmpComponentCode;
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

   public ConsultAmpComponentType() {
   }

   public String getDividable() {
      return this.dividable;
   }

   public void setDividable(String value) {
      this.dividable = value;
   }

   public String getScored() {
      return this.scored;
   }

   public void setScored(String value) {
      this.scored = value;
   }

   public String getCrushable() {
      return this.crushable;
   }

   public void setCrushable(String value) {
      this.crushable = value;
   }

   public String getContainsAlcohol() {
      return this.containsAlcohol;
   }

   public void setContainsAlcohol(String value) {
      this.containsAlcohol = value;
   }

   public Boolean isSugarFree() {
      return this.sugarFree;
   }

   public void setSugarFree(Boolean value) {
      this.sugarFree = value;
   }

   public BigInteger getModifiedReleaseType() {
      return this.modifiedReleaseType;
   }

   public void setModifiedReleaseType(BigInteger value) {
      this.modifiedReleaseType = value;
   }

   public BigInteger getSpecificDrugDevice() {
      return this.specificDrugDevice;
   }

   public void setSpecificDrugDevice(BigInteger value) {
      this.specificDrugDevice = value;
   }

   public String getDimensions() {
      return this.dimensions;
   }

   public void setDimensions(String value) {
      this.dimensions = value;
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public ConsultTextType getNote() {
      return this.note;
   }

   public void setNote(ConsultTextType value) {
      this.note = value;
   }

   public List<PharmaceuticalFormWithStandardsType> getPharmaceuticalForms() {
      if (this.pharmaceuticalForms == null) {
         this.pharmaceuticalForms = new ArrayList();
      }

      return this.pharmaceuticalForms;
   }

   public List<RouteOfAdministrationWithStandardsType> getRouteOfAdministrations() {
      if (this.routeOfAdministrations == null) {
         this.routeOfAdministrations = new ArrayList();
      }

      return this.routeOfAdministrations;
   }

   public List<ConsultRealActualIngredientType> getRealActualIngredients() {
      if (this.realActualIngredients == null) {
         this.realActualIngredients = new ArrayList();
      }

      return this.realActualIngredients;
   }

   public BigInteger getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(BigInteger value) {
      this.sequenceNr = value;
   }

   public BigInteger getVmpComponentCode() {
      return this.vmpComponentCode;
   }

   public void setVmpComponentCode(BigInteger value) {
      this.vmpComponentCode = value;
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
