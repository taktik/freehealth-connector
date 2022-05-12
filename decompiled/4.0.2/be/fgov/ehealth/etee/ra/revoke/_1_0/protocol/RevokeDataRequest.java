package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"requestId", "contract", "revocableCertificatesDataSignedResponse"}
)
@XmlRootElement(
   name = "RevokeDataRequest"
)
public class RevokeDataRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RequestId",
      required = true
   )
   protected String requestId;
   @XmlElement(
      name = "Contract",
      required = true
   )
   protected String contract;
   @XmlElement(
      name = "RevocableCertificatesDataSignedResponse"
   )
   protected byte[] revocableCertificatesDataSignedResponse;

   public RevokeDataRequest() {
   }

   public String getRequestId() {
      return this.requestId;
   }

   public void setRequestId(String value) {
      this.requestId = value;
   }

   public String getContract() {
      return this.contract;
   }

   public void setContract(String value) {
      this.contract = value;
   }

   public byte[] getRevocableCertificatesDataSignedResponse() {
      return this.revocableCertificatesDataSignedResponse;
   }

   public void setRevocableCertificatesDataSignedResponse(byte[] value) {
      this.revocableCertificatesDataSignedResponse = value;
   }
}
