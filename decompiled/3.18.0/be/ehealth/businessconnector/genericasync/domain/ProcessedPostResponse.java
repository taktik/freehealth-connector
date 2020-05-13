package be.ehealth.businessconnector.genericasync.domain;

import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;

public class ProcessedPostResponse {
   private PostResponse postResponse;
   private Post post;

   public ProcessedPostResponse(PostResponse postResponse, Post post) {
      this.postResponse = postResponse;
      this.post = post;
   }

   public PostResponse getPostResponse() {
      return this.postResponse;
   }

   public Post getPost() {
      return this.post;
   }
}
