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
   name = "InsurabilityResponseListType",
   propOrder = {"singleInsurabilityResponses"}
)
@XmlRootElement(
   name = "InsurabilityResponseList"
)
public class InsurabilityResponseList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SingleInsurabilityResponse",
      required = true
   )
   protected List<SingleInsurabilityResponse> singleInsurabilityResponses;

   public InsurabilityResponseList() {
   }

   public List<SingleInsurabilityResponse> getSingleInsurabilityResponses() {
      if (this.singleInsurabilityResponses == null) {
         this.singleInsurabilityResponses = new ArrayList();
      }

      return this.singleInsurabilityResponses;
   }
}
