package be.ehealth.businessconnector.genericasync.builders.impl;

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
import be.ehealth.business.mycarenetcommons.builders.util.BlobUtil;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.business.mycarenetdomaincommons.domain.async.PostContent;
import be.ehealth.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.businessconnector.genericasync.builders.BuilderFactory;
import be.ehealth.businessconnector.genericasync.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.genericasync.encrypt.BusinessContentEncryptor;
import be.ehealth.businessconnector.genericasync.mappers.CommonInputMapper;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestObjectBuilderImpl implements RequestObjectBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);
   public static final String GENERICASYNC = "genericasync.";
   private static Configuration config = ConfigFactory.getConfigValidator();

   public final Post buildPostRequest(CommonInput commonInput, Blob blob, byte[] xades) {
      Post post = new Post();
      post.setCommonInput(commonInput);
      post.setDetail(blob);
      if (xades != null) {
         post.setXadesT(DomainBlobMapper.mapB64fromByte(xades));
      }

      return post;
   }

   public final Post buildPostRequest(String projectName, PostContent postContent) throws TechnicalConnectorException {
      Blob detail = DomainBlobMapper.mapBlobToCinBlob(postContent.getBlob());
      if (LOG.isDebugEnabled()) {
         ConnectorXmlUtils.dump(detail);
      }

      Post post = new Post();
      post.setDetail(detail);
      post.setCommonInput(CommonInputMapper.mapCommonInputType(RequestBuilderFactory.getCommonBuilder(projectName).createCommonInput(McnConfigUtil.retrievePackageInfo("genericasync." + projectName), postContent.isTest(), postContent.getCommonInputReference())));
      if (postContent.getXades() != null) {
         post.setXadesT(DomainBlobMapper.mapB64fromByte(postContent.getXades()));
      }

      return post;
   }

   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery) {
      Get get = new Get();
      get.setMsgQuery(msgQuery);
      get.setOrigin(origin);
      get.setTAckQuery(tackQuery);
      return get;
   }

   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, QueryParameters queryParameters) {
      Get get = this.buildGetRequest(origin, msgQuery, tackQuery);
      get.setQueryParameters(queryParameters);
      return get;
   }

   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, QueryParameters queryParameters, byte[] replyToEtk) {
      Get get = this.buildGetRequest(origin, msgQuery, tackQuery, queryParameters);
      get.setReplyToEtk(replyToEtk);
      return get;
   }

   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, byte[] replyToEtk) {
      return this.buildGetRequest(origin, msgQuery, tackQuery, (QueryParameters)null, replyToEtk);
   }

   public final Confirm buildConfirmRequest(OrigineType origin, List<MsgResponse> msgResponses, List<TAckResponse> tackResponses) throws TechnicalConnectorException, DataFormatException {
      List<byte[]> msgHashValues = new ArrayList();
      List<byte[]> tackContents = new ArrayList();
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

   public Confirm buildConfirmRequestWithHashes(OrigineType origin, List<byte[]> msgHashValues, List<byte[]> tackContents) {
      Confirm confirm = new Confirm();
      confirm.setOrigin(origin);
      confirm.getMsgHashValues().addAll(msgHashValues);
      confirm.getTAckContents().addAll(tackContents);
      return confirm;
   }

   public Confirm buildConfirmWithReferences(OrigineType origin, GetResponse getResponse) {
      Confirm request = new Confirm();
      request.setOrigin(origin);
      this.addTAckResponseReferencesToConfirm(getResponse, request);
      this.addMsgReferencesToConfirm(getResponse, request);
      return request;
   }

   private void addTAckResponseReferencesToConfirm(GetResponse getResponse, Confirm request) {
      Iterator i$ = getResponse.getReturn().getTAckResponses().iterator();

      while(i$.hasNext()) {
         TAckResponse tAckResponse = (TAckResponse)i$.next();
         request.getTAckReferences().add(tAckResponse.getTAck().getReference());
      }

   }

   private void addMsgReferencesToConfirm(GetResponse getResponse, Confirm request) {
      Iterator i$ = getResponse.getReturn().getMsgResponses().iterator();

      while(i$.hasNext()) {
         MsgResponse msgResponse = (MsgResponse)i$.next();
         request.getMsgRefValues().add(msgResponse.getDetail().getReference());
      }

   }

   public Query createQuery(Integer max, Boolean include) {
      Query query = new Query();
      query.setInclude(include);
      query.setMax(max);
      return query;
   }

   public MsgQuery createMsgQuery(Integer max, Boolean include, String... messageNames) {
      MsgQuery msgQuery = new MsgQuery();
      msgQuery.setInclude(include);
      msgQuery.setMax(max);
      String[] arr$ = messageNames;
      int len$ = messageNames.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String messageName = arr$[i$];
         msgQuery.getMessageNames().add(messageName);
      }

      return msgQuery;
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
   }

   public Post buildPostRequest(String messageName, String projectName, String platformName, Object object, String schemaLocation, InputReference inputReference) throws TechnicalConnectorException {
      if (config.getBooleanProperty("genericasync." + projectName + "." + messageName + ".validation.outgoing", true)) {
         ValidatorHelper.validate(object, schemaLocation);
      }

      return BuilderFactory.getRequestObjectBuilder(projectName).buildPostRequest(messageName, projectName, platformName, ConnectorXmlUtils.toByteArray(object), inputReference);
   }

   public Post buildPostRequest(String messageName, String projectName, String platformName, byte[] xmlByteArray, InputReference inputReference) throws TechnicalConnectorException {
      Validate.notNull(inputReference, "You must define an InputReference", new Object[0]);
      boolean encrypt = StringUtils.isNotBlank(config.getProperty(platformName + ".blobbuilder." + projectName + "." + messageName + ".contentencryption"));
      byte[] businessContent;
      if (encrypt) {
         businessContent = BusinessContentEncryptor.encrypt(projectName, xmlByteArray, platformName, messageName);
      } else {
         businessContent = xmlByteArray;
      }

      BlobBuilder blobBuilder = BlobBuilderFactory.getBlobBuilder(platformName, projectName, messageName);
      be.ehealth.business.mycarenetdomaincommons.domain.Blob blob = blobBuilder.build(businessContent);
      blob.setMessageName(messageName);
      PostContent postContent = PostContent.Builder().blob(blob).commonInputReference(inputReference.getInputReference()).isTest(config.getBooleanProperty("genericasync." + projectName + ".istest", true)).messageName(messageName).xades(encrypt ? null : BlobUtil.generateXades(blob, projectName, platformName)).build();
      return BuilderFactory.getRequestObjectBuilder(projectName).buildPostRequest(projectName, postContent);
   }
}
