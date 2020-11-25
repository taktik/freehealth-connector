package org.taktik.connector.business.genericasync.builders.impl;

import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgQuery;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.Query;
import be.cin.nip.async.generic.QueryParameters;
import be.cin.nip.async.generic.TAckResponse;
import be.cin.types.v1.Blob;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.genericasync.builders.BuilderFactory;
import org.taktik.connector.business.genericasync.builders.RequestObjectBuilder;
import org.taktik.connector.business.genericasync.domain.ConfigName;
import org.taktik.connector.business.genericasync.encrypt.BusinessContentEncryptor;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.mappers.CommonInputMapper;
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.InputReference;
import org.taktik.connector.business.mycarenetdomaincommons.domain.async.PostContent;
import org.taktik.connector.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.validator.ValidatorHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RequestObjectBuilderImpl implements RequestObjectBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);
   private static Configuration config = ConfigFactory.getConfigValidator();

   @Override
   public final Post buildPostRequest(CommonInput commonInput, Blob blob, byte[] xades) {
      Post post = new Post();
      post.setCommonInput(commonInput);
      post.setDetail(blob);
      if (xades != null) {
         post.setXadesT(DomainBlobMapper.mapB64fromByte(xades));
      }

      return post;
   }

   @Override
   public final Post buildPostRequest(String projectName, PostContent postContent, String licenseUsername, String licensePassword, String packageName) throws TechnicalConnectorException {
      Blob detail = DomainBlobMapper.mapBlobToCinBlob(postContent.getBlob());
      if (LOG.isDebugEnabled()) {
         ConnectorXmlUtils.dump(detail);
      }

      Post post = new Post();
      post.setDetail(detail);
      post.setCommonInput(CommonInputMapper.mapCommonInputType(RequestBuilderFactory.getCommonBuilder(projectName).createCommonInput(McnConfigUtil.retrievePackageInfo("genericasync." + projectName, licenseUsername, licensePassword, packageName), postContent.isTest(), postContent.getCommonInputReference())));
      if (postContent.getXades() != null) {
         post.setXadesT(DomainBlobMapper.mapB64fromByte(postContent.getXades()));
      }

      return post;
   }

   @Override
   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery) {
      Get get = new Get();
      get.setMsgQuery(msgQuery);
      get.setOrigin(origin);
      get.setTAckQuery(tackQuery);
      return get;
   }

   @Override
   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, QueryParameters queryParameters) {
      Get get = this.buildGetRequest(origin, msgQuery, tackQuery);
      get.setQueryParameters(queryParameters);
      return get;
   }

   @Override
   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, QueryParameters queryParameters, byte[] replyToEtk) {
      Get get = this.buildGetRequest(origin, msgQuery, tackQuery, queryParameters);
      get.setReplyToEtk(replyToEtk);
      return get;
   }

   @Override
   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, byte[] replyToEtk) {
      return this.buildGetRequest(origin, msgQuery, tackQuery, null, replyToEtk);
   }

   @Override
   public final Confirm buildConfirmRequest(OrigineType origin, List<MsgResponse> msgResponses, List<TAckResponse> tackResponses) {
      List<byte[]> msgHashValues = new ArrayList<>();
      List<byte[]> tackContents = new ArrayList<>();
      Iterator i$;
      if (msgResponses != null && !msgResponses.isEmpty()) {
         i$ = msgResponses.iterator();

         while(i$.hasNext()) {
            MsgResponse msgResponse = (MsgResponse)i$.next();
            msgHashValues.add(msgResponse.getDetail().getHashValue());
         }
      } else {
         msgHashValues.add(new byte[0]);
      }

      if (tackResponses != null && !tackResponses.isEmpty()) {
         i$ = tackResponses.iterator();

         while(i$.hasNext()) {
            TAckResponse tackResponse = (TAckResponse)i$.next();
            tackContents.add(tackResponse.getTAck().getValue());
         }
      } else {
         tackContents.add(new byte[0]);
      }

      return this.buildConfirmRequestWithHashes(origin, msgHashValues, tackContents);
   }

   @Override
   public Confirm buildConfirmRequestWithHashes(OrigineType origin, List<byte[]> msgHashValues, List<byte[]> tackContents) {
      Confirm confirm = new Confirm();
      confirm.setOrigin(origin);
      confirm.getMsgHashValues().addAll(msgHashValues);
      confirm.getTAckContents().addAll(tackContents);
      return confirm;
   }

   @Override
   public Confirm buildConfirmWithReferences(OrigineType origin, GetResponse getResponse) {
      Confirm request = new Confirm();
      request.setOrigin(origin);
      this.addTAckResponseReferencesToConfirm(getResponse, request);
      this.addMsgReferencesToConfirm(getResponse, request);
      return request;
   }

   private void addTAckResponseReferencesToConfirm(GetResponse getResponse, Confirm request) {
      for (TAckResponse tAckResponse : getResponse.getReturn().getTAckResponses()) {
         request.getTAckReferences().add(tAckResponse.getTAck().getReference());
      }
   }

   private void addMsgReferencesToConfirm(GetResponse getResponse, Confirm request) {
      for (MsgResponse msgResponse : getResponse.getReturn().getMsgResponses()) {
         request.getMsgRefValues().add(msgResponse.getDetail().getReference());
      }
   }

   @Override
   public Query createQuery(Integer max, Boolean include) {
      Query query = new Query();
      query.setInclude(include);
      query.setMax(max);
      return query;
   }

   @Override
   public MsgQuery createMsgQuery(Integer max, Boolean include, String... messageNames) {
      MsgQuery msgQuery = new MsgQuery();
      msgQuery.setInclude(include);
      msgQuery.setMax(max);

      for (String messageName : messageNames) {
         msgQuery.getMessageNames().add(messageName);
      }

      return msgQuery;
   }

   public void initialize(Map<String, Object> parameterMap) {
   }

   @Override
   public Post buildPostRequest(String messageName, String projectName, String platformName, Object object, String schemaLocation, KeyStoreCredential credential, KeyDepotManager keyDepotManager, InputReference inputReference, String licenseUsername, String licensePassword, String packageName) throws TechnicalConnectorException, InstantiationException, GenAsyncBusinessConnectorException {
      if (config.getBooleanProperty("genericasync." + projectName + "." + messageName + ".validation.outgoing", true)) {
         ValidatorHelper.Companion.validate(object, schemaLocation);
      }

      return this.buildPostRequest(messageName, projectName, platformName, ConnectorXmlUtils.toByteArray(object), credential, keyDepotManager, inputReference, licenseUsername, licensePassword, packageName);
   }

   @Override
   public Post buildPostRequest(String messageName, String projectName, String platformName, byte[] xmlByteArray, KeyStoreCredential credential, KeyDepotManager keyDepotManager, InputReference inputReference, String licenseUsername, String licensePassword, String packageName) throws TechnicalConnectorException, GenAsyncBusinessConnectorException, InstantiationException {
      return this.buildPostRequest(messageName, projectName, platformName, null, xmlByteArray, credential, keyDepotManager, inputReference, licenseUsername, licensePassword, packageName);
   }

   @Override
   public Post buildPostRequest(String messageName, String projectName, String platformName, ConfigName configName, byte[] xmlByteArray, KeyStoreCredential credential, KeyDepotManager keyDepotManager, InputReference inputReference, String licenseUsername, String licensePassword, String packageName) throws TechnicalConnectorException, InstantiationException, GenAsyncBusinessConnectorException {
      String extraConfig = configName == null ? messageName : configName.name();
      Validate.notNull(inputReference, "You must define an InputReference");
      boolean encrypt = StringUtils.isNotBlank(config.getProperty(platformName + ".blobbuilder." + projectName + "." + extraConfig + ".contentencryption"));
      byte[] businessContent;
      if (encrypt) {
         businessContent = BusinessContentEncryptor.encrypt(projectName, xmlByteArray, credential, keyDepotManager, platformName, extraConfig);
      } else {
         businessContent = xmlByteArray;
      }

      BlobBuilder blobBuilder = BlobBuilderFactory.getBlobBuilder(platformName, projectName, extraConfig);
      org.taktik.connector.business.mycarenetdomaincommons.domain.Blob blob = blobBuilder.build(businessContent);
      blob.setMessageName(messageName);
      PostContent postContent = PostContent.Builder().blob(blob).commonInputReference(inputReference.getInputReference()).isTest(config.getBooleanProperty("genericasync." + projectName + ".istest", true)).messageName(messageName).xades(encrypt ? null : BlobUtil.generateXades(credential, blob, projectName, platformName)).build();
      return BuilderFactory.getRequestObjectBuilder(projectName).buildPostRequest(projectName, postContent, licenseUsername, licensePassword, packageName);
   }
}
