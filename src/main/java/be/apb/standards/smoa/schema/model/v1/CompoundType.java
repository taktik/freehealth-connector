package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.QuantityPrefix;
import be.apb.standards.smoa.schema.v1.QuantityType;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "compoundType",
   propOrder = {"order", "medicinalproduct", "substance", "quantityprefix", "quantity"}
)
public class CompoundType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger order;
   protected Medicinalproduct medicinalproduct;
   protected Substance substance;
   @XmlSchemaType(
      name = "token"
   )
   protected QuantityPrefix quantityprefix;
   protected QuantityType quantity;

   public BigInteger getOrder() {
      return this.order;
   }

   public void setOrder(BigInteger value) {
      this.order = value;
   }

   public Medicinalproduct getMedicinalproduct() {
      return this.medicinalproduct;
   }

   public void setMedicinalproduct(Medicinalproduct value) {
      this.medicinalproduct = value;
   }

   public Substance getSubstance() {
      return this.substance;
   }

   public void setSubstance(Substance value) {
      this.substance = value;
   }

   public QuantityPrefix getQuantityprefix() {
      return this.quantityprefix;
   }

   public void setQuantityprefix(QuantityPrefix value) {
      this.quantityprefix = value;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"substancecode", "substancename"}
   )
   public static class Substance {
      @XmlElement(
         required = true
      )
      protected String substancecode;
      @XmlElement(
         required = true
      )
      protected String substancename;

      public String getSubstancecode() {
         return this.substancecode;
      }

      public void setSubstancecode(String value) {
         this.substancecode = value;
      }

      public String getSubstancename() {
         return this.substancename;
      }

      public void setSubstancename(String value) {
         this.substancename = value;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"intendedProduct", "deliveredProduct", "intendedname", "deliveredname"}
   )
   public static class Medicinalproduct {
      @XmlElement(
         required = true
      )
      protected String intendedProduct;
      protected String deliveredProduct;
      @XmlElement(
         required = true
      )
      protected String intendedname;
      protected String deliveredname;

      public String getIntendedProduct() {
         return this.intendedProduct;
      }

      public void setIntendedProduct(String value) {
         this.intendedProduct = value;
      }

      public String getDeliveredProduct() {
         return this.deliveredProduct;
      }

      public void setDeliveredProduct(String value) {
         this.deliveredProduct = value;
      }

      public String getIntendedname() {
         return this.intendedname;
      }

      public void setIntendedname(String value) {
         this.intendedname = value;
      }

      public String getDeliveredname() {
         return this.deliveredname;
      }

      public void setDeliveredname(String value) {
         this.deliveredname = value;
      }
   }
}
