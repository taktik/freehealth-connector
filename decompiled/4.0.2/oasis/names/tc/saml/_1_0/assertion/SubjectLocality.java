package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectLocalityType"
)
@XmlRootElement(
   name = "SubjectLocality"
)
public class SubjectLocality implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "IPAddress"
   )
   protected String ipAddress;
   @XmlAttribute(
      name = "DNSAddress"
   )
   protected String dnsAddress;

   public SubjectLocality() {
   }

   public String getIPAddress() {
      return this.ipAddress;
   }

   public void setIPAddress(String value) {
      this.ipAddress = value;
   }

   public String getDNSAddress() {
      return this.dnsAddress;
   }

   public void setDNSAddress(String value) {
      this.dnsAddress = value;
   }
}
