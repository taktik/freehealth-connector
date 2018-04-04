package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Identification",
   propOrder = {"id", "financialSeqCode"}
)
public class Identification {
   @XmlElement(
      required = true
   )
   protected String id;
   protected String financialSeqCode;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getFinancialSeqCode() {
      return this.financialSeqCode;
   }

   public void setFinancialSeqCode(String value) {
      this.financialSeqCode = value;
   }
}
