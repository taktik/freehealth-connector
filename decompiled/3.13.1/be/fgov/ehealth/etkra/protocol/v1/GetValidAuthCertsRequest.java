package be.fgov.ehealth.etkra.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"distinguishedName"}
)
@XmlRootElement(
   name = "GetValidAuthCertsRequest"
)
public class GetValidAuthCertsRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DistinguishedName",
      required = true
   )
   protected EhealthDistinguishedNameType distinguishedName;

   public EhealthDistinguishedNameType getDistinguishedName() {
      return this.distinguishedName;
   }

   public void setDistinguishedName(EhealthDistinguishedNameType value) {
      this.distinguishedName = value;
   }
}
