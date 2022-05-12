package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NurseContractualCareRequestDetailType",
   propOrder = {"nurseReference", "requester", "contractualCareDetail"}
)
@XmlRootElement(
   name = "NurseContractualCareRequestDetail"
)
public class NurseContractualCareRequestDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NurseReference",
      required = true
   )
   protected String nurseReference;
   @XmlElement(
      name = "Requester",
      required = true
   )
   protected String requester;
   @XmlElement(
      name = "ContractualCareDetail",
      required = true
   )
   protected NurseContractualCareDetailType contractualCareDetail;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected RequestTypeType type;

   public NurseContractualCareRequestDetail() {
   }

   public String getNurseReference() {
      return this.nurseReference;
   }

   public void setNurseReference(String value) {
      this.nurseReference = value;
   }

   public String getRequester() {
      return this.requester;
   }

   public void setRequester(String value) {
      this.requester = value;
   }

   public NurseContractualCareDetailType getContractualCareDetail() {
      return this.contractualCareDetail;
   }

   public void setContractualCareDetail(NurseContractualCareDetailType value) {
      this.contractualCareDetail = value;
   }

   public RequestTypeType getType() {
      return this.type;
   }

   public void setType(RequestTypeType value) {
      this.type = value;
   }
}
