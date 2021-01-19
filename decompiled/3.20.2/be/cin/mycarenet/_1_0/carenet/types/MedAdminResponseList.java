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
   name = "MedAdminResponseListType",
   propOrder = {"singleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses"}
)
@XmlRootElement(
   name = "MedAdminResponseList"
)
public class MedAdminResponseList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "SingleNurseContractualCareResponse",
   type = SingleNurseContractualCareResponse.class
), @XmlElement(
   name = "SingleNurseContractualCareUpdate",
   type = SingleNurseContractualCareUpdate.class
), @XmlElement(
   name = "SinglePalliativeCareResponse",
   type = SinglePalliativeCareResponse.class
), @XmlElement(
   name = "SingleSpecificTechnicalCareResponse",
   type = SingleSpecificTechnicalCareResponse.class
)})
   protected List<Serializable> singleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses;

   public List<Serializable> getSingleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses() {
      if (this.singleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses == null) {
         this.singleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses = new ArrayList();
      }

      return this.singleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses;
   }
}
