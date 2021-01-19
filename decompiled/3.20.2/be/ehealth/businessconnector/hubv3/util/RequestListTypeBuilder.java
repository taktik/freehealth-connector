package be.ehealth.businessconnector.hubv3.util;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.hubservices.core.v3.Paginationrequestinfo;
import be.fgov.ehealth.hubservices.core.v3.RequestList;
import org.joda.time.DateTime;

public class RequestListTypeBuilder {
   public static final String PROJECT_NAME = "hubservicev3";
   private RequestList request;

   public static RequestListTypeBuilder init() throws TechnicalConnectorException {
      RequestListTypeBuilder requestTypeBuilder = new RequestListTypeBuilder();
      RequestList newRequest = new RequestList();
      newRequest.setDate(new DateTime());
      newRequest.setTime(new DateTime());
      newRequest.setId(HcPartyUtil.createKmehrId("hubservicev3", SessionUtil.getNihii()));
      requestTypeBuilder.request = newRequest;
      return requestTypeBuilder;
   }

   public RequestListTypeBuilder addGenericAuthor() throws TechnicalConnectorException {
      this.request.setAuthor(HcPartyUtil.createAuthor("hubservicev3"));
      return this;
   }

   public RequestListTypeBuilder addBreakTheGlass(String breakTheGlass) {
      this.request.setBreaktheglass(breakTheGlass);
      return this;
   }

   public RequestListTypeBuilder addPaginationInfo(Paginationrequestinfo pagReqInfo) {
      this.request.setPaginationinfo(pagReqInfo);
      return this;
   }

   public RequestList build() {
      return this.request;
   }

   private RequestListTypeBuilder() {
   }
}
