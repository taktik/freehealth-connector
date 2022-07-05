package be.fgov.ehealth.consultrn.commons.core.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStateRequestType",
   propOrder = {"civilStateCode", "partnerSsin", "location", "startDate", "endDate"}
)
public class CivilStateRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilStateCode",
      required = true
   )
   protected String civilStateCode;
   @XmlElement(
      name = "PartnerSsin"
   )
   protected String partnerSsin;
   @XmlElement(
      name = "Location"
   )
   protected WhereRequestType location;
   @XmlElement(
      name = "StartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public CivilStateRequestType() {
   }

   public String getCivilStateCode() {
      return this.civilStateCode;
   }

   public void setCivilStateCode(String value) {
      this.civilStateCode = value;
   }

   public String getPartnerSsin() {
      return this.partnerSsin;
   }

   public void setPartnerSsin(String value) {
      this.partnerSsin = value;
   }

   public WhereRequestType getLocation() {
      return this.location;
   }

   public void setLocation(WhereRequestType value) {
      this.location = value;
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
