package be.fgov.ehealth.dics.core.v4.reimbursement.submit;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public ReimbursementContextKeyType createReimbursementContextKeyType() {
      return new ReimbursementContextKeyType();
   }

   public CopaymentKeyType createCopaymentKeyType() {
      return new CopaymentKeyType();
   }
}
