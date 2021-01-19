package be.cin.mycarenet._1_0.carenet.types;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CareReceiverDetailType",
   propOrder = {"inss", "firstName", "lastName", "birthday", "deceased", "sex"}
)
@XmlSeeAlso({ExtCareReceiverDetailType.class})
public class CareReceiverDetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Inss"
   )
   protected String inss;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "LastName"
   )
   protected String lastName;
   @XmlElement(
      name = "Birthday",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime birthday;
   @XmlElement(
      name = "Deceased",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime deceased;
   @XmlElement(
      name = "Sex"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected SexType sex;

   public String getInss() {
      return this.inss;
   }

   public void setInss(String value) {
      this.inss = value;
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

   public DateTime getBirthday() {
      return this.birthday;
   }

   public void setBirthday(DateTime value) {
      this.birthday = value;
   }

   public DateTime getDeceased() {
      return this.deceased;
   }

   public void setDeceased(DateTime value) {
      this.deceased = value;
   }

   public SexType getSex() {
      return this.sex;
   }

   public void setSex(SexType value) {
      this.sex = value;
   }
}
