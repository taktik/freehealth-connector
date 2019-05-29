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
   name = "PartnerType",
   propOrder = {"partnerReference", "partnerSsin", "partnerBirthDate", "partnerName"}
)
public class PartnerType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PartnerReference"
   )
   protected String partnerReference;
   @XmlElement(
      name = "PartnerSsin"
   )
   protected String partnerSsin;
   @XmlElement(
      name = "PartnerBirthDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime partnerBirthDate;
   @XmlElement(
      name = "PartnerName"
   )
   protected PersonNameResponseType partnerName;

   public String getPartnerReference() {
      return this.partnerReference;
   }

   public void setPartnerReference(String value) {
      this.partnerReference = value;
   }

   public String getPartnerSsin() {
      return this.partnerSsin;
   }

   public void setPartnerSsin(String value) {
      this.partnerSsin = value;
   }

   public DateTime getPartnerBirthDate() {
      return this.partnerBirthDate;
   }

   public void setPartnerBirthDate(DateTime value) {
      this.partnerBirthDate = value;
   }

   public PersonNameResponseType getPartnerName() {
      return this.partnerName;
   }

   public void setPartnerName(PersonNameResponseType value) {
      this.partnerName = value;
   }
}
