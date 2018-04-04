package be.cin.nip.sync.reg.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "bankAccountType"
)
public class BankAccountType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "bic"
   )
   protected String bic;
   @XmlAttribute(
      name = "iban",
      required = true
   )
   protected String iban;

   public String getBic() {
      return this.bic;
   }

   public void setBic(String value) {
      this.bic = value;
   }

   public String getIban() {
      return this.iban;
   }

   public void setIban(String value) {
      this.iban = value;
   }
}
