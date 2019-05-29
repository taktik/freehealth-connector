package be.cin.mycarenet._1_0.carenet.types;

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
   name = "InsurabilityRequestListType",
   propOrder = {"singleInsurabilityRequests"}
)
@XmlRootElement(
   name = "InsurabilityRequestList"
)
public class InsurabilityRequestList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SingleInsurabilityRequest",
      required = true
   )
   protected List<SingleInsurabilityRequest> singleInsurabilityRequests;

   public List<SingleInsurabilityRequest> getSingleInsurabilityRequests() {
      if (this.singleInsurabilityRequests == null) {
         this.singleInsurabilityRequests = new ArrayList();
      }

      return this.singleInsurabilityRequests;
   }
}
