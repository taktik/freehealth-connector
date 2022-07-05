package be.cin.nip.async.business;

import be.cin.mycarenet._1_0.carenet.types.CareReceiverDetailType;
import be.cin.mycarenet._1_0.carenet.types.CareReceiverId;
import be.cin.mycarenet._1_0.carenet.types.InsurabilityRequestDetail;
import be.cin.mycarenet._1_0.carenet.types.InsurabilityResponseDetail;
import be.cin.mycarenet._1_0.carenet.types.MessageFaultType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericResponseType",
   propOrder = {"careReceiverId", "insRequest", "extendedInformation", "careReceiverDetail", "insurabilityResponseDetail", "messageFault"}
)
@XmlRootElement(
   name = "GenericResponse"
)
public class GenericResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareReceiverId",
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      required = true
   )
   protected CareReceiverId careReceiverId;
   @XmlElement(
      required = true
   )
   protected InsurabilityRequestDetail insRequest;
   @XmlElement(
      name = "ExtendedInformation"
   )
   protected SamlAttributeType extendedInformation;
   @XmlElement(
      name = "CareReceiverDetail",
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types"
   )
   protected CareReceiverDetailType careReceiverDetail;
   @XmlElement(
      name = "InsurabilityResponseDetail",
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types"
   )
   protected InsurabilityResponseDetail insurabilityResponseDetail;
   @XmlElement(
      name = "MessageFault"
   )
   protected MessageFaultType messageFault;
   @XmlAttribute(
      name = "recordInputReference"
   )
   protected String recordInputReference;
   @XmlAttribute(
      name = "recordOutputReference"
   )
   protected String recordOutputReference;

   public GenericResponse() {
   }

   public CareReceiverId getCareReceiverId() {
      return this.careReceiverId;
   }

   public void setCareReceiverId(CareReceiverId value) {
      this.careReceiverId = value;
   }

   public InsurabilityRequestDetail getInsRequest() {
      return this.insRequest;
   }

   public void setInsRequest(InsurabilityRequestDetail value) {
      this.insRequest = value;
   }

   public SamlAttributeType getExtendedInformation() {
      return this.extendedInformation;
   }

   public void setExtendedInformation(SamlAttributeType value) {
      this.extendedInformation = value;
   }

   public CareReceiverDetailType getCareReceiverDetail() {
      return this.careReceiverDetail;
   }

   public void setCareReceiverDetail(CareReceiverDetailType value) {
      this.careReceiverDetail = value;
   }

   public InsurabilityResponseDetail getInsurabilityResponseDetail() {
      return this.insurabilityResponseDetail;
   }

   public void setInsurabilityResponseDetail(InsurabilityResponseDetail value) {
      this.insurabilityResponseDetail = value;
   }

   public MessageFaultType getMessageFault() {
      return this.messageFault;
   }

   public void setMessageFault(MessageFaultType value) {
      this.messageFault = value;
   }

   public String getRecordInputReference() {
      return this.recordInputReference;
   }

   public void setRecordInputReference(String value) {
      this.recordInputReference = value;
   }

   public String getRecordOutputReference() {
      return this.recordOutputReference;
   }

   public void setRecordOutputReference(String value) {
      this.recordOutputReference = value;
   }
}
