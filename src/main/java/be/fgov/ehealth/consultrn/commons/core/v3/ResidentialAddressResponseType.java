package be.fgov.ehealth.consultrn.commons.core.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
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
   name = "ResidentialAddressResponseType",
   propOrder = {"postalCode", "streetCode", "streetNames", "houseNumber", "boxNumber", "startDate"}
)
public class ResidentialAddressResponseType extends WhereResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PostalCode"
   )
   protected String postalCode;
   @XmlElement(
      name = "StreetCode"
   )
   protected String streetCode;
   @XmlElement(
      name = "StreetName"
   )
   protected List<NameType> streetNames;
   @XmlElement(
      name = "HouseNumber"
   )
   protected String houseNumber;
   @XmlElement(
      name = "BoxNumber"
   )
   protected String boxNumber;
   @XmlElement(
      name = "StartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String value) {
      this.postalCode = value;
   }

   public String getStreetCode() {
      return this.streetCode;
   }

   public void setStreetCode(String value) {
      this.streetCode = value;
   }

   public List<NameType> getStreetNames() {
      if (this.streetNames == null) {
         this.streetNames = new ArrayList();
      }

      return this.streetNames;
   }

   public String getHouseNumber() {
      return this.houseNumber;
   }

   public void setHouseNumber(String value) {
      this.houseNumber = value;
   }

   public String getBoxNumber() {
      return this.boxNumber;
   }

   public void setBoxNumber(String value) {
      this.boxNumber = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }
}
