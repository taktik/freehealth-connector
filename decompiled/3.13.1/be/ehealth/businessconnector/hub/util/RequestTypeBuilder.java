package be.ehealth.businessconnector.hub.util;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.business.kmehrcommons.builders.Cd;
import be.ehealth.business.kmehrcommons.builders.Id;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.hubservices.core.v1.RequestType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.Iterator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestTypeBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(RequestTypeBuilder.class);
   private RequestType request;

   public static RequestTypeBuilder init() throws TechnicalConnectorException {
      RequestTypeBuilder requestTypeBuilder = new RequestTypeBuilder();
      RequestType newRequest = new RequestType();
      newRequest.setDate(new DateTime());
      newRequest.setTime(new DateTime());
      newRequest.setId(HcPartyUtil.createKmehrId("intrahub", SessionUtil.getNihii()));
      requestTypeBuilder.request = newRequest;
      return requestTypeBuilder;
   }

   public RequestTypeBuilder addGenericAuthor() throws TechnicalConnectorException {
      this.request.setAuthor(HcPartyUtil.createAuthor("intrahub"));
      return this;
   }

   public RequestTypeBuilder addAuthorWithEncryptionInformation() throws TechnicalConnectorException {
      AuthorType author = HcPartyUtil.createAuthor("intrahub");
      this.addSecurityTags(author);
      this.request.setAuthor(author);
      return this;
   }

   private void addSecurityTags(AuthorType author) throws TechnicalConnectorException {
      boolean found = false;
      Iterator i$ = author.getHcparties().iterator();

      while(i$.hasNext()) {
         HcpartyType party = (HcpartyType)i$.next();

         for(Iterator i$ = party.getCds().iterator(); i$.hasNext(); found = true) {
            CDHCPARTY partyType = (CDHCPARTY)i$.next();
            partyType.getValue().equalsIgnoreCase(HcPartyUtil.getAuthorKmehrQuality());
         }

         if (found) {
            this.addEncryptionActorForNihiiOrNiss(party);
         }
      }

   }

   public RequestType build() {
      return this.request;
   }

   private void addEncryptionActorForNihiiOrNiss(HcpartyType hcParty) throws TechnicalConnectorException {
      Id idBuilder = (new Id()).s(IDHCPARTYschemes.ID_ENCRYPTION_ACTOR).sv("1.0");
      Cd cdBuilder = (new Cd()).s(CDHCPARTYschemes.CD_ENCRYPTION_ACTOR).sv("1.0");
      if (this.getNihii() == null) {
         idBuilder.value(SessionUtil.getNiss());
         cdBuilder.value(IdentifierType.SSIN.getType(48));
      } else {
         idBuilder.value(this.getNihii());
         cdBuilder.value(IdentifierType.NIHII.getType(48));
      }

      hcParty.getIds().add(idBuilder.build());
      hcParty.getCds().add(cdBuilder.build());
   }

   private String getNihii() {
      try {
         return SessionUtil.getNihii();
      } catch (TechnicalConnectorException var2) {
         LOG.debug("Unable to obtain nihii. Reason: " + var2.getMessage(), var2);
         return null;
      }
   }
}
