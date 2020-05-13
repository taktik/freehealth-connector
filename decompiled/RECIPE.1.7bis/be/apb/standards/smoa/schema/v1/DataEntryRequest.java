package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataEntryRequest",
   propOrder = {"reference"}
)
public class DataEntryRequest extends AbstractDataEntry {
   @XmlElement(
      required = true
   )
   protected String reference;

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }
}
