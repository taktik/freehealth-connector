package be.cin.nip.sync.reg.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "registrationType",
   propOrder = {"bankAccount", "anies"}
)
public class RegistrationType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected BankAccountType bankAccount;
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;
   @XmlAttribute(
      name = "serviceName",
      required = true
   )
   protected String serviceName;

   public RegistrationType() {
   }

   public BankAccountType getBankAccount() {
      return this.bankAccount;
   }

   public void setBankAccount(BankAccountType value) {
      this.bankAccount = value;
   }

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String value) {
      this.serviceName = value;
   }
}
