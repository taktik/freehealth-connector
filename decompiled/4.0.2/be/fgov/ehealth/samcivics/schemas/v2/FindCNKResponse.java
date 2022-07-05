package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.ProductType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCNKResponseType",
   propOrder = {"products", "versionNum"}
)
@XmlRootElement(
   name = "FindCNKResponse"
)
public class FindCNKResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Product",
      required = true
   )
   protected List<ProductType> products;
   @XmlElement(
      name = "VersionNum",
      required = true
   )
   protected BigInteger versionNum;

   public FindCNKResponse() {
   }

   public List<ProductType> getProducts() {
      if (this.products == null) {
         this.products = new ArrayList();
      }

      return this.products;
   }

   public BigInteger getVersionNum() {
      return this.versionNum;
   }

   public void setVersionNum(BigInteger value) {
      this.versionNum = value;
   }
}
