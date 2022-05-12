package be.ehealth.business.common.util;

import be.fgov.ehealth.commons.core.v2.Status;

public final class EhealthServiceV2Util {
   private static final String SUCCESS = "urn:be:fgov:ehealth:2.0:status:Success";

   public EhealthServiceV2Util() {
   }

   public static boolean isSuccess(Status status) {
      return "urn:be:fgov:ehealth:2.0:status:Success".equals(status.getStatusCode().getValue());
   }
}
