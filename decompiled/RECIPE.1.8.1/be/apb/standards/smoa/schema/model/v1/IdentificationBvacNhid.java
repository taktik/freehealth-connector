package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationBvacNhid",
   propOrder = {"nhiId", "id"}
)
public class IdentificationBvacNhid {
   @XmlElement(
      defaultValue = "000"
   )
   protected String nhiId;
   protected String id;

   public String getNhiId() {
      return this.nhiId;
   }

   public void setNhiId(String value) {
      this.nhiId = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
