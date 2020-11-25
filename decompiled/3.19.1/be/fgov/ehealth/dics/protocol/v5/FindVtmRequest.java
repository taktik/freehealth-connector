package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindVtmRequestType",
   propOrder = {"findByProduct", "findByTherapeuticMoiety"}
)
@XmlRootElement(
   name = "FindVtmRequest"
)
public class FindVtmRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByProduct"
   )
   protected FindByVirtualProductType findByProduct;
   @XmlElement(
      name = "FindByTherapeuticMoiety"
   )
   protected FindByTherapeuticMoietyType findByTherapeuticMoiety;

   public FindByVirtualProductType getFindByProduct() {
      return this.findByProduct;
   }

   public void setFindByProduct(FindByVirtualProductType value) {
      this.findByProduct = value;
   }

   public FindByTherapeuticMoietyType getFindByTherapeuticMoiety() {
      return this.findByTherapeuticMoiety;
   }

   public void setFindByTherapeuticMoiety(FindByTherapeuticMoietyType value) {
      this.findByTherapeuticMoiety = value;
   }
}
