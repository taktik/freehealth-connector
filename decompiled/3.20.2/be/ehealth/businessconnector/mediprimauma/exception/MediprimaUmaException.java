package be.ehealth.businessconnector.mediprimauma.exception;

import be.ehealth.business.common.exception.EhealthServiceV2Exception;
import be.ehealth.business.common.helper.EhealthServiceHelper;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.mediprima.uma.core.v1.RegistryStatus;

public abstract class MediprimaUmaException extends EhealthServiceV2Exception {
   private static final long serialVersionUID = 1L;
   private final RegistryStatus registryStatus;

   public MediprimaUmaException(StatusResponseType response) {
      super(response);
      this.registryStatus = (RegistryStatus)EhealthServiceHelper.getFirst(response.getStatus().getStatusDetail(), RegistryStatus.class);
   }

   public RegistryStatus getRegistryStatus() {
      return this.registryStatus;
   }
}
