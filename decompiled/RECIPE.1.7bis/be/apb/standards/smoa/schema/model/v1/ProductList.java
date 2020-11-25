package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProductList",
   propOrder = {"product"}
)
public class ProductList {
   @XmlElement(
      required = true
   )
   protected List<ProductBvac> product;

   public List<ProductBvac> getProduct() {
      if (this.product == null) {
         this.product = new ArrayList();
      }

      return this.product;
   }
}
