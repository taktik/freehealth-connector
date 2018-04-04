package be.fgov.ehealth.technicalconnector.ra.domain;

import org.taktik.connector.technical.beid.BeIDInfo;
import org.taktik.connector.technical.beid.domain.Identity;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.TemplateEngineUtils;
import java.io.Serializable;
import java.util.Map;

public abstract class Contract implements Serializable {
   private static final long serialVersionUID = 1L;
   private boolean contractViewed;
   private Identity requestor;

   Contract() throws TechnicalConnectorException {
      this.requestor = BeIDInfo.getInstance().getIdentity();
   }

   protected Contract(Identity requestor) {
      this.requestor = requestor;
   }

   protected abstract String getContent();

   public String getContract() {
      this.contractViewed = true;
      return this.getContent();
   }

   public boolean isContractViewed() {
      return this.contractViewed;
   }

   public Identity getRequestor() {
      return this.requestor;
   }

   protected static String generatedContract(Map<String, Object> ctx, String templateLocation) throws TechnicalConnectorException {
      return TemplateEngineUtils.generate(ctx, templateLocation);
   }
}
