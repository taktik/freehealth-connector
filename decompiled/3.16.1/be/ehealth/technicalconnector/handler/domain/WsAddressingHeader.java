package be.ehealth.technicalconnector.handler.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WsAddressingHeader {
   private String mustUnderstand = "1";
   private URI messageID;
   private List<WsAddressingRelatesTo> relatesTo = new ArrayList();
   private URI to;
   private URI action;
   private String from;
   private String replyTo;
   private String faultTo;

   public WsAddressingHeader(URI action) {
      this.action = action;
   }

   public URI getMessageID() {
      return this.messageID;
   }

   public void setMessageID(URI messageID) {
      this.messageID = messageID;
   }

   public URI getTo() {
      return this.to;
   }

   public void setTo(URI to) {
      this.to = to;
   }

   public URI getAction() {
      return this.action;
   }

   public void setAction(URI action) {
      this.action = action;
   }

   public String getFrom() {
      return this.from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getReplyTo() {
      return this.replyTo;
   }

   public void setReplyTo(String replyTo) {
      this.replyTo = replyTo;
   }

   public String getFaultTo() {
      return this.faultTo;
   }

   public void setFaultTo(String faultTo) {
      this.faultTo = faultTo;
   }

   public List<WsAddressingRelatesTo> getRelatesTo() {
      return this.relatesTo;
   }

   public String getMustUnderstand() {
      return this.mustUnderstand;
   }

   public void setMustUnderstand(boolean mustUnderstand) {
      if (mustUnderstand) {
         this.mustUnderstand = "1";
      } else {
         this.mustUnderstand = "0";
      }

   }
}
