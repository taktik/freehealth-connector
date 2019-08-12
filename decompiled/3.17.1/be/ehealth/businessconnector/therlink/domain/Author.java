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
      Iterator it = this.hcParties.iterator();

      while(it.hasNext()) {
         HcParty next = (HcParty)it.next();
         builder.append("[");
         builder.append("Family name : " + next.getFamilyName());
         builder.append("First Name: " + next.getFirstName());
         builder.append("Name: " + next.getName());
         builder.append("Type: " + next.getType());
         builder.append("ids : [");
         Iterator i$ = next.getIds().iterator();

         while(i$.hasNext()) {
            IDHCPARTY idHcParty = (IDHCPARTY)i$.next();
            builder.append("idHcParty:scheme=").append(idHcParty.getS());
            builder.append(", value=").append(idHcParty.getValue());
         }

         builder.append("] ");
         builder.append("cds : [");
         i$ = next.getCds().iterator();

         while(i$.hasNext()) {
            CDHCPARTY cdHcParty = (CDHCPARTY)i$.next();
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

      public Author.Builder addHcParty(HcParty hcp) {
         this.author.getHcParties().add(hcp);
         return this;
      }

      public Author build() {
         return this.author;
      }
   }
}
