package be.cin.nip.async.generic;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RejectInb createRejectInb() {
      return new RejectInb();
   }

   public RejectOutb createRejectOutb() {
      return new RejectOutb();
   }

   public Post createPost() {
      return new Post();
   }

   public InternalPostResponse createInternalPostResponse() {
      return new InternalPostResponse();
   }

   public PostResponseReturn createPostResponseReturn() {
      return new PostResponseReturn();
   }

   public PostResponse createPostResponse() {
      return new PostResponse();
   }

   public TAck createTAck() {
      return new TAck();
   }

   public Get createGet() {
      return new Get();
   }

   public QueryParameters createQueryParameters() {
      return new QueryParameters();
   }

   public MsgQuery createMsgQuery() {
      return new MsgQuery();
   }

   public Query createQuery() {
      return new Query();
   }

   public GetResponse createGetResponse() {
      return new GetResponse();
   }

   public Responses createResponses() {
      return new Responses();
   }

   public Confirm createConfirm() {
      return new Confirm();
   }

   public ConfirmResponse createConfirmResponse() {
      return new ConfirmResponse();
   }

   public MsgResponse createMsgResponse() {
      return new MsgResponse();
   }

   public TAckResponse createTAckResponse() {
      return new TAckResponse();
   }
}
