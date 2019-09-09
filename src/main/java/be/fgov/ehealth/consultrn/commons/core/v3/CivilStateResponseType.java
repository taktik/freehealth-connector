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
   name = "CivilStateResponseType",
   propOrder = {"civilStateCode", "civilStateDescriptions", "partner", "location", "startDate", "endDate"}
)
public class CivilStateResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilStateCode"
   )
   protected String civilStateCode;
   @XmlElement(
      name = "CivilStateDescription"
   )
   protected List<NameType> civilStateDescriptions;
   @XmlElement(
      name = "Partner"
   )
   protected PartnerType partner;
   @XmlElement(
      name = "Location"
   )
   protected WhereResponseType location;
   @XmlElement(
      name = "StartDate",
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

   public String getCivilStateCode() {
      return this.civilStateCode;
   }

   public void setCivilStateCode(String value) {
      this.civilStateCode = value;
   }

   public List<NameType> getCivilStateDescriptions() {
      if (this.civilStateDescriptions == null) {
         this.civilStateDescriptions = new ArrayList();
      }

      return this.civilStateDescriptions;
   }

   public PartnerType getPartner() {
      return this.partner;
   }

   public void setPartner(PartnerType value) {
      this.partner = value;
   }

   public WhereResponseType getLocation() {
      return this.location;
   }

   public void setLocation(WhereResponseType value) {
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
