package oasis.names.tc.saml._2_0.assertion;

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
      name = "Address"
   )
   protected String address;
   @XmlAttribute(
      name = "DNSName"
   )
   protected String dnsName;

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String value) {
      this.address = value;
   }

   public String getDNSName() {
      return this.dnsName;
   }

   public void setDNSName(String value) {
      this.dnsName = value;
   }
}
