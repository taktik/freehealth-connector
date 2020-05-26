package be.fgov.ehealth.certra.core.v2;

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
   name = "CertificateDetailsType",
   propOrder = {"serialNumber", "dn", "issuerDN", "validNotBefore", "validNotAfter"}
)
public class CertificateDetailsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SerialNumber",
      required = true
   )
   protected String serialNumber;
   @XmlElement(
      name = "DN",
      required = true
   )
   protected String dn;
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

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String value) {
      this.serialNumber = value;
   }

   public String getDN() {
      return this.dn;
   }

   public void setDN(String value) {
      this.dn = value;
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
}
