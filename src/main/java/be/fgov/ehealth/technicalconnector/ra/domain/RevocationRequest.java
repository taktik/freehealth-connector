package be.fgov.ehealth.technicalconnector.ra.domain;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.io.Serializable;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;

public final class RevocationRequest extends Request implements Serializable {
   private static final long serialVersionUID = 1L;
   private byte[] publicKeyIdentifier;
   private GeneratedRevocationContract contract;
   private boolean contractViewed;

   public RevocationRequest(byte[] publicKeyIdentifier, GeneratedRevocationContract contract) throws TechnicalConnectorException {
      Validate.notNull(publicKeyIdentifier);
      Validate.notNull(contract);
      Validate.isTrue(contract.isContractViewed());
      this.publicKeyIdentifier = ArrayUtils.clone(publicKeyIdentifier);
      this.contract = contract;
   }

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public GeneratedRevocationContract getContract() {
      return this.contract;
   }

   public boolean isContractViewed() {
      return this.contractViewed;
   }

   public void setContractViewed(boolean contractViewed) {
      this.contractViewed = contractViewed;
   }
}
