package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindNonMedicinalProductResponseType",
   propOrder = {"nonMedicinalProducts"}
)
@XmlRootElement(
   name = "FindNonMedicinalProductResponse"
)
public class FindNonMedicinalProductResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NonMedicinalProduct"
   )
   protected List<ConsultNonMedicinalProductType> nonMedicinalProducts;

   public FindNonMedicinalProductResponse() {
   }

   public List<ConsultNonMedicinalProductType> getNonMedicinalProducts() {
      if (this.nonMedicinalProducts == null) {
         this.nonMedicinalProducts = new ArrayList();
      }

      return this.nonMedicinalProducts;
   }
}
