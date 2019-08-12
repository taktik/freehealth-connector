package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevocableCertificateType",
   propOrder = {"requestId", "authDN", "issuerDN", "validNotBefore", "validNotAfter", "authSerial"}
)
public class RevocableCertificateType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RequestId",
      required = true
   )
   protected String requestId;
   @XmlElement(
      name = "AuthDN",
      required = true
   )
   protected String authDN;
   @XmlElement(
      name = "IssuerDN",
      required = true
   )
   protected String issuerDN;
   @XmlElement(
      name = "ValidNotBefore",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime validNotBefore;
   @XmlElement(
      name = "ValidNotAfter",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime validNotAfter;
   @XmlElement(
      name = "AuthSerial",
      required = true
   )
   protected String authSerial;

   public String getRequestId() {
      return this.requestId;
   }

   public void setRequestId(String value) {
      this.requestId = value;
   }

   public String getAuthDN() {
      return this.authDN;
   }

   public void setAuthDN(String value) {
      this.authDN = value;
   }

   public String getIssuerDN() {
      return this.issuerDN;
   }

   public void setIssuerDN(String value) {
      this.issuerDN = value;
   }

   public DateTime getValidNotBefore() {
      return this.validNotBefore;
   }

   public void setValidNotBefore(DateTime value) {
      this.validNotBefore = value;
   }

   public DateTime getValidNotAfter() {
      return this.validNotAfter;
   }

   public void setValidNotAfter(DateTime value) {
      this.validNotAfter = value;
   }

   public String getAuthSerial() {
      return this.authSerial;
   }

   public void setAuthSerial(String value) {
      this.authSerial = value;
   }
}
