package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RawMaterialCNKNumber",
   propOrder = {"cnk"}
)
public class RawMaterialCNKNumber extends AbstractRawMaterialIdType {
   @XmlElement(
      required = true
   )
   protected String cnk;

   public String getCnk() {
      return this.cnk;
   }

   public void setCnk(String value) {
      this.cnk = value;
   }
}
