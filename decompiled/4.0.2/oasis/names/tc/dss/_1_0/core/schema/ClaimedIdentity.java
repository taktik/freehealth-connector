package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"name", "supportingInfo"}
)
@XmlRootElement(
   name = "ClaimedIdentity"
)
public class ClaimedIdentity implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected NameIdentifierType name;
   @XmlElement(
      name = "SupportingInfo"
   )
   protected AnyType supportingInfo;

   public ClaimedIdentity() {
   }

   public NameIdentifierType getName() {
      return this.name;
   }

   public void setName(NameIdentifierType value) {
      this.name = value;
   }

   public AnyType getSupportingInfo() {
      return this.supportingInfo;
   }

   public void setSupportingInfo(AnyType value) {
      this.supportingInfo = value;
   }
}
