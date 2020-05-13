package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.QuantityType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MagistralPreparationType",
   propOrder = {"compound", "formularyReference", "magistralText", "galenicform", "quantity"}
)
public class MagistralPreparationType {
   protected List<CompoundType> compound;
   protected FormularyReferenceType formularyReference;
   protected String magistralText;
   protected GalenicformType galenicform;
   protected QuantityType quantity;

   public List<CompoundType> getCompound() {
      if (this.compound == null) {
         this.compound = new ArrayList();
      }

      return this.compound;
   }

   public FormularyReferenceType getFormularyReference() {
      return this.formularyReference;
   }

   public void setFormularyReference(FormularyReferenceType value) {
      this.formularyReference = value;
   }

   public String getMagistralText() {
      return this.magistralText;
   }

   public void setMagistralText(String value) {
      this.magistralText = value;
   }

   public GalenicformType getGalenicform() {
      return this.galenicform;
   }

   public void setGalenicform(GalenicformType value) {
      this.galenicform = value;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }
}
