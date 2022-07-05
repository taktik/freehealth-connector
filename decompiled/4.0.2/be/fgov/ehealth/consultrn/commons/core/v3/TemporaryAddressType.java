package be.fgov.ehealth.consultrn.commons.core.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TemporaryAddressType",
   propOrder = {"countryCode", "countryNames", "unformattedAddress", "startDate"}
)
public class TemporaryAddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   protected String countryCode;
   @XmlElement(
      name = "CountryName"
   )
   protected List<NameType> countryNames;
   @XmlElement(
      name = "UnformattedAddress"
   )
   protected String unformattedAddress;
   @XmlElement(
      name = "StartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;

   public TemporaryAddressType() {
   }

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(String value) {
      this.countryCode = value;
   }

   public List<NameType> getCountryNames() {
      if (this.countryNames == null) {
         this.countryNames = new ArrayList();
      }

      return this.countryNames;
   }

   public String getUnformattedAddress() {
      return this.unformattedAddress;
   }

   public void setUnformattedAddress(String value) {
      this.unformattedAddress = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }
}
