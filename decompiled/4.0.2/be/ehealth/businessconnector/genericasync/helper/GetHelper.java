package be.ehealth.businessconnector.genericasync.helper;

import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgQuery;
import be.cin.nip.async.generic.Query;
import be.cin.nip.async.generic.QueryParameters;
import be.ehealth.business.mycarenetdomaincommons.builders.CommonBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.business.mycarenetdomaincommons.util.WsAddressingUtil;
import be.ehealth.businessconnector.genericasync.builders.BuilderFactory;
import be.ehealth.businessconnector.genericasync.domain.GetRequest;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedTAckResponse;
import be.ehealth.businessconnector.genericasync.mappers.CommonInputMapper;
import be.ehealth.businessconnector.genericasync.session.GenAsyncSessionServiceFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import java.util.Iterator;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.factory.Mappers;

public class GetHelper {
   private String projectName;

   public GetHelper(String projectName) {
      this.projectName = projectName;
   }

   public ProcessedGetResponse get(GetRequest request, String message, String recipient, Class clazz, String schemaLocation) throws ConnectorException {
      MsgQuery msgQuery = new MsgQuery();
      msgQuery.setInclude(true);
      msgQuery.setMax(request.getMaxMessages());
      msgQuery.getMessageNames().add(message);
      QueryParameters queryParameters = new QueryParameters();
      queryParameters.setReference(request.getReference());
      queryParameters.getExcludeIOs().addAll(request.getExcludeIOs());
      queryParameters.getIncludeIOs().addAll(request.getIncludeIOs());
      if (request.getTackMessageNames() != null) {
         queryParameters.getTackMessageNames().addAll(request.getTackMessageNames());
      }

      Query tackQuery = new Query();
      tackQuery.setInclude(true);
      tackQuery.setMax(request.getMaxTAcks());
      OrigineType origin = this.getOrigineType(this.projectName);
      WsAddressingHeader getResponseHeader = WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:get:query");
      GetResponse getResponse = GenAsyncSessionServiceFactory.getGenAsyncService(this.projectName).getRequest(BuilderFactory.getRequestObjectBuilder(this.projectName).buildGetRequest(origin, msgQuery, tackQuery, queryParameters, KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded()), getResponseHeader);
      return BuilderFactory.getResponseObjectBuilder().processResponse(getResponse, clazz, this.projectName, schemaLocation);
   }

   public ProcessedGetResponse get(GetRequest request, String message, Class clazz, String schemaLocation) throws ConnectorException {
      return this.get(request, message, (String)null, clazz, schemaLocation);
   }

   public ProcessedGetResponse get(GetRequest request, String message, Class clazz) throws ConnectorException {
      return this.get(request, message, (String)null, clazz, (String)null);
   }

   public ConfirmResponse confirmAll(ProcessedGetResponse getResponse) throws ConnectorException {
      return this.confirmAll(getResponse, (String)null);
   }

   public <T> ConfirmResponse confirmAll(ProcessedGetResponse<T> getResponse, String recipient) throws ConnectorException {
      if (!CollectionUtils.isNotEmpty(getResponse.getTAckResponses()) && !CollectionUtils.isNotEmpty(getResponse.getMsgResponses())) {
         return null;
      } else {
         Confirm confirm = this.initConfirmRequest(this.projectName);
         Iterator var4 = getResponse.getTAckResponses().iterator();

         while(var4.hasNext()) {
            ProcessedTAckResponse tAckResponse = (ProcessedTAckResponse)var4.next();
            confirm.getTAckReferences().add(tAckResponse.getTAckResponse().getTAck().getReference());
         }

         var4 = getResponse.getMsgResponses().iterator();

         while(var4.hasNext()) {
            ProcessedMsgResponse msgResponse = (ProcessedMsgResponse)var4.next();
            confirm.getMsgRefValues().add(msgResponse.getMsgResponse().getDetail().getReference());
         }

         return GenAsyncSessionServiceFactory.getGenAsyncService(this.projectName).confirmRequest(confirm, WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:confirm:hash"));
      }
   }

   public ConfirmResponse confirmWithTAckReferences(ProcessedGetResponse getResponse) throws ConnectorException {
      return this.confirmWithTAckReferences(getResponse, (String)null);
   }

   public <T> ConfirmResponse confirmWithTAckReferences(ProcessedGetResponse<T> getResponse, String recipient) throws ConnectorException {
      if (!CollectionUtils.isNotEmpty(getResponse.getTAckResponses())) {
         return null;
      } else {
         Confirm confirm = this.initConfirmRequest(this.projectName);
         Iterator var4 = getResponse.getTAckResponses().iterator();

         while(var4.hasNext()) {
            ProcessedTAckResponse tAckResponse = (ProcessedTAckResponse)var4.next();
            confirm.getTAckReferences().add(tAckResponse.getTAckResponse().getTAck().getReference());
         }

         return GenAsyncSessionServiceFactory.getGenAsyncService(this.projectName).confirmRequest(confirm, WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:confirm:hash"));
      }
   }

   public Confirm initConfirmRequest(String projectName) throws TechnicalConnectorException {
      Confirm confirm = new Confirm();
      confirm.setOrigin(this.getOrigineType(projectName));
      return confirm;
   }

   public ConfirmResponse confirmWithMessageReferences(ProcessedGetResponse getResponse) throws ConnectorException {
      return this.confirmWithMessageReferences(getResponse, (String)null);
   }

   public <T> ConfirmResponse confirmWithMessageReferences(ProcessedGetResponse<T> getResponse, String recipient) throws ConnectorException {
      if (!CollectionUtils.isNotEmpty(getResponse.getMsgResponses())) {
         return null;
      } else {
         Confirm confirm = this.initConfirmRequest(this.projectName);
         Iterator var4 = getResponse.getMsgResponses().iterator();

         while(var4.hasNext()) {
            ProcessedMsgResponse msgResponse = (ProcessedMsgResponse)var4.next();
            confirm.getMsgRefValues().add(msgResponse.getMsgResponse().getDetail().getReference());
         }

         return GenAsyncSessionServiceFactory.getGenAsyncService(this.projectName).confirmRequest(confirm, WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:confirm:hash"));
      }
   }

   public ConfirmResponse confirmTAckWithReference(ProcessedTAckResponse tAckResponse) throws ConnectorException {
      return this.confirmTAckWithReference(tAckResponse, (String)null);
   }

   public ConfirmResponse confirmTAckWithReference(ProcessedTAckResponse tAckResponse, String recipient) throws ConnectorException {
      Confirm confirm = this.initConfirmRequest(this.projectName);
      confirm.getTAckReferences().add(tAckResponse.getTAckResponse().getTAck().getReference());
      return GenAsyncSessionServiceFactory.getGenAsyncService(this.projectName).confirmRequest(confirm, WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:confirm:hash"));
   }

   public ConfirmResponse confirmMessageWithReference(ProcessedMsgResponse msgResponse) throws ConnectorException {
      return this.confirmMessageWithReference(msgResponse, (String)null);
   }

   public ConfirmResponse confirmMessageWithReference(ProcessedMsgResponse msgResponse, String recipient) throws ConnectorException {
      Confirm confirm = this.initConfirmRequest(this.projectName);
      confirm.getMsgRefValues().add(msgResponse.getMsgResponse().getDetail().getReference());
      return GenAsyncSessionServiceFactory.getGenAsyncService(this.projectName).confirmRequest(confirm, WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:confirm:hash"));
   }

   public OrigineType getOrigineType(String projectName) throws TechnicalConnectorException {
      McnPackageInfo packageInfo = McnConfigUtil.retrievePackageInfo("genericasync." + projectName);
      CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder(projectName);
      return ((CommonInputMapper)Mappers.getMapper(CommonInputMapper.class)).map(commonBuilder.createOrigin(packageInfo));
   }
}
