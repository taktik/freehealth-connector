package be.cin.io.sealed.medicaladvisoragreement.ask.v1;

import be.cin.types.v1.CareReceiverIdType;
import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"careReceiver", "agreementStartDate", "unsealKeyId", "sealedContent"}
)
@XmlRootElement(
   name = "Request"
)
public class Request implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverIdType careReceiver;
   @XmlElement(
      name = "AgreementStartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime agreementStartDate;
   @XmlElement(
      name = "UnsealKeyId",
      required = true
   )
   protected String unsealKeyId;
   @XmlElement(
      name = "SealedContent",
      required = true
   )
   protected byte[] sealedContent;

   public Request() {
   }

   public CareReceiverIdType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverIdType value) {
      this.careReceiver = value;
   }

   public DateTime getAgreementStartDate() {
      return this.agreementStartDate;
   }

   public void setAgreementStartDate(DateTime value) {
      this.agreementStartDate = value;
   }

   public String getUnsealKeyId() {
      return this.unsealKeyId;
   }

   public void setUnsealKeyId(String value) {
      this.unsealKeyId = value;
   }

   public byte[] getSealedContent() {
      return this.sealedContent;
   }

   public void setSealedContent(byte[] value) {
      this.sealedContent = value;
   }
}
