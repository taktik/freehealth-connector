package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCommentedClassificationRequestType",
   propOrder = {"findByProduct", "findByCommentedClassification"}
)
@XmlRootElement(
   name = "FindCommentedClassificationRequest"
)
public class FindCommentedClassificationRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByProduct"
   )
   protected FindByVirtualProductType findByProduct;
   @XmlElement(
      name = "FindByCommentedClassification"
   )
   protected FindByCommentedClassificationType findByCommentedClassification;

   public FindByVirtualProductType getFindByProduct() {
      return this.findByProduct;
   }

   public void setFindByProduct(FindByVirtualProductType value) {
      this.findByProduct = value;
   }

   public FindByCommentedClassificationType getFindByCommentedClassification() {
      return this.findByCommentedClassification;
   }

   public void setFindByCommentedClassification(FindByCommentedClassificationType value) {
      this.findByCommentedClassification = value;
   }
}
