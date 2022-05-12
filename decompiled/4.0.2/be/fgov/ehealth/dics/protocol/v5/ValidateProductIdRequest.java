package be.fgov.ehealth.dics.protocol.v5;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValidateProductIdRequestType",
   propOrder = {"productId"}
)
@XmlRootElement(
   name = "ValidateProductIdRequest"
)
public class ValidateProductIdRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProductId",
      required = true
   )
   protected String productId;

   public ValidateProductIdRequest() {
   }

   public String getProductId() {
      return this.productId;
   }

   public void setProductId(String value) {
      this.productId = value;
   }
}
