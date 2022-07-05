package be.fgov.ehealth.genericinsurability.core.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
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
   name = "MedicalHouseType",
   propOrder = {"periodStart", "periodEnd"}
)
public class MedicalHouseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PeriodStart",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodStart;
   @XmlElement(
      name = "PeriodEnd",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodEnd;
   @XmlAttribute(
      name = "Nurse"
   )
   protected Boolean nurse;
   @XmlAttribute(
      name = "Medical"
   )
   protected Boolean medical;
   @XmlAttribute(
      name = "Kine"
   )
   protected Boolean kine;

   public MedicalHouseType() {
   }

   public DateTime getPeriodStart() {
      return this.periodStart;
   }

   public void setPeriodStart(DateTime value) {
      this.periodStart = value;
   }

   public DateTime getPeriodEnd() {
      return this.periodEnd;
   }

   public void setPeriodEnd(DateTime value) {
      this.periodEnd = value;
   }

   public Boolean isNurse() {
      return this.nurse;
   }

   public void setNurse(Boolean value) {
      this.nurse = value;
   }

   public Boolean isMedical() {
      return this.medical;
   }

   public void setMedical(Boolean value) {
      this.medical = value;
   }

   public Boolean isKine() {
      return this.kine;
   }

   public void setKine(Boolean value) {
      this.kine = value;
   }
}
