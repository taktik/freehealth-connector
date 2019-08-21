package org.taktik.connector.business.consultrn.exception.manageperson;

import org.taktik.connector.business.common.exception.EhealthServiceV2Exception;
import org.taktik.connector.business.common.helper.EhealthServiceHelper;
import be.fgov.ehealth.consultrn.commons.core.v3.BusinessAnomalies;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;

public class ConsultrnRegisterPersonException extends EhealthServiceV2Exception {
   private static final long serialVersionUID = 1L;
   private final RegisterPersonResponse registerPersonResponse;
   private final BusinessAnomalies businessAnomalies;

   public ConsultrnRegisterPersonException(RegisterPersonResponse response) {
      super(response);
      this.registerPersonResponse = response;
      this.businessAnomalies = (BusinessAnomalies)EhealthServiceHelper.getFirst(response.getStatus().getStatusDetail(), BusinessAnomalies.class);
   }

   public RegisterPersonResponse getRegisterPersonResponse() {
      return this.registerPersonResponse;
   }

   public BusinessAnomalies getBusinessAnomalies() {
      return this.businessAnomalies;
   }
}
