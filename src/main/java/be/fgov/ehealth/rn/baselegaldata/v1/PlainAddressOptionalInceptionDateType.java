package be.fgov.ehealth.rn.baselegaldata.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   name = "PlainAddressOptionalInceptionDateType",
   propOrder = {"countryCode", "countryIsoCode", "countryNames", "address", "inceptionDate"}
)
@XmlSeeAlso({PlainAddressType.class})
public class PlainAddressOptionalInceptionDateType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer countryCode;
   @XmlElement(
      name = "CountryIsoCode"
   )
   protected String countryIsoCode;
   @XmlElement(
      name = "CountryName"
   )
   protected List countryNames;
   @XmlElement(
      name = "Address"
   )
   protected String address;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public Integer getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(Integer value) {
      this.countryCode = value;
   }

   public String getCountryIsoCode() {
      return this.countryIsoCode;
   }

   public void setCountryIsoCode(String value) {
      this.countryIsoCode = value;
   }

   public List getCountryNames() {
      if (this.countryNames == null) {
         this.countryNames = new ArrayList();
      }

      return this.countryNames;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String value) {
      this.address = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
