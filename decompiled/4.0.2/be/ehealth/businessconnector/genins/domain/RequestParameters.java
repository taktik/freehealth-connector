package be.ehealth.businessconnector.genins.domain;

import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestTypeType;
import java.io.Serializable;
import org.joda.time.DateTime;

public class RequestParameters extends Routing implements Serializable {
   private static final long serialVersionUID = 1L;
   private Routing routing = new Routing();
   private InsurabilityRequestTypeType insurabilityRequestType;
   private InsurabilityContactTypeType insurabilityContactType;
   private String insurabilityReference;

   public RequestParameters() {
   }

   public String getInss() {
      return this.routing.getCareReceiver() == null ? null : this.routing.getCareReceiver().getSsinNumber();
   }

   public String getRegNrWithMut() {
      return this.routing.getCareReceiver() == null ? null : this.routing.getCareReceiver().getRegistrationNumberWithMutuality();
   }

   public String getMutuality() {
      return this.routing.getCareReceiver() == null ? null : this.routing.getCareReceiver().getMutuality();
   }

   public InsurabilityRequestTypeType getInsurabilityRequestType() {
      return this.insurabilityRequestType;
   }

   public DateTime getPeriodStart() {
      return this.routing.getPeriod() == null ? null : this.routing.getPeriod().getBegin();
   }

   public DateTime getPeriodEnd() {
      return this.routing.getPeriod() == null ? null : this.routing.getPeriod().getEnd();
   }

   public InsurabilityContactTypeType getInsurabilityContactType() {
      return this.insurabilityContactType;
   }

   public String getInsurabilityReference() {
      return this.insurabilityReference;
   }

   public void setInss(String inss) {
      this.routing.setCareReceiver(new CareReceiverId(inss));
      this.routing.getCareReceiver().setSsinNumber(inss);
   }

   public void setRegNrWithMut(String regNrWithMut) {
      if (this.routing.getCareReceiver() == null) {
         this.routing.setCareReceiver((CareReceiverId)null);
      }

      this.routing.getCareReceiver().setRegistrationNumberWithMutuality(regNrWithMut);
   }

   public void setMutuality(String mutuality) {
      if (this.routing.getCareReceiver() == null) {
         this.routing.setCareReceiver((CareReceiverId)null);
      }

      this.routing.getCareReceiver().setMutuality(mutuality);
   }

   public void setInsurabilityRequestType(InsurabilityRequestTypeType insurabilityRequestType) {
      this.insurabilityRequestType = insurabilityRequestType;
   }

   public void setPeriodStart(DateTime periodStart) {
      if (this.routing.getPeriod() == null) {
         this.routing.setPeriod(new Period(periodStart, (DateTime)null));
      }

      this.routing.getPeriod().setBegin(periodStart);
   }

   public void setPeriodEnd(DateTime periodEnd) {
      if (this.routing.getPeriod() == null) {
         this.routing.setPeriod(new Period((DateTime)null, periodEnd));
      }

      this.routing.getPeriod().setEnd(periodEnd);
   }

   public void setInsurabilityContactType(InsurabilityContactTypeType insurabilityContactType) {
      this.insurabilityContactType = insurabilityContactType;
   }

   public void setInsurabilityReference(String insurabilityReference) {
      this.insurabilityReference = insurabilityReference;
   }
}
