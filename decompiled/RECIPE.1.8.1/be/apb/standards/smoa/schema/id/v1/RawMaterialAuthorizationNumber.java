package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RawMaterialAuthorizationNumber",
   propOrder = {"authorizationNr"}
)
public class RawMaterialAuthorizationNumber extends AbstractRawMaterialIdType {
   @XmlElement(
      required = true
   )
   protected String authorizationNr;

   public String getAuthorizationNr() {
      return this.authorizationNr;
   }

   public void setAuthorizationNr(String value) {
      this.authorizationNr = value;
   }
}
