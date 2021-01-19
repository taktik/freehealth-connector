package be.cin.nip.async.business;

import be.cin.mycarenet._1_0.carenet.types.CareReceiverId;
import be.cin.mycarenet._1_0.carenet.types.InsurabilityRequestDetail;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericRequestType",
   propOrder = {"careReceiver", "insRequest", "extendedInformation"}
)
@XmlRootElement(
   name = "GenericRequest"
)
public class GenericRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverId careReceiver;
   @XmlElement(
      required = true
   )
   protected InsurabilityRequestDetail insRequest;
   @XmlElement(
      name = "ExtendedInformation"
   )
   protected SamlAttributeType extendedInformation;
   @XmlAttribute(
      name = "recordInputReference"
   )
   protected String recordInputReference;

   public CareReceiverId getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverId value) {
      this.careReceiver = value;
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

   public String getRecordInputReference() {
      return this.recordInputReference;
   }

   public void setRecordInputReference(String value) {
      this.recordInputReference = value;
   }
}
