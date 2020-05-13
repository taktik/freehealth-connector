package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CareReceiverIdType",
   propOrder = {"inss", "regNrWithMut", "mutuality"}
)
public class CareReceiverIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Inss"
   )
   protected String inss;
   @XmlElement(
      name = "RegNrWithMut"
   )
   protected String regNrWithMut;
   @XmlElement(
      name = "Mutuality"
   )
   protected String mutuality;

   public String getInss() {
      return this.inss;
   }

   public void setInss(String value) {
      this.inss = value;
   }

   public String getRegNrWithMut() {
      return this.regNrWithMut;
   }

   public void setRegNrWithMut(String value) {
      this.regNrWithMut = value;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String value) {
      this.mutuality = value;
   }
}
