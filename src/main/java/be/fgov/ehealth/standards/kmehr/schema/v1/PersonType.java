package be.fgov.ehealth.standards.kmehr.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "personType",
   propOrder = {"ids", "firstnames", "familyname", "birthdate", "birthlocation", "deathdate", "deathlocation", "sex", "nationality", "addresses", "telecoms", "usuallanguage", "profession", "insurancystatus", "insurancymembership", "recorddatetime", "texts", "civilstate"}
)
@XmlRootElement(
   name = "personType"
)
public class PersonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDPATIENT> ids;
   @XmlElement(
      name = "firstname",
      required = true
   )
   protected List<String> firstnames;
   @XmlElement(
      required = true
   )
   protected String familyname;
   protected DateType birthdate;
   protected AddressTypeBase birthlocation;
   protected DateType deathdate;
   protected AddressTypeBase deathlocation;
   @XmlElement(
      required = true
   )
   protected SexType sex;
   protected Nationality nationality;
   @XmlElement(
      name = "address"
   )
   protected List<AddressType> addresses;
   @XmlElement(
      name = "telecom"
   )
   protected List<TelecomType> telecoms;
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String usuallanguage;
   protected ProfessionType profession;
   protected InsuranceType insurancystatus;
   protected MemberinsuranceType insurancymembership;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime recorddatetime;
   @XmlElement(
      name = "text"
   )
   protected List<TextType> texts;
   protected Civilstate civilstate;

   public List<IDPATIENT> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<String> getFirstnames() {
      if (this.firstnames == null) {
         this.firstnames = new ArrayList();
      }

      return this.firstnames;
   }

   public String getFamilyname() {
      return this.familyname;
   }

   public void setFamilyname(String value) {
      this.familyname = value;
   }

   public DateType getBirthdate() {
      return this.birthdate;
   }

   public void setBirthdate(DateType value) {
      this.birthdate = value;
   }

   public AddressTypeBase getBirthlocation() {
      return this.birthlocation;
   }

   public void setBirthlocation(AddressTypeBase value) {
      this.birthlocation = value;
   }

   public DateType getDeathdate() {
      return this.deathdate;
   }

   public void setDeathdate(DateType value) {
      this.deathdate = value;
   }

   public AddressTypeBase getDeathlocation() {
      return this.deathlocation;
   }

   public void setDeathlocation(AddressTypeBase value) {
      this.deathlocation = value;
   }

   public SexType getSex() {
      return this.sex;
   }

   public void setSex(SexType value) {
      this.sex = value;
   }

   public Nationality getNationality() {
      return this.nationality;
   }

   public void setNationality(Nationality value) {
      this.nationality = value;
   }

   public List<AddressType> getAddresses() {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      return this.addresses;
   }

   public List<TelecomType> getTelecoms() {
      if (this.telecoms == null) {
         this.telecoms = new ArrayList();
      }

      return this.telecoms;
   }

   public String getUsuallanguage() {
      return this.usuallanguage;
   }

   public void setUsuallanguage(String value) {
      this.usuallanguage = value;
   }

   public ProfessionType getProfession() {
      return this.profession;
   }

   public void setProfession(ProfessionType value) {
      this.profession = value;
   }

   public InsuranceType getInsurancystatus() {
      return this.insurancystatus;
   }

   public void setInsurancystatus(InsuranceType value) {
      this.insurancystatus = value;
   }

   public MemberinsuranceType getInsurancymembership() {
      return this.insurancymembership;
   }

   public void setInsurancymembership(MemberinsuranceType value) {
      this.insurancymembership = value;
   }

   public DateTime getRecorddatetime() {
      return this.recorddatetime;
   }

   public void setRecorddatetime(DateTime value) {
      this.recorddatetime = value;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }

   public Civilstate getCivilstate() {
      return this.civilstate;
   }

   public void setCivilstate(Civilstate value) {
      this.civilstate = value;
   }
}
