package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractPharmFormCodeType;
import be.apb.standards.smoa.schema.v1.QuantityType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PreparationType",
   propOrder = {"substance", "quantity", "pharmForm"}
)
public class PreparationType extends AbstractPreparationType {
   @XmlElement(
      required = true
   )
   protected List<PreparationSubstanceType> substance;
   @XmlElement(
      required = true
   )
   protected QuantityType quantity;
   protected AbstractPharmFormCodeType pharmForm;

   public List<PreparationSubstanceType> getSubstance() {
      if (this.substance == null) {
         this.substance = new ArrayList();
      }

      return this.substance;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }

   public AbstractPharmFormCodeType getPharmForm() {
      return this.pharmForm;
   }

   public void setPharmForm(AbstractPharmFormCodeType value) {
      this.pharmForm = value;
   }
}
