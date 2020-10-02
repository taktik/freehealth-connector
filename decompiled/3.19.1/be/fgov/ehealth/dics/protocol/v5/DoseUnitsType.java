package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DoseUnitsType",
   propOrder = {"substanceCode", "strength"}
)
public class DoseUnitsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubstanceCode",
      required = true
   )
   protected String substanceCode;
   @XmlElement(
      name = "Strength",
      required = true
   )
   protected StrengthRangeType strength;
   @XmlAttribute(
      name = "Rank"
   )
   protected Integer rank;

   public String getSubstanceCode() {
      return this.substanceCode;
   }

   public void setSubstanceCode(String value) {
      this.substanceCode = value;
   }

   public StrengthRangeType getStrength() {
      return this.strength;
   }

   public void setStrength(StrengthRangeType value) {
      this.strength = value;
   }

   public Integer getRank() {
      return this.rank;
   }

   public void setRank(Integer value) {
      this.rank = value;
   }
}
