package be.ehealth.businessconnector.hubv3.util;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.hubservices.core.v3.RequestType;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import org.joda.time.DateTime;

public class RequestTypeBuilder {
   public static final String PROJECT_NAME = "hubservicev3";
   private RequestType request;

   public static RequestTypeBuilder init() throws TechnicalConnectorException {
      RequestTypeBuilder requestTypeBuilder = new RequestTypeBuilder();
      RequestType newRequest = new RequestType();
      newRequest.setDate(new DateTime());
      newRequest.setTime(new DateTime());
      newRequest.setId(HcPartyUtil.createKmehrId("hubservicev3", SessionUtil.getNihii()));
      requestTypeBuilder.request = newRequest;
      return requestTypeBuilder;
   }

   public RequestTypeBuilder addGenericAuthor() throws TechnicalConnectorException {
      this.request.setAuthor(HcPartyUtil.createAuthor("hubservicev3"));
      return this;
   }

   public RequestTypeBuilder addAuthorWithEncryptionInformation() throws TechnicalConnectorException {
      AuthorType author = HcPartyUtil.createAuthor("hubservicev3");
      HcPartyUtil.addSecurityTags(author);
      this.request.setAuthor(author);
      return this;
   }

   public RequestType build() {
      return this.request;
   }
}
