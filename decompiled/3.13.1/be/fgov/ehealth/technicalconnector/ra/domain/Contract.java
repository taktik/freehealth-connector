package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.TemplateEngineUtils;
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
