package org.taktik.connector.business.chapterIV.wrapper;

import be.fgov.ehealth.commons.core.v1.StatusType;

public interface ResponseTypeIf {
   StatusType getStatus();

   void setStatus(StatusType var1);

   String getId();

   void setId(String var1);
}
