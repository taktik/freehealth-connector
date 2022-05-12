package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.GenderType;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticCriteriaType",
   propOrder = {"lastName", "firstName", "middleName", "birthDate", "gender", "tolerance", "maximum"}
)
public class PhoneticCriteriaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LastName",
      required = true
   )
   protected String lastName;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "MiddleName"
   )
   protected String middleName;
   @XmlElement(
      name = "BirthDate",
      required = true
   )
   protected String birthDate;
   @XmlElement(
      name = "Gender"
   )
   protected GenderType gender;
   @XmlElement(
      name = "Tolerance"
   )
   protected BigInteger tolerance;
   @XmlElement(
      name = "Maximum"
   )
   protected BigInteger maximum;

   public PhoneticCriteriaType() {
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getMiddleName() {
      return this.middleName;
   }

   public void setMiddleName(String value) {
      this.middleName = value;
   }

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public GenderType getGender() {
      return this.gender;
   }

   public void setGender(GenderType value) {
      this.gender = value;
   }

   public BigInteger getTolerance() {
      return this.tolerance;
   }

   public void setTolerance(BigInteger value) {
      this.tolerance = value;
   }

   public BigInteger getMaximum() {
      return this.maximum;
   }

   public void setMaximum(BigInteger value) {
      this.maximum = value;
   }
}
