package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractCountryCodeType;
import be.apb.standards.smoa.schema.code.v1.AbstractProfessionCodeType;
import be.apb.standards.smoa.schema.code.v1.AbstractSexCodeType;
import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonType",
   propOrder = {"id", "firstNames", "familyName", "birthDate", "deathdate", "sex", "nationality", "address", "telecom", "usuallanguage", "profession"}
)
public class PersonType extends AbstractPersonType {
   @XmlElement(
      required = true
   )
   protected AbstractPersonIdType id;
   @XmlElement(
      required = true
   )
   protected PersonType.FirstNames firstNames;
   @XmlElement(
      required = true
   )
   protected String familyName;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar birthDate;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar deathdate;
   @XmlElement(
      required = true
   )
   protected AbstractSexCodeType sex;
   protected AbstractCountryCodeType nationality;
   protected List<AbstractAddressType> address;
   protected List<AbstractTelecomType> telecom;
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String usuallanguage;
   protected AbstractProfessionCodeType profession;

   public AbstractPersonIdType getId() {
      return this.id;
   }

   public void setId(AbstractPersonIdType value) {
      this.id = value;
   }

   public PersonType.FirstNames getFirstNames() {
      return this.firstNames;
   }

   public void setFirstNames(PersonType.FirstNames value) {
      this.firstNames = value;
   }

   public String getFamilyName() {
      return this.familyName;
   }

   public void setFamilyName(String value) {
      this.familyName = value;
   }

   public XMLGregorianCalendar getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(XMLGregorianCalendar value) {
      this.birthDate = value;
   }

   public XMLGregorianCalendar getDeathdate() {
      return this.deathdate;
   }

   public void setDeathdate(XMLGregorianCalendar value) {
      this.deathdate = value;
   }

   public AbstractSexCodeType getSex() {
      return this.sex;
   }

   public void setSex(AbstractSexCodeType value) {
      this.sex = value;
   }

   public AbstractCountryCodeType getNationality() {
      return this.nationality;
   }

   public void setNationality(AbstractCountryCodeType value) {
      this.nationality = value;
   }

   public List<AbstractAddressType> getAddress() {
      if (this.address == null) {
         this.address = new ArrayList();
      }

      return this.address;
   }

   public List<AbstractTelecomType> getTelecom() {
      if (this.telecom == null) {
         this.telecom = new ArrayList();
      }

      return this.telecom;
   }

   public String getUsuallanguage() {
      return this.usuallanguage;
   }

   public void setUsuallanguage(String value) {
      this.usuallanguage = value;
   }

   public AbstractProfessionCodeType getProfession() {
      return this.profession;
   }

   public void setProfession(AbstractProfessionCodeType value) {
      this.profession = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"firstName"}
   )
   public static class FirstNames {
      @XmlElement(
         required = true
      )
      protected List<String> firstName;

      public List<String> getFirstName() {
         if (this.firstName == null) {
            this.firstName = new ArrayList();
         }

         return this.firstName;
      }
   }
}
