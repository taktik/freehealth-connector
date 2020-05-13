package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InscriptionType",
   propOrder = {"ssin", "name", "qualityCode", "period"}
)
public class InscriptionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected EncodedSSINType ssin;
   @XmlElement(
      name = "Name"
   )
   protected NameType name;
   @XmlElement(
      name = "QualityCode"
   )
   protected String qualityCode;
   @XmlElement(
      name = "Period",
      required = true
   )
   protected PeriodType period;

   public EncodedSSINType getSSIN() {
      return this.ssin;
   }

   public void setSSIN(EncodedSSINType value) {
      this.ssin = value;
   }

   public NameType getName() {
      return this.name;
   }

   public void setName(NameType value) {
      this.name = value;
   }

   public String getQualityCode() {
      return this.qualityCode;
   }

   public void setQualityCode(String value) {
      this.qualityCode = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }
}
