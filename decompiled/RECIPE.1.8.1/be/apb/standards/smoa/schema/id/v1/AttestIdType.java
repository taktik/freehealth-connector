package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttestIdType",
   propOrder = {"attestNumber"}
)
public class AttestIdType extends AbstractAttestIdType {
   @XmlElement(
      required = true
   )
   protected String attestNumber;

   public String getAttestNumber() {
      return this.attestNumber;
   }

   public void setAttestNumber(String value) {
      this.attestNumber = value;
   }
}
