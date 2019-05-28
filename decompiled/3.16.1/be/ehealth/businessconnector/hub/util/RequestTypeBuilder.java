package be.ehealth.businessconnector.hub.util;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.hubservices.core.v1.RequestType;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
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
      HcPartyUtil.addSecurityTags(author);
      this.request.setAuthor(author);
      return this;
   }

   public RequestType build() {
      return this.request;
   }
}
