package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import be.ehealth.technicalconnector.adapter.XmlTimeNoTzAdapter;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDCONTENT;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.LnkType;
import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHR;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contentType",
   propOrder = {"location", "lnks", "bacteriology", "ecg", "holter", "medication", "compoundprescription", "substanceproduct", "medicinalproduct", "error", "incapacity", "insurance", "person", "hcparty", "date", "time", "yearmonth", "year", "texts", "_boolean", "unsignedInt", "decimal", "cds", "ids", "unit", "minref", "maxref", "refscopes"}
)
public class ContentType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected LocationBirthPlaceType location;
   @XmlElement(
      name = "lnk"
   )
   protected List<LnkType> lnks;
   protected TextType bacteriology;
   protected TextType ecg;
   protected HolterType holter;
   protected MedicationType medication;
   protected CompoundprescriptionType compoundprescription;
   protected Substanceproduct substanceproduct;
   protected MedicinalProductType medicinalproduct;
   protected ErrorType error;
   protected IncapacityType incapacity;
   protected InsuranceType insurance;
   protected PersonType person;
   protected HcpartyType hcparty;
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
   @XmlJavaTypeAdapter(XmlTimeNoTzAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   @XmlSchemaType(
      name = "gYearMonth"
   )
   protected XMLGregorianCalendar yearmonth;
   @XmlSchemaType(
      name = "gYear"
   )
   protected XMLGregorianCalendar year;
   @XmlElement(
      name = "text"
   )
   protected List<TextType> texts;
   @XmlElement(
      name = "boolean"
   )
   protected Boolean _boolean;
   @XmlSchemaType(
      name = "unsignedInt"
   )
   protected Long unsignedInt;
   protected BigDecimal decimal;
   @XmlElement(
      name = "cd"
   )
   protected List<CDCONTENT> cds;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   protected UnitType unit;
   protected MinrefType minref;
   protected MaxrefType maxref;
   @XmlElement(
      name = "refscope"
   )
   protected List<RefscopeType> refscopes;

   public ContentType() {
   }

   public LocationBirthPlaceType getLocation() {
      return this.location;
   }

   public void setLocation(LocationBirthPlaceType value) {
      this.location = value;
   }

   public List<LnkType> getLnks() {
      if (this.lnks == null) {
         this.lnks = new ArrayList();
      }

      return this.lnks;
   }

   public TextType getBacteriology() {
      return this.bacteriology;
   }

   public void setBacteriology(TextType value) {
      this.bacteriology = value;
   }

   public TextType getEcg() {
      return this.ecg;
   }

   public void setEcg(TextType value) {
      this.ecg = value;
   }

   public HolterType getHolter() {
      return this.holter;
   }

   public void setHolter(HolterType value) {
      this.holter = value;
   }

   public MedicationType getMedication() {
      return this.medication;
   }

   public void setMedication(MedicationType value) {
      this.medication = value;
   }

   public CompoundprescriptionType getCompoundprescription() {
      return this.compoundprescription;
   }

   public void setCompoundprescription(CompoundprescriptionType value) {
      this.compoundprescription = value;
   }

   public Substanceproduct getSubstanceproduct() {
      return this.substanceproduct;
   }

   public void setSubstanceproduct(Substanceproduct value) {
      this.substanceproduct = value;
   }

   public MedicinalProductType getMedicinalproduct() {
      return this.medicinalproduct;
   }

   public void setMedicinalproduct(MedicinalProductType value) {
      this.medicinalproduct = value;
   }

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public IncapacityType getIncapacity() {
      return this.incapacity;
   }

   public void setIncapacity(IncapacityType value) {
      this.incapacity = value;
   }

   public InsuranceType getInsurance() {
      return this.insurance;
   }

   public void setInsurance(InsuranceType value) {
      this.insurance = value;
   }

   public PersonType getPerson() {
      return this.person;
   }

   public void setPerson(PersonType value) {
      this.person = value;
   }

   public HcpartyType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HcpartyType value) {
      this.hcparty = value;
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

   public XMLGregorianCalendar getYearmonth() {
      return this.yearmonth;
   }

   public void setYearmonth(XMLGregorianCalendar value) {
      this.yearmonth = value;
   }

   public XMLGregorianCalendar getYear() {
      return this.year;
   }

   public void setYear(XMLGregorianCalendar value) {
      this.year = value;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }

   public Boolean isBoolean() {
      return this._boolean;
   }

   public void setBoolean(Boolean value) {
      this._boolean = value;
   }

   public Long getUnsignedInt() {
      return this.unsignedInt;
   }

   public void setUnsignedInt(Long value) {
      this.unsignedInt = value;
   }

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public List<CDCONTENT> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public UnitType getUnit() {
      return this.unit;
   }

   public void setUnit(UnitType value) {
      this.unit = value;
   }

   public MinrefType getMinref() {
      return this.minref;
   }

   public void setMinref(MinrefType value) {
      this.minref = value;
   }

   public MaxrefType getMaxref() {
      return this.maxref;
   }

   public void setMaxref(MaxrefType value) {
      this.maxref = value;
   }

   public List<RefscopeType> getRefscopes() {
      if (this.refscopes == null) {
         this.refscopes = new ArrayList();
      }

      return this.refscopes;
   }
}
