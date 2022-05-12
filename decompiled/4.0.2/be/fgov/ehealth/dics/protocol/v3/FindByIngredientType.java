package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.core.QuantityType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByIngredientType",
   propOrder = {"substanceName", "substanceCode", "strength"}
)
public class FindByIngredientType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubstanceName"
   )
   protected StandardSubstanceNameCriterionType substanceName;
   @XmlElement(
      name = "SubstanceCode"
   )
   protected StandardSubstanceCodeCriterionType substanceCode;
   @XmlElement(
      name = "Strength"
   )
   protected QuantityType strength;

   public FindByIngredientType() {
   }

   public StandardSubstanceNameCriterionType getSubstanceName() {
      return this.substanceName;
   }

   public void setSubstanceName(StandardSubstanceNameCriterionType value) {
      this.substanceName = value;
   }

   public StandardSubstanceCodeCriterionType getSubstanceCode() {
      return this.substanceCode;
   }

   public void setSubstanceCode(StandardSubstanceCodeCriterionType value) {
      this.substanceCode = value;
   }

   public QuantityType getStrength() {
      return this.strength;
   }

   public void setStrength(QuantityType value) {
      this.strength = value;
   }
}
