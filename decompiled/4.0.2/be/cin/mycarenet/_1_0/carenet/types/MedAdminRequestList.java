package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedAdminRequestListType",
   propOrder = {"singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests"}
)
@XmlRootElement(
   name = "MedAdminRequestList"
)
public class MedAdminRequestList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "SingleNurseContractualCareRequest",
   type = SingleNurseContractualCareRequest.class
), @XmlElement(
   name = "SinglePalliativeCareRequest",
   type = SinglePalliativeCareRequest.class
), @XmlElement(
   name = "SingleSpecificTechnicalCareRequest",
   type = SingleSpecificTechnicalCareRequest.class
)})
   protected List<Serializable> singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests;

   public MedAdminRequestList() {
   }

   public List<Serializable> getSingleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests() {
      if (this.singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests == null) {
         this.singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests = new ArrayList();
      }

      return this.singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests;
   }
}
