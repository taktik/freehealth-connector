package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "compoundprescriptionType",
   propOrder = {"formularyreference", "galenicform", "compound", "quantity", "magistraltext", "mixedContent"}
)
public class CompoundprescriptionType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected List<FormularyreferenceType> formularyreference;
   protected List<GalenicformType> galenicform;
   protected List<CompoundType> compound;
   protected List<QuantityType> quantity;
   protected List<TextType> magistraltext;
   @XmlMixed
   protected List<String> mixedContent;
   @XmlAttribute(
      name = "L"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String l;

   public List<FormularyreferenceType> getFormularyreference() {
      if (this.formularyreference == null) {
         this.formularyreference = new ArrayList();
      }

      return this.formularyreference;
   }

   public List<GalenicformType> getGalenicform() {
      if (this.galenicform == null) {
         this.galenicform = new ArrayList();
      }

      return this.galenicform;
   }

   public List<CompoundType> getCompound() {
      if (this.compound == null) {
         this.compound = new ArrayList();
      }

      return this.compound;
   }

   public List<QuantityType> getQuantity() {
      if (this.quantity == null) {
         this.quantity = new ArrayList();
      }

      return this.quantity;
   }

   public List<TextType> getMagistraltext() {
      if (this.magistraltext == null) {
         this.magistraltext = new ArrayList();
      }

      return this.magistraltext;
   }

   public List<String> getMixedContent() {
      if (this.mixedContent == null) {
         this.mixedContent = new ArrayList();
      }

      return this.mixedContent;
   }

   public String getL() {
      return this.l;
   }

   public void setL(String value) {
      this.l = value;
   }
}
