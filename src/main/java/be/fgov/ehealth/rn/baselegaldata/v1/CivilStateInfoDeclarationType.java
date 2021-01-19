package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStateInfoDeclarationType",
   propOrder = {"civilStateCode", "partnerSsin", "location", "inceptionDate", "expiryDate"}
)
public class CivilStateInfoDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilStateCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer civilStateCode;
   @XmlElement(
      name = "PartnerSsin"
   )
   protected String partnerSsin;
   @XmlElement(
      name = "Location"
   )
   protected LocationDeclarationType location;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;
   @XmlElement(
      name = "ExpiryDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expiryDate;

   public Integer getCivilStateCode() {
      return this.civilStateCode;
   }

   public void setCivilStateCode(Integer value) {
      this.civilStateCode = value;
   }

   public String getPartnerSsin() {
      return this.partnerSsin;
   }

   public void setPartnerSsin(String value) {
      this.partnerSsin = value;
   }

   public LocationDeclarationType getLocation() {
      return this.location;
   }

   public void setLocation(LocationDeclarationType value) {
      this.location = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }

   public DateTime getExpiryDate() {
      return this.expiryDate;
   }

   public void setExpiryDate(DateTime value) {
      this.expiryDate = value;
   }
}
