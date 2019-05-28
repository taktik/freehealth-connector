package be.ehealth.technicalconnector.handler.domain;

import java.net.URI;

public class WsAddressingRelatesTo {
   private String relationshipType;
   private URI releatesTo;

   public String getRelationshipType() {
      return this.relationshipType;
   }

   public void setRelationshipType(String relationshipType) {
      this.relationshipType = relationshipType;
   }

   public URI getReleatesTo() {
      return this.releatesTo;
   }

   public void setReleatesTo(URI releatesTo) {
      this.releatesTo = releatesTo;
   }
}
