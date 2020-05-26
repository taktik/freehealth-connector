package be.fgov.ehealth.insurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CareReceiverType",
   propOrder = {"ssin", "regNrWithMut", "mutuality", "firstName", "lastName", "birthday", "deceased", "sex"}
)
public class CareReceiverType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin"
   )
   protected String ssin;
   @XmlElement(
      name = "RegNrWithMut"
   )
   protected String regNrWithMut;
   @XmlElement(
      name = "Mutuality"
   )
   protected String mutuality;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "LastName"
   )
   protected String lastName;
   @XmlElement(
      name = "Birthday"
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar birthday;
   @XmlElement(
      name = "Deceased"
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar deceased;
   @XmlElement(
      name = "Sex"
   )
   protected SexType sex;

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String value) {
      this.ssin = value;
   }

   public String getRegNrWithMut() {
      return this.regNrWithMut;
   }

   public void setRegNrWithMut(String value) {
      this.regNrWithMut = value;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String value) {
      this.mutuality = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public XMLGregorianCalendar getBirthday() {
      return this.birthday;
   }

   public void setBirthday(XMLGregorianCalendar value) {
      this.birthday = value;
   }

   public XMLGregorianCalendar getDeceased() {
      return this.deceased;
   }

   public void setDeceased(XMLGregorianCalendar value) {
      this.deceased = value;
   }

   public SexType getSex() {
      return this.sex;
   }

   public void setSex(SexType value) {
      this.sex = value;
   }
}
