package be.ehealth.businessconnector.therlink.domain;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Author implements Serializable {
   private static final long serialVersionUID = 1L;
   private Set<HcParty> hcParties;

   public Author() {
   }

   public Set<HcParty> getHcParties() {
      if (this.hcParties == null) {
         this.hcParties = new LinkedHashSet();
      }

      return this.hcParties;
   }

   public void setHcParties(Set<HcParty> hcParties) {
      this.hcParties = hcParties;
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      Iterator<HcParty> it = this.hcParties.iterator();

      while(it.hasNext()) {
         HcParty next = (HcParty)it.next();
         builder.append("[");
         builder.append("Family name : " + next.getFamilyName());
         builder.append("First Name: " + next.getFirstName());
         builder.append("Name: " + next.getName());
         builder.append("Type: " + next.getType());
         builder.append("ids : [");
         Iterator var4 = next.getIds().iterator();

         while(var4.hasNext()) {
            IDHCPARTY idHcParty = (IDHCPARTY)var4.next();
            builder.append("idHcParty:scheme=").append(idHcParty.getS());
            builder.append(", value=").append(idHcParty.getValue());
         }

         builder.append("] ");
         builder.append("cds : [");
         var4 = next.getCds().iterator();

         while(var4.hasNext()) {
            CDHCPARTY cdHcParty = (CDHCPARTY)var4.next();
            builder.append("cdHcParty:scheme=").append(cdHcParty.getS());
            builder.append(", value=").append(cdHcParty.getValue());
         }

         builder.append("]");
         builder.append("]");
         if (it.hasNext()) {
            builder.append(", ");
         }
      }

      return builder.toString();
   }

   public static class Builder {
      private Author author = new Author();

      public Builder() {
      }

      public Builder addHcParty(HcParty hcp) {
         this.author.getHcParties().add(hcp);
         return this;
      }

      public Author build() {
         return this.author;
      }
   }
}
