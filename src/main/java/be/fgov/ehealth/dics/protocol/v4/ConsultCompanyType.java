package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.company.submit.CompanyKeyType;
import be.fgov.ehealth.dics.core.v4.company.submit.VatNrPerCountryType;
import java.io.Serializable;
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
   name = "ConsultCompanyType",
   propOrder = {"authorisationNr", "vatNr", "europeanNr", "denomination", "legalForm", "building", "streetName", "streetNum", "postbox", "postcode", "city", "countryCode", "phone", "language", "website"}
)
public class ConsultCompanyType extends CompanyKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AuthorisationNr"
   )
   protected String authorisationNr;
   @XmlElement(
      name = "VatNr"
   )
   protected VatNrPerCountryType vatNr;
   @XmlElement(
      name = "EuropeanNr"
   )
   protected String europeanNr;
   @XmlElement(
      name = "Denomination",
      required = true
   )
   protected String denomination;
   @XmlElement(
      name = "LegalForm"
   )
   protected String legalForm;
   @XmlElement(
      name = "Building"
   )
   protected String building;
   @XmlElement(
      name = "StreetName"
   )
   protected String streetName;
   @XmlElement(
      name = "StreetNum"
   )
   protected String streetNum;
   @XmlElement(
      name = "Postbox"
   )
   protected String postbox;
   @XmlElement(
      name = "Postcode"
   )
   protected String postcode;
   @XmlElement(
      name = "City"
   )
   protected String city;
   @XmlElement(
      name = "CountryCode"
   )
   protected String countryCode;
   @XmlElement(
      name = "Phone"
   )
   protected String phone;
   @XmlElement(
      name = "Language",
      required = true
   )
   protected String language;
   @XmlElement(
      name = "Website"
   )
   protected String website;
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

   public String getAuthorisationNr() {
      return this.authorisationNr;
   }

   public void setAuthorisationNr(String value) {
      this.authorisationNr = value;
   }

   public VatNrPerCountryType getVatNr() {
      return this.vatNr;
   }

   public void setVatNr(VatNrPerCountryType value) {
      this.vatNr = value;
   }

   public String getEuropeanNr() {
      return this.europeanNr;
   }

   public void setEuropeanNr(String value) {
      this.europeanNr = value;
   }

   public String getDenomination() {
      return this.denomination;
   }

   public void setDenomination(String value) {
      this.denomination = value;
   }

   public String getLegalForm() {
      return this.legalForm;
   }

   public void setLegalForm(String value) {
      this.legalForm = value;
   }

   public String getBuilding() {
      return this.building;
   }

   public void setBuilding(String value) {
      this.building = value;
   }

   public String getStreetName() {
      return this.streetName;
   }

   public void setStreetName(String value) {
      this.streetName = value;
   }

   public String getStreetNum() {
      return this.streetNum;
   }

   public void setStreetNum(String value) {
      this.streetNum = value;
   }

   public String getPostbox() {
      return this.postbox;
   }

   public void setPostbox(String value) {
      this.postbox = value;
   }

   public String getPostcode() {
      return this.postcode;
   }

   public void setPostcode(String value) {
      this.postcode = value;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String value) {
      this.city = value;
   }

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(String value) {
      this.countryCode = value;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String value) {
      this.phone = value;
   }

   public String getLanguage() {
      return this.language;
   }

   public void setLanguage(String value) {
      this.language = value;
   }

   public String getWebsite() {
      return this.website;
   }

   public void setWebsite(String value) {
      this.website = value;
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
