package be.ehealth.businessconnector.genericasync.helper;

import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.business.mycarenetdomaincommons.util.WsAddressingUtil;
import be.ehealth.businessconnector.genericasync.builders.BuilderFactory;
import be.ehealth.businessconnector.genericasync.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.genericasync.domain.ConfigName;
import be.ehealth.businessconnector.genericasync.domain.ProcessedPostResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.session.GenAsyncService;
import be.ehealth.businessconnector.genericasync.session.GenAsyncSessionServiceFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;

public class PostHelper {
   private static final String ENCRYPTED = "_ENCRYPTED";
   private String platformName;
   private String projectName;

   public PostHelper(String platformName, String projectName) {
      this.projectName = projectName;
      this.platformName = platformName;
   }

   public ProcessedPostResponse post(Object request, String messageName, String schemaLocation, InputReference inputReference) throws ConnectorException {
      Post post = BuilderFactory.getRequestObjectBuilder(this.projectName).buildPostRequest(messageName, this.projectName, this.platformName, request, schemaLocation, inputReference);
      return new ProcessedPostResponse(this.getPostResponse(this.projectName, post, (String)null), post);
   }

   public ProcessedPostResponse post(byte[] request, String messageName, InputReference inputReference) throws ConnectorException {
      return this.post(request, messageName, (String)null, inputReference);
   }

   public ProcessedPostResponse post(byte[] request, String messageName, ConfigName configName, InputReference inputReference) throws ConnectorException {
      return this.post((byte[])request, messageName, (ConfigName)configName, (String)null, inputReference);
   }

   public ProcessedPostResponse post(Object request, String messageName, String schemaLocation, String recipient, InputReference inputReference) throws ConnectorException {
      Post post = BuilderFactory.getRequestObjectBuilder(this.projectName).buildPostRequest(messageName, this.projectName, this.platformName, request, schemaLocation, inputReference);
      return new ProcessedPostResponse(this.getPostResponse(this.projectName, post, recipient), post);
   }

   private PostResponse getPostResponse(String projectName, Post post, String recipient) throws ConnectorException {
      PostResponse responsePost = this.post(projectName, post, recipient);
      this.validateResponse(responsePost);
      return responsePost;
   }

   public ProcessedPostResponse post(byte[] request, String messageName, String recipient, InputReference inputReference) throws ConnectorException {
      Post post = BuilderFactory.getRequestObjectBuilder(this.projectName).buildPostRequest(messageName, this.projectName, this.platformName, request, inputReference);
      return new ProcessedPostResponse(this.getPostResponse(this.projectName, post, recipient), post);
   }

   public ProcessedPostResponse post(byte[] request, String messageName, ConfigName configName, String recipient, InputReference inputReference) throws ConnectorException {
      Post post = BuilderFactory.getRequestObjectBuilder(this.projectName).buildPostRequest(messageName, this.projectName, this.platformName, configName, request, inputReference);
      return new ProcessedPostResponse(this.getPostResponse(this.projectName, post, recipient), post);
   }

   private void validateResponse(PostResponse responsePost) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      ResponseObjectBuilder responseBuilder = BuilderFactory.getResponseObjectBuilder();
      responseBuilder.handlePostResponse(responsePost);
   }

   private PostResponse post(String serviceName, Post post, String recipient) throws ConnectorException {
      GenAsyncService service = GenAsyncSessionServiceFactory.getGenAsyncService(serviceName);
      WsAddressingHeader header = WsAddressingUtil.createHeader(recipient, "urn:be:cin:nip:async:generic:post:msg");
      return service.postRequest(post, header);
   }
}
