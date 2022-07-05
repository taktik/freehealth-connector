package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ScopingType",
   propOrder = {"idpList", "requesterIDs"}
)
@XmlRootElement(
   name = "Scoping"
)
public class Scoping implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IDPList"
   )
   protected IDPList idpList;
   @XmlElement(
      name = "RequesterID"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> requesterIDs;
   @XmlAttribute(
      name = "ProxyCount"
   )
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   protected BigInteger proxyCount;

   public Scoping() {
   }

   public IDPList getIDPList() {
      return this.idpList;
   }

   public void setIDPList(IDPList value) {
      this.idpList = value;
   }

   public List<String> getRequesterIDs() {
      if (this.requesterIDs == null) {
         this.requesterIDs = new ArrayList();
      }

      return this.requesterIDs;
   }

   public BigInteger getProxyCount() {
      return this.proxyCount;
   }

   public void setProxyCount(BigInteger value) {
      this.proxyCount = value;
   }
}
