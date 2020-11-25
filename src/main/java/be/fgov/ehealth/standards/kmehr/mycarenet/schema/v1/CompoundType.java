package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "compoundType",
   propOrder = {"ids", "substance", "medicinalproduct", "quantityprefix", "quantity"}
)
public class CompoundType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   protected SubstanceType substance;
   protected MedicinalProductType medicinalproduct;
   protected Quantityprefix quantityprefix;
   protected QuantityType quantity;

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public SubstanceType getSubstance() {
      return this.substance;
   }

   public void setSubstance(SubstanceType value) {
      this.substance = value;
   }

   public MedicinalProductType getMedicinalproduct() {
      return this.medicinalproduct;
   }

   public void setMedicinalproduct(MedicinalProductType value) {
      this.medicinalproduct = value;
   }

   public Quantityprefix getQuantityprefix() {
      return this.quantityprefix;
   }

   public void setQuantityprefix(Quantityprefix value) {
      this.quantityprefix = value;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }
}
