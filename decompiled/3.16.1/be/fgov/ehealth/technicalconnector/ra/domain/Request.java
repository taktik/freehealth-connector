package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.utils.RaUtils;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Request implements Serializable {
   private static final long serialVersionUID = 1L;
   private Identity requestor;
   private String id;

   public Request() throws TechnicalConnectorException {
      this((Identity)null);
   }

   protected Request(Identity requestor) throws TechnicalConnectorException {
      this.requestor = requestor;
      this.id = RaUtils.generateRequestId();
   }

   public String getId() {
      return this.id;
   }

   public Identity getRequestor() {
      return this.requestor;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Request request = (Request)o;
         return (new EqualsBuilder()).append(this.getRequestor(), request.getRequestor()).append(this.getId(), request.getId()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getRequestor()).append(this.getId()).toHashCode();
   }
}
